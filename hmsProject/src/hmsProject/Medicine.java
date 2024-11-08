package hmsProject;

import java.io.Serializable;

public class Medicine implements Serializable {
	private String name;
	private int stock;
	private int stockThreshold;

	public Medicine(String name, int stock, int thres) {
		this.name=name;
		this.stock=stock;
		this.stockThreshold=thres;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStockThreshold() {
		return stockThreshold;
	}

	public void setStockThreshold(int stockThreshold) {
		this.stockThreshold = stockThreshold;
	}
}