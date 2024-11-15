package hmsProject;
import java.io.Serializable;
import java.util.Scanner;
import java.util.ArrayList;

public class AppointmentSystem implements Serializable {

	private ArrayList<Appointment> scheduledAppt;
	private ArrayList<Appointment> completedAppt;
	private ArrayList<OutcomeRecord> apptOutcomeRec;
	private ArrayList<Appointment> allAppt;
	
	public AppointmentSystem() {
		this.apptOutcomeRec=new ArrayList<OutcomeRecord>();
		this.scheduledAppt=new ArrayList<Appointment>();
		this.completedAppt=new ArrayList<Appointment>();
		this.allAppt = new ArrayList<Appointment>();
	}
	
	public void addSchAppt(Appointment appt) { this.scheduledAppt.add(appt); }
	public ArrayList<Appointment> getSchAppt(){	return this.scheduledAppt; }
	public void removeSchAppt(Appointment appt) { this.scheduledAppt.remove(appt); }
	
	public void addCompAppt(Appointment appt) {	this.completedAppt.add(appt); } 
	public ArrayList<Appointment> getCompAppt(){ return this.completedAppt; }
	//should only have add and get operation because its suppose to be a record of completed appointments
	
	public void addOutcomeRecord(OutcomeRecord rec) { this.apptOutcomeRec.add(rec); }
	public ArrayList<OutcomeRecord> getOutcomeRec(){ return this.apptOutcomeRec; }
	
	public void updateOutcomeRec(Scanner sc) {		//used by Pharmacists, add controller to parameter here when
		System.out.println("Selection of indexes out of range indicated will redirect you back to the main page");		
		System.out.println("Select Outcome record to update");
		int index=0;
		for(OutcomeRecord outRec: this.getOutcomeRec()) {
			System.out.println(index+": Date: "+outRec.getDateOfAppointment()+
										"| Service Provide"+outRec.getServiceProvided());
			outRec.printMeds();
		}
		try {
			int updateChoice=-1;
			updateChoice=sc.nextInt();
			OutcomeRecord selectedRec = this.getOutcomeRec().get(updateChoice);
			
			//take selectedRec and cross check with stock to see if its dispensible
			//make the system check based on string of the medicine, as prescribedMeds and medicine are separate objects
			//as one exist in the inventory and the other belongs to the patient
			//logic to add based on pharmacist controller to complete this part
		} catch (Exception e) {
			System.out.println("Input out of range, exiting Update of Outcome Records");
		}
		
	}
	
	public void scheAppt(PatientController patientCont,Scanner sc) {	
		System.out.println("Selection of indexes out of range indicated will redirect you back to the main page");		
		System.out.println("Choose a doctor for your appointment:");
		int index=0;
		for(Doctor doc: patientCont.getDocList()) {
			System.out.println(index+": "+doc.getName());
			index++;
		}
		//from doctor chosen based on index, the appointment available from the doctor can be retrieved from the doctor
		try {
			int docChoice=-1;
			docChoice=sc.nextInt();
			
			System.out.println("Choose an appointment listed");
			index=0;
			for(Appointment appt: patientCont.getDocList().get(docChoice).getAvailableAppt()) {
				System.out.println(index+": Date: "+appt.getDate()+"| Time: "+appt.getTime());
			}
			
			int apptChoice=-1;
			apptChoice=sc.nextInt();
			Appointment selectedAppt=patientCont.getDocList().get(docChoice).getAvailableAppt().get(apptChoice);
					
			patientCont.addToScheduled(selectedAppt); //as status pending
		} catch (Exception e) {
			System.out.println("Input out of range, exiting Appointment Scheduling");
		}

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
		try {
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
		} catch (Exception e) {
			System.out.println("Input out of range, exiting Rescheduling of Appointment");
		}
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