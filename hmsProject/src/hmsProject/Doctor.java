package hmsProject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Doctor extends User implements Serializable {
    private ArrayList<Patient> patients;
    private ArrayList<Appointment> availableAppt;
    private ArrayList<Appointment> apptRequest;
    private ArrayList<Appointment> comingAppt;
    private String name;
    private String gender;
    private int age;
    
    public Doctor(String uid, String pw, String name, String gender, int age) {
        super(uid,pw);
        this.patients = new ArrayList<Patient>();
        this.availableAppt = new ArrayList<Appointment>();
        this.apptRequest = new ArrayList<Appointment>();
        this.comingAppt = new ArrayList<Appointment>();
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
		
	public void revertSetAppointment(Appointment oldAppt) {
		//appointment either pending or confirmed
		if (oldAppt.getStatus().equals("Pending")) {
	        this.removeApptReq(oldAppt);
	        this.addAvailAppointment(oldAppt);
	    } else if (oldAppt.getStatus().equals("Confirmed")) {
	        this.removeComingAppt(oldAppt);
	        this.addAvailAppointment(oldAppt);
	    }
		
		Collections.sort(availableAppt, (a1, a2) -> {
	        int dateCompare = a1.getDate().compareTo(a2.getDate());
	        return dateCompare != 0 ? dateCompare : a1.getTime().compareTo(a2.getTime());
	    });
	}
	
	public void updateApptReq(Appointment appt) {
		this.addApptRequest(appt);		
		this.removeAvailAppt(appt);
	}
	
	
	public void updateComingAppt(Appointment appt) {
		this.removeApptReq(appt);
		this.addComingAppt(appt);
	}
	
	public void completedAppt(Appointment appt) {
		this.removeComingAppt(appt);
	}
	
	private void addComingAppt(Appointment appt) {
		this.comingAppt.add(appt);
	}
	
	public void removeApptReq(Appointment appt) {
		this.apptRequest.remove(appt);
	}
	
	public void addAvailAppointment(Appointment appt) {	
		this.availableAppt.add(appt);
	}
	
	private void removeComingAppt(Appointment appt) {
		this.comingAppt.remove(appt);
	}
	
	private void removeAvailAppt(Appointment appt) {
		this.availableAppt.remove(appt);
	}
	
	private void addApptRequest(Appointment appt) {
		this.apptRequest.add(appt);
	}
	
	public ArrayList<Appointment> getComingAppt(){
		return this.comingAppt;
	}
	
	public ArrayList<Appointment> getApptReq(){
		return this.apptRequest;
	}
	
	public ArrayList<Patient> getPatients() {
		return patients;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Appointment> getAvailableAppt() {
		return availableAppt;
	}
	
	public void userInterface(DoctorController docCont, Scanner sc) {
        System.out.println("Welcome Dr. " + this.name);
        int userMenuInput = -1;
        while(userMenuInput != 0) {
            this.displayMenu();
			try {
				userMenuInput = sc.nextInt();
				sc.nextLine();
				switch(userMenuInput) {
					case 1:
						viewMedRec(sc);
						break;
					case 2:
						updateMedRec(sc);
						docCont.save();
						break;
					case 3:
						viewPersonalSchedule();
						break;
					case 4:
						setAvailAppt(sc);
						docCont.save();
						break;
					case 5:
						apptOp(docCont, sc);
						break;
					case 6:
						viewUpcomingAppointments();
						break;
					case 7:
						recordAppointmentOutcome(docCont,sc);
						break;
					case 0:
						System.out.println("Logging out.\n");
						//printCompletedAppointments(docCont.getDataStorage().retrieveApptSys());
						break;
					default:
						System.out.println("Invalid input.\n");
						break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				sc.nextLine();
			}
		}
	}

	// For testing purposes
	private void printCompletedAppointments(AppointmentSystem apptSystem) {
		System.out.println("\n=== Completed Appointments ===");
		for (Appointment appt : apptSystem.getCompAppt()) {
			OutcomeRecord outcome = appt.getApptOutcomeRecord();
			System.out.println("Date: " + appt.getDate() +
					" | Time: " + appt.getTime() +
					" | Patient: " + appt.getPatient().getMedicalRecord().getName() +
					" | Status: " + appt.getStatus() +
					" | Outcome Date: " + outcome.getDateOfAppointment() +
					" | Service Provided: " + outcome.getServiceProvided() +
					" | Consultation Note: " + outcome.getConsultationNote());
			System.out.println("Prescribed Medications:");
			for (prescribedMed med : outcome.getMeds()) {
				System.out.println("- Medication Name: " + med.getMedicationName() +
						" | Status: " + med.getStatus());
			}
		}
	}


	public void displayMenu() {
		System.out.println("1. View Patient Medical Records\n2. Update Patient Medical Records\n"
				+ "3. View Personal Schedule\n4. Set Availability for Appointments\n"
				+ "5. Accept or Decline Appointment Requests\n6. View Upcoming Appointments\n"
				+ "7. Record Appointment Outcome\n0. Logout");
	}

	private void viewMedRec(Scanner sc) {
		System.out.println("\n=== View Patient Medical Records ===");

		if (patients.isEmpty()) {
			System.out.println("No patients assigned.");
		} else {
			listPatients();
			System.out.println("0. Exit");
			System.out.println("Enter patient number to view medical record:");
			try {
				int choice = sc.nextInt();
				if (choice > 0 && choice <= patients.size()) {
					Patient selectedPatient = patients.get(choice - 1);
					selectedPatient.getMedicalRecord().viewAll(); //print function copied to medical record
				} else if (choice != 0) {
					System.out.println("Invalid input.");
					viewMedRec(sc);
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				sc.nextLine();
				viewMedRec(sc);
			}
		}
		System.out.println();
	}

	private void updateMedRec(Scanner sc) {
		System.out.println("\n=== Update Patient Medical Records ===");

		if (patients.isEmpty()) {
			System.out.println("No patients assigned.");
		} else {
			listPatients();
			System.out.println("0. Exit");
			System.out.println("Enter patient number to update medical record:");
			try {
				int choice = sc.nextInt();
				sc.nextLine();
				if (choice > 0 && choice <= patients.size()) {
					Patient selectedPatient = patients.get(choice - 1);
					selectedPatient.getMedicalRecord().updatePatientRecord(sc, this.getName()); //updated to be abstracted to respective classes
				} else if (choice != 0) {
					System.out.println("Invalid input.");
					updateMedRec(sc);
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				sc.nextLine();
				updateMedRec(sc);
			}
		}
		System.out.println();
	}

	private void viewPersonalSchedule() {
		System.out.println("\n=== Personal Schedule ===");
		System.out.println("Available Appointments:");
		for(Appointment appt : availableAppt) {
			System.out.println("Date: " + appt.getDate() + " | Time: " + appt.getTime());
		}
		viewUpcomingAppointments();
	}

	private void setAvailAppt(Scanner sc) {
		System.out.println("\n=== Set Available Appointments ===");
		while(true) {
			String date;
			while(true) {
				System.out.println("Enter date (YYYY-MM-DD) or 0 to exit:");
				date = sc.nextLine();
				if(date.equals("0")) return;
				if (!UtilityClass.isValidDate(date)) {
					System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
					continue;
				}
				break;
			}
			String time;
			while(true) {
				System.out.println("Enter time (HH:mm) in 24-hour format:");
				time = sc.nextLine();
				if (!UtilityClass.isValidTime(time)) {
					System.out.println("Invalid time format. Please use HH:mm in 24-hour format.");
					System.out.println("Ensure time is between 00:00 and 23:59.");
					continue;
				}
				break;
			}
			Appointment newAppt = new Appointment("Available", this, date, time);
			this.addAvailAppointment(newAppt);
			System.out.println("Appointment slot added successfully!");
			System.out.println("Add another appointment? (Y/N)");
			if(!sc.nextLine().equalsIgnoreCase("Y")) break;
		}
		Collections.sort(availableAppt, (a1, a2) -> {
			int dateCompare = a1.getDate().compareTo(a2.getDate());
			return dateCompare != 0 ? dateCompare : a1.getTime().compareTo(a2.getTime());
		});
	}

	private void apptOp(DoctorController docCont, Scanner sc) {
		if (this.getApptReq().isEmpty()) {
			System.out.println("No appointment requests at the moment.\n");
			return;
		}

		System.out.println("\nPending Appointment Requests:");
		int cnt = 1;
		for (Appointment appt : this.getApptReq()) {
			System.out.println(cnt + ". Date: " + appt.getDate() +
					" | Time: " + appt.getTime() +
					" | Status: " + appt.getStatus() +
					" | Patient: " + appt.getPatient().getMedicalRecord().getName());
			cnt++;
		}
		System.out.println("\nSelect appointment number to update (1-" + (this.getApptReq().size()) + ") or 0 to cancel:");
		try {
			int apptSelect = sc.nextInt();
			sc.nextLine();
			if (apptSelect > 0 && apptSelect <= this.getApptReq().size()) {
				System.out.println("\nFor the selected appointment:");
				System.out.println("1. Accept");
				System.out.println("2. Decline");
				System.out.println("3. Cancel operation");
				int opSelect = sc.nextInt();
				sc.nextLine();
				Appointment selectedAppt = this.getApptReq().get(apptSelect - 1);
				switch (opSelect) {
					case 1:
						//docCont.getApptSys().acceptAppt(docCont, selectedAppt);
						docCont.addAccAppt(selectedAppt);
						Collections.sort(comingAppt, (a1, a2) -> {
							int dateCompare = a1.getDate().compareTo(a2.getDate());
							return dateCompare != 0 ? dateCompare : a1.getTime().compareTo(a2.getTime());
						});
						addPatient(selectedAppt.getPatient());
						docCont.save();
						System.out.println("Appointment accepted successfully.");
						break;
					case 2:
						docCont.declineAppt(selectedAppt);
						System.out.println("Appointment declined successfully.");
						break;
					case 3:
						System.out.println("Operation cancelled.");
						break;
					default:
						System.out.println("Invalid option selected.");
						break;
				}
			} else if (apptSelect != 0) {
				System.out.println("Invalid appointment number.");
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid input.");
			sc.nextLine();
			apptOp(docCont, sc);
		}
		System.out.println();
	}

    private void viewUpcomingAppointments() {
        System.out.println("\n=== Upcoming Appointments ===");
        if(comingAppt.isEmpty()) {
            System.out.println("No upcoming appointments.");
        }
        else {
        	for(Appointment appt : comingAppt) {
                System.out.println("Date: " + appt.getDate() +
                                 " | Time: " + appt.getTime() +
                                 " | Patient: " + appt.getPatient().getMedicalRecord().getName() +
                                 " | Status: " + appt.getStatus());
            }
        }
        System.out.println();
    }

	private void recordAppointmentOutcome(DoctorController docCont,Scanner sc) {
		System.out.println("\n=== Record Appointment Outcome ===");
		if (comingAppt.isEmpty()) {
			System.out.println("No appointments to record outcome for.\n");
			return;
		}
		System.out.println("Select appointment to record outcome (0 to exit):");
		for (int i = 0; i < comingAppt.size(); i++) {
			Appointment appt = comingAppt.get(i);
			System.out.println((i + 1) + ". Date: " + appt.getDate() +
					" | Time: " + appt.getTime() +
					" | Patient: " + appt.getPatient().getMedicalRecord().getName());
		}
		try {
			int choice = sc.nextInt();
			sc.nextLine();
			if (choice > 0 && choice <= comingAppt.size()) {
				Appointment selectedAppt = comingAppt.get(choice - 1);
				docCont.updateAppt(selectedAppt);
				this.removeComingAppt(selectedAppt);
				System.out.println("Appointment outcome recorded successfully!");
			} else if (choice != 0) {
				System.out.println("Invalid input.");
				recordAppointmentOutcome(docCont,sc);
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid input.");
			sc.nextLine();
			recordAppointmentOutcome(docCont, sc);
		}
		System.out.println();
	}

    private void listPatients() {
        for(int i = 0; i < patients.size(); i++) {
            System.out.println((i+1) + ". " + patients.get(i).getMedicalRecord().getName());
        }
    }

    public void addPatient(Patient newPatient) {
		if (!this.patients.contains(newPatient)) {
			this.patients.add(newPatient);
		}
	}
}