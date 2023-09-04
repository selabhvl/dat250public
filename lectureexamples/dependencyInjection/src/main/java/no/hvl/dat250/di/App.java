package no.hvl.dat250.di;

import no.hvl.dat250.di.presenters.QuoteCLI;

public class App {

    public static void main(String[] args) {
        QuoteCLI cli = new QuoteCLI();
        cli.run();
    }
}
