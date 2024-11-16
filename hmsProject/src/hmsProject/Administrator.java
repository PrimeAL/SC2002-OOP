package hmsProject;

import java.util.Scanner;

/**
 * Administrator class that inherits from User class
 */
public class Administrator extends User {
	private String name;
	private String gender;
	private int age;

	/**
	 * Administrator class constructor
	 * @param uid
	 * @param pw
	 * @param name
	 * @param gender
	 * @param age
	 */
	public Administrator(String uid, String pw, String name, String gender, int age) {
		super(uid, pw);
		this.name=name;
		//Remember getter setters
		this.gender=gender;
		this.age=age;
	}

	/**
	 * Overrides userInterface method from User class to use Administrator-specific interface
	 * @param adminCont
	 * @param sc
	 */
	public void userInterface(AdminController adminCont,Scanner sc) {
		// TODO - implement Administrator.userInterface
		for(Appointment appt: adminCont.getScheduledAppts()) { //testing
			System.out.println("Date:"+appt.getDate()+"|Time:"+appt.getTime()+"|Status:"+appt.getStatus());
		} 
	}

	/**
	 * ToFill
	 */
	public void manageStaff() {
		// TODO - implement Administrator.manageStaff
		throw new UnsupportedOperationException();
	}

	/**
	 * ToFill
	 */
	public void apptOp() {
		// TODO - implement Administrator.apptOp
		throw new UnsupportedOperationException();
	}

}