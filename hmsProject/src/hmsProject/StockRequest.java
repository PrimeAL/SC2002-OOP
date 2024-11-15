package hmsProject;
import java.util.ArrayList;

public class StockRequest {

	private String medicineName;
	private int stockAmt;
	private String status;
	private ArrayList<StockRequest> stockRequestList;

	public StockRequest(String medicineName, int stockAmt, String status) {
		this.medicineName = medicineName;
		this.stockAmt = stockAmt;
		this.status = status;
	}

	public void createStockRequest(StockRequest stockRequest) {
		stockRequestList.add(stockRequest);
	}

	public void viewStockRequests() { //haven finish

		for (int i = 0; i < stockRequestList.size(); i++) {
			System.out.println((i + 1) + "Medicine Name: " + stockRequestList.get(i).getMedicineName());
		}
	}

	public ArrayList<StockRequest> getStockRequestList() {
		return stockRequestList;
	}
	public int getStockAmt() {
		return stockAmt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMedicineName() {
		return medicineName;
	}
}