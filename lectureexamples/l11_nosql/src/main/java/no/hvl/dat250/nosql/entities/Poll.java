package no.hvl.dat250.nosql.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "polls")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @Version
    private Instant publishedAt;

    private Instant validUntil;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "voteOptions")
    private List<VoteOption> voteOptions;

    public Poll(String question) {
        this.question = question;
        this.voteOptions = new ArrayList<>();
        this.publishedAt = Instant.now();
    }

    public Poll() {
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    public VoteOption addOption(String caption) {
        VoteOption o = new VoteOption(caption, this.voteOptions.size());
        this.voteOptions.add(o);
        return o;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public Instant getValidUntil() {
        return validUntil;
    }


    public List<VoteOption> getVoteOptions() {
        return voteOptions;
    }

}
