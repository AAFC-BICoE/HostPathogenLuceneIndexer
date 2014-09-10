
package ca.gc.agr.mbb.hostpathogen.hostPathogenLuceneLoader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;

import java.util.Map;
import java.io.File;

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
    public void index(CSVParser parser, String filename, Builder pb){
	Map<String,Integer> headers = parser.getHeaderMap();
	Directory dir = null;
	Analyzer analyzer = null;
	IndexWriterConfig iwc = null;
	IndexWriter writer = null;

	try{

	    try{
		dir = FSDirectory.open(new File(Util.makeIndexName(filename)));
		analyzer = new StandardAnalyzer(Version.LUCENE_4_10_0);

		iwc = new IndexWriterConfig(Version.LUCENE_4_10_0, analyzer);
		iwc.setOpenMode(OpenMode.CREATE);

		writer = new IndexWriter(dir, iwc);
	    }catch(Exception e){
		e.printStackTrace();
		return;
	    }

	    int count = 0;
	    for (CSVRecord record : parser) {
		count++;
		//System.out.println("---");
		Document doc = null;
		if(filename == "pathogens.csv"){
		    doc = pb.makeDocument(record);
		}else{

		    doc = new Document();
		    for(String fieldName: headers.keySet()){
			String value = record.get(fieldName);
			if(value == null || value.length() == 0){
			    continue;
			}
			if(fieldName.endsWith("id")){
			    try{
				doc.add(new LongField(fieldName, Long.parseLong(value), Field.Store.YES));
			    }catch(Throwable e){
				System.err.println(filename);
				System.err.println(fieldName);
				System.err.println(count);
				e.printStackTrace();
				return;
			    }
			}else{
			    doc.add(new StringField(fieldName, record.get(fieldName), Field.Store.YES));
			}
			//System.out.println(fieldName + ":" + record.get(fieldName));
		    }
		}
		try{
		    //System.out.println(doc);
		    writer.addDocument(doc);
		}catch(Exception e){
		    e.printStackTrace();
		    return;
		}
	    }
	}
	finally{
	    try{
		writer.close();
	    }catch(Exception e){
		e.printStackTrace();
	    }
	}
    }
    
}
