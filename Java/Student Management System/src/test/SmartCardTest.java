package test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import main.*;

public class SmartCardTest {

	@Test
	public void testSmartCard() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999,2,4);
		Name testName = new Name("Mason", "Powell");
		Student testStu = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), testName, dateOfBirth);
		SmartCard test = new SmartCard(testStu);
		assertEquals(SmartCard.class, test.getClass());
	}

	@Test
	public void testGetStuName() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999,2,4);
		Name testName = new Name("Mason", "Powell");
		Student testStu = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), testName, dateOfBirth);
		SmartCard test = new SmartCard(testStu);
		Name expName = new Name("Mason", "Powell");
		
		assertEquals(expName.toString(),test.getStuName().toString());
	}

	@Test
	public void testGetDateOfBirth() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999,2,4);
		Name testName = new Name("Mason", "Powell");
		Student testStu = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), testName, dateOfBirth);
		SmartCard test = new SmartCard(testStu);
		Calendar cal = Calendar.getInstance();
		cal.set(1999, 2, 4);
		Date expDateOfBirth = cal.getTime();
		
		assertEquals(expDateOfBirth.toString(),test.getDateOfBirth().toString());
	}

	@Test
	public void testGetStuID() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999,2,4);
		Name testName = new Name("Mason", "Powell");
		Student testStu = StudentFactory.getStudent("undergraduate", IDFactory.getInstance("a",1234), testName, dateOfBirth);
		SmartCard test = new SmartCard(testStu);
		ID expID = IDFactory.getInstance("a",1234);
		
		assertEquals(expID, test.getStuID());
	}

	@Test
	public void testGetIssueDate() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999,2,4);
		Name testName = new Name("Mason", "Powell");
		Student testStu = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), testName, dateOfBirth);
		SmartCard test = new SmartCard(testStu);
		Calendar cal = Calendar.getInstance();
		Date expIssueDate = cal.getTime();
		
		assertEquals(expIssueDate,test.getIssueDate());
	}

}
