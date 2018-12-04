// Author: Mason Powell
// Date: 08/05/2018
// Purpose: Concrete class for an postgraduate (research) student, stores the ID, name, date of birth, their supervisors and their age

package main;

import java.util.*;


public class PostgradResearchStudent extends StudentFactory{
	private ID personalID;
	private Name personalName;
	private Date dob;
	private int age;
	private Date currentDate = new Date();
	private int supervisorCount;
	private static final Supervisor SUPERVISORS = new Supervisor();
	private Name supervisor;
	
	
	public PostgradResearchStudent(ID id, Name personalName, Calendar dateOfBirth) throws InvalidAgeException {
		setPersonalID(id);
		setPersonalName(personalName);
		setDob(dateOfBirth);
	}
	
	public void setPersonalID(ID newID) {
		if(!inUse.containsKey(newID.toString())){
			inUse.put(newID.toString(), newID);
			this.personalID = newID;
		}else if(inUse.containsKey(newID.toString())){
				newID = IDFactory.getInstance();
				setPersonalID(newID);
				System.out.println("This ID is in use already, a new one has been generated: " + newID);
		}
	}

	public ID getPersonalID() {
		return personalID;
	}

	public Name getPersonalName() {
		return personalName;
	}

	public void setPersonalName(Name personalName) {
		this.personalName = personalName;
	}

	public void setDob(Calendar dateOfBirth) throws InvalidAgeException {
		Date dob = dateOfBirth.getTime();
		this.dob = dob;
		setAge();
	}

	public Date getDob() {
		return dob;
	}

	public int getAge() {
		return age;
	}

	private void setAge() throws InvalidAgeException {
		/**
		 * Sets the age of the student, checks if they are old enough to be this type of student, throws an InvalidAgeException if the student is too young
		 * 
		 * Example Usage:
		 * setAge();
		 */
		
		Calendar currentCal = Calendar.getInstance();
		currentCal.setTime(currentDate);
		Calendar birthDate = Calendar.getInstance();
		birthDate.setTime(dob);
		int cYear = currentCal.get(Calendar.YEAR);
		int bYear = birthDate.get(Calendar.YEAR);
		
		this.age = cYear - bYear; 
		if(age<20){
			throw new InvalidAgeException("This student is too young to be a postgraduate!");
		}
	}
	
	public void addModule(String moduleCode) throws NotImplementedException {
			throw new NotImplementedException("Research students don't have modules!");
	}
	
	public List<String> getModuleList() throws NotImplementedException {
			throw new NotImplementedException("Research students don't have modules!");
	}
		
		
	
	public void addSupervisor(String supervis) throws TooManyModSupException{
		String[] supervisSplit = supervis.split(" ");
		String supervisFName = supervisSplit[0];
		if(SUPERVISORS.supervisorMap.get(supervisFName) == null){
			try{
				throw new IllegalArgumentException();
			}
			catch(IllegalArgumentException iae){
				System.out.println("That is not a registered supervisor");
			}
		}else if(supervisorCount >= 1){
				throw new TooManyModSupException("Postgraduates can only have 1 supervisor");
	
		}else {
			this.supervisor = SUPERVISORS.supervisorMap.get(supervisFName);
			this.supervisorCount += 1;
		}
	}
	
	public Name getSupervisor() {
		return supervisor;
	}
}
