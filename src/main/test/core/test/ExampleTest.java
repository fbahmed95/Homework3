package core.test;

import core.api.IAdmin;
import core.api.impl.Admin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vincent on 23/2/2017.
 */
public class ExampleTest {

    private IAdmin admin;

    	// before every unit test is done, it does something 
    // before the test, i want a new admin 
    // can also use BeforeClass <-- risk of running something once for all unit tests:
    // is that some field could be instantiated, modified, and a modified field could 
    // be passed to another test. 
    // make a test for every class 
    @Before
    public void setup() {
        this.admin = new Admin();
    }

    @Test
    public void testMakeClass() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testMakeClass2() {
        this.admin.createClass("Test", 2016, "Instructor", 15);
        assertFalse(this.admin.classExists("Test", 2016));
    }
}

/* for TestAdmin 
@Test
public void instructorCourseLimitCornerCase(){
	//two courses for 1 instructor
	 * this.admin.createClass("Test", 2017, "Instructor", 15);
	 * this.admin.createClass("Test2", 2017, "Instructor", 15);
		assertTrue(this.admin.classExists("Test2", 2017));
		}
		
public void instructorCourseLimitViolated(){
	//three courses for 1 instructor --> is not allowed
	   this.admin.createClass("Test", 2017, "Instructor", 15);
	    this.admin.createClass("Test2", 2017, "Instructor", 15);
		this.admin.createClass("Test3", 2017, "Instructor", 15);
		assertFalse(this.admin.classExists("Test3", 2017));
		}
	 */
