package automation.jsondata;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDatatoMap() throws IOException
	{
		//read JSON to String
	String JsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\automation\\jsondata\\PurchaseOrder.json"),
			StandardCharsets.UTF_8);
	
	//String to HashMap - Jackson DataBind
	
	ObjectMapper Mapper = new ObjectMapper();
	List<HashMap<String,String>> data = Mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>(){
		
	});
	
	return data;
	}

}
