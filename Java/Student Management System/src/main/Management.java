// Author: Mason Powell
// Date: 08/05/2018
// Purpose: System for managing Students and their data as well as SmartCards

package main;

import java.util.*;

public class Management {

	private static Map<ID,Student> registeredStudents = new HashMap<ID,Student>();
	private static int noOfUGStudents = 0;
	private static int noOfPGTStudents = 0;
	private static int noOfPGRStudents = 0;
	
	public static int noOfStudents(String typeOfStudent) {
		/**
		 * Returns the number of registered students of a certain concrete implementation
		 * 
		 * Parameters:
		 * typeOfStudent -- a String containing either "undergraduate", "postgraduate taught", or "postgraduate research" to decide which int to return
		 * Returns:
		 * An integer of the amount of undergrad, postgrad taught or postgrad research dependent on typeOfStudent
		 * Example Usage:
		 * Management.getNoOfStudents("undergraduate");
		 */
		
		if (typeOfStudent.equalsIgnoreCase("undergraduate")) {
			return noOfUGStudents;
		} else if (typeOfStudent.equalsIgnoreCase("postgraduate taught")) {
			return noOfPGTStudents;
		} else if(typeOfStudent.equalsIgnoreCase("postgraduate research")) {
			return noOfPGRStudents;
		} else {
			throw new IllegalArgumentException("3 options: undergraduate, postgraduate taught, or postgraduate research");
		}
	}
	
	public static void registerStudent(Student stu) throws RegistrationException{
		/**
		 * Registers a student by placing them in the registeredStudents map, navigated by ID, throws a RegistrationException if the student is already registered
		 * 
		 * Parameters:
		 * stu -- A Student object to be registered
		 * Example Usage:
		 * Calendar dateOfBirth = Calendar.getInstance();
		 * dateOfBirth.set(1998, 2, 4);
		 * Student undergrad = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), new Name("Mason", "Powell"), dateOfBirth)
		 * Management.registerStudent(undergrad);
		 */
		
		ID k = stu.getPersonalID();
		if(!getRegisteredStudents().containsKey(k)){
			if(stu.getClass() == UndergradStudent.class){
				noOfUGStudents = noOfUGStudents + 1;
			} else if(stu.getClass() == PostgradStudent.class){
				noOfPGTStudents = noOfPGTStudents + 1;
			} else if(stu.getClass() == PostgradResearchStudent.class){
				noOfPGRStudents = noOfPGRStudents + 1;
			}
			getRegisteredStudents().put(k, stu);
		} else if(getRegisteredStudents().containsKey(k)){
			throw new RegistrationException("That student is already registered!");
		}
	}
	
	public static void amendStudentData(String studentID, Name studentData) throws RegistrationException{
		/**
		 * Amends the students personalName data, throws a RegistrationException if the student isn't registered
		 * 
		 * Parameters:
		 * studentID --  A String containing the ID of the student whose data is to be amended
		 * studentData -- A Name object containing the new name for the student
		 * Example Usage:
		 * Calendar dateOfBirth = Calendar.getInstance();
		 * dateOfBirth.set(1998, 2, 4);
		 * Student undergrad = StudentFactory.getStudent("undergraduate", IDFactory.getInstance("a", 1234), new Name("Mason", "Powell"), dateOfBirth)
		 * Management.registerStudent(undergrad);
		 * Management.amendStudentData("a1234", new Name("Nosam", "Llewop"));
		 */
		
		String letter = studentID.substring(0,1);
		int number = Integer.parseInt(studentID.substring(1,5));
		
		ID stuID = IDFactory.getInstance(letter, number);
		if(getRegisteredStudents().containsKey(stuID)){
			getRegisteredStudents().get(stuID).setPersonalName(studentData);
		} else {
			throw new RegistrationException("That student is not registered!");
		}
	}
	
	public static void amendStudentData(String studentID, Calendar studentData) throws InvalidAgeException, RegistrationException{
		/**
		 * Amends the students dateOfBirth data, throws a RegistrationException if the student isn't registered, throws an InvalidAgeException is the new dateOfBirth makes the student too young
		 * 
		 * Parameters:
		 * studentID --  A String containing the ID of the student whose data is to be amended
		 * studentData -- A Calendar object containing the new dateOfBirth for the student
		 * Example Usage:
		 * Calendar dateOfBirth = Calendar.getInstance();
		 * dateOfBirth.set(1998, 2, 4);
		 * Calendar newNateOfBirth = Calendar.getInstance();
		 * dateOfBirth.set(1999, 2, 4);
		 * Student undergrad = StudentFactory.getStudent("undergraduate", IDFactory.getInstance("a", 1234), new Name("Mason", "Powell"), dateOfBirth)
		 * Management.registerStudent(undergrad);
		 * Management.amendStudentData("a1234", newDateOfBirth));
		 */
		
		String letter = studentID.substring(0,1);
		int number = Integer.parseInt(studentID.substring(1,5));
		
		ID stuID = IDFactory.getInstance(letter, number);
		
		if(getRegisteredStudents().containsKey(stuID)){
			getRegisteredStudents().get(stuID).setDob(studentData);
		} else {
			throw new RegistrationException("That student is not registered!");
		}
	}
	
	public static void terminateStudent(String studentID) throws RegistrationException{
		/**
		 * Terminates the student by removing them from the registeredStudents map and the STUDENTS map in StudentFactory, throws a RegistrationException if the student isn't registered
		 * 
		 * Parameters:
		 * studentID -- A String containing the ID of the student to be terminated
		 * Example Usage:
		 * Calendar dateOfBirth = Calendar.getInstance();
		 * dateOfBirth.set(1998, 2, 4);
		 * Student undergrad = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), new Name("Mason", "Powell"), dateOfBirth)
		 * Management.registerStudent(undergrad);
		 * Management.terminateStudent(undergrad.getPersonalID());
		 * 
		 */
		String letter = studentID.substring(0,1);
		int number = Integer.parseInt(studentID.substring(1,5));
		ID stuID = IDFactory.getInstance(letter, number);
		
		if(getRegisteredStudents().containsKey(stuID)){
			getRegisteredStudents().remove(stuID);
			StudentFactory.getSTUDENTS().remove(stuID);
		} else {
			throw new RegistrationException("That student is not registered!");
		}
		
	}

	public static SmartCard assignSmartcard(String studentID) throws RegistrationException {
		/**
		 * Returns a new SmartCard for a student, throws a RegistrationException if the student isn't registered
		 * 
		 * Parameters:
		 * studentID -- A String containing the ID of the student to be assigned a SmartCard
		 * Example Usage:
		 * Calendar dateOfBirth = Calendar.getInstance();
		 * dateOfBirth.set(1998, 2, 4);
		 * Student undergrad = StudentFactory.getStudent("undergraduate", IDFactory.getInstance("a", 1234), new Name("Mason", "Powell"), dateOfBirth)
		 * Management.registerStudent(undergrad);
		 * Management.assignSmartcard("a1234");
		 */
		String letter = studentID.substring(0,1);
		int number = Integer.parseInt(studentID.substring(1,5));
		ID stuID = IDFactory.getInstance(letter, number);
		if(getRegisteredStudents().containsKey(stuID)){
			SmartCard newSmartCard = new SmartCard(registeredStudents.get(stuID));
			return newSmartCard;
		} else {
			throw new RegistrationException("That student is not registered!");
		}
	}
	
	public static Map<ID,Student> getRegisteredStudents() {
		/**
		 * Returns a map of all registered students
		 * 
		 * Returns:
		 * A map containing all registered Student objects, navigated by ID
		 * Example Usage:
		 * 
		 * Management.getRegisteredStudents()
		 */
		
		return registeredStudents;
	}
}
