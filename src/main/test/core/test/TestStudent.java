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

public class TestStudent {
	 private IAdmin admin;
	 private IStudent student; 
	 private IInstructor instructor; 
	    

		@Before
		public void setup() {
		    this.admin = new Admin();
		    this.student = new Student();
		    this.instructor = new Instructor();
		}
		
		// register student: provided this class exists and has not met its enrollment capacity.
		// registerForClass 
		// class does not exist 
		// past enrollment cap 
		
		
		@Test 
		public void registerClassDoesntExist() {
			this.student.registerForClass("Fareeha", "DNE", 2017);
			assertFalse(student.isRegisteredFor("Fareeha", "DNE", 2017));
		}
		
		//checks for when class exists & within capacity
		@Test 
		public void registerClassExists() {
			this.admin.createClass("Math", 2017, "Cherney", 1);
			this.student.registerForClass("Fareeha", "Math", 2017);
			assertTrue(student.isRegisteredFor("Fareeha", "Math", 2017));
		}
		
		//BUG
		@Test 
		public void registerClassPastEnrollment() {
			this.admin.createClass("Math", 2017, "Fareeha", 1);
			this.student.registerForClass("Fareeha", "Math", 2017);
			this.student.registerForClass("Ahmed", "Math", 2017);
			assertFalse(student.isRegisteredFor("Ahmed", "Math", 2017));
		}
		
		
		// drop class: provided the student is registered and the class has not ended
		// dropClass
		// unenrolled student --> nothing will happen
		// class has ended --> can't make a class in the past anyway 
		
		
		
		// submit hw: provided homework exists, student is registered and the class is 
		// taught in the current year
		// submitHomework 
		// hw does not exist
		// student is not registered
		// class is taught in future 
		@Test 
		public void HWDNE() {
			this.admin.createClass("Math", 2017, "Cherney", 2);
			this.student.registerForClass("Fareeha", "Math", 2017);
			this.student.submitHomework("Fareeha", "Homework", "No", "Math", 2017);
			assertFalse(student.hasSubmitted("Fareeha", "Homework", "Math", 2017));
		}
		//BUG
		@Test
		public void HWEStudentNotReg() {
			this.admin.createClass("Math", 2017, "Cherney", 2);
			this.instructor.addHomework("Cherney", "Math", 2017, "Homework");
			this.student.submitHomework("Fareeha", "Homework", "No", "Math", 2017);
			assertFalse(student.hasSubmitted("Fareeha", "Homework", "Math", 2017));
		}
		//BUG
		@Test 
		public void classInFuture() {
			this.admin.createClass("Math", 2018, "Cherney", 2);
			this.student.registerForClass("Fareeha", "Math", 2017);
			this.instructor.addHomework("Cherney", "Math", 2018, "Homework");
			this.student.submitHomework("Fareeha", "Homework", "No", "Math", 2018);
			assertFalse(student.hasSubmitted("Fareeha", "Homework", "Math", 2018));
		}
}
