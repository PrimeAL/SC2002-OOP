package hmsProject;
import java.io.Serializable;
import java.util.ArrayList;

public class StockRequest implements Serializable{

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
		for (int i = 0; i < stockRequestList.size(); i++) {
			if (stockRequestList.get(i).getMedicineName().equalsIgnoreCase(stockRequest.getMedicineName())) { //check if stock request already exists regardless of med name
				if (stockRequestList.get(i).getStatus().equals("pending"))
				{
					System.out.println("Stock request for " + stockRequest.getMedicineName() + " already exists.");
				}
				else
				{
					stockRequestList.get(i).setStatus("pending");
					stockRequestList.get(i).setStockAmt(stockRequest.getStockAmt());
				}
				return;
			}
		}
		stockRequestList.add(stockRequest);
	}

	public void viewStockRequests() { //haven finish
		for (int i = 0; i < stockRequestList.size(); i++) {
			if (stockRequestList.get(i).getStatus().equals("pending")) {
				System.out.println((i + 1) + "Medicine Name: " + stockRequestList.get(i).getMedicineName());
			}
		}
	}

	public ArrayList<StockRequest> getStockRequestList() {
		return stockRequestList;
	}

	public ArrayList<StockRequest> getPendingStockRequests() {
		ArrayList<StockRequest> pendingStockRequests = new ArrayList<StockRequest>();
		for (int i = 0; i < stockRequestList.size(); i++) {
			if (stockRequestList.get(i).getStatus().equals("pending")) {
				pendingStockRequests.add(stockRequestList.get(i));
			}
		}
		return pendingStockRequests;
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