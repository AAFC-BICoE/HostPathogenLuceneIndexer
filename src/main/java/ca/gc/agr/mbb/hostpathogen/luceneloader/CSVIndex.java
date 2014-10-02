package ca.gc.agr.mbb.hostpathogen.hostpathogenluceneloader;

import org.apache.commons.csv.CSVRecord;
import java.util.Map;
import java.util.HashMap;

public class CSVIndex{

    Map<String, CSVRecord>index = new HashMap<String, CSVRecord>();

    String indexKeyField = null;

    public void setIndexKeyField(final String indexKeyField){
	this.indexKeyField = indexKeyField;
    }

    public void add(final CSVRecord record){
	index.put(record.get(indexKeyField), record);
    }

    public CSVRecord get(final String key){
	return index.get(key);
    }
    

}
