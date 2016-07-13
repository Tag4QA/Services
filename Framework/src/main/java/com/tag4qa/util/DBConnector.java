package com.tag4qa.util;

/**
 * Class to initialize the DB connection and to fetch the result set from the DB..
 * 
 * @author mperumal
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class DBConnector {

	private static String db_username = "blingo";
	private static String db_password = "blingo";
	private static String dbIWEHostName = "qpzmdb01.pchoso.com";
	private static String dbRBHostName = "qrealdb01.pchoso.com";
	private static String db_url;
	private Connection iwe_connection = null;
	private Connection rb_connection = null;
	private static List<Connection> conn_list = new ArrayList<Connection>();
	private static DBConnector db_Instance;

	/**
	 * Close the open database connection of all the database
	 * 
	 * @author mperumal
	 * @throws SQLException 
	 */
	public static void dbConnectionClose() {
		for (Connection c : conn_list) {
			try {
				if (!c.isClosed() || c != null) {
					c.close();			
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Return the DB Connection to IWEQAINT database
	 * 
	 * @return Connection instance
	 * @throws SQLException
	 * @author mperumal
	 * 
	 */
	public Connection getIWEConnection() throws SQLException {
		if (iwe_connection == null || iwe_connection.isClosed()) {
			System.out.println("inside getIWEConnection");
			db_url = "jdbc:mysql://" + dbIWEHostName + ":3306/iweqaint?autoReconnect=true&useUnicode=yes&failOverReadOnly=false&maxReconnects=10";
			iwe_connection = DriverManager.getConnection(db_url.trim(), db_username.trim(), db_password.trim());
			System.out.println("Connected to Db");
			conn_list.add(iwe_connection);
		}
		return iwe_connection;
	}
	
	/**
	 * Return the DB Connection to RealToken database
	 * 
	 * @return Connection instance
	 * @throws SQLException
	 * @author mperumal
	 * 
	 */
	public Connection getRBConnection() throws SQLException {
		if (rb_connection == null || rb_connection.isClosed()) {
			System.out.println("inside getRBConnection");
			db_url = "jdbc:mysql://" + dbRBHostName + ":3306/rbcoreqa?autoReconnect=true&useUnicode=yes&failOverReadOnly=false&maxReconnects=10";
			rb_connection = DriverManager.getConnection(db_url.trim(), db_username.trim(), db_password.trim());
			System.out.println("Connected to Db");
			conn_list.add(rb_connection);
		}
		return rb_connection;
	}

	/**
	 * Execute the SQL query and return the result set value of the first
	 * column.
	 *  
	 * @param conn
	 * @param query
	 * @return
	 * @throws SQLException
	 * @author mperumal
	 */
	public String getResultSet(Connection conn, String query) throws SQLException {
		Statement stmt = conn.createStatement();
		// Execute the SQL Query. Store results in ResultSet
		ResultSet rs = stmt.executeQuery(query);
		// Iterate and return the value
		while (rs.next()) {
			return rs.getString(1);
		}
		return null;
	}
	
	/**
	 * Execute the update SQL query and return the update status
	 *  
	 * @param conn
	 * @param query
	 * @return
	 * @throws SQLException
	 * @author mperumal
	 */
	public int updateQuery(Connection conn, String query) throws SQLException {
		Statement stmt = conn.createStatement();
		// Execute the SQL Query. Store results in ResultSet
		return stmt.executeUpdate(query);
	}
	
	/**
	 * Execute the SQL query and return the result set value of the first
	 * column with multiple rows.
	 * 
	 * @param conn
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public LinkedList<String> getResultSetList(Connection conn, String query) throws SQLException {
		Statement stmt = conn.createStatement();
		// Execute the SQL Query. Store results in ResultSet
		ResultSet rs = stmt.executeQuery(query);
		LinkedList<String> list_row_values = new LinkedList<String>();
		// Iterate and return the value
		while (rs.next()) {
			String s1 = rs.getString(1).trim();
			list_row_values.add(s1);
		}
		return list_row_values;
	}
	
	/**
	 * Execute the SQL query and return the result set value of all the column
	 * as a Map and for all the rows as List.
	 * 
	 * @param conn
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public LinkedList<LinkedHashMap<String, String>> getResultSetAsMap(Connection conn, String query) {

		LinkedList<LinkedHashMap<String, String>> resultset_multiple_row = new LinkedList<LinkedHashMap<String, String>>();
		LinkedHashMap<String, String> column_value_map;		
		try {
			Statement stmt = conn.createStatement();
			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int column_count = rsmd.getColumnCount();
			String column_name;
			String column_value;
			while (rs.next()) {
				column_value_map = new LinkedHashMap<String, String>();
				for (int i = 1; i <= column_count; i++) {
					column_name = rsmd.getColumnName(i).trim();
					column_value = rs.getString(i);
					column_value = column_value == null ? "" : column_value;
					column_value_map.put(column_name, column_value);
				}
				resultset_multiple_row.add(column_value_map);
			}
		} catch (SQLException SQL) {
			SQL.printStackTrace();
		}
		return resultset_multiple_row;
	}
	
	/**
	 * Singleton class to initialize the DBConenctor class
	 * 
	 * @return DBConnector instance
	 * @author mperumal
	 */
	public static DBConnector getInstance(){
		if(db_Instance == null){
			synchronized(DBConnector.class){
				db_Instance = new DBConnector();
			}
		}
		return db_Instance;
	}
}
