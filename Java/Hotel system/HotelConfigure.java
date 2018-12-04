// Author: Mason Powell
// Date: 13/12/2017
// Purpose: Provides a simple user interface, which allows the user to choose a number of rooms, and for each room choose the number and size of beds.
//			Then displays this information back to the user.

import java.util.InputMismatchException;
import java.util.Scanner;

public class HotelConfigure {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Hotel hotel1 = new Hotel();
		boolean validInput = false;
		String bedInput;
		String vacantInput;
		BedType bed = null;
		
		System.out.println("Please enter the name for your new hotel: ");
		hotel1.setName(input.nextLine());
		
		// While loop that runs until the user has entered an integer, catches the exception thrown by input.nextInt() to print an easier to read error message.
		while(validInput == false){
			System.out.println("Please enter how many rooms you would like: ");
			try{
				// If the user enters anything other than an integer, nextInt() throws an InputMismatchException.
				hotel1.setRoomAmount(input.nextInt());
				hotel1.generateRoom();
				
				// If the try is a success the while ends and we can continue the program.
				validInput = true;
			}
			// Here the exception is caught instead of ending the program.
			catch (InputMismatchException ime){
				System.out.println("Invalid value entered, please enter an integer");
				// next() flushes the scanner to stop exception being constantly thrown.
				input.next();
			}
			// setRoomAmount() throws an exception if a number <1 is input.
			catch (IllegalArgumentException iae){
				System.out.println("Invalid value entered, please enter a value greater than 0");
			}
		}
		
		// Iterates through the amount of rooms.
		for(int i = 0; i < hotel1.getRoomAmount(); i++){
			validInput = false;
			
			// While loop that runs until the user has entered an integer, catches the exception thrown by input.nextInt() to print an easier to read error message.
			while(validInput == false){
				System.out.println("Please enter how many beds you would like in room " + (i+1) + ": ");
				try{
					// If the user enters anything other than an integer, nextInt() throws an InputMismatchException.
					hotel1.getRoomList().get(i).setBedAmount(input.nextInt());
					hotel1.getRoomList().get(i).generateBeds();
					
					// Iterates through the amount of beds.
					for(int j = 0; j < hotel1.getRoomList().get(i).getBedAmount(); j++){
						System.out.println("Please enter the size (SINGLE or DOUBLE) of bed " + (j+1) + ": ");
						// Lets the user enter the bed size as Single or single etc. and still be accepted.
						bedInput = input.next().toUpperCase();
						
						// Leftover from an attempt at a conversion from String to BedType.
						/*bed.stringTo(bedInput);
						hotel1.getRoomList().get(i).getBedList().get(j).setBed(bed);*/
						
						// Sets the bed size for bed j in room i or throws an exception based on what the user enters.
						if(bedInput.equals("SINGLE")){
							hotel1.getRoomList().get(i).getBedList().get(j).setBed(BedType.SINGLE);
						}
						else if(bedInput.equals("DOUBLE")){
							hotel1.getRoomList().get(i).getBedList().get(j).setBed(BedType.DOUBLE);
						}
						else{
							throw new IllegalArgumentException("Must be either SINGLE or DOUBLE");
						}
					}
					
					System.out.println("Is this room vacant? (Y/N)");
					// Lets the user enter the vacancy as y or n and still be accepted.
					vacantInput = input.next().toUpperCase();
					
					// Sets the vacancy for room i or throws and exception based on what the user enters.
					if(vacantInput.equals("Y")){
						hotel1.getRoomList().get(i).setVacant(true);
					}
					else if(vacantInput.equals("N")){
						hotel1.getRoomList().get(i).setVacant(false);
					}
					else{
						throw new IllegalArgumentException("Must be either Y or N");
					}
					// If the try is a success the while ends and this room has all data entered.
					validInput = true;
				}
				// Here the exception is caught instead of ending the program.
				catch (InputMismatchException ime){
					System.out.println("Invalid value entered, please enter an integer");
					// next() flushes the scanner to stop exception being constantly thrown.
					input.next();
				}
				// If the user enters anything other than SINGLE/DOUBLE or Y/N an IllegalArgumentException is thrown, since this exception is valid for both cases.
				// There is most likely a better way to handle this.
				catch (IllegalArgumentException iae){
					System.out.println("Invalid value entered");
				}
			}
		}
		// Builds the report for hotel1
		HotelReport.buildReport(hotel1);
	}
}
