
package ca.gc.agr.mbb.hostpathogen.hostpathogenluceneloader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;

import java.util.Map;
import java.io.File;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Loader{
    private final static Logger LOG = Logger.getLogger(Loader.class.getName()); 
    String luceneIndexDir = null;
    public Loader(final String luceneIndexDir){
	this.luceneIndexDir = luceneIndexDir;
    }

    public void index(CSVParser parser, String filename, Builder pb){
	Map<String,Integer> headers = parser.getHeaderMap();
	Directory dir = null;
	Analyzer analyzer = null;
	IndexWriterConfig iwc = null;
	IndexWriter writer = null;

	pb.init();
	pb.initFields();


	try{
	    dir = FSDirectory.open(new File(luceneIndexDir + "/" + Util.makeIndexName(filename)));
	    analyzer = new StandardAnalyzer(Version.LUCENE_4_10_0);
	    
	    iwc = new IndexWriterConfig(Version.LUCENE_4_10_0, analyzer);
	    iwc.setOpenMode(OpenMode.CREATE);
	    
	    writer = new IndexWriter(dir, iwc);
	}catch(Exception e){
	    e.printStackTrace();
	    return;
	}
	try{
	    int count = 0;
	    LOG.info("Filename csv=" + filename);
	    if(filename == "pathogens.csv" || filename == "hosts.csv"){
		LOG.info("SPECIAL");
	    }
	    
	    for (CSVRecord record : parser) {
		count++;
		//System.out.println("---");
		Document doc = null;

		if(pb != null){
		    doc = pb.makeDocument(record);
		}
		try{
		    writer.addDocument(doc);
		}catch(Exception e){
		    e.printStackTrace();
		    return;
		}
	    }
	}
	finally{
	    try{
		System.err.println("Closing index for: " + filename);
		writer.close();
	    }catch(Exception e){
		e.printStackTrace();
	    }
	}
    }
    
}
