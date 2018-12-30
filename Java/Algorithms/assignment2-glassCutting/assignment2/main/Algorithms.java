package main;

/**
 * @Purpose: The Algorithms class contains the two algorithms you have to implement  
 * Do NOT modify the names and signatures of the two algorithm methods
 * 
 * @author  RYK
 * @since   30/10/2018
 * extended by @author 
 *
 **/

import java.util.ArrayList;
import java.util.List;

public class Algorithms {

	/**
	 * This method is used to implement the next fit algorithm
	 * 
	 * @param shapes:a list of shapes representing customer requests(shapes to be
	 *            cut/placed)
	 * @return a list of the sheets used to fulfil all customer requests (i.e.
	 *         place all the shapes). e.g. if you pass a "shapes" list that has
	 *         two shapes {(w=200,h=200),(w=50,h=100)}, then the returned list
	 *         of sheets should show us all the information needed (e.g. that
	 *         one sheet is used, this sheet has one shelf and this shelf has
	 *         two shapes in it). In the test program, you can use the returned
	 *         list to retrieve the total number of sheets used.
	 **/

	public List<Sheet> nextFit(List<Shape> shapes) {

		List<Sheet> usedSheets = new ArrayList<Sheet>();
		
		usedSheets.add(new Sheet());
		Sheet currentSheet = usedSheets.get(0);
		Shelf currentShelf = new Shelf();
		Shape currentShape;
		
		int i = 0;
		
		boolean shelfAdded = false;
		
		while(i < shapes.size()) {
			currentShape = shapes.get(i);
			if(currentShelf.getHeight() == 0) {
				// if shelf is empty the shape can just be placed
				currentShelf.place(currentShape);
			} else if(currentShape.getHeight() <= currentShelf.getHeight()
					  && currentShape.getWidth() <= (currentSheet.getWidth() - currentShelf.getWidth()) && currentSheet.shapeAmount() < 20) {
				// if the height of the shape will fit and the width won't overrun, place the shape
				currentShelf.place(currentShape);
			} else {
				// shape won't fit on the current shelf so update current shelf and place it
				currentShelf = new Shelf();
				currentShelf.place(currentShape);
				shelfAdded = false;
			}
			
			if(shelfAdded == false) {
				if(currentShelf.getHeight() <= currentSheet.remainingHeight() && currentSheet.shapeAmount() < 20) {
					// if the shelf will fit on the sheet and the sheet isn't filled with shapes, add the shelf
					currentSheet.addShelf(currentShelf);
					shelfAdded = true;
				} else {
					// otherwise create a new sheet and place the shelf on there
					currentSheet = new Sheet();
					currentSheet.addShelf(currentShelf);
					usedSheets.add(currentSheet);
					shelfAdded = true;
				}
			}
			i++;
		}
		return usedSheets;
	}

	/**
	 * This method is used to implement the first fit algorithm
	 * 
	 * @param shapes:a list of shapes representing customer requests (shapes to be
	 *            cut/placed)
	 * @return a list of the sheets used to fulfil all customer requests (i.e. place
	 *         all the shapes). e.g. if you pass a "shapes" list that has two
	 *         shapes {(w=200,h=200),(w=50,h=100)}, then the returned list of
	 *         sheets should show us all the information needed (e.g. that one
	 *         sheet is used, this sheet has one shelf and this shelf has two
	 *         shapes in it). In the test program, you can use the returned list
	 *         to retrieve the total number of sheets used
	 **/
	public List<Sheet> firstFit(List<Shape> shapes) {
		
		List<Sheet> usedSheets = new ArrayList<Sheet>();
		List<Shelf> usedShelves = new ArrayList<Shelf>();
		
		usedSheets.add(new Sheet());
		usedShelves.add(new Shelf());
		Shelf currentShelf = usedShelves.get(0);
		Sheet currentSheet = usedSheets.get(0);
		Shape currentShape;
		
		
		boolean shelfAdded = false;
		boolean shapeAdded = false;
		
		int i = 0;
		while(i < shapes.size()) {
			currentShape = shapes.get(i);
			shapeAdded = false;
			
			int j = 0;
			while( shapeAdded == false && j < usedShelves.size()){
				currentShelf = usedShelves.get(j);
				if(currentShelf.getHeight() == 0) {
					// if shelf is empty the shape can just be placed
					currentShelf.place(currentShape);
					shapeAdded = true;
				} else if(currentShape.getHeight() <= currentShelf.getHeight()
						  && currentShape.getWidth() <= (currentSheet.getWidth() - currentShelf.getWidth()) && currentSheet.shapeAmount() < 20) {
					// if the height of the shape will fit and the width won't overrun the remaining width, place the shape
					currentShelf.place(currentShape);
					shapeAdded = true;
				} 
				
				j++;
			}
			if(!shapeAdded){
				// shape won't fit on the current shelf so update current shelf and place it
				currentShelf = new Shelf();
				currentShelf.place(currentShape);
				shapeAdded = true;
				usedShelves.add(currentShelf);
				shelfAdded = false;
			}
			
			int k = 0;
			while(k < usedSheets.size()){
				currentSheet = usedSheets.get(k);
				if(shelfAdded == false) {
					if(currentShelf.getHeight() <= currentSheet.remainingHeight() && currentSheet.shapeAmount() < 20) {
						// if the shelf will fit on the sheet and the sheet isn't filled with shapes, add the shelf
						currentSheet.addShelf(currentShelf);
						shelfAdded = true;
					} 
				}
				if(shelfAdded == false && k > (usedSheets.size() -2)) {
					// otherwise create a new sheet and place the shelf on there
					currentSheet = new Sheet();
					currentSheet.addShelf(currentShelf);
					usedSheets.add(currentSheet);
					shelfAdded = true;
				}
				k++;
			}
			i++;
		}
		return usedSheets;
	}
}
