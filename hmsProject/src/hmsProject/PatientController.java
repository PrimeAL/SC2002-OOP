package hmsProject;

import java.util.ArrayList;

public class PatientController extends controller{
	private Patient currentPatient;
	private String[] acceptedEmails = {"gmail.com", "hotmail.com", "e.ntu.edu.sg", "yahoo.com", "live.com", "msn.com"};
	
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

	public int updatePhone(String phoneNum) {
		// TODO Auto-generated method stub
		if (phoneNum.matches("[0-9]+")) {
			this.getDataStorage().updatePhone(currentPatient,phoneNum);
			return 0;
		}
		else return 1;
	}

	public int updateEmail(String email) {
		// TODO Auto-generated method stub
		for (String i : acceptedEmails) {
			if (email.endsWith(i)) {
				this.getDataStorage().updateEmail(currentPatient, email);
				return 0;
			}
		}
		return 2;
	}
}
