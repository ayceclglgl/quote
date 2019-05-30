# Quote Calculation System

# Problem Statement
System allowing prospective borrowers to obtain a quote from pool of lenders for 36 month loans.
System uses monthly compounding interest to provide the monthly and total repayment.

# Assumptions
- The rate in the csv file is yearly rate.
- The csv file contains non duplicate lenders.
- An offer can be partly used.

# Prerequisites
- Java 1.8
- Maven

# Running the Application
Get executable jar file(quote-0.0.1-SNAPSHOT.jar) with 'mvn clean package' command.

Using the 'quote-0.0.1-SNAPSHOT.jar' file, run the following command:

java -jar target/quote-0.0.1-SNAPSHOT.jar [market_file] [loan_amount]

# Example

java -jar target/quote-0.0.1-SNAPSHOT.jar market.csv 2500

Requested amount: £2500
Rate: 7.4%
Monthly repayment: £86.96
Total repayment: £3130.56