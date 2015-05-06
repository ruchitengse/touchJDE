package cse6324.uta.edu.persistent;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cse6324.uta.edu.properties.Database;
/**
*
* Persistent class for handling database operations
*/
public class DbConnection {
	
	public static Connection connection = null;
	public static Statement stmt = null;
	public static PreparedStatement preparedStmt = null;
	public static ResultSet rs = null;

	//Insert class from web page to database
	/*public static void insert(String cls, String insertCls) {
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
	}*/
	
	//New Insert
	public static void insertProject(String username, String projectName, String packageName, String className, String classData){
		
		try {
			connection = Database.getConnection();
			String query = "SELECT proj_id FROM jde_projects WHERE proj_name = ? AND user_name = ?";
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, projectName);
			preparedStmt.setString(2, username);
			rs = preparedStmt.executeQuery();
			
			boolean projectPresent = rs.next();
			boolean packagePresent = true;
			boolean classPresent = true;
			int projectId = 0;
			if(projectPresent){
				projectId = rs.getInt("proj_id");
			} else {
				query = "INSERT INTO jde_projects(proj_name, user_name) VALUES (?, ?)";
				preparedStmt = connection.prepareStatement(query);
				preparedStmt.setString(1, projectName);
				preparedStmt.setString(2, username);
				preparedStmt.executeUpdate();
				connection.commit();
				
				query = "SELECT proj_id FROM jde_projects WHERE proj_name = ? AND user_name = ?";
				preparedStmt = connection.prepareStatement(query);
				preparedStmt.setString(1, projectName);
				preparedStmt.setString(2, username);
				rs = preparedStmt.executeQuery();
				
				while(rs.next()){
					projectId = rs.getInt("proj_id");	
				}
				packagePresent = false;
			}
			
			if(packagePresent){
				query = "SELECT pkg_id FROM jde_packages WHERE proj_id = ? AND pkg_name = ?";
				preparedStmt = connection.prepareStatement(query);
				preparedStmt.setInt(1, projectId);
				preparedStmt.setString(2, username);
				rs = preparedStmt.executeQuery();
				packagePresent = rs.next();
			}
			
			int packageId = 0;
			if(packagePresent){
				packageId = rs.getInt("pkg_id");
			}
			else if(!packagePresent){
				query = "INSERT INTO jde_packages(pkg_name, proj_id, user_name) VALUES (?, ?, ?)";
				preparedStmt = connection.prepareStatement(query);
				preparedStmt.setString(1, packageName);
				preparedStmt.setInt(2, projectId);
				preparedStmt.setString(3, username);
				preparedStmt.executeUpdate();
				connection.commit();
				
				query = "SELECT pkg_id FROM jde_packages WHERE proj_id = ? AND pkg_name = ?";
				preparedStmt = connection.prepareStatement(query);
				preparedStmt.setInt(1, projectId);
				preparedStmt.setString(2, packageName);
				rs = preparedStmt.executeQuery();
				
				while(rs.next()){
					packageId = rs.getInt("pkg_id");
				}
				classPresent = false;
			}
			
			if(classPresent){
				query = "SELECT classID FROM classstore WHERE package_id = ? AND className = ?";
				preparedStmt = connection.prepareStatement(query);
				preparedStmt.setInt(1, packageId);
				preparedStmt.setString(2, className);
				rs = preparedStmt.executeQuery();
				classPresent = rs.next();
			}
			
			if(!classPresent){
				
				Random ran = new Random();
				int min = 10, max = 500;
				int rand = min + ran.nextInt(max - min + 1);
				String clsId = "cl" + rand;
				
				query = "INSERT INTO classstore(classID, className, classData, package_id) VALUES (?, ?, ?, ?)";
				preparedStmt = connection.prepareStatement(query);
				preparedStmt.setString(1, clsId);
				preparedStmt.setString(2, className);
				preparedStmt.setString(3, classData);
				preparedStmt.setInt(4, packageId);
				preparedStmt.executeUpdate();
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

	//Get data for open classes page
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map getData(String id, String event){
		
		String query = "";
		if("getprojects".equals(event)){
			query = "SELECT proj_id AS id, proj_name as name FROM jde_projects WHERE user_name = '" + id + "'";
		} else if("getpackages".equals(event)){
			query = "SELECT pkg_id AS id, pkg_name as name FROM jde_packages WHERE proj_id = " + id;
		} else if("getclasses".equals(event)){
			query = "SELECT classID AS id, className as name FROM classstore WHERE package_id = " + id;
		}
		Map idNameMap = new HashMap();
		try {
			connection = Database.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){
				if("getclasses".equals(event)){
					idNameMap.put(rs.getString("id"), rs.getString("name"));
				} else {
					idNameMap.put(rs.getInt("id"), rs.getString("name"));
				}
			}
		} catch(Exception e){
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
		return idNameMap;
	}

	//Get userid for open classes page
	public static int getUserId(String username) {
		
		int userId = 0;
		try {
			connection = Database.getConnection();
			stmt = connection.createStatement();
			String users = "SELECT user_id FROM jde_users where user_name='"
					+ username.trim() + "'";
			rs = stmt.executeQuery(users);
			if (rs.next()) {
				userId = rs.getInt("user_id");
			}
		} catch (SQLException e) {
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
		return userId;
	}
	
	/* ***** Handling User Register/Login Activities starts here ***** */
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
	/* ***** Handling User Register/Login Activities ends here ***** */
	
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
	/* End Query Function*/
	public static String getClassData(String classId) {
		
		String classData = "";
		try {
			connection = Database.getConnection();
			stmt = connection.createStatement();
			String query = "SELECT classData FROM classstore where classID='"
					+ classId.trim() + "'";
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				classData = rs.getString("classData");
			}
		} catch (SQLException e) {
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
		return classData;
	}
}