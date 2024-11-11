package hmsProject;
import java.io.Serializable;
import java.util.Scanner;
import java.util.ArrayList;

public class OutcomeRecord implements Serializable {
	private String dateOfAppointment;
	private String serviceProvided;
	private ArrayList<prescribedMed> meds;
	private String consultationNote;
	private String status; // new field for prescription status (Alan)

	public OutcomeRecord() {
		this.status = "pending"; // default status (Alan)
	}
	
	public OutcomeRecord(Appointment appt) {
		this.dateOfAppointment=appt.getDate();
		this.serviceProvided=null;
		this.meds=null;
		this.consultationNote=null;
		this.status = "pending"; // default status
	}
	
	public String getStatus() {
        return this.status;
    }

	public void setStatus(String status) {
        this.status = status;
    }
	
	public static OutcomeRecord createOutcomeRecord(Appointment cmptAppt) {
		// TODO - implement OutcomeRecord.createOutcomeRecord
		OutcomeRecord newRecord= new OutcomeRecord(cmptAppt);
		Scanner sc=new Scanner(System.in);
		newRecord.setServiceProvided(sc);
		newRecord.setMeds(sc);
		newRecord.writeNote(sc);
		return newRecord;
	}
	
	public void writeNote(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("What are the notes for this appointment?");
		this.consultationNote=sc.nextLine();
	}

	public void setMeds(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("What are the medicine(s) to be prescribed?\nEnter 1 to add new Medicine\n Enter 0 when done");
		while(true) {
			if(sc.nextLine()=="1") { //should be changed to selection from list of available medicine to create prescribed med
				this.meds.add(prescribedMed.createPrescribedMed(sc));
			}
			else {
				return;
			}
			
		}
	}
	
	public void setServiceProvided(Scanner sc) {
		System.out.println("What is the service provided?");
		this.serviceProvided=sc.nextLine();
	}
	
}