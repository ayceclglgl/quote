package com.quote.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.quote.model.Offer;
import com.quote.utils.Constants;

public class OfferReaderImpl implements OfferReader {

	/**
	 * Read offers from a input file.
	 * @param pathname
	 * @return
	 * @throws IOException
	 */
	public List<Offer> getOffers(String pathname) throws IOException {
		List<Offer> records = new ArrayList<>();
		
		File inputFile = new File(pathname);
		InputStream inputStream = new FileInputStream(inputFile);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

		records = br
				.lines()
				.skip(1)
				.map(line -> {
					String[] offerArr = line.split(Constants.COMMA_DELIMITER);
					String lender = offerArr[0];
					BigDecimal rate = new BigDecimal(offerArr[1]);
					BigDecimal available = new BigDecimal(offerArr[2]);
					return new Offer(lender, rate, available);
				})
				.sorted()
				.collect(Collectors.toList());

		br.close();

		return records;
	}
}