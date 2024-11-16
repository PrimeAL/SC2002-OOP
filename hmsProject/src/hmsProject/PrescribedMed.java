package hmsProject;

import java.io.Serializable;

public class PrescribedMed implements Serializable {
	private String medicationName;
	private String status;
	private int quantity;

	public PrescribedMed(String medicationName, int quantity) {
		this.medicationName = medicationName;
		this.quantity = quantity;
		this.status = "Prescribed";
	}

	public static PrescribedMed createPrescribedMed(String medicationName, int quantity) {
		return new PrescribedMed(medicationName, quantity);
	}

	public String getMedicationName() {
		return medicationName;
	}

	public String getStatus() {
		return status;
	}

	public int getQuantity() {
		return quantity;
	}
}