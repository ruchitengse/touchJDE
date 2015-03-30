package cse6324.uta.edu;

import java.sql.*;
import java.util.Random;

public class DbConnection {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/touchJDE";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "Jaggi@830";
	static Connection conn = null;
	static Statement stmt = null;
	PreparedStatement preparedStmt = null;
	static int count = 10;

	public void insert(String cls, String insertCls) {
		try {
			//stmt = conn.createStatement();
			Random ran = new Random();
			int min=10, max=500;
			int rand = min + ran.nextInt(max - min + 1);
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sqlcheck = "SELECT * FROM classstore where className='" + cls +"'";
			System.out.println(sqlcheck);
			ResultSet rs = stmt.executeQuery(sqlcheck);
			if (!rs.next()) {
				String sql = "INSERT into classstore values (?,?,?)";
				preparedStmt = conn.prepareStatement(sql);
				String clsId = "cl" + rand;
				preparedStmt.setString(1, clsId);
				preparedStmt.setString(2, cls);
				preparedStmt.setString(3, insertCls);
				preparedStmt.execute();
			} else {
				stmt = conn.createStatement();
				String sqlupdate = "UPDATE classstore SET classData='" + insertCls +"' WHERE className='" + cls +"'";
				System.out.println(sqlupdate);
				stmt.executeUpdate(sqlupdate);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void query() {
		try {
			// STEP 2: Register JDBC driver

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connecting to database...");
			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT className FROM classstore";
			ResultSet rs = stmt.executeQuery(sql);
			// STEP 5: Extract data from result set
			while (rs.next()) {
				String className = rs.getString("className");
				System.out.println("className: " + className);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try

	}

	public static void main(String[] args) {
	}
}
