package no.hvl.dat250.di.repositories;

import no.hvl.dat250.di.domain.Quote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ImmutableQuoteStore implements QuoteStorage {


    @Override
    public Collection<Quote> loadQuotes() {
        List<Quote> result = new ArrayList<>();

        Quote q1 = new Quote("Mister Gorbatschow, tear down this wall!", "David Hasselhoff");
        q1.upvote();
        q1.upvote();
        Quote q2 = new Quote("Hasta la victoria siempre!", "John D. Rockefeller");
        q2.upvote();
        Quote q3 = new Quote("How much is the fish?", "Karl Marx");
        q3.upvote();
        q3.upvote();
        q3.upvote();
        q3.upvote();
        q3.upvote();
        result.add(q1);
        result.add(q2);
        result.add(q3);

        return result;
    }

    @Override
    public void storeQuote(Quote q) {
    }

    @Override
    public void updateQuote(Quote q) {
    }
}
