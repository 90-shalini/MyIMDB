package com.trainings.jdbc.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.trainings.jdbc.tables.Film;

public class FetchDataFromFile {

	File file = new File(".\\data\\film.csv");
	FileReader fr = null;
	BufferedReader br = null;
	String line = null;
	String []dataArray = null;
	ResultSet rs =null;
	Connection conn =null;
	Statement stmt = null;
	ArrayList<Film> filmList = new ArrayList<Film>();

	public FetchDataFromFile(Connection conn){
		this.conn= conn;
	}

	//Main function to set data in film object and return arraylist
	public ArrayList<Film> getFilmData(){
		int count = 1;
		try {
			fr = new FileReader(file);			
			br = new BufferedReader(fr);

			/**For each line from file , will split on ";", 
	will insert values in different tables to generate ID's, 
	set all object properties and add into arrayList of file object**/

			while((line = br.readLine())!=null){
				if(line.startsWith("Year")||line.startsWith("INT"))
				{
					System.out.println("Skipping Line");
					continue;
				}
				else{
					Film film = new Film();
					dataArray = line.split(";");
					film.setYearOfRelease(Integer.parseInt(dataArray[0]));
					if(dataArray[1].trim().length()>0)
						film.setLength(Integer.parseInt(dataArray[1]));
					film.setTitle(dataArray[2]);
					film.setPopularity(Integer.parseInt(dataArray[7]));			
					film.setAwards(setAwards(dataArray[8]));
					film.setImage(dataArray[9]);
					film.setGenre_id(getTableID(dataArray[3],"genre"));
					//System.out.println("Genre done");
					film.setActor_id(getTableID(dataArray[4],"actor"));
					//System.out.println("Actor done");
					film.setActress_id(getTableID(dataArray[5],"actress"));
					//System.out.println("Actress done");
					film.setDirector_id(getTableID(dataArray[6],"director"));
					//System.out.println("Director done");
					//filmList.add(film);
					createFilmTable(film,count++);
					
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return filmList;

	}

	/**Set Awards column value(taking string from file and converting to char, 
	 * to store as enum in database)**/
	public char setAwards(String yesNO){
		if(yesNO.equalsIgnoreCase("No"))
			return 'N';
		else
			return 'Y';

	}

	//Get ID from particular table when value is present/not present(create new row and get ID)
	public int getTableID(String data,String tableName){
		//System.out.println(data);
		int id = 0;
		String names[] =null;
		String firstName;
		String lastName;
		String selectQuery=null;
		String insertQuery = null;		
		if(data.contains(",")){
			firstName=data.split(",")[1];
			lastName=data.split(",")[0];
		}
		else{
			firstName = "FNU";
			lastName = data;
		}

		//fixing firstname lastname to fit into sql
		firstName = firstName.trim().replaceAll("'", "''");
		lastName = lastName.trim().replaceAll("'", "''");

		if(tableName.equals("genre"))
		{
			selectQuery = "SELECT id FROM genre WHERE subject='"+data+"';";
			insertQuery= "INSERT INTO genre(subject) VALUES ('"+data+"');";
		}
		else {
			selectQuery = "SELECT id FROM "+ tableName.toLowerCase() +" WHERE firstName='"+firstName+"'"+" AND lastName='"+lastName+"';";
			insertQuery= "INSERT INTO "+ tableName.toLowerCase() +"(firstName, lastName) VALUES ('"+firstName+"',"+"'"+lastName+"');";
		}


		try {
			stmt = conn.createStatement();
			//System.out.println("SELECT QUERY: "+selectQuery);
			id = getID(selectQuery);
			//System.out.println("ID: "+id);
			if(id==0){
				stmt.executeUpdate(insertQuery);
				id=getID(selectQuery);
			}
			else
				return id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	///Get ID function when Value is present in the table
	public int getID(String selectQuery){
		int id =0;
		try {
			rs = stmt.executeQuery(selectQuery);
			if(rs!=null){
				while(rs.next())
					id = rs.getInt("id");
			}
			else
				return 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}


	/////Insert film into film table
//	public ArrayList<Integer> createFilmTable(ArrayList<Film> filmList){
	public void createFilmTable(Film film,int count){
		String selectQuery = null;
		int film_id = 0;
		//ArrayList<Integer> film_idList = new ArrayList<Integer>();
		try {
			//for(Film film:filmList)	{
				//selectQuery="SELECT id FROM film WHERE title='"+film.getTitle()+"'";
				//film_id = getID(selectQuery);
				//if(film_id==0){
					//insert film arraylist in film table
					PreparedStatement ps = conn.prepareStatement("INSERT INTO film VALUES (?,?,?,?,?,?,?,?,?,?,?)");
					ps.setInt(1, count);
					ps.setString(2, film.getTitle());
					ps.setInt(3, film.getYearOfRelease());
					ps.setInt(4, film.getLength());
					ps.setInt(5, film.getPopularity());
					ps.setString(6, String.valueOf(film.getAwards()));
					ps.setString(7, film.getImage());
					ps.setInt(8, film.getGenre_id());
					ps.setInt(9, film.getActor_id());
					ps.setInt(10, film.getActress_id());
					ps.setInt(11, film.getDirector_id());
					ps.executeUpdate();
					//film_idList.add(getID(selectQuery));
				//}
				//else
					//film_idList.add(film_id);
			//} 
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
//			if(conn!=null)
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
		}
//		System.out.println("totals number of records:"+ film_idList.size());
//		for(int id:film_idList)			
//			System.out.print(id+" , ");

//		return film_idList;

	}

}



