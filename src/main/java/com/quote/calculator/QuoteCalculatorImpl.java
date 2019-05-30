package com.quote.calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.quote.model.Offer;
import com.quote.model.Quote;
import com.quote.utils.Constants;

public class QuoteCalculatorImpl implements QuoteCalculator{

	/**
	 * Returns a quote if there is one.
	 * @param amount
	 * @param offerList
	 * @return
	 */
	public Optional<Quote> getQuote(BigDecimal amount, List<Offer> offerList) {
		if (isQuotePossible(amount, offerList)) {
			List<Offer> usedOffers = getOffersToUse(amount, offerList);
			
			BigDecimal rate = computeRate(amount, usedOffers);
			BigDecimal totalRepayment = computeTotalRepayment(amount, usedOffers);
			BigDecimal montlyRepayment = computeMonthlyRepayment(totalRepayment);
			
			return Optional.of(new Quote(amount, rate, montlyRepayment, totalRepayment));
		}
		return Optional.empty();
	}
	
	/**
	 * Checks if the market does not have sufficient offers from lenders to provide a quote. 
	 * @param amount
	 * @param offerList
	 */
	private boolean isQuotePossible(BigDecimal amount, List<Offer> offerList){
		BigDecimal totalAvailableAmount = offerList
				.stream()
				.map(offer -> offer.getAvailable())
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		return totalAvailableAmount.compareTo(amount) == -1 ? false : true;
	}
	
	/**
	 * Returns used offers from lender offers.
	 * @param amount
	 * @param offerList
	 * @return
	 */
	private List<Offer> getOffersToUse(BigDecimal amount, List<Offer> offerList){
		List<Offer> usedOffers = new ArrayList<>();
		BigDecimal tempAmount = BigDecimal.ZERO;
		BigDecimal usedAmount = BigDecimal.ZERO;
		
		for (Offer offer : offerList) {
			if (tempAmount.compareTo(amount) == 0) 
				break;
			
			usedAmount = tempAmount.add(offer.getAvailable()).compareTo(amount) < 0 ? offer.getAvailable() : amount.subtract(tempAmount);
			tempAmount = tempAmount.add(usedAmount);
			offer.setUsedAmount(usedAmount);
			usedOffers.add(offer);
		}

		return usedOffers;
	}
	
	/**
	 * Return an annual interest rate.
	 * @param amount
	 * @param offerList
	 * @return
	 */
	private BigDecimal computeRate(BigDecimal amount, List<Offer> list ) {
		return list
				.stream()
				.map(offer -> offer.getUsedAmount().multiply(offer.getRate()))
				.reduce(BigDecimal.ZERO, BigDecimal::add)
				.divide(amount, BigDecimal.ROUND_HALF_UP );
	}
	
	/**
	 * Calculates total repayments via monthly compounding interest.
	 * A = P (1 + r / n ) ^ nt
	 * P: principle amount
	 * r: annual rate of return
	 * n: number of compounding periods per year
	 * t: number of years
	 * @param amount
	 * @param rate
	 * @return
	 */
	private BigDecimal computeTotalRepayment(BigDecimal amount, List<Offer> list) {
		return list
				.stream()
				.map(offer -> offer.getUsedAmount().multiply(function(offer.getRate())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	/**
	 * Part of the monthly compounding interest formula.
	 * (1 + r / n ) ^ nt
	 * r: annual rate of return
	 * n: number of compounding periods per year
	 * t: number of years
	 * @param rate
	 * @return
	 */
	private BigDecimal function(BigDecimal rate) {
		return BigDecimal.ONE.add(rate.divide(new BigDecimal(Constants.COMPOUND_PERIOD), BigDecimal.ROUND_HALF_UP))
				.pow(Constants.TERM * Constants.COMPOUND_PERIOD);
	}

	/**
	 * Calculates monthly repayments.
	 * @param totalRepayment
	 * @return
	 */
	private BigDecimal computeMonthlyRepayment(BigDecimal totalRepayment) {
		return totalRepayment.divide(new BigDecimal(Constants.TERM * Constants.COMPOUND_PERIOD),
				BigDecimal.ROUND_HALF_UP);
	}

}
