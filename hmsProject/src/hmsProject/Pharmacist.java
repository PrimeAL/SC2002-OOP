package hmsProject;

import java.util.Scanner;

public class Pharmacist extends User {
	private String name;
	private String gender;
	private int age;

	public Pharmacist(String uid, String pw, String name, String gender, int age) {
		super(uid, pw);
		this.name=name;
		//Remember getter setters
		this.gender=gender;
		this.age=age;
	}

	/**
	 * 
	 * @param apptSys
	 * @param inven
	 */
	/*public void userInterface(AppointmentSystem apptSys, Inventory inven) {
		// TODO - implement Pharmacist.userInterface
		throw new UnsupportedOperationException();
	}*/


	public void userInterface(PharmacistController pharmacistCont, Scanner sc){
		System.out.println("\nPharmacist UI");
		displayMenu();
	}

	public void displayMenu(){
		String text = 
			"1. View appointment outcome record\n"
    		+"2. Update prescription (Appt Outcome Record)\n"
    		+"3. View medicine inventory\n"
			+"4. Request for replenishment\n"
			+"5. Log out\n\n"; 
		System.out.print(text);
	}

	public void apptOp() {
		// TODO - implement Pharmacist.apptOp
		throw new UnsupportedOperationException();
	}

}