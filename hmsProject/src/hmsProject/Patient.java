package hmsProject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Patient extends User implements Serializable {
	private MedicalRecord medicalRecord;
	private ArrayList<Appointment> appt;
	private ArrayList<Appointment> completedAppt;

	public Patient(String uid, String pw) {
		super(uid, pw);
		this.appt=new ArrayList<Appointment>();
		this.completedAppt=new ArrayList<Appointment>();
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public ArrayList<Appointment> getAppt() {
		return appt;
	}

	public ArrayList<Appointment> getCompletedAppt() {
		return completedAppt;
	}

	/**
	 * 
	 * @param apptSys
	 */
	public void userInterface(PatientController patientCont, Scanner sc) {
		// TODO - implement Patient.userInterface
		System.out.println("Patient UI");
		int userMenuInput=0;
		while(userMenuInput!=7) {
			this.displayMenu();
			userMenuInput=sc.nextInt();
			sc.nextLine();//clear buffer
			switch(userMenuInput) {
				case 1:
					printMedicalRecord();
					break;
				case 2:
					updateInfo(patientCont,sc);
					break;
				case 3: 
					this.apptOp(patientCont,sc);
					break;
				case 4:
					viewAppt();
					break;
				case 5:
					viewCompAppt();
					break;
				case 6:
					this.changePW(sc);
					break;
				case 7:
					System.out.println("Logging out as Patient");
					break;
				default:
					System.out.println("Input out of range");
					break;
			}
		}
	}
	
	public void displayMenu() {
		System.out.println(
                """
                1.View Medical Record
                2.Update Personal Information
                3.Schedule, Reschedule or Cancel Appointment
                4.View Scheduled Appointments
                5.View Past Appointment Outcome Records
                6.Change Password
                7.Logout
                """);
	}

	public void printMedicalRecord() {
		System.out.println("Diagnosis: ");
		for (Diagnosis diagnosis : this.getMedicalRecord().getDiagnoses()) {
			System.out.println(diagnosis);
		}

		System.out.println("Treatments: ");
		for (Treatment treatment : this.getMedicalRecord().getTreatments()) {
			System.out.println(treatment);
		}
	}

	public void updateInfo(PatientController patientCont,Scanner sc) {
		System.out.println(
				"""
				Which would you like to change?
				1.Phone Number
				2.Email
				""");
		// Should add error checking here
		int choice=sc.nextInt();
		sc.nextLine();//clear buffer
		switch (choice) {
			case 1:
				System.out.println("This is your current phone number: " + this.getMedicalRecord().getPhone());
				System.out.print("Please enter your new phone number: ");
				patientCont.updatePhone(sc.nextLine());
				//this.getMedicalRecord().setPhone(sc.nextLine());
				break;
			case 2:
				System.out.println("This is your current email: " + this.getMedicalRecord().getEmail());
				System.out.print("Please enter your new email: ");
				patientCont.updateEmail(sc.nextLine());
				//this.getMedicalRecord().setEmail(sc.nextLine());
				break;
			default:
				break;
		}
		System.out.println("Your information has been updated. ");
	}

	public void apptOp(PatientController patientCont,Scanner sc) {
		// TODO - implement Patient.apptOp
		System.out.println("Manage Appointments(Patient)");
		int uApptOpIn=0;
		while(uApptOpIn!=4) {
			System.out.println(
			"""
			1.View and Schedule available appointments
			2.Reschedule appointments
			3.Cancel appointments
			4.Exit managing appointment
			""");
			uApptOpIn=sc.nextInt();
			if(uApptOpIn==1)
				patientCont.getApptSys().scheAppt(patientCont,sc);
			else if(uApptOpIn==2) {
				if(this.getAppt().size()>0) {
					int cnt=1;
					System.out.println("Appointment Scheduled: (enter 0 to go back)");
					for(Appointment appt: this.getAppt()) {
						System.out.println(cnt+". Date:"+appt.getDate()+"|Time:"+appt.getTime()+"|Status:"+appt.getStatus());
						cnt++;
					}
					int apptSelect=0;
					apptSelect=sc.nextInt();
					if(apptSelect>0) {
						patientCont.getApptSys().rescheAppt(patientCont,this.getAppt().get(apptSelect-1),sc);
					}
				}
				else {
					System.out.println("No appointment has been scheduled so far.");
				}
			} else if (uApptOpIn==3) {
				if(this.getAppt().size()>0) {
					int cnt=1;
					System.out.println("Appointment Scheduled: (enter 0 to go back)");
					for(Appointment appt: this.getAppt()) {
						System.out.println(cnt+". Date:"+appt.getDate()+"|Time:"+appt.getTime()+"|Status:"+appt.getStatus());
						cnt++;
					}
					int apptSelect=0;
					apptSelect=sc.nextInt();
					if(apptSelect>0) {
						patientCont.getApptSys().cancelAppt(patientCont,this.getAppt().get(apptSelect-1));
					}
				}
				else {
					System.out.println("No appointment has been scheduled so far.");
				}
			}
		}
	}

	public void viewAppt() {
		int cnt=1;
		for(Appointment appt: this.getAppt()) {
			System.out.println(cnt+". Date:"+appt.getDate()+"|Time:"+appt.getTime()+"|Status:"+appt.getStatus());
			cnt++;
		}
	}

	public void viewCompAppt() {
		int cnt=1;
		for(Appointment appt: this.getCompletedAppt()) {
			System.out.println(cnt+". Date:"+appt.getDate()+"|Time:"+appt.getTime()+"|Status:"+appt.getStatus());
			cnt++;
		}
	}

	public void removeAppt(Appointment appt) {
		this.appt.remove(appt);
	}
}