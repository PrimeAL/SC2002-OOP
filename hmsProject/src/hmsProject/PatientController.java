package hmsProject;

import java.util.ArrayList;

public class PatientController extends controller{
	private Patient currentPatient;
	
	public PatientController(MainController mainCont, Patient patient) {
		super(mainCont); //references the same datastorage that was initialised
		this.currentPatient=patient;
	}
	
	public AppointmentSystem getApptSys() {
		return this.getDataStorage().retrieveApptSys();
	}

	public void addToScheduled(Appointment selectedAppt) {
		this.getDataStorage().patientSchAppt(this.currentPatient,selectedAppt);
		System.out.println("Request for Appointment under Dr "+selectedAppt.getDoctor().getName()
		+" at "+selectedAppt.getDate() +" "+selectedAppt.getTime()+" has been sent");
	}

	public void reschAppt(Appointment oldAppt, Appointment newSelectedAppt) {
		this.getDataStorage().revertOldAppt(oldAppt);
		this.addToScheduled(newSelectedAppt);
	}

	public void cancelAppt(Appointment appt) {
		this.getDataStorage().cancelAppt(this.currentPatient,appt);
	}

	public ArrayList<Doctor> getDocList() {
		return this.getDataStorage().retrieveDoctors();
	}

	public void save() {
		this.getDataStorage().save();
	}
}
