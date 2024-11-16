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

	//FOR ADMIN
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

	@Override
    public String toString() {
        return String.format("Pharmacist [Name: %s, ID: %s, Age: %d, Gender: %s]", getName(), gethID(), getAge(), getGender());
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
		createStockReq(pharmacistCont.getInventory(), sc);
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

	public void createStockReq(Inventory inventory, Scanner sc) {
		
		inventory.viewInventory(1);
		int changeMedOption = selectMedicine(inventory, sc, "Select Medicine to change: ");

		String newMedName = inventory.getMedicineList().get(changeMedOption-1).getName();
		int stockAmt = getValidIntInput(sc, "Input Stock Amount to replenish: ");
		StockRequest stockReq = new StockRequest(newMedName, stockAmt, "Pending");
		inventory.addPendingStockReq(stockReq);
	}

	public int getValidIntInput(Scanner sc, String prompt) {
        int input = 0;

        while (true) {
            System.out.print(prompt);
            
            // Check if input is a valid integer
            if (sc.hasNextInt()) {
                input = sc.nextInt();
				sc.nextLine();
                break; // Exit loop if integer is valid
            } else {
                System.out.println("Invalid input. Input must be an integer! ");
                sc.nextLine(); // Clear the invalid input
            }
        }
        return input;
    }

	public String getValidAlphabeticString(Scanner sc, String prompt) {
		String input;

		if (sc.hasNextLine()) {
			sc.nextLine();
		}

		while (true) {
			System.out.print(prompt);
			input = sc.nextLine();
			// Check if input contains only alphabets
			if (input.matches("[a-zA-Z]+")) {break;} // Exit loop if input is valid
			else { 
				System.out.println("Invalid input. Please enter alphabets only.");
			}
		}
		
		return input;
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

}