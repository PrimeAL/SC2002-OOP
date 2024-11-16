package hmsProject;

import java.io.Serializable;
import java.util.Scanner;

public class PrescribedMed implements Serializable {
	private String medicationName;
	private String status;
	
	public PrescribedMed(String medicationName) {
		this.medicationName=medicationName;
		this.status="Pending";
	}
	
	public static PrescribedMed createPrescribedMed(Scanner sc) {
		System.out.println("What is the medicine to be prescribed?");
		PrescribedMed newMed= new PrescribedMed(sc.nextLine());
		return newMed;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public String getStatus() {
		return status;
	}
	
}
