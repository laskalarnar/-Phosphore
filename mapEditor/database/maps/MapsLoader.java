package database.maps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.connection.DatabaseConnection;
import javafx.util.Pair;
import physique.sprite.Spritesheet;
import physique.tile.SimpleTile;
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
		for(MapDB map : maps) {
			loadTiles(map);
		}
		return maps;
	}
	
	private static void loadTiles(MapDB map) {
		Connection conn;
		Statement stmt;
		ResultSet rs = null;
		try {
			conn = DatabaseConnection.createDatabaseConnection();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT * FROM SIMPLE_TILES "
					+ "INNER JOIN SPRITESHEETS "
					+ "ON SIMPLE_TILES.ID_SPRITESHEET"
					+ "=SPRITESHEETS.ID_SPRITESHEET "
					+ "INNER JOIN LINK_MAPS_SIMPLE_TILES "
					+ "ON SIMPLE_TILES.ID_SIMPLE_TILE"
					+ "=LINK_MAPS_SIMPLE_TILES.ID_SIMPLE_TILE "
					+ "WHERE ID_MAP = " + map.getID());
			SimpleTile[][] tileArray =
					new SimpleTile[map.getMap().getMapXX()][map.getMap().getMapYY()];
			while (rs.next()) {
				Spritesheet ss = new Spritesheet(rs.getString("SPRITESHEET_NAME"));
				SimpleTile tile = new SimpleTile(
						ss,
						new Pair<Integer, Integer>(rs.getInt("X_SPRITE"), rs.getInt("Y_SPRITE")));
				tileArray[rs.getInt("X")][rs.getInt("Y")] = tile;
			}
			map.getMap().setTileArray(tileArray);
		} catch (ClassNotFoundException | SQLException | IllegalAccessException e) {
			e.printStackTrace();
		}

		DatabaseConnection.closeConnection();

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
