package hmsProject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Staff System in charge of managing staff (Doctors, Pharmacists and Admin) operations.
 */
public class StaffSystem {
	private ArrayList<Doctor> docList;
	private ArrayList<Pharmacist> phaList;
	private ArrayList<Administrator> admList;

	/**
	 * Staff System constructor.
	 * @param staffList list of staffs from admin
	 */
	public StaffSystem(ArrayList<User> staffList) {
		docList = new ArrayList<Doctor>();
		phaList = new ArrayList<Pharmacist>();
		admList = new ArrayList<Administrator>();

		for (User user : staffList) {
			if (user instanceof Doctor) { 
				Doctor dr = (Doctor)user;
				docList.add(dr); 
			}
			if (user instanceof Pharmacist) { 
				Pharmacist pha = (Pharmacist)user;
				phaList.add(pha); 
			}
			if (user instanceof Administrator) { 
				Administrator adm = (Administrator)user;
				admList.add(adm); 
			}
		}
		docList.sort(Comparator.comparing(Doctor::gethID , String.CASE_INSENSITIVE_ORDER));
		phaList.sort(Comparator.comparing(Pharmacist::gethID , String.CASE_INSENSITIVE_ORDER));
		admList.sort(Comparator.comparing(Administrator::gethID , String.CASE_INSENSITIVE_ORDER));
	}

	/**
	 * Print list of staff based on filters.
	 * @param sc Scanner class
	 * @param staffList list of staffs from admin
	 */
	public void filerStaff(Scanner sc, ArrayList<User> staffList) {
		int choice = 0;
		while (choice != 6) {
			System.out.println(
				"""
				1. Filter by Role
				2. Filter by Name
				3. Filter by Age
				4. Filter by Gender
				5. View ALL
				6. Exit Filter Menu
				""");
			while (true) {
				System.out.print("Input Choice: ");
				if (sc.hasNextInt()) { // check if input is an integer
					choice = sc.nextInt();
					sc.nextLine();	// Clear the newline character after the integer
					if (choice >= 1 && choice <= 6) {break;} // Exit loop if input is valid within the valid range
					else { System.out.println("Invalid input. Please enter a number between 1 and 6.");}
				}
				else { 
					System.out.println("Invalid input. Please enter a number.");
					sc.nextLine();
				}
			}
			System.out.println();
			switch (choice) {
				case 1:
					int role = 0; int i = 0;
					System.out.println(
						"""
						1. Filter by Doctor
						2. Filter by Pharmacist
						3. Filter by Administrator
						""");
					while (true) {
						System.out.print("Input Choice: ");
						if (sc.hasNextInt()) { // check if input is an integer
							role = sc.nextInt();
							sc.nextLine();	// Clear the newline character after the integer
							if (role >= 1 && role <= 3) {break;} // Exit loop if input is valid within the valid range
							else { System.out.println("Invalid input. Please enter a number between 1 and 3.");}
						}
						else { 
							System.out.println("Invalid input. Please enter a number.");
							sc.nextLine();
						}
					}
					System.out.printf("%-10s %-10s %-20s %-15s %-10s %-5s%n", "Index", "Staff ID", "Name", "Role", "Gender", "Age");
					System.out.println("=======================================================================================");
					
					if (role == 1) {
						for (User user : staffList) {
							if (user instanceof Doctor) { 
								Doctor doc = (Doctor)user; 
								System.out.printf("%-10s %-10s %-20s %-15s %-10s %-5s%n", 
								++i, 
								doc.gethID(),
								doc.getName(),
								"Doctor",
								doc.getGender(),
								doc.getAge());
							}
						}
					}

					else if (role == 2) {
						for (User user : staffList) {
							if (user instanceof Pharmacist) { 
								Pharmacist pha = (Pharmacist)user; 
								System.out.printf("%-10s %-10s %-20s %-15s %-10s %-5s%n", 
								++i, 
								pha.gethID(),
								pha.getName(),
								"Pharmacist",
								pha.getGender(),
								pha.getAge());
							}
						}
					}

					else {
						for (User user : staffList) {
							if (user instanceof Administrator) { 
								Administrator ad = (Administrator)user; 
								System.out.printf("%-10s %-10s %-20s %-15s %-10s %-5s%n", 
								++i, 
								ad.gethID(),
								ad.getName(),
								"Administrator",
								ad.getGender(),
								ad.getAge());
							}
						}
					}
					System.out.println("");
					break;
				case 2:
					ArrayList<User> filterStaffList = new ArrayList<User>();
					String filteredName;
					while (true) {
						System.out.println("Input Name to search: ");
						filteredName = sc.nextLine();
						if (filteredName.isEmpty()) {
							System.out.println("Invalid input. Please enter a name.");
							filteredName = sc.nextLine();
						} 
						else if (!filteredName.matches("[a-zA-Z\\s]+"))
						{
							System.out.println("Invalid input. Search Name should contain only letters and spaces.");
						}
						else {break;}
					}
					
					for (Doctor doc : docList) {
						if (doc.getName().toLowerCase().contains(filteredName.toLowerCase())) {
							filterStaffList.add(doc);
						}
					}

					for (Pharmacist pha : phaList) {
						if (pha.getName().toLowerCase().contains(filteredName.toLowerCase())) {
							filterStaffList.add(pha);
						}
					}

					for (Administrator ad : admList) {
						if (ad.getName().toLowerCase().contains(filteredName.toLowerCase())) {
							filterStaffList.add(ad);
						}
					}

					if (filterStaffList.isEmpty()) {
						System.out.println("No results found.\n");
					}
					else {
						filterStaffList.sort(Comparator.comparing(User::gethID, String.CASE_INSENSITIVE_ORDER));
						System.out.println("Filter Results by Name: " + filteredName);
						printfilteredList(filterStaffList);
					}
					break;
				case 3:
					ArrayList<User> filteredStaffList = new ArrayList<User>();
					int minAge = getValidIntInput(sc, "Input Min Age Range(inclusive):");
					int maxAge = getValidIntInput(sc, "Input Max Age Range(inclusive):");
					
					for (Doctor doc : docList) {
						if (doc.getAge() >= minAge && doc.getAge() <= maxAge) {
							filteredStaffList.add(doc);
						}
					}

					for (Pharmacist pha : phaList) {
						if (pha.getAge() >= minAge && pha.getAge() <= maxAge) {	
							filteredStaffList.add(pha);
						}
					}

					for (Administrator ad : admList) {
						if (ad.getAge() >= minAge && ad.getAge() <= maxAge) {
							filteredStaffList.add(ad);
						}
					}

					filteredStaffList.sort(Comparator.comparing(User::gethID, String.CASE_INSENSITIVE_ORDER));
					System.out.println("Filter Results by Age: " + minAge + " to " + maxAge);
					printfilteredList(filteredStaffList);
					break;
				case 4:
					ArrayList<User> filtersStaffList = new ArrayList<User>();
					String filteredGender;
					while (true) {
						System.out.println("Input Gender to search: ");
						filteredGender = sc.nextLine();
						if (filteredGender.isEmpty()) {
							System.out.println("Invalid input. Please enter a Gender(Male or Female).");
							filteredGender = sc.nextLine();
						} 
						else if (!filteredGender.equalsIgnoreCase("male") && !filteredGender.equalsIgnoreCase("female"))
						{
							System.out.println("Invalid input. Input must be either 'male' or 'female'.");
						}
						else {break;}
					}
					
					for (Doctor doc : docList) {
						if (doc.getGender().equalsIgnoreCase(filteredGender.toLowerCase())) {
							filtersStaffList.add(doc);
						}
					}

					for (Pharmacist pha : phaList) {
						if (pha.getGender().equalsIgnoreCase(filteredGender)) {
							filtersStaffList.add(pha);
						}
					}

					for (Administrator ad : admList) {
						if (ad.getGender().equalsIgnoreCase(filteredGender)) {
							filtersStaffList.add(ad);
						}
					}

					if (filtersStaffList.isEmpty()) {
						System.out.println("No results found.\n");
					}
					else {
						filtersStaffList.sort(Comparator.comparing(User::gethID, String.CASE_INSENSITIVE_ORDER));
						System.out.println("Filter Results by Gender: " + filteredGender);
						printfilteredList(filtersStaffList);
					}
					break;
				case 5:
					printStaff(staffList);
					break;
				case 6:
					System.out.println("Exiting Filter Menu....");
					break;
				default:
					System.out.println("Invalid input. Please enter a number between 1 and 4.");
					break;
			}
		}
	}

	/**
	 * Formatting list of staff based on filter and then printing it. Called by filterStaff().
	 * @param filteredList filtered users list to print
	 */
	public void printfilteredList(ArrayList<User> filteredList) {
		int index = 0;
		System.out.printf("%-10s %-10s %-20s %-15s %-10s %-5s%n", "Index", "Staff ID", "Name", "Role", "Gender", "Age");
		System.out.println("=======================================================================================");
		
		for (User user : filteredList) {
			if (user instanceof Doctor) { 
				Doctor doc = (Doctor)user; 
				System.out.printf("%-10s %-10s %-20s %-15s %-10s %-5s%n", 
				index + 1, 
				doc.gethID(),
				doc.getName(),
				"Doctor",
				doc.getGender(),
				doc.getAge());
			}

			if (user instanceof Pharmacist) { 
				Pharmacist pha = (Pharmacist)user; 
				System.out.printf("%-10s %-10s %-20s %-15s %-10s %-5s%n", 
				index + 1, 
				pha.gethID(),
				pha.getName(),
				"Pharmacist",
				pha.getGender(),
				pha.getAge());
			}

			if (user instanceof Administrator) { 
				Administrator ad = (Administrator)user; 
				System.out.printf("%-10s %-10s %-20s %-15s %-10s %-5s%n", 
				index + 1, 
				ad.gethID(),
				ad.getName(),
				"Administrator",
				ad.getGender(),
				ad.getAge());
			}
			index++;
		}

		System.out.println("");
	}

	/**
	 * Print list of staff.
	 * @param staffList list of staff from admin
	 */
	public void printStaff(ArrayList<User> staffList) {
		int i = 0;
		System.out.printf("%-10s %-10s %-20s %-15s %-10s %-5s%n", "Index", "Staff ID", "Name", "Role", "Gender", "Age");
		System.out.println("=======================================================================================");
		
		for (User user : staffList) {
			if (user instanceof Doctor) { 
				Doctor doc = (Doctor)user; 
				System.out.printf("%-10s %-10s %-20s %-15s %-10s %-5s%n", 
				i + 1, 
				doc.gethID(),
				doc.getName(),
				"Doctor",
				doc.getGender(),
				doc.getAge());
			}

			if (user instanceof Pharmacist) { 
				Pharmacist pha = (Pharmacist)user; 
				System.out.printf("%-10s %-10s %-20s %-15s %-10s %-5s%n", 
				i + 1, 
				pha.gethID(),
				pha.getName(),
				"Pharmacist",
				pha.getGender(),
				pha.getAge());
			}

			if (user instanceof Administrator) { 
				Administrator ad = (Administrator)user; 
				System.out.printf("%-10s %-10s %-20s %-15s %-10s %-5s%n", 
				i + 1, 
				ad.gethID(),
				ad.getName(),
				"Administrator",
				ad.getGender(),
				ad.getAge());
			}
			i++;
		}
	}

	/**
	 * Add new staff to staff list in admin and user list in DataStorage.
	 * Input all details of new staff and do respective input validations.
	 * @param adminCont Admin Controller
	 * @param sc Scanner class
	 * @param staffList list of staffs
	 */
	public void addStaff(AdministratorController adminCont, Scanner sc, ArrayList<User> staffList) {
		int choice = 0;
		System.out.println(
			"""
			1. Add New Doctor
			2. Add New Pharmacist
			3. Add New Administrator
			""");
		while (true) {
			System.out.print("Input Choice: ");
			if (sc.hasNextInt()) { // check if input is an integer
				choice = sc.nextInt();
				sc.nextLine();	// Clear the newline character after the integer
				if (choice >= 1 && choice <= 3) {break;} // Exit loop if input is valid within the valid range
				else { System.out.println("Invalid input. Please enter a number between 1 and 5.");}
			}
			else { 
				System.out.println("Invalid input. Please enter a number.");
				sc.nextLine();
			}
		}

		String staffName = "";
		while (true) {
			boolean nameExists = false; // Flag to track if the name already exists
			
			staffName = getValidAlphabeticString(sc, "Enter Staff Name: ");
			
			for (User user : staffList) {
				if (choice == 1 && user instanceof Doctor) {
					Doctor tempDoc = (Doctor) user;
					if (tempDoc.getName().equalsIgnoreCase(staffName)) {
						System.out.println("Doctor " + staffName + " already exists. Please enter a different name.");
						nameExists = true;
						break;
					}
				}
				if (choice == 2 && user instanceof Pharmacist) {
					Pharmacist tempPha = (Pharmacist) user;
					if (tempPha.getName().equalsIgnoreCase(staffName)) {
						System.out.println("Pharmacist " + staffName + " already exists. Please enter a different name.");
						nameExists = true;
						break;
					}
				}
				if (choice == 3 && user instanceof Administrator) {
					Administrator tempAdm = (Administrator) user;
					if (tempAdm.getName().equalsIgnoreCase(staffName)) {
						System.out.println("Administrator " + staffName + " already exists. Please enter a different name.");
						nameExists = true;
						break;
					}
				}
			}
			
			// If no duplicate name is found, exit the while loop
			if (!nameExists) {
				break;
			}
		}

		int staffAge;
		while (true) {
			System.out.println("Enter new Staff Age: ");
			// Check if input is a valid integer
			if (sc.hasNextInt()) {
				staffAge = sc.nextInt();
				sc.nextLine();
				if (staffAge < 16 || staffAge > 100) {
					System.out.println("Invalid input. Age must be between 16 to 100! ");
				}
				else break;
			} else {
				System.out.println("Invalid input. Input must be an integer! ");
				sc.nextLine(); // Clear the invalid input
			}
		}

		String staffGender = null;
		while (true) {
			System.out.print("Enter Staff Gender: ");
			staffGender = sc.nextLine(); // Read the input once

			// Check if the input is either "male" or "female" (case-insensitive)
			if (staffGender.equalsIgnoreCase("male") || staffGender.equalsIgnoreCase("female")) {break;} // Exit loop if input is valid
			else {System.out.println("Invalid input. Please enter 'male' or 'female'.");}
		}

		String staffID;
		while (true) {
			boolean validID = false;
			System.out.println("Enter Staff ID (starting with D/P/A(Uppercase), followed by 3 digits):");
			staffID = sc.nextLine().toUpperCase();
			if (staffID.length() != 4 || !staffID.matches("^[DPA]\\d{3}$")) {
				System.out.println("Invalid input. Please enter a valid ID(starting with D/P/A(Uppercase), followed by 3 digits).");
			}
			else {
				for (User user : staffList) { // check if staff ID already exists
					if (user.gethID().equals(staffID)) {
						validID = true;
					}
				}
				if (!validID) { // if unique, check if staff ID is for correct role
					if (choice == 1 && staffID.toUpperCase().contains("D")) {
						Doctor dr = new Doctor(staffID, "default", staffName, staffGender, staffAge);
						docList.add(dr);
						staffList.add(dr);
						adminCont.addNewStaff(dr);
						break;
					}
					else if (choice == 2 && staffID.toUpperCase().contains("P")) {
						//Add new Pharmacist
						Pharmacist phar = new Pharmacist(staffID, "default", staffName, staffGender, staffAge);
						phaList.add(phar);
						staffList.add(phar);
						adminCont.addNewStaff(phar);
						break;
					}
					else if (choice == 3 && staffID.toUpperCase().contains("A")) {
						//Add new Administrator
						Administrator admin = new Administrator(staffID, "default", staffName, staffGender, staffAge);
						admList.add(admin);
						staffList.add(admin);
						adminCont.addNewStaff(admin);
						break;
					}
					else {System.out.println("Invalid input. Please enter a valid ID. (4 characters starting with D(Doctor)/P(Pharmacist)/A(Administrator)).");}
				}		
				else {System.out.println("ID already exists. Please enter a unique ID.");}
			}
		}

		staffList.sort(Comparator.comparing(User::gethID , String.CASE_INSENSITIVE_ORDER));
		System.out.println("Staff added successfully!");
	}

	/**
	 * Update staff information.
	 * @param adminCont Admin Controller
	 * @param sc Scanner class
	 * @param staffList list of staffs
	 */
	public void updateStaff(AdministratorController adminCont, Scanner sc, ArrayList<User> staffList) { 
		int changeStaff = 0; int choice = 0;
		System.out.println(
			"""
			1. Update Name
			2. Update Age
			3. Update Gender
			4. Update ID
			5. Update Role
			6. Exit
			""");
			while (true) {
				System.out.print("Input Choice: ");
				if (sc.hasNextInt()) { // check if input is an integer
					choice = sc.nextInt();
					sc.nextLine();	// Clear the newline character after the integer
					if (choice >= 1 && choice <= 6) {break;} // Exit loop if input is valid within the valid range
					else { System.out.println("Invalid input. Please enter a number between 1 and 5.");}
				}
				else { 
					System.out.println("Invalid input. Please enter a number.");
					sc.nextLine();
				}
			}
		switch (choice) {
			case 1:
			    changeStaff = selectStaff(sc, staffList);
				String staffName = "";
				while (true) {
					boolean nameExists = false; // Flag to track if the name already exists
					
					staffName = getValidAlphabeticString(sc, "Enter new Staff Name: ");
					
					for (User user : staffList) {
						if (choice == 1 && user instanceof Doctor) {
							Doctor tempDoc = (Doctor) user;
							if (tempDoc.getName().equalsIgnoreCase(staffName)) {
								System.out.println("Doctor " + staffName + " already exists. Please enter a different name.");
								nameExists = true;
								break;
							}
						}
						if (choice == 2 && user instanceof Pharmacist) {
							Pharmacist tempPha = (Pharmacist) user;
							if (tempPha.getName().equalsIgnoreCase(staffName)) {
								System.out.println("Pharmacist " + staffName + " already exists. Please enter a different name.");
								nameExists = true;
								break;
							}
						}
						if (choice == 3 && user instanceof Administrator) {
							Administrator tempAdm = (Administrator) user;
							if (tempAdm.getName().equalsIgnoreCase(staffName)) {
								System.out.println("Administrator " + staffName + " already exists. Please enter a different name.");
								nameExists = true;
								break;
							}
						}
					}
					
					if (!nameExists) {// If no duplicate name is found, exit the while loop
						break;
					}
				}
				// Determine the staff type and update the name
				if (changeStaff <= docList.size()) { 
					Doctor d = docList.get(changeStaff - 1);
					staffList.remove(d);
					d.setName(staffName);
					staffList.add(d);
				} else if (changeStaff <= docList.size() + phaList.size()) {
					Pharmacist p = phaList.get(changeStaff - docList.size() - 1);
					staffList.remove(p);
					p.setName(staffName);
					staffList.add(p);
				} else {
					Administrator a = admList.get(changeStaff - docList.size() - phaList.size() - 1);
					staffList.remove(a);
					a.setName(staffName);
					staffList.add(a);
				}
				staffList.sort(Comparator.comparing(User::gethID , String.CASE_INSENSITIVE_ORDER));
				System.out.println("Staff name updated successfully!");
				break;
			case 2: //update age
				changeStaff = selectStaff(sc, staffList);
				int staffAge;
				while (true) {
					System.out.println("Enter new Staff Age: ");
					// Check if input is a valid integer
					if (sc.hasNextInt()) {
						staffAge = sc.nextInt();
						sc.nextLine();
						if (staffAge < 16 || staffAge > 100) {
							System.out.println("Invalid input. Age must be between 16 to 100! ");
						}
						else break;
					} else {
						System.out.println("Invalid input. Input must be an integer! ");
						sc.nextLine(); // Clear the invalid input
					}
				}
				
				if (changeStaff <= docList.size()) { // Determine the staff type and update the name
					Doctor d = docList.get(changeStaff - 1);
					staffList.remove(d);
					d.setAge(staffAge);
					staffList.add(d);
				} else if (changeStaff <= docList.size() + phaList.size()) {
					Pharmacist p = phaList.get(changeStaff - docList.size() - 1);
					staffList.remove(p);
					p.setAge(staffAge);
					staffList.add(p);
				} else {
					Administrator a = admList.get(changeStaff - docList.size() - phaList.size() - 1);
					staffList.remove(a);
					a.setAge(staffAge);
					staffList.add(a);
				}
				staffList.sort(Comparator.comparing(User::gethID , String.CASE_INSENSITIVE_ORDER));
				System.out.println("Staff age updated successfully!");
				break;
			case 3: //Update Gender
				changeStaff = selectStaff(sc, staffList);	
				if (changeStaff <= docList.size()) { // Determine the staff type and update the name
					Doctor d = docList.get(changeStaff - 1);
					staffList.remove(d);
					if (d.getGender().equalsIgnoreCase("male")) d.setGender("female"); 
					else d.setGender("male");
					staffList.add(d);
				} else if (changeStaff <= docList.size() + phaList.size()) {
					Pharmacist p = phaList.get(changeStaff - docList.size() - 1);
					staffList.remove(p);
					if (p.getGender().equalsIgnoreCase("male")) p.setGender("female"); 
					else p.setGender("male");
					staffList.add(p);
				} else {
					Administrator a = admList.get(changeStaff - docList.size() - phaList.size() - 1);
					staffList.remove(a);
					if (a.getGender().equalsIgnoreCase("male")) a.setGender("female"); 
					else a.setGender("male");
					staffList.add(a);
				}
				staffList.sort(Comparator.comparing(User::gethID , String.CASE_INSENSITIVE_ORDER));
				System.out.println("Staff Gender updated successfully!");
				break;
			case 4: // Update Staff ID
				changeStaff = selectStaff(sc, staffList);
				String staffID;
				while (true) {
					boolean validID = false;
					System.out.println("Enter Staff ID (starting with D/P/A(Uppercase), followed by 3 digits):");
					staffID = sc.nextLine();
					if (staffID.length() != 4 || !staffID.matches("^[DPA]\\d{3}$")) {
						System.out.println("Invalid input. Please enter a valid Staff ID (starting with D/P/A(Uppercase), followed by 3 digits):");
					}
					else {
						for (User user : staffList) { // check if staff ID already exists
							if (user.gethID().equals(staffID)) {
								validID = true;
								break;
							}
						}
						if (!validID) { // if unique, check if staff ID is for correct role
							if (changeStaff <= docList.size() && staffID.toUpperCase().contains("D")) {
								Doctor dr = docList.get(changeStaff - 1);
								staffList.remove(dr);
								dr.sethID(staffID);
								staffList.add(dr);
								break;
							}
							else if (changeStaff > docList.size() && changeStaff <= docList.size() + phaList.size() && staffID.toUpperCase().contains("P")) {
								Pharmacist p = phaList.get(changeStaff - docList.size() - 1);
								staffList.remove(p);
								p.sethID(staffID);
								staffList.add(p);
								break;
							}
							else if (changeStaff > docList.size() + phaList.size() && staffID.toUpperCase().contains("A")) {
								Administrator a = admList.get(changeStaff - docList.size() - phaList.size() - 1);
								staffList.remove(a);
								a.sethID(staffID);
								staffList.add(a);
								break;
							}
							else {System.out.println("Invalid input. Please enter a valid ID. (4 characters starting with D(Doctor)/P(Pharmacist)/A(Administrator)).");}
						}		
						else {System.out.println("ID already exists. Please enter a unique ID.");}
					}
				}
				staffList.sort(Comparator.comparing(User::gethID , String.CASE_INSENSITIVE_ORDER));
				System.out.println("Staff ID updated successfully!");
				break;
			case 5:
				changeStaff = selectStaff(sc, staffList);
				int selectRole = 0;
				
				while (true) {
					System.out.println("Enter new role(1 for Doctor, 2 for Pharmacist, 3 for Administrator).");
					if (sc.hasNextInt()) {// Check if input is a valid integer
						selectRole = sc.nextInt();
						sc.nextLine();
						if (selectRole <= 0 || selectRole > 3) {
							System.out.println("Invalid input. Input must 1 to 3! ");
							continue;
						}
					} 
					else {
						System.out.println("Invalid input. Input must be an integer! ");
						sc.nextLine(); // Clear the invalid input
						continue;
					}

					if (changeStaff <= docList.size() && selectRole == 1) { //Check if selected same role
						System.out.println("This user is already a Doctor. Please try again.");
							continue;
					}
					else if (changeStaff > docList.size() && changeStaff <= docList.size() + phaList.size() && selectRole == 2) {
						System.out.println("This user is already a Pharmacist. Please try again.");
							continue;
					}
					else if (changeStaff > docList.size() + phaList.size() && selectRole == 3) {
						System.out.println("This user is already an Administrator. Please try again.");
							continue;
					}

					String id = checkValidID(sc, staffList, selectRole);
					if (changeStaff <= docList.size()) { // Determine the staff type and update the name
						if (selectRole == 1) {
							System.out.println("This user is already a Doctor. Please try again.");
							continue;
						}

						Doctor d = docList.get(changeStaff - 1);
						staffList.remove(d);
						docList.remove(d);
						if (selectRole == 2) {
							Pharmacist p = new Pharmacist(id, d.getPw(),d.getName(), d.getGender(), d.getAge());
							staffList.add(p);
							phaList.add(p);
							break;
						}
						
						if (selectRole == 3) {
							Administrator a = new Administrator(id, d.getPw(),d.getName(), d.getGender(), d.getAge());
							staffList.add(a);
							admList.add(a);
							break;
						}
					} 
					else if (changeStaff > docList.size() && changeStaff <= docList.size() + phaList.size()) {
						if (selectRole == 2) {
							System.out.println("This user is already a Pharmacist. Please try again.");
							continue;
						}

						Pharmacist p = phaList.get(changeStaff - docList.size() - 1);
						staffList.remove(p);
						phaList.remove(p);
						if (selectRole == 1) {
							Doctor d = new Doctor(id, p.getPw(), p.getName(), p.getGender(), p.getAge());
							staffList.add(d);
							docList.add(d);
							break;
						}
						
						if (selectRole == 3) {
							Administrator a = new Administrator(id, p.getPw(), p.getName(), p.getGender(), p.getAge());
							staffList.add(a);
							admList.add(a);
							break;
						}
					} 
					else {
						if (selectRole == 3) {
							System.out.println("This user is already a Administrator. Please try again.");
							continue;
						}
						Administrator a = admList.get(changeStaff - docList.size() - phaList.size() - 1);
						staffList.remove(a);
						admList.remove(a);
						if (selectRole == 1) {
							Doctor d = new Doctor(id, a.getPw(), a.getName(), a.getGender(), a.getAge());
							staffList.add(d);
							docList.add(d);
							break;
						}
						
						if (selectRole == 2) {
							Pharmacist p = new Pharmacist(id, a.getPw(),a.getName(), a.getGender(), a.getAge());
							staffList.add(p);
							phaList.add(p);
							break;
						}
					}
				}
				staffList.sort(Comparator.comparing(User::gethID , String.CASE_INSENSITIVE_ORDER));
				System.out.println("Staff Role updated successfully!");
				adminCont.saveData();
				break;
			case 6:
				System.out.println("Exiting update staff menu.");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				break;
		}
	}

	/**
	 * Get staff position in doctor list or pharmacist list or admin list.
	 * @param sc Scanner sc
	 * @param staffList list of staffs
	 * @return index position in respective list.
	 */
	public int selectStaff(Scanner sc, ArrayList<User> staffList) {
		int choice = 0, index = 0;
		for (Doctor doc : docList) {
			System.out.printf("%d. %s%n", ++index, doc); // Print index + 1 and the objects toString()
		}

		for (Pharmacist pha : phaList) {
			System.out.printf("%d. %s%n", ++index, pha); // Print index + 1 and the objects toString()
		}
	
		for (Administrator adm : admList) {
			System.out.printf("%d. %s%n", ++index, adm);
		}

		while (true) {
			System.out.print("\nSelect Staff: ");
			if (sc.hasNextInt()) { // check if input is an integer
				choice = sc.nextInt();	
				sc.nextLine(); // Clear the newline character after reading the integer
				if (choice >= 1 && choice <= index) {break;} // Exit loop if input is valid within the valid range
				else { System.out.println("Invalid input. Please enter a number between 1 and " + index + ".");}
			}
			else { 
				System.out.println("Invalid input. Please enter a number.");
				sc.nextLine();
			}
		}

		return choice;
	}

	/**
	 * Remove staff from staff list in admin and role list in this class as well as user list in DataStorage.
	 * @param adminCont Admin Controller
	 * @param sc Scanner class
	 * @param staffList list of staffs
	 */
	public void removeStaff(AdministratorController adminCont, Scanner sc, ArrayList<User> staffList) {
		String staffID = "";
		int choice = selectStaff(sc, staffList);

		if (choice <= docList.size()) {
			staffID = docList.get(choice - 1).gethID();
			System.out.println("Doctor removed successfully!");
			docList.remove(choice - 1);
		}
		else if (choice <= docList.size() + phaList.size()) {
			staffID = phaList.get(choice - docList.size() - 1).gethID();
			System.out.println("Pharmacist removed successfully!");
			phaList.remove(choice - docList.size() - 1);
		}
		else {
			staffID = admList.get(choice - docList.size() - phaList.size() - 1).gethID();
			System.out.println("Administrator removed successfully!");
			admList.remove(choice - docList.size() - phaList.size() - 1);
		}

		User user = null;
		for (int i = 0; i < staffList.size(); i++) {
			if (staffList.get(i).gethID().equals(staffID)) {
				user = staffList.get(i);
		}
		staffList.remove(user);
		adminCont.removeStaff(user);
		}
	}

	/**
	 * Integer input validation.
	 * @param sc Scanner class
	 * @param prompt reason for input
	 * @return integer input.
	 */
	//input checking
	public int getValidIntInput(Scanner sc, String prompt) {
        int input = 0;

        while (true) {
            System.out.print(prompt);
            
            // Check if input is a valid integer
            if (sc.hasNextInt()) {
                input = sc.nextInt();
				sc.nextLine();
				if (input <= 0) {
					System.out.println("Invalid input. Input must be a positive integer! ");
				}
				else break;
            } else {
                System.out.println("Invalid input. Input must be an integer! ");
                sc.nextLine(); // Clear the invalid input
            }
        }
        return input;
    }

	/**
	 * Check if input contains only alphabets and spaces.
	 * @param sc Scanner class
	 * @param prompt reason for input
	 * @return string input.
	 */
	public String getValidAlphabeticString(Scanner sc, String prompt) {
		String input;
		while (true) {
			System.out.print(prompt);
			input = sc.nextLine();
		
			if (input.matches("^[a-zA-Z\\s]+$")) {// Check if input contains only alphabets and spaces
				break; // Exit loop if input is valid
			} else {
				System.out.println("Invalid input. Please enter alphabets only, without numbers.");
			}
		}
		return input;
	}

	/**
	 * Update Staff ID
	 * @param sc Scanner class
	 * @param staffList list of staffs
	 * @param changeStaff index of staff to change
	 */
	public void updateStaffID(Scanner sc, ArrayList<User> staffList, int changeStaff) {
		String staffID; boolean validID = false;
		while (true) {
			System.out.println("Enter new Staff ID(4 characters starting with D/P/A).");
			staffID = sc.nextLine().toUpperCase();
			if (staffID.length() != 4 || staffID.toUpperCase().contains("D") == false && staffID.toUpperCase().contains("P") == false && staffID.toUpperCase().contains("A") == false) {
				System.out.println("Invalid input. Please enter a valid ID(4 characters starting with D/P/A).");
			}
			else {
				for (User user : staffList) { // check if staff ID already exists
					if (user.gethID().equals(staffID)) {
						validID = true;
					}
				}
				if (!validID) { // if unique, check if staff ID is for correct role
					if (changeStaff <= docList.size()) { // Determine the staff type and update the name
						Doctor d = docList.get(changeStaff - 1);
						staffList.remove(d);
						d.sethID(staffID);
						staffList.add(d);
						break;
					} else if (changeStaff <= docList.size() + phaList.size()) {
						Pharmacist p = phaList.get(changeStaff - docList.size() - 1);
						staffList.remove(p);
						p.sethID(staffID);
						staffList.add(p);
						break;
					} else {
						Administrator a = admList.get(changeStaff - docList.size() - phaList.size() - 1);
						staffList.remove(a);
						a.sethID(staffID);
						staffList.add(a);
						break;
					}
				}		
				else {System.out.println("ID already exists. Please enter a unique ID.");}
			}
		}
		staffList.sort(Comparator.comparing(User::gethID , String.CASE_INSENSITIVE_ORDER));
		System.out.println("Staff Gender updated successfully!");
	}

	/**
	 * Check if ID is new, unique and valid.
	 * @param sc Scanner class
	 * @param staffList list of staff from admin
	 * @param role role
	 * @return valid ID
	 */
	public String checkValidID(Scanner sc, ArrayList<User> staffList, int role) {
		// Prompt for a new, unique, and valid staff ID
		String staffID;
		while (true) {
			boolean validID = false;
			System.out.println("Enter Staff ID (length 4 starting with D/P/A(Uppercase), followed by 3 digits): ");
			staffID = sc.nextLine();

			if (staffID.length() != 4 || (!staffID.matches("^[DPA]\\d{3}$"))) {
				System.out.println("Invalid input. Please enter a valid ID (length 4 starting with D/P/A(Uppercase), followed by 3 digits).");
			} 
			else {
				// Check if staff ID already exists
				for (User user : staffList) {
					if (user.gethID().equals(staffID)) {
						System.out.println("ID already exists. Please enter a unique ID.");
						validID = true;
						break;
					}
				}

				if (!validID) {
					if (role == 1 && staffID.startsWith("D")) {
						return staffID;
					} else if (role == 2 && staffID.startsWith("P")) {
						return staffID;
					} else if (role == 3 && staffID.startsWith("A")) {
						return staffID;
					}
					else {
						System.out.println("ID invalid for role selected.");
					}
				}
				else {
					System.out.println("ID already exists. Please enter a unique ID.");
				}
			}
		}

	}
}