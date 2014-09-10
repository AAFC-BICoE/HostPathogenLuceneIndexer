package ca.gc.agr.mbb.hostpathogen.hostPathogenLuceneLoader;

import org.apache.commons.csv.CSVRecord;

import org.apache.lucene.document.Document;

public interface DocumentBuilder{
    Document makeDocument(CSVRecord record);
    void init();

    public static final String ID_SUFFIX= "_ID";


    public static final String CULTIVAR="cultivar";
    public static final String FK_ANAMORPH_ID = "fk_anamorph_id";
    public static final String FK_HIGHER_TAXA_ID = "fk_higher_taxa_id";
    public static final String FK_HOST_ID = "fk_host_id";
    public static final String FK_HOST_ID_ACCEPTED = "fk_host_id_accepted";
    public static final String FK_PATHOGEN_ID = "fk_pathogen_id";
    public static final String FK_PATHOGEN_ID_ACCEPTED = "fk_pathogen_id_accepted";
    public static final String FK_REFERENCE_ID="fk_reference_id";
    public static final String FUNGAL_STATE = "fungal_state";
    public static final String HOST_AUTHOR = "host_author";
    public static final String HOST_GENUS = "host_genus";
    public static final String HOST_ID = "host_id";
    public static final String HOST_ID_ACCEPTED = "host_id_accepted";
    public static final String HOST_SPECIES = "host_species";
    public static final String HOST_SUBSPECIFIC_TAXA = "host_subspecific_taxa";
    public static final String LOOKUP_AUTHOR="lookup_author";
    public static final String NOTES="notes";
    public static final String PATHOGEN_AUTHOR = "pathogen_author";
    public static final String PATHOGEN_GENUS = "pathogen_genus";
    public static final String PATHOGEN_SPECIES = "pathogen_species";
    public static final String PATHOGEN_SUBSPECIFIC_TAXA = "pathogen_subspecific_taxa";
    public static final String PK_AUTHOR_LOOKUP_ID="pk_author_lookup_id";
    public static final String PK_HOST_ID = "pk_host_id";
    public static final String PK_HOST_PATHOGEN_ID = "pk_host_pathogen_id";
    public static final String PK_PATHOGEN_ID = "pk_pathogen_id";
    public static final String PLANT_PART="plant_part";
    public static final String RUST_STATE="rust_state";
    public static final String SYMPTOM="symptom";
    public static final String VIRUS_MPLO_NAMES = "virus_MPLO_names";
    

}
