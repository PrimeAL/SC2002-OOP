package hmsProject;

import java.io.Serializable;
import java.util.ArrayList;
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
		System.out.println("\tMedicine Name\t Stock Level\t Stock Alert\n"); //View Medicine

		for (int i = 0; i < medicineList.size(); i++) { //Display medicines and their current stock levels
			System.out.println((i + 1) + ".\t " + medicineList.get(i).getName() + "\t" + medicineList.get(i).getStock()
			+ "\t" + medicineList.get(i).getStockThreshold());

			if (medicineList.get(i).getStock() < medicineList.get(i).getStockThreshold()) {
				System.out.println("\t" +medicineList.get(i).getName() + " is running low on stock.");
			}
		}
	}

	public ArrayList<Medicine> getMedicineList() {
		return this.medicineList;
	}

	public void addMedicine(Medicine newMedicine) {
		medicineList.add(newMedicine);
		medicineList.sort(Comparator.comparing(Medicine::getName, String.CASE_INSENSITIVE_ORDER)); //Sort by name
	}

	public void deleteMedicine(int index) {
		String removeMedName = medicineList.get(index).getName();
		medicineList.remove(index);
		System.out.println(removeMedName + " has been removed!");
	}

	public void changeMedicineName(int index, String name) {
		medicineList.get(index).setName(name);
		medicineList.sort(Comparator.comparing(Medicine::getName, String.CASE_INSENSITIVE_ORDER)); //Sort by name
	}

	public void submitReq() {
		// TODO - implement Inventory.submitReq
		throw new UnsupportedOperationException();
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