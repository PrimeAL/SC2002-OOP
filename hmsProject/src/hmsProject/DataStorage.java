package hmsProject;
import java.util.ArrayList;

public class DataStorage {
	private ArrayList<User> user;
	private AppointmentSystem apptSystem;
	
	public DataStorage(){
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

		this.apptSystem=new AppointmentSystem();
		apptSystem.addDoc(d1);
		
	}
	
	public User getUser(String id, String pw) {
		int cnt=0;
		for(User rUser: user) {
			if( rUser.gethID().equals(id) && rUser.getPw().equals(pw)) {
				System.out.println("Account Verified");
				return user.get(cnt);
			}
			cnt++;
		}
		return null;
	}
	
	public AppointmentSystem getApptSys() {
		return this.apptSystem;
	}

	public void declineAppt(Doctor dr,Appointment appt) {
		// TODO Auto-generated method stub
		dr.getComingAppt().remove(appt);
		appt.getPatient().removeAppt(appt);
	}

	public void patientSchAppt(Patient currentPatient, Appointment selectedAppt) {
		// TODO Auto-generated method stub
		currentPatient.getAppt().add(selectedAppt);
		selectedAppt.setStatus("Pending");
		selectedAppt.setPatient(currentPatient);
		Doctor docOfAppt=selectedAppt.getDoctor();
		docOfAppt.updateApptReq(selectedAppt);
		
	}

	public void revertOldAppt(Appointment oldAppt) {
		// TODO Auto-generated method stub
		Doctor docOfAppt=oldAppt.getDoctor();
		oldAppt.setPatient(null);
		oldAppt.setStatus("Available");
		docOfAppt.revertSetAppointment(oldAppt);
	}

	public void cancelAppt(Patient patient, Appointment appt) {
		// TODO Auto-generated method stub
		appt.setStatus("Available");
		Doctor docOfAppt=appt.getDoctor();
		docOfAppt.revertSetAppointment(appt);
		patient.removeAppt(appt);
	}

	public void acceptAppt(Appointment appt) {
		// TODO Auto-generated method stub
		this.apptSystem.addSchAppt(appt);
		Doctor docOfAppt=appt.getDoctor();
		appt.setStatus("Confirmed");
		docOfAppt.updateComingAppt(appt);
	}

	public void updateCompletedAppt(Appointment appt) {
		// TODO Auto-generated method stub
		this.getApptSys().addCompAppt(appt);
		appt.setStatus("Completed");
		appt.getDoctor().completedAppt(appt);
		appt.setApptOutcomeRecord(OutcomeRecord.createOutcomeRecord(appt));
		this.getApptSys().addOutcomeRecord(appt.getApptOutcomeRecord());
		
	}
	
	
}
