package database.spritesheets;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.connection.DatabaseConnection;
import physique.sprite.Spritesheet;

public abstract class SpritesheetsLoader {

	public static ArrayList<SpritesheetDB> loadSpritesheets() {
		ArrayList<SpritesheetDB> spritesheets = new ArrayList<>();
		
		Connection conn;
		Statement stmt;
		ResultSet rs = null;
		try {
			conn = DatabaseConnection.createDatabaseConnection();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Spritesheets");
			while (rs.next()) {
				Spritesheet ss = new Spritesheet(rs.getString("NAME"));
				spritesheets.add(new SpritesheetDB(rs.getInt("ID_SPRITESHEET"), ss));
			}
		} catch (ClassNotFoundException | SQLException | IllegalAccessException e) {
			e.printStackTrace();
		}

		DatabaseConnection.closeConnection();
		return spritesheets;
	}
}
