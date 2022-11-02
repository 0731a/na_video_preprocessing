import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Json {

	public static Metadata getData(String filePath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        // JSON 파일 읽기
        Reader reader = new FileReader(filePath);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        JSONObject metadataObject = (JSONObject) jsonObject.get("metadata");

        Metadata metadata = new Metadata();

        metadata.fileName = (String) metadataObject.get("file_name");
        
        metadata.type = (String) metadataObject.get("type");
        metadata.length = (Double) metadataObject.get("length");
        metadata.quality = (String) metadataObject.get("quality");
        metadata.date = Date.valueOf((String) metadataObject.get("date"));
        metadata.license = (String) metadataObject.get("license");
        metadata.classCode = (Long) metadataObject.get("class_code");
        metadata.annotatorId = (Long) metadataObject.get("annotator_id");
        
        JSONArray timeLinesObject = (JSONArray) jsonObject.get("timelines");
        metadata.timeLines = getTimeLines(timeLinesObject);
        
		
		return metadata;
	}
	
	public static List<TimeLine> getTimeLines(JSONArray timeLines){
		
		ArrayList<TimeLine> timeList = new ArrayList<>();
		
        for (Object obj : timeLines) {
        	JSONObject jsonObject = (JSONObject) obj;
        	TimeLine timeLine = new TimeLine();
        	
        	timeLine.id = (Long) jsonObject.get("id");
        	timeLine.start = (Long) jsonObject.get("start");
        	timeLine.end = (Long) jsonObject.get("end");
        	timeLine.variation = (String) jsonObject.get("variation");
        	
        	JSONObject attributes = (JSONObject) jsonObject.get("attributes");
        	timeLine.place = (Long) attributes.get("place");
        	timeLine.action = (Long) attributes.get("action");
        	timeLine.emotion = (Long) attributes.get("emotion");
        	timeLine.relationShip = (Long) attributes.get("relationship");
        			
        	timeList.add(timeLine);
        }
        
		return timeList;
	}
}
