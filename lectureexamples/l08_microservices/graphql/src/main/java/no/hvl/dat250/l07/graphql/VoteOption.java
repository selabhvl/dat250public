package no.hvl.dat250.l07.graphql;

import java.util.ArrayList;
import java.util.Collection;

public class VoteOption {

    private String text;

    private Poll poll;

    private Collection<Vote> votes;

    public VoteOption(String text, Poll poll) {
        this.text = text;
        this.poll = poll;
        poll.getOptions().add(this);
        this.votes = new ArrayList<>();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public Collection<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Collection<Vote> votes) {
        this.votes = votes;
    }
}
