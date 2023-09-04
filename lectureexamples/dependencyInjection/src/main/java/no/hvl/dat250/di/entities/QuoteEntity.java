package no.hvl.dat250.di.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QuoteEntity {

    private String text;
    private String who;
    private Integer votes;


    public QuoteEntity() {
    }

    public QuoteEntity(String text, String who, Integer votes) {
        this.text = text;
        this.who = who;
        this.votes = votes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
