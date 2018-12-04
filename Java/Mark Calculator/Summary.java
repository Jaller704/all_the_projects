// Author: Mason Powell
// Date: 6/11/2017
// Purpose: Ask the student for their exam and coursework marks, calculate their score for the module and whether they have 
//			been awarded a pass, fail, or compensatable fail, repeated for each module. Once all marks have been calculated 
//			the student is told their overall marks and their stage result, and a bar graph is then drawn with a corresponding table 
//			printed to accompany it.

import java.util.Arrays;
import java.util.Scanner;

public class Summary {

	public static void main(String[] args) {
		studentSummary();

	}
	
	public static void studentSummary() {
		/**
		 * Asks the student for their exam and coursework marks, then calculates their score for the module and whether they have 
		 * been awarded a pass, fail, or compensatable fail, this is repeated for each module. Once all marks have been calculated 
		 * the student is told their overall marks and their stage result, a bar graph is then drawn and a corresponding table 
		 * printed to accompany it.
		 * 
		 * Example Usage:
		 * Summary.studentSummary()
		 */
		Scanner input = new Scanner(System.in);
		int [] moduleArray = {0, 0};
		int [] csc1023Array = {0};
		int [] moduleResult = {0, 0, 0, 0, 0, 0};
		boolean maxMinHit = false;
		String [] moduleGrade = {null, null, null, null, null, null};
		String [] moduleNamesArray = {"CSC1021", "CSC1022", "CSC1023", "CSC1024", "CSC1025", "CSC1026"};
		
		for(int i = 0; i < 6; i += 1) {
			if(i != 2) {
				System.out.println("Enter EXAM marks THEN COURSEWORK marks for " + moduleNamesArray[i] + ", SEPARATE with a SPACE");
				moduleArray[0] = input.nextInt();
				moduleArray[1] = input.nextInt();
				maxMinHit = MarkCalculator.maxMin(moduleArray);
				if(maxMinHit == false) {
					moduleResult[i] = MarkCalculator.computeMarks(moduleResult, moduleArray, i);
					moduleGrade[i] = MarkCalculator.computeResult(i, moduleArray, moduleResult, moduleGrade);
					}
				else {
					System.out.println("Please rerun the program");
					System.exit(0);
					}
				}
			else {
				System.out.println("Enter COURSEWORK mark for " + moduleNamesArray[i] +" then enter any character and press enter to confirm");
				csc1023Array[0] = input.nextInt();
				input.next();
				maxMinHit = MarkCalculator.maxMin(csc1023Array);
				if(maxMinHit == false) {
					moduleResult[i] = MarkCalculator.computeMarks(moduleResult, csc1023Array, i);
					moduleGrade[i] = MarkCalculator.computeResult(i, csc1023Array, moduleResult, moduleGrade);
				}
				else {
					System.out.println("Please rerun the program");
					System.exit(0);
					}
			}
		}
		
		System.out.println("Your module marks from CSC1021 to CSC1026 are: " + Arrays.toString(moduleResult));
		System.out.println("Your Stage Result is " + MarkCalculator.computeResult(0, null, moduleResult, moduleGrade) + "\n");
		StudentChart myChart = new StudentChart(moduleResult);
		myChart.draw();
		myChart.printSummary();
	}
}
