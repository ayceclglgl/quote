package com.quote.model;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.quote.utils.Constants;

public class QuoteTest {
	
	private final ByteArrayOutputStream out = new ByteArrayOutputStream();
	private Quote quote;
	private String expectedQuote;

	@Before
	public void setUp() {
		System.setOut(new PrintStream(out));
		quote = new Quote(new BigDecimal("1000"), new BigDecimal("0.07"), new BigDecimal("34.45"),
				new BigDecimal("1240.30"));
		expectedQuote = "Requested amount: £1000\nRate: 7.0%\nMonthly repayment: £34.45\nTotal repayment: £1240.30";
	}

	@After
	public void tearDown() {
		System.setOut(System.out);
	}
	
	@Test
	public void testToString() {
		assertEquals(expectedQuote, quote.toString());
	}
	
	@Test
	public void testStaticPrintWithQuote() {
		Quote.print(Optional.of(quote));
		
		assertEquals(expectedQuote, out.toString());
	}
	
	@Test
	public void testStaticPrintWithNoQuote() {
		Quote.print(Optional.empty());
		
		assertEquals(Constants.STR_NO_QUOTE, out.toString());
	}
	
	@Test
	public void testPrint() {
		quote.print();
		
		assertEquals(expectedQuote, out.toString());
	}
	
}
