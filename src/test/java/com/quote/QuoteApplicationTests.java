package com.quote;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.quote.utils.Constants;

public class QuoteApplicationTests {
	
	private final ByteArrayOutputStream out = new ByteArrayOutputStream();
	
	@Before
	public void setUp() {
		System.setOut(new PrintStream(out));
	}

	@After
	public void tearDown() {
		System.setOut(System.out);
	}
	
	@Test
	public void testInvalidArgsNumber() throws IOException {
		QuoteApplication.main(new String[] {"7000"});
		
		assertEquals(Constants.STR_INVALID_ARGS, out.toString());
	}
	
	@Test
	public void testInvalidMarketFileName() throws IOException {
		QuoteApplication.main(new String[] {"", "7000"});
		
		assertEquals(Constants.STR_INVALID_MARKET_FILE_NAME, out.toString());
	}
	
	@Test
	public void testInvalidLoanAmountNumber() throws IOException {
		QuoteApplication.main(new String[] {"market.csv", "70k0"});
		
		assertEquals(Constants.STR_INVALID_LOAN_AMOUNT_NUMBER, out.toString());
	}
	
	@Test
	public void testInvalidLoanAmountMin() throws IOException {
		QuoteApplication.main(new String[] {"market.csv", "700"});
		
		assertEquals(Constants.STR_INVALID_LOAN_AMOUNT, out.toString());
	}
	
	@Test
	public void testInvalidLoanAmountMax() throws IOException {
		QuoteApplication.main(new String[] {"market.csv", "20000"});
		
		assertEquals(Constants.STR_INVALID_LOAN_AMOUNT, out.toString());
	}
	
	@Test
	public void testInvalidLoanAmountFactor() throws IOException {
		QuoteApplication.main(new String[] {"market.csv", "1250"});
		
		assertEquals(Constants.STR_INVALID_LOAN_AMOUNT, out.toString());
	}
	
	@Test(expected = IOException.class)// FileNotFoundException.class
	public void testIOException() throws IOException {
		QuoteApplication.main(new String[] {"filenotfound.csv", "7000"});
	}

	@Test
	public void testQuoteApplication() throws IOException {
		QuoteApplication.main(new String[] {"market.csv", "1000"});
		
		String expectedQuote = "Requested amount: £1000\nRate: 7.0%\nMonthly repayment: £34.45\nTotal repayment: £1240.30";
		assertEquals(expectedQuote, out.toString());
	}
	
	@Test
	public void testQuoteApplicationWhenNoQuote() throws IOException {
		QuoteApplication.main(new String[] {"market.csv", "15000"});
		
		assertEquals(Constants.STR_NO_QUOTE, out.toString());
	}

}
