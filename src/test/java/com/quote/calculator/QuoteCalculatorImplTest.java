package com.quote.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.quote.model.Offer;
import com.quote.model.Quote;

public class QuoteCalculatorImplTest {
	
	List<Offer> offerList;
	QuoteCalculator qc;
	
	@Before
	public void setUp() {
		qc = new QuoteCalculatorImpl();
		offerList = Arrays.asList(
						new Offer("Liz",  new BigDecimal("0.07"),  new BigDecimal("1200")),
						new Offer("Mike",  new BigDecimal("0.091"), new BigDecimal("600")),
						new Offer("Bob",  new BigDecimal("0.072"), new BigDecimal("450")));
	}
	
	@Test
	public void testGetQuoteOk() {
		BigDecimal loanAmount = new BigDecimal("1000");
		
		Optional<Quote> optionalQuote = qc.getQuote(loanAmount, offerList);
		
		assertTrue(optionalQuote.isPresent());
		assertEquals(loanAmount, optionalQuote.get().getRequestedAmount());
		assertNotEquals(optionalQuote.get().getTotalRepayment(), loanAmount);
		assertEquals(new BigDecimal("0.07"), optionalQuote.get().getRate());
		assertEquals(new BigDecimal("39.74"), optionalQuote.get().getMontlyRepayment().setScale(2, BigDecimal.ROUND_HALF_UP));
		assertEquals(new BigDecimal("1430.77"),	optionalQuote.get().getTotalRepayment().setScale(2, BigDecimal.ROUND_HALF_UP));
	}
	
	@Test
	public void testGetQuoteOkMultipleLenders() {
		BigDecimal loanAmount = new BigDecimal("2000");
		
		Optional<Quote> optionalQuote = qc.getQuote(loanAmount, offerList);
		
		assertTrue(optionalQuote.isPresent());
		assertEquals(loanAmount, optionalQuote.get().getRequestedAmount());
		assertNotEquals(optionalQuote.get().getTotalRepayment(), loanAmount);
		assertEquals(new BigDecimal("0.077"), optionalQuote.get().getRate());
		assertEquals(new BigDecimal("76.79"), optionalQuote.get().getMontlyRepayment().setScale(2, BigDecimal.ROUND_HALF_UP));
		assertEquals(new BigDecimal("2764.32"), optionalQuote.get().getTotalRepayment().setScale(2, BigDecimal.ROUND_HALF_UP));
	}
	
	@Test
	public void testGetQuoteNotOk() {
		BigDecimal loanAmount = new BigDecimal("10000");
		
		Optional<Quote> optionalQuote = qc.getQuote(loanAmount, offerList);
		
		assertFalse(optionalQuote.isPresent());
	}

	
}
