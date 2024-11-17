package hmsProject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Medicine Inventory.
 */
public class Inventory implements Serializable{
	private ArrayList<Medicine> medicineList;
	private ArrayList<StockRequest> pendingStockReq;
	private ArrayList<StockRequest> completedStockReq;

	/**
	 * Inventory constructor.
	 */
	public Inventory() {
		this.medicineList = new ArrayList<Medicine>();
		this.pendingStockReq = new ArrayList<StockRequest>();
		this.completedStockReq = new ArrayList<StockRequest>();
	}

	/**
	 * Medicine list getter.
	 * @return medicine list.
	 */
	public ArrayList<Medicine> getMedicineList() {
		return medicineList;
	}

	/**
	 * Add medicine to list.
	 * @param newMedicine new medicine
	 */
	public void addMedicine(Medicine newMedicine) {
		medicineList.add(newMedicine);
		medicineList.sort(Comparator.comparing(Medicine::getName, String.CASE_INSENSITIVE_ORDER));
	}

	/**
	 * Delete medicine to list.
	 * @param index medicine to delete
	 */
	public void deleteMedicine(int index) {
		String removeMedName = medicineList.get(index).getName();
		medicineList.remove(index);
		System.out.println(removeMedName + " has been removed!");
	}

	/**
	 * Change medicine name.
	 * @param index index of medicine in medicine list
	 * @param name new medicine name
	 */
	public void changeMedicineName(int index, String name) {
		medicineList.get(index).setName(name);
	}

	/**
	 * Pending stock request list getter.
	 * @return pending stock request list.
	 */
	public ArrayList<StockRequest> getPendingStockReq() {
		return pendingStockReq;
	}

	/**
	 * Add stock request to pending stock list.
	 * @param pendingStockReq new stock request
	 */
	public void addPendingStockReq(StockRequest pendingStockReq) {
		this.pendingStockReq.add(pendingStockReq);
	}

	/**
	 * Remove stock request from pending stock list.
	 * @param pendingStockReq stock request to remove from list
	 */
	public void removePendingStockReq(StockRequest pendingStockReq) {
		this.pendingStockReq.remove(pendingStockReq);
	}

	/**
	 * Completed stock request list.
	 * @return completed stock request list.
	 */
	public ArrayList<StockRequest> getCompletedStockReq() {
		return completedStockReq;
	}

	/**
	 * Add completed stock request to completed stock request list.
	 * @param completedStockReq completed stock request
	 */
	public void addCompletedStockReq(StockRequest completedStockReq) {
		this.completedStockReq.add(completedStockReq);
	}

	/**
	 * Medicine stock getter.
	 * @param medicineName medicine
	 * @return medicine stock.
	 */
	public int getMedicineStock(String medicineName) {
		for (Medicine med : medicineList) {
			if (med.getName().equalsIgnoreCase(medicineName)) {
				return med.getStock();
			}
		}
		return 0; 
	}

	/**
	 * Decrease stock amount.
	 * @param medicineName medicine name
	 * @param amount amount to decrease
	 */
	public void decrementStock(String medicineName, int amount) {
		for (Medicine med : medicineList) {
			if (med.getName().equalsIgnoreCase(medicineName)) {
				if (med.getStock() >= amount) {
					med.setStock(med.getStock() - amount);
					System.out.println("Stock updated. New stock for " + medicineName + ": " + med.getStock());
					System.out.println("Update process completed.");
				} else {
					System.out.println("Insufficient stock to deduct " + amount + " from " + medicineName);
				}
				return;
			}
		}
		System.out.println("Medicine " + medicineName + " not found in inventory.");
	}

	/**
	 * Print inventory information (Medicine, stock and threshold) and display alert if stock level is below threshold
	 * @param type 1 if you want to update/remove medicine, else just print the entire inventory
	 */
	public void viewInventory(int type) {
		if (type == 1){ //Display names of medicine to remove
			for (int i = 0; i < getMedicineList().size(); i++) { 
				System.out.println((i + 1) + ". " + getMedicineList().get(i).getName());
			}
		}
		else { //Display all information of medicine
			ArrayList<Medicine> tempArray = new ArrayList<Medicine>();
			for (int i = 0; i < medicineList.size(); i++) { //Display medicines and their current stock levels
				System.out.println((i + 1) + ". MedicineName: " + medicineList.get(i).getName() + 
				" | Stock: " + medicineList.get(i).getStock() + 
				" | Stock Alert Threshold: " + medicineList.get(i).getStockThreshold());
				
				if (medicineList.get(i).getStock() < medicineList.get(i).getStockThreshold()) { //adds low stock medicine into temp array
					tempArray.add(medicineList.get(i));
				}
			}
	
			if (tempArray.size() == 0) { //if no medicines are low on stock
				System.out.println("No medicines are low on stock.");
			}
			else {
				System.out.println("Warning! The following medicines are low on stock: ");
				for (int i = 0; i < medicineList.size(); i++) {
					System.out.println(tempArray.get(i).getName() + ", ");
				}
			}
		}
	}
	
	public boolean checkStockRequest(StockRequest stockRequest) {
		for(StockRequest pendingReq: getPendingStockReq()) {
			if(pendingReq.getMedicineName().equalsIgnoreCase(stockRequest.getMedicineName())) {
				System.out.println("Stock request for " + stockRequest.getMedicineName() + " already exists.");
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Check if input to select medicine is in list range.
	 * @param sc Scanner class
	 * @param prompt reason for input
	 * @return medicine index in medicine list.
	 */
	public int selectMedicine(Scanner sc, String prompt) { // Checks if medicine index is valid
		int option = 0;
	
		while (true) {
			System.out.print(prompt);
			//sc.nextLine();
			
			if (sc.hasNextInt()) {
				option = sc.nextInt();
				sc.nextLine(); // Clear the newline character after reading the integer
				
				if (option <= 0 || option > getMedicineList().size()) {
					System.out.println("Invalid Input. Input out of Range.");
				} else {
					break; // Valid input
				}
			} else {
				System.out.println("Invalid Input. Please enter a number.");
				sc.nextLine();
			}
		}
		return option;
	}
	
}