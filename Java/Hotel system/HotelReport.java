// Author: Mason Powell
// Date: 11/12/2017
// Purpose: Builds a report using the information in a given Hotel object.

public class HotelReport {
	
	public static void buildReport(Hotel inputHotel){
		/**
		 * Builds a report based on a given Hotel object.
		 * 
		 * Parameters:
		 * inputHotel -- A given Hotel object, should have already been filled with rooms, beds, and other information.
		 * 
		 * Example Usage:
		 * Hotel myHotel = new Hotel;
		 * // build the hotel
		 * HotelReport.buildReport(myHotel);
		 */
		
		System.out.println("Hotel Report");
		System.out.println("Name: " + inputHotel.getName());
		System.out.println("Room amount: " + inputHotel.getRoomAmount() + " room(s)");
		// Iterates through each room to print its amount of beds and each beds size.
		for(int i = 0; i < inputHotel.getRoomAmount(); i++){
			System.out.println("Room " + (i+1) + " bed amount: " + inputHotel.getRoomList().get(i).getBedAmount() + " bed(s)");
			System.out.println("Room " + (i+1) + " bed(s): ");
			// Iterates through the beds in a room to print out their size.
			for(int j = 0; j < inputHotel.getRoomList().get(i).getBedAmount(); j++){
				System.out.println(inputHotel.getRoomList().get(i).getBedList().get(j).getBed());
			}
		}
		
		System.out.println("Max Occupancy: " + inputHotel.calculateMaxOccupancy());
		System.out.println("Has Vacancies? " + inputHotel.calculateVacancy());
	}
}
