import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB{

	private static String dbUrl = "jdbc:mysql://localhost:3306/ai_hub?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
	private static String dbUser = "me"; //연구실은 me 사용
	private static String dbPasswd = "hong1234"; //hong1234 // 2150
	
	private static Connection dbConnection = null;

	
	public static Connection getDBConnection() {
		if(dbConnection == null) ConnectDB();
		return dbConnection;
	}
	
	public static void ConnectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			dbConnection = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
			System.out.println("DB연결 성공");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void closeDB() {
		if (dbConnection != null) {
			try {
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
	}

}
