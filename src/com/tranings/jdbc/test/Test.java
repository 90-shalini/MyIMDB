package com.tranings.jdbc.test;

import com.trainings.jdbc.database.DBConnection;
import com.trainings.jdbc.frontend.HomePage;

public class Test {

	public static void main(String[] args) {
		// Single object of DB Connection using singleton class, single connection
		DBConnection db = DBConnection.getInstance();
//		MainHandler.fillDB(db.getConnection());
		
//		Home hm = new Home();
//		hm.initialize(db.getConnection());
		
		/* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage(db.getConnection()).setVisible(true);
            }
        });
		
		
		
		
		
	}

}
