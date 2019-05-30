package com.quote.utils;

public class Constants {
	public static final String COMMA_DELIMITER = ",";
	
	public static final int MIN_AMOUNT = 1000;
	public static final int MAX_AMOUNT = 15000;
	public static final int FACTOR = 100;
	
	public static final int COMPOUND_PERIOD = 12;
	public static final int TERM = 3;
	
	public static final String STR_NO_QUOTE = "It is not possible to provide a quote at that time.";
	public static final String STR_INVALID_ARGS = "Application should take 2 arguments as market file and loan amount respectively.";
	public static final String STR_INVALID_MARKET_FILE_NAME = "Invalid market file name!";
	public static final String STR_INVALID_LOAN_AMOUNT = "A loan of any £100 increment between £1000 and £15000 inclusive can br requested.";
	public static final String STR_INVALID_LOAN_AMOUNT_NUMBER = "Loan amount must be a number!";
}
