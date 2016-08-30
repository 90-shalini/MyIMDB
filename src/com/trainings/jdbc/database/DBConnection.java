package com.trainings.jdbc.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {
	Properties configProp = new Properties();
	InputStream inputConfig= null;
	private String URL= null;
	private String username = null;
	private String password= null;
	private String query = null;
	Connection conn = null;
	public void getURL(){
		try {
			inputConfig = new FileInputStream(".\\config\\config.properties");
			configProp.load(inputConfig);
			URL= "jdbc:"+configProp.getProperty("DRIVER")+"://"+configProp.getProperty("SERVER")+":"+configProp.getProperty("PORT")+"/"+configProp.getProperty("DATABASENAME");
			System.out.println(URL);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException ie) {
			// TODO Auto-generated catch block
			ie.printStackTrace();
		}

	}
	public Connection getConnection(){
		username = configProp.getProperty("USERNAME");
		password = configProp.getProperty("PASSWORD");
		System.out.println(username+password);
		//query = "SELECT * FROM ";
		
	try {
		conn = DriverManager.getConnection(URL, username, password);
		
//		Statement stmt =conn.createStatement();
//		ResultSet rs = stmt.executeQuery(query);
//		stmt.executeUpdate(query);
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return conn;
	}
	
	
}
