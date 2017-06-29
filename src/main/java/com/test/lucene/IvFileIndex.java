package com.test.lucene;
  
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;  
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
  
public class IvFileIndex  
{  
  
	private static int length = "E:/runtime-ZTP2.0/".length();
    private static List<File> fileList = new ArrayList<File>();  
    
    private static Pattern pFilterDir = Pattern.compile("Client|Data|Function|Job|TU|Var");
    private static Pattern pFilterFile = Pattern.compile("\\.(client|var|groovy|job)$");
    private static Pattern pZtpDir = Pattern.compile("Case|CTU");
    private static Pattern pZtpDirEx = Pattern.compile("\\.(case|ctu)$");
    
    public static List<File> getFileList()
    {
    	return fileList;
    }
    
    public static void listAllFile(File fileDir)
    {

    	File[] files = fileDir.listFiles();  
        for (File file : files)  
        {   
        	String seg = file.getAbsolutePath().substring(length).split("/")[0];
        	
            if (file.isDirectory() && pFilterDir.matcher(seg).find())  
            {  
                listAllFile(file);
            }
            if (file.isDirectory() && pZtpDir.matcher(seg).find() && !pZtpDirEx.matcher(file.getName()).find())  
            {
            	listAllFile(file); 
            }            
            if (file.isDirectory() && pZtpDir.matcher(seg).find() && pZtpDirEx.matcher(file.getName()).find())  
            {
            	fileList.add(file); 
            }
            if (file.isFile() && pFilterDir.matcher(seg).find()) // && findLocalPath.segmentCount() > 2
            {  
            	fileList.add(file); 
            }            
        }  
    }  
 
    public static void createIndex(List<String> FilePathList,String IndexPath) throws Exception  
    {

        File indexDir = new File(IndexPath);
        
//        Analyzer luceneAnalyzer = new SmartChineseAnalyzer(Version.LUCENE_42);  
        Analyzer luceneAnalyzer = new StandardAnalyzer(Version.LUCENE_42);
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_42, luceneAnalyzer);  
        config.setOpenMode(org.apache.lucene.index.IndexWriterConfig.OpenMode.CREATE);  
  
        Directory fsDir = new SimpleFSDirectory(indexDir);
        
        if(IndexWriter.isLocked(fsDir)){
            IndexWriter.unlock(fsDir);  
        }  

        IndexWriter indexWriter = new IndexWriter(fsDir, config);
        long startTime = new Date().getTime();
        fileList = new ArrayList<File>();
        
        for(String inFile:FilePathList)
        {
        	File fileDir = new File(inFile);
            if(!fileDir.exists())
            {
            	continue;
            }
            listAllFile(fileDir);
        }
        long endTime = new Date().getTime();  
        System.out.println("ListAllFile 耗费时间：" + (endTime - startTime) + " 毫秒!");  
        
        startTime = new Date().getTime();  

        indexWriter.deleteAll();
  
        // 增加document到索引去
        /*如果是case、ctu遍历目录加载文件*/
        try
        {
	        for (File inFile : fileList)
	        {
	        	//System.out.println("fieldBody->"+inFile.getPath().toString().substring(GDefinition.rootPath.length()+1, inFile.getPath().length()));
	        	/*IPath findLocalPath = new Path(inFile.getPath().toString().substring(GDefinition.rootPath.length()+1, inFile.getPath().length()));
	            if(inFile.isDirectory() && pZtpDir.matcher(findLocalPath.segment(1)).find())*/
	        	 if(inFile.isDirectory())
	            {
	            	Document document = new Document();
	                Field fieldParent = new StringField("parent", inFile.getParent() == null ? null:inFile.getParentFile().getName() , Field.Store.YES);
	                Field fieldName = new StringField("name", inFile.getName(), Field.Store.YES);
	                Field fieldNameLower = new StringField("namelower", inFile.getName().toLowerCase(), Field.Store.YES);
	                Field fieldPath = new StringField("path", inFile.getPath(), Field.Store.YES);
	                Field fieldBody = new StringField("body", inFile.getName(), Field.Store.YES);
	            	for(File pFile:inFile.listFiles())
	            	{
		                if(pFile.isDirectory())
		                {
		              	   continue;
		                }
		                if(pFile.isFile() && !pFile.getName().endsWith(".bak"))
		                {
		              	   document.add(new TextField(pFile.getName(), new BufferedReader(new InputStreamReader(new FileInputStream(pFile), "UTF-8"))));
		                }

	            	}
	                document.add(fieldParent);
	                document.add(fieldName);
	                document.add(fieldNameLower);
	                document.add(fieldPath);
	                document.add(fieldBody);
	            	indexWriter.addDocument(document);
	            	continue;
	            }
	            if(inFile.isFile() || inFile.isDirectory())
	            {
	                Document document = new Document();  
	                Field fieldParent = new StringField("parent", inFile.getParent() == null ? null:inFile.getParentFile().getName() , Field.Store.YES);
	                Field fieldName = new StringField("name", inFile.getName(), Field.Store.YES);
	                Field fieldNameLower = new StringField("namelower", inFile.getName().toLowerCase(), Field.Store.YES);
	                Field fieldPath = new StringField("path", inFile.getPath(), Field.Store.YES); 
	                Field fieldBody = null;
	                if(inFile.isFile())
	                {
	              	   fieldBody = new TextField("body", new BufferedReader(new InputStreamReader(new FileInputStream(inFile), "UTF-8")));  
	                }
	                if(inFile.isDirectory())
	                {
	              	   fieldBody = new StringField("body", inFile.getName(), Field.Store.YES);
	                }
	                document.add(fieldParent);
	                document.add(fieldName);
	                document.add(fieldNameLower);
	                document.add(fieldPath);  
	                document.add(fieldBody);  
	                indexWriter.addDocument(document);
	            } 
	        }
	        indexWriter.forceMerge(1000); 
          }catch(Exception e)
          {
              e.printStackTrace();
          }finally
          {
        	  indexWriter.close();  
          }  
        // 测试一下索引的时间  
        endTime = new Date().getTime();  
        System.out.println("索引耗费时间：" + (endTime - startTime) + " 毫秒!");  
    }    
    
    public static void main(String[] args) throws Exception  
    {  
/*    	List<String> aa = new ArrayList<String>();
    	aa.add("D:\\AUTO_TOOL\\runtime-EclipseApplication\\ZTP2.0-Test");
        createIndex(aa,"D:\\Lucene_index\\index",null);
    	
//        Pattern p = Pattern.compile("\\.(client|var)$");
//        Matcher m = p.matcher("aaa.var");
//    	System.out.println(m.find());
*/    }
} 