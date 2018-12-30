package test;

import java.util.ArrayList;
import java.util.List;

import main.*;

/**
 * @Purpose: The CorrectnessTest class is used to validate the correctness of
 *           the implemented algorithms You can add additional methods if you
 *           need
 * 
 * @author RYK
 * @since 30/10/2018 extended by @author
 */

public class CorrectnessTest {
	public static void main(String[] args) {
		System.out.println("*********************************************");
		System.out.println("*********** Correctness testing *************");
		System.out.println("*********************************************");
		System.out.println();

		/*
		 * Here you will need to validate that your algorithms (nextFit() and
		 * firstFit()) behave as expected on small data sets. 
		 * Think about normal cases and border cases. 
		 * You can use any additional method you created in this class
		 */
		
		
		List<Shape> data =  new ArrayList<Shape>();
		Algorithms a = new Algorithms();
		List<Sheet> result;
		
		System.out.println("------------------ SINGLE SHAPE TEST ------------------");
		
		/* Test that just putting a singular shape in 
		 * actually makes a shelf of the correct height 
		 * and places it along with placing that shelf 
		 * on the sheet
		 */
		
		data.add(new Shape(1,1));
		
		System.out.println("Test data: " + data + "\n");
		
		result = a.nextFit(data);
		
		System.out.print("resulting shelves (nextFit): ");
		for(int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).getShelves());
		}
		System.out.println("resulting sheets (nextFit): " + result);
		
		if(result.size() == 1) {
			if(result.get(0).getShelves().size() == 1){
				if(result.get(0).getShelves().get(0).getShapes().size() == 1){
					System.out.println("Passed\n");
				} else {
					System.out.println("Failed\n");
				}
			} else {
				System.out.println("Failed\n");
			}
		} else {
			System.out.println("Failed\n");
		}
		
		
		
		
		result = a.firstFit(data);
		
		System.out.print("resulting shelves (firstFit): ");
		for(int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).getShelves());
		}
		System.out.println("resulting sheets (firstFit): " + result);
		
		if(result.size() == 1) {
			if(result.get(0).getShelves().size() == 1){
				if(result.get(0).getShelves().get(0).getShapes().size() == 1){
					System.out.println("Passed\n");
				} else {
					System.out.println("Failed\n");
				}
			} else {
				System.out.println("Failed\n");
			}
		} else {
			System.out.println("Failed\n");
		}
		
		
		System.out.println("------------------ MULTIPLE SHAPES TEST ------------------");
		
		/* Test that placing multiple shapes that all fit 
		 * on one shelf works and that the shelf is placed 
		 * on a sheet
		 */
		
		data = new ArrayList<Shape>();
		
		data.add(new Shape(1,1));
		data.add(new Shape(1,1));
		
		System.out.println("Test data: " + data + "\n");
		
		result = a.nextFit(data);
		
		System.out.print("resulting shelves (nextFit): ");
		for(int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).getShelves());
		}
		
		System.out.println("resulting sheets (nextFit): " + result);
		if(result.size() == 1) {
			if(result.get(0).getShelves().size() == 1){
				if(result.get(0).getShelves().get(0).getShapes().size() == 2){
					System.out.println("Passed\n");
				} else {
					System.out.println("Failed\n");
				}
			} else {
				System.out.println("Failed\n");
			}
		} else {
			System.out.println("Failed\n");
		}
		
		result = a.firstFit(data);
		
		System.out.print("resulting shelves (firstFit): ");
		for(int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).getShelves());
		}
		System.out.println("resulting sheets (firstFit): " + result);
		
		if(result.size() == 1) {
			if(result.get(0).getShelves().size() == 1){
				if(result.get(0).getShelves().get(0).getShapes().size() == 2){
					System.out.println("Passed\n");
				} else {
					System.out.println("Failed\n");
				}
			} else {
				System.out.println("Failed\n");
			}
		} else {
			System.out.println("Failed\n");
		}
		
		
		
		System.out.println("------------------ MULTIPLE SHAPES ON MULTIPLE SHELVES TEST (HEIGHT) ------------------");
		
		/* one shape should go on the first shelf then the next
		 * shape should be too tall to fit on the first shelf and
		 * as such should be placed on a new shelf above the first
		 * but still on the same sheet
		 */
		
		data = new ArrayList<Shape>();
		
		data.add(new Shape(1,1));
		data.add(new Shape(1,2));
		
		System.out.println("Test data: " + data + "\n");
		
		result = a.nextFit(data);
		
		System.out.print("resulting shelves (nextFit): ");
		for(int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).getShelves());
		}
		
		System.out.println("resulting sheets (nextFit): " + result);
		
		if(result.size() == 1) {
			if(result.get(0).getShelves().size() == 2){
				if(result.get(0).getShelves().get(0).getShapes().size() == 1 && result.get(0).getShelves().get(1).getShapes().size() == 1){
					System.out.println("Passed\n");
				} else {
					System.out.println("Failed\n");
				}
			} else {
				System.out.println("Failed\n");
			}
		} else {
			System.out.println("Failed\n");
		}
		
		result = a.firstFit(data);
		
		System.out.print("resulting shelves (firstFit): ");
		for(int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).getShelves());
		}
		System.out.println("resulting sheets (firstFit): " + result);
		
		if(result.size() == 1) {
			if(result.get(0).getShelves().size() == 2){
				if(result.get(0).getShelves().get(0).getShapes().size() == 1 && result.get(0).getShelves().get(1).getShapes().size() == 1){
					System.out.println("Passed\n");
				} else {
					System.out.println("Failed\n");
				}
			} else {
				System.out.println("Failed\n");
			}
		} else {
			System.out.println("Failed\n");
		}
		
		System.out.println("------------------ MULTIPLE SHAPES ON MULTIPLE SHELVES TEST (WIDTH) ------------------");
		
		/* one shape should go on the first shelf then the next
		 * shape should be too wide to fit on the first shelf and
		 * as such should be placed on a new shelf above the first
		 * but still on the same sheet
		 */
		
		data = new ArrayList<Shape>();
		
		data.add(new Shape(1,1));
		data.add(new Shape(300,1));
		
		System.out.println("Test data: " + data + "\n");
		
		result = a.nextFit(data);
		
		System.out.print("resulting shelves (nextFit): ");
		for(int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).getShelves());
		}
		
		System.out.println("resulting sheets (nextFit): " + result);
		
		if(result.size() == 1) {
			if(result.get(0).getShelves().size() == 2){
				if(result.get(0).getShelves().get(0).getShapes().size() == 1 && result.get(0).getShelves().get(1).getShapes().size() == 1){
					System.out.println("Passed\n");
				} else {
					System.out.println("Failed\n");
				}
			} else {
				System.out.println("Failed\n");
			}
		} else {
			System.out.println("Failed\n");
		}
		
		result = a.firstFit(data);
		
		System.out.print("resulting shelves (firstFit): ");
		for(int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).getShelves());
		}
		System.out.println("resulting sheets (firstFit): " + result);
		
		if(result.size() == 1) {
			if(result.get(0).getShelves().size() == 2){
				if(result.get(0).getShelves().get(0).getShapes().size() == 1 && result.get(0).getShelves().get(1).getShapes().size() == 1){
					System.out.println("Passed\n");
				} else {
					System.out.println("Failed\n");
				}
			} else {
				System.out.println("Failed\n");
			}
		} else {
			System.out.println("Failed\n");
		}
		
		System.out.println("------------------ NEW SHEET ON LIMIT REACHED TEST ------------------");
		
		/* if there are 20 shapes added to a sheet, regardless
		 * of the the shelves they are on, no more shapes can 
		 * be added and a new sheet must be created
		 */
		
		data = new ArrayList<Shape>();

		for(int i = 0; i < 21; i++){
			data.add(new Shape(1,1));
		}
		
		System.out.println("Test data: " + data + "\n");
		
		result = a.nextFit(data);
		
		System.out.print("resulting shelves (nextFit): ");
		for(int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).getShelves());
		}
		
		System.out.println("resulting sheets (nextFit): " + result);
		
		if(result.size() == 2) {
			if(result.get(0).getShelves().size() == 1 && result.get(1).getShelves().size() == 1){
				if(result.get(0).getShelves().get(0).getShapes().size() == 20 && result.get(1).getShelves().get(0).getShapes().size() == 1){
					System.out.println("Passed\n");
				} else {
					System.out.println("Failed\n");
				}
			} else {
				System.out.println("Failed\n");
			}
		} else {
			System.out.println("Failed\n");
		}
		
		result = a.firstFit(data);
		
		System.out.print("resulting shelves (firstFit): ");
		for(int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).getShelves());
		}
		System.out.println("resulting sheets (firstFit): " + result);
		
		if(result.size() == 2) {
			if(result.get(0).getShelves().size() == 1 && result.get(1).getShelves().size() == 1){
				if(result.get(0).getShelves().get(0).getShapes().size() == 20 && result.get(1).getShelves().get(0).getShapes().size() == 1){
					System.out.println("Passed\n");
				} else {
					System.out.println("Failed\n");
				}
			} else {
				System.out.println("Failed\n");
			}
		} else {
			System.out.println("Failed\n");
		}
		
		System.out.println("------------------ FIRST FIT WORKS AS EXPECTED (SHELVES) ------------------");
		
		/* Test that firstFit actually goes back and
		 * places a shape in a shelf where there is space
		 * instead of just putting it in the next shelf
		 * aka nextFit
		 */
		
		data = new ArrayList<Shape>();
		
		data.add(new Shape(100,100));
		data.add(new Shape(50,50));
		data.add(new Shape(50,150));
		data.add(new Shape(50,50));
		data.add(new Shape(250,50));
		
		System.out.println("Test data: " + data + "\n");	// data should all fit on one sheet through first fit
		
		result = a.firstFit(data);
		List<Sheet> incorrectResult = a.nextFit(data);	// produces two sheets
		
		System.out.print("resulting shelves (firstFit): ");
		for(int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).getShelves());
		}
				
		System.out.println("resulting sheets (firstFit): " + result);
	
		if(result.size() != incorrectResult.size()) {
			if(result.get(0).getShelves().size() == 2) {
				if(result.get(0).getShelves().get(0).getShapes().size() == 3 && result.get(0).getShelves().get(1).getShapes().size() == 2){
					System.out.println("Passed\n");
				} else {
					System.out.println("Failed\n");
				}
			} else {
				System.out.println("Failed\n");
			}
		} else {
			System.out.println("Failed\n");
		}	
		
		System.out.println("------------------ FIRST FIT WORKS AS EXPECTED (SHEETS) ------------------");
		
		/* Test that firstFit actually goes back and
		 * places a shape in a shelf on a previous sheet 
		 * where there is space instead of just putting
		 * it in the next shelf or sheet aka nextFit
		 */
		
		data = new ArrayList<Shape>();
		
		data.add(new Shape(100,100));
		data.add(new Shape(50,50));
		data.add(new Shape(50,130));
		data.add(new Shape(50,50));
		data.add(new Shape(250,50));
		data.add(new Shape(150,150));
		data.add(new Shape(50,20));
		
		System.out.println("Test data: " + data + "\n");
		
		result = a.firstFit(data);	// should produce two sheets but only one shelf on the second sheet
		incorrectResult = a.nextFit(data);	// should produce two sheets and another shelf on the second sheet
		
		System.out.print("resulting shelves (firstFit): ");
		for(int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).getShelves());
		}
				
		System.out.println("resulting sheets (firstFit): " + result);
	
		if(result.size() == 2) {
			if(result.get(0).getShelves().size() == 2 && result.get(1).getShelves().size() != incorrectResult.get(1).getShelves().size()) {
				if(result.get(0).getShelves().get(0).getShapes().size() == 4 && result.get(0).getShelves().get(1).getShapes().size() == 2){
					System.out.println("Passed\n");
				} else {
					System.out.println("Failed\n");
				}
			} else {
				System.out.println("Failed\n");
			}
		} else {
			System.out.println("Failed\n");
		}	
		
	}
		
	
	
	
	
	
}
