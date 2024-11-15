package hmsProject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Inventory implements Serializable{
	private ArrayList<Medicine> medicineList;
	private ArrayList<StockRequest> pendingStockReq;
	private ArrayList<StockRequest> completedStockReq;

	public ArrayList<Medicine> getMedicineList() {
		return medicineList;
	}

	public void addMedicine(Medicine newMedicine) {
		medicineList.add(newMedicine);
		medicineList.sort(Comparator.comparing(Medicine::getName, String.CASE_INSENSITIVE_ORDER));
	}

	public void deleteMedicine(int index) {
		String removeMedName = medicineList.get(index).getName();
		medicineList.remove(index);
		System.out.println(removeMedName + " has been removed!");
	}

	public void changeMedicineName(int index, String name) {
		medicineList.get(index).setName(name);
	}

	public ArrayList<StockRequest> getPendingStockReq() {
		return pendingStockReq;
	}

	public void addPendingStockReq(StockRequest pendingStockReq) {
		this.pendingStockReq.add(pendingStockReq);
	}

	public ArrayList<StockRequest> getCompletedStockReq() {
		return completedStockReq;
	}

	public void addCompletedStockReq(StockRequest completedStockReq) {
		this.completedStockReq.add(completedStockReq);
	}

	public Inventory() {
		this.medicineList = new ArrayList<Medicine>();
		this.pendingStockReq = new ArrayList<StockRequest>();
		this.completedStockReq = new ArrayList<StockRequest>();
	}
	
	public void viewInventory() {
		ArrayList<Medicine> tempArray = new ArrayList<Medicine>();
		for (int i = 0; i < medicineList.size(); i++) { //Display medicines and their current stock levels
			System.out.println((i + 1) + ". MedicineName: " + medicineList.get(i).getName() + 
			" | Stock: " + medicineList.get(i).getStock() + 
			" | Stock Alert Threshold: " + medicineList.get(i).getStockThreshold());
			
			if (medicineList.get(i).getStock() < medicineList.get(i).getStockThreshold()) { //adds low stock medicine into temp array
				tempArray.add(medicineList.get(i));
			}
		}

		if (tempArray.size() == 0) {
			System.out.println("Warning! The following medicines are low on stock: ");
			for (int i = 0; i < medicineList.size(); i++) {
				System.out.println(tempArray.get(i).getName() + ", ");
			}
		} 
		else
			System.out.println("No medicine low on stock");
	}

	//alan pharamcist input//
	//This method checks if a medicine with the given name exists in the medicineList and has a stock greater than zero.//
	public boolean isMedicineAvailable(String medicineName) {
		for (Medicine med : medicineList) {
			if (med.getName().equalsIgnoreCase(medicineName) && med.getStock() > 0) {
				return true;
			}
		}
		return false;
	}

	//This method retrieves the current stock level of a specific medicine. Return 0 if not found
	public int getMedicineStock(String medicineName) {
		for (Medicine med : medicineList) {
			if (med.getName().equalsIgnoreCase(medicineName)) {
				return med.getStock();
			}
		}
		return 0; 
	}

	//Stock deduction
	public void decrementStock(String medicineName, int amount) {
		for (Medicine med : medicineList) {
			if (med.getName().equalsIgnoreCase(medicineName)) {
				if (med.getStock() >= amount) {
					med.setStock(med.getStock() - amount);
					System.out.println("Stock updated. New stock for " + medicineName + ": " + med.getStock());
				} else {
					System.out.println("Insufficient stock to deduct " + amount + " from " + medicineName);
				}
				return;
			}
		}
		System.out.println("Medicine " + medicineName + " not found in inventory.");
	}
	
}