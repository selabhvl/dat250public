package no.hvl.dat250.di;

import no.hvl.dat250.di.repositories.InMemoryQuoteStore;
import no.hvl.dat250.di.repositories.QuoteStorage;
import no.hvl.dat250.di.services.FamousQuoteService;
import no.hvl.dat250.di.services.QuoteService;

public class ApplicationManager {

    private QuoteService quoteService;
    private QuoteStorage quoteStorage;

    private static ApplicationManager instance;


    private ApplicationManager() {
        quoteStorage = new InMemoryQuoteStore();
        quoteService = new FamousQuoteService(quoteStorage);
    }

    public static ApplicationManager getInstance() {
        if (instance == null) {
            instance = new ApplicationManager();
        }
        return instance;
    }

    public QuoteService getQuoteService() {
        return quoteService;
    }

    public QuoteStorage getQuoteStorage() {
        return quoteStorage;
    }
}
