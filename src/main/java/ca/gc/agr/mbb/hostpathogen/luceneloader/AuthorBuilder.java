package ca.gc.agr.mbb.hostpathogen.hostPathogenLuceneLoader;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class AuthorBuilder extends Builder{

    public static final String[] AUTHOR_FIELDS={
	LOOKUP_AUTHOR,
	PK_AUTHOR_LOOKUP_ID,
    };

    @Override
    public void init(){
	objectFields = AUTHOR_FIELDS;
    }


}
