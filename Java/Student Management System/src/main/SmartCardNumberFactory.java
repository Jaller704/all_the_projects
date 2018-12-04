// Author: Mason Powell
// Date: 08/05/2018
// Purpose: Object factory for the SmartCardNumbers, ensures uniqueness and a level of abstraction away from the concrete SmartCardNumber class

package main;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class SmartCardNumberFactory implements SCNumber {
	private static final Map<String,SCNumber> NUMBERS = new HashMap<String,SCNumber>();
	
	public static SCNumber getInstance(String initials, Date issueDate){
		/**
		 * Returns new SmartCardNumber and places it into a map using it's toString() as the key (if it isn't already in there)
		 * 
		 * Parameters:
		 * initials -- String containing the first letter of the Student first name and last name
		 * issueDate -- Date containing the date of issue of the calling SmartCard
		 * Example Usage:
		 * SmartCard sc = new SmartCard(stu);
		 * SmartCardNumberFactory.getInstance(stu.getPersonalName().getInitials(), sc.getIssueDate());
		 */
		
		SCNumber newNumber = new SmartCardNumber(initials, issueDate);
		final String k = newNumber.toString();
		if(!NUMBERS.containsKey(k)) {
			NUMBERS.put(k, newNumber);
		};
		return NUMBERS.get(k);
	}
}
