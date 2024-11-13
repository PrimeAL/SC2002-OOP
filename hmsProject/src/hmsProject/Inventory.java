package hmsProject;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable{
	private ArrayList<Medicine> medicine;
	private ArrayList<StockRequest> pendingStockReq;
	private ArrayList<StockRequest> completedStockReq;

	public ArrayList<Medicine> getMedicine() {
		return medicine;
	}

	public void addMedicine(Medicine medicine) {
		this.medicine.add(medicine);
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
		this.medicine = new ArrayList<Medicine>();
		this.pendingStockReq = new ArrayList<StockRequest>();
		this.completedStockReq = new ArrayList<StockRequest>();
	}
	
	public void viewStock() {
		// TODO - implement Inventory.viewStock
		throw new UnsupportedOperationException();
	}

	public void submitReq() {
		// TODO - implement Inventory.submitReq
		throw new UnsupportedOperationException();
	}

	public void updateInven() {
		// TODO - implement Inventory.updateInven
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