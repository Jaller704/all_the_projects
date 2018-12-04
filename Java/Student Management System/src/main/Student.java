// Author: Mason Powell
// Date: 08/05/2018
// Purpose: Interface for the three different concrete implementations of Student, defines the methods to be implemented

package main;

import java.util.*;

public interface Student {
	static final Map<String,ID> inUse = new HashMap<String,ID>();
	
	void setPersonalID(ID id);
	/**
	 * Sets the personal ID for the student using the passed in ID object
	 * 
	 * Parameters:
	 * id -- An ID object containing a letter and a number
	 * Example Usage:
	 * Calendar dateOfBirth = Calendar.getInstance();
	 * dateOfBirth.set(1999, 2, 4);
	 * Student undergrad = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), new Name("Mason", "Powell"), dateOfBirth);
	 * undergrad.setPersonalID(IDFactory.getInstance("a",1234));
	 */
	
	public ID getPersonalID();
	/**
	 * Gets the personal ID for the student
	 * 
	 * Example Usage:
	 * Calendar dateOfBirth = Calendar.getInstance();
	 * dateOfBirth.set(1999, 2, 4);
	 * Student undergrad = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), new Name("Mason", "Powell"), dateOfBirth);
	 * undergrad.getPersonalID();
	 */
	
	public Name getPersonalName();
	/**
	 * Gets the personal Name for the student
	 * 
	 * Example Usage:
	 * Calendar dateOfBirth = Calendar.getInstance();
	 * dateOfBirth.set(1999, 2, 4);
	 * Student undergrad = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), new Name("Mason", "Powell"), dateOfBirth);
	 * undergrad.getPersonalName();
	 */
	
	public void setPersonalName(Name personalName);
	/**
	 * Sets the personal Name for the student using the passed in Name object
	 * 
	 * Parameters:
	 * personalName -- A Name object containing a first name, a last name, and the initials
	 * Example Usage:
	 * Calendar dateOfBirth = Calendar.getInstance();
	 * dateOfBirth.set(1999, 2, 4);
	 * Student undergrad = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), new Name("Mason", "Powell"), dateOfBirth);
	 * undergrad.setPersonalName(new Name("Nosam", "Llewop"));
	 */
	
	public void setDob(Calendar dateOfBirth) throws InvalidAgeException;
	/**
	 * Sets the date of birth for the student using the passed in Calendar object, throws an InvalidAgeException if the student is too young
	 * 
	 * Parameters:
	 * dateOfBirth -- A Calendar object containing the date of birth of the student
	 * Example Usage:
	 * Calendar dateOfBirth = Calendar.getInstance();
	 * dateOfBirth.set(1999, 2, 4);
	 * Calendar newDateOfBirth = Calendar.getInstance();
	 * newDateOfBirth.set(1998, 2, 4);
	 * Student undergrad = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), new Name("Mason", "Powell"), dateOfBirth);
	 * undergrad.setDob(newDateOfBirth);
	 */
	
	public Date getDob();
	/**
	 * Gets the date of birth for the student
	 * 
	 * Example Usage:
	 * Calendar dateOfBirth = Calendar.getInstance();
	 * dateOfBirth.set(1999, 2, 4);
	 * Student undergrad = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), new Name("Mason", "Powell"), dateOfBirth);
	 * undergrad.getDob();
	 */
	
	public int getAge();
	/**
	 * Gets the age of the student
	 * 
	 * Example Usage:
	 * Calendar dateOfBirth = Calendar.getInstance();
	 * dateOfBirth.set(1999, 2, 4);
	 * Student undergrad = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), new Name("Mason", "Powell"), dateOfBirth);
	 * undergrad.getAge();
	 */
	
	public void addModule(String moduleCode) throws TooManyModSupException, NotImplementedException;
	/**
	 * Adds a module to the students moduleList, throws a TooManyModSupException if their module credit values exceeds their defined max credit value, throws a NotImplementedException if the method isn't applicable to the implementation
	 * 
	 * Parameters:
	 * moduleCode -- A String containing the module code for navigating the map produced by Module (e.g. CSC1022)
	 * Example Usage:
	 * Calendar dateOfBirth = Calendar.getInstance();
	 * dateOfBirth.set(1999, 2, 4);
	 * Student undergrad = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), new Name("Mason", "Powell"), dateOfBirth);
	 * undergrad.addModule("CSC1024");
	 */
	
	public List<String> getModuleList() throws NotImplementedException;
	/**
	 * Returns the list of modules the student is currently taking, throws a NotImplementedException if the method isn't applicable to the implementation
	 * 
	 * Example Usage:
	 * Calendar dateOfBirth = Calendar.getInstance();
	 * dateOfBirth.set(1999, 2, 4);
	 * Student undergrad = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), new Name("Mason", "Powell"), dateOfBirth)
	 * undergrad.addModule("CSC1024");
	 * undergrad.getModuleList();
	 */

	void addSupervisor(String supervisorFirstName) throws TooManyModSupException, NotImplementedException;
	/**
	 * Adds a supervisor to the student, throws a TooManyModSupException if their supervisor count exceeds their defined max supervisor count, throws a NotImplementedException if the method isn't applicable to the implementation
	 * 
	 * Parameters:
	 * supervisorFirstName -- A String containing the first name of the supervisor to be added, used as the key for the supervisor map produced by Supervisor
	 * Example Usage:
	 * Calendar dateOfBirth = Calendar.getInstance();
	 * dateOfBirth.set(1999, 2, 4);
	 * Student undergrad = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), new Name("Mason", "Powell"), dateOfBirth);
	 * undergrad.addSupervisor("Neil");
	 */

	Name getSupervisor() throws NotImplementedException;
	/**
	 * Returns the supervisor the student currently has, throws a NotImplementedException if the method isn't applicable to the implementation
	 * 
	 * Example Usage:
	 * Calendar dateOfBirth = Calendar.getInstance();
	 * dateOfBirth.set(1999, 2, 4);
	 * Student undergrad = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), new Name("Mason", "Powell"), dateOfBirth)
	 * undergrad.addSupervisor("Neil");
	 * undergrad.getSupervisor();
	 */
	
	
}
