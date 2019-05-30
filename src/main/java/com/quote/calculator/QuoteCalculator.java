package com.quote.calculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.quote.model.Offer;
import com.quote.model.Quote;

public interface QuoteCalculator {
	Optional<Quote> getQuote(BigDecimal amount, List<Offer> offerList);
}
