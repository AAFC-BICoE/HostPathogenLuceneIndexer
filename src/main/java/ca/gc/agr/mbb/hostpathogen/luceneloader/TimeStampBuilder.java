package ca.gc.agr.mbb.hostpathogen.hostpathogenluceneloader;

import java.util.ArrayList;

public class TimeStampBuilder extends Builder{


    public static final String[] TIMESTAMP_FIELDS={
	TIMESTAMP_FIELD,
	TIMESTAMP_FIELD_IN_MILLIS
    };

    public TimeStampBuilder(final String csvFilename){
	super(csvFilename);
    }

    @Override
    public void init(){
	objectFields = TIMESTAMP_FIELDS;
	primaryKeyField = TIMESTAMP_FIELD;
	recordType = TIMESTAMP_TYPE;
    }

}
