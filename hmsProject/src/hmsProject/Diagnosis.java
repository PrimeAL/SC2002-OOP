package hmsProject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Patient Diagnosis class.
 */
public class Diagnosis implements Serializable {
    private String description;
    private String diagnosisDate;
    private String diagnosedBy;
    private String severity;

    /**
     * Diagnosis Constructor.
     * @param description description of diagnosis
     * @param diagnosisDate date of diagnosis
     * @param diagnosedBy the doctor who does the diagnosis
     * @param severity the severity of the diagnosis: Mild, Moderate, Severe
     */
    public Diagnosis(String description, String diagnosisDate, String diagnosedBy, String severity) {
        this.description = description;
        this.diagnosisDate = diagnosisDate;
        this.diagnosedBy = diagnosedBy;
        this.severity = severity;
    }
    
    // Getters and Setters

    /**
     * Diagnosis description getter.
     * @return diagnosis description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Diagnosis description setter.
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Diagnosis date getter.
     * @return diagnosis date.
     */
    public String getDiagnosisDate() {
        return diagnosisDate;
    }

    /**
     * Diagnosis date setter.
     * @param diagnosisDate new date
     */
    public void setDiagnosisDate(String diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    /**
     * Diagnosis doctor getter.
     * @return diagnosis doctor.
     */
    public String getDiagnosedBy() {
        return diagnosedBy;
    }

    /**
     * Diagnosis doctor setter.
     * @param diagnosedBy new doctor
     */
    public void setDiagnosedBy(String diagnosedBy) {
        this.diagnosedBy = diagnosedBy;
    }

    /**
     * Diagnosis severity getter.
     * @return diagnosis severity.
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * Diagnosis severity setter.
     * @param severity diagnosis severity.
     */
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    /**
     * New Diagnosis class creation.
     * @param sc Scanner class for input
     * @param drName diagnosis doctor
     * @return diagnosis object.
     */
    public static Diagnosis createDiagosis(Scanner sc, String drName) {
		System.out.println("Enter diagnosis description:");
		String description = sc.nextLine();
		System.out.println("Enter severity (Mild/Moderate/Severe):");
		String severity = sc.nextLine();
		return new Diagnosis(description, LocalDate.now().toString(), drName, severity);
    	
    }
}