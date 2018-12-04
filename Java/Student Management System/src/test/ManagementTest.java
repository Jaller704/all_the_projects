package test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import main.*;

public class ManagementTest {
	
	@Test
	public void testNoOfStudentsPostgradT() throws RegistrationException, InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1998, 2, 4);
		Name n = new Name("Mason", "Powell");
		Name n1 = new Name("Mason", "Powell");
		Name n2 = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("postgraduate taught", IDFactory.getInstance(), n, dateOfBirth);
		Student test1 = StudentFactory.getStudent("postgraduate taught", IDFactory.getInstance(), n1, dateOfBirth);
		Student test2 = StudentFactory.getStudent("postgraduate taught", IDFactory.getInstance(), n2, dateOfBirth);
		int expNo = 3;
		
		Management.registerStudent(test);
		Management.registerStudent(test1);
		Management.registerStudent(test2);
		
		assertEquals(expNo, Management.noOfStudents("postgraduate taught"));
	}
	
	@Test
	public void testNoOfStudentsPostgradR() throws RegistrationException, InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1998, 2, 4);
		Name n = new Name("Mason", "Powell");
		Name n1 = new Name("Christie", "Powell");
		Name n2 = new Name("Terry", "Powell");
		Name n3 = new Name("Alison", "Powell");
		Student test= StudentFactory.getStudent("postgraduate research", IDFactory.getInstance(), n, dateOfBirth);
		Student test1 = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance(), n1, dateOfBirth);
		Student test2 = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance(), n2, dateOfBirth);
		Student test3 = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance(), n3, dateOfBirth);
		int expNo = 4;
		
		Management.registerStudent(test);
		Management.registerStudent(test1);
		Management.registerStudent(test2);
		Management.registerStudent(test3);
		
		assertEquals(expNo, Management.noOfStudents("postgraduate research")); 
		
	}
	
	@Test
	public void testNoOfStudentsUndergrad() throws RegistrationException, InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Name n1 = new Name("Christie", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n1, dateOfBirth);
		Student test1 = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n, dateOfBirth);
		int expNo = 8; // Tested second last (in my tests) so 8 undergrads have already been registered before this actually runs
		
		Management.registerStudent(test);
		Management.registerStudent(test1);
		
		assertEquals(expNo, Management.noOfStudents("undergraduate")); 
	}

	@Test(expected = RegistrationException.class)
	public void testRegisterStudentAlreadyRegistered() throws RegistrationException, InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Name n1 = new Name("Christie", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n1, dateOfBirth);
		Student test1 = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n, dateOfBirth);

		Management.registerStudent(test);
		Management.registerStudent(test1);
		Management.registerStudent(test);
	}
	
	@Test
	public void testRegisterStudentUnregistered() throws RegistrationException, InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Name n1 = new Name("Christie", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n, dateOfBirth);
		Student test1 = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n1, dateOfBirth);

		Management.registerStudent(test);
		Management.registerStudent(test1);
		
	}

	@Test
	public void testAmendStudentDataStringName() throws RegistrationException, InvalidAgeException {
		Name n = new Name("Nosam", "Llewop");
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n, dateOfBirth);
		Name testName = new Name("Mason", "Powell");
		Name expName = new Name("Mason", "Powell");
		
		Management.registerStudent(test);
		Management.amendStudentData(test.getPersonalID().toString(), testName);
		assertEquals(expName.toString(),test.getPersonalName().toString());
	}

	@Test
	public void testAmendStudentDataStringDate() throws InvalidAgeException, RegistrationException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n, dateOfBirth);
		Calendar cal = Calendar.getInstance();
		cal.set(1992, 5, 7);
		Date testDateOfBirth = cal.getTime();

		
		Management.registerStudent(test);
		Management.amendStudentData(test.getPersonalID().toString(), cal);
		
		assertEquals(testDateOfBirth,test.getDob());
	}

	@Test
	public void testTerminateStudent() throws RegistrationException, InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Name n1 = new Name("Christie", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n1, dateOfBirth);
		Student test1 = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n, dateOfBirth);
		
		Management.registerStudent(test);
		Management.registerStudent(test1);
		
		
		Management.terminateStudent(test.getPersonalID().toString());
		assertEquals(null,Management.getRegisteredStudents().get(test.getPersonalID()));
	}

}
