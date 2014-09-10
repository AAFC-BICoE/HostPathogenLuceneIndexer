package ca.gc.agr.mbb.hostpathogen.hostPathogenLuceneLoader;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.csv.CSVRecord;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.Field;




abstract public class Builder implements DocumentBuilder{
    protected List<String> fields;

    protected String[] objectFields;


    @Override
    final public Document makeDocument(final CSVRecord record){
	init();
	initFields();
	Set<String> idFields = findIdFields(fields);

	Document doc = new Document();
	for(String fieldName: fields){
	    String value = record.get(fieldName);
	    if (value == null || value.length() == 0){
		// throw exception??
		continue;
	    }
	    if( idFields.contains(fieldName)){
		doc.add(new LongField(fieldName, Long.parseLong(value), Field.Store.YES));
	    }else{
		doc.add(new StringField(fieldName, value, Field.Store.YES));
	    }
	}
	return doc;
    }

    private void initFields(){
	fields = new ArrayList<String>(objectFields.length);
	for(String field: objectFields){
	    fields.add(field);
	}
    }

    private Set<String> findIdFields(final List<String> fields){
	Set<String>idFields = new HashSet<String>();
	for(String field:fields){
	    if (field.endsWith(ID_SUFFIX)){
		idFields.add(field);
	    }
	}
	return idFields;
    }

}
