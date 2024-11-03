package hmsProject;

import java.util.ArrayList;
import java.util.Scanner;

public class Patient extends User {

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

	public Patient(String uid, String pw) {
		super(uid, pw);
		this.appt=new ArrayList<Appointment>();
		this.completedAppt=new ArrayList<Appointment>();
	}

	private MedicalRecord medicalRecord;
	private ArrayList<Appointment> appt;
	private ArrayList<Appointment> completedAppt;

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
			switch(userMenuInput) {
				case 1:
				case 2: break;
				case 3: 
					this.apptOp(patientCont,sc);
					break;
				case 5:
					int cnt=1;
					for(Appointment appt: this.getAppt()) {
						System.out.println(cnt+". Date:"+appt.getDate()+"|Time:"+appt.getTime()+"|Status:"+appt.getStatus());
						cnt++;
					}
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
		System.out.println("1.View Medical Record\n2.Update Personal Information"
				+ "\n3.View,Schedule,Reschedule Appointments\n4.Cancel an Appointment\n"
				+ "5.View Scheduled Appointments\n6.View Past Appointment Outcome Records\n7.Logout");
	}

	public void apptOp(PatientController patientCont,Scanner sc) {
		// TODO - implement Patient.apptOp
		System.out.println("Manage Appointments(Patient)");
		int uApptOpIn=0;
		while(uApptOpIn!=3) {
			System.out.println("1.View and Schedule available appointments\n2.Reschedule appointments\n3.Exit managing appointment");
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
			}
		}
	}

	public void changePW() {
		// TODO - implement Patient.changePW
		throw new UnsupportedOperationException();
	}

	public void updateInfo() {
		// TODO - implement Patient.updateInfo
		throw new UnsupportedOperationException();
	}

	public void viewAppt() {
		// TODO - implement Patient.viewAppt
		throw new UnsupportedOperationException();
	}

	public void viewCompAppt() {
		// TODO - implement Patient.viewCompAppt
		throw new UnsupportedOperationException();
	}

	public void removeAppt(Appointment appt) {
		// TODO Auto-generated method stub
		this.appt.remove(appt);		
	}
}