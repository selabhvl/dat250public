package no.hvl.dat250.di.domain;

public class Quote {

    private String text;

    private String whoSaidIt;

    private int votes;

    public Quote(String text, String whoSaidIt) {
        this.text = text;
        this.whoSaidIt = whoSaidIt;
        this.votes = 0;
    }

    public void upvote() {
        this.votes++;
    }

    public String getText() {
        return text;
    }

    public String getWhoSaidIt() {
        return whoSaidIt;
    }

    public int getVotes() {
        return votes;
    }
}
