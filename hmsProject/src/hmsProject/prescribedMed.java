package hmsProject;

import java.util.Scanner;

public class prescribedMed {
	private String medicationName;
	private String status;
	
	public prescribedMed(String medicationName) {
		this.medicationName=medicationName;
		this.status="pending";
	}
	
	public static prescribedMed createPrescribedMed(Scanner sc) {
		System.out.println("What is the medicine to be prescribed?");
		prescribedMed newMed= new prescribedMed(sc.nextLine());
		return newMed;
	}

	public String getMedicationName() {
		// TODO Auto-generated method stub
		return medicationName;
	}

	public String getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

    public void setStatus(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setStatus'");
    }
	
}
