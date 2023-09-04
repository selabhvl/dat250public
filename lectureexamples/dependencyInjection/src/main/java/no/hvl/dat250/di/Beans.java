package no.hvl.dat250.di;

import no.hvl.dat250.di.repositories.FileBasedQuoteStore;
import no.hvl.dat250.di.repositories.ImmutableQuoteStore;
import no.hvl.dat250.di.repositories.QuoteStorage;
import no.hvl.dat250.di.services.FamousQuoteService;
import no.hvl.dat250.di.services.QuoteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("no.hvl.dat250.di.presenters")
public class Beans {

    @Bean
    public QuoteStorage fileStorage() {
        return new FileBasedQuoteStore("quotes.xml");
    }

    @Bean
    public QuoteService appService() {
        return new FamousQuoteService(fileStorage());
    }
}
