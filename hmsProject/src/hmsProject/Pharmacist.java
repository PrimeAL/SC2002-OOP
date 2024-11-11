package hmsProject;
import java.util.ArrayList;
import java.util.Scanner;

public class Pharmacist extends User{
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
		System.out.println("\nPharmcist UI");
		int userMenuInput=0;
		while(userMenuInput!=4){
			displayMenu();
			Scanner sc = new Scanner(System.in);
			userMenuInput=sc.nextInt();

			switch(userMenuInput){
				case 1: 
					System.out.println("Appointment Outcome Record"); //outcome record
					for (OutcomeRecord record : apptSys.getApptOutcomeRecords()) {
                        System.out.println(record);
                    }
					break;
				case 2: 
					System.out.println("*** Updating prescrption to completed ***");
					System.out.println("prescription updated!");

					break;
				case 3:
					System.out.println("Medicine List");
					break;
				case 4:
					break;
				default:
					System.out.println("Input out of range");
					break;
			}
		}
	}*/

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

	public void userInterface(AppointmentSystem apptSys, Inventory inven) {
    System.out.println("\nPharmacist UI");
    int userMenuInput = 0;
    Scanner sc = new Scanner(System.in);

    while (userMenuInput != 5) {
        displayMenu();
        userMenuInput = sc.nextInt();

        switch (userMenuInput) {
            case 1: 
                System.out.println("Appointment Outcome Record");
                for (OutcomeRecord record : apptSys.getApptOutcomeRecords()) {
                    System.out.println(record);
                }
                break;
                
            case 2: 
                // Updating prescription status
                System.out.println("Select a record to update prescription status:");
                ArrayList<OutcomeRecord> records = apptSys.getApptOutcomeRecords();

                if (records.isEmpty()) {
                    System.out.println("No records available.");
                    break;
                }

                // Display records with indices
                for (int i = 0; i < records.size(); i++) {
                    System.out.println(i + ": " + records.get(i));
                }

                int recordIndex = sc.nextInt();
                if (recordIndex >= 0 && recordIndex < records.size()) {
                    OutcomeRecord selectedRecord = records.get(recordIndex);
                    
                    if ("pending".equals(selectedRecord.getStatus())) {
                        selectedRecord.setStatus("completed");
                        System.out.println("Prescription status updated to completed.");
                    } else {
                        System.out.println("Prescription already completed.");
                    }
                } else {
                    System.out.println("Invalid record selection.");
                }
                break;
                
            case 3:
                System.out.println("Inventory Menu");
				inven.viewStock();
                break;
			
			case 4:
				System.out.println("Submit request for low stock");
				inven.submitReq(); 
				break;
				
				
                
            case 5:
                System.out.println("Logging out...");
                break;
                
            default:
                System.out.println("Input out of range");
                break;
        }
    }
    sc.close();
}
}