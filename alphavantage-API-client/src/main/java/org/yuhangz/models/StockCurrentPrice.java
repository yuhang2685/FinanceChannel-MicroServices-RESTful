package org.yuhangz.models;

import java.time.LocalDateTime;

public class StockCurrentPrice {
	  
	private String symbol;
	private LocalDateTime dateTime;
	private double close;
	private double previousClose;
	  
	public StockCurrentPrice() {}
	
	public StockCurrentPrice(String symbol, LocalDateTime dateTime, double close, double previousClose) {
		super();
		this.symbol = symbol;
		this.dateTime = dateTime;
		this.close = close;
		this.previousClose = previousClose;
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public double getPreviousClose() {
		return previousClose;
	}
	public void setPreviousClose(double previousClose) {
		this.previousClose = previousClose;
	}
	@Override
	public String toString() {
		return "StockCurrentPrice [symbol=" + symbol + ", dateTime=" + dateTime + ", close=" + close
				+ ", previousClose=" + previousClose + "]";
	}
	  
	  
}
