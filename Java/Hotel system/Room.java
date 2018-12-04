// Author: Mason Powell
// Date: 10/12/2017
// Purpose: Holds all the variables for a room and the method for populating it with beds.

import java.util.ArrayList;
import java.util.List;

public class Room extends Hotel{
	
	private int bedAmount;
	private List<Bed> bedList = new ArrayList<Bed>();
	private boolean isVacant;

	public Room() {
	}
	
	public int getBedAmount() {
		return bedAmount;
	}
	public void setBedAmount(int beds) {
		// Stops a room from having 0 or negative beds.
		if(beds < 1){
			throw new IllegalArgumentException("Please enter a positive value greater than 0");
		}
		this.bedAmount = beds;
	}
	
	public List<Bed> getBedList() {
		return bedList;
	}
	public void setBedList(List<Bed> bedList) {
		this.bedList = bedList;
	}

	public boolean isVacant() {
		return isVacant;
	}
	public void setVacant(boolean isVacant) {
		this.isVacant = isVacant;
	}

	public void generateBeds(){
		/**
		 * Populates the bedList list with beds based on bedAmount
		 * 
		 * Example Usage:
		 * Hotel myHotel = new Hotel();
		 * myHotel.getRoomList().get(0).setBedAmount(2);
		 * myHotel.getRoomList().get(0).generateBeds();
		 */
		
		for(int i = 0; i < getBedAmount(); i++){
			getBedList().add(new Bed());
		}
	}
}