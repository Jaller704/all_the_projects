// Author: Mason Powell
// Date: 08/05/2018
// Purpose: Store a first name, a last name, and the initials produced
package main;

public class Name {
	private String fName = "Blank";
	private String lName = "Blank";
	private String initials;	
	public Name(String fName, String lName){
		setFName(fName);
		setLName(lName);
	}
	
	public String getFName() {
		return fName;
	}
	public void setFName(String fName) {
		this.fName = fName;
		setInitials();
	}
	public String getLName() {
		return lName;
	}
	public void setLName(String lName) {
		this.lName = lName;
		setInitials();
	}
	public String getInitials() {
		return initials;
	}

	private void setInitials(){
		this.initials = fName.substring(0, 1) + lName.substring(0, 1);
	}
	public String toString() {
		return "First name= " + fName + ", last name= " + lName + ", initials= " + initials;
	}
	
}
