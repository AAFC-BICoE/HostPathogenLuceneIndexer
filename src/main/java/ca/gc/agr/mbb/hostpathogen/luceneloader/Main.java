package ca.gc.agr.mbb.hostpathogen.hostPathogenLuceneLoader;

import java.io.Reader;
import java.io.FileReader;
import java.lang.Iterable;
import java.util.Set;

import java.util.HashSet;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;

public class Main{

    static String[] files = {
	///"anamprphs.csv",  
	///"author_lookup.csv",  
	///"higher_taxa.csv",  
	///"host_pathogens.csv",  
	///"hosts.csv",  
	//"hp_locality_links.csv",  
	///"localities.csv",  
	"pathogens.csv",
	///"references.csv",
	///"ref_sources.csv"
	// "SBML_phcitq_temp.csv",
	//	"Switchboard_Items.csv",
	//	"tblLinkedFiles.csv",
	//	"temp_citations.csv"
    };

    public static final void main(final String[] args) {
	System.out.println("hello");
	Loader loader = new Loader();
	for(String file: files){
	    System.out.println("------------------------------------------------------");
	    CSVParser parser = fileReader(file);
	    loader.index(parser, file);
	    System.out.println(file);
	}

	
    }


    public static CSVParser fileReader(String filename){
	Reader in = null;
	try{
	    in = new FileReader("/home/newtong/2014/AgCan/HostPathogen/accessdb/" + filename);

	    CSVFormat format = CSVFormat.EXCEL.withHeader();
	    CSVParser parser = new CSVParser(in, format);
	    return parser;
		/*
	    Map<String,Integer> headers = parser.getHeaderMap();
		
	    for (CSVRecord record : parser) {
		System.out.println("---");
		for(String fieldName: headers.keySet()){
		    //for(String field: record){
		    //for (String field : record) {
		    //System.out.print("\"" + field + "\", ");
		    //System.out.println(field + ":" + record.get(field) + "|");
		    System.out.println(fieldName + ":" + record.get(fieldName));
		}
	    }
		*/
	}
	catch(Exception e){
	    e.printStackTrace();
	}
	// finally{
	//     if (in != null){
	// 	try{
	// 	    in.close();
	// 	}catch(Exception e){
	// 	    e.printStackTrace();
	// 	}
	//     }
	// }
	return null;
    }

}
