// Author: Mason Powell
// Date: 23/10/2017
// Purpose: Calculate the overall mark for each individual module, combine these marks and average them to compute a Stage Result.

import java.util.Arrays;
import java.util.Scanner;

public class MarkCalculator {

	public static String computeResult(int i, int [] exAndCwMarksArray, int [] moduleResult, String [] moduleGrades) {
		/**
		 * Computes the result for each module and can also compute the stage result once all module results have been computed
		 * 
		 * Parameters:
		 * i -- Used for selecting the index value of moduleResult
		 * exAndCwMarksArray -- The array of input exam and coursework marks e.g. csc1025Array or marksArray
		 * moduleResult -- The array of results from computeMarks
		 * moduleGrades -- The array of grades for each module e.g moduleGrades[4] = "Pass"
		 * Returns:
		 * Changes depending on the usage and multiple if statements, if values for moduleGrades are still being filled, then the index location i of moduleGrades gets 
		 * returned, if all values for moduleGrades are filled, then the Stage Result is returned.
		 * Example Usage:
		 * moduleGrade[5] = computeResult(5, csc1026Array, moduleResult, moduleGrade);
		 * System.out.println("Your Stage Result is " + computeResult(0, null, moduleResult, moduleGrade));
		 */
		
		int checkGrades = 0;
		int passCheck = 0;
		int compFailCheck = 0;
		
		for(int j = 0; j < 6; j += 1) {
			if(moduleGrades[j] != null) {
				checkGrades += 1;
			}
		}
		
		if(checkGrades == 6) {
			for(int j = 0; j < 6; j += 1) {
				if(moduleGrades[j] == "Pass") {
					passCheck += 1;
				}
				if(moduleGrades[j] == "CFail") {
					compFailCheck += 1;
				}
				if(moduleGrades[j] == "Fail") {
					return "Fail";
				}
			}
			
			if(passCheck == 6) {
				return "Pass";
			}
			if((moduleResult[0] + moduleResult[1] + moduleResult[2] + moduleResult[3] + moduleResult[4] + moduleResult[5])/6 >= 40 && compFailCheck <= 2) {
				return "Pass By Compensation";
			}
			if(compFailCheck > 2) {
				return "Fail";
			}
		}
		else {
			moduleResult[i] = computeMarks(moduleResult, exAndCwMarksArray, i);
			if(moduleResult[i] >= 40) {
				moduleGrades[i] = "Pass";
				return moduleGrades[i];
			}
			if(i != 1) {
				if(moduleResult[i] >= 35 && moduleResult[i] < 40) {
					moduleGrades[i] = "CFail";
					return moduleGrades[i];
				}
			}
			if(i == 1 && moduleResult[i] < 40) {
				moduleGrades[i] = "Fail";
				return moduleGrades[i];
			}
			if(moduleResult[i] < 35) {
				moduleGrades[i] = "Fail";
				return moduleGrades[i];
			}			
		}
		return null;
	}

	public static int computeMarks(int [] modResult, int [] exAndCwMarksArray, int i) { 
		/**
		 * Runs the input marks through an equation to give an overall mark for each module
		 * 
		 * Parameters:
		 * modResult -- The array of overall module marks to be filled
		 * exAndCwMarksArray -- The array of input exam and coursework marks e.g. csc1025Array or marksArray
		 * i -- The value to be used for selecting a case in the switch statement, each case corresponding to the order of the modules for the coursework 
		 * 		weightings e.g. csc1021Array wants case 0, csc1022Array wants case 1. Also used for the index value of modResult
		 * Returns:
		 * The result of the equation in the index location i of modResult
		 * Example usage:
		 * csc1021Array[0] = 65;
		 * csc1021Array[1] = 40;
		 * System.out.println(computeMarks(overallMarksArray, csc1021Array, 0));
		 */
		
		switch(i) {
			case 0:
				if(exAndCwMarksArray[0] <= 35 ^ exAndCwMarksArray[1] <= 35) {
					modResult[i] = 35;
					return modResult[i];
				}
				else {
					modResult[i] = ((((exAndCwMarksArray[1] * 50) + (exAndCwMarksArray[0] * (100 - 50))) + 50) / 100);
					System.out.println(modResult[i]);
					return modResult[i];
				}
			case 1:
				if(exAndCwMarksArray[0] <= 35 ^ exAndCwMarksArray[1] <= 35) {
					modResult[i] = 35;
					return modResult[i];
				}
				else {
					modResult[i] = ((((exAndCwMarksArray[1] * 40) + (exAndCwMarksArray[0] * (100 - 40))) + 50) / 100);
					System.out.println(modResult[i]);
					return modResult[i];
				}
			case 2:
				modResult[i] = exAndCwMarksArray[0];
				System.out.println(modResult[i]);
				return modResult[i];
			case 3:
				if(exAndCwMarksArray[0] <= 35 ^ exAndCwMarksArray[1] <= 35) {
					modResult[i] = 35;
					return modResult[i];
				}
				else {
					modResult[i] = ((((exAndCwMarksArray[1] * 50) + (exAndCwMarksArray[0] * (100 - 50))) + 50) / 100);
					System.out.println(modResult[i]);
					return modResult[i];
				}
			case 4:
				if(exAndCwMarksArray[0] <= 35 ^ exAndCwMarksArray[1] <= 35) {
					modResult[i] = 35;
					return modResult[i];
				}
				else {
					modResult[i] = ((((exAndCwMarksArray[1] * 20) + (exAndCwMarksArray[0] * (100 - 20))) + 50) / 100);
					System.out.println(modResult[i]);
					return modResult[i];
				}
			case 5:
				if(exAndCwMarksArray[0] <= 35 ^ exAndCwMarksArray[1] <= 35) {
					modResult[i] = 35;
					return modResult[i];
				}
				else {
					modResult[i] = ((((exAndCwMarksArray[1] * 35) + (exAndCwMarksArray[0] * (100 - 35))) + 50) / 100);
					System.out.println(modResult[i]);
					return modResult[i];
				}
			default: 
				System.out.println("Unexpected");
				break;
			}
		
		return modResult[i];
	}
	
	public static boolean maxMin(int [] marksArray) {
		/**
		 * Checks the inputed values on whether they have exceeded the limit or are below a certain limit
		 * 
		 * Parameters:
		 * marksArray -- The array of inputed marks e.g. csc1025Array, max of 2 elements
		 * i -- The value for computeMarks() second parameter, switch case
		 * Returns:
		 * Prints different things depending on the result of a few if statements, e.g. if one of the marks is over 100, the user is told this is not 
		 * valid. If everything passes the checks then the overall result of computeMarks() is printed
		 * Example usage:
		 * csc1021Array[0] = 65;
		 * csc1021Array[1] = 40;
		 * maxMinOverall(csc1021Array, 0);
		 */
		
		int arrayLength = marksArray.length;
		
		if(arrayLength == 2) {
			if(marksArray[0] > 100 || marksArray[1] > 100) {
				System.out.println("Cannot have a mark greater than 100!");
				return true;
			}
			if(marksArray[0] < 0 || marksArray[1] < 0) {
				System.out.println("Cannot have a mark less than 0!");
				return true;
			}
			else {
				return false;
			}
		}
		if(arrayLength == 1) {
			if(marksArray[0] > 100) {
				System.out.println("Cannot have a mark greater than 100!");
				return true;
			}
			if(marksArray[0] < 0) {
				System.out.println("Cannot have a mark less than 0!");
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
}
