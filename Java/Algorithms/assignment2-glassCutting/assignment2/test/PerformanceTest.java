package test;

import java.util.*;

import main.*;

/**
 * @Purpose: The PerformanceTest class is used to compare the implemented
 *           algorithms in term of time and the number of sheets used
 *
 *           You can add additional methods if you need to in this class
 * 
 * @author RYK
 * @since 30/10/2018
 * extended by @author
 */

public class PerformanceTest {

	public static void main(String[] args) {

		System.out.println("***********************************************");
		System.out.println("*********** Performance analysis **************");
		System.out.println("**********************************************");

		System.out.println();
		/*
		 * You must complete the Generator class in order to generate a random
		 * test values. You must complete the Algorithms class in order to call
		 * the two algorithms.
		 * 
		 * You can use any additional method you created in this class
		 */

		/*
		 * 
		 * NOTE: you are using an unsorted list of shapes
		 */

		/**
		 * Remember: You need to calculate the time and number of sheets used
		 * for each run of each algorithm.
		 * 
		 * You are expected to run several tests (e.g. noOfTests=5) of your
		 * programs for, 10000, 20000, 30000, 40000, 50000 shapes
		 * (noOfShapes=10000, increment=10000) so that one can see how the
		 * algorithms perform for large datasets.
		 * 
		 * You are expected to run the same test a number of times to ensure
		 * accurate result (noOfRep=4). In this case, you need to calculate the
		 * average time and sheets needed for each run
		 **/

		// total number of tests - you need to CHANGE this value
		int noOfTests = 5;

		// number of repetitions for each test - you need to CHANGE this value
		int noOfRep = 4;

		// number of shapes needed for the first run - you need to CHANGE this
		// value
		int noOfShapes = 10000;

		// the increment in the number of shapes - you need to CHANGE this value
		int increment = 10000;
		
		Generator g = new Generator();
		Algorithms a = new Algorithms();
		List<Sheet> result;
		List<Shape> shapes;
		List<List<Shape>> dataset = new ArrayList<List<Shape>>();
		List<Long> timeResults = new ArrayList<Long>();
		List<Integer> sheetResults = new ArrayList<Integer>();
		
		long averageTime = 0;
		long startTime;
		long finalTime;
		int averageSheets = 0;
		int datasetIndex = 0;
		
		for(int i = 0; i < noOfTests; i++) {
			for(int j = 0; j < noOfRep; j++) {
				dataset.add(g.generateShapeList(noOfShapes));
			}
			noOfShapes = noOfShapes + increment;
		}
		
		System.out.println("----------------------------- NEXT FIT TESTS -----------------------------");
		for(int i = 0; i < noOfTests; i++) {
			for(int j = 0; j < noOfRep; j++) {
				startTime = System.nanoTime();
				result = a.nextFit(dataset.get(datasetIndex));
				finalTime = System.nanoTime() - startTime;
				averageTime = averageTime + finalTime;
				averageSheets = averageSheets + result.size();
				datasetIndex++;
				}
			
			timeResults.add((averageTime/noOfRep));
			sheetResults.add((averageSheets/noOfRep));
			averageTime = 0;
			averageSheets = 0;
		}
		
		noOfShapes = 10000;
		for(int i = 0; i < noOfTests; i++) {
			System.out.println("Shapes: " + noOfShapes +  "	|	Time (ms): " + (timeResults.get(i)/1000000) + ", Sheets: " + sheetResults.get(i));
			noOfShapes = noOfShapes + increment;
		}
		
		System.out.println("----------------------------- FIRST FIT TESTS -----------------------------");
		
		noOfShapes = 10000;
		datasetIndex = 0;
		timeResults = new ArrayList<Long>();
		sheetResults = new ArrayList<Integer>();
		
		for(int i = 0; i < noOfTests; i++) {
			for(int j = 0; j < noOfRep; j++) {
				startTime = System.nanoTime();
				result = a.firstFit(dataset.get(datasetIndex));
				finalTime = System.nanoTime() - startTime;
				averageTime = averageTime + finalTime;
				averageSheets = averageSheets + result.size();
				datasetIndex++;
				}
			timeResults.add((averageTime/noOfRep));
			sheetResults.add((averageSheets/noOfRep));
			averageTime = 0;
			averageSheets = 0;
		}
		noOfShapes = 10000;
		for(int i = 0; i < noOfTests; i++) {
			System.out.println("Shapes: " + noOfShapes +  "	|	Time (ms): " + (timeResults.get(i)/1000000) + ", Sheets: " + sheetResults.get(i));
			noOfShapes = noOfShapes + increment;
		}
		

	}
}
