// Author: Mason Powell
// Date: 08/05/2018
// Purpose: Interface for the StudentID, defines the methods to be implemented

package main;

public interface ID {
	
	public int getNumber();
	
	public String getLetter();
	
	public String toString();
	/**
	 * Returns the letter and the number as a String, formatted to have 4 significant figures
	 */

	public String getID();
}
