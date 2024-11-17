package hmsProject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Patient's Medical Records
 */
public class MedicalRecord implements Serializable {
	private String pID;
	private String name;
	private String DOB;
	private String gender;
	private String phone;
	private String email;
	private String bloodType;
	private ArrayList<Patient> dependencies;
	private ArrayList<Diagnosis> diagnoses;
	private ArrayList<Treatment> treatments;

	/**
	 * MedicalRecord constructor that creates empty diagnosis and treatment lists.
	 */
	public MedicalRecord() {
		diagnoses = new ArrayList<Diagnosis>();
		treatments = new ArrayList<Treatment>();
		dependencies = new ArrayList<Patient>();
	}

	/**
	 * MedicalRecord constructor that initialises all attributes and creates empty diagnosis and treatment lists.
	 * @param pID unique user identifier
	 * @param name Patient name
	 * @param DOB Patient date of birth
	 * @param gender Patient gender
	 * @param phone Patient phone number
	 * @param email Patient email
	 * @param bloodType Patient blood type
	 */
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
		this.dependencies = new ArrayList<Patient>();
	}

	/**
	 * id/username getter.
	 * @return id.
	 */
	public String getpID() {
		return pID;
	}

	/**
	 * id setter.
	 * @param pID new id
	 */
	public void setpID(String pID) {
		this.pID = pID;
	}

	/**
	 * name getter.
	 * @return name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * name setter.
	 * @param name new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Date of birth getter.
	 * @return date of birth.
	 */
	public String getDOB() {
		return DOB;
	}

	/**
	 * Date of birth setter.
	 * @param DOB new date of birth
	 */
	public void setDOB(String DOB) {
		this.DOB = DOB;
	}

	/**
	 * Gender getter.
	 * @return gender.
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Gender setter.
	 * @param gender new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Phone getter.
	 * @return phone.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Phone setter.
	 * @param phone new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Email getter.
	 * @return email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Email setter.
	 * @param email new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Blood type getter.
	 * @return blood type.
	 */
	public String getBloodType() {
		return bloodType;
	}

	/**
	 * Blood type setter.
	 * @param bloodType new blood type
	 */
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	/**
	 * Diagnoses List getter.
	 * @return Diagnoses List.
	 */
	public ArrayList<Diagnosis> getDiagnoses() {
		return diagnoses;
	}

	/**
	 * Diagnoses List setter.
	 * @param diagnoses new DiagnosesList
	 */
	public void setDiagnoses(ArrayList<Diagnosis> diagnoses) {
		this.diagnoses = diagnoses;
	}
	/**
	 * Treatments List getter.
	 * @return TreatmentsList.
	 */
	public ArrayList<Treatment> getTreatments() {
		return treatments;
	}

	/**
	 * Treatments List setter.
	 * @param treatments new TreatmentList
	 */
	public void setTreatments(ArrayList<Treatment> treatments) {
		this.treatments = treatments;
	}

	/**
	 * Add new Diagnosis into DiagnosesList.
	 * @param newDiagnosis new Diagnosis
	 */
	public void updateDiagnosis(Diagnosis newDiagnosis) {
		diagnoses.add(newDiagnosis);
	}

	/**
	 * Add new Treatment into TreatmentList.
	 * @param newTreatment new Treatment
	 */
	public void updateTreatment(Treatment newTreatment) {
		treatments.add(newTreatment);
	}

	/**
	 * Dependencies list getter.
	 * @return dependencies list.
	 */
	public ArrayList<Patient> getDependencies() {
		return this.dependencies;
	}

	/**
	 * Adding dependant into dependencies list.
	 * @param person dependant
	 */
	public void addDependencies(Patient person) {
		dependencies.add(person);
	}

	/**
	 * Removing dependant from dependencies list.
	 * @param patient dependant
	 */
	public void removeDependencies(Patient patient) {
		dependencies.remove(patient);
	}

	/**
	 * Print everything.
	 */
	public void viewAll() {	
		System.out.println("\nPatient ID: " + getpID());
		System.out.println("Name: " + getName());
		System.out.println("DOB: " + getDOB());
		System.out.println("Gender: " + getGender());
		System.out.println("Phone: " + getPhone());
		System.out.println("Email: " + getEmail());
		System.out.println("Blood Type: " + getBloodType());

		System.out.println("\nDependencies:");
		for(Patient p : getDependencies()) {
			System.out.println("- Date: " + p.getMedicalRecord().getName());
		}

		System.out.println("\nDiagnoses:");
		for(Diagnosis diag : getDiagnoses()) {
			System.out.println("- Date: " + diag.getDiagnosisDate());
			System.out.println("  Description: " + diag.getDescription());
			System.out.println("  Severity: " + diag.getSeverity());
			System.out.println("  Diagnosed by: Dr. " + diag.getDiagnosedBy());
		}

		System.out.println("\nTreatments:");
		for(Treatment treat : getTreatments()) {
			System.out.println("- Date: " + treat.getTreatmentDate());
			System.out.println("  Description: " + treat.getDescription());
			System.out.println("  Treatment by: Dr. " + treat.getTreatmentBy());
		}
		
	}

	/**
	 * Input for adding new Diagnosis or Treatment.
	 * @param sc Scanner class for input
	 * @param drName Doctor name
	 */
	public void updatePatientRecord(Scanner sc, String drName) {
		System.out.println("1. Add Diagnosis");
		System.out.println("2. Add Treatment");
		System.out.println("Enter choice:");
		int choice = sc.nextInt();
		sc.nextLine();
		if(choice == 1) {
			updateDiagnosis(Diagnosis.createDiagosis(sc, drName));

		} else if(choice == 2) {

			updateTreatment(Treatment.createTreatment(sc, drName));
		}

		System.out.println("Medical record updated successfully!");
	}
}