package ca.gc.agr.mbb.hostpathogen.hostPathogenLuceneLoader;

import org.apache.commons.csv.CSVRecord;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.Field;


public class PathogenBuilder implements DocumentBuilder{
    public static final String ID_SUFFIX= "_ID";

    public static final String FK_ANAMORPH_ID = "fk_anamorph_id";
    public static final String FK_HIGHER_TAXA_ID = "fk_higher_taxa_id";
    public static final String FK_PATHOGEN_ID_ACCEPTED = "fk_pathogen_id_accepted";
    public static final String FUNGAL_STATE = "fungal_state";
    public static final String PATHOGEN_AUTHOR = "pathogen_author";
    public static final String PATHOGEN_GENUS = "pathogen_genus";
    public static final String PATHOGEN_SPECIES = "pathogen_species";
    public static final String PATHOGEN_SUBSPECIFIC_TAXA = "pathogen_subspecific_taxa";
    public static final String PK_PATHOGEN_ID = "pk_pathogen_id";
    public static final String VIRUS_MPLO_NAMES = "virus_MPLO_names";

    public static final String[] PATHOGEN_FIELDS={
	FK_ANAMORPH_ID,
	FK_HIGHER_TAXA_ID,
	FK_PATHOGEN_ID_ACCEPTED,
	FUNGAL_STATE,
	PATHOGEN_AUTHOR,
	PATHOGEN_GENUS,
	PATHOGEN_SPECIES,
	PATHOGEN_SUBSPECIFIC_TAXA,
	PK_PATHOGEN_ID,
	VIRUS_MPLO_NAMES
    };



    public Document makeDocument(CSVRecord record){
	Document doc = new Document();
	for(String fieldName: PATHOGEN_FIELDS){
	    String value = record.get(fieldName);
	    if (value == null || value.length() == 0){
		// throw exception??
		continue;
	    }
	    switch (fieldName){
	    case FK_ANAMORPH_ID:
	    case FK_HIGHER_TAXA_ID:
	    case FK_PATHOGEN_ID_ACCEPTED:
	    case PK_PATHOGEN_ID:
		doc.add(new LongField(fieldName, Long.parseLong(value), Field.Store.YES));
		break;
		
	    case PATHOGEN_GENUS:
	    case PATHOGEN_SPECIES:
	    case PATHOGEN_SUBSPECIFIC_TAXA:
	    case PATHOGEN_AUTHOR:
	    case FUNGAL_STATE:
	    case VIRUS_MPLO_NAMES:
		doc.add(new StringField(fieldName, value, Field.Store.YES));
		break;
		
	    }
	}
	return doc;
    }

}
