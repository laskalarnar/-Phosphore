package database.simpleTiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import database.connection.DatabaseConnection;
import database.spritesheets.SpritesheetDB;
import database.spritesheets.SpritesheetsLoader;
import javafx.scene.image.Image;
import javafx.util.Pair;
import physique.sprite.Spritesheet;
import physique.tile.SimpleTile;

public abstract class SimpleTilesLoader {

	public static void updateSimpleTilesTable() {
		ArrayList<SpritesheetDB> spritesheets = SpritesheetsLoader.loadSpritesheets();
		ArrayList<SimpleTileDB> simpleTilesDB = loadSimpleTiles();
		ArrayList<SimpleTile> simpleTiles = new ArrayList<>();
		for (SimpleTileDB st : simpleTilesDB) {
			simpleTiles.add(st.getSimpleTile());
		}
		Connection conn;
		PreparedStatement pstmt;

		try {
			conn = DatabaseConnection.createDatabaseConnection();
			conn.setAutoCommit(false);
			for (SpritesheetDB ss : spritesheets) {
				HashMap<Pair<Integer, Integer>, Image> tileSet = ss.getSpritesheet().getTileSet();
				for (Pair<Integer, Integer> coordinates : tileSet.keySet()) {
					SimpleTile check = new SimpleTile(ss.getSpritesheet(), coordinates);
					if (!simpleTiles.contains(check)) {
						pstmt = conn.prepareStatement(
								"insert into SIMPLE_TILES (ID_SPRITESHEET, X_SPRITE, Y_SPRITE) values (?, ?, ?)");
						pstmt.setInt(1, ss.getID_Spritesheet());
						pstmt.setInt(2, coordinates.getKey());
						pstmt.setInt(3, coordinates.getValue());
						pstmt.executeUpdate();
					}
				}
			}

			conn.commit();
		} catch (IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		DatabaseConnection.closeConnection();
	}

	public static ArrayList<SimpleTileDB> loadTileSet(Spritesheet ss) {
		ArrayList<SimpleTileDB> tileSet = new ArrayList<>();

		Connection conn;
		Statement stmt;
		ResultSet rs = null;
		try {
			conn = DatabaseConnection.createDatabaseConnection();

			stmt = conn.createStatement();
			String selectQuery = "SELECT * FROM SIMPLE_TILES INNER JOIN SPRITESHEETS "
					+ "ON SIMPLE_TILES.ID_SPRITESHEET = SPRITESHEETS.ID_SPRITESHEET " + "WHERE SPRITESHEET_NAME = '"
					+ ss.getName() + "'";
			rs = stmt.executeQuery(selectQuery);
			while (rs.next()) {
				SimpleTile simpleTile = new SimpleTile(ss,
						new Pair<Integer, Integer>(rs.getInt("X_SPRITE"), rs.getInt("Y_SPRITE")));
				SimpleTileDB stDB = new SimpleTileDB(rs.getInt("ID_SIMPLE_TILE"), simpleTile);
				tileSet.add(stDB);
			}
		} catch (ClassNotFoundException | SQLException | IllegalAccessException e) {
			e.printStackTrace();
		}

		DatabaseConnection.closeConnection();
		return tileSet;
	}

	public static ArrayList<SimpleTileDB> loadSimpleTiles() {
		ArrayList<SimpleTileDB> tileSet = new ArrayList<>();

		Connection conn;
		Statement stmt;
		ResultSet rs = null;
		try {
			conn = DatabaseConnection.createDatabaseConnection();

			stmt = conn.createStatement();
			String selectQuery = "SELECT * FROM SIMPLE_TILES INNER JOIN SPRITESHEETS "
					+ "ON SIMPLE_TILES.ID_SPRITESHEET = SPRITESHEETS.ID_SPRITESHEET";
			rs = stmt.executeQuery(selectQuery);
			while (rs.next()) {
				Spritesheet ss = new Spritesheet(rs.getString("SPRITESHEET_NAME"));
				SimpleTile simpleTile = new SimpleTile(ss,
						new Pair<Integer, Integer>(rs.getInt("X_SPRITE"), rs.getInt("Y_SPRITE")));
				SimpleTileDB stDB = new SimpleTileDB(rs.getInt("ID_SIMPLE_TILE"), simpleTile);
				tileSet.add(stDB);
			}
		} catch (ClassNotFoundException | SQLException | IllegalAccessException e) {
			e.printStackTrace();
		}

		DatabaseConnection.closeConnection();
		return tileSet;
	}
}
