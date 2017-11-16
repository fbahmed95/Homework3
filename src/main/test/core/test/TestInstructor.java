package core.test;
import core.api.IAdmin;
import core.api.impl.Admin;
import core.api.IStudent;
import core.api.impl.Student; 
import core.api.IInstructor; 
import core.api.impl.Instructor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestInstructor {
	 private IAdmin admin;
	 private IStudent student; 
	 private IInstructor instructor; 
	    

	@Before
	public void setup() {
	    this.admin = new Admin();
	    this.student = new Student();
	    this.instructor = new Instructor();
	}
	
	// addHomework 
	// wrong instructor 
	//  
	//BUG 
	@Test 
	public void addHwWrongInstructor() {
		this.admin.createClass("Math", 2017, "Cherney", 15);
		this.admin.createClass("ProgrammingTools", 2017, "Devanbu", 30);
		this.instructor.addHomework("Devanbu", "Math", 2017, "ThisHW");
		assertFalse(this.instructor.homeworkExists("Math", 2017, "ThisHW"));
	}
	@Test 
	public void addHwRightInstructor() {
		this.admin.createClass("Math", 2017, "Cherney", 15);
		this.admin.createClass("ProgrammingTools", 2017, "Devanbu", 30);
		this.instructor.addHomework("Devanbu", "ProgrammingTools", 2017, "ThisHW");
		assertTrue(this.instructor.homeworkExists("ProgrammingTools", 2017, "ThisHW"));
	}
	
	
	// assignGrade 
	// provided this instructor has been assigned to this class,
	// the homework has been assigned and the student has submitted this homework.
	// negative grade 
	// student that has not submitted can still be given a grade
	// student not registered can still be given a grade
	// 
	//BUG
	@Test 
	public void negativeGrade() {
		this.admin.createClass("Math", 2017, "Cherney", 15);
		this.instructor.addHomework("Cherney", "Math", 2017, "Assignment");
		this.student.registerForClass("Fareeha", "Math", 2017);
		this.student.submitHomework("Fareeha", "Assignment", "Hello", "Math", 2017);
		this.instructor.assignGrade("Cherney", "Math", 2017, "Assignment", "Fareeha", -10);
		assertFalse(this.instructor.getGrade("Math", 2017, "Assignment", "Fareeha") == -10);
	}
	
	@Test 
	public void positiveGrade() {
		this.admin.createClass("Math", 2017, "Cherney", 15);
		this.instructor.addHomework("Cherney", "Math", 2017, "Assignment");
		this.student.registerForClass("Fareeha", "Math", 2017);
		this.student.submitHomework("Fareeha", "Assignment", "Hello", "Math", 2017);
		this.instructor.assignGrade("Cherney", "Math", 2017, "Assignment", "Fareeha", 10);
		assertTrue(this.instructor.getGrade("Math", 2017, "Assignment", "Fareeha") == 10);
	}
	
	//BUG
	@Test 
	public void notSubmitted() {
		this.admin.createClass("Math", 2017, "Cherney", 15);
		this.instructor.addHomework("Cherney", "Math", 2017, "Assignment");
		this.student.registerForClass("Fareeha", "Math", 2017);
		this.instructor.assignGrade("Cherney", "Math", 2017, "Assignment", "Fareeha", 10);
		assertFalse(this.instructor.getGrade("Math", 2017, "Assignment", "Fareeha") == 10);
	}
	
	//BUG
	@Test 
	public void notRegistered() {
		this.admin.createClass("Math", 2017, "Cherney", 15);
		this.instructor.addHomework("Cherney", "Math", 2017, "Assignment");
		this.student.submitHomework("Fareeha", "Assignment", "Hello", "Math", 2017);
		this.instructor.assignGrade("Cherney", "Math", 2017, "Assignment", "Fareeha", 10);
		assertFalse(this.instructor.getGrade("Math", 2017, "Assignment", "Fareeha") == 10);
	}
	
	

	
		
		
		
		
}
