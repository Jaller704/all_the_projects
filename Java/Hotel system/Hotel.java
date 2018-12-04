// Author: Mason Powell
// Date: 10/12/2017
// Purpose: Holds all the variables for a hotel and the methods for populating it with rooms, 
//			checking its vacancy, and calculating its max occupancy.

import java.util.ArrayList;
import java.util.List;

public class Hotel {
	
	private String name;
	private int roomAmount;
	private int customerAmount;
	private List<Room> roomList = new ArrayList<Room>();
	private BedType bed;
	private int maxOccupancy;
	private boolean hasVacancies = false;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getRoomAmount() {
		return roomAmount;
	}
	public void setRoomAmount(int roomAmount) {
		// Stops a hotel from having 0 or negative rooms.
		if(roomAmount < 1){
			throw new IllegalArgumentException("Please enter a positive value greater than 0");
		}
		this.roomAmount = roomAmount;
	}

	public int getCustomerAmount() {
		return customerAmount;
	}
	public void setCustomerAmount(int customerAmount) {
		this.customerAmount = customerAmount;
	}

	public List<Room> getRoomList() {
		return roomList;
	}
	public void setRoomList(List<Room> roomList) {
		this.roomList = roomList;
	}
	
	public BedType getBed() {
		return bed;
	}
	public void setBed(BedType bed) {
		this.bed = bed;
	}

	public int getMaxOccupancy() {
		return maxOccupancy;
	}
	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}

	public boolean isHasVacancies() {
		return hasVacancies;
	}
	public void setHasVacancies(boolean hasVacancies) {
		this.hasVacancies = hasVacancies;
	}
	
	public void generateRoom(){
		/**
		 * Populates the roomList list with rooms based on roomAmount
		 * 
		 * Example Usage:
		 * Hotel myHotel = new Hotel();
		 * myHotel.setRoomAmount(2);
		 * myHotel.generateRoom();
		 */
		
		for(int i = 0; i < getRoomAmount(); i++) {
			getRoomList().add(new Room());
		}
		
	}
		
	public int calculateMaxOccupancy(){
		/**
		 * Calculates the max amount of people based on the amount of beds and their size.
		 * 
		 * Returns:
		 * The maxOccupancy variable which equals the size of each bed added together.
		 * Example Usage:
		 * Hotel myHotel = new Hotel();
		 * myHotel.setRoomAmount(2);
		 * myHotel.generateRoom();
		 * myHotel.getRoomList().get(0).setBedAmount(2);
		 * myHotel.getRoomList().get(0).generateBeds();
		 * myHotel.getRoomList().get(0).getBedList().get(0).setBed(BedType.SINGLE);
		 * myHotel.getRoomList().get(0).getBedList().get(1).setBed(BedType.DOUBLE);
		 * 
		 * System.out.println(myHotel.calculateMaxOccupancy());
		 */
		
		int total = 0;
		
		for(int i = 0; i < getRoomAmount(); i++)
			for(int j = 0; j < getRoomList().get(i).getBedAmount(); j++){
				total = total + getRoomList().get(i).getBedList().get(j).sizeValue();
		}
		setMaxOccupancy(total);
		return getMaxOccupancy();
	}
	
	public boolean calculateVacancy(){
		/**
		 * Checks each room for its vacancy, once a witness value is found hasVacancies is set to true.
		 * 
		 * Returns:
		 * hasVacancies either set to true if a witness is found or unchanged (false) if no witness is found.
		 * Example Usage:
		 * Hotel myHotel = new Hotel();
		 * myHotel.setRoomAmount(2);
		 * myHotel.generateRoom();
		 * myHotel.getRoomList().get(0).setVacant(true);
		 * myHotel.getRoomList().get(1).setVacant(false);
		 * 
		 * System.out.println(myHotel.calculateVacancy());
		 */
		
		for(int i = 0; i < getRoomAmount(); i++) {
			if(getRoomList().get(i).isVacant() == true){
				setHasVacancies(true);
				return isHasVacancies();
			}
		}
		return isHasVacancies();
	}
	
}
