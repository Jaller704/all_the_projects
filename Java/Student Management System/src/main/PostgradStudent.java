// Author: Mason Powell
// Date: 08/05/2018
// Purpose: Concrete class for an postgraduate (taught) student, stores the ID, name, date of birth, their modules and their age

package main;

import java.util.*;

public class PostgradStudent extends StudentFactory {
	
	private ID personalID;
	private List<String> moduleList = new ArrayList<String>();
	private static final Module PGMODULES = new Module(2);
	private Name personalName;
	private Date dob;
	private int age;
	private Date currentDate = new Date();
	private int moduleCredits = 0;
	
	PostgradStudent(ID id, Name personalName, Calendar dateOfBirth) throws InvalidAgeException {
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

	public void addModule(String moduleCode) throws TooManyModSupException{
		
		if(PGMODULES.moduleMap.get(moduleCode) == null){
			try{
				throw new IllegalArgumentException();
			}
			catch(IllegalArgumentException iae){
				System.out.println("That is not a registered module");
			}
		}else if(moduleCredits >= 180){
				throw new TooManyModSupException("Postgraduates can only have 120 credits worth of modules");
		
		}else{
			String credits = PGMODULES.moduleMap.get(moduleCode);
			int length = PGMODULES.moduleMap.get(moduleCode).length();
			length = length - 2;
			credits = credits.substring(length, credits.length());
			moduleCredits = moduleCredits + Integer.parseInt(credits);
				
			moduleList.add(PGMODULES.moduleMap.get(moduleCode));
		}
	}
	
	public List<String> getModuleList() {
		return moduleList;
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

	public void addSupervisor(String string) throws TooManyModSupException, NotImplementedException {
		throw new NotImplementedException("Taught students don't have supervisors!");
		
	}
	
	public Name getSupervisor() throws NotImplementedException {
		throw new NotImplementedException("Taught students don't have supervisors!");
	}
}
