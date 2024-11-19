package hmsProject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Patient subclass of User
 */
public class Patient extends User implements Serializable {
	private MedicalRecord medicalRecord;
	private ArrayList<Appointment> appt;
	private ArrayList<Appointment> completedAppt;

	/**
	 * Patient constructor
	 * @param uid unique user identifier
	 * @param pw password
	 */
	public Patient(String uid, String pw) {
		super(uid, pw);
		this.appt=new ArrayList<Appointment>();
		this.completedAppt=new ArrayList<Appointment>();
	}

	/**
	 * Medical Record getter.
	 * @return MedicalRecord.
	 */
	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	/**
	 * Medical Record setter.
	 * @param medicalRecord new MedicalRecord
	 */
	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	/**
	 * Current appointments list.
	 * @return appointment list.
	 */
	public ArrayList<Appointment> getAppt() {
		return appt;
	}

	/**
	 * Completed appointments list.
	 * @return Completed appointment list.
	 */
	public ArrayList<Appointment> getCompletedAppt() {
		return completedAppt;
	}

	/**
	 * Print all current appointments.
	 */
	public void viewAppt() {
		System.out.println("\n=== Scheduled Appointments ===");
		if (this.getAppt().isEmpty()) {
			System.out.println("No scheduled appointments.");
		} else {
			int cnt = 1;
			for (Appointment appt : this.getAppt()) {
				System.out.println(cnt + ". Date: " + appt.getDate() + " | Time: " + appt.getTime() + " | Status: " + appt.getStatus());
				cnt++;
			}
		}
	}

	/**
	 * Print all completed appointments.
	 */
	public void viewCompAppt() {
		System.out.println("\n=== Completed Appointments ===");
		if (this.getCompletedAppt().isEmpty()) {
			System.out.println("No completed appointments.");
		} else {
			int cnt = 1;
			for (Appointment appt : this.getCompletedAppt()) {
				System.out.println(cnt + ". Date: " + appt.getDate() + " | Time: " + appt.getTime() + " | Status: " + appt.getStatus());
				appt.getApptOutcomeRecord().printAll();
				cnt++;
			}
		}
	}

	/**
	 * Remove appointment from current appointment list.
	 * @param appt appointment to be removed
	 */
	public void removeAppt(Appointment appt) {
		this.appt.remove(appt);
	}

	/**
	 * Add completed appointment into completed appointment list.
	 * @param appt completed appointment
	 */
	public void addCompAppt(Appointment appt) {
		this.completedAppt.add(appt);
	}

	/**
	 * Patient-specific user interface that overrides from User.
	 * @param patientCont PatientController
	 * @param sc Scanner class for input
	 * @return Dependant to login as.
	 */
	public Patient userInterface(PatientController patientCont, Scanner sc) {
		int userMenuInput;
		Patient switchTo = null;
		System.out.println("Hi " + this.medicalRecord.getName() + "!");
		while (true) {
			try {
				userMenuInput = 0;
				while (userMenuInput != 8 && switchTo == null) {
					this.displayMenu();
					userMenuInput = sc.nextInt();
					sc.nextLine();
					switch (userMenuInput) {
						case 1:
							printMedicalRecord();
							break;
						case 2:
							updateInfo(sc);
							patientCont.save();
							break;
						case 3:
							this.apptOp(patientCont, sc);
							break;
						case 4:
							viewAppt();
							break;
						case 5:
							viewCompAppt();
							break;
						case 6:
							switchTo = manageDependencies(patientCont, sc);
							break;
						case 7:
							this.changePW(sc);
							patientCont.save();
							break;
						case 8:
							System.out.println("Logging out.\n");
							break;
						default:
							System.out.println("Input out of range");
							break;
					}
				}
				return switchTo;
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("Wrong input. Please try again.");
			}
		}
	}

	/**
	 * Patient menu.
	 */
	private void displayMenu() {
		System.out.println(
                """
                1. View Medical Record
                2. Update Personal Information
                3. Schedule, Reschedule or Cancel Appointment
                4. View Scheduled Appointments
                5. View Past Appointment Outcome Records
                6. Manage Dependencies
                7. Change Password
                8. Logout
                """);
	}

	/**
	 * Print medical record.
	 */
	private void printMedicalRecord() {
		this.getMedicalRecord().viewAll();
	}

	/**
	 * Update phone and email information.
	 * @param sc Scanner class for input.
	 */
	private void updateInfo(Scanner sc) {
		int success, choice;
		String input=null;
		try {
			System.out.println(
					"""
                    Which would you like to change?
                    1. Phone Number
                    2. Email
                    """);
			success = 0;
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
				case 1:
					System.out.println("This is your current phone number: " + this.getMedicalRecord().getPhone());
					System.out.print("Please enter your new phone number (enter only numbers and include country code): ");
					input=sc.nextLine();
					success=UtilityClass.checkPhone(input);
					break;
				case 2:
					System.out.println("This is your current email: " + this.getMedicalRecord().getEmail());
					System.out.print("Please enter your new email: ");
					input=sc.nextLine();
					success=UtilityClass.checkEmail(input);
					break;
				default:
					break;
			}
			if (success == 0) {
				if(choice==1) this.getMedicalRecord().setPhone(input);
				if(choice==2) this.getMedicalRecord().setEmail(input);
				System.out.println("Your information has been updated.");
			}
			else if (success == 1) System.out.println("Update unsuccessful. Make sure the phone number only contains number and include country code.");
			else System.out.println("Update unsuccessful. Make sure you use the correct email format.");
		} catch (Exception e) {
			sc.nextLine();
			System.out.println("Wrong input. Please try again.");
		}
	}

	/**
	 * All appointment operations such as scheduling appointments, rescheduling appointments and cancelling appointments.
	 * Links to PatientController.
	 * @param patientCont PatientController
	 * @param sc Scanner class for input
	 */
	private void apptOp(PatientController patientCont,Scanner sc) {
		int uApptOpIn;
		try {
			System.out.println("Manage Appointments(Patient)");
			uApptOpIn = 0;
			while (uApptOpIn != 4) {
				System.out.println(
						"""
						1. View and Schedule available appointments
						2. Reschedule appointments
						3. Cancel appointments
						4. Exit managing appointment
						""");
				uApptOpIn = sc.nextInt();
				if (uApptOpIn == 1)
					patientCont.getApptSys().scheAppt(patientCont, sc);
				else if (uApptOpIn == 2) {
					if (this.getAppt().size() > 0) {
						int cnt = 1;
						System.out.println("Appointment Scheduled: (enter 0 to go back)");
						for (Appointment appt : this.getAppt()) {
							System.out.println(cnt + ". Date:" + appt.getDate() + " | Time:" + appt.getTime() + " | Status:" + appt.getStatus());
							cnt++;
						}
						int apptSelect = 0;
						apptSelect = sc.nextInt();
						if (apptSelect > 0) {
							patientCont.getApptSys().rescheAppt(patientCont, this.getAppt().get(apptSelect - 1), sc);
						}
					} else {
						System.out.println("No appointment has been scheduled so far.");
					}
				} else if (uApptOpIn == 3) {
					if (this.getAppt().size() > 0) {
						int cnt = 1;
						System.out.println("Appointment Scheduled: (enter 0 to go back)");
						for (Appointment appt : this.getAppt()) {
							System.out.println(cnt + ". Date:" + appt.getDate() + " | Time:" + appt.getTime() + " | Status:" + appt.getStatus());
							cnt++;
						}
						int apptSelect = 0;
						apptSelect = sc.nextInt();
						if (apptSelect > 0) {
							//patientCont.getApptSys().cancelAppt(patientCont, this.getAppt().get(apptSelect - 1));
							patientCont.cancelAppt(this.getAppt().get(apptSelect-1));
						}
					} else {
						System.out.println("No appointment has been scheduled so far.");
					}
				}
			}
		} catch (Exception e) {
			sc.nextLine();
			System.out.println("Wrong input. Please try again. ");
		}
	}

	/**
	 * Add, Remove or Login to a dependant.
	 * @param patientCon Patient Controller
	 * @param sc Scanner class for input
	 * @return dependant to log in to if it is chosen.
	 */
	public Patient manageDependencies(PatientController patientCon, Scanner sc) {
		int choice, cnt, index;
		String dID;
		try {
			System.out.println(
					"""
                    Which would you like to do?
                    1.Add dependant
                    2.Remove dependant
                    3.View dependant medical records
                    4.Login to dependant
                    5.Back
                    """);
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
				case 1:
					System.out.print("Please key in the pID of dependant: ");
					dID = sc.nextLine();
					patientCon.addDependant(dID);
					break;
				case 2:
					if (this.getMedicalRecord().getDependencies().size() > 0) {
						cnt = 1;
						for (Patient p : this.getMedicalRecord().getDependencies()) {
							System.out.println(cnt + ". " + p.getMedicalRecord().getName());
							cnt++;
						}
						System.out.print("Please select the dependent you wish to remove (0 to return): ");
						index = sc.nextInt() - 1;
						sc.nextLine();
						if (index != -1) patientCon.removeDependant(this.getMedicalRecord().getDependencies().get(index));
					} else {
						System.out.println("You have no dependent.");
					}
					break;
				case 3:
					if (this.getMedicalRecord().getDependencies().size() > 0) {
						cnt = 1;
						for (Patient p : this.getMedicalRecord().getDependencies()) {
							System.out.println(cnt + ". " + p.getMedicalRecord().getName());
							cnt++;
						}
						System.out.print("Please select the dependent you wish to view medical record of (0 to return): ");
						index = sc.nextInt() - 1;
						sc.nextLine();
						if (index != -1) this.getMedicalRecord().getDependencies().get(index).getMedicalRecord().viewAll();
					} else {
						System.out.println("You have no dependent.");
					}
					break;
				case 4:
					if (this.getMedicalRecord().getDependencies().size() > 0) {
						cnt = 1;
						for (Patient p : this.getMedicalRecord().getDependencies()) {
							System.out.println(cnt + ". " + p.getMedicalRecord().getName());
							cnt++;
						}
						System.out.print("Please select the dependent you wish to login to (0 to return): ");
						index = sc.nextInt() - 1;
						sc.nextLine();
						if (index != -1) return this.getMedicalRecord().getDependencies().get(index);
					} else {
						System.out.println("You have no dependent.");
					}
					break;
				case 5:
					break;
				default:
					break;
			}
			return null;
		} catch (Exception e) {
			sc.nextLine();
			System.out.println("Wrong input. Please try again.");
			return null;
		}
	}
}