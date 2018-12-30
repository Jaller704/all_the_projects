package test;

import java.util.*;

import main.*;

/**
 * @Purpose: The SortedTest class is used to compare the implemented algorithms
 *           in term of the number of sheets used WHEN the list of
 *           shapes is SORTED
 *
 *           You can add additional methods if you need to in this class
 * 
 * @author RYK
 * @since 30/10/2018
 * extended by @author
 */

public class SortedTest {
	public static void main(String[] args) {
		System.out.println("*********************************************");
		System.out.println("**************** Sorted Test ****************");
		System.out.println("*********************************************");
		System.out.println();

		/*
		 * Generate a random shape list and then check the number of sheets used
		 * when
		 ** this shape list is passed un-sorted
		 ** the list is sorted in ascending surface size
		 ** the list is sorted in descending surface size
		 * 
		 * run several tests for different sizes of the "list of shapes" 
		 */
		
		Generator g = new Generator();
		List<Shape> data = new ArrayList<Shape>();
		List<List<Shape>> datasets = new ArrayList<List<Shape>>();
		List<Shape> dataAscendSort = new ArrayList<Shape>();
		List<Shape> dataDescendSort = new ArrayList<Shape>();
		List<Integer> averages = new ArrayList<Integer>();
		
		datasets.add(g.generateShapeList(10));
		dataAscendSort = new ArrayList<Shape>(datasets.get(0));
		dataDescendSort = new ArrayList<Shape>(datasets.get(0));
		Collections.sort(dataAscendSort);
		Collections.sort(dataDescendSort, Collections.reverseOrder());
		datasets.add(dataAscendSort);
		datasets.add(dataDescendSort);
		datasets.add(g.generateShapeList(100));
		dataAscendSort = new ArrayList<Shape>(datasets.get(3));
		dataDescendSort = new ArrayList<Shape>(datasets.get(3));
		Collections.sort(dataAscendSort);
		Collections.sort(dataDescendSort, Collections.reverseOrder());
		datasets.add(dataAscendSort);
		datasets.add(dataDescendSort);
		datasets.add(g.generateShapeList(1000));
		dataAscendSort = new ArrayList<Shape>(datasets.get(6));
		dataDescendSort = new ArrayList<Shape>(datasets.get(6));
		Collections.sort(dataAscendSort);
		Collections.sort(dataDescendSort, Collections.reverseOrder());
		datasets.add(dataAscendSort);
		datasets.add(dataDescendSort);
		datasets.add(g.generateShapeList(10000));
		dataAscendSort = new ArrayList<Shape>(datasets.get(9));
		dataDescendSort = new ArrayList<Shape>(datasets.get(9));
		Collections.sort(dataAscendSort);
		Collections.sort(dataDescendSort, Collections.reverseOrder());
		datasets.add(dataAscendSort);
		datasets.add(dataDescendSort);
		datasets.add(g.generateShapeList(50000));
		dataAscendSort = new ArrayList<Shape>(datasets.get(12));
		dataDescendSort = new ArrayList<Shape>(datasets.get(12));
		Collections.sort(dataAscendSort);
		Collections.sort(dataDescendSort, Collections.reverseOrder());
		datasets.add(dataAscendSort);
		datasets.add(dataDescendSort);

		
		

		int noOfReps = 4;

		for(int i = 0; i <= 12; i = i+3){
			averages.add(generateAverages(datasets.get(i), noOfReps, 1));
		}
		
		
		System.out.println("UNSORTED DATA RESULTS");
		System.out.println("___________________________________________________________");
		System.out.println("Algorithm & data set	|	Average sheets used");
		System.out.println("___________________________________________________________");
		System.out.println("nextFit (10 shapes)	| 		" + (averages.get(0)));
		System.out.println("nextFit (100 shapes)	| 		" + (averages.get(1)));
		System.out.println("nextFit (1000 shapes)	| 		" + (averages.get(2)));
		System.out.println("nextFit (10000 shapes)	| 		" + (averages.get(3)));
		System.out.println("nextFit (50000 shapes)	| 		" + (averages.get(4)));
		System.out.println("___________________________________________________________");
		
		
		averages = new ArrayList<Integer>();
		for(int i = 0; i <= 12; i = i+3){
			averages.add(generateAverages(datasets.get(i), noOfReps, 2));
		}
		
		System.out.println("firstFit (10 shapes)	| 		" + (averages.get(0)));
		System.out.println("firstFit (100 shapes)	| 		" + (averages.get(1)));
		System.out.println("firstFit (1000 shapes)	| 		" + (averages.get(2)));
		System.out.println("firstFit (10000 shapes)	| 		" + (averages.get(3)));
		System.out.println("firstFit (50000 shapes)	| 		" + (averages.get(4)));
		System.out.println("___________________________________________________________");
		
		averages = new ArrayList<Integer>();
		for(int i = 1; i <= 13; i = i+3){
			averages.add(generateAverages(datasets.get(i), noOfReps, 1));
		}
		
		System.out.println("SORTED DATA (ASCENDING) RESULTS");
		System.out.println("___________________________________________________________");
		System.out.println("Algorithm & data set	|	Average sheets used");
		System.out.println("___________________________________________________________");
		System.out.println("nextFit (10 shapes)	| 		" + (averages.get(0)));
		System.out.println("nextFit (100 shapes)	| 		" + (averages.get(1)));
		System.out.println("nextFit (1000 shapes)	| 		" + (averages.get(2)));
		System.out.println("nextFit (10000 shapes)	| 		" + (averages.get(3)));
		System.out.println("nextFit (50000 shapes)	| 		" + (averages.get(4)));
		System.out.println("___________________________________________________________");
		
		averages = new ArrayList<Integer>();
		for(int i = 1; i <= 13; i = i+3){
			averages.add(generateAverages(datasets.get(i), noOfReps, 2));
		}

		System.out.println("firstFit (10 shapes)	| 		" + (averages.get(0)));
		System.out.println("firstFit (100 shapes)	| 		" + (averages.get(1)));
		System.out.println("firstFit (1000 shapes)	| 		" + (averages.get(2)));
		System.out.println("firstFit (10000 shapes)	| 		" + (averages.get(3)));
		System.out.println("firstFit (50000 shapes)	| 		" + (averages.get(4)));
		System.out.println("___________________________________________________________");
		

		averages = new ArrayList<Integer>();
		for(int i = 2; i <= 14; i = i+3){
			averages.add(generateAverages(datasets.get(i), noOfReps, 1));
		}
		
		System.out.println("SORTED DATA (DESCENDING) RESULTS");
		System.out.println("___________________________________________________________");
		System.out.println("Algorithm & data set	|	Average sheets used");
		System.out.println("___________________________________________________________");
		System.out.println("nextFit (10 shapes)	| 		" + (averages.get(0)));
		System.out.println("nextFit (100 shapes)	| 		" + (averages.get(1)));
		System.out.println("nextFit (1000 shapes)	| 		" + (averages.get(2)));
		System.out.println("nextFit (10000 shapes)	| 		" + (averages.get(3)));
		System.out.println("nextFit (50000 shapes)	| 		" + (averages.get(4)));
		System.out.println("___________________________________________________________");
		
		averages = new ArrayList<Integer>();
		for(int i = 2; i <= 14; i = i+3){
			averages.add(generateAverages(datasets.get(i), noOfReps, 2));
		}

		System.out.println("firstFit (10 shapes)	| 		" + (averages.get(0)));
		System.out.println("firstFit (100 shapes)	| 		" + (averages.get(1)));
		System.out.println("firstFit (1000 shapes)	| 		" + (averages.get(2)));
		System.out.println("firstFit (10000 shapes)	| 		" + (averages.get(3)));
		System.out.println("firstFit (50000 shapes)	| 		" + (averages.get(4)));
		System.out.println("___________________________________________________________");
						

	}
	
	private static int generateAverages(List<Shape> data, int noOfReps, int testAlgorithm){
		Algorithms a = new Algorithms();
		List<Sheet> result = new ArrayList<Sheet>();
		int sheetAverage = 0;
		int average = 0;
		if(testAlgorithm == 1){
			for(int j = 0; j < noOfReps; j++) {
				result = a.nextFit(data);
				sheetAverage = sheetAverage + result.size();
			}
			average = sheetAverage/noOfReps;
		}else if(testAlgorithm == 2){
			for(int j = 0; j < noOfReps; j++) {
				result = a.firstFit(data);
				sheetAverage = sheetAverage + result.size();
			}
			average = sheetAverage/noOfReps;
		}
		
		return average;
	}

}
