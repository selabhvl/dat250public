package no.hvl.dat250.di;

import no.hvl.dat250.di.presenters.QuoteCLI;
import no.hvl.dat250.di.repositories.ImmutableQuoteStore;
import no.hvl.dat250.di.repositories.InMemoryQuoteStore;
import no.hvl.dat250.di.services.FamousQuoteService;
import no.hvl.dat250.di.services.QuoteService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Beans.class);
        QuoteCLI cli = context.getBean(QuoteCLI.class);
        cli.run();
    }
}
