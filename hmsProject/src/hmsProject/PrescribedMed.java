package hmsProject;

import java.io.Serializable;

/**
 * Prescribed Medicine for patient.
 */
public class PrescribedMed implements Serializable {
	private String medicationName;
	private String status;
	private int quantity;

	/**
	 * Prescribed Medicine constructor.
	 * @param medicationName medicine name
	 * @param quantity amount to prescribe
	 */
	public PrescribedMed(String medicationName, int quantity) {
		this.medicationName = medicationName;
		this.quantity = quantity;
		this.status = "pending";
	}

	/**
	 * Create new PrescribedMed object.
	 * @param medicationName medicine name
	 * @param quantity amount to prescribe
	 * @return new PrescribedMed object.
	 */
	public static PrescribedMed createPrescribedMed(String medicationName, int quantity) {
		return new PrescribedMed(medicationName, quantity);
	}

	/**
	 * Medicine name getter.
	 * @return medicine name.
	 */
	public String getMedicationName() {
		return medicationName;
	}

	/**
	 * Dispensed status getter.
	 * @return dispensed status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Amount to prescribe getter.
	 * @return amount to prescribe.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Dispensed status setter.
	 * @param status new status (dispensed)
	 */
	public void setStatus(String status) {
	    this.status = status;
	}
	
	
}