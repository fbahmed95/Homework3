package core.api;

import core.api.impl.Admin;

public class CourseManager {

	private Admin admin;
	
	public CourseManager(Admin admin) {
		this.admin = admin;
	}

	public void createClass(String name, int year) {
		this.admin.createClass(name, year, "", 1);
		// relies on Admin to be correct
		// capture behavior and override
		//if (this.admin.classExists(name, year)) {
		//	mockito returns false when 2016
		//}
	}

	public boolean classExists(String name, int year) {
		return this.admin.classExists(name, year);
	}

	public void setCapacity(String name, int year, int capacity) {
		this.admin.changeCapacity(name, year, capacity);
	}
}
