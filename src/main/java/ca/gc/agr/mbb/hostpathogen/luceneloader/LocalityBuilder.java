package ca.gc.agr.mbb.hostpathogen.hostpathogenluceneloader;

import java.util.ArrayList;

public class LocalityBuilder extends Builder{

    public static final String[] LOCALITY_FIELDS={
	PK_LOCATION_ID,
	GEOGRAPHICAL_ABBREVIATION,
	INTERPRETATION,
	COUNTRY,
    };

    public LocalityBuilder(final String csvFilename){
	super(csvFilename);
    }

    @Override
    public void init(){
	objectFields = LOCALITY_FIELDS;
    }

}
