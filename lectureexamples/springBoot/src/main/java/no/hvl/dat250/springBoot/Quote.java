package no.hvl.dat250.springBoot;

public class Quote {
    private String quote;
    private String who;

    public Quote() {

    }

    public Quote(String quote, String who) {
        this.quote = quote;
        this.who = who;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
