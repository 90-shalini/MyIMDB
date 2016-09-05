package com.trainings.jdbc.queryhandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.trainings.jdbc.tables.Film;

public class QueryHandler {
	
	public static List<Film> getDataFromTable(String tableName, String col, String subString, Connection con){

		List<Film> result = new ArrayList<Film>();

		String query = "SELECT * from "+tableName+" WHERE "+col+" like '%"+subString+"%';";
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
