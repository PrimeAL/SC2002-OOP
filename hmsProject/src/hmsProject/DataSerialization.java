package hmsProject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Objects;

public class DataSerialization {
    public void serializeAll(AppointmentSystem apptSystem, Inventory inven,ArrayList<User> user) {
    	try {
    		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("hmsProject/src/hmsProject/newDB.ser"));
    		out.writeObject(apptSystem);
    		out.writeObject(inven);
    		out.writeObject(user);
    	} catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
	public void deserializeAll(DataStorage dataStorage) {
		// TODO Auto-generated method stub
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("hmsProject/src/hmsProject/newDB.ser"));
			dataStorage.setAppt((AppointmentSystem)in.readObject());
			dataStorage.setInven((Inventory)in.readObject());
			dataStorage.setUser((ArrayList<User>)in.readObject());
			in.close();
		} catch (ClassNotFoundException ex) {
            System.out.println("File not found");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
	}

	public void initializeUser(ArrayList<User> user) {
		// TODO Auto-generated method stub
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
						user.add(newDoctor);
						break;
					case "Pharmacist":
						Pharmacist newPharmacist = new Pharmacist(staff[0], "default", staff[1], staff[3], Integer.parseInt(staff[4]));
						user.add(newPharmacist);
						break;
					case "Administrator":
						Administrator newAdmin = new Administrator(staff[0], "default", staff[1], staff[3], Integer.parseInt(staff[4]));
						user.add(newAdmin);
						break;
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e);
		}

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
                newMedicalRecord.setpID(patient[0]);
				newMedicalRecord.setName(patient[1]);
				newMedicalRecord.setDOB(patient[2]);
				newMedicalRecord.setGender(patient[3]);
				newMedicalRecord.setBloodType(patient[4]);
				newMedicalRecord.setEmail(patient[5]);
				newPatient.setMedicalRecord(newMedicalRecord);
				user.add(newPatient);
			}
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e);
		}
	}

	public void initializeMedData(Inventory inven) {
		// TODO Auto-generated method stub
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
				inven.addMedicine(newMedicine);
			}
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e);
		}
	}
}
