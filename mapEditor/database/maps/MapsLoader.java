package database.maps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.connection.DatabaseConnection;
import physique.world.Map;

public abstract class MapsLoader {
	
	public static void addMap(Map map) {
		Connection conn;
		PreparedStatement pstmt;
		
		try {
			conn = DatabaseConnection.createDatabaseConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(
					"insert into MAPS (NAME, WIDTH, HEIGHT) values (?, ?, ?)");
			pstmt.setString(1, map.getMapName());
			pstmt.setInt(2, map.getMapXX());
			pstmt.setInt(3, map.getMapYY());
			pstmt.executeUpdate();
			
			conn.commit();
			conn.close();
			
		} catch (IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DatabaseConnection.closeConnection();
	}

}
