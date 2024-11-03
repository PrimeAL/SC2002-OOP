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
	
}
