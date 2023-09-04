package no.hvl.dat250.di;

import no.hvl.dat250.di.presenters.QuoteCLI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Beans.class);
        QuoteCLI cli = context.getBean(QuoteCLI.class);
        cli.run();
    }
}
