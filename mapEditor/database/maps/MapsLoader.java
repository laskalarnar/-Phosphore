package database.maps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.connection.DatabaseConnection;
import physique.world.Map;

public abstract class MapsLoader {
	
	public static ArrayList<MapDB> loadMaps() {
		ArrayList<MapDB> maps = new ArrayList<>();
		
		Connection conn;
		Statement stmt;
		ResultSet rs = null;
		try {
			conn = DatabaseConnection.createDatabaseConnection();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM MAPS");
			while (rs.next()) {
				Map map = new Map(rs.getInt("WIDTH"), rs.getInt("HEIGHT"), rs.getString("NAME"));
				maps.add(new MapDB(rs.getInt("ID_MAP"), map));
			}
		} catch (ClassNotFoundException | SQLException | IllegalAccessException e) {
			e.printStackTrace();
		}

		DatabaseConnection.closeConnection();
		return maps;
	}
	
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
