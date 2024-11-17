package hmsProject;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Administrator class that inherits from User class
 */
public class Administrator extends User {
	private String name;
	private String gender;
	private int age;

	/**
	 * Administrator constructor.
	 * @param uid unique user identifier
	 * @param pw password
	 * @param name name
	 * @param gender gender
	 * @param age age
	 */
	public Administrator(String uid, String pw, String name, String gender, int age) {
		super(uid, pw);
		this.name=name;
		this.gender=gender;
		this.age=age;
	}

	/**
	 * Admin details in a row.
	 * @return admin detail row.
	 */
    public String toString() {
        return String.format("Administrator [Name: %s, ID: %s, Age: %d, Gender: %s]", getName(), gethID(), getAge(), getGender());
    }

	/**
	 * Name getter.
	 * @return name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Name setter.
	 * @param name new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Age getter.
	 * @return age.
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Age setter.
	 * @param age new age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Gender getter.
	 * @return gender.
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Gender setter.
	 * @param gender new gender
	 */
	public void setGender(String gender) {	
		this.gender = gender;
	}

	/**
	 * Admin user interface.
	 * @param adminCont Admin Controller
	 * @param sc Scanner class for input
	 */
	public void userInterface(AdministratorController adminCont, Scanner sc) {
		//StockRequest sr = new StockRequest("Aspirin", 20, "pending");
		//StockRequest sr2 = new StockRequest("Paracetamol", 20, "pending");
		//adminCont.getInventory().addPendingStockReq(sr);
		//adminCont.getInventory().addPendingStockReq(sr2);

		int choice = 0;
		while(choice != 5) {
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
				adminCont.saveData();
				break;
			case 5:
				System.out.println("Logging out as Administrator\n");
				break;
			default:
				System.out.println("Input out of range");
				break;
			}
		}
		adminCont.saveData();
	}

	/**
	 * View, add, update, remove staffs.
	 * @param adminCont Admin Controller
	 * @param sc Scanner class for input
	 */
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
					staffSys.filerStaff(sc, staffList);
					break;
				case 2: //Add new Staff
					staffSys.addStaff(adminCont,sc, staffList);
					break;
				case 3: //Update Existng Staff
					staffSys.updateStaff(adminCont, sc, staffList);
					break;
				case 4: //Remove Staff
					staffSys.removeStaff(adminCont, sc, staffList);
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

	/**
	 * Print real-time scheduled and completed appointments.
	 * @param adminCont Admin Controller
	 */
	public void apptOp(AdministratorController adminCont) {
		ArrayList<Appointment> scheduledAppt = adminCont.getApptSys().getSchAppt();
		if (scheduledAppt.size() == 0) {
			System.out.println("There are no scheduled appointments.");
			//return;
		}
		else {
			System.out.println("List of Scheduled Appointments: ");
			System.out.printf("%-5s %-5s %-5s %-15s %-10s %-10s \n", "Index", "Patient ID", "Doctor ID", "Status", "Date", "Time");
			System.out.println("===========================================================================================");
	
			for (int i = 0; i < scheduledAppt.size(); i++) {
				Appointment appt = scheduledAppt.get(i);
				System.out.printf("%-5s %-5s %-5s %-15s %-10s %-10s \n", 
				i + 1, 
				appt.getPatient().gethID(),
				appt.getDoctor().gethID(),
			    appt.getStatus(),
				appt.getDate(),
				appt.getTime());
				appt.getApptOutcomeRecord().printAll();
			}
		}
		
		ArrayList<Appointment> completedAppt = adminCont.getApptSys().getCompAppt();
		if (completedAppt.size() == 0) {
			System.out.println("There are no completed appointments.");
			return;
		}

		System.out.println("List of Completed Appointments: ");
		System.out.printf("%-5s %-5s %-5s %-15s %-10s %-10s \n", "Index", "Patient ID", "Doctor ID", "Status", "Date", "Time");
		System.out.println("===========================================================================================");

		for (int i = 0; i < completedAppt.size(); i++) {
			Appointment appt = completedAppt.get(i);
			System.out.printf("%-5s %-5s %-5s %-15s %-10s %-10s \n", 
			i + 1, 
			appt.getPatient().gethID(),
			appt.getDoctor().gethID(),
		    appt.getStatus(),
			appt.getDate(),
			appt.getTime());
			appt.getApptOutcomeRecord().printAll();
		}
		
	}

	/**
	 * View, change, add, remove medicine and change stock level and threshold.
	 * @param adminCont Admin Controller
	 * @param inventory Inventory
	 * @param sc Scanner class
	 */
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
				//sc.next();
			}		
			
			switch (choice) {
				case 1:	//Display all medicines in inventory & display alert if stock level is below threshold	
					inventory.viewInventory(0);
					break;
				case 2: //Change Medicine Name
					inventory.viewInventory(1);
					changeMedOption = inventory.selectMedicine(sc, "Select Medicine to change: ");
					//changeMedOption = selectMedicine(inventory, sc, "Select Medicine to change: ");
					
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
					changeMedOption=inventory.selectMedicine(sc, "Select Medicine to change: ");
					//changeMedOption = selectMedicine(inventory, sc, "Select Medicine to change: ");
					inventory.deleteMedicine(changeMedOption - 1);
					break;
				case 5: //Change Stock Level
					inventory.viewInventory(1);
					changeMedOption=inventory.selectMedicine(sc, "Select Medicine to change stock level: ");
					//changeMedOption = selectMedicine(inventory, sc, "Select Medicine to change stock level: ");
					newStockLevel = getValidIntInput(sc, "Input New Stock Level: ");

					while (inventory.getMedicineList().get(changeMedOption - 1).getStockThreshold() <= newStockLevel) { //Checks if input is valid
						System.out.println("Stock Level must be higher than Stock Alert!!!\n");
						newStockLevel = getValidIntInput(sc, "Input Stock new Level: ");
					}

					inventory.getMedicineList().get(changeMedOption - 1).setStock(newStockLevel);
					break;
				case 6: //Change Stock Alert
					inventory.viewInventory(1);
					inventory.selectMedicine(sc, "Select Medicine to change stock alert threshold: ");
					//changeMedOption = selectMedicine(inventory, sc, "Select Medicine to change stock alert threshold: ");

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
			adminCont.saveData();
		}
	}

	/**
	 * Approve medicine replenishment.
	 * @param adminCont Admin Controller
	 */
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

	/**
	 * Integer input checking.
	 * @param sc Scanner class
	 * @param prompt What are you inputting int for
	 * @return the input integer.
	 */
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

	/**
	 * Check if input contains only alphabets and spaces.
	 * @param sc Scanner class
	 * @param prompt What you are inputting for
	 * @return the input String.
	 */
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

	/**
	 * Admin menu.
	 */
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
