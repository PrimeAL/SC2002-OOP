package hmsProject;
import java.io.Serializable;
import java.util.Scanner;
import java.util.ArrayList;

public class OutcomeRecord implements Serializable {
    private String dateOfAppointment;
    private String serviceProvided;
    private ArrayList<prescribedMed> meds;
    private String consultationNote;

    public OutcomeRecord() {
        this.meds = new ArrayList<prescribedMed>();
    }
    
    public OutcomeRecord(Appointment appt) {
        this.dateOfAppointment = appt.getDate();
        this.serviceProvided = null;
        this.meds = new ArrayList<prescribedMed>();
        this.consultationNote = null;
    }
    
    public static OutcomeRecord createOutcomeRecord(Appointment cmptAppt) {
        OutcomeRecord newRecord = new OutcomeRecord(cmptAppt);
        Scanner sc = new Scanner(System.in);
        newRecord.setServiceProvided(sc);
        newRecord.setMeds(sc);
        newRecord.writeNote(sc);
        return newRecord;
    }
    
    public void writeNote(Scanner sc) {
        System.out.println("What are the notes for this appointment?");
        this.consultationNote = sc.nextLine();
    }

    public void setMeds(Scanner sc) {
        System.out.println("What are the medicine(s) to be prescribed?");
        while (true) {
            System.out.println("Enter 1 to add new Medicine or 0 when done.");
            String input = sc.nextLine();
            if (input.equals("1")) {
                this.meds.add(prescribedMed.createPrescribedMed(sc));
            } else if (input.equals("0")) {
                return;
            } else {
                System.out.println("Invalid input. Enter 1 to add new Medicine or 0 when done.");
            }
        }
    }
    
    public void setServiceProvided(Scanner sc) {
        System.out.println("What is the service provided?");
        this.serviceProvided = sc.nextLine();
    }

    // Getters
    public String getDateOfAppointment() {
        return dateOfAppointment;
    }

    public String getServiceProvided() {
        return serviceProvided;
    }
    
    public ArrayList<prescribedMed> getMeds() {
    	return this.meds;
    }
    
    public void printMeds() {  
    	System.out.println("\tMedicine List:");
        for(prescribedMed preMed: this.getMeds()) {
        	System.out.println("\tMedication Name:"+preMed.getMedicationName()+" Status:"+preMed.getStatus());
        }
    }
    
    public String getConsultationNote() {
        return consultationNote;
    }
    
    public void printAll() {
    	System.out.println("Consultation Notes:"+this.consultationNote);
    	System.out.println("Date of Appointment:"+this.dateOfAppointment);
    	System.out.println("Service Provided:"+this.serviceProvided);
    	printMeds();
    }
}