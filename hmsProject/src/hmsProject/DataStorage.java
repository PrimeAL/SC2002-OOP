package hmsProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Acts as a kind of database. Contains all database operations which includes saving,
 * and locally stored data from deserialized file.
 */
public class DataStorage {
	private ArrayList<User> user;
	private AppointmentSystem apptSystem;
	private Inventory inven;
	private DataSerialization dataOps;
	private ArrayList<Doctor> docList;

	/**
	 * DataStorage Constructor. Initialise all locally stored data with an option of whether to refresh the data or use saved data.
	 */
	public DataStorage(){
		this.dataOps =new DataSerialization();
		System.out.println("To refresh data, key in 1. Otherwise, key in anything. ");
		Scanner scanner = new Scanner(System.in); //This is just for testing only. Will remove in final product.
		
		this.apptSystem=new AppointmentSystem();
		this.inven=new Inventory();
		this.user=new ArrayList<User>();

		while (true) {
			try {
				if (scanner.nextInt() == 1) {
					dataOps.initializeUser(this.user);
					dataOps.initializeMedData(this.inven);
					dataOps.serializeAll(apptSystem, inven, user);
				} else {
					dataOps.deserializeAll(this);
				}
				this.docList = new ArrayList<Doctor>();
				for (User u : this.user) {
					if (u.gethID().startsWith("D")) this.addToDocList(u); //doctor
				}
				scanner.nextLine();
				break;
			} catch (Exception e) {
				scanner.nextLine();
				System.out.println("Wrong input. Please try again. ");
			}
		}
	}

	/**
	 * Appointment System setter.
	 * @param apptSys Appointment System from deserialization
	 */
	public void setAppt(AppointmentSystem apptSys) {
		this.apptSystem=apptSys;
	}

	/**
	 * Inventory setter.
	 * @param inven Inventory from deserialization
	 */
	public void setInven(Inventory inven) {
		this.inven=inven;
	}

	/**
	 * User list setter.
	 * @param user all users from deserialization
	 */
	public void setUser(ArrayList<User> user) {
		this.user=user;
	}

	/**
	 * Saving the entire project via serialization into a single file.
	 */
	public void save() {
		dataOps.serializeAll(apptSystem, inven, user);
	}

	/**
	 * Add to list of Doctors.
	 * @param user Doctor to be added to the list.
	 */
	public void addToDocList(User user) {
		this.docList.add((Doctor)user);
	}

	/**
	 * Account verification. Verifies whether the user exists by matching username and password.
	 * @param id unique username that all Users have
	 * @param pw password to login
	 * @return the user that has been verified. Hence, the current user.
	 */
	public User getUser(String id, String pw) {
		User rUser = this.retrieveUser(id);
		if (rUser == null) {
			return null;
		} else {
			if (rUser.gethID().equals(id) && rUser.getPw().equals(pw)) {
				System.out.println("Account Verified");
				return rUser;
			}
		}
        return null;
    }

	/**
	 * Specific User getter.
	 * @param id unique User identifier
	 * @return retrieved User.
	 */
	public User retrieveUser(String id) {
		for(User u: this.user) {
			if(u.gethID().equals(id)) return u;
		}
		return null;
	}

	/**
	 * Specific Doctor getter.
	 * @return retrieved Doctor.
	 */
	public ArrayList<Doctor> retrieveDoctors(){
		return this.docList;
	}

	/**
	 * AppointmentSystem getter.
	 * @return retrieved AppointmentSystem.
	 */
	public AppointmentSystem retrieveApptSys() {
		return this.apptSystem;
	}

	/**
	 * Inventory getter.
	 * @return retrieved Inventory.
	 */
	public Inventory getInventory(){
		return this.inven;
	}

	/**
	 * Called by DoctorController to decline an appointment. Saves everything after.
	 * @param dr the Doctor that declines the appointment
	 * @param appt the Appointment declined
	 */
	public void declineAppt(Doctor dr,Appointment appt) {
		appt.setStatus("Cancelled");
		dr.removeApptReq(appt);
		appt.getPatient().removeAppt(appt);
		this.retrieveApptSys().removeSchAppt(appt);
		this.retrieveApptSys().addCompAppt(appt); //cancelled appointments are recorded as completed appt
		this.save();
	}

	/**
	 * Called by PatientController to schedule appointment. Saves everything after.
	 * @param currentPatient the Patient that schedules the appointment
	 * @param selectedAppt the Appointment to be scheduled
	 */
	public void patientSchAppt(Patient currentPatient, Appointment selectedAppt) {
		currentPatient.getAppt().add(selectedAppt);
		selectedAppt.setStatus("Pending");
		selectedAppt.setPatient(currentPatient);
		Doctor docOfAppt=selectedAppt.getDoctor();
		docOfAppt.updateApptReq(selectedAppt);
		this.retrieveApptSys().addSchAppt(selectedAppt); //added pending appointments
		this.save();
	}

	/**
	 * Called by PatientController to reschedule appointment. Saves everything after.
	 * @param oldAppt the old Appointment to be rescheduled
	 */
	public void revertOldAppt(Appointment oldAppt) {
		Doctor docOfAppt=oldAppt.getDoctor();
		Patient patientOfAppt=oldAppt.getPatient();
		docOfAppt.revertSetAppointment(oldAppt);
		patientOfAppt.removeAppt(oldAppt);
		oldAppt.setPatient(null);
		oldAppt.setStatus("Available"); //This must be placed after Dcotor.revertSetAppointment because that method checks the status
		this.retrieveApptSys().removeSchAppt(oldAppt);
		this.save();
	}

	/**
	 * Called by PatientController to cancel appointment. Saves everything after.
	 * @param patient the Patient that cancels the appointment
	 * @param appt the Appointment to be cancelled
	 */
	public void cancelAppt(Patient patient, Appointment appt) {
		Doctor docOfAppt=appt.getDoctor();
		docOfAppt.revertSetAppointment(appt);
		appt.setStatus("Available");
		patient.removeAppt(appt);
		this.retrieveApptSys().removeSchAppt(appt);
		this.save();
	}

	/**
	 * Called by DoctorController to accept an appointment. Saves everything after.
	 * @param appt the Appointment to be accepted
	 */
	public void acceptAppt(Appointment appt) {
		Doctor docOfAppt=appt.getDoctor();
		appt.setStatus("Confirmed");
		docOfAppt.updateComingAppt(appt);
		this.save();
	}

	/**
	 * Called by DoctorController to change appointment to completed and
	 * add that appointment to Patient and DataStorage's completed appointment list and
	 * remove that appointment from Patient and DataStorage's current appointments.
	 * Create an OutcomeRecord for the Appointment and add into AppointmentSystem's OutcomeRecordList
	 * @param appt the Appointment that is completed
	 */
	public void updateCompletedAppt(Appointment appt) {
		this.retrieveApptSys().addCompAppt(appt);
		this.retrieveApptSys().removeSchAppt(appt);
		appt.setStatus("Completed");
		appt.getDoctor().completedAppt(appt);
		appt.setApptOutcomeRecord(OutcomeRecord.createOutcomeRecord(appt, this.getInventory()));
		this.retrieveApptSys().addOutcomeRecord(appt.getApptOutcomeRecord());
		appt.getPatient().removeAppt(appt);
		appt.getPatient().addCompAppt(appt);
		this.save();
	}

	public void addStockReq(StockRequest stockReq) {
		this.getInventory().addPendingStockReq(stockReq);
		this.save();
	}

	public ArrayList<User> getUserList() {
		return this.user;
	}

	public void addNewStaff(User staffUser) {
		this.getUserList().add(staffUser);
		this.save();
	}

	public void removeStaff(User staffUser) {
		this.getUserList().remove(staffUser);
		this.save();
	}
}
