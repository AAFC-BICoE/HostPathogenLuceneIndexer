package ca.gc.agr.mbb.hostpathogen.hostpathogenluceneloader;



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

public class Main{

    static final String AUTHOR_FILE = "author_lookup.csv";
    static final String HIGHER_TAXA_FILE = "higher_taxa.csv";  
    static final String HOSTS_FILE = "hosts.csv";
    static final String HOST_PATHOGENS_FILE = "host_pathogens.csv";
    static final String LOCALITIES_FILE = "localities.csv";
    static final String PATHOGENS_FILE = "pathogens.csv";
    static final String REFERENCES_FILE = "references.csv";
    static final String REF_SOURCES_FILE = "ref_sources.csv";

    static final String[] csvFiles = {
	AUTHOR_FILE,
	HIGHER_TAXA_FILE,
	HOSTS_FILE,
	HOST_PATHOGENS_FILE,
	PATHOGENS_FILE,
	REFERENCES_FILE,
	REF_SOURCES_FILE,
    };


    public static final void main(final String[] args) {
	if(args.length != 2){
	    usage();
	    System.exit(42);
	}

	String csvDir = args[0];
	String indexWriterDir = args[1];
	Map<String, Builder>builderMap = new HashMap<String, Builder>();
	init(builderMap);
	Loader loader = new Loader(indexWriterDir);
	IndexDumper idmp = new IndexDumper();

	for(String csvFile: csvFiles){
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
	    loader.index(parser, csvFile, builder);
	    System.out.println(csvFile);
	    System.out.println("------------------------------------------------------");
	    //idmp.dump(Util.makeIndexName(file));
	    idmp.count(indexWriterDir + "/" + Util.makeIndexName(csvFile));
	}
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


    public static void init(Map<String, Builder> bm){
 	if (bm == null){
		throw new NullPointerException("mapBuilder is null");
	    }
	bm.put(AUTHOR_FILE, new AuthorBuilder(AUTHOR_FILE));
	bm.put(HIGHER_TAXA_FILE, new HigherTaxaBuilder(HIGHER_TAXA_FILE));
	bm.put(HOSTS_FILE, new HostBuilder(HOSTS_FILE));
	bm.put(HOST_PATHOGENS_FILE, new HostPathogenBuilder(HOST_PATHOGENS_FILE));
	bm.put(LOCALITIES_FILE, new LocalityBuilder(LOCALITIES_FILE));
	bm.put(PATHOGENS_FILE, new PathogenBuilder(PATHOGENS_FILE));
	bm.put(REFERENCES_FILE,new ReferenceBuilder(REFERENCES_FILE));
	bm.put(REF_SOURCES_FILE, new ReferenceSourceBuilder(REF_SOURCES_FILE));
    }
    
}
