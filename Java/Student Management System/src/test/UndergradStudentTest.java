package test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import main.*;

public class UndergradStudentTest {

	@Test
	public void testUndergradStudent() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n, dateOfBirth);
		
		assertEquals(UndergradStudent.class, test.getClass());
	}

	@Test
	public void testSetPersonalIDValid() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Name n1 = new Name("Christie", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance("a",1234), n1, dateOfBirth);
		Student test2 = StudentFactory.getStudent("undergraduate", IDFactory.getInstance("a",5678), n, dateOfBirth);
		ID testID = IDFactory.getInstance("a", 4321);
		
		test2.setPersonalID(testID);
	}

	@Test
	public void testSetPersonalIDInvalid() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Name n1 = new Name("Christie", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance("a",1234), n1, dateOfBirth);
		Student test2 = StudentFactory.getStudent("undergraduate", IDFactory.getInstance("a",5678), n, dateOfBirth);
		ID testID = IDFactory.getInstance("a", 1234);
		
		test2.setPersonalID(testID);
	}
	
	@Test
	public void testGetPersonalID() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance("a",1234), n, dateOfBirth);
		ID expID = IDFactory.getInstance("a", 1234);
		
		assertEquals(expID,test.getPersonalID());
	}
	
	@Test
	public void testSetPersonalName() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n, dateOfBirth);
		test.setPersonalName(n);
		
	}
	
	@Test
	public void testGetPersonalName() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n, dateOfBirth);
		
		Name expName = new Name("Mason", "Powell");
		Name testName = test.getPersonalName();
		
		assertEquals(testName.toString(), expName.toString());
	}

	@Test
	public void testAddModule() throws TooManyModSupException, NotImplementedException, InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n, dateOfBirth);
		
		test.addModule("CSC1022");
	}
	
	@Test(expected = TooManyModSupException.class)
	public void testAddModuleTooMany() throws TooManyModSupException, NotImplementedException, InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n, dateOfBirth);
		
		test.addModule("CSC1021");
		test.addModule("CSC1022");
		test.addModule("CSC1023");
		test.addModule("CSC1024");
		test.addModule("CSC1025");
		test.addModule("CSC1026");
		test.addModule("CSC2026");
	}

	@Test
	public void testGetModuleList() throws NotImplementedException, TooManyModSupException, InvalidAgeException {
		List<String> expList = new ArrayList<String>();
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n, dateOfBirth);
		
		expList.add("CSC1022, Programming 2, 20");
		test.addModule("CSC1022");
		
		assertEquals(expList, test.getModuleList());
	}

	@Test
	public void testSetDobValidAge() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n, dateOfBirth);
		
		test.setDob(dateOfBirth);
	}
	
	@Test(expected = InvalidAgeException.class)
	public void testSetDobInvalidAge() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(2003,2,4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n, dateOfBirth);
		
		test.setDob(dateOfBirth);
	}

	@Test
	public void testGetDob() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999,2,4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n, dateOfBirth);
		Calendar cal = Calendar.getInstance();
		cal.set(1999,2,4);
		Date expDateOfBirth = cal.getTime();
		test.setDob(dateOfBirth);
		
		assertEquals(expDateOfBirth.toString(), test.getDob().toString());
	}

	@Test
	public void testGetAge() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n, dateOfBirth);
		test.setDob(dateOfBirth);
		
		int expAge = 19;
		
		assertEquals(expAge,test.getAge());
	}
	
	@Test(expected = NotImplementedException.class)
	public void testAddSupervisor() throws InvalidAgeException, TooManyModSupException, NotImplementedException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n, dateOfBirth);
		
		test.addSupervisor("Alan");
	}

	@Test(expected = NotImplementedException.class)
	public void testGetSupervisor() throws InvalidAgeException, NotImplementedException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n, dateOfBirth);
		
		test.getSupervisor();
	}
}
