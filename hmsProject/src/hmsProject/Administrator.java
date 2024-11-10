package hmsProject;

import java.util.Scanner;
import java.util.ArrayList;


public class Administrator extends User {	
	private String name;
	private ArrayList<Appointment> apptList;
	private ArrayList<Medicine> medicineArray;

	public Administrator(String uid, String pw, String name, String role, String gender, int age) {
		super(uid, pw);
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param apptSys
	 * @param inven
	 * @param users
	 */

	 //AppointmentSystem apptSys, Inventory inven, User[] users
	 //	public void userInterface(AdministratorController adminCont, AppointmentSystem apptSys, Inventory inven, User[] users, Scanner sc)
	public void userInterface(AdministratorController adminCont, Scanner sc) {
		// TODO - implement Administrator.userInterface
		System.out.println("\n Administrator UI\n");
		System.out.println("\n ================================\n");
		int choice = 0;
		while(choice != 6) {
			this.displayMenu();
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				this.changePW();
				break;
			case 2:
				this.manageStaff();
				break;
			case 3:
				this.apptOp();
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
		System.out.println("1. Change Password\n2. Manage Staff\n3. View Appointments\n" 
		+ "4. Manage Medicines\n5. Approve Replenishment Requests\n6. Logout\n");
	}

	public void changePW() {
		// TODO - implement Administrator.changePW
		throw new UnsupportedOperationException();
	}

	public void manageStaff() {
		// TODO - implement Administrator.manageStaff
		throw new UnsupportedOperationException();
	}

	public void apptOp() {
		// TODO - implement Administrator.apptOp
		throw new UnsupportedOperationException();
	}

	public void manageMedicines(Inventory inventory, Scanner sc) {
		// TODO - implement Administrator.manageMedicines
		int choice = 0, changeMed = 0, newStockLevel = 0;
		//Medicine[] tempArray = inventory.getMedicineList();
	
		while (choice != 7) {
			System.out.println(
				"""
				1. View Medicines
				2. Change Medicine Name
				3. Add New Medicine
				4. Remove Medicine"
			    5. Change Stock Level
				6.Change Stock Alert Threshold
				7. Exit
				""");
			System.out.println("Input Choice: ");
			choice = sc.nextInt();		
			
			switch (choice) {
				case 1:	//Display all medicines in inventory & display alert if stock level is below threshold	
					inventory.viewStock();
					inventory.checkStock();
					break;
				case 2: //Change Medicine Name
					inventory.viewStock();
					System.out.println("\nSelect Medicine to change: ");
					int option = sc.nextInt();

					while (option <= 0 || option > inventory.getMedicineList().size()) {
						System.out.println("\nInvalid Input. Select Medicine to change: ");
						option = sc.nextInt();
					}

					System.out.println("\nInput New Name: ");
					String newName = sc.next();
					inventory.changeMedicineName(option - 1, newName);
					break;
				case 3: //Add New Medicine
					System.out.println("\nInput Name of Medicine: ");
					String newMedName = sc.next();

					System.out.println("\nInput Stock Level: ");
					int newStock = sc.nextInt();

					System.out.println("\nInput Stock Alert Threshold: ");
					int newStockAlert = sc.nextInt();

					
					while (newStock <= newStockAlert) { //check if new stock level is lower or equal to alert
						System.out.println("Stock Level must be higher than Stock Alert!!!\n");
						System.out.println("Input Stock Level: ");
						newStock = sc.nextInt();

						System.out.println("\nInput Stock Alert: ");
						newStockAlert = sc.nextInt();
					}

					inventory.addMedicine(newMedName, newStockAlert, newStock);
					break;
				case 4: //Remove Medicine
					inventory.viewStock();

					System.out.println("Select Medicine to remove: ");
					int removeMed = sc.nextInt();

					while (removeMed > inventory.getMedicineList().size() || removeMed <= 0) { //Checks if input is valid
						System.out.println("Input Valid Number: ");
						removeMed = sc.nextInt();
					}
					inventory.deleteMedicine(removeMed - 1);
					break;
				case 5: //Change Stock Level
					inventory.viewStock();

					System.out.println("Select Medicine to change stock level: ");
					changeMed = sc.nextInt();

					System.out.println("Input New Stock Level: ");
					newStockLevel = sc.nextInt();
					while (newStockLevel <= inventory.getMedicineList().get(changeMed - 1).getStockThreshold()) { //Checks if input is valid
						System.out.println("Stock Level must be higher than Stock Alert!!!\n");
						System.out.println("Input Stock Level: ");
						newStockLevel = sc.nextInt();
					}
					medicineList.get(changeMed).setStock(newStockLevel);
					break;
				case 6: //Change Stock Alert
					displayMedicines(medicineList);
					System.out.println("Select Medicine to change stock alert threshold: ");
					changeMed = sc.nextInt();

					System.out.println("Input New Threshold: ");
					int threshold = sc.nextInt();
					medicineList.get(changeMed - 1).setStockThreshold(threshold);
					break;
				case 7: //Exit to Admin Menu
					System.out.println("\nExiting Manage Medicine Menu...");
					break;
				default:
					System.out.println("\nInput out of range");
					break;
			}
		}
	}

	public void checkvalidStock(int stock) {
		while (newStockLevel <= inventory.getMedicineList().get(changeMed - 1).getStockThreshold()) { //Checks if input is valid
			System.out.println("Stock Level must be higher than Stock Alert!!!\n");
			System.out.println("Input Stock Level: ");
			newStockLevel = sc.nextInt();
		}
	}
	public void displayMedicines(ArrayList<Medicine> medicineList) {
		System.out.println("Medicine Name\t Stock Level\t Stock Alert\n"); //View Medicine

		for (int i = 0; i < medicineList.size(); i++){ //Display medicines and their current stock levels
			System.out.println((i + 1) + ". " + medicineList.get(i).getName() + "\t" + medicineList.get(i).getStock()
			+ "\t" + medicineList.get(i).getStockThreshold());
		}
	}
	public void approveReplenishment() {
		// TODO - implement Administrator.approveReplenishment
		throw new UnsupportedOperationException();
	}


}