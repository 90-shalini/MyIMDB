package com.trainings.jdbc.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FetchDataFromFile {
	File file = new File(".\\data\\film.csv");
	FileReader fr = null;
	BufferedReader br = null;
	String line = null;
	String []dataArray = null;
	public void getData(){
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			while((line = br.readLine())!=null){
				dataArray = line.split(";");
				System.out.println(dataArray[3]);
			} 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
