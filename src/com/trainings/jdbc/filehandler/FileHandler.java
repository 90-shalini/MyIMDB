package com.trainings.jdbc.filehandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

	public List<String> getFileContent(String fileName){

		List<String> lines = new ArrayList<String>();
		
		File file = new File(fileName);
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;

		try {
			fr = new FileReader(file);			
			br = new BufferedReader(fr);

	/**For each line from file , will split on ";", will insert values in different tables to generate ID's, 
	set all object properties and add into arrayList of file object**/

			while((line = br.readLine())!=null){
				if(line.startsWith("Year")||line.startsWith("INT"))
					continue;
				else
					lines.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}
//----------------------------------------------------------------------------------
}
