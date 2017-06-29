package com.test.lucene;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

public class TestLucene {

	public static void main(String[] args) {
		

	}
	
//	@Test
	public  void testtry() throws IOException, ParseException {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);

	    // Store the index in memory:
	    Directory directory = new RAMDirectory();
	    // To store an index on disk, use this instead:
	    //Directory directory = FSDirectory.open("/tmp/testindex");
	    IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_CURRENT, analyzer);
	    IndexWriter iwriter = new IndexWriter(directory, config);
	    Document doc = new Document();
	    String text = "This is the text to be indexed.";
	    doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
	    iwriter.addDocument(doc);
	    iwriter.close();
	    
	    // Now search the index:
	    DirectoryReader ireader = DirectoryReader.open(directory);
	    IndexSearcher isearcher = new IndexSearcher(ireader);
	    // Parse a simple query that searches for "text":
	    QueryParser parser = new QueryParser(Version.LUCENE_CURRENT, "fieldname", analyzer);
	    Query query = parser.parse("text");
	    ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
	    assertEquals(1, hits.length);
	    // Iterate through the results:
	    for (int i = 0; i < hits.length; i++) {
	      Document hitDoc = isearcher.doc(hits[i].doc);
	      assertEquals("This is the text to be indexed.", hitDoc.get("fieldname"));
	    }
	    ireader.close();
	    directory.close();
	}
	
	@Test
	public void testZTP() throws Exception {
		List<String> indexdir = new ArrayList<String>();
		indexdir.add("E:/runtime-ZTP2.0/Test");
		IvFileIndex.createIndex(indexdir,"E:/ztptemplucene");
		HashMap<String, String> result = IvFileSearch.search("E:/ztptemplucene","Case Name".toLowerCase(),"momo".trim(),false);
		System.out.println(result);
	}
}
