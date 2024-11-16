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
	
	public void setAppt(AppointmentSystem apptSys) {
		this.apptSystem=apptSys;
	}

	public void setInven(Inventory inven) {
		this.inven=inven;
	}

	public void setUser(ArrayList<User> user) {
		this.user=user;
	}
	
	public void save() {
		dataOps.serializeAll(apptSystem, inven, user);
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
        return null;
    }

	public User retrieveUser(String id) {
		for(User u: this.user) {
			if(u.gethID().equals(id)) return u;
		}
		return null;
	}
	
	public ArrayList<Doctor> retrieveDoctors(){
		return this.docList;
	}

	public AppointmentSystem retrieveApptSys() {
		return this.apptSystem;
	}

	public Inventory getInventory(){
		return this.inven;
	}

	public void declineAppt(Doctor dr,Appointment appt) {
		appt.setStatus("Cancelled");
		dr.removeApptReq(appt);
		appt.getPatient().removeAppt(appt);
		this.retrieveApptSys().removeSchAppt(appt);
		this.retrieveApptSys().addCompAppt(appt); //cancelled appointments are recorded as completed appt
		this.save();
	}

	public void patientSchAppt(Patient currentPatient, Appointment selectedAppt) {
		currentPatient.getAppt().add(selectedAppt);
		selectedAppt.setStatus("Pending");
		selectedAppt.setPatient(currentPatient);
		Doctor docOfAppt=selectedAppt.getDoctor();
		docOfAppt.updateApptReq(selectedAppt);
		this.retrieveApptSys().addSchAppt(selectedAppt); //added pending appointments
		this.save();
	}

	public void revertOldAppt(Appointment oldAppt) {
		Doctor docOfAppt=oldAppt.getDoctor();
		oldAppt.setPatient(null);
		oldAppt.setStatus("Available");
		docOfAppt.revertSetAppointment(oldAppt);
		this.retrieveApptSys().removeSchAppt(oldAppt);
		this.save();
	}

	public void cancelAppt(Patient patient, Appointment appt) {
		appt.setStatus("Available");
		Doctor docOfAppt=appt.getDoctor();
		docOfAppt.revertSetAppointment(appt);
		patient.removeAppt(appt);
		this.retrieveApptSys().removeSchAppt(appt);
		this.save();
	}

	public void acceptAppt(Appointment appt) {
		Doctor docOfAppt=appt.getDoctor();
		appt.setStatus("Confirmed");
		docOfAppt.updateComingAppt(appt);
		this.save();
	}

	public void updateCompletedAppt(Appointment appt) {
		this.retrieveApptSys().addCompAppt(appt);
		this.retrieveApptSys().removeSchAppt(appt);
		appt.setStatus("Completed");
		appt.getDoctor().completedAppt(appt);
		appt.setApptOutcomeRecord(OutcomeRecord.createOutcomeRecord(appt));
		this.retrieveApptSys().addOutcomeRecord(appt.getApptOutcomeRecord());
		appt.getPatient().removeAppt(appt);
		appt.getPatient().addCompAppt(appt);
		this.save();
	}
}
