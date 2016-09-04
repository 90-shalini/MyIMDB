package com.tranings.jdbc.test;

import com.trainings.jdbc.database.DBConnection;

public class Test {

	public static void main(String[] args) {
		// Single object of DB Connection using singleton class, single connection
		DBConnection db = DBConnection.getInstance();
		MainHandler.fillDB(db.getConnection());
		
		
		
		
		
	}

}
