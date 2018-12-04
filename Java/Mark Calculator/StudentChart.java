// Author: Mason Powell
// Date: 6/11/2017
// Purpose: Draw a bar graph with height corresponding to the users input marks, print a table corresponding to the chart.

public class StudentChart {
	
	static int [] moduleMarks = {0, 0, 0, 0, 0, 0};
	static String [] moduleGrades = {null, null, null, null, null, null};
	
	public StudentChart(int [] inputModuleMarks) {
		moduleMarks = inputModuleMarks;
	}
	
	public static String computeResult(int i, int [] moduleResult, String [] moduleGrades) {
		/**
		 * Computes the result for each module
		 * 
		 * Parameters:
		 * i -- Used for selecting the index value of moduleResult
		 * moduleResult -- An array of module results e.g. {20, 40, 56, 65, 34, 90}
		 * moduleGrades -- The array of grades for each module e.g moduleGrades[4] = "Pass"
		 * Returns:
		 * Changes depending on multiple if statements, the index location i of moduleGrades gets 
		 * returned.
		 * Example Usage:
		 * moduleGrade[5] = computeResult(5, csc1026Array, moduleResult, moduleGrade);
		 * System.out.println("Your Stage Result is " + computeResult(0, null, moduleResult, moduleGrade));
		 */
		
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
		return null;
	}
	
	public void draw() {
		/**
		 * Draws a bar chart based on the students result for each module
		 * 
		 * Example Usage:
		 * int [] myMarks = {100, 35, 37, 80, 20, 65};
		 * StudentChart myChart = new StudentChart(myMarks);
		 * myChart.draw();
		 */
		
		Bar xAxis = new Bar();
		Bar yAxis = new Bar();
		Bar csc1021 = new Bar();
		Bar csc1022 = new Bar();
		Bar csc1023 = new Bar();
		Bar csc1024 = new Bar();
		Bar csc1025 = new Bar();
		Bar csc1026 = new Bar();
		
		for(int i = 0; i < 6; i += 1) {
			moduleGrades[i] = computeResult(i, moduleMarks, moduleGrades);
		}
		xAxis.changeSize(5,225);
		xAxis.changeColour(Colour.BLACK);
		xAxis.moveHorizontal(-20);
		xAxis.makeVisible();
		
		yAxis.changeSize(225,5);
		yAxis.changeColour(Colour.BLACK);
		yAxis.moveVertical(200);
		yAxis.moveHorizontal(-40);
		yAxis.makeVisible();
		
		csc1021.changeSize(20, moduleMarks[0]);
		csc1021.moveVertical(200 - moduleMarks[0]);
		if(moduleGrades[0] == "Fail") {
			csc1021.changeColour(Colour.RED);
		}
		if(moduleGrades[0] == "CFail") {
			csc1021.changeColour(Colour.YELLOW);
		}
		if(moduleGrades[0] == "Pass") {
			csc1021.changeColour(Colour.GREEN);
		}
		if(moduleGrades[0] == "Pass" && moduleMarks[0] >= 70) {
			csc1021.changeColour(Colour.MAGENTA);
		}
		csc1021.makeVisible();
		
		csc1022.changeSize(20, moduleMarks[1]);
		csc1022.moveVertical(200 - moduleMarks[1]);
		csc1022.moveHorizontal(30);
		if(moduleGrades[1] == "Fail") {
			csc1022.changeColour(Colour.RED);
		}
		if(moduleGrades[1] == "CFail") {
			csc1022.changeColour(Colour.YELLOW);
		}
		if(moduleGrades[1] == "Pass") {
			csc1022.changeColour(Colour.GREEN);
		}
		if(moduleGrades[1] == "Pass" && moduleMarks[1] >= 70) {
			csc1022.changeColour(Colour.MAGENTA);
		}
		csc1022.makeVisible();
		
		csc1023.changeSize(20, moduleMarks[2]);
		csc1023.moveVertical(200 - moduleMarks[2]);
		csc1023.moveHorizontal(60);
		if(moduleGrades[2] == "Fail") {
			csc1023.changeColour(Colour.RED);
		}
		if(moduleGrades[2] == "CFail") {
			csc1023.changeColour(Colour.YELLOW);
		}
		if(moduleGrades[2] == "Pass") {
			csc1023.changeColour(Colour.GREEN);
		}
		if(moduleGrades[2] == "Pass" && moduleMarks[2] >= 70) {
			csc1023.changeColour(Colour.MAGENTA);
		}
		csc1023.makeVisible();

		csc1024.changeSize(20, moduleMarks[3]);
		csc1024.moveVertical(200 - moduleMarks[3]);
		csc1024.moveHorizontal(90);
		if(moduleGrades[3] == "Fail") {
			csc1024.changeColour(Colour.RED);
		}
		if(moduleGrades[3] == "CFail") {
			csc1024.changeColour(Colour.YELLOW);
		}
		if(moduleGrades[3] == "Pass") {
			csc1024.changeColour(Colour.GREEN);
		}
		if(moduleGrades[3] == "Pass" && moduleMarks[3] >= 70) {
			csc1024.changeColour(Colour.MAGENTA);
		}
		csc1024.makeVisible();
		
		csc1025.changeSize(20, moduleMarks[4]);
		csc1025.moveVertical(200 - moduleMarks[4]);
		csc1025.moveHorizontal(120);
		if(moduleGrades[4] == "Fail") {
			csc1025.changeColour(Colour.RED);
		}
		if(moduleGrades[4] == "CFail") {
			csc1025.changeColour(Colour.YELLOW);
		}
		if(moduleGrades[4] == "Pass") {
			csc1025.changeColour(Colour.GREEN);
		}
		if(moduleGrades[4] == "Pass" && moduleMarks[4] >= 70) {
			csc1025.changeColour(Colour.MAGENTA);
		}
		csc1025.makeVisible();
		
		csc1026.changeSize(20, moduleMarks[5]);
		csc1026.moveVertical(200 - moduleMarks[5]);
		csc1026.moveHorizontal(150);
		if(moduleGrades[5] == "Fail") {
			csc1026.changeColour(Colour.RED);
		}
		if(moduleGrades[5] == "CFail") {
			csc1026.changeColour(Colour.YELLOW);
		}
		if(moduleGrades[5] == "Pass") {
			csc1026.changeColour(Colour.GREEN);
		}
		if(moduleGrades[5] == "Pass" && moduleMarks[5] >= 70) {
			csc1026.changeColour(Colour.MAGENTA);
		}
		csc1026.makeVisible();
	}

	public void printSummary() {
		/**
		 * Prints a formatted table to complement the bar chart created by draw()
		 * 
		 * Example Usage:
		 * int [] myMarks = {100, 35, 37, 80, 20, 65};
		 * StudentChart myChart = new StudentChart(myMarks);
		 * myChart.draw();
		 * myChart.printSummary();
		 */
		System.out.format("%6s %3s %9s %9s %9s %9s %9s %9s %n", "Module", "|", "csc1021", "csc1022", "csc1023", "csc1024", "csc1025", "csc1026");
		System.out.format("%6s %3s %9s %9s %9s %9s %9s %9s %n", "Mark", "|", moduleMarks[0], moduleMarks[1], moduleMarks[2], moduleMarks[3], moduleMarks[4], moduleMarks[5]);
		System.out.format("%6s %3s %9s %9s %9s %9s %9s %9s", "Grade", "|", moduleGrades[0], moduleGrades[1], moduleGrades[2], moduleGrades[3], moduleGrades[4], moduleGrades[5]);
	}
}
