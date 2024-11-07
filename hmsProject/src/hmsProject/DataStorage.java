package hmsProject;

import java.util.ArrayList;

public class DataStorage {
	private ArrayList<User> user;
	private AppointmentSystem apptSystem;
	private DataSerialization dataOps;
	
	public DataStorage(){
		this.dataOps =new DataSerialization();
		this.apptSystem=this.retrieveApptSys();

		//AFTER INITIALISING THE FILES, COMMENT OUT EVERYTHING BELOW IF YOU WANT TO ACTUALLY SAVE

		this.apptSystem=new AppointmentSystem();
		this.user=new ArrayList<User>();
		user.add(new Patient("p1","pw1"));
		MedicalRecord medRecord = new MedicalRecord();
		medRecord.setpID(1);
		medRecord.setName("Patient 1");
		medRecord.setDOB("11/01/12");
		medRecord.setGender("F");
		medRecord.setPhone("12349876");
		medRecord.setEmail("Patient1@tmail.com");
		medRecord.setBloodType("B+");
		((Patient) user.get(0)).setMedicalRecord(medRecord);
		
		user.add(new Doctor("d1","pw2","dr"));
		Doctor d1=(Doctor) user.get(1);
		d1.addPatient(((Patient)user.get(0)));
		d1.addAvailAppointment(new Appointment("Available",d1,"1/11/2024", "1000"));
		//.out.println("DataStorage initialised")
		apptSystem.addDoc(d1);
		this.saveUser(user.get(0));
		this.saveUser(user.get(1));
		this.saveApptSys();

	}
	
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
		/*
		int cnt=0;
		for(User rUser: user) {
			if( rUser.gethID().equals(id) && rUser.getPw().equals(pw)) {
				System.out.println("Account Verified");
				return user.get(cnt);
			}
			cnt++;
		}
		return null;
		 */
        return null;
    }
	
	public AppointmentSystem getApptSys() {
		return this.apptSystem;
	}

	public void saveUser(User userToSave) { dataOps.serialiseUser(userToSave); }

	public void saveApptSys() { dataOps.serialiseApptSys(this.getApptSys()); }

	public User retrieveUser(String id) { return dataOps.deserialiseUser(id); }

	public AppointmentSystem retrieveApptSys() { return dataOps.deserialiseApptSys(); }

	public void declineAppt(Doctor dr,Appointment appt) {
		dr.getComingAppt().remove(appt);
		appt.getPatient().removeAppt(appt);
		this.saveUser(appt.getPatient());
		this.saveUser(dr);
		this.saveApptSys();
	}

	public void patientSchAppt(Patient currentPatient, Appointment selectedAppt) {
		currentPatient.getAppt().add(selectedAppt);
		selectedAppt.setStatus("Pending");
		selectedAppt.setPatient(currentPatient);
		Doctor docOfAppt=selectedAppt.getDoctor();
		docOfAppt.updateApptReq(selectedAppt);
		this.saveUser(currentPatient);
		this.saveUser(docOfAppt);
		this.saveApptSys();
	}

	public void revertOldAppt(Appointment oldAppt) {
		Doctor docOfAppt=oldAppt.getDoctor();
		oldAppt.setPatient(null);
		oldAppt.setStatus("Available");
		docOfAppt.revertSetAppointment(oldAppt);
		this.saveUser(oldAppt.getPatient());
		this.saveUser(docOfAppt);
		this.saveApptSys();
	}

	public void cancelAppt(Patient patient, Appointment appt) {
		appt.setStatus("Available");
		Doctor docOfAppt=appt.getDoctor();
		docOfAppt.revertSetAppointment(appt);
		patient.removeAppt(appt);
		this.saveUser(patient);
		this.saveUser(docOfAppt);
		this.saveApptSys();
	}

	public void acceptAppt(Appointment appt) {
		this.apptSystem.addSchAppt(appt);
		Doctor docOfAppt=appt.getDoctor();
		appt.setStatus("Confirmed");
		docOfAppt.updateComingAppt(appt);
		this.saveUser(appt.getPatient());
		this.saveUser(docOfAppt);
		this.saveApptSys();
	}

	public void updateCompletedAppt(Appointment appt) {
		this.getApptSys().addCompAppt(appt);
		appt.setStatus("Completed");
		appt.getDoctor().completedAppt(appt);
		appt.setApptOutcomeRecord(OutcomeRecord.createOutcomeRecord(appt));
		this.getApptSys().addOutcomeRecord(appt.getApptOutcomeRecord());
		this.saveUser(appt.getPatient());
		this.saveUser(appt.getDoctor());
		this.saveApptSys();
	}
	
	
}
