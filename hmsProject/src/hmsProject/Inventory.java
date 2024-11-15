package hmsProject;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.Comparator;
public class Inventory implements Serializable {

	private ArrayList<Medicine> medicineList;
	private StockRequest[] pendingStockReq;
	private StockRequest[] completedStockReq;

	public Inventory() {
		this.medicineList = new ArrayList<Medicine>();
		this.pendingStockReq = new StockRequest[0];
		this.completedStockReq = new StockRequest[0];
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

		System.out.println("Warning! The following medicines are low on stock: ");
		for (int i = 0; i < medicineList.size(); i++) {
			System.out.println(tempArray.get(i).getName() + ", ");
		}
	}

	public ArrayList<Medicine> getMedicineList() {
		return this.medicineList;
	}

	public void submitReq() {
		// TODO - implement Inventory.submitReq
		throw new UnsupportedOperationException();
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

	public void acceptReq() {
		// TODO - implement Inventory.acceptReq
		throw new UnsupportedOperationException();
	}

	public void dispense() {
		// TODO - implement Inventory.dispense
		throw new UnsupportedOperationException();
	}

}