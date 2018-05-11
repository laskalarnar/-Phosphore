package database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	/*
	 * The method creates a Connection object. Loads the embedded driver, starts and
	 * connects to the database using the connection URL.
	 */

	public static Connection createDatabaseConnection() throws SQLException, IllegalAccessException, ClassNotFoundException {
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:derby:gamedb";
		Connection c = DriverManager.getConnection(url);
		return c;
	}
	
	public static void closeConnection(){
		try {
			DriverManager.getConnection("jdbc:derby:;shutdown=true");
		} catch (SQLException ex) {
			if (((ex.getErrorCode() == 50000) && ("XJ015".equals(ex.getSQLState())))) {
				System.out.println("Derby shut down normally");
			} else {
				System.err.println("Derby did not shut down normally");
				System.err.println(ex.getMessage());
			}
		}
	}
}
