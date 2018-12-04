// Author: Mason Powell
// Date: 12/12/2017
// Purpose: Allows me to demonstrate and test the functions of the Hotel, Room, and Bed classes.

public class HotelTest {
	
	public static void main(String[] args){
		Hotel hotel1 = new Hotel();
		
		// Setting the name and room amount, and using the generateRoom method to populate the roomList list
		hotel1.setName("Urban Sciences");
		hotel1.setRoomAmount(2);
		hotel1.generateRoom();
		
		// Setting the bed amount, populating the bedList list, setting the size of each bed in said list,
		// and setting the vacancy for room 1
		hotel1.getRoomList().get(0).setBedAmount(2);
		hotel1.getRoomList().get(0).generateBeds();
		hotel1.getRoomList().get(0).getBedList().get(0).setBed(BedType.SINGLE);
		hotel1.getRoomList().get(0).getBedList().get(1).setBed(BedType.DOUBLE);
		hotel1.getRoomList().get(0).setVacant(false);
		
		// Setting the bed amount, populating the bedList list, setting the size of each bed in said list,
		// and setting the vacancy for room 2
		hotel1.getRoomList().get(1).setBedAmount(1);
		hotel1.getRoomList().get(1).generateBeds();
		hotel1.getRoomList().get(1).getBedList().get(0).setBed(BedType.DOUBLE);
		hotel1.getRoomList().get(1).setVacant(true);
		
		// Tests the calculateMaxOccupancy method
		System.out.println("Occupancy: " + hotel1.calculateMaxOccupancy());
		
		// Tests the calculateVacancy method
		System.out.println("Vacant? " + hotel1.calculateVacancy());
	}
}
