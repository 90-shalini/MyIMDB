package com.trainings.jdbc.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	static Properties configProp = new Properties();
	static InputStream inputConfig= null;
	private static String URL= null;
	private static String username = null;
	private static String password= null;
	static Connection conn = null;

	public void getURL(){
		try {
			inputConfig = new FileInputStream(".\\config\\config.properties");
			configProp.load(inputConfig);
			URL= "jdbc:"+configProp.getProperty("DRIVER")+"://"+configProp.getProperty("SERVER")+":"+configProp.getProperty("PORT")+"/"+configProp.getProperty("DATABASENAME");
			System.out.println(URL);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}

	}
	public static Connection getConnection(){
		username = configProp.getProperty("USERNAME");
		password = configProp.getProperty("PASSWORD");
		System.out.println(username+password);

		try {
			conn = DriverManager.getConnection(URL, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}


}
