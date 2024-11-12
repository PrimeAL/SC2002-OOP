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
		// TODO Auto-generated method stub
		this.getDataStorage().patientSchAppt(this.currentPatient,selectedAppt);
		System.out.println("Request for Appointment under Dr "+selectedAppt.getDoctor().getName()
		+" at "+selectedAppt.getDate() +" "+selectedAppt.getTime()+" has been sent");
	}

	public void reschAppt(Appointment oldAppt, Appointment newSelectedAppt) {
		// TODO Auto-generated method stub
		this.getDataStorage().revertOldAppt(oldAppt);
		this.addToScheduled(newSelectedAppt);
	}

	public void cancelAppt(Appointment appt) {
		// TODO Auto-generated method stub
		this.getDataStorage().cancelAppt(this.currentPatient,appt);
	}

	public ArrayList<Doctor> getDocList() {
		// TODO Auto-generated method stub
		return this.getDataStorage().retrieveDoctors();
	}

	public void updatePhone(String phoneNum) {
		// TODO Auto-generated method stub
		this.getDataStorage().updatePhone(currentPatient,phoneNum);
	}

	public void updateEmail(String email) {
		// TODO Auto-generated method stub
		this.getDataStorage().updateEmail(currentPatient,email);
	}
}
