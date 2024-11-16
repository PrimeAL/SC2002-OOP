package hmsProject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

public class Diagnosis implements Serializable {
    private String description;
    private String diagnosisDate;
    private String diagnosedBy;
    private String severity;
    
    public Diagnosis(String description, String diagnosisDate, String diagnosedBy, String severity) {
        this.description = description;
        this.diagnosisDate = diagnosisDate;
        this.diagnosedBy = diagnosedBy;
        this.severity = severity;
    }
    
    // Getters and Setters
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDiagnosisDate() {
        return diagnosisDate;
    }
    
    public void setDiagnosisDate(String diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }
    
    public String getDiagnosedBy() {
        return diagnosedBy;
    }
    
    public void setDiagnosedBy(String diagnosedBy) {
        this.diagnosedBy = diagnosedBy;
    }
    
    public String getSeverity() {
        return severity;
    }
    
    public void setSeverity(String severity) {
        this.severity = severity;
    }
    
    public static Diagnosis createDiagosis(Scanner sc, String drName) {
		System.out.println("Enter diagnosis description:");
		String description = sc.nextLine();
		System.out.println("Enter severity (Mild/Moderate/Severe):");
		String severity = sc.nextLine();
		return new Diagnosis(description, LocalDate.now().toString(), drName, severity);
    	
    }
}