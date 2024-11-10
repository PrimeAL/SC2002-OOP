package hmsProject;

import java.io.Serializable;

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
}