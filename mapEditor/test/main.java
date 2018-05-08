package test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.jdbc.EmbeddedDriver;

import database.test;

public class main {

	public static void main(String[] args) {
		//testCreateTable();
		//testInsertTable();
		testSelectTable();
		//testDropTable();
	}

	public static void create() {
		try {
			test.createDatabase();
			test.createDatabaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void testCreateTable() {
		Connection conn = null;
		Statement stmt;
		String createSQL = "CREATE TABLE SPRITESHEETS "
				+ "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) "
				+ "CONSTRAINT PK PRIMARY KEY, " + "NAME VARCHAR(30))";

		try {
			Driver derbyEmbeddedDriver = new EmbeddedDriver();
			DriverManager.registerDriver(derbyEmbeddedDriver);
			try {
				conn = test.createDatabaseConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.execute(createSQL);

			conn.commit();

		} catch (SQLException ex) {
			System.out.println("in connection " + ex);
		}

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

	public static void testInsertTable() {
		Connection conn = null;
		PreparedStatement pstmt;

		try {
			Driver derbyEmbeddedDriver = new EmbeddedDriver();
			DriverManager.registerDriver(derbyEmbeddedDriver);
			try {
				conn = test.createDatabaseConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement("insert into SPRITESHEETS (NAME) values(?)");
			pstmt.setString(1, "testSpriteSheet");
			pstmt.executeUpdate();

			conn.commit();

		} catch (SQLException ex) {
			System.out.println("in connection " + ex);
		}

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

	public static void testSelectTable() {
		Connection conn = null;
		PreparedStatement pstmt;
		Statement stmt;
		ResultSet rs = null;
		String createSQL = "CREATE TABLE SPRITESHEETS "
				+ "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) "
				+ "CONSTRAINT PK PRIMARY KEY, " + "NAME VARCHAR(30))";

		try {
			Driver derbyEmbeddedDriver = new EmbeddedDriver();
			DriverManager.registerDriver(derbyEmbeddedDriver);
			try {
				conn = test.createDatabaseConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			rs = stmt.executeQuery("select * from SPRITESHEETS");
			while (rs.next()) {
				System.out.printf("%d %s\n", rs.getInt(1), rs.getString(2));
			}

			conn.commit();

		} catch (SQLException ex) {
			System.out.println("in connection " + ex);
		}

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

	public static void testDropTable() {

		Connection conn = null;
		Statement stmt;

		try {
			Driver derbyEmbeddedDriver = new EmbeddedDriver();
			DriverManager.registerDriver(derbyEmbeddedDriver);
			try {
				conn = test.createDatabaseConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			stmt.execute("drop table SPRITESHEETS");

			conn.commit();

		} catch (SQLException ex) {
			System.out.println("in connection " + ex);
		}

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

	public static void test() {
		Connection conn = null;
		PreparedStatement pstmt;
		Statement stmt;
		ResultSet rs = null;
		String createSQL = "CREATE TABLE SPRITESHEETS "
				+ "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) "
				+ "CONSTRAINT PK PRIMARY KEY, " + "NAME VARCHAR(30))";

		try {
			Driver derbyEmbeddedDriver = new EmbeddedDriver();
			DriverManager.registerDriver(derbyEmbeddedDriver);
			try {
				conn = test.createDatabaseConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.execute(createSQL);

			pstmt = conn.prepareStatement("insert into SPRITESHEETS (NAME) values(?)");
			pstmt.setString(1, "testSpriteSheet");
			pstmt.executeUpdate();

			rs = stmt.executeQuery("select * from SPRITESHEETS");
			while (rs.next()) {
				System.out.printf("%d %s\n", rs.getInt(1), rs.getString(2));
			}

			stmt.execute("drop table SPRITESHEETS");

			conn.commit();

		} catch (SQLException ex) {
			System.out.println("in connection " + ex);
		}

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
