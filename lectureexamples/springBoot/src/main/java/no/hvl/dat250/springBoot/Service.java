package no.hvl.dat250.springBoot;

@org.springframework.stereotype.Service
public class Service {

    public Quote randomQuote() {
        return new Quote("How much is this fish?", "Karl Marx");
    }

}
