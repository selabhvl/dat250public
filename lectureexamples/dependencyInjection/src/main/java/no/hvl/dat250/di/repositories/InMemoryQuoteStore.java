package no.hvl.dat250.di.repositories;

import no.hvl.dat250.di.domain.Quote;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryQuoteStore implements QuoteStorage{

    private Set<Quote> store;

    public InMemoryQuoteStore() {
        this.store = new HashSet<>();
    }

    @Override
    public Collection<Quote> loadQuotes() {
        return store.stream().collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void storeQuote(Quote q) {
        store.add(q);
    }

    @Override
    public void updateQuote(Quote q) {
        if (!store.contains(q)) {
            this.store.add(q);
        }
    }
}
