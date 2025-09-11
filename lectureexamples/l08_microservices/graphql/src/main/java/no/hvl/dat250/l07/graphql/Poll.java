package no.hvl.dat250.l07.graphql;

import java.util.ArrayList;
import java.util.List;

public class Poll {

    private int id;
    private String question;

    private User owner;

    private List<VoteOption> options;

    public Poll(int id, String question, User owner) {
        this.question = question;
        this.options = new ArrayList<>();
        this.id = id;
        this.owner = owner;
        owner.getCreated().add(this);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<VoteOption> getOptions() {
        return options;
    }

    public void setOptions(List<VoteOption> options) {
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
