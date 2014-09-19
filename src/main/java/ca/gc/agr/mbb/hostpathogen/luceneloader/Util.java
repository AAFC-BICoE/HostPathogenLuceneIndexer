package ca.gc.agr.mbb.hostpathogen.hostpathogenluceneloader;


public final class Util{
    static final String SUFFIX = "\\.csv";

    public static final String makeIndexName(String base){
	// Assumes .csv suffix
	return "luceneIndex." + base.replaceAll(SUFFIX, "");
    }

}
