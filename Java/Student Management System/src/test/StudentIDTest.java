package test;

import static org.junit.Assert.*;

import org.junit.Test;
import main.*;

public class StudentIDTest {

	@Test
	public void testStudentID() {
		ID test = IDFactory.getInstance();
		
		assertEquals(StudentID.class, test.getClass());
	}

	@Test
	public void testStudentIDStringInt() {
		ID test = IDFactory.getInstance("m", 2468);
		
		assertEquals(StudentID.class, test.getClass());
	}

	@Test
	public void testGetID() {
		ID test = IDFactory.getInstance("m", 0004);
		
		String expID = "m0004";
		String actualID = test.getID();
		
		assertEquals(expID,actualID);
		
	}

	@Test
	public void testGetNumber() {
		ID test = IDFactory.getInstance("m", 2468);
		
		int expNumber = 2468;
		int  actualNumber = test.getNumber();
		
		assertEquals(expNumber,actualNumber);
	}

	@Test
	public void testGetLetter() {
		ID test = IDFactory.getInstance("m", 2468);
		
		String expLetter = "m";
		String actualLetter =  test.getLetter();
		
		assertEquals(expLetter,actualLetter);
	}

	@Test
	public void testToString() {
		ID test = IDFactory.getInstance("m", 0004);
		
		String expID = "m0004";
		String actualID = test.toString();
		
		assertEquals(expID,actualID);
	}

}
