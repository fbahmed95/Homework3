package core.test;
import core.api.IAdmin;
import core.api.impl.Admin;
import core.api.IStudent;
import core.api.impl.Student; 
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAdmin {
    private IAdmin admin;
    private IStudent student; 
    

	@Before
	public void setup() {
	    this.admin = new Admin();
	    this.student = new Student();
	}

	// TEST FOR year >= 2017 
	@Test
	public void testMakeClass() {
	    this.admin.createClass("Test", 2017, "Instructor", 15);
	    assertTrue(this.admin.classExists("Test", 2017));
	}

	// BUG: year < 2017 
	@Test
	public void testMakeClass2() {
	    this.admin.createClass("Test", 2016, "Instructor", 15);
	    assertFalse(this.admin.classExists("Test", 2016));
	}
	
	// TEST FOR no instructor can be assigned to more than 2 courses in a year 
	@Test
	public void instructorCourseLimitCornerCase(){
	//two courses for 1 instructor --> is allowed 
	  this.admin.createClass("Test", 2017, "Instructor", 15);
	  this.admin.createClass("Test2", 2017, "Instructor", 15);
	  assertTrue(this.admin.classExists("Test2", 2017));
	}
	
	// BUG: num courses per instructor can be more than 2
	@Test 
	public void instructorCourseLimitViolated(){
		//three courses for 1 instructor --> is not allowed
		this.admin.createClass("Test", 2017, "Instructor", 15);
		this.admin.createClass("Test2", 2017, "Instructor", 15);
		this.admin.createClass("Test3", 2017, "Instructor", 15);
		assertFalse(this.admin.classExists("Test3", 2017));
	}
	
	// TEST FOR CAPACITY  
	@Test 
	public void checkCapacityValid() {
		// cap > 0 
		this.admin.createClass("TestCap", 2017, "Instructor", 1);
		assertTrue(this.admin.classExists("TestCap", 2017));
	}
	
	//BUG: capacity can be 0
	@Test 
	public void checkCapacityViolatedGTZero() {
		// cap == 0 
		this.admin.createClass("TestCap", 2017, "Instructor", 0);
		assertFalse(this.admin.classExists("TestCap", 2017));
	}
	
	// BUG: can be less than 0 
	@Test
	public void checkCapacityViolated() {
		// cap < 0 
		this.admin.createClass("TestCap", 2017, "Instructor", -1); 
		assertFalse(this.admin.classExists("TestCap", 2017));
		
	}
	
	// TEST FOR CHANGE CAPACITY 
	// BUG 
	@Test 
	public void checkChangeCap() {
		this.admin.createClass("Math", 2017, "Cherney", 2);		
		this.student.registerForClass("sponge", "Math", 2017);
		this.student.registerForClass("bob", "Math", 2017);
		this.student.registerForClass("square", "Math", 2017);
		this.student.registerForClass("pants", "Math", 2017);
		this.admin.changeCapacity("Math", 2017, 3);
		assertFalse(admin.getClassCapacity("Math", 2017) == 3);	
	}
	
	//BUG 
	@Test 
	public void checkChangeCapToInvalidCap() {
		this.admin.createClass("Math", 2017, "Cherney", 2);		
		this.student.registerForClass("bayboo", "Math", 2017);
		this.student.registerForClass("bayboo1", "Math", 2017);
		this.admin.changeCapacity("Math", 2017, 0);
		assertFalse(admin.getClassCapacity("Math", 2017) == 0);	
	}
	
	//BUG
	@Test 
	public void checkChangeCapWithoutEnrolling() {
		this.admin.createClass("Math", 2017, "Cherney", 2);		
		this.admin.changeCapacity("Math", 2017, 0);
		assertFalse(admin.getClassCapacity("Math", 2017) == 0);	
	}

	// TEST FOR class Name/year pair must be unique 
	@Test 
	public void checkUnique() {
		this.admin.createClass("test", 2017, "Instructor", 1); 
		this.admin.createClass("test", 2017, "Instructor", 1);
		assertFalse(this.admin.classExists("test", 2017)); 
	}
	
	//TEST FOR NULL --> then changing things
	
	
	
	
	

}


