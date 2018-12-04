// Author: Mason Powell
// Date: 08/05/2018
// Purpose: Concrete class for an undergraduate student, stores the ID, name, date of birth, their modules and their age

package main;

import java.util.*;

public class UndergradStudent extends StudentFactory {

	private ID personalID;
	private List<String> moduleList = new ArrayList<String>();
	private static final Module UGMODULES = new Module(1);
	private Name personalName;
	private Date dob;
	private int age;
	private Date currentDate = new Date();
	private int moduleCredits = 0;

	
	
	public UndergradStudent(ID id, Name personalName, Calendar dateOfBirth) throws InvalidAgeException{
		setPersonalID(id);
		setPersonalName(personalName);
		setDob(dateOfBirth);
	}

	public void setPersonalID(ID newID){
		if(inUse.size() < 260000){
			if(!inUse.containsKey(newID.toString())){
				inUse.put(newID.toString(), newID);
				this.personalID = newID;
			}else if(inUse.containsKey(newID.toString())){
				newID = IDFactory.getInstance();
				setPersonalID(newID);
				System.out.println("This ID is in use already, a new one has been generated: " + newID);
			}
		}else{
			System.out.println("All IDs are being used! (that's a lot of students!)");
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
		if(UGMODULES.moduleMap.get(moduleCode) == null){
			try{
				throw new IllegalArgumentException();
			}
			catch(IllegalArgumentException iae){
				System.out.println("That is not a registered module");
			}
		}else if(moduleCredits >= 120){
				throw new TooManyModSupException("Undergraduates can only have 120 credits worth of modules");
		}else {
			String credits = UGMODULES.moduleMap.get(moduleCode);
			int length = UGMODULES.moduleMap.get(moduleCode).length();
			length = length - 2;
			credits = credits.substring(length, credits.length());
			moduleCredits = moduleCredits + Integer.parseInt(credits);
			
			moduleList.add(UGMODULES.moduleMap.get(moduleCode));
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
	
	private void setAge() throws InvalidAgeException{
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
		if(age<17){
			throw new InvalidAgeException("This student is too young to be an undergraduate!");
		}
	}
	
	public void addSupervisor(String string) throws TooManyModSupException, NotImplementedException {
		throw new NotImplementedException("Taught students don't have supervisors!");
		
	}

	public Name getSupervisor() throws NotImplementedException {
		throw new NotImplementedException("Taught students don't have supervisors!");
	}

}
