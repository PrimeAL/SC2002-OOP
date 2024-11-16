package hmsProject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Represents a treatment given to a patient.
 */
public class Treatment implements Serializable {
    private String description;
    private String treatmentDate;
    private String treatmentBy;

    /**
     * Constructs a Treatment with the specified description, date, and doctor.
     *
     * @param description the description of the treatment
     * @param treatmentDate the date the treatment was given
     * @param treatmentBy the name of the doctor who gave the treatment
     */
    public Treatment(String description, String treatmentDate, String treatmentBy) {
        this.description = description;
        this.treatmentDate = treatmentDate;
        this.treatmentBy = treatmentBy;
    }

    /**
     * Gets the description of the treatment.
     *
     * @return the description of the treatment
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the treatment.
     *
     * @param description the new description of the treatment
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the date the treatment was given.
     *
     * @return the date the treatment was given
     */
    public String getTreatmentDate() {
        return treatmentDate;
    }

    /**
     * Sets the date the treatment was given.
     *
     * @param treatmentDate the new date the treatment was given
     */
    public void setTreatmentDate(String treatmentDate) {
        this.treatmentDate = treatmentDate;
    }

    /**
     * Gets the name of the doctor who gave the treatment.
     *
     * @return the name of the doctor who gave the treatment
     */
    public String getTreatmentBy() {
        return treatmentBy;
    }

    /**
     * Sets the name of the doctor who gave the treatment.
     *
     * @param treatmentBy the new name of the doctor who gave the treatment
     */
    public void setTreatmentBy(String treatmentBy) {
        this.treatmentBy = treatmentBy;
    }

    /**
     * Creates a new Treatment by prompting the user for input.
     *
     * @param sc the Scanner to use for input
     * @param drName the name of the doctor who is giving the treatment
     * @return a new Treatment object
     */
    public static Treatment createTreatment(Scanner sc, String drName) {
        System.out.println("Enter treatment description:");
        String description = sc.nextLine();

        return new Treatment(description, LocalDate.now().toString(), drName);
    }
}