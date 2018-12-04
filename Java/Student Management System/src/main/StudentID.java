// Author: Mason Powell
// Date: 08/05/2018
// Purpose: 

package main;

import java.util.*;

public class StudentID extends IDFactory{
	private static final String[] LETTERS = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	private final int[] numbers = new int[9999];
	private String letter = null;
	private int number = -1;
	private String iD;
	
	
	StudentID() {
		generate();
		setID();
	}
	
	StudentID(String letter, int number) {
		boolean found = false;

		for(String l:LETTERS) {
			found = letter.equalsIgnoreCase(l);
			if(found) {
				setLetter(letter);
			}
		} 
		found = false;
		for(int i = 0; i < numbers.length; i++) {
			found = number==i;
			if(found){
				setNumber(number);
			}
		}
		generate();
		setID();
			
	}
	
	private void setNumber(int number) {
		this.number = number;
		
	}

	private void setLetter(String letter){
		this.letter = letter;
	}
	private void generate() {
		/**
		 * Fills the numbers array with numbers going 0 - 9999
		 */
		
		for(int i = 0; i < numbers.length; i++){
			numbers[i] = i + 1;
		}
	}
	
	private void setID() {
		/**
		 * Sets the ID to a combination of letter and 4 digit number, if letter or number values haven't been changed then it randomly chooses values
		 */
		
		if(letter==null || number == -1){
			setLetter(LETTERS[new Random().nextInt(LETTERS.length)]);
			setNumber(numbers[new Random().nextInt(numbers.length)]);
					
			iD = String.format("%s%04d", getLetter(), getNumber());
		}	else {
			iD = String.format("%s%04d", getLetter(), getNumber());
		}
		
	}
	
	public String getID(){
		return iD;
	}

	public int getNumber() {
		return number;
	}

	public String getLetter() {
		return letter;
	}
	
	public String toString(){
		return String.format("%s%04d", getLetter(), getNumber());
	}
}
