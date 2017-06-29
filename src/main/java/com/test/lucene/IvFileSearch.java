package com.test.lucene; 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.xml.ParserException;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiPhraseQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.RegexpQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/*import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.ztesoft.zsmart.test.exec.executor.yaml.ExecTestCaseStep;
import com.ztesoft.zsmart.test.wsexplorer.ui.definition.GDefinition;
import com.ztesoft.zsmart.test.wsexplorer.ui.fileoperate.Combination.common.permutation.Permutation;
import com.ztesoft.zsmart.ztp.exectypes.ExecTypeConstants;
import com.ztesoft.zsmart.ztp.exectypes.TypesConstructor;
import com.ztesoft.zsmart.ztp.tool.core.ExternalHelper;*/

public class IvFileSearch  
{  
	
	/*public static TypesConstructor Type_Constructor = null;*/
	public static Query LuceneTermQuery(Term term)
	{
		return new TermQuery(term);
	}

	public static FuzzyQuery LuceneFuzzyQuery(Term term)
	{
		return new FuzzyQuery(term);
	}
	
	
	public static PrefixQuery LucenePrefixQuery(Term term)
	{
		return new PrefixQuery(term);
	}	

	public static Query LuceneQueryParser(String column,String querystring) throws ParseException
	{
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_42);
		QueryParser parser=new QueryParser(Version.LUCENE_42,column,analyzer);
		parser.setLowercaseExpandedTerms(false);
		return parser.parse(querystring);
	}
	
//	public static Query LuceneBooleanQuery()
//	{
//		
//	}
	
	public static Query LuceneNumericRangeQuery(String field,int min,int max,boolean minInclusive,boolean maxInclusive)
	{
		return NumericRangeQuery.newIntRange(field, min, max, minInclusive, maxInclusive);
	}
	
	public static Query LuceneTermRangeQuery(String field,String lowerTerm,String upperTerm,boolean includeLower,boolean includeUpper)
	{
		return  TermRangeQuery.newStringRange(field, lowerTerm, upperTerm, includeLower, includeUpper);
	}
	
//	public static Query LucenePhraseQuery()
//	{
//		
//	}
	
	public static MultiPhraseQuery LuceneMultiPhraseQuery(Map<String,Term> termList)
	{
		MultiPhraseQuery query=new MultiPhraseQuery();
		for(String field:termList.keySet())
		{
		   query.add(termList.get(field));
		}
		return query;
	}
	
	public static RegexpQuery LuceneRegexpQuery(Term term)
	{
		RegexpQuery query=new RegexpQuery(term);
		return query;
	}
	
	public static WildcardQuery LuceneWildcardQuery(Term term)
	{
		WildcardQuery query = new WildcardQuery(term);
		return query;
	}
	
//	public static SpanQuery LuceneSpanQuery(Term term)
//	{
//		SpanQuery query = new Span
//		return query;
//	}
	
	private static List<File> fileList = new ArrayList<File>();
	
	private static void listAllFile(File fileDir)
	{
		
		if(fileDir.isFile() && !fileDir.getName().startsWith(".") && !FilenameUtils.getExtension(fileDir.getName()).equals("bak"))
		{
			fileList.add(fileDir);
		}
		if(fileDir.isDirectory() && !fileDir.getName().startsWith("."))
		{
			for(File nowFile : fileDir.listFiles())
			{
				if(nowFile.isFile() && !nowFile.getName().startsWith(".") && !FilenameUtils.getExtension(nowFile.getName()).equals("bak"))
				{
					fileList.add(nowFile);
				}
				if(nowFile.isDirectory())
				{
					listAllFile(nowFile);
				}	
			}
		}
	}
	

	private static Pattern pFilterFile = Pattern.compile("\\.(case|ctu|job)$");
	public static HashMap<String,String> searchBody(String IndexPath,String type,String queryString,boolean CaseSensitiveFlag) throws IOException, ParseException
	{
		HashMap<String,String> searchList = new HashMap<String,String>();
		fileList.clear();
		try
		{
			File indexDir = new File(IndexPath);
			listAllFile(indexDir);
			List<File> SearchFileList = fileList;
			for (File inFile : SearchFileList)
			{
				
				if(searchList.containsKey(inFile.getPath()) || searchList.containsKey(inFile.getParentFile().getPath()))
				{
					continue;
				}
				if(inFile.isFile() && FileUtils.readFileToString(inFile,"utf-8").contains(queryString) && CaseSensitiveFlag)
				{					
					if(pFilterFile.matcher(inFile.getParentFile().getName()).find())
					{
					   searchList.put(inFile.getParentFile().getPath(), inFile.getParentFile().getName());
					}else
					{
					   searchList.put(inFile.getPath(), inFile.getName());
					}
				}
				if(inFile.isFile() && FileUtils.readFileToString(inFile,"utf-8").toLowerCase().contains(queryString.toLowerCase()) && !CaseSensitiveFlag)
				{					
					if(pFilterFile.matcher(inFile.getParentFile().getName()).find())
					{
					   searchList.put(inFile.getParentFile().getPath(), inFile.getParentFile().getName());
					}else
					{
					   searchList.put(inFile.getPath(), inFile.getName());
					}
				}				
			}
		}
		finally
		{
			return searchList;
		}
	}	

	private static String conStr(List<String> ls)
	{
		String res = "";
		if(ls == null || ls.size()<=0)
		{
			return null;
		}
		for(String s :ls)
		{
			res += s+" ";
		}
		return res;
	}
	
	
	private static Pattern pFilterCaseNameDescriptionCaseTextInfo = Pattern.compile("\\.(case)$");
	/*public static HashMap<String,String> searchCaseNameDescriptionCaseTextInfo(String IndexPath,String type,String queryString,boolean CaseSensitiveFlag) throws IOException, ParseException
	{
		HashMap<String,String> searchList = new HashMap<String,String>();
		fileList.clear();
		try
		{
			File indexDir = new File(IndexPath);
			listAllFile(indexDir);
			List<File> SearchFileList = fileList;
			List<String> arraylist = new ArrayList<String>();
			String[] queryListin = queryString.split(" ");
			for(String in:queryListin)
			{
				if(in != null && !in.trim().equals(""))
				{
					arraylist.add(in);
				}
			}
			
			for (File inFile : SearchFileList)
			{
				
				if(searchList.containsKey(inFile.getPath()) || searchList.containsKey(inFile.getParentFile().getPath()))
				{
					continue;
				}
				for(int i = arraylist.size();i>0;i--)
				{
					for(List<String> list : Permutation.of(arraylist,i))
					{
						String queryxh = conStr(list).trim();
//						System.out.println("->"+queryxh);
						if(searchList.containsKey(inFile.getPath()) || searchList.containsKey(inFile.getParentFile().getPath()))
						{
							continue;
						}
						if(inFile.getParent() != null && inFile.getParentFile().getName().endsWith(".case"))
						{
							if(CaseSensitiveFlag && inFile.getParentFile().getName().contains(queryxh))
							{
								searchList.put(inFile.getParentFile().getPath(), inFile.getParentFile().getName());
								continue;
							}
							if(!CaseSensitiveFlag && inFile.getParentFile().getName().toLowerCase().contains(queryxh.toLowerCase()))
							{
								searchList.put(inFile.getParentFile().getPath(), inFile.getParentFile().getName());
								continue;
							}
						}
						if(inFile.isFile() && (inFile.getName().equals("case.desc") || inFile.getName().equals("case.txt") || inFile.getName().equals("case.xml")))
						{
							if(inFile.isFile() && FileUtils.readFileToString(inFile,"utf-8").contains(queryxh) && CaseSensitiveFlag)
							{					
								if(pFilterCaseNameDescriptionCaseTextInfo.matcher(inFile.getParentFile().getName()).find())
								{
								   searchList.put(inFile.getParentFile().getPath(), inFile.getParentFile().getName());
								}else
								{
								   searchList.put(inFile.getPath(), inFile.getName());
								}
							}
							if(inFile.isFile() && FileUtils.readFileToString(inFile,"utf-8").toLowerCase().contains(queryxh.toLowerCase()) && !CaseSensitiveFlag)
							{					
								if(pFilterCaseNameDescriptionCaseTextInfo.matcher(inFile.getParentFile().getName()).find())
								{
								   searchList.put(inFile.getParentFile().getPath(), inFile.getParentFile().getName());
								}else
								{
								   searchList.put(inFile.getPath(), inFile.getName());
								}
							}	
						}
					}
				}				
			}
		}
		finally
		{
			return searchList;
		}
	}	*/
	
	
	private static Pattern pFilterScriptFile = Pattern.compile("\\.(script)$|\\w*-\\w*-\\w*-\\w*-\\w*");
	public static HashMap<String,String> searchScriptBody(String IndexPath,String type,String queryString,boolean CaseSensitiveFlag) throws IOException, ParseException
	{
		HashMap<String,String> searchList = new HashMap<String,String>();
		fileList.clear();
		try
		{
			File indexDir = new File(IndexPath);
			listAllFile(indexDir);
			List<File> SearchFileList = fileList;
			for (File inFile : SearchFileList)
			{
				
				if(searchList.containsKey(inFile.getPath()) || searchList.containsKey(inFile.getParentFile().getPath()))
				{
					continue;
				}
				if(inFile.isFile() && FileUtils.readFileToString(inFile,"utf-8").contains(queryString) && CaseSensitiveFlag)
				{					
					if(pFilterScriptFile.matcher(inFile.getName()).find())
					{
					   searchList.put(inFile.getParentFile().getPath(), inFile.getParentFile().getName());
					}
				}
				if(inFile.isFile() && FileUtils.readFileToString(inFile,"utf-8").toLowerCase().contains(queryString.toLowerCase()) && !CaseSensitiveFlag)
				{					
					if(pFilterScriptFile.matcher(inFile.getName()).find())
					{
					   searchList.put(inFile.getParentFile().getPath(), inFile.getParentFile().getName());
					}
				}				
			}
		}
		finally
		{
			return searchList;
		}
	}		
	

	private static Pattern pFilterScriptStepFile = Pattern.compile("\\.(script)$");
	/*public static HashMap<String,String> searchScriptStepBody(String IndexPath,String type,String queryString,boolean CaseSensitiveFlag) throws IOException, ParseException
	{
		HashMap<String,String> searchList = new HashMap<String,String>();
		fileList.clear();
		try
		{
			File indexDir = new File(IndexPath);
			listAllFile(indexDir);
			List<File> SearchFileList = fileList;
			for (File inFile : SearchFileList)
			{
				if(searchList.containsKey(inFile.getPath()))
				{
					continue;
				}
				if(inFile.isFile() && pFilterScriptStepFile.matcher(inFile.getName()).find())
				{					
					HashMap<String, Object> filestruct = parserData(inFile);
					for(Entry<String, Object> structkey:filestruct.entrySet())
					{
						if(structkey==null || structkey.getValue()==null)
						{
							continue;
						}
						List<ExecTestCaseStep> caseList = (List<ExecTestCaseStep>) structkey.getValue();
						for(ExecTestCaseStep node : caseList)
						{
						    if(CaseSensitiveFlag)
						    {
								if(type.equals("stepName") && node.getStepName()!=null && node.getStepName().equals(queryString))
								{
									searchList.put(inFile.getParentFile().getPath(), inFile.getParentFile().getName());
								}
								if(type.equals("stepId") && node.getStepId()!=null && node.getStepId().equals(queryString))
								{
									searchList.put(inFile.getParentFile().getPath(), inFile.getParentFile().getName());
								}
						    }
							else
							{
								if(type.equals("stepName") && node.getStepName()!=null && node.getStepName().toLowerCase().equals(queryString.toLowerCase()))
								{
									searchList.put(inFile.getParentFile().getPath(), inFile.getParentFile().getName());
								}
								if(type.equals("stepId") && node.getStepId()!=null && node.getStepId().toLowerCase().equals(queryString.toLowerCase()))
								{
									searchList.put(inFile.getParentFile().getPath(), inFile.getParentFile().getName());
								}
							}
						}
						
						if(structkey.equalsIgnoreCase(type) && filestruct.get(structkey).toString().equalsIgnoreCase(queryString))
						{
							searchList.put(inFile.getParentFile().getPath(), inFile.getParentFile().getName());
						}
						
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			return searchList;
		}
	}		*/
	
    public static HashMap<String,String> search(String IndexPath,String type,String queryString,boolean CaseSensitiveFlag) throws IOException, ParseException  
    {
    	HashMap<String,String> searchList = new HashMap<String,String>();
        File indexDir = new File(IndexPath);  
        IndexReader reader = DirectoryReader.open(FSDirectory.open(indexDir));  
        IndexSearcher searcher = new IndexSearcher(reader);
        IndexSearcher searcherXml = new IndexSearcher(reader);  	
        
  
        TopDocs docs = null;  
        TopDocs docsXml = null;  
        
        long startTime = new Date().getTime();  
        
        try
        {
  
		//		String rootPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
		        if (searcher != null)  
		        {  
		        	switch(type)
		        	{
//		        	   case "name" : docs = searcher.search(LuceneWildcardQuery(new Term("namelower",queryString.toLowerCase())), 1000000000); break;
		        	   case "name" : docs = searcher.search(LuceneWildcardQuery(new Term("name","*"+queryString+"*.case")), 1000000000); break;
		        	   case "case name" :
		        		   if(CaseSensitiveFlag)
		        		   {
		        		      docs = searcher.search(LuceneWildcardQuery(new Term("name","*"+queryString.toLowerCase()+"*.case")), 1000000000); break;
		        		   }else
		        		   {
		        			   docs = searcher.search(LuceneWildcardQuery(new Term("namelower","*"+queryString.toLowerCase()+"*.case")), 1000000000); break; 
		        		   }
//		        	   case "body" : docs = searcher.search(LuceneQueryParser("case.script",queryString), 1000000000); break;
//		        	   case "body" : docs = searcher.search(LuceneWildcardQuery(new Term("case.script",queryString.toLowerCase())), 1000000000); break;
		        	   case "pass case" :   docs = searcher.search(LuceneQueryParser("case.log","Result AND = AND pass"), 1000000000);
		        	                        docsXml = searcherXml.search(LuceneQueryParser("case.xml","result value AND = AND \"pass\""), 1000000000);
		        	                        break;
		        	   case "fail case" :   docs = searcher.search(LuceneQueryParser("case.log","Result AND = AND fail"), 1000000000);
		        	                        docsXml = searcherXml.search(LuceneQueryParser("case.xml","result value AND = AND \"fail\""), 1000000000);		        	   
		        	                        break;
		        	   /*case "zmp transid" : docs = searcher.search(LuceneQueryParser("case.zmp",GDefinition.C_Attr_ZmpOrderID+" AND = AND "+queryString.replaceAll("\\*", "")), 1000000000);
		        	                        docsXml = searcherXml.search(LuceneQueryParser("case.xml","zmpId value=\""+queryString.replaceAll("\\*", "")+"\""), 1000000000);
		        	                        break;*/
		        	   default : docs = searcher.search(LuceneRegexpQuery(new Term("name",queryString)), 1000000000);
		        	}

		        	if(docsXml!=null)
		        	{
			            for(ScoreDoc now :docsXml.scoreDocs)
			            {
							
			                Document targetDoc = searcher.doc(now.doc);
			                String parent = targetDoc.get("parent");
			                String name = targetDoc.get("name");
			                String path = targetDoc.get("path");
			
			                if(type.equals("body") ||
			                		type.equals("pass case") ||
			                		type.equals("fail case") ||
			                		type.equals("zmp transid")
			                		)
			                {
				                if(parent != null && 
				                		(parent.endsWith(".case") || 
				                		 parent.endsWith(".ctu")
				                		 )
				                  )
				                {
				                	 name = parent == null ? name:parent;
				                	 path = new File(path).getParentFile().getPath(); 
				                }
				                 
				            	if(searchList.containsKey(targetDoc.get(name)) || name.equals(""))
				            	{
				            		continue;
				            	}
				                
			//	            	System.out.println("name:" + name +"\npath:" + path);
	//			                searchList.put(name, path);
				                searchList.put(path, name);
			                }
			                if(type.equals("case name"))
			                {
				                if(parent != null && 
				                		(parent.endsWith(".case") || 
				                		 parent.endsWith(".ctu")) || 
				                   name.equals("") 
				                  )
				                {
			                       continue; 
				                }
	//			                searchList.put(name, path);
				                searchList.put(path, name); /*这里反过来放置名字重复导致的覆盖*/
			                }
			            }		        	
		        	}
		            for(ScoreDoc now :docs.scoreDocs)
		            {
						
		                Document targetDoc = searcher.doc(now.doc);
		                String parent = targetDoc.get("parent");
		                String name = targetDoc.get("name");
		                String path = targetDoc.get("path");
		
		                if(type.equals("body") ||
		                		type.equals("pass case") ||
		                		type.equals("fail case") ||
		                		type.equals("zmp transid")
		                		)
		                {
			                if(parent != null && 
			                		(parent.endsWith(".case") || 
			                		 parent.endsWith(".ctu")
			                		 )
			                  )
			                {
			                	 name = parent == null ? name:parent;
			                	 path = new File(path).getParentFile().getPath(); 
			                }
			                 
			            	if(searchList.containsKey(targetDoc.get(name)) || name.equals(""))
			            	{
			            		continue;
			            	}
			                
		//	            	System.out.println("name:" + name +"\npath:" + path);
//			                searchList.put(name, path);
			                searchList.put(path, name);
		                }
		                if(type.equals("case name"))
		                {
			                if(parent != null && 
			                		(parent.endsWith(".case") || 
			                		 parent.endsWith(".ctu")) || 
			                   name.equals("") 
			                  )
			                {
		                       continue; 
			                }
//			                searchList.put(name, path);
			                searchList.put(path, name); /*这里反过来放置名字重复导致的覆盖*/
		                }
		            }
		        } 
		        long endTime = new Date().getTime();
				System.out.println("索引耗费时间：" + (endTime - startTime) + " 毫秒!");
		        return searchList;
        }finally
        {
        	 reader.close();
        }
    } 
	
	
/*	public static Constructor getConstructor() throws CoreException {
		if(Type_Constructor == null) {
			IConfigurationElement cfgElement = ExternalHelper
					.getExtensionElementByAttribute(
							ExecTypeConstants.EXECUTOR_CONSTRUCTOR_EXTENSION_ID,
							ExecTypeConstants.EXECUTOR_CONSTRUCTOR_ELEMENT_NAME,
							ExecTypeConstants.EXECUTOR_CONSTRUCTOR_ATTRIBUTE_ID,
							ExecTypeConstants.DEFAULT_CONSTRUCTOR_ID);

			if (cfgElement == null) {
				// TODO
				throw new CoreException(new Status(Status.ERROR, "",
						"load executor constructor failed! "));
			}
			
			Type_Constructor = (TypesConstructor) cfgElement
					.createExecutableExtension(ExecTypeConstants.EXECUTOR_CONSTRUCTOR_ATTRIBUTE_EXECUTABLE);
			
		}
		return Type_Constructor;		

	}  */  
    
    
	/*public static <T> T parserData(File file) throws Exception {
		if (file == null) {
			throw new Exception("Source not found."+file.getAbsolutePath());
		}
		System.out.println(file.toString());
		InputStreamReader is = null;
		Object obj = null;
		try {
			Constructor constructor = getConstructor();
			Yaml simpleYaml = new Yaml(constructor);
			is = new InputStreamReader(new FileInputStream(file),"UTF-8");
			obj = simpleYaml.load(is);
			
		}
		finally {
			try {
				is.close();
			} catch (FileNotFoundException fne) {
				throw new Exception(fne.getMessage());
			} catch (IOException ioe) {
				throw new ParserException(ioe.getMessage());
			}
		}
		return (T) obj;
	}*/    
    
    
    public static void main(String[] args) throws IOException, ParseException  
    {
//        HashMap<String,String> resultmap = search("D:/tools/eclipse 4.2/eclipse-sdk-4.2.1-win32-svn/eclipse/tmp/templucene","case name","*.case",false,null);
//        for(String key:resultmap.keySet())
//        {
//        	System.out.println(resultmap.get(key));
//        }
    }  
} 