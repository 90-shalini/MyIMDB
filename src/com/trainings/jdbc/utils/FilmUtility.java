package com.trainings.jdbc.utils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trainings.jdbc.tables.Film;

public class FilmUtility {

	public List<Film> convertFileDataToFilmList(List<String> lines){

		List<Film> films = new ArrayList<Film>();
		String entries [];
		for(String line : lines){

			entries = line.split(";");
			entries = cleanValues(entries);   //removing quotes(') and space from each data value

			Film film = new Film();
			//Checking if value is integer or not (to avoid exception in Int parsing)
			film.setYearOfRelease(Utilities.isInteger(entries[Film.FileColumns.YEAR.getColumn()])? Integer.parseInt(entries[Film.FileColumns.YEAR.getColumn()]) : -1);
			film.setLength(Utilities.isInteger(entries[Film.FileColumns.DURATION.getColumn()])? Integer.parseInt(entries[Film.FileColumns.DURATION.getColumn()]) : -1);
			film.setTitle(entries[Film.FileColumns.TITLE.getColumn()]);
			film.setGenre(entries[Film.FileColumns.GENRE.getColumn()]);
			film.setActor(entries[Film.FileColumns.ACTOR.getColumn()]);
			film.setActress(entries[Film.FileColumns.ACTRESS.getColumn()]);
			film.setDirector(entries[Film.FileColumns.DIRECTOR.getColumn()]);
			film.setPopularity(Utilities.isInteger(entries[Film.FileColumns.POPULARITY.getColumn()])? Integer.parseInt(entries[Film.FileColumns.POPULARITY.getColumn()]) : -1);
			film.setAwards((entries[Film.FileColumns.AWARDS.getColumn()]!=null && !entries[Film.FileColumns.AWARDS.getColumn()].isEmpty() &&  entries[Film.FileColumns.AWARDS.getColumn()].toUpperCase().charAt(0) == 'Y') ? 'Y' : 'N');
			film.setImage(entries[Film.FileColumns.IMAGE.getColumn()]);
			film.setGenre_id(0);
			film.setActor_id(0);
			film.setActress_id(0);
			film.setDirector_id(0);
			film.setImage_id(0);
			
			films.add(film);
			
		}


		return films;
	}
	
	//--------------------------------------------------------------------------------------------------------------
	
	public List<Film> populateAllTables(List<Film> films,Connection con){
		
		Map<String,String> filmMap = null;
		for(Film film : films){
			film.setGenre_id(Utilities.getIDFromTable("genre",getWhereClause("genre", film), con));			
			film.setActor_id(Utilities.getIDFromTable("actor",getWhereClause("actor", film), con));
			film.setActress_id(Utilities.getIDFromTable("actress",getWhereClause("actress", film), con));
			film.setDirector_id(Utilities.getIDFromTable("director",getWhereClause("director", film), con));
			film.setImage_id(Utilities.getIDFromTable("image",getWhereClause("image", film), con));
			filmMap = populateFilmMapping(film);
			Utilities.insertIntoTable("film", filmMap, con);
		}
		
		
		return films;
		
	}
	

	
	
	//----------------Generating Map of columns, data required for query purposes

	public Map<String,String> getWhereClause(String tableName, Film film){
		
		List<String> columns = getColumns(tableName);
		List<String> values = getValues(tableName, film);
		
		Map<String,String> whereClause = new HashMap<String,String>();
		
		for(int i = 0; i < columns.size(); i++)
			whereClause.put(columns.get(i), values.get(i));
		
		return whereClause;
	}
	
	//--------------------------------------------------------------------------------------------------------------

public List<String> getColumns(String tableName){
		
		List<String>columns = new ArrayList<String>();
		if(tableName.equalsIgnoreCase("GENRE")){
			columns.add("subject");
		}else if(tableName.equalsIgnoreCase("ACTOR") || (tableName.equalsIgnoreCase("ACTRESS")) ||(tableName.equalsIgnoreCase("DIRECTOR")) ){
			columns.add("firstName");
			columns.add("lastName");
		}else if(tableName.equalsIgnoreCase("IMAGE")){
			columns.add("name");
		}else
			System.out.println("Invalid Tablename");
		
		return columns;
		
	}

	//--------------------------------------------------------------------------------------------------------------

	public List<String> getValues(String tableName, Film film){
		
		List<String> values = new ArrayList<String>();
		if(tableName.equalsIgnoreCase("GENRE")){
			values.add(film.getGenre());
		}else if(tableName.equalsIgnoreCase("ACTOR")){
			values.addAll(getCommonValues(film.getActor()));
		}else if(tableName.equalsIgnoreCase("ACTRESS")){
			values.addAll(getCommonValues(film.getActress()));
		}else if(tableName.equalsIgnoreCase("DIRECTOR")){
			values.addAll(getCommonValues(film.getDirector()));
		}else if(tableName.equalsIgnoreCase("IMAGE")){
			values.add(film.getImage());
		}else
			System.out.println("Invalid Tablename");
		
		return values;
		
	}

	//--------------------------------------------------------------------------------------------------------------

	public List<String> getCommonValues(String currentCol){
	
		List<String> values = new ArrayList<String>();
		if(currentCol.contains(",")){
			values.add(currentCol.split(",")[0].replaceAll("'", "''").trim());
			values.add(currentCol.split(",")[1].replaceAll("'", "''").trim());
		}else{
			values.add("FNU");
			values.add(currentCol.replaceAll("'", "''").trim());
		}
		
		return values;
		
	}
	
	//--------------------------------------------------------------------------------------------------------------

	public Map<String,String> populateFilmMapping(Film film){
		
		Map<String,String> filmMap = new HashMap<String,String>();
		
		filmMap.put("yearOfRelease", String.valueOf(film.getYearOfRelease()));
		filmMap.put("duration", String.valueOf(film.getLength()));
		filmMap.put("title", film.getTitle());
		filmMap.put("genre_id", String.valueOf(film.getGenre_id()));
		filmMap.put("actor_id", String.valueOf(film.getActor_id()));
		filmMap.put("actress_id", String.valueOf(film.getActress_id()));
		filmMap.put("director_id", String.valueOf(film.getDirector_id()));
		filmMap.put("popularity", String.valueOf(film.getPopularity()));
		filmMap.put("awards", String.valueOf(film.getAwards()));
		filmMap.put("image_id", String.valueOf(film.getImage_id()));
		
		return filmMap;
		
	}
	
//------------------------------------------------------------------------------
	
	public String[] cleanValues(String[] entries){
		
		String newEntries[] = new String[entries.length];
		
		for(int i=0; i<entries.length;i++)
				newEntries[i] = entries[i].replaceAll("'", "''").trim();	
		return newEntries;
	}
	
//------------------------------------------------------------------------------

}
