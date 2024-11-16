package hmsProject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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

	@Override
    public String toString() {
        return String.format("Doctor [Name: %s, ID: %s, Age: %d, Gender: %s]", getName(), gethID(), getAge(), getGender());
    }
	
	public void updateApptReq(Appointment appt) {
		this.addApptRequest(appt);		
		this.removeAvailAppt(appt);
	}
	
	public void revertSetAppointment(Appointment oldAppt) {
		// TODO Auto-generated method stub
		
		//appointment either pending or confirmed
		if (oldAppt.getStatus().equals("Pending")) {
	        this.removeApptReq(oldAppt);
	        this.addAvailAppointment(oldAppt);
	    } else if (oldAppt.getStatus().equals("Confirmed")) {
	        this.removeComingAppt(oldAppt);
	        this.addAvailAppointment(oldAppt);
	    }
		
		//to add sort method after appointment has been added back to ensure correct order displayed.
		Collections.sort(availableAppt, (a1, a2) -> {
	        int dateCompare = a1.getDate().compareTo(a2.getDate());
	        return dateCompare != 0 ? dateCompare : a1.getTime().compareTo(a2.getTime());
	    });
	}
	
	public void updateComingAppt(Appointment appt) {
		// TODO Auto-generated method stub
		this.removeApptReq(appt);
		this.addComingAppt(appt);
	}
	
	public void completedAppt(Appointment appt) {
		this.removeComingAppt(appt);
	}
	
	private void addComingAppt(Appointment appt) {
		this.comingAppt.add(appt);
	}
	
	private void removeApptReq(Appointment appt) {
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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Appointment> getAvailableAppt() {
		return availableAppt;
	}
	
	public void userInterface(DoctorController docCont, Scanner sc) {
        System.out.println("Welcome Dr. " + this.name);
        int userMenuInput = -1;
        while(userMenuInput != 0) {
            this.displayMenu();
            userMenuInput = sc.nextInt();
            sc.nextLine();
            switch(userMenuInput) {
                case 1:
                    viewMedRec();
                    break;
                case 2:
                    updateMedRec();
                    break;
                case 3:
                    viewPersonalSchedule();
                    break;
                case 4:
                    setAvailAppt(docCont,sc);
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
                    System.out.println("Logging out.");
                    break;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
        }
    }
	
	public void displayMenu() {
		System.out.println("1. View Patient Medical Records\n2. Update Patient Medical Records\n"
				+ "3. View Personal Schedule\n4. Set Availability for Appointments\n"
				+ "5. Accept or Decline Appointment Requests\n6. View Upcoming Appointments\n"
				+ "7. Record Appointment Outcome\n0. Logout");
	}

	public void apptOp(DoctorController docCont, Scanner sc) {
	    if(this.getApptReq().isEmpty()) {
	        System.out.println("No appointment requests at the moment.\n");
	        return;
	    }
	    
	    System.out.println("\nPending Appointment Requests:");
	    int cnt = 1;
	    for(Appointment appt: this.getApptReq()) {
	        System.out.println(cnt + ". Date: " + appt.getDate() + 
	                         " | Time: " + appt.getTime() + 
	                         " | Status: " + appt.getStatus() +
	                         " | Patient: " + appt.getPatient().getMedicalRecord().getName());
	        cnt++;
	    }
	    System.out.println("\nSelect appointment number to update (1-" + (this.getApptReq().size()) + ") or 0 to cancel:");
	    int apptSelect = sc.nextInt();
	    sc.nextLine();
	    if(apptSelect > 0 && apptSelect <= this.getApptReq().size()) {
	        System.out.println("\nFor the selected appointment:");
	        System.out.println("1. Accept");
	        System.out.println("2. Decline");
	        System.out.println("3. Cancel operation");  
	        int opSelect = sc.nextInt();
	        sc.nextLine();
	        Appointment selectedAppt = this.getApptReq().get(apptSelect - 1);
	        switch(opSelect) {
	            case 1:
	                //selectedAppt.setStatus("Confirmed");
	                docCont.getApptSys().acceptAppt(docCont, selectedAppt);
	                Collections.sort(comingAppt, (a1, a2) -> {
	                    int dateCompare = a1.getDate().compareTo(a2.getDate());
	                    return dateCompare != 0 ? dateCompare : a1.getTime().compareTo(a2.getTime());
	                });
	                System.out.println("Appointment accepted successfully.");
	                break;
	            case 2:
	                //selectedAppt.setStatus("Cancelled");
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
	    } else if(apptSelect != 0) {
	        System.out.println("Invalid appointment number.");
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

	public void setAvailAppt(DoctorController docCont,Scanner sc) {
	    //Scanner sc = new Scanner(System.in);
	    System.out.println("\n=== Set Available Appointments ===");
	    while(true) {
	        String date;
	        while(true) {
	            System.out.println("Enter date (YYYY-MM-DD) or 0 to exit:");
	            date = sc.nextLine();
	            if(date.equals("0")) return;
	            if (!isValidDate(date)) {
	                System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
	                System.out.println("Ensure the date is valid and in the future.");
	                continue;
	            }
	            break;
	        }
	        String time;
	        while(true) {
	            System.out.println("Enter time (HH:mm) in 24-hour format:");
	            time = sc.nextLine();
	            if (!isValidTime(time)) {
	                System.out.println("Invalid time format. Please use HH:mm in 24-hour format.");
	                System.out.println("Ensure time is between 00:00 and 23:59.");
	                continue;
	            }
	            break;
	        }
	        Appointment newAppt = new Appointment("Available", this, date, time);
	        docCont.addAvailAppointment(newAppt);
	        System.out.println("Appointment slot added successfully!");
	        System.out.println("Add another appointment? (Y/N)");
	        if(!sc.nextLine().equalsIgnoreCase("Y")) break;
	    }
	    Collections.sort(availableAppt, (a1, a2) -> {
	        int dateCompare = a1.getDate().compareTo(a2.getDate());
	        return dateCompare != 0 ? dateCompare : a1.getTime().compareTo(a2.getTime());
	    });
	}

	private boolean isValidDate(String dateStr) {
	    try {
	        LocalDate.parse(dateStr);
	        return true;
	    } catch (DateTimeParseException e) {
	        return false;
	    }
	}

	private boolean isValidTime(String timeStr) {
	    if (!timeStr.matches("\\d{2}:\\d{2}")) {
	        return false;
	    }
	    try {
	        LocalTime inputTime = LocalTime.parse(timeStr);
	        return true;
	    } catch (DateTimeParseException e) {
	        return false;
	    }
	}

    public void viewMedRec() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== View Patient Medical Records ===");
        
        if(patients.isEmpty()) {
            System.out.println("No patients assigned.");
        }
        else {
	        listPatients();
	        System.out.println("0. Exit");
	        System.out.println("Enter patient number to view medical record:");
	        int choice = sc.nextInt();
	        
	        if(choice > 0 && choice <= patients.size()) {
	            Patient selectedPatient = patients.get(choice - 1);
	            MedicalRecord record = selectedPatient.getMedicalRecord();
	            printMedicalRecord(record);
	        }
	        else if(choice != 0) {
	        	System.out.println("Invalid input.");
	        }
        }
        System.out.println();
    }

    private void printMedicalRecord(MedicalRecord record) {
        System.out.println("\nPatient ID: " + record.getpID());
        System.out.println("Name: " + record.getName());
        System.out.println("DOB: " + record.getDOB());
        System.out.println("Gender: " + record.getGender());
        System.out.println("Phone: " + record.getPhone());
        System.out.println("Email: " + record.getEmail());
        System.out.println("Blood Type: " + record.getBloodType());
        
        System.out.println("\nDiagnoses:");
        for(Diagnosis diag : record.getDiagnoses()) {
        	System.out.println("- Date: " + diag.getDiagnosisDate());
        	System.out.println("  Description: " + diag.getDescription());
        	System.out.println("  Severity: " + diag.getSeverity());
        	System.out.println("  Diagnosed by: Dr. " + diag.getDiagnosedBy());
        }
        
        System.out.println("\nTreatments:");
        for(Treatment treat : record.getTreatments()) {
        	System.out.println("- Date: " + treat.getPrescribedDate());
        	System.out.println("  Treatment Type: " + treat.getTreatmentType());
        	System.out.println("  Prescription: " + treat.getPrescription());
        	System.out.println("  Prescribed by: Dr. " + treat.getPrescribedBy());
        }
    }

    public void updateMedRec() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== Update Patient Medical Records ===");
        
        if(patients.isEmpty()) {
            System.out.println("No patients assigned.");
        }
        else {
        	listPatients();
            System.out.println("0. Exit");
            System.out.println("Enter patient number to update medical record:");
            int choice = sc.nextInt();
            sc.nextLine();
            if(choice > 0 && choice <= patients.size()) {
                Patient selectedPatient = patients.get(choice - 1);
                updatePatientRecord(selectedPatient, sc);
            }
            else if(choice != 0) {
            	System.out.println("Invalid input.");
            }
        }
        System.out.println();
    }

    private void updatePatientRecord(Patient patient, Scanner sc) {
        System.out.println("1. Add Diagnosis");
        System.out.println("2. Add Treatment");
        System.out.println("Enter choice:");
        int choice = sc.nextInt();
        sc.nextLine();
        if(choice == 1) {
            System.out.println("Enter diagnosis description:");
            String description = sc.nextLine();
            System.out.println("Enter severity (Mild/Moderate/Severe):");
            String severity = sc.nextLine();
            
            Diagnosis newDiagnosis = new Diagnosis(description, 
                                                 LocalDate.now().toString(), 
                                                 this.name, 
                                                 severity);
            patient.getMedicalRecord().updateDiagnosis(newDiagnosis);
            
        } else if(choice == 2) {
            System.out.println("Enter treatment type:");
            String treatmentType = sc.nextLine();
            System.out.println("Enter prescription:");
            String prescription = sc.nextLine();
            
            Treatment newTreatment = new Treatment(treatmentType,
                                                 prescription,
                                                 LocalDate.now().toString(),
                                                 this.name);
            patient.getMedicalRecord().updateTreatment(newTreatment);
        }
        
        System.out.println("Medical record updated successfully!");
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
        if(comingAppt.isEmpty()) {
            System.out.println("No appointments to record outcome for.\n");
            return;
        }
        System.out.println("Select appointment to record outcome:");
        for(int i = 0; i < comingAppt.size(); i++) {
            Appointment appt = comingAppt.get(i);
            System.out.println((i+1) + ". Date: " + appt.getDate() +
                             " | Time: " + appt.getTime() +
                             " | Patient: " + appt.getPatient().getMedicalRecord().getName());
        }
        int choice = sc.nextInt();
        sc.nextLine();
        if(choice > 0 && choice <= comingAppt.size()) {
            Appointment selectedAppt = comingAppt.get(choice - 1);
            OutcomeRecord outcome = OutcomeRecord.createOutcomeRecord(selectedAppt);
            selectedAppt.setApptOutcomeRecord(outcome);
            selectedAppt.setStatus("Completed");
            completedAppt(selectedAppt);
            System.out.println("Appointment outcome recorded successfully!");
        }
        System.out.println();
    }

    private void listPatients() {
        for(int i = 0; i < patients.size(); i++) {
            System.out.println((i+1) + ". " + patients.get(i).getMedicalRecord().getName());
        }
    }

    public void addPatient(Patient newPatient) {
		this.patients.add(newPatient);
	}

	//Admin Usage
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}