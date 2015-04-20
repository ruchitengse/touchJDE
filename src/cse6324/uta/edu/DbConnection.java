package cse6324.uta.edu;

import java.sql.*;
import java.util.Random;

import com.rdbms.Database;

public class DbConnection {
	
	public void insert(String cls, String insertCls) {
		
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;
		try {
			//stmt = conn.createStatement();
			Random ran = new Random();
			int min=10, max=500;
			int rand = min + ran.nextInt(max - min + 1);
			connection = Database.getConnection();
			stmt = connection.createStatement();
			String sqlcheck = "SELECT * FROM classstore where className='" + cls +"'";
			System.out.println(sqlcheck);
			rs = stmt.executeQuery(sqlcheck);
			if (!rs.next()) {
				String sql = "INSERT into classstore (classID, className, classData) values (?,?,?)";
				preparedStmt = connection.prepareStatement(sql);
				String clsId = "cl" + rand;
				preparedStmt.setString(1, clsId);
				preparedStmt.setString(2, cls);
				preparedStmt.setString(3, insertCls);
				preparedStmt.executeUpdate();
			} else {
				String sqlupdate = "UPDATE classstore SET classData='" + insertCls +"' WHERE className='" + cls +"'";
				System.out.println(sqlupdate);
				stmt.executeUpdate(sqlupdate);
			}
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(preparedStmt != null){
				try {
					preparedStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(connection != null){
				try {
					connection.close();
					Database.closeConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void query() {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = Database.getConnection();
			stmt = connection.createStatement();
			String sql;
			sql = "SELECT className FROM classstore";
			rs = stmt.executeQuery(sql);
			// STEP 5: Extract data from result set
			while (rs.next()) {
				String className = rs.getString("className");
				System.out.println("className: " + className);
			}
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} finally {

			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
}
