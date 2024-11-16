package hmsProject;
import java.io.Serializable;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This AppointmentSystem class manages all appointment related operations.
 */
public class AppointmentSystem implements Serializable {
	private ArrayList<Appointment> scheduledAppt;
	private ArrayList<Appointment> completedAppt;
	private ArrayList<OutcomeRecord> apptOutcomeRec;

	/**
	 * AppointmentSystem class constructor to initialise the arrays.
	 */
	public AppointmentSystem() {
		this.apptOutcomeRec=new ArrayList<OutcomeRecord>();
		this.scheduledAppt=new ArrayList<Appointment>();
		this.completedAppt=new ArrayList<Appointment>();
	}

	/**
	 * Adds scheduled appointment into the scheduled appointments list.
	 * @param appt scheduled appointment to be added.
	 */
	public void addSchAppt(Appointment appt) { this.scheduledAppt.add(appt); }

	/**
	 * Scheduled appointment list getter.
	 * @return array list of scheduled appointments.
	 */
	public ArrayList<Appointment> getSchAppt(){	return this.scheduledAppt; }

	/**
	 * Removes scheduled appointment from the scheduled appointments list.
	 * @param appt scheduled appointment to be removed.
	 */
	public void removeSchAppt(Appointment appt) { this.scheduledAppt.remove(appt); }

	/**
	 * Adds completed appointment into the completed appointments list.
	 * @param appt completed appointment to be added.
	 */
	public void addCompAppt(Appointment appt) {	this.completedAppt.add(appt); }

	/**
	 * Completed appointments list getter.
	 * @return array list of completed appointments.
	 */
	public ArrayList<Appointment> getCompAppt(){ return this.completedAppt; }
	//should only have add and get operation because its suppose to be a record of completed appointments

	/**
	 * Adds outcome record into the outcome records list.
	 * @param rec outcome record to be added.
	 */
	public void addOutcomeRecord(OutcomeRecord rec) { this.apptOutcomeRec.add(rec); }

	/**
	 * Outcome records list getter.
	 * @return array list of outcome records.
	 */
	public ArrayList<OutcomeRecord> getOutcomeRec(){ return this.apptOutcomeRec; }

	/**
	 * TO-BE-ADDED
	 * @param sc TO-BE-ADDED
	 */
	public void updateOutcomeRec(Scanner sc) {		//used by Pharmacists, add controller to parameter here when
		System.out.println("Selection of indexes out of range indicated will redirect you back to the main page");		
		System.out.println("Select Outcome record to update");
		int index = 1;
		for(OutcomeRecord outRec: this.getOutcomeRec()) {
			System.out.println(index+". Date: "+outRec.getDateOfAppointment()+
										" 1| Service Provide"+outRec.getServiceProvided());
			outRec.printMeds();
			index++;
		}
		try {
			int updateChoice = sc.nextInt() - 1;
			OutcomeRecord selectedRec = this.getOutcomeRec().get(updateChoice);
			
			//take selectedRec and cross check with stock to see if its dispensible
			//make the system check based on string of the medicine, as prescribedMeds and medicine are separate objects
			//as one exist in the inventory and the other belongs to the patient
			//logic to add based on pharmacist controller to complete this part
		} catch (Exception e) {
			System.out.println("Input out of range, exiting Update of Outcome Records");
		}
		
	}

	/**
	 * Scheduling an appointment. Links to PatientController which links to DataStorage.
	 * @param patientCont Patient Controller
	 * @param sc Scanner for input
	 */
	public void scheAppt(PatientController patientCont,Scanner sc) {	
		System.out.println("Selection of indexes out of range indicated will redirect you back to the main page");		
		System.out.println("Choose a doctor for your appointment:");
		int index = 1;
		for(Doctor doc: patientCont.getDocList()) {
			System.out.println(index+": "+doc.getName());
			index++;
		}
		//from doctor chosen based on index, the appointment available from the doctor can be retrieved from the doctor
		try {
			int docChoice = sc.nextInt() - 1;
			if(patientCont.getDocList().get(docChoice).getAvailableAppt().size()==0) { 
				System.out.println("No appointments available from this doctor");
				return;
			}
			
			System.out.println("Choose an appointment listed");
			index = 1;
			for(Appointment appt: patientCont.getDocList().get(docChoice).getAvailableAppt()) {
				System.out.println(index+". Date: "+appt.getDate()+" | Time: "+appt.getTime());
				index++;
			}
			
			int apptChoice = sc.nextInt() - 1;
			Appointment selectedAppt=patientCont.getDocList().get(docChoice).getAvailableAppt().get(apptChoice);
					
			patientCont.addToScheduled(selectedAppt); //as status pending
		} catch (Exception e) {
			System.out.println("Input out of range, exiting Appointment Scheduling");
		}

	}

	/**
	 * Rescheduling an appointment. Links to PatientController which links to DataStorage.
	 * @param patientCont Patient Controller
	 * @param appt appointment to reschedule
	 * @param sc Scanner class for input
	 */
	public void rescheAppt(PatientController patientCont, Appointment appt, Scanner sc) {
		//appointment of pending/completed status will be passed in from user
		//after rescheduling it will be pending again, going through the SAME doctor
		//reference of the specific appointment object is not changed throughout ***

		System.out.println("Selection of indexes out of range indicated will redirect you back to the main page");
		//check to be implemented
		try {
			Doctor apptDoc = appt.getDoctor();
			if (apptDoc.getAvailableAppt().size() == 0) {
				System.out.println("No other available appointments under selected Doctor");
				return;
			}
			int index = 1;
			for (Appointment apptOption : apptDoc.getAvailableAppt()) {
				System.out.println(index + ": Date: " + apptOption.getDate() + " | Time: " + apptOption.getTime());
				index++;
			}
			int newApptChoice = sc.nextInt() - 1;
			Appointment newSelectedAppt = apptDoc.getAvailableAppt().get(newApptChoice);
			patientCont.reschAppt(appt, newSelectedAppt);
		} catch (Exception e) {
			System.out.println("Input out of range, exiting Rescheduling of Appointment");
		}
		//Appointment.reschAppt(appt,newSelectedAppt, newApptChoice);
				
	}

}