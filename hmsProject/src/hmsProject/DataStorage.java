package hmsProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class DataStorage {
	//private ArrayList<User> user;
	private AppointmentSystem apptSystem;
	private DataSerialization dataOps;
	private Inventory inventory;
	
	public DataStorage(){
		this.dataOps =new DataSerialization();
		this.apptSystem=this.retrieveApptSys();

		System.out.println("To refresh data, key in 1. Otherwise, key whatever. ");
		Scanner scanner = new Scanner(System.in); //This is just for testing only. Will remove in final product.
		if (scanner.nextInt() == 1) {
			this.apptSystem = new AppointmentSystem();
			this.inventory = new Inventory(); //test
			this.initialisingPatientData();
			this.initialisingStaffData();
			this.initialisingMedicineData();
			Doctor d1 = this.getApptSys().getFirstDocForTesting(); //I had no choice but to do this for testing because
																	// ApptSys keeps its own Doctors list. This can be avoided if
																	//ApptSys retrieves its Doctors from database instead of having its own storage
			d1.addPatient(((Patient) retrieveUser("P1001")));
			d1.addAvailAppointment(new Appointment("Available", d1, "1/11/2024", "1000")); //Just for testing
			
			
			this.saveApptSys();

			
			//this.saveInventory();
		}
		scanner.nextLine();//Clear buffer

		/*
		this.user=new ArrayList<User>();
		user.add(new Doctor("d1","pw2","dr"));
		Doctor d1=(Doctor) user.get(1);
		d1.addPatient(((Patient)user.get(0)));
		d1.addAvailAppointment(new Appointment("Available",d1,"1/11/2024", "1000"));
		//.out.println("DataStorage initialised")
		apptSystem.addDoc(d1);
		this.saveUser(user.get(0));
		this.saveUser(user.get(1));
		 */
	}

	public void initialisingMedicineData() {
		String line = "";
		String splitBy = ",";

		try
		{
			FileReader file = new FileReader("hmsProject/src/hmsProject/database/Medicine_List.csv");
			BufferedReader br = new BufferedReader(file);
			while ((line = br.readLine()) != null)
			{
				String[] medicine = line.split(splitBy);
				if (Objects.equals(medicine[0], "Medicine Name")) { //To ignore first line of csv which are headers
					continue;
				}
				Medicine newMedicine = new Medicine(medicine[0], Integer.parseInt(medicine[1]), Integer.parseInt(medicine[2]));
				this.saveMedicine(newMedicine);
				inventory.addMedicine(newMedicine); //add medicine into inventory
			}

			
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e);
		}
	}

	public void initialisingStaffData() {
		String line = "";
		String splitBy = ",";

		try
		{
			FileReader file = new FileReader("hmsProject/src/hmsProject/database/Staff_List.csv");
			BufferedReader br = new BufferedReader(file);
			while ((line = br.readLine()) != null)
			{
				String[] staff = line.split(splitBy);
				if (Objects.equals(staff[0], "Staff ID")) { //To ignore first line of csv which are headers
					continue;
				}
				switch (staff[2]) {
					case "Doctor":
						Doctor newDoctor = new Doctor(staff[0], "default", staff[1], staff[3], Integer.parseInt(staff[4]));
						apptSystem.addDoc(newDoctor);
						this.saveUser(newDoctor);
						break;
					case "Pharmacist":
						Pharmacist newPharmacist = new Pharmacist(staff[0], "default", staff[1], staff[3], Integer.parseInt(staff[4]));
						this.saveUser(newPharmacist);
						break;
					case "Administrator":
						Administrator newAdmin = new Administrator(staff[0], "default", staff[1], staff[3], Integer.parseInt(staff[4]));
						this.saveUser(newAdmin);
						break;
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e);
		}
	}

	public void initialisingPatientData() {
		String line = "";
		String splitBy = ",";

		try
		{
			FileReader file = new FileReader("hmsProject/src/hmsProject/database/Patient_List.csv");
			BufferedReader br = new BufferedReader(file);
			while ((line = br.readLine()) != null)
			{
				String[] patient = line.split(splitBy);
				if (Objects.equals(patient[0], "Patient ID")) { //To ignore first line of csv which are headers
					continue;
				}
				Patient newPatient = new Patient(patient[0], "default");
				MedicalRecord newMedicalRecord = new MedicalRecord();
				newMedicalRecord.setName(patient[1]);
				newMedicalRecord.setDOB(patient[2]);
				newMedicalRecord.setGender(patient[3]);
				newMedicalRecord.setBloodType(patient[4]);
				newMedicalRecord.setEmail(patient[5]);
				newPatient.setMedicalRecord(newMedicalRecord);
				this.saveUser(newPatient);
			}
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e);
		}
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

	public Inventory getInventory() { //test
		return this.inventory;
	}

	public void saveUser(User userToSave) { dataOps.serialiseUser(userToSave); }

	public void saveApptSys() { dataOps.serialiseApptSys(this.getApptSys()); }

	public void saveMedicine(Medicine medicine) { dataOps.serialiseMedicine(medicine); }

	//public void saveInventory() { dataOps.serialiseInventory(this.getInventory()); } //test

	public User retrieveUser(String id) { return dataOps.deserialiseUser(id); }

	public AppointmentSystem retrieveApptSys() { return dataOps.deserialiseApptSys(); }

	public Medicine retrieveMedicine(String medicine) { return dataOps.deserialiseMedicine(medicine); } //test


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
