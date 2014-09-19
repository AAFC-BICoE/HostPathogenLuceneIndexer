package ca.gc.agr.mbb.hostpathogen.hostpathogenluceneloader;

import org.apache.commons.csv.CSVRecord;

import org.apache.lucene.document.Document;

public class Anamorph extends Builder{
    //public static final String "pk_anamorph_id"
    //public static final String "fk_pathogen_id_ana"

    /*
    public Document makeDocument(CSVRecord record){
	Document doc = new Document();
	//doc.add(new LongField("pk_anamorph_id", Long.parseLong(value), Field.Store.YES));
	//doc.add(new LongField("fk_pathogen_id_ana", Long.parseLong(value), Field.Store.YES));
	return doc;
    }
    */

    public Anamorph(final String csvFilename){
	super(csvFilename);
    }

    public void init(){

    }

}
