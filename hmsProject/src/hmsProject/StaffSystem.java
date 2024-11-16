package hmsProject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class StaffSystem{
	private ArrayList<Doctor> docList;
	private ArrayList<Pharmacist> phaList;
	private ArrayList<Administrator> admList;
	/**
	 * 
	 * @param staff
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

	public void addStaff(Scanner sc, ArrayList<User> staffList) {
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

		String staffName = getValidAlphabeticString(sc, "Enter Staff Name: ");
/* 					while (true) {
			boolean nameExists = false; // Flag to track if the name already exists
		
			// Check if the entered name matches any existing user's name
			for (User user : staffList) {
				Class<?> obj = adminCont.getRole(user);
				if (obj.getName().equalsIgnoreCase(staffName)) {
					System.out.println("Name already exists. Please enter a unique name.");
					nameExists = true;
					break; // Exit the for loop as we found a duplicate
				}
			}
		
			// If the name exists, prompt for a new name, otherwise break the while loop
			if (nameExists) {
				staffName = getValidAlphabeticString(sc, "Enter Staff Name: ");
			} else {
				break; // Name is unique, exit the while loop
			}
		} */

		int staffAge = getValidIntInput(sc, "Enter Staff Age: ");
		String staffGender = null;
		while (true) {
			System.out.print("Enter Staff Gender: ");
			staffGender = sc.nextLine(); // Read the input once

			// Check if the input is either "male" or "female" (case-insensitive)
			if (staffGender.equalsIgnoreCase("male") || staffGender.equalsIgnoreCase("female")) {break;} // Exit loop if input is valid
			else {System.out.println("Invalid input. Please enter 'male' or 'female'.");}
		}

		String staffID; boolean validID = false;
		while (true) {
			System.out.println("Enter Staff ID(4 characters starting with D/P/A).");
			staffID = sc.nextLine().toUpperCase();
			if (staffID.length() != 4) {System.out.println("Invalid input. Please enter a valid ID(4 characters starting with D/P/A).");}
			else {
				for (User user : staffList) { // check if staff ID already exists
					if (user.gethID().equals(staffID)) {
						validID = true;
					}
				}
				if (!validID) { // if unique, check if staff ID is for correct role
					if (choice == 1 && staffID.toUpperCase().contains("D")) {
						Doctor dr = new Doctor(staffID, "default", staffName, staffGender, staffAge);
						staffList.add(dr);
						break;
					}
					else if (choice == 2 && staffID.toUpperCase().contains("P")) {
						//Add new Pharmacist
						Pharmacist phar = new Pharmacist(staffID, "default", staffName, staffGender, staffAge);
						staffList.add(phar);
						break;
					}
					else if (choice == 3 && staffID.toUpperCase().contains("A")) {
						//Add new Administrator
						Administrator admin = new Administrator(staffID, "default", staffName, staffGender, staffAge);
						staffList.add(admin);
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

	public void removeStaff(Scanner sc, ArrayList<User> staffList) {
		int index = 0; int choice = 0; String staffID = "";
		System.out.println("Doctors:");
		for (Doctor doc : docList) {
			System.out.printf("%d. %s%n", ++index, doc); // Print index + 1 and the object’s toString()
		}
	
		System.out.println("\nPharmacists:");
		for (Pharmacist pha : phaList) {
			System.out.printf("%d. %s%n", ++index, pha); // Print index + 1 and the object’s toString()
		}
	
		System.out.println("\nAdministrators:");
		for (Administrator adm : admList) {
			System.out.printf("%d. %s%n", ++index, adm);
		}

		while (true) {
			System.out.print("\nSelect Staff to Remove: ");
			if (sc.hasNextInt()) { // check if input is an integer
				choice = sc.nextInt();	
				
				if (choice >= 1 && choice <= index) {break;} // Exit loop if input is valid within the valid range
				else { System.out.println("Invalid input. Please enter a number between 1 and " + index + ".");}
			}
			else { 
				System.out.println("Invalid input. Please enter a number.");
				sc.nextLine();
			}
		}

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
		for (User user : staffList) {
			if (user.gethID().equals(staffID)) {
				staffList.remove(user);
			}
		}
	}


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
}