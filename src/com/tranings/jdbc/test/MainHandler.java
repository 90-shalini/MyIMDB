package com.tranings.jdbc.test;

import java.sql.Connection;
import java.util.List;

import com.trainings.jdbc.filehandler.FileHandler;
import com.trainings.jdbc.tables.Film;
import com.trainings.jdbc.utils.FilmUtility;

public class MainHandler {

	public static void fillDB(Connection con){
		
		//Take all the contents of file in a list 
		List<String> fileContent = new FileHandler().getFileContent(".\\data\\film.csv");
		//Set film object values and create filmList
		List<Film> films = new FilmUtility().convertFileDataToFilmList(fileContent);
		new FilmUtility().populateAllTables(films, con);
		
	}
}
