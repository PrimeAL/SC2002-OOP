package hmsProject;

import java.util.ArrayList;
import java.util.Comparator;
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

		/*@Override
    public String toString() {
        return String.format("[Name: %s, ID: %s]", getName(), gethID());
    }*/

	@Override
    public String toString() {
        return String.format("Administrator [Name: %s, ID: %s]", getName(), gethID());
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {	
		this.gender = gender;
	}
	
	public void userInterface(AdministratorController adminCont, Scanner sc) {
		StockRequest sr = new StockRequest("Aspirin", 20, "pending");
		StockRequest sr2 = new StockRequest("Paracetamol", 20, "pending");
		adminCont.getInventory().addPendingStockReq(sr);
		adminCont.getInventory().addPendingStockReq(sr2);

		int choice = 0;
		while(choice != 6) {
			this.displayMenu();

			while (true) {
				System.out.print("Input Choice: ");
				if (sc.hasNextInt()) { // check if input is an integer
					choice = sc.nextInt();
					sc.nextLine();	// Clear the newline character after the integer
					if (choice >= 1 && choice <= 6) {break;} // Exit loop if input is valid within the valid range
					else { System.out.println("Input out of range. Please enter a number between 1 and 6.");}
				}
				else { 
					System.out.println("Invalid input. Please enter a number between 1 and 6.");
					sc.nextLine();
				}
			}	

			switch(choice) {
			case 1:
				this.manageStaff(adminCont, sc);
				break;
			case 2:
				this.apptOp(adminCont);
				break;
			case 3:
				Inventory inventory = adminCont.getInventory();
				this.manageMedicines(adminCont,inventory,sc);
				break;
			case 4: 
				this.approveReplenishment(adminCont);
			case 5:
				System.out.println("Logging out as Administrator\n");
			default:
				System.out.println("Input out of range");
				break;
			}
		}
	}

	public void manageStaff(AdministratorController adminCont, Scanner sc) {
		ArrayList<User> staffList = adminCont.getStaffs();
		StaffSystem staffSys = new StaffSystem(staffList);
		int choice = 0;
		while (choice != 5) {
			System.out.println(
				"""
				\n=======================================================================================
				Manage Staff Menu
				=======================================================================================
				1. View Staff List
				2. Add New Staff
				3. Update Staff
				4. Remove Staff
				5. Exit Staff Menu
				"""
			);

			while (true) {
				System.out.print("Input Choice: ");
				if (sc.hasNextInt()) { // check if input is an integer
					choice = sc.nextInt();
					sc.nextLine();	// Clear the newline character after the integer
					if (choice >= 1 && choice <= 5) {break;} // Exit loop if input is valid within the valid range
					else { System.out.println("Invalid input. Please enter a number between 1 and 5.");}
				}
				else { 
					System.out.println("Invalid input. Please enter a number.");
					sc.nextLine();
				}
			}

			switch (choice) {
				case 1: //View Staff List
					staffSys.printStaff(staffList);
					break;
				case 2: //Add new Staff
					staffSys.addStaff(sc, staffList);
					break;
				case 3: //Update Existng Staff
					break;
				case 4: //Remove Staff
					staffSys.removeStaff(sc, staffList);
					break;
				case 5: //Logout
					System.out.println("Exiting Manage Staff Menu...");
					break;
				default:
					System.out.println("Input out of range");
					break;
			}
		}
	}

	public void apptOp(AdministratorController adminCont) {
		ArrayList<Appointment> scheduledAppt = adminCont.getApptSys().getSchAppt();
		if (scheduledAppt.size() == 0) {
			System.out.println("There are no scheduled appointments.");
			return;
		}

		System.out.println("List of Scheduled Appointments: ");
		System.out.printf("%-5s %-5s %-5s %-15s %-10s %-10s %-20s%n", "Index", "Patient ID", "Doctor ID", "Status", "Date", "Time", "Outcome Record");
		System.out.println("===========================================================================================");

		for (int i = 0; i < scheduledAppt.size(); i++) {
			Appointment appt = scheduledAppt.get(i);
			System.out.printf("%-5s %-5s %-5s %-15s %-10s %-10s %-20s%n", 
			i + 1, 
			appt.getPatient().gethID(),
			appt.getDoctor().gethID(),
		    appt.getStatus(),
			appt.getDate(),
			appt.getTime(),
			appt.getApptOutcomeRecord());
		}
	}

	public void manageMedicines(AdministratorController adminCont,Inventory inventory, Scanner sc) {
		int choice = 0, changeMedOption = 0, newStockLevel = 0, newStockThreshold = 0;
	
		while (choice != 7) {
			System.out.println(
				"""
				\n=======================================================================================
				Manage Medicines Menu
				=======================================================================================
				1. View Medicines
				2. Change Medicine Name
				3. Add New Medicine
				4. Remove Medicine
				5. Change Stock Level
				6. Change Stock Alert Threshold
				7. Logout
				""");

			while (true) {
				System.out.println("Input Choice: ");
				if (sc.hasNextInt()) { // check if input is an integer
					choice = sc.nextInt();	
					if (choice >= 1 && choice <= 7) {break;} // Exit loop if input is valid within the valid range
					else { System.out.println("Invalid input. Please enter a number between 1 and 7.");}
				}
				else { System.out.println("Invalid input. Please enter a number.");}
				sc.next();
			}		
			
			switch (choice) {
				case 1:	//Display all medicines in inventory & display alert if stock level is below threshold	
					inventory.viewInventory(0);
					break;
				case 2: //Change Medicine Name
					inventory.viewInventory(1);
					changeMedOption = selectMedicine(inventory, sc, "Select Medicine to change: ");
					
					String newName = getValidAlphabeticString(sc, "Enter medicine name (alphabets only): ");
					inventory.changeMedicineName(changeMedOption - 1, newName);
					break;
				case 3: //Add New Medicine
					String newMedName = getValidAlphabeticString(sc, "Enter medicine name (alphabets only): ");
					newStockLevel = getValidIntInput(sc, "Input Stock Level: ");
					newStockThreshold = getValidIntInput(sc, "Input Stock Alert Threshold: ");
					
					while (newStockLevel <= newStockThreshold) { //Checks if input is valid
						System.out.println("Stock Level must be higher than Stock Alert!!!\n");
						newStockLevel = getValidIntInput(sc, "Input Stock Level: ");
						newStockThreshold = getValidIntInput(sc, "Input Stock Alert Threshold: ");
					}
					
					Medicine newMedicine = new Medicine(newMedName, newStockLevel, newStockThreshold);
					inventory.addMedicine(newMedicine);
					break;
				case 4: //Remove Medicine
					inventory.viewInventory(1);
					changeMedOption = selectMedicine(inventory, sc, "Select Medicine to remove: ");
					inventory.deleteMedicine(changeMedOption - 1);
					break;
				case 5: //Change Stock Level
					inventory.viewInventory(1);

					changeMedOption = selectMedicine(inventory, sc, "Select Medicine to change stock level: ");
					newStockLevel = getValidIntInput(sc, "Input New Stock Level: ");

					while (inventory.getMedicineList().get(changeMedOption - 1).getStockThreshold() <= newStockLevel) { //Checks if input is valid
						System.out.println("Stock Level must be higher than Stock Alert!!!\n");
						newStockLevel = getValidIntInput(sc, "Input Stock new Level: ");
					}

					inventory.getMedicineList().get(changeMedOption - 1).setStock(newStockLevel);
					break;
				case 6: //Change Stock Alert
					inventory.viewInventory(1);
					changeMedOption = selectMedicine(inventory, sc, "Select Medicine to change stock alert threshold: ");

					System.out.println("Input New Stock Threshold: ");
					newStockThreshold = getValidIntInput(sc, "Input New Stock Threshold: ");
					
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
			adminCont.saveInventory(inventory);
		}
	}

	public int selectMedicine(Inventory inventory, Scanner sc, String prompt) { // Checks if medicine index is valid
		int option = 0;
	
		while (true) {
			System.out.print(prompt);
			//sc.nextLine();
			
			if (sc.hasNextInt()) {
				option = sc.nextInt();
				sc.nextLine(); // Clear the newline character after reading the integer
				
				if (option <= 0 || option > inventory.getMedicineList().size()) {
					System.out.println("Invalid Input. Input out of Range.");
				} else {
					break; // Valid input
				}
			} else {
				System.out.println("Invalid Input. Please enter a number.");
				sc.nextLine();
			}
		}
		return option;
	}

	public void approveReplenishment(AdministratorController adminCont) {
		Inventory inventory = adminCont.getInventory();

		if (inventory.getPendingStockReq().size() == 0) {
			System.out.println("There are no pending replenishment requests.\n");
			return;
		}

		for (int i = 0; i < inventory.getPendingStockReq().size(); i++) {
			StockRequest stockRequest = inventory.getPendingStockReq().get(i);

			for (int j = 0; j < inventory.getMedicineList().size(); j++) {
				if (stockRequest.getMedicineName().equalsIgnoreCase(inventory.getMedicineList().get(j).getName())) {
					inventory.getMedicineList().get(j).setStock(inventory.getMedicineList().get(j).getStock() + stockRequest.getStockAmt());
					stockRequest.setStatus("completed");
					inventory.removePendingStockReq(stockRequest);
					inventory.addCompletedStockReq(stockRequest);
					System.out.println(stockRequest.getMedicineName() + " replenishment request completed.");
					break;
				}
			}
		}
	}

	public int getValidIntInput(Scanner sc, String prompt) {
        int input = 0;

        while (true) {
            System.out.print(prompt);
            
            // Check if input is a valid integer
            if (sc.hasNextInt()) {
                input = sc.nextInt();
				sc.nextLine();
				if (input <= 0) {
					System.out.println("Invalid input. Input must be a positive integer! ");
				}
				else break;
            } else {
                System.out.println("Invalid input. Input must be an integer! ");
                sc.nextLine(); // Clear the invalid input
            }
        }
        return input;
    }

	public String getValidAlphabeticString(Scanner sc, String prompt) {
		String input;
		while (true) {
			System.out.print(prompt);
			input = sc.nextLine();
		
			if (input.matches("^[a-zA-Z\\s]+$")) {// Check if input contains only alphabets and spaces
				break; // Exit loop if input is valid
			} else {
				System.out.println("Invalid input. Please enter alphabets only, without numbers.");
			}
		}
		return input;
	}

	public void displayMenu() {
		System.out.println(
			"""
			\n=======================================================================================
			Administrator UI
			=======================================================================================
			1. Manage Staff
			2. View Appointments 
			3. Manage Medicines
			4. Approve Replenishment Requests
			5. Logout
			""");
	}
}