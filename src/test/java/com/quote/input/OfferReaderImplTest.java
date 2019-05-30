package com.quote.input;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.quote.model.Offer;

public class OfferReaderImplTest {
	
	OfferReader reader;
	
	@Before
	public void setUp() {
		reader = new OfferReaderImpl();
	}
	
	
	@Test
	public void testGetOffers() throws IOException {
		String fileName = "market.csv";
		
		List<Offer> offerList = reader.getOffers(fileName);
		
		assertNotEquals(0, offerList.size());
		assertTrue(offerList.get(0).compareTo(offerList.get(1)) < 0);
	}
	
	@Test(expected = IOException.class)
	public void testGetOffersWithException() throws IOException {
		String fileName = "filenotfound.csv";
		
		reader.getOffers(fileName);
	}
	
}
