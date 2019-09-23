package com.yuhang.stock.stockservice.models;

public class StockQuote {

	private String symbol;
	private double price;
	private double open;
	private double high;
	private double low;
	private int volumn;
	private double change;
	
	
	public StockQuote() {
	}
	
	
	public StockQuote(String symbol, double price, double open, double high, double low, int volumn, double change) {
		
		this.symbol = symbol;
		this.price = price;
		this.open = open;
		this.high = high;
		this.low = low;
		this.volumn = volumn;
		this.change = change;
	}


	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public int getVolumn() {
		return volumn;
	}
	public void setVolumn(int volumn) {
		this.volumn = volumn;
	}
	public double getChange() {
		return change;
	}
	public void setChange(double change) {
		this.change = change;
	}
	
	
}
