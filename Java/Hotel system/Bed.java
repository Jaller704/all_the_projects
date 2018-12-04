// Author: Mason Powell
// Date: 10/12/2017
// Purpose: Holds all the variables for a bed and the method for returning its size as an integer 
//			instead of my enumerated type.

public class Bed {
	
	private BedType bed;
	
	public Bed(){
		
	}
	
	public BedType getBed() {
		return bed;
	}
	public void setBed(BedType bed) {
		this.bed = bed;
	}
	
	public int sizeValue(){
		/**
		 * Converts the size of the bed from BedType to int.
		 * 
		 * Returns:
		 * 1 if the bed is a SINGLE, 2 if the bed is a DOUBLE, and 0 if the bed has no size yet.
		 * Example Usage:
		 * Hotel myHotel = new Hotel();
		 * myHotel.setRoomAmount(1);
		 * myHotel.generateRoom();
		 * myHotel.getRoomList().get(0).setBedAmount(1);
		 * myHotel.getRoomList().get(0).generateBeds();
		 * myHotel.getRoomList().get(0).getBedList().get(0).setBed(BedType.DOUBLE);
		 * int maxOccupancy = myHotel.getRoomList().get(0).getBedList().get(0).sizeValue();
		 * 
		 * System.out.println(maxOccupancy);
		 */
		
		if(getBed() == BedType.SINGLE){
			return 1;
		}
		if(getBed() == BedType.DOUBLE){
			return 2;
		}
		return 0;
	}

}
