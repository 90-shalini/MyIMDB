package com.trainings.jdbc.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class Utilities {

	public static int insertIntoTable(String tableName, Map<String,String> colValuesMap, Connection con){

		String values = "";
		String query = "INSERT INTO "+tableName+"(";
		Iterator it = colValuesMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			query += pair.getKey() + ",";
			values += "'"+pair.getValue()+"',";
		}
		
		query = query.substring(0, query.length()-1)+") VALUES("+values.substring(0,values.length()-1)+");";

		try{
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}

	//----This function is getting ID from table if already exists , 
	
	
	public static int getIDIfExistsFromTable(String tableName, Map<String,String> whereClause, Connection con){

		int id = 0;
		String query = "SELECT id from "+tableName+" WHERE ";
		Iterator it = whereClause.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			query += pair.getKey() + " = " + "'" + pair.getValue() + "' AND ";
		}

		query = query.substring(0, query.length() - 5)+";";
		ResultSet rs = null;
		try{
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){
				id = rs.getInt("id");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}


		return id;
	}

	//---------------------Getting ID if already present, creating new if not Present

	public static int getIDFromTable(String tableName, Map<String,String> whereClause, Connection con){

		int id=-1;
		id = (getIDIfExistsFromTable(tableName, whereClause, con) == 0) ? insertIntoTable(tableName, whereClause, con) : getIDIfExistsFromTable(tableName, whereClause, con); 
		
		if(id == 0){
			id = getIDIfExistsFromTable(tableName, whereClause, con);
		}
		
		return id;
	}

	//----------------------------------------------------------------



	public static boolean isInteger(String s) {
		return isInteger(s,10);
	}

	//----------------------------------------------------------------


	public static boolean isInteger(String s, int radix) {
		if(s.isEmpty()) return false;
		for(int i = 0; i < s.length(); i++) {
			if(i == 0 && s.charAt(i) == '-') {
				if(s.length() == 1) return false;
				else continue;
			}
			if(Character.digit(s.charAt(i),radix) < 0) return false;
		}
		return true;
	}

	//----------------------------------------------------------------

}
