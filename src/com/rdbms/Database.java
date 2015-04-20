package com.rdbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.properties.ReadProperties;

public class Database {
	
	private static Connection connection = null;
    private static String connectionString = null;
	
    public static Connection getConnection(){
        
        if(connectionString == null){
            setConnectionString();
        }
        try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(connectionString);
                connection.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        return connection;
    }

    private static void setConnectionString() {
        connectionString = ReadProperties.getInstance().getValue("DATABASE_SERVER") + "?user=" + ReadProperties.getInstance().getValue("DATABASE_USERNAME") + "&password=" + ReadProperties.getInstance().getValue("DATABASE_PASSWORD");
    }
	
    public static void closeConnection(){
        if(connection != null){
                try {
                        connection.close();
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
    }
}
