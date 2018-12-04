// Author: Mason Powell
// Date: 08/05/2018
// Purpose: Stores and provides access to the serial number, issue year and initials used for building a SmartCardNumber

package main;

import java.util.*;

class SmartCardNumber extends SmartCardNumberFactory {
	int serialNumber = -1;
	String initials;
	int issueYear;
	
	public SmartCardNumber(String initials, Date issueDate) {
		setSerialNumber();
		setInitials(initials);
		setIssueYear(issueDate);
	}
	
	private void setIssueYear(Date issueDate) {
		/**
		 * Sets the issue year based on the issue date passed in from the calling SmartCardNumberFactory
		 * Parameters:
		 * issueDate -- a Date usually passed into the factory then into this class from the calling SmartCard object
		 * Example Usage:
		 * SmartCard sc = new SmartCard(stu);
		 * setIssueYear(sc.getIssueDate());
		 */
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(issueDate);
		this.issueYear = cal.get(Calendar.YEAR);
	}

	private void setInitials(String initials) {
		/**
		 * Sets the initials based on the initials given from the Student object passed into SmartCardNumber
		 * 
		 * Parameters:
		 * initials -- Two letters taken from the beginning letter of a students first and last names, 
		 * the getInitials method in Name provides these in this implementation
		 * Example Usage:
		 * setInitials(stu.getPersonalName().getInitials())
		 */
		
		this.initials = initials.toUpperCase();
	}

	private void setSerialNumber() {
		/**
		 * Sets the serial number, picked randomly out of 2^32 numbers
		 * Example Usage:
		 * setSerialNumber()
		 */
		
		this.serialNumber = new Random().nextInt();
	}

	private int getSerialNumber() {
		/**
		 * Returns the serial number, if it hasn't been generated then one is generated on the fly
		 * 
		 * Returns:
		 * Changes depending an if statement, if a serial number hasn't been generated (serialNumber == -1) then a new one is generated and returned
		 * if one has already been generated then it just returns the contents of serialNumber
		 * Example Usage:
		 * setSerialNumber();		// Generates one first
		 * getSerialNumber();		// Returns the generated one
		 */
		
		if(serialNumber == -1){
			setSerialNumber();
		}
		return serialNumber;
	}

	private int getIssueYear() {
		/**
		 * Returns the issue year taken from the issue date of the SmartCard object
		 * 
		 * Returns:
		 * An integer of the year taken from the issue date
		 * Example Usage:
		 * SmartCard sc = new SmartCard(stu);
		 * setIssueYear(sc.getIssueDate());
		 * getIssueYear();
		 */
		return issueYear;
	}

	private String getInitials() {
		/**
		 * Returns the initials taken from the name of the Student object
		 * 
		 * Returns:
		 * An integer of the year taken from the issue date
		 * Example Usage:
		 * SmartCard sc = new SmartCard(stu);
		 * setInitials(stu.getPersonalName().getInitials())
		 * getInitials();
		 */
		
		return initials;
	}
	
	 public String toString() {
		 /**
		  * Returns the initials + the issueYear + the serialNumber separated by "-"
		  * 
		  * Returns:
		  * A string representation of the initials + the issueYear + the serialNumber separated by "-"
		  */
		return getInitials() + "-" + getIssueYear() + "-" + getSerialNumber();
	}
}
	

