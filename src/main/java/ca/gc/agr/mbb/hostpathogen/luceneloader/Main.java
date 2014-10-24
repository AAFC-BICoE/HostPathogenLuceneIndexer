package ca.gc.agr.mbb.hostpathogen.hostpathogenluceneloader;

import ca.gc.agr.mbb.hostpathogen.hostpathogenlucenesearcher.UtilLucene;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.lang.Iterable;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;

public class Main{

    static final String AUTHOR_FILE = "author_lookup.csv";
    static final String HIGHER_TAXA_FILE = "higher_taxa.csv";  
    static final String HOSTS_FILE = "hosts.csv";
    static final String HOST_PATHOGENS_FILE = "host_pathogens.csv";
    static final String LOCALITIES_FILE = "localities.csv";
    static final String HP_LOCALITIES_JOIN_FILE = "hp_locality_links.csv";
    static final String PATHOGENS_FILE = "pathogens.csv";
    static final String REFERENCES_FILE = "references.csv";
    static final String REF_SOURCES_FILE = "ref_sources.csv";

    static final String[] csvFiles = {
	AUTHOR_FILE,         // 4728
	HIGHER_TAXA_FILE,    // 300
	//HP_LOCALITIES_JOIN_FILE, // 140113
	REFERENCES_FILE,     // 8744
	REF_SOURCES_FILE,    // 486
	HOSTS_FILE,          // 6657
	PATHOGENS_FILE,      // 13556
	HOST_PATHOGENS_FILE, // 121349
    };


    public static final void main(final String[] args) {
	if(args.length != 2){
	    usage();
	    System.exit(42);
	}
	
	String csvDir = args[0];
	String indexWriterDir = args[1];
	Map<String, Builder>builderMap = new HashMap<String, Builder>();
	init(builderMap, csvDir);
	Loader loader = new Loader();

	Analyzer analyzer = null;
	IndexWriter writer = null;
	final String indexDir = indexWriterDir + "/" + "HPLuceneIndex";
	try{
	    analyzer = UtilLucene.makeAnalyzer();
	    writer = UtilLucene.makeIndexWriter(indexDir, analyzer);
	}catch(Exception e){
	    e.printStackTrace();
	    return;
	}

	try{
	    for(String csvFile: csvFiles){
		System.err.println("IndexWriter: " + writer);
		if (!builderMap.containsKey(csvFile)){
		    System.err.println("File not in builder map: " + csvFile);
		    System.exit(42);
		}
		Builder builder = builderMap.get(csvFile);

		CSVParser parser = null;
		try{
		    parser = fileReader(csvDir, csvFile);
		}catch(java.io.FileNotFoundException e){
		    e.printStackTrace();
		    System.err.println("Unable to find file: " + csvDir + "/" + csvFile);
		    System.exit(42);
		}catch(java.io.IOException e){
		    e.printStackTrace();
		    System.exit(42);
		}
		loader.index(writer, parser, csvFile, builder);
		System.out.println(csvFile);
		//idmp.dump(Util.makeIndexName(file));
		try{
		    writer.commit();
		}catch(java.io.IOException e){
		    e.printStackTrace();
		}
		builder.close();
	    }
	}
	finally{
	    try{
		System.err.println("Closing index ");
		writer.close();
	    }catch(Exception e){
		e.printStackTrace();
	    }
	}
	IndexDumper idmp = new IndexDumper();
	idmp.count(indexDir);
    }

    public static void usage(){
	System.err.println("Usage: java " + Main.class.getCanonicalName() + " cvsFileDir luceneIndexDir");
    }

    public static CSVParser fileReader(String dir, String filename) throws java.io.FileNotFoundException, java.io.IOException{
	Reader in = null;
	in = new FileReader(dir + "/" + filename);
	CSVFormat format = CSVFormat.EXCEL.withHeader();
	CSVParser parser = new CSVParser(in, format);
	return parser;
    }


    public static void init(final Map<String, Builder> bm, final String csvDir){
 	if (bm == null){
		throw new NullPointerException("mapBuilder is null");
	    }
	bm.put(AUTHOR_FILE, new AuthorBuilder(AUTHOR_FILE));
	bm.put(HIGHER_TAXA_FILE, new HigherTaxaBuilder(HIGHER_TAXA_FILE));
	bm.put(HOSTS_FILE, new HostBuilder(HOSTS_FILE));
	bm.put(HOST_PATHOGENS_FILE, new HostPathogenBuilder(HOST_PATHOGENS_FILE, HOSTS_FILE, PATHOGENS_FILE, csvDir));
	bm.put(LOCALITIES_FILE, new LocalityBuilder(LOCALITIES_FILE));
	bm.put(PATHOGENS_FILE, new PathogenBuilder(PATHOGENS_FILE));
	bm.put(REFERENCES_FILE,new ReferenceBuilder(REFERENCES_FILE));
	bm.put(REF_SOURCES_FILE, new ReferenceSourceBuilder(REF_SOURCES_FILE));
    }
    
}
