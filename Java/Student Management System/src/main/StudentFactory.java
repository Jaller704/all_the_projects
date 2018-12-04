// Author: Mason Powell
// Date: 08/05/2018
// Purpose: Object factory for the students, ensures uniqueness and a level of abstraction away from the concrete Student implementations

package main;

import java.util.*;


public abstract class StudentFactory implements Student{
	private static final Map<ID,Student> STUDENTS = new HashMap<ID,Student>();
	public static Student getStudent(String stu, ID id, Name personalName, Calendar dateOfBirth) throws InvalidAgeException {
		/**
		 * Returns a new Student with the concrete implementation dependent on stu and places it into a map using id as the key (if it isn't already in there)
		 * 
		 * Parameters:
		 * stu -- String containing either "undergraduate", "postgraduate taught", or "postgraduate research" to decide which object to return
		 * id -- ID object used for creating a new object and as the key of STUDENTS
		 * personalName -- a Name object storing the name for the new student
		 * dateOfBirth -- a Calendar object storing the date of birth for the new student
		 * Example Usage:
		 * Calendar dateOfBirth = Calendar.getInstance();
		 * dateOfBirth.set(1998, 2, 4);
		 * Student undergrad = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), new Name("Mason", "Powell"), dateOfBirth)
		 */
		
		final ID k = id;
		if (stu.equalsIgnoreCase("undergraduate")) {
			
			if(!STUDENTS.containsKey(k)) {
				STUDENTS.put(k, new UndergradStudent(id, personalName, dateOfBirth));
			}
			return STUDENTS.get(k);
			
		} else if (stu.equalsIgnoreCase("postgraduate taught")) {
			
			if(!STUDENTS.containsKey(k)) {
				STUDENTS.put(k, new PostgradStudent(id, personalName, dateOfBirth));
			}
			return STUDENTS.get(k);
		} else if(stu.equalsIgnoreCase("postgraduate research")) {
			if(!STUDENTS.containsKey(k)) {
				STUDENTS.put(k, new PostgradResearchStudent(id, personalName, dateOfBirth));
			}
			return STUDENTS.get(k);
		} else {
			throw new IllegalArgumentException("3 options: undergraduate, postgraduate taught, or postgraduate research");
		}
		
	}
	
	public static Map<ID,Student> getSTUDENTS(){
		/**
		 * Returns a map of all created Student objects
		 * 
		 * Returns:
		 * A map containing all created Student objects, navigated by ID
		 * Example Usage:
		 * 
		 * getSTUDENTS()
		 */
		
		return STUDENTS;
	}
	
}
