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
    	prescribedMed newMed = prescribedMed.createPrescribedMed(sc);
        if(newMed != null) {
            this.meds.add(newMed);
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
    	System.out.print("\n\tMedicine List:");
        for(prescribedMed preMed: this.getMeds()) {
        	System.out.print("\n\tMedication Name"+preMed.getMedicationName()+" Status:"+preMed.getStatus());
        }
    }

    public String getConsultationNote() {
        return consultationNote;
    }
}