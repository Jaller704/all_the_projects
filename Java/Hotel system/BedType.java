// Author: Mason Powell
// Date: 11/12/2017
// Purpose: Represent a set of constants for the bed size.

public enum BedType {
	SINGLE, DOUBLE;
	
	// An attempt at conversion of a string into my enum, returned null pointer exceptions and decided against it 
	/*public BedType stringTo(String inputString){
		if(inputString.equals("SINGLE")){
			return SINGLE;
		}
		else if(inputString.equals("DOUBLE")){
			return DOUBLE;
		}
		else{
			throw new IllegaLArgumentException("Must be SINGLE or DOUBLE");
		}
	}*/
}