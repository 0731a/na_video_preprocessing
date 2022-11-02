import java.io.File;
import java.io.IOException;
import java.sql.Connection;


import org.json.simple.parser.ParseException;

public class Main {

	public static void main(String[] args) throws IOException, ParseException{
		System.out.println("hi");
		
		Connection db = DB.getDBConnection();

		File tlDir = new File("C:\\Users\\user\\Desktop\\TL_유튜브");
		File vlDir = new File("C:\\Users\\user\\Desktop\\VL_유튜브");
		File tlFiles[] = tlDir.listFiles();
		File vlFiles[] = vlDir.listFiles();

		for (int i = 0; i < tlFiles.length; i++) {
		    //System.out.println("file: " + files[i]);
		    Metadata metadata = Json.getData(tlFiles[i].toString());
		    //metadata.saveDB(db, true);
		}
		
		for (int i = 0; i < vlFiles.length; i++) {

		    Metadata metadata = Json.getData(vlFiles[i].toString());
		    //metadata.saveDB(db, false);
		}
		
		
		System.out.println(" 검증 :: 총 " + vlFiles.length + "개의 파일");
		System.out.println(" 학습 :: 총 " + tlFiles.length + "개의 파일");
	}
}
