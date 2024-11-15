package hmsProject;
import java.io.Serializable;
import java.util.Scanner;
import java.util.ArrayList;

public class AppointmentSystem implements Serializable {

	private ArrayList<Doctor> doctors;
	private ArrayList<OutcomeRecord> apptOutcomeRec;
	private ArrayList<Appointment> scheduledAppt;
	private ArrayList<Appointment> completedAppt;
	private ArrayList<Appointment> allAppt;
	
	public AppointmentSystem() {
		this.doctors=new ArrayList<Doctor>();
		this.apptOutcomeRec=new ArrayList<OutcomeRecord>();
		this.scheduledAppt=new ArrayList<Appointment>();
		this.completedAppt=new ArrayList<Appointment>();
		this.allAppt = new ArrayList<Appointment>();
	}

	//This is just for testing because the doctors list is independent of database doctors. See DataStorage for more info.
	public Doctor getFirstDocForTesting() { return this.doctors.get(0); };
	
	public void addDoc(Doctor dr) {
		this.doctors.add(dr);
	}
	
	public void scheAppt(PatientController patientCont,Scanner sc) {	
		//patients can choose from the list of doctors listed in the appointment system to facilitate the booking
		//**missing link of patient to doctor for checks (to be implemented)
		System.out.println("Selection of indexes out of range indicated will redirect you back to the main page");
		//check to be implemented
		
		System.out.println("Choose a doctor for your appointment:");
		int index=0;
		for(Doctor doc: this.doctors) {
			System.out.println(index+": "+doc.getName());
			index++;
		}
		//from doctor chosen based on index, the appointment available from the doctor can be retrieved from the doctor
		
		int docChoice=-1;
		docChoice=sc.nextInt();
		
		System.out.println("Choose an appointment listed");
		index=0;
		for(Appointment appt: this.doctors.get(docChoice).getAvailableAppt()) {
			System.out.println(index+": Date: "+appt.getDate()+"| Time: "+appt.getTime());
		}
		
		int apptChoice=-1;
		apptChoice=sc.nextInt();
		Appointment selectedAppt=this.doctors.get(docChoice).getAvailableAppt().get(apptChoice);
		
		
		//Appointment.requestForAppt(selectedAppt, apptChoice);  //changes state of appt to pending and print msg
		patientCont.addToScheduled(selectedAppt); //as status pending
		
	}

	/**
	 * 
	 * @param appt
	 */
	public void rescheAppt(PatientController patientCont, Appointment appt, Scanner sc) {
		//appointment of pending/completed status will be passed in from user
		//after rescheduling it will be pending again, going through the SAME doctor
		//reference of the specific appointment object is not changed throughout ***

		System.out.println("Selection of indexes out of range indicated will redirect you back to the main page");
		//check to be implemented
		
		Doctor apptDoc=appt.getDoctor();
		if(apptDoc.getAvailableAppt().size()==0) {
			System.out.println("No other available appointments under selected Doctor");
			return;
		}
		int index=0;
		for(Appointment apptOption: apptDoc.getAvailableAppt()) {
			System.out.println(index+": Date: "+apptOption.getDate()+"| Time: "+apptOption.getTime());
		}
		
		int newApptChoice=-1;
		newApptChoice=sc.nextInt();
		Appointment newSelectedAppt=apptDoc.getAvailableAppt().get(newApptChoice);
		
		patientCont.reschAppt(appt,newSelectedAppt);
		//Appointment.reschAppt(appt,newSelectedAppt, newApptChoice);
				
	}

	/**
	 * 
	 * @param appt
	 */
	public boolean cancelAppt(PatientController patientCont,Appointment appt) {
		// TODO - implement AppointmentSystem.cancelAppt
		patientCont.cancelAppt(appt);		
		return true; 
	}

	public boolean acceptAppt(DoctorController docCont, Appointment appt) {
		// TODO - implement AppointmentSystem.acceptAppt
		//appointment passed in from doctor side
		docCont.addAccAppt(appt);
		return true;
	}
	
	public boolean updateAppt(DoctorController docCont,Appointment appt) {
		// TODO - implement AppointmentSystem.updateAppt
		//after appointment is completed
		docCont.updateAppt(appt);

		return true;
	}
	
	public void addSchAppt(Appointment appt) {
		this.scheduledAppt.add(appt);
	}
	
	public void addCompAppt(Appointment appt) {
		this.completedAppt.add(appt);
	}
	
	public void addOutcomeRecord(OutcomeRecord rec) {
		this.apptOutcomeRec.add(rec);
	}
	
	public void updateOutcomeRec() {
		// TODO - implement AppointmentSystem.updateOutcomeRec
		throw new UnsupportedOperationException();
	}


	//for Administrator
	public ArrayList<Appointment> getAllAppt() { // returns the list of scheduled appointments
		int index = 0;
		while (index < this.scheduledAppt.size() && index <this.completedAppt.size()) {
			getAllAppt().add(scheduledAppt.get(index));
			getAllAppt().add(completedAppt.get(index));
			index++;
		}
		
		return allAppt;
	}
}