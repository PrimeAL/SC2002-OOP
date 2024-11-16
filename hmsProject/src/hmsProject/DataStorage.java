package hmsProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class DataStorage {
	private ArrayList<User> user;
	private AppointmentSystem apptSystem;
	private Inventory inven;
	private DataSerialization dataOps;
	private ArrayList<Doctor> docList;
	
	public DataStorage(){
		this.dataOps = new DataSerialization();
		System.out.println("To refresh data, key in 1. Otherwise, key whatever. ");
		Scanner scanner = new Scanner(System.in); //This is just for testing only. Will remove in final product.
		
		this.apptSystem=new AppointmentSystem();
		this.inven=new Inventory();
		this.user=new ArrayList<User>();
		
		if (scanner.nextInt() == 1) {
			/*this.inventory = new Inventory(); //test
			this.apptSystem = new AppointmentSystem();
			this.initialisingPatientData();
			this.initialisingStaffData();
			this.initialisingMedicineData();
			Doctor d1 = this.getApptSys().getFirstDocForTesting(); //I had no choice but to do this for testing because
																	// ApptSys keeps its own Doctors list. This can be avoided if
																	//ApptSys retrieves its Doctors from database instead of having its own storage
			d1.addPatient(((Patient) retrieveUser("P1001")));
			d1.addAvailAppointment(new Appointment("Available", d1, "1/11/2024", "1000")); //Just for testing
			this.saveApptSys();*/

			dataOps.initializeUser(this.user);
			dataOps.initializeMedData(this.inven);
			dataOps.serializeAll(apptSystem, inven, user);
		}
		else {
			//this.apptSystem =null;
			//this.user=null;
			//this.inven=null;
			//dataOps.deserializeAll(this.apptSystem,this.inven,this.user);
			dataOps.deserializeAll(this);
		}
		this.docList=new ArrayList<Doctor>();
		for(User u:this.user) {
			if(u.gethID().startsWith("D")) this.addToDocList(u); //doctor
		}
		scanner.nextLine();//Clear buffer
		
	}
	
	public void setAppt(AppointmentSystem apptSys) {
		// TODO Auto-generated method stub
		this.apptSystem=apptSys;
	}

	public void setInven(Inventory inven) {
		// TODO Auto-generated method stub
		this.inven=inven;
	}

	public void setUser(ArrayList<User> user) {
		// TODO Auto-generated method stub
		this.user=user;
	}
	
	public void save() {
		dataOps.serializeAll(apptSystem, inven, user);
		dataOps.serialiseInventory(inven);
	}
	
	public void addToDocList(User user) {
		this.docList.add((Doctor)user);
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

	public void saveUser(User userToSave) { dataOps.serialiseUser(userToSave); }

	public void saveApptSys() { dataOps.serialiseApptSys(this.retrieveApptSys()); }

	public void saveMedicine(Medicine medicine) { dataOps.serialiseMedicine(medicine); }

	public void saveInventory(Inventory inven) { dataOps.serialiseInventory(this.getInventory()); } //test

	public User retrieveUser(String id) { //return dataOps.deserialiseUser(id); 
		for(User u: this.user) {
			if(u.gethID().equals(id)) return u;
		}
		return null;
	}
	
	public ArrayList<Doctor> retrieveDoctors(){
		return this.docList;
	}

	public AppointmentSystem retrieveApptSys() { //return dataOps.deserialiseApptSys(); 
		return this.apptSystem;
	}

	public Inventory getInventory(){
		return this.inven; //Test
	}

	public Medicine retrieveMedicine(String medicine) { return dataOps.deserialiseMedicine(medicine); } //test

	public void declineAppt(Doctor dr,Appointment appt) {
		appt.setStatus("Cancelled");
		dr.getComingAppt().remove(appt);
		appt.getPatient().removeAppt(appt);
		this.save();
		//this.saveUser(appt.getPatient());
		//this.saveUser(dr);
		//this.saveApptSys();
	}

	public void patientSchAppt(Patient currentPatient, Appointment selectedAppt) {
		currentPatient.getAppt().add(selectedAppt);
		selectedAppt.setStatus("Pending");
		selectedAppt.setPatient(currentPatient);
		Doctor docOfAppt=selectedAppt.getDoctor();
		docOfAppt.updateApptReq(selectedAppt);
		this.retrieveApptSys().addSchAppt(selectedAppt); //added pending appointments
		this.save();
		//this.saveUser(currentPatient);
		//this.saveUser(docOfAppt);
		//this.saveApptSys();
	}

	public void revertOldAppt(Appointment oldAppt) {
		Doctor docOfAppt=oldAppt.getDoctor();
		oldAppt.setPatient(null);
		oldAppt.setStatus("Available");
		docOfAppt.revertSetAppointment(oldAppt);
		this.save();
		//this.saveUser(oldAppt.getPatient());
		//this.saveUser(docOfAppt);
		//this.saveApptSys();
	}

	public void cancelAppt(Patient patient, Appointment appt) {
		appt.setStatus("Available");
		Doctor docOfAppt=appt.getDoctor();
		docOfAppt.revertSetAppointment(appt);
		patient.removeAppt(appt);
		this.save();
		//this.saveUser(patient);
		//this.saveUser(docOfAppt);
		//this.saveApptSys();
	}

	public void acceptAppt(Appointment appt) {
		Doctor docOfAppt=appt.getDoctor();
		appt.setStatus("Confirmed");
		docOfAppt.updateComingAppt(appt);
		this.retrieveApptSys().addSchAppt(appt);
		this.save();
		//this.saveUser(appt.getPatient());
		//this.saveUser(docOfAppt);
		//this.saveApptSys();
	}

	public void updateCompletedAppt(Appointment appt) {
		this.retrieveApptSys().addCompAppt(appt);
		appt.setStatus("Completed");
		appt.getDoctor().completedAppt(appt);
		appt.setApptOutcomeRecord(OutcomeRecord.createOutcomeRecord(appt));
		this.retrieveApptSys().addOutcomeRecord(appt.getApptOutcomeRecord());
		this.save();
		//this.saveUser(appt.getPatient());
		//this.saveUser(appt.getDoctor());
		//this.saveApptSys();
	}

	public void updatePhone(Patient currentPatient, String phoneNum) {
		// TODO Auto-generated method stub
		currentPatient.getMedicalRecord().setPhone(phoneNum);
	}

	public void updateEmail(Patient currentPatient, String email) {
		// TODO Auto-generated method stub
		currentPatient.getMedicalRecord().setEmail(email);
	}

	public void updateAvailAppt(Doctor currentDoctor, Appointment newAppt) {
		// TODO Auto-generated method stub
		currentDoctor.addAvailAppointment(newAppt);
	}	

	//Admin needs user List
	public ArrayList<User> getUserList() { return user; }
	
}
