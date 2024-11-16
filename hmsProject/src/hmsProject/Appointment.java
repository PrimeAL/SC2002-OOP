package hmsProject;

import java.io.Serializable;

/**
 * Appointment class which contains information about an appointment
 */
public class Appointment implements Serializable {
	private String status;
	private Doctor doctor;
	private Patient patient;
	private String date;
	private String time;
	private OutcomeRecord apptOutcomeRecord;

	/**
	 * Appointment class constructor
	 * @param status
	 * @param doctor
	 * @param date
	 * @param time
	 */
	public Appointment(String status, Doctor doctor, String date, String time) {
		//Appointment will be created through doctor availability and is open as a timeslot that is viewable by the patient
		//through the appointment system.
		//Appointment are created as open time slots initially, so patient and outcomeRecord should have the default value
		this.status=status;
		this.doctor=doctor;
		this.patient=null;
		this.date=date;
		this.time=time;
		this.apptOutcomeRecord=null;
	}
	
	/**
	 * Status attribute Getter
	 * @return String
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Status attribute Setter
	 * @param status
	 */
	public void setStatus(String status) {
		this.status=status;
	}

	/**
	 * Doctor attribute Getter
	 * @return Doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * Doctor attribute Setter
	 * @param doctor
	 */
	private void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * Patient attribute Getter
	 * @return Patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Patient attribute Setter
	 * @param patient
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Date attribute Getter
	 * @return String
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Date attribute Setter
	 * @param date String
	 */
	private void setDate(String date) {
		this.date = date;
	}

	/**
	 * Time attribute Getter
	 * @return String
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Time attribute Setter
	 * @param time
	 */
	private void setTime(String time) {
		this.time = time;
	}

	/**
	 * OutcomeRecord class Getter
	 * @return OutcomeRecord
	 */
	public OutcomeRecord getApptOutcomeRecord() {
		return apptOutcomeRecord;
	}

	/**
	 * OutcomeRecord attribute Setter
	 * @param apptOutcomeRecord
	 */
	public void setApptOutcomeRecord(OutcomeRecord apptOutcomeRecord) {
		this.apptOutcomeRecord = apptOutcomeRecord;
	}

}