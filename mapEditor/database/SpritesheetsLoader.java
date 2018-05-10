package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import physique.sprite.Spritesheet;

public abstract class SpritesheetsLoader {

	public static ArrayList<Spritesheet> loadSpritesheets() {
		ArrayList<Spritesheet> spritesheets = new ArrayList<>();
		
		Connection conn;
		Statement stmt;
		ResultSet rs = null;
		try {
			conn = DatabaseConnection.createDatabaseConnection();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT NAME FROM Spritesheets");
			while (rs.next()) {
				spritesheets.add(new Spritesheet(rs.getString(1)));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		DatabaseConnection.closeConnection();
		return spritesheets;
	}
}
