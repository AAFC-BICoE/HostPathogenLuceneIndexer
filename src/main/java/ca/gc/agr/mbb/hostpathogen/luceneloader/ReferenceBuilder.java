package ca.gc.agr.mbb.hostpathogen.hostPathogenLuceneLoader;

import java.util.ArrayList;

public class ReferenceBuilder extends Builder{

    public static final String[] REFERENCE_FIELDS={
	CHAPTER_ARTICLE_TITLE,
	DATA_SOURCE,
	PAGES,
	REFERENCE_AUTHORS,
	REFERENCE_YEAR,
	VOLUME,
    };

    @Override
    public void init(){
	objectFields = REFERENCE_FIELDS;
    }

}
