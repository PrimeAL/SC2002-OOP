package hmsProject;

public class Inventory {

	private Medicine[] medicine;
	private StockRequest[] pendingStockReq;
	private StockRequest[] completedStockReq;

	public void viewStock() {
		// TODO - implement Inventory.viewStock
		throw new UnsupportedOperationException();
	}

	public void submitReq() {
		// TODO - implement Inventory.submitReq
		/*public void submitReq() {
			Scanner sc = new Scanner(System.in);
		
			// Display available medicines
			System.out.println("Available medicines in inventory:");
			for (int i = 0; i < medicine.length; i++) {
				if (medicine[i] != null) {
					System.out.println(i + 1 + ". " + medicine[i].getName() + " (Stock: " + medicine[i].getStock() + ")");
				}
			}
		
			// Ask the user to select a medicine to request
			System.out.print("Enter the number of the medicine you want to request for replenishment: ");
			int selectedMedicineIndex = sc.nextInt() - 1;  // Adjusting index to match user input
		
			// Validate the selection
			if (selectedMedicineIndex < 0 || selectedMedicineIndex >= medicine.length || medicine[selectedMedicineIndex] == null) {
				System.out.println("Invalid selection. No such medicine found.");
				return;
			}
		
			// Ask for the quantity to request
			System.out.print("Enter the quantity to request: ");
			int quantityToRequest = sc.nextInt();
		
			// Validate the requested quantity
			if (quantityToRequest <= 0) {
				System.out.println("Invalid quantity. It must be greater than zero.");
				return;
			}
		
			// Create a new StockRequest
			StockRequest newRequest = new StockRequest(medicine[selectedMedicineIndex], quantityToRequest);
		
			// Add the request to the pending requests
			// If pendingStockReq is full, increase its size or handle it dynamically using ArrayList
			// Assuming you have a way to dynamically grow the array or use ArrayList
			if (pendingStockReq == null) {
				pendingStockReq = new StockRequest[1];
				pendingStockReq[0] = newRequest;
			} else {
				// Expanding the array size
				StockRequest[] newPendingReq = new StockRequest[pendingStockReq.length + 1];
				System.arraycopy(pendingStockReq, 0, newPendingReq, 0, pendingStockReq.length);
				newPendingReq[pendingStockReq.length] = newRequest;
				pendingStockReq = newPendingReq;
			}
		
			// Confirm submission
			System.out.println("Stock request for " + quantityToRequest + " units of " + medicine[selectedMedicineIndex].getName() + " has been submitted.");
		}*/
		
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