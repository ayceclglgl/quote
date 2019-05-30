package com.quote;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.quote.calculator.QuoteCalculator;
import com.quote.calculator.QuoteCalculatorImpl;
import com.quote.input.OfferReader;
import com.quote.input.OfferReaderImpl;
import com.quote.model.Offer;
import com.quote.model.Quote;
import com.quote.utils.Constants;


public class QuoteApplication {

	public static void main(String[] args) throws IOException {
		if (!isArgsValid(args)) {
			return;
		}

		OfferReader reader = new OfferReaderImpl();
		List<Offer> offerList = reader.getOffers(args[0]);
		
		QuoteCalculator qc = new QuoteCalculatorImpl();
		Optional<Quote> quote = qc.getQuote(new BigDecimal(args[1]), offerList);
		Quote.print(quote);
	}
	
	private static boolean isArgsValid(String[] arr) {
		if(arr.length != 2) {
			System.out.print(Constants.STR_INVALID_ARGS);
			return false;
		}
		 
		String marketFileName = arr[0];
		if(marketFileName == null || marketFileName.isEmpty()) {
			System.out.print(Constants.STR_INVALID_MARKET_FILE_NAME);
			return false;
		}
		
		long loanAmount = 0;
		try {
			loanAmount = Long.parseLong(arr[1]);
		} catch (NumberFormatException e) {
			System.out.print(Constants.STR_INVALID_LOAN_AMOUNT_NUMBER);
			return false;
		}
		if(loanAmount % Constants.FACTOR != 0 || loanAmount < Constants.MIN_AMOUNT  || loanAmount > Constants.MAX_AMOUNT ) {
			System.out.print(Constants.STR_INVALID_LOAN_AMOUNT);
			return false;
		}
		
		return true;
	}
}