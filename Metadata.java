import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Metadata{
	public long metadataId;
	public String fileName;
	public String type;
	public double length;
	public String quality;
	public Date date;
	public String license;
	public long classCode;
	public long annotatorId;
	public boolean isSaved;
	public boolean isTrain;
	public List<TimeLine> timeLines;
	
	public void saveDB(Connection conn, boolean isTrain) {
		
		
		String query = "INSERT INTO metadata (file_name, type, length, quality, date, license, class_code, annotator_id, is_saved, is_train) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, this.fileName);
			pstmt.setString(2, this.type);
			pstmt.setDouble(3, this.length);
			pstmt.setString(4, this.quality);
			pstmt.setDate(5, this.date);
			pstmt.setString(6, this.license);
			pstmt.setInt(7, (int)this.classCode);
			pstmt.setInt(8, (int)this.annotatorId);
			pstmt.setBoolean(9, this.isSaved);
			pstmt.setBoolean(10, isTrain);
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			this.metadataId = (rs.next()) ? Integer.parseInt(rs.getString(1)) : -1;
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		for(TimeLine timeLine : this.timeLines){
			String subQuery= "INSERT INTO timelines (id, start, end, variation, place, action, emotion, relationship, metadata_id) VALUES (?, ?, ?,?,?,?, ?,?,? )";
			try {
				PreparedStatement pstmt = conn.prepareStatement(subQuery, Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, (int) timeLine.id);
				pstmt.setInt(2, (int) timeLine.start);
				pstmt.setInt(3, (int) timeLine.end);
				pstmt.setString(4, timeLine.variation);
				pstmt.setInt(5, (int) timeLine.place);
				pstmt.setInt(6, (int) timeLine.action);
				pstmt.setInt(7, (int) timeLine.emotion);
				pstmt.setInt(8, (int) timeLine.relationShip);
				pstmt.setLong(9, this.metadataId);
				
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
			
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}