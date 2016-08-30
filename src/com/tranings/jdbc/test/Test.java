package com.tranings.jdbc.test;

import com.trainings.jdbc.database.DBConnection;
import com.trainings.jdbc.utils.FetchDataFromFile;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBConnection db = new DBConnection();
		db.getURL();
		db.getConnection();
		FetchDataFromFile f = new FetchDataFromFile();
		f.getData();
	}

}
