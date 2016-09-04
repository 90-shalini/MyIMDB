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
	private Properties configurations = new Properties();
	
	static Connection conn = null;

	private static DBConnection dbCon = new DBConnection();

	private Properties loadProperties(){
		Properties configurations = new Properties();
		try{
			InputStream is = new FileInputStream(".\\config\\config.properties");
			configurations.load(is);
		}catch(IOException e){
			e.printStackTrace();
		}
		return configurations;
	}
	private DBConnection(){
		configurations = loadProperties();
	}

	public String getURL(){
		String URL= "jdbc:"+configurations.getProperty("DRIVER")+"://"+configurations.getProperty("SERVER")+":"+configurations.getProperty("PORT")+"/"+configurations.getProperty("DATABASENAME");
		System.out.println(URL);
		return URL;

	}
	public Connection getConnection(){

		String username = configurations.getProperty("USERNAME");
		String password = configurations.getProperty("PASSWORD");
		
		try {
			if(this.conn == null)
				conn = DriverManager.getConnection(getURL(), username, password);
			else
				return this.conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static DBConnection getInstance(){
		if(dbCon == null)
			dbCon = new DBConnection();

		return dbCon;
	}


}
