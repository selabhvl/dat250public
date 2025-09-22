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


    public Vote(VoteOption votedOn) {
        this.votedOn = votedOn;
        this.publishedAt = Instant.now();
    }

    public Vote() {
    }


    public Instant getPublishedAt() {
        return publishedAt;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public VoteOption getVotedOn() {
        return votedOn;
    }

}
