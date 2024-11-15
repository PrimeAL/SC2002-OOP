package hmsProject;

import java.util.ArrayList;
import java.util.Scanner;

public class Administrator extends User {
	private String name;
	private String gender;
	private int age;

	public Administrator(String uid, String pw, String name, String gender, int age) {
		super(uid, pw);
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	
	public void userInterface(AdministratorController adminCont, Scanner sc) {
		// TODO - implement Administrator.userInterface
		System.out.println("Administrator UI");
		System.out.println("================================");
		int choice = 0;
		while(choice != 6) {
			this.displayMenu();
			System.out.println("Input Choice: ");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				this.changePW();
				break;
			case 2:
				this.manageStaff(sc);
				break;
			case 3:
				this.apptOp(adminCont.getApptSys());
				break;
			case 4:
				Inventory inventory = adminCont.getInventory();
				this.manageMedicines(inventory,sc);
				break;
			case 5: 
				this.approveReplenishment();
			case 6:
				System.out.println("Logging out as Administrator\n");
			default:
				System.out.println("Input out of range");
				break;
			}
		}
	}

	public void displayMenu() {
		System.out.println(
			"""
			1. Change Password
			2. Manage Staff
			3. View Appointments 
			4. Manage Medicines
			5. Approve Replenishment Requests
			6. Logout
			""");
	}

	public void changePW() {
		// TODO - implement Administrator.changePW
		throw new UnsupportedOperationException();
	}

	public void manageStaff(Scanner sc) {
		int choice = 0;
		System.out.println(
			"""
			\n========================
			Manage Staff Menu
			=========================
			1. View Staff List
			2. Add New Staff
			3. Update Staff
			4. Remove Staff
			"""
		);
		System.out.println("Input Choice: ");
		choice = sc.nextInt();

		switch (choice) {
			case 1: //View Staff List
				break;
			case 2: //Add new Staff
				break;
			case 3: //Update Existng Staff
				break;
			case 4: //Remove Staff
				break;
			default:
				System.out.println("Input out of range");
				break;

		}

	}

	public void apptOp(AppointmentSystem apptSys) {
		System.out.println("List of Scheduled Appointments: ");
		ArrayList<Appointment> scheduledAppt = apptSys.getScheduledAppt();
		Appointment appt = null;
		System.out.println("Patient ID\tDoctor ID\tStatus\tDate\tTime\tOutcome Record");

		for (int i = 0; i < scheduledAppt.size(); i++) {
			appt = scheduledAppt.get(i);
			if (appt != null) {
				System.out.println(appt.getPatient().gethID() + "\t" + appt.getDoctor().gethID() + "\t" 
				+ appt.getStatus() + "\t" + appt.getDate() + "\t" + appt.getTime() + "\t" + appt.getApptOutcomeRecord());
			}
		}
	}

	public void manageMedicines(Inventory inventory, Scanner sc) {
		int choice = 0, changeMedOption = 0, newStockLevel = 0, newStockThreshold = 0;
	
		while (choice != 7) {
			System.out.println(
				"""
				\n========================
				Manage Medicines Menu
				========================
				1. View Medicines
				2. Change Medicine Name
				3. Add New Medicine
				4. Remove Medicine
				5. Change Stock Level
				6. Change Stock Alert Threshold
				7. Logout
				""");
			System.out.println("Input Choice: ");
			choice = sc.nextInt();		
			
			switch (choice) {
				case 1:	//Display all medicines in inventory & display alert if stock level is below threshold	
					inventory.viewInventory();
					break;
				case 2: //Change Medicine Name
					inventory.viewInventory();
					System.out.println("Select Medicine to change: ");
					changeMedOption = selectMedicine(inventory, sc, changeMedOption);
					
					System.out.println("Input New Name: ");
					String newName = sc.next();
					inventory.changeMedicineName(changeMedOption - 1, newName);
					break;
				case 3: //Add New Medicine
					System.out.println("Input Name of Medicine: ");
					String newMedName = sc.next();

					System.out.println("Input Stock Level: ");
					newStockLevel = sc.nextInt();

					System.out.println("Input Stock Alert Threshold: ");
					newStockThreshold = sc.nextInt();
					
					while (newStockLevel <= newStockThreshold) { //Checks if input is valid
						System.out.println("Stock Level must be higher than Stock Alert!!!\n");
						System.out.println("Input Stock Level: ");
						newStockLevel = sc.nextInt();

						System.out.println("Input Stock Alert Threshold: ");
						newStockThreshold = sc.nextInt();
					}
					
					Medicine newMedicine = new Medicine(newMedName, newStockLevel, newStockThreshold);
					inventory.addMedicine(newMedicine);
					break;
				case 4: //Remove Medicine
					inventory.viewInventory();
					System.out.println("Select Medicine to remove: ");
					changeMedOption = sc.nextInt();
					changeMedOption = selectMedicine(inventory, sc, changeMedOption);
					inventory.deleteMedicine(changeMedOption - 1);
					break;
				case 5: //Change Stock Level
					inventory.viewInventory();
					System.out.println("Select Medicine to change stock level: ");
					changeMedOption = sc.nextInt();
					changeMedOption = selectMedicine(inventory, sc, changeMedOption);
					System.out.println("Input New Stock Level: ");
					newStockLevel = sc.nextInt();

					while (inventory.getMedicineList().get(changeMedOption - 1).getStockThreshold() <= newStockLevel) { //Checks if input is valid
						System.out.println("Stock Level must be higher than Stock Alert!!!\n");
						System.out.println("Input Stock Level: ");
						newStockLevel = sc.nextInt();
					}

					inventory.getMedicineList().get(changeMedOption - 1).setStock(newStockLevel);
					break;
				case 6: //Change Stock Alert
					inventory.viewInventory();
					System.out.println("Select Medicine to change stock alert threshold: ");
					changeMedOption = selectMedicine(inventory, sc, changeMedOption);

					System.out.println("Input New Stock Threshold: ");
					newStockThreshold = sc.nextInt();
					
					while (inventory.getMedicineList().get(changeMedOption - 1).getStock() <= newStockThreshold) { //Checks if input is valid
						System.out.println("Stock Level must be higher than Stock Alert!!!\n");
						System.out.println("Input New Stock Threshold: ");
						newStockLevel = sc.nextInt();
					}
					inventory.getMedicineList().get(changeMedOption - 1).setStockThreshold(newStockThreshold);
					break;
				case 7: //Exit to Admin Menu
					System.out.println("Exiting Manage Medicine Menu...");
					break;
				default:
					System.out.println("Input out of range");
					break;
			}
		}
	}

	public int selectMedicine(Inventory inventory, Scanner sc, int option) { //Checks if medicine index is valid
		option = sc.nextInt();
		while (option <= 0 || option > inventory.getMedicineList().size()) {
			System.out.println("Invalid Input. Select Again: ");
			option = sc.nextInt();
		}
		return option;
	}

	public void approveReplenishment() {
		// TODO - implement Administrator.approveReplenishment
		throw new UnsupportedOperationException();
	}


}