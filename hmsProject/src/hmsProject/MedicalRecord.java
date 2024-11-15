package hmsProject;

import java.io.Serializable;
import java.util.ArrayList;

public class MedicalRecord implements Serializable {
	private String pID;
	private String name;
	private String DOB;
	private String gender;
	private String phone;
	private String email;
	private String bloodType;
	private ArrayList<Diagnosis> diagnoses;
	private ArrayList<Treatment> treatments;

	public MedicalRecord() {
		diagnoses = new ArrayList<Diagnosis>();
		treatments = new ArrayList<Treatment>();
	}
	
	public MedicalRecord(String pID, String name, String DOB, String gender, 
            String phone, String email, String bloodType) {
	this.pID = pID;
	this.name = name;
	this.DOB = DOB;
	this.gender = gender;
	this.phone = phone;
	this.email = email;
	this.bloodType = bloodType;
	
	// Initialize ArrayLists as in the default constructor
	this.diagnoses = new ArrayList<Diagnosis>();
	this.treatments = new ArrayList<Treatment>();
	}
	
	public String getpID() {
		return pID;
	}

	public void setpID(String pID) {
		this.pID = pID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String DOB) {
		this.DOB = DOB;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public ArrayList<Diagnosis> getDiagnoses() {
		return diagnoses;
	}

	public void setDiagnoses(ArrayList<Diagnosis> diagnoses) {
		this.diagnoses = diagnoses;
	}

	public ArrayList<Treatment> getTreatments() {
		return treatments;
	}

	public void setTreatments(ArrayList<Treatment> treatments) {
		this.treatments = treatments;
	}

	public void updateDiagnosis(Diagnosis newDiagnosis) {
		diagnoses.add(newDiagnosis);
	}

	public void updateTreatment(Treatment newTreatment) {
		treatments.add(newTreatment);
	}

}