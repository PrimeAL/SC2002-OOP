package hmsProject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

public class Treatment implements Serializable {
    private String treatmentType;
    private String prescription;
    private String prescribedDate;
    private String prescribedBy;
    
    public Treatment(String treatmentType, String prescription, String prescribedDate, String prescribedBy) {
        this.treatmentType = treatmentType;
        this.prescription = prescription;
        this.prescribedDate = prescribedDate;
        this.prescribedBy = prescribedBy;
    }
    
    // Getters and Setters
    public String getTreatmentType() {
        return treatmentType;
    }
    
    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }
    
    public String getPrescription() {
        return prescription;
    }
    
    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }
    
    public String getPrescribedDate() {
        return prescribedDate;
    }
    
    public void setPrescribedDate(String prescribedDate) {
        this.prescribedDate = prescribedDate;
    }
    
    public String getPrescribedBy() {
        return prescribedBy;
    }
    
    public void setPrescribedBy(String prescribedBy) {
        this.prescribedBy = prescribedBy;
    }
    
    public static Treatment createTreatment(Scanner sc, String drName) {
		System.out.println("Enter treatment type:");
		String treatmentType = sc.nextLine();
		System.out.println("Enter prescription:");
		String prescription = sc.nextLine();

		return new Treatment(treatmentType, prescription, LocalDate.now().toString(), drName);
    }
}