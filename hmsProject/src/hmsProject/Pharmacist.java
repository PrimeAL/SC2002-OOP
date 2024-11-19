package hmsProject;

import java.util.Scanner;

/**
 * Pharmacist subclass of User.
 */
public class Pharmacist extends User {
	private String name;
	private String gender;
	private int age;

	/**
	 * Name getter.
	 * @return pharmacist name.
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
	 * Pharmacist details in a row.
	 * @return pharmacist details row.
	 */
    public String toString() {
        return String.format("Pharmacist [Name: %s, ID: %s, Age: %d, Gender: %s]", getName(), gethID(), getAge(), getGender());
    }

	/**
	 * Pharmacist constructor.
	 * @param uid unique user identifier
	 * @param pw password
	 * @param name pharmacist name
	 * @param gender gender
	 * @param age age
	 */
	public Pharmacist(String uid, String pw, String name, String gender, int age) {
		super(uid, pw);
		setName(name);
		setGender(gender);
		setAge(age);
	}

	/**
	 * Pharmacist user interface.
	 * @param pharmacistCont Pharmacist Controller
	 * @param sc Scanner class
	 */
	public void userInterface(PharmacistController pharmacistCont, Scanner sc) {
		int choice = 0;
		while(choice!=5) {
			try {
				displayMenu();
				choice = sc.nextInt();
				sc.nextLine();

				switch (choice) {
					case 1:
						System.out.println("Option 1 selected");
						viewApptOutcomeRec(pharmacistCont);
						break;
					case 2:
						System.out.println("Option 2 selected");
						pharmacistCont.getApptSys().updateOutcomeRec(pharmacistCont, sc);
						pharmacistCont.save();
						break;
					case 3:
						pharmacistCont.getInventory().viewInventory(2);
						break;
					case 4:
						System.out.println("Option 4 selected");
						replenishRequest(pharmacistCont, sc);
						break;
					case 5:
						System.out.println("Logging Out.\n");
						break;
					default:
						System.out.println("Invalid input. Please enter a number between 1 and 5.");
						break;
				}
			} catch (Exception e) {
				System.out.println("Invalid input. Please enter a number.");
				sc.next();  // Clear the invalid input
			}
		}
	}

	/**
	 * Pharmacist menu.
	 */
	private void displayMenu(){
		System.out.println("Pharmacist UI");
		String text = 
			"1. View appointment outcome record\n"
    		+"2. Update prescription (Appt Outcome Record)\n"
    		+"3. View medicine inventory\n"
			+"4. Request for replenishment\n"
			+"5. Log out\n\n"; 
		System.out.print(text);
	}

	/**
	 * Print outcome records
	 * @param pharmacistCont Pharmacist Controller
	 */
	private void viewApptOutcomeRec(PharmacistController pharmacistCont) {
		if(pharmacistCont.getApptSys().getOutcomeRec().isEmpty()) {
			System.out.println("No outcome records available.");
			return;  // Exit the method early if there are no records
		}
		
		int cnt=1;
		for(OutcomeRecord record: pharmacistCont.getApptSys().getOutcomeRec()) {
			System.out.print(cnt+".");
			record.printAll();
			cnt++;
		}		
	}

	/**
	 * Medicine replenishment request.
	 * @param pharmaCont Pharmacist Controller
	 * @param sc Scanner class
	 */
	private void replenishRequest(PharmacistController pharmaCont, Scanner sc) {
		StockRequest stockReq=StockRequest.createStockRequest(pharmaCont, sc);
		if(pharmaCont.getInventory().checkStockRequest(stockReq)) {
			return;
		}
		else {
			pharmaCont.addStockReq(stockReq);
			System.out.println("Request for replenishment has been added successfully");
		}
		
	}
}