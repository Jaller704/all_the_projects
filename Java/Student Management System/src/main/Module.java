// Author: Mason Powell
// Date: 08/05/2018
// Purpose: Reads in a file containing a list of modules for either postgraduate or undergraduate and builds a map navigated by module code

package main;

import java.io.*;
import java.util.*;

class Module {
	final Map<String,String> moduleMap = new HashMap<String,String>();
	private Scanner inFile;
	
	Module(int listType){
		
		if(listType == 1){
			try {
				inFile = new Scanner(new FileReader("src/main/ugModuleList.txt"));
			} catch (FileNotFoundException fnfe) {
				System.out.println("ugModuleList.txt cannot be found");
			}
		} else if(listType == 2){
			try {
				inFile = new Scanner(new FileReader("src/main/pgModuleList.txt"));
			} catch (FileNotFoundException fnfe) {
				System.out.println("pgModuleList.txt cannot be found");
			}
		}
		buildMap();
		inFile.close();
	}
	
	private void buildMap(){
		/**
		 * Builds the map of modules taken from the file input, with the key being the module code and the values String containing the entire line
		 * 
		 * Example Usage:
		 * buildMap()
		 */
		
		while(inFile.hasNextLine()){
			String inputLine = inFile.nextLine();
			Scanner lineScanner = new Scanner(inputLine);
			String k = lineScanner.next();
			k = k.substring(0, 7);
			if(!moduleMap.containsKey(k)){
				moduleMap.put(k, inputLine);
			}
			lineScanner.close();
		}
	}
	
	public String toString(){
		/**
		  * Returns all map values by storing all keys into an array and then iterating through the map using the values in the array
		  * 
		  * Returns:
		  * All the currently stored modules String values
		  */
		
		String mapValues = "";
		Set<String> s = moduleMap.keySet();
		String[] moduleArray = new String[moduleMap.size()];
		moduleArray = s.toArray(moduleArray);
		for(int i = 0; i <= moduleMap.size(); i++){
			mapValues = mapValues + moduleMap.get(moduleArray[i]) +"\n";
		}
		return mapValues;
	}
}

