package com.trainings.jdbc.queryhandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.trainings.jdbc.tables.Film;

public class QueryHandler {
	
	public static List<Film> getDataFromTable(String tableName, String condition, String subString, Connection con){

		List<Film> result = new ArrayList<Film>();

		String query = "SELECT film.id,yearOfRelease,duration,title,genre.subject AS genre,"
		+"CONCAT(actor.firstName,',',actor.lastName) AS actorName,"
		+"CONCAT(actress.firstName,',',actress.lastName) AS actressName,"
		+"CONCAT(director.firstName,',',director.lastName) AS directorName,"
		+"popularity,awards,"
		+"image.name AS image"
		+" FROM film,genre,actor,actress,director,image"
		+" WHERE film.genre_id=genre.id"
		+" AND film.actor_id = actor.id"
		+" AND film.actress_id=actress.id"
		+" AND film.director_id=director.id"
		+" AND film.image_id=image.id"
		+" "+condition + subString;
		
		System.out.println(query);
		
		ResultSet rs = null;
		try{
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){
				Film film = new Film();
				
				film.setTitle(rs.getString("title"));
				film.setLength(rs.getInt("duration"));
				film.setPopularity(rs.getInt("popularity"));
				film.setYearOfRelease(rs.getInt("yearOfRelease"));
				
				film.setGenre(rs.getString("genre"));
				film.setActor(rs.getString("actorName"));
				film.setActress(rs.getString("actressName"));
				film.setDirector(rs.getString("directorName"));
				film.setAwards(rs.getString("awards").charAt(0));
				film.setImage(rs.getString("image"));
				
				result.add(film);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}


		return result;
	}

	
	public static List<Film> getTopNMovies(String tableName, String col,int limit, Connection con){

		
		List<Film> result = new ArrayList<Film>();	
		String query = "SELECT * from "+tableName+" ORDER BY "+ col+" DESC LIMIT " +limit+ ";";
		System.out.println(query);

		ResultSet rs = null;
		try{
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){
				Film film = new Film();
				
				film.setTitle(rs.getString("title"));
				film.setLength(rs.getInt("duration"));
				film.setPopularity(rs.getInt("popularity"));
				film.setYearOfRelease(rs.getInt("yearOfRelease"));
				
				result.add(film);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}


		return result;
	}

	
	
}
