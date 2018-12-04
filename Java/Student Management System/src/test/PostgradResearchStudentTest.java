package test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import main.*;

public class PostgradResearchStudentTest {

	@Test
	public void testPostgradResearchStudent() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1998, 2, 4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance(), n, dateOfBirth);
		
		assertEquals(PostgradResearchStudent.class, test.getClass());
	}

	@Test
	public void testSetPersonalIDValid() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1998, 2, 4);
		Name n = new Name("Mason", "Powell");
		Name n1 = new Name("Christie", "Powell");
		Student test = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance("a",1234), n1, dateOfBirth);
		Student test2 = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance("a",5678), n, dateOfBirth);
		ID testID = IDFactory.getInstance("b", 8765);
		
		test2.setPersonalID(testID);
	}

	@Test
	public void testSetPersonalIDInvalid() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1998, 2, 4);
		Name n = new Name("Mason", "Powell");
		Name n1 = new Name("Christie", "Powell");
		Student test = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance("c",2468), n, dateOfBirth);
		Student test2 = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance("a",5678), n1, dateOfBirth);
		ID testID = IDFactory.getInstance("c", 2468);
		
		test2.setPersonalID(testID);
	}
	
	@Test
	public void testGetPersonalID() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1998, 2, 4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance("a",1234), n, dateOfBirth);
		ID expID = IDFactory.getInstance("a", 1234);
		
		assertEquals(expID,test.getPersonalID());
	}

	@Test
	public void testGetPersonalName() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1998, 2, 4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance("a",1234), n, dateOfBirth);

		Name expName = new Name("Mason", "Powell");
		Name testName = test.getPersonalName();
		
		assertEquals(testName.toString(), expName.toString());
	}

	@Test
	public void testSetPersonalName() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1998, 2, 4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance("a",1234), n, dateOfBirth);
		test.setPersonalName(n);
		
	}

	@Test
	public void testSetDobValidAge() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1998,2,4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance(), n, dateOfBirth);
		
		test.setDob(dateOfBirth);
	}
	
	@Test(expected = InvalidAgeException.class)
	public void testSetDobInvalidAge() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1998,2,4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance(), n, dateOfBirth);

		dateOfBirth.set(2002,2,4);
		
		test.setDob(dateOfBirth);
	}

	@Test
	public void testGetDob() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1998,2,4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance(), n, dateOfBirth);
		Calendar cal = Calendar.getInstance();
		cal.set(1998,2,4);
		Date expDateOfBirth = cal.getTime();
		test.setDob(dateOfBirth);
		
		assertEquals(expDateOfBirth.toString(), test.getDob().toString());
	}

	@Test
	public void testGetAge() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1998,2,4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance(), n, dateOfBirth);
		test.setDob(dateOfBirth);
		
		int expAge = 20;
		
		assertEquals(expAge,test.getAge());
	}

	@Test(expected = NotImplementedException.class)
	public void testAddModule() throws TooManyModSupException, NotImplementedException, InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1998,2,4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance(), n, dateOfBirth);
		
		test.addModule("CSC1022");
	}

	@Test(expected = NotImplementedException.class)
	public void testGetModuleList() throws NotImplementedException, InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1998,2,4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance(), n, dateOfBirth);
		
		test.getModuleList();
	}

	@Test
	public void testAddSupervisor() throws TooManyModSupException, InvalidAgeException, NotImplementedException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1998,2,4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance(), n, dateOfBirth);
		
		test.addSupervisor("Alan");
	}
	
	@Test(expected = TooManyModSupException.class)
	public void testAddSupervisorTooMany() throws TooManyModSupException, NotImplementedException, InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1998,2,4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance(), n, dateOfBirth);
		
		test.addSupervisor("Alan");
		test.addSupervisor("Neil");
		
	}

	@Test
	public void testGetSupervisor() throws TooManyModSupException, NotImplementedException, InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1998,2,4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance(), n, dateOfBirth);
		Name expSup = new Name("Alan", "Tully");
		
		test.addSupervisor("Alan");
		
		assertEquals(expSup.toString(),test.getSupervisor().toString());
	}

}
