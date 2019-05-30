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
