package hmsProject;

import java.io.Serializable;
import java.util.Scanner;

public class prescribedMed implements Serializable {
	private String medicationName;
	private String status;
	
	public prescribedMed(String medicationName) {
		this.medicationName=medicationName;
		this.status="Pending";
	}
	
	public static prescribedMed createPrescribedMed(Scanner sc) {
		System.out.println("What is the medicine to be prescribed?");
		prescribedMed newMed= new prescribedMed(sc.nextLine());
		return newMed;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public String getStatus() {
		return status;
	}
	
}
