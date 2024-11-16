package hmsProject;

import java.util.Scanner;

public class Pharmacist extends User {
	private String name;
	private String gender;
	private int age;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

    public String toString() {
        return String.format("Pharmacist [Name: %s, ID: %s, Age: %d, Gender: %s]", getName(), gethID(), getAge(), getGender());
    }
	
	
	public Pharmacist(String uid, String pw, String name, String gender, int age) {
		super(uid, pw);
		setName(name);
		setGender(gender);
		setAge(age);
	}

	public void userInterface(PharmacistController pharmacistCont, Scanner sc) {
		int choice = 0;
		try {
		while(choice!=5) {
			displayMenu();
			choice=sc.nextInt();
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
				replenishRequest(pharmacistCont,sc);
				break;
			case 5:
				System.out.println("Logging Out.");
				break;
			default:
				System.out.println("Invalid input. Please enter a number between 1 and 5.");
				break;
			}
			
		}
		} catch (Exception e) {
			System.out.println("Invalid input. Please enter a number.");
			sc.next();  // Clear the invalid input
		}
	}
		

	private void displayMenu(){
		System.out.println("\nPharmacist UI");
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