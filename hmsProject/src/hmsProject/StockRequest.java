package hmsProject;

import java.io.Serializable;
import java.util.Scanner;

public class StockRequest implements Serializable{

	private String medicineName;
	private int stockAmt;
	private String status;

	public StockRequest(String medicineName, int stockAmt, String status) {
		this.medicineName = medicineName;
		this.stockAmt = stockAmt;
		this.status = status;
	}

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
		int stockAmt = UtilityClass.getValidIntInput(sc, "Input Stock Amount to replenish: ");
		return new StockRequest(newMedName, stockAmt,"Pending");
	}

	public void viewStockRequests() {
		System.out.println("Medicine Name: "+this.getMedicineName()+"|Stock Amount: "+this.getStockAmt()+"|Status: "+this.getStatus());
	}

	public int getStockAmt() {
		return stockAmt;
	}

	public void setStockAmt(int stockAmt) {
		this.stockAmt = stockAmt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status.toLowerCase();
	}
	
	public String getMedicineName() {
		return medicineName;
	}
	
}