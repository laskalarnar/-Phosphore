package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	/*
	 * The method creates a Connection object. Loads the embedded driver, starts and
	 * connects to the database using the connection URL.
	 */

	public static Connection createDatabaseConnection() throws SQLException, ClassNotFoundException {
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		Class.forName(driver);
		String url = "jdbc:derby:sampleDB";
		Connection c = DriverManager.getConnection(url);
		return c;
	}
}
