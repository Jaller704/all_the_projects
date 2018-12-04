package test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import main.*;

public class StudentFactoryTest {

	@Test
	public void testGetStudentUndergraduate() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("undergraduate", IDFactory.getInstance(), n, dateOfBirth);
		
		assertEquals(UndergradStudent.class, test.getClass());
	}
	
	@Test
	public void testGetStudentPostgraduateTaught() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("postgraduate taught", IDFactory.getInstance(), n, dateOfBirth);
		
		assertEquals(PostgradStudent.class, test.getClass());
	}
	
	@Test
	public void testGetStudentPostgraduateResearch() throws InvalidAgeException {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1999, 2, 4);
		Name n = new Name("Mason", "Powell");
		Student test = StudentFactory.getStudent("postgraduate research", IDFactory.getInstance(), n, dateOfBirth);
		
		assertEquals(PostgradResearchStudent.class, test.getClass());
	}

}
