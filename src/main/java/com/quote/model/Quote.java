package com.quote.model;

import java.math.BigDecimal;
import java.util.Optional;

import com.quote.utils.Constants;

public class Quote {
	private BigDecimal requestedAmount;
	private BigDecimal montlyRepayment;
	private BigDecimal totalRepayment;
	private BigDecimal rate;
	
	
	public Quote(BigDecimal requestedAmount, BigDecimal rate, BigDecimal montlyRepayment, BigDecimal totalRepayment) {
		this.requestedAmount = requestedAmount;
		this.rate = rate;
		this.montlyRepayment = montlyRepayment;
		this.totalRepayment = totalRepayment;
	}

	public BigDecimal getRequestedAmount() {
		return requestedAmount;
	}

	public BigDecimal getMontlyRepayment() {
		return montlyRepayment;
	}

	public BigDecimal getTotalRepayment() {
		return totalRepayment;
	}

	public BigDecimal getRate() {
		return rate;
	}

	
	private BigDecimal formatRate(BigDecimal rate) {
		return rate.multiply(new BigDecimal(100)).setScale(1, BigDecimal.ROUND_HALF_UP);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Requested amount: \u00a3");
		builder.append(this.requestedAmount);
		builder.append("\n");
		
		builder.append("Rate: ");
		builder.append(formatRate(this.rate));
		builder.append("%");
		builder.append("\n");
		
		builder.append("Monthly repayment: \u00a3");
		builder.append(this.montlyRepayment.setScale(2, BigDecimal.ROUND_HALF_UP));
		builder.append("\n");
		builder.append("Total repayment: \u00a3");
		builder.append(this.totalRepayment.setScale(2, BigDecimal.ROUND_HALF_UP));
		return builder.toString();
	}
	
	public void print() {
		System.out.print(this.toString());
	}
	
	public static void print(Optional<Quote> quote) {
		System.out.print( quote.isPresent() ? quote.get().toString() : Constants.STR_NO_QUOTE);
	}
}
