package de.p39.asrs.server.controller.util.parser;

import java.io.BufferedReader;
import java.io.FileReader;

public class PropertiesReader {
	
	public static String getDBName(String pathToFile) {
		BufferedReader reader;
		try{
			reader = new BufferedReader(new FileReader(pathToFile));
			String[] splitted = reader.readLine().split("/");
			reader.close();
			return splitted[splitted.length-1];
		} catch (Exception e) {
			System.err.println(e.toString());
			System.exit(1);
		}
		return null;
	}

}
