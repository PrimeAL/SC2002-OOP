package hmsProject;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Stock Request.
 */
public class StockRequest implements Serializable{
	private String medicineName;
	private int stockAmt;
	private String status;

	/**
	 * Stock request constructor.
	 * @param medicineName medicine name
	 * @param stockAmt stock amount
	 * @param status request status, pending or completed
	 */
	public StockRequest(String medicineName, int stockAmt, String status) {
		this.medicineName = medicineName;
		this.stockAmt = stockAmt;
		this.status = status;
	}

	/**
	 * Create new StockRequest object.
	 * @param pharmaCont Pharmacist Controller
	 * @param sc Scanner class
	 * @return StockRequest object.
	 */
	public static StockRequest createStockRequest(PharmacistController pharmaCont,Scanner sc) {
		pharmaCont.getInventory().viewInventory(1);

		int option = 0;
		
		while (true) {
			System.out.print("Select Medicine to create Stock Request:");
			//sc.nextLine();
			
			if (sc.hasNextInt()) {
				option = sc.nextInt();
				sc.nextLine(); // Clear the newline character after reading the integer
				
				if (option <= 0 || option > pharmaCont.getInventory().getMedicineList().size()) {
					System.out.println("Invalid Input. Input out of Range.");
				} else {
					break; // Valid input
				}
			} else {
				System.out.println("Invalid Input. Please enter a number.");
				sc.nextLine();
			}
		}
				
		String newMedName = pharmaCont.getInventory().getMedicineList().get(option-1).getName();
		int stockAmt=0;
		while(true) {
			stockAmt = UtilityClass.getValidIntInput(sc, "Input Stock Amount to replenish: ");
			if(stockAmt>0) break;
			System.out.println("Quantity cannot be lesser than 0!");
		}
		return new StockRequest(newMedName, stockAmt,"Pending");
	}

	/**
	 * Print all stock requests.
	 */
	public void viewStockRequests() {
		System.out.println("Medicine Name: "+this.getMedicineName()+"|Stock Amount: "+this.getStockAmt()+"|Status: "+this.getStatus());
	}

	/**
	 * Stock amount getter.
	 * @return stock amount.
	 */
	public int getStockAmt() {
		return stockAmt;
	}

	/**
	 * Stock amount setter.
	 * @param stockAmt new stock
	 */
	public void setStockAmt(int stockAmt) {
		this.stockAmt = stockAmt;
	}

	/**
	 * Status getter.
	 * @return status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Status setter.
	 * @param status new status.
	 */
	public void setStatus(String status) {
		this.status = status.toLowerCase();
	}

	/**
	 * Medicine name getter.
	 * @return medicine name.
	 */
	public String getMedicineName() {
		return medicineName;
	}
	
}