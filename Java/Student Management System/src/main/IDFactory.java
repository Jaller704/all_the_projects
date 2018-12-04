// Author: Mason Powell
// Date: 08/05/2018
// Purpose: Object factory for the IDs, ensures uniqueness and a level of abstraction away from the concrete StudentID class

package main;

import java.util.*;


public abstract class IDFactory implements ID {
	
	private static final Map<String,ID> IDS = new HashMap<String,ID>();
	
	
	public static ID getInstance(String letter, int number){
		/**
		 * Returns a new StudentID and places it into a map using the letter and number (formatted to 4 significant figures) as the key (if it isn't already in there)
		 * 
		 * Parameters:
		 * letter -- String containing a letter of the alphabet
		 * number -- int containing a number, the second part of the StudentID
		 * Example Usage:
		 * ID newID = IDFactory.getInstance("a", 1234);
		 */
		
		letter = letter.toLowerCase();
		final String k = String.format("%s%04d", letter, number);
		if(!IDS.containsKey(k)){
			ID newID = new StudentID(letter, number);
			IDS.put(k, newID);
		};
		return IDS.get(k);
	}
	
	public static ID getInstance(){
		/**
		 * Returns a new StudentID and places it into a map using it's toString (e.g. b2468) as the key (if it isn't already in there)
		 * 
		 * Example Usage:
		 * ID newID = IDFactory.getInstance();
		 */
		
		ID newID = new StudentID();
		final String k = newID.toString();
		if(!IDS.containsKey(k)) {
			IDS.put(k, newID);
		};
		return IDS.get(k);
	}
}