package com.quote.model;

import java.math.BigDecimal;

public class Offer implements Comparable<Offer> {
	private String lender;
	private BigDecimal rate;
	private BigDecimal available;
	private BigDecimal usedAmount;
	
	public Offer() {}
	
	public Offer(String lender, BigDecimal rate, BigDecimal available) {
		this.lender = lender;
		this.rate = rate;
		this.available = available;
	}
	
	public String getLender() {
		return lender;
	}
	
	public BigDecimal getRate() {
		return rate;
	}

	public BigDecimal getAvailable() {
		return available;
	}
	
	public BigDecimal getUsedAmount() {
		return usedAmount;
	}

	public void setUsedAmount(BigDecimal usedAmount) {
		this.usedAmount = usedAmount;
	}

	@Override
	public int compareTo(Offer o) {
		return this.getRate().compareTo(o.getRate());
	}

}
