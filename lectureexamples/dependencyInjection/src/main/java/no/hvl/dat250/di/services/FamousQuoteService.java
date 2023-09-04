package no.hvl.dat250.di.services;

import no.hvl.dat250.di.domain.Quote;
import no.hvl.dat250.di.repositories.ImmutableQuoteStore;
import no.hvl.dat250.di.repositories.InMemoryQuoteStore;
import no.hvl.dat250.di.repositories.QuoteStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FamousQuoteService  implements QuoteService{

    private QuoteStorage quoteStorage;


    public FamousQuoteService(QuoteStorage quoteStorage) {
        this.quoteStorage = quoteStorage;
    }

    public List<Quote> listQuotes() {
        return this.quoteStorage.loadQuotes().stream()
                .sorted((q1, q2) -> -Integer.compare(q1.getVotes(), q2.getVotes()))
                .collect(Collectors.toList());
    }

    public void addQuote(String quote, String whoSaidIt) {
        this.quoteStorage.storeQuote(new Quote(quote, whoSaidIt));
    }

    @Override
    public void upvoteQuote(Quote q) {
        q.upvote();
        quoteStorage.updateQuote(q);
    }


}
