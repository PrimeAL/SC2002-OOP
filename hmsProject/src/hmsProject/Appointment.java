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
	 * @param status status of appointment: Available, Pending, Confirmed
	 * @param doctor doctor-in-charge of appointment
	 * @param date date of appointment
	 * @param time time of appointment
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
	 * @return status of appointment.
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Status attribute Setter
	 * @param status new status
	 */
	public void setStatus(String status) {
		this.status=status;
	}

	/**
	 * Doctor attribute Getter
	 * @return the Doctor class of the doctor-in-charge.
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * Doctor attribute Setter
	 * @param doctor new Doctor class
	 */
	private void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * Patient attribute Getter
	 * @return the Patient class of the appointment.
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Patient attribute Setter
	 * @param patient new Patient class of the appointment
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Date attribute Getter
	 * @return date of appointment.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Date attribute Setter
	 * @param date new date
	 */
	private void setDate(String date) {
		this.date = date;
	}

	/**
	 * Time attribute Getter
	 * @return time of appointment.
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Time attribute Setter
	 * @param time new time
	 */
	private void setTime(String time) {
		this.time = time;
	}

	/**
	 * OutcomeRecord class Getter
	 * @return OutcomeRecord class of appointment.
	 */
	public OutcomeRecord getApptOutcomeRecord() {
		return apptOutcomeRecord;
	}

	/**
	 * OutcomeRecord attribute Setter
	 * @param apptOutcomeRecord new OutcomeRecord class
	 */
	public void setApptOutcomeRecord(OutcomeRecord apptOutcomeRecord) {
		this.apptOutcomeRecord = apptOutcomeRecord;
	}

}