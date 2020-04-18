package com.maxlogic.tutorials.jdbc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionFactory {
	private static Driver driver;
	private static String dbURL;
	private static String dbUser;
	private static String dbPassword;
	private static Properties info = new Properties();
	
	static{
		Properties dbProperties = new Properties();
		try {
			dbProperties.load(ConnectionFactory.class.getClassLoader().getResourceAsStream("com/example/jdbc/db.properties"));
			String dbDriver = dbProperties.getProperty("oracle.database.driver");
			dbURL = dbProperties.getProperty("oracle.database.url");
			dbUser = dbProperties.getProperty("oracle.database.username");
			dbPassword = dbProperties.getProperty("oracle.database.password");
			
			driver = (Driver) Class.forName(dbDriver).newInstance();
			info.setProperty("user", dbUser);
			info.setProperty("password", dbPassword);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("DB properties file not found");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception while reading DB properties");
		} catch (InstantiationException|IllegalAccessException|ClassNotFoundException ex) { //Java 7 feature
			ex.printStackTrace();
			System.out.println("Error while loading driver class");
		} 
	}
	
	public static Connection getConnection() throws SQLException{
		Connection con = null;
		try {
			con = driver.connect(dbURL, info);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return con;
	}
	
	public static void close(Connection con, Statement stmt, ResultSet rs){
		try {
			if(rs != null)  rs.close();
			if(stmt != null) stmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
