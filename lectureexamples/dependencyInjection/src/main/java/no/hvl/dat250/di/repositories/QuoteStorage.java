package no.hvl.dat250.di.repositories;

import no.hvl.dat250.di.domain.Quote;

import java.util.Collection;

public interface QuoteStorage {

    public Collection<Quote> loadQuotes();

    public void storeQuote(Quote q);

    public void updateQuote(Quote q);

}
