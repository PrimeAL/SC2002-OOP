package hmsProject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

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
		this.patients=new ArrayList<Patient>();
		this.availableAppt=new ArrayList<Appointment>();
		this.apptRequest=new ArrayList<Appointment>();
		this.comingAppt=new ArrayList<Appointment>();
		this.name=name;
		//Remember getter setters
		this.gender=gender;
		this.age=age;
	}
	
	public void updateApptReq(Appointment appt) {
		this.addApptRequest(appt);		
		this.removeAvailAppt(appt);
	}
	
	public void revertSetAppointment(Appointment oldAppt) {
		// TODO Auto-generated method stub
		
		//appointment either pending or confirmed
		if(oldAppt.getStatus()=="Pending") {
			this.removeApptReq(oldAppt);
			this.addAvailAppointment(oldAppt);
		}
		else if(oldAppt.getStatus()=="Confirmed") {
			this.removeComingAppt(oldAppt);
			this.addAvailAppointment(oldAppt);         
		}
		
		//to add sort method after appointment has been added back to ensure correct order displayed.
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
	
	public String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	
	
	public ArrayList<Appointment> getAvailableAppt() {
		return availableAppt;
	}


	private void setAvailableAppt(ArrayList<Appointment> availableAppt) {
		this.availableAppt = availableAppt;
	}


	public void userInterface(DoctorController docCont, Scanner sc) {
		// TODO - implement Doctor.userInterface
		System.out.println("Doctor UI");
		int userMenuInput=0;
		while(userMenuInput!=8) {
			this.displayMenu();
			userMenuInput=sc.nextInt();
			switch(userMenuInput) {
				case 1:
				case 2: break;
				case 5:
					this.apptOp(docCont,sc);
					break;
				case 6:
					for(Appointment appt:this.getComingAppt()) {
						System.out.println("Date:"+appt.getDate()+"|Time:"+appt.getTime()+"|Status:"+appt.getStatus());
					}
					break;
				case 8:
					break;
				default:
					System.out.println("Input out of range");
					break;
			}
		}
		
	}
	
	public void displayMenu() {
		System.out.println("1.View Patient Medical Records\n2.Update Patient Medical Records\n"
				+ "3.View Personal Schedule\n4.Set Availability for Appointments\n"
				+ "5.View,Accept or Decline Appointment Requests\n6.View Upcoming Appointments\n" //5 is edited because view is missing
				+ "7.Record Appointment Outcome\n8.Logout");
	}

	public void apptOp(DoctorController docCont,Scanner sc) {
		// TODO - implement Doctor.apptOp
		int uApptOpIn=0;
		while(uApptOpIn!=3) {
			System.out.println("1.View current available appointment\n2.Manage requested appointment\n3.Exit managing appointment");
			uApptOpIn=sc.nextInt();
			switch(uApptOpIn) {
				case 1: 
					for(Appointment appt: this.getAvailableAppt()) {
						System.out.println("Date:"+appt.getDate()+"|Time:"+appt.getTime()+"|Status:"+appt.getStatus());
					}
					break;
				case 2: 
					//implement boundary check
					if(this.getApptReq().size()==0) {
						System.out.println("No request at the moment");
						break;
					}
					System.out.println("Select appointment to update");
					int apptSelect=0;
					int cnt=1;
					for(Appointment appt: this.getApptReq()) {
						System.out.println(cnt+". Date:"+appt.getDate()+"|Time:"+appt.getTime()+"|Status:"+appt.getStatus());
					}
					apptSelect=sc.nextInt();
					System.out.println("For the selected appointment: 1.Accept, 2.Decline, 3.Cancel");
					int opSelect=0;
					opSelect=sc.nextInt();
					if(opSelect==1) {
						docCont.getApptSys().acceptAppt(docCont,this.getApptReq().get(apptSelect-1));
					}
					else if(opSelect==2) {
						docCont.declineAppt(this.getApptReq().get(apptSelect-1));   //not in apptSystem
					}
					break;
			}
			
		}
	}

	public void setAvailAppt() {
		// TODO - implement Doctor.setAvailAppt
		throw new UnsupportedOperationException();
	}

	public void viewMedRec() {
		// TODO - implement Doctor.viewMedRec
		throw new UnsupportedOperationException();
	}

	public void updateMedRec() {
		// TODO - implement Doctor.updateMedRec
		throw new UnsupportedOperationException();
	}

	public void addPatient(Patient newPatient) {
		this.patients.add(newPatient);
	}



}