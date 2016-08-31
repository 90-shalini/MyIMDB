package com.tranings.jdbc.test;

import java.util.ArrayList;

import com.trainings.jdbc.database.DBConnection;
import com.trainings.jdbc.tables.Film;
import com.trainings.jdbc.utils.FetchDataFromFile;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBConnection db = new DBConnection();
		db.getURL();
		
		FetchDataFromFile f = new FetchDataFromFile(DBConnection.getConnection());
		ArrayList<Film> films= f.getFilmData();
		//f.createFilmTable(films);

	}

}
