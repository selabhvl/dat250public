package no.hvl.dat250.di.services;

import no.hvl.dat250.di.domain.Quote;

import java.util.List;

public interface QuoteService {

    public List<Quote> listQuotes();

    public void addQuote(String text, String whoSaidIt);

    public void upvoteQuote(Quote q);
}
