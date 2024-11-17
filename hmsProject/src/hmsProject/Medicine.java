package hmsProject;

import java.io.Serializable;

/**
 * Medicine class.
 */
public class Medicine implements Serializable {
	private String name;
	private int stock;
	private int stockThreshold;

	/**
	 * Medicine constructor.
	 * @param name medicine name
	 * @param stock stock level
	 * @param thres threshold to alert that stock is low
	 */
	public Medicine(String name, int stock, int thres) {
		this.name=name;
		this.stock=stock;
		this.stockThreshold=thres;
	}

	/**
	 * name getter.
	 * @return medicine name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * name setter.
	 * @param name new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * stock getter.
	 * @return stock amount.
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * stock setter.
	 * @param stock new stock
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * threshold getter.
	 * @return threshold.
	 */
	public int getStockThreshold() {
		return stockThreshold;
	}

	/**
	 * threshold setter.
	 * @param stockThreshold new threshold
	 */
	public void setStockThreshold(int stockThreshold) {
		this.stockThreshold = stockThreshold;
	}
}