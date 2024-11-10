package hmsProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
public class Inventory {

	private ArrayList<Medicine> medicineList;
	private StockRequest[] pendingStockReq;
	private StockRequest[] completedStockReq;

	public void viewStock() {
		System.out.println("Medicine Name\t Stock Level\t Stock Alert\n"); //View Medicine

		for (int i = 0; i < medicineList.size(); i++) { //Display medicines and their current stock levels
			System.out.println((i + 1) + ". " + medicineList.get(i).getName() + "\t" + medicineList.get(i).getStock()
			+ "\t" + medicineList.get(i).getStockThreshold());
		}
	}

	public ArrayList<Medicine> getMedicineList() {
		return this.medicineList;
	}

	public void checkStock() {
		for (int i = 0; i < medicineList.size(); i++) {
			if (medicineList.get(i).getStock() < medicineList.get(i).getStockThreshold()) {
				System.out.println(medicineList.get(i).getName() + " is running low on stock.");
			}
		}
	}

	public void addMedicine(String name, int stock, int stockThreshold) {
		Medicine newMedicine = new Medicine(name, stock, stockThreshold);
		medicineList.add(newMedicine);
		medicineList.sort(Comparator.comparing(Medicine::getName, String.CASE_INSENSITIVE_ORDER)); //Sort by name
	}

	public void deleteMedicine(int index) {
		String removeMedName = medicineList.get(index).getName();
		medicineList.remove(index);
		System.out.println(removeMedName + "has been removed!");
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