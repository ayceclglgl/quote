package com.quote.input;

import java.io.IOException;
import java.util.List;

import com.quote.model.Offer;

public interface OfferReader {
	List<Offer> getOffers(String pathname) throws IOException;
}
