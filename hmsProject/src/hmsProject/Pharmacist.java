package hmsProject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Pharmacist extends User implements Serializable {
	private String name;
	private String gender;
	private int age;
	private ArrayList<OutcomeRecord> apptOutcomeRecords;
	

	public Pharmacist(String uid, String pw, String name, String gender, int age) {
		super(uid, pw);
		this.name=name;
		this.apptOutcomeRecords = new ArrayList<OutcomeRecord>();
		
		//Remember getter setters
		this.gender=gender;
		this.age=age;
	}

	public ArrayList<OutcomeRecord>getOutcomeRecords(){
		return apptOutcomeRecords;
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


	public void userInterface(PharmacistController pharmacistCont, Scanner sc) {
		System.out.println("\nPharmacist UI");
		int choice = 0;
	
		while (choice != 5) {
			displayMenu();  

			// Validate input to ensure it’s an integer
			if (sc.hasNextInt()) {
				choice = sc.nextInt();
				sc.nextLine();  // Clear the newline character from the buffer
	
				switch (choice) {
					case 1:
						System.out.println("Option 1 selected");
						//pharmacistCont.getApptSys().getOutcomeRec();
						viewApptOutcomeRec();
						break;
					case 2:
						System.out.println("Option 2 selected");
						// Add functionality for Option 2
						break;
					case 3:
						System.out.println("Option 3 selected");
						// Add functionality for Option 3
						break;
					case 4:
						System.out.println("Option 4 selected");
						// Add functionality for Option 4
						break;
					case 5:
						System.out.println("Logging Out.");
						break;
					default:
						System.out.println("Invalid input. Please enter a number between 1 and 5.");
						break;
				}
			} else {
				System.out.println("Invalid input. Please enter a number.");
				sc.next();  // Clear the invalid input
			}
		}
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

	public void viewApptOutcomeRec() {
		if (apptOutcomeRecords.isEmpty()) {
			System.out.println("No outcome records available.");
			return;  // Exit the method early if there are no records
		}
	
		int cnt = 1;
		for (OutcomeRecord record : apptOutcomeRecords) {
			System.out.println(cnt + ". Date: " + record.getDateOfAppointment());
			System.out.println("Service Provided: " + record.getServiceProvided());
			record.printMeds();  // Assuming this method prints each medication's details
			System.out.println("Consultation Note: " + record.getConsultationNote());
			System.out.println("-------------------------------");
			cnt++;
		}
	}

}