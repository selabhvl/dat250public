package no.hvl.dat250.di;

import no.hvl.dat250.di.repositories.FileBasedQuoteStore;
import no.hvl.dat250.di.repositories.ImmutableQuoteStore;
import no.hvl.dat250.di.repositories.InMemoryQuoteStore;
import no.hvl.dat250.di.repositories.QuoteStorage;
import no.hvl.dat250.di.services.FamousQuoteService;
import no.hvl.dat250.di.services.QuoteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration // alternative to the xml file configuration method
@ComponentScan("no.hvl.dat250.di.presenters") // looks for @Configuration or @Component annotations in that package
public class Beans {

    // all @Bean annotated methods below become Spring-managed "beans"

    @Bean
    public QuoteStorage memoryStorage() {
        return new InMemoryQuoteStore();
    }

    @Bean
    public QuoteStorage stubStorage() {
        return new ImmutableQuoteStore();
    }

    @Bean
    public QuoteStorage fileStorage() {
        return new FileBasedQuoteStore("quotes.xml");
    }

    @Bean
    @Primary // resolves ambiguities when a bean requests an impl of QuoteService, this one is chosen
    public QuoteService appService() {
        return new FamousQuoteService(fileStorage());
    }


    @Bean
    public QuoteService testService() {
        return new FamousQuoteService(stubStorage());
    }


}
