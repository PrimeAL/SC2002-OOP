package hmsProject;

import java.util.ArrayList;

/**
 * Patient Controller that manages all Patient operations and links to DataStorage.
 */
public class PatientController extends Controller{
	private Patient currentPatient;

	/**
	 * PatientController constructor.
	 * @param mainCont MainController
	 * @param patient Patient
	 */
	public PatientController(MainController mainCont, Patient patient) {
		super(mainCont); //references the same datastorage that was initialised
		this.currentPatient=patient;
	}

	/**
	 * AppointmentSystem getter.
	 * @return AppointmentSystem.
	 */
	public AppointmentSystem getApptSys() {
		return this.getDataStorage().retrieveApptSys();
	}

	/**
	 * Schedule appointment.
	 * @param selectedAppt appointment to be scheduled
	 */
	public void addToScheduled(Appointment selectedAppt) {
		this.getDataStorage().patientSchAppt(this.currentPatient,selectedAppt);
		System.out.println("Request for Appointment under Dr "+selectedAppt.getDoctor().getName()
		+" at "+selectedAppt.getDate() +" "+selectedAppt.getTime()+" has been sent");
	}

	/**
	 * Reschedule appointment.
	 * @param oldAppt appointment to be rescheduled
	 * @param newSelectedAppt appointment to be scheduled to
	 */
	public void reschAppt(Appointment oldAppt, Appointment newSelectedAppt) {
		this.getDataStorage().revertOldAppt(oldAppt);
		this.addToScheduled(newSelectedAppt);
	}

	/**
	 * Cancel appointment.
	 * @param appt appointment to be cancelled
	 */
	public void cancelAppt(Appointment appt) {
		this.getDataStorage().cancelAppt(this.currentPatient,appt);
	}

	/**
	 * Doctor List getter.
	 * @return DoctorList.
	 */
	public ArrayList<Doctor> getDocList() {
		return this.getDataStorage().retrieveDoctors();
	}

	/**
	 * Save all changes.
	 */
	public void save() {
		this.getDataStorage().save();
	}
}
