package test;

import static org.junit.Assert.*;

import org.junit.Test;


import main.*;


public class NameTest {

	@Test
	public void testName() {
		Name test = new Name("Mason", "Powell");
		
		assertEquals(Name.class, test.getClass());
	}

	@Test
	public void testGetFName() {
		Name test = new Name("Mason", "Powell");
		String expName = "Mason";
		
		assertEquals(expName, test.getFName());
	}

	@Test
	public void testSetFName() {
		Name test = new Name("Mason", "Powell");
		String expName = "Nosam";
		
		test.setFName("Nosam");
		
		assertEquals(expName, test.getFName());
	}

	@Test
	public void testGetLName() {
		Name test = new Name("Mason", "Powell");
		String expName = "Powell";
		
		assertEquals(expName, test.getLName());
	}

	@Test
	public void testSetLName() {
		Name test = new Name("Mason", "Powell");
		String expName = "Llewop";
		
		test.setLName("Llewop");
				
		assertEquals(expName, test.getLName());
	}

	@Test
	public void testGetInitials() {
		Name test = new Name("Mason", "Powell");
		
		String expInitials = "MP";
		
		assertEquals(expInitials,test.getInitials());
	}

	@Test
	public void testToString() {
		Name test = new Name("Mason", "Powell");
		
		String expString = "First name= Mason, last name= Powell, initials= MP";
		
		assertEquals(expString, test.toString());
	}

}
