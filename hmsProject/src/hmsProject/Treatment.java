package hmsProject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Patient Treatment.
 */
public class Treatment implements Serializable {
    private String treatmentType;
    private String prescription;
    private String prescribedDate;
    private String prescribedBy;

    /**
     * Treatment constructor
     * @param treatmentType treatment type
     * @param prescription prescription
     * @param prescribedDate date
     * @param prescribedBy doctor
     */
    public Treatment(String treatmentType, String prescription, String prescribedDate, String prescribedBy) {
        this.treatmentType = treatmentType;
        this.prescription = prescription;
        this.prescribedDate = prescribedDate;
        this.prescribedBy = prescribedBy;
    }
    
    // Getters and Setters

    /**
     * Treatment type getter.
     * @return treatment type.
     */
    public String getTreatmentType() {
        return treatmentType;
    }

    /**
     * Treatment type setter.
     * @param treatmentType new treatment type
     */
    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    /**
     * Prescription getter.
     * @return prescription.
     */
    public String getPrescription() {
        return prescription;
    }

    /**
     * Prescription setter.
     * @param prescription new prescription
     */
    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    /**
     * Date getter.
     * @return date.
     */
    public String getPrescribedDate() {
        return prescribedDate;
    }

    /**
     * Date setter.
     * @param prescribedDate new Date
     */
    public void setPrescribedDate(String prescribedDate) {
        this.prescribedDate = prescribedDate;
    }

    /**
     * Doctor getter.
     * @return doctor name.
     */
    public String getPrescribedBy() {
        return prescribedBy;
    }

    /**
     * Doctor setter.
     * @param prescribedBy doctor name
     */
    public void setPrescribedBy(String prescribedBy) {
        this.prescribedBy = prescribedBy;
    }

    /**
     * New Treatment class creation.
     * @param sc Scanner class for input
     * @param drName doctor name
     * @return new Treatment
     */
    public static Treatment createTreatment(Scanner sc, String drName) {
		System.out.println("Enter treatment type:");
		String treatmentType = sc.nextLine();
		System.out.println("Enter prescription:");
		String prescription = sc.nextLine();

		return new Treatment(treatmentType, prescription, LocalDate.now().toString(), drName);
    }
}