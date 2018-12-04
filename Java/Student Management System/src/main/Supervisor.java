// Author: Mason Powell
// Date: 08/05/2018
// Purpose: Reads in a file containing a list of supervisors and builds a map navigated by first name

package main;

import java.io.*;
import java.util.*;


public class Supervisor {
	final Map<String,Name> supervisorMap = new HashMap<String,Name>();
	private Scanner inFile;
	
	public Supervisor() {
		try {
			inFile = new Scanner(new FileReader("src/main/supervisorList.txt"));
		} catch (FileNotFoundException fnfe) {
			System.out.println("supervisorList.txt cannot be found");
		}
		buildMap();
		inFile.close();
	}
	
	private void buildMap(){
		/**
		 * Builds the map of supervisors taken from the file input, with the key being the supervisors name and the values Name objects
		 * 
		 * Example Usage:
		 * buildMap()
		 */
		
		while(inFile.hasNextLine()){
			String inputLine = inFile.nextLine();
			Scanner lineScanner = new Scanner(inputLine);
			String k = lineScanner.next();
			String secName = lineScanner.next();
			Name supName = new Name(k, secName);
			if(!supervisorMap.containsKey(k)){
				// System.out.println(supName)		// Check if iterating correctly
				supervisorMap.put(k, supName);
			}
			lineScanner.close();
		}
	}
	
	public String toString(){
		/**
		  * Returns all map values by storing all keys into an array and then iterating through the map using the values in the array
		  * 
		  * Returns:
		  * All the currently stored supervisors Name.toString() values
		  */
		
		String mapValues = "";
		Set<String> s = supervisorMap.keySet();
		String[] supervisorArray = new String[supervisorMap.size()];
		supervisorArray = s.toArray(supervisorArray);
		for(int i = 0; i <= supervisorMap.size(); i++){
			mapValues = mapValues + supervisorMap.get(supervisorArray[i]) +"\n";
		}
		return mapValues;
	}
}
