package hmsProject;
import java.io.Serializable;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Outcome Record after an appointment.
 */
public class OutcomeRecord implements Serializable {
    private String dateOfAppointment;
    private String serviceProvided;
    private ArrayList<PrescribedMed> meds;
    private String consultationNote;

    /**
     * OutcomeRecord constructor which creates empty medicine list.
     */
    public OutcomeRecord() {
        this.meds = new ArrayList<PrescribedMed>();
    }

    /**
     * OutcomeRecord constructor which initialises all attributes.
     * @param appt Completed Appointment
     */
    public OutcomeRecord(Appointment appt) {
        this.dateOfAppointment = appt.getDate();
        this.serviceProvided = null;
        this.meds = new ArrayList<PrescribedMed>();
        this.consultationNote = null;
    }

    /**
     * Manually input new OutcomeRecord.
     * @param cmptAppt Completed appointment
     * @return new OutcomeRecord.
     */
    public static OutcomeRecord createOutcomeRecord(Appointment cmptAppt) {
        OutcomeRecord newRecord = new OutcomeRecord(cmptAppt);
        Scanner sc = new Scanner(System.in);
        newRecord.setServiceProvided(sc);
        newRecord.setMeds(sc);
        newRecord.writeNote(sc);
        return newRecord;
    }

    /**
     * Appointment note input.
     * @param sc Scanner class for input
     */
    public void writeNote(Scanner sc) {
        System.out.println("What are the notes for this appointment?");
        this.consultationNote = sc.nextLine();
    }

    /**
     * Medicine to be prescribed input.
     * @param sc Scanner class for input
     */
    public void setMeds(Scanner sc) {
        System.out.println("What are the medicine(s) to be prescribed?");
        while (true) {
            System.out.println("Enter 1 to add new Medicine or 0 when done.");
            String input = sc.nextLine();
            if (input.equals("1")) {
                this.meds.add(PrescribedMed.createPrescribedMed(sc));
            } else if (input.equals("0")) {
                return;
            } else {
                System.out.println("Invalid input. Enter 1 to add new Medicine or 0 when done.");
            }
        }
    }

    /**
     * Appointment service provided input.
     * @param sc Scanner class for input
     */
    public void setServiceProvided(Scanner sc) {
        System.out.println("What is the service provided?");
        this.serviceProvided = sc.nextLine();
    }

    // Getters

    /**
     * Appointment date getter.
     * @return appointment date.
     */
    public String getDateOfAppointment() {
        return dateOfAppointment;
    }

    /**
     * Appointment service provided getter.
     * @return service provided.
     */
    public String getServiceProvided() {
        return serviceProvided;
    }

    /**
     * List of prescribed medicine getter.
     * @return prescribed medicine list.
     */
    public ArrayList<PrescribedMed> getMeds() {
    	return this.meds;
    }

    /**
     * Print all medicines prescribed.
     */
    public void printMeds() {  
    	System.out.println("\tMedicine List:");
        for(PrescribedMed preMed: this.getMeds()) {
        	System.out.println("\tMedication Name:"+preMed.getMedicationName()+" Status:"+preMed.getStatus());
        }
    }

    /**
     * Appointment consultation note getter.
     * @return consultation note
     */
    public String getConsultationNote() {
        return consultationNote;
    }

    /**
     * Print everything.
     */
    public void printAll() {
    	System.out.println("Consultation Notes:"+this.consultationNote);
    	System.out.println("Date of Appointment:"+this.dateOfAppointment);
    	System.out.println("Service Provided:"+this.serviceProvided);
    	printMeds();
    }
}