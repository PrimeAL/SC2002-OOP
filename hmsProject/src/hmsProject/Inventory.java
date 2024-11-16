package hmsProject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Inventory implements Serializable{
	private ArrayList<Medicine> medicineList;
	private ArrayList<StockRequest> pendingStockReq;
	private ArrayList<StockRequest> completedStockReq;

	public Inventory() {
		this.medicineList = new ArrayList<Medicine>();
		this.pendingStockReq = new ArrayList<StockRequest>();
		this.completedStockReq = new ArrayList<StockRequest>();
	}

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

	public void removePendingStockReq(StockRequest pendingStockReq) {
		this.pendingStockReq.remove(pendingStockReq);
	}

	public ArrayList<StockRequest> getCompletedStockReq() {
		return completedStockReq;
	}

	public void addCompletedStockReq(StockRequest completedStockReq) {
		this.completedStockReq.add(completedStockReq);
	}
	
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
}