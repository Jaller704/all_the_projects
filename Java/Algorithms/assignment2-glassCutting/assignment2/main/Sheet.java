package main;

/**
 * @Purpose: The Sheet class represents a sheet with a list of shelves.
 * DO NOT MODIFY THE EXISTING METHODS SIGNITURE, You may add additional methods if you wish
 * 
 * @author  RYK 
 * @since   30/10/2018
 * extended by @author 
 */
import java.util.ArrayList;
import java.util.List;

public class Sheet {

	public static final int SHEET_HEIGHT = 250; // sheet height

	public static final int SHEET_WIDTH = 300; // sheet width

	public static final int SHAPE_LIMIT = 20; // maximum number of shapes that can be placed in each sheet

	private List<Shelf> shelves = new ArrayList<Shelf>(); // list of shelves

	/**
	 * empty constructor
	 */
	public Sheet() {
	}

	/**
	 * This method is used to add a shelf to a sheet
	 * @param a shelf
	 */
	public void addShelf(Shelf shelf) {
		shelves.add(shelf);
	}

	/**
	 * @return height of all shelves in a sheet
	 */
	public int allShelvesHeight() {

		int total = 0;

		for (Shelf shelf : this.shelves) {

			// has a shelf with at least 1 shape
			if (!shelf.getShapes().isEmpty()) {

				// add all shelf height to total
				total += shelf.getHeight();
			}
		}
		return total;
	}

	/**
	 * @return list of all shelves in a sheet
	 */
	public List<Shelf> getShelves() {
		return this.shelves;
	}

	/**
	 * @return height
	 */
	public int getHeight() {
		return SHEET_HEIGHT;
	}

	/**
	 * @return width
	 */
	public int getWidth() {
		return SHEET_WIDTH ;
	}
	
	public int shapeAmount() {
		// make a running total of all shapes on all shelves
		int shapeCount = 0;
		for(int i = 0; i < getShelves().size(); i++) {
			shapeCount = shapeCount + getShelves().get(i).getShapes().size();
		}
		return shapeCount;
	}
	
	public int remainingHeight() {
		return getHeight()-allShelvesHeight();
	}
	
	public String toString() {
		return "HEIGHT: " + getHeight() + ", WIDTH: " + getWidth()  + ", SHELF AMOUNT: " + getShelves().size() +
				", SHAPE AMOUNT: " + shapeAmount() + ", REMAINING HEIGHT: " + remainingHeight();
	}

}
