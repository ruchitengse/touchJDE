package cse6324.uta.edu.properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private static Connection connection = null;
	private static String connectionString = null;

	public static Connection getConnection() {

		if (connectionString == null) {
			setConnectionString();
		}
		try {
			System.out.println("Loading driver...");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded...");
			connection = DriverManager.getConnection(connectionString);
			System.out.println(connection+"Connecting...");
			connection.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(
					"Cannot find the driver in the classpath!", e);
		}
		return connection;
	}

	private static void setConnectionString() {
		connectionString = ReadProperties.getInstance().getValue(
				"DATABASE_SERVER")
				+ "?user="
				+ ReadProperties.getInstance().getValue("DATABASE_USERNAME")
				+ "&password="
				+ ReadProperties.getInstance().getValue("DATABASE_PASSWORD");
	}

	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
