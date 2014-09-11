package ca.gc.agr.mbb.hostpathogen.hostpathogenluceneloader;

import java.util.ArrayList;

public class HostPathogenBuilder extends Builder{

    public static final String[] HOST_PATHOGEN_FIELDS={
	PK_HOST_PATHOGEN_ID,
	FK_REFERENCE_ID,
	FK_HOST_ID,
	FK_PATHOGEN_ID,
	RUST_STATE,
	PLANT_PART,
	SYMPTOM,
	NOTES,
    };

    @Override
    public void init(){
	objectFields = HOST_PATHOGEN_FIELDS;
    }

}
