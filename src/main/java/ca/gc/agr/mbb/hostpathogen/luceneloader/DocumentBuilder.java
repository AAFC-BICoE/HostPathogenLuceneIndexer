package ca.gc.agr.mbb.hostpathogen.hostPathogenLuceneLoader;

import org.apache.commons.csv.CSVRecord;

import org.apache.lucene.document.Document;

public interface DocumentBuilder{
    Document makeDocument(CSVRecord record);

}
