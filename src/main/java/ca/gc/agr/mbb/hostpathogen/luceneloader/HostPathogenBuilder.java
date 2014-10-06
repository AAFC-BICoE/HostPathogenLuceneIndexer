package ca.gc.agr.mbb.hostpathogen.hostpathogenluceneloader;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVRecord;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

public class HostPathogenBuilder extends Builder{
    private final Logger LOG = Logger.getLogger(HostPathogenBuilder.class.getName());

    private CSVMap pathogenMap, hostMap;
    private String csvDir, hostCsv, pathogenCsv;

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
	primaryKeyField = PK_HOST_PATHOGEN_ID;
	recordType = HOST_PATHOGEN_TYPE;

	pathogenMap = CSVMapLoader.load(csvDir, pathogenCsv, PK_PATHOGEN_ID);
	hostMap = CSVMapLoader.load(csvDir, hostCsv, PK_HOST_ID);
	LOG.info("################ init done");
	
    }

    public HostPathogenBuilder(final String csvFilename, final String hostCsv, final String pathogenCsv, final String csvDir){
	super(csvFilename);
	this.hostCsv = hostCsv;
	this.pathogenCsv = pathogenCsv;
	this.csvDir = csvDir;
    }

    public void afterMakeDocument(final Document doc){

	CSVRecord pathogen = pathogenMap.get(doc.getValues(FK_PATHOGEN_ID + STORED_SUFFIX)[0]);
	doc.add(new TextField(PATHOGEN_GENUS, pathogen.get(PATHOGEN_GENUS), Field.Store.NO));
	doc.add(new TextField(PATHOGEN_SPECIES, pathogen.get(PATHOGEN_SPECIES), Field.Store.NO));

	CSVRecord host = hostMap.get(doc.getValues(FK_HOST_ID + STORED_SUFFIX)[0]);
	doc.add(new TextField(HOST_GENUS, host.get(HOST_GENUS), Field.Store.NO));
	doc.add(new TextField(HOST_SPECIES, host.get(HOST_SPECIES), Field.Store.NO));
	doc.add(new TextField(CULTIVAR, host.get(CULTIVAR), Field.Store.NO));

	//LOG.info(doc.toString());

    }

}
