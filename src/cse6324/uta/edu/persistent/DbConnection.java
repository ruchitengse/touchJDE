package cse6324.uta.edu.persistent;

import java.sql.*;
import java.util.Random;

import cse6324.uta.edu.properties.Database;

public class DbConnection {
	
	public static Connection connection = null;
	public static Statement stmt = null;
	public static PreparedStatement preparedStmt = null;
	public static ResultSet rs = null;

	public static void insert(String cls, String insertCls) {
		try {
			// stmt = conn.createStatement();
			Random ran = new Random();
			int min = 10, max = 500;
			int rand = min + ran.nextInt(max - min + 1);
			connection = Database.getConnection();
			System.out.println("conn:" + connection);
			stmt = connection.createStatement();
			String sqlcheck = "SELECT * FROM classstore where className='"
					+ cls + "'";
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
				String sqlupdate = "UPDATE classstore SET classData='"
						+ insertCls + "' WHERE className='" + cls + "'";
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (preparedStmt != null) {
				try {
					preparedStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (connection != null) {
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

	//User Register
	public static boolean register(String username, String email, String password) {
		try {
			connection = Database.getConnection();
			System.out.println("conn:" + connection);
			stmt = connection.createStatement();
			String checkusers = "SELECT * FROM jde_users where user_name='"
					+ username + "'";
			rs = stmt.executeQuery(checkusers);
			if (!rs.next()) {
				System.out.println("User not present");
				String sql = "INSERT into jde_users (user_name, email, password) values (?,?,?)";
				preparedStmt = connection.prepareStatement(sql);
				preparedStmt.setString(1, username);
				preparedStmt.setString(2, email);
				preparedStmt.setString(3, password);
				preparedStmt.executeUpdate();
				connection.commit();
				return true;
			} 
			else {
				System.out.println("User already present");
				return false;
			}
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (preparedStmt != null) {
				try {
					preparedStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
					Database.closeConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	//Login User
	public static boolean login(String username, String password) {
		try {
			connection = Database.getConnection();
			System.out.println("conn:" + connection);
			stmt = connection.createStatement();
			String users = "SELECT user_name FROM jde_users where user_name='"
					+ username + "' and password='" + password + "'";
			rs = stmt.executeQuery(users);
			if (rs.next()) {
				System.out.println("login info found");
				return true;
			} 
			else {
				System.out.println("login info not found");
				return false;
			}
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (preparedStmt != null) {
				try {
					preparedStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
					Database.closeConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	//Fetch Class
	public static void query() {
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

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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

	/*public static void main(String[] args) {
		DbConnection.insert("user1", "user1");
	}*/
}