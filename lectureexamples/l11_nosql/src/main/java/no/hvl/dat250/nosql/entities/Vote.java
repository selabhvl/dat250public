package no.hvl.dat250.nosql.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Version
    private Instant publishedAt;

    @ManyToOne
    private VoteOption votedOn;

//    @ManyToOne
//    @JsonBackReference(value = "voted")
//    private User voter;

    public Vote(VoteOption votedOn) {
        this.votedOn = votedOn;
        this.publishedAt = Instant.now();
    }

    public Vote() {
    }


    public String getId() {
        return id.toString();
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public VoteOption getVotedOn() {
        return votedOn;
    }
//
//    public User getVoter() {
//        return voter;
//    }
}
