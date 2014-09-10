package ca.gc.agr.mbb.hostpathogen.hostPathogenLuceneLoader;

import java.util.ArrayList;

public class ReferenceSourceBuilder extends Builder{

    public static final String[] REF_SOURCE_FIELDS={
	BOOK_AUTHOR,
	BOOK_EDITOR,
	BOOK_PAGES,
	BOOK_PUBLISHER,
	BOOK_PUBLISHER_PLACE,
	BOOK_TITLE,
	BOOK_YEAR,
	JOURNAL_ABBREVIATION,
	JOURNAL_TITLE,
	PK_REF_SOURCE_ID,
    };

    @Override
    public void init(){
	objectFields = REF_SOURCE_FIELDS;
    }

}
