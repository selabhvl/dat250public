package no.hvl.dat250.nosql.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "options")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class VoteOption {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String caption;

    @JsonIgnore
    private Integer presentationOder;

    @OneToMany(mappedBy = "votedOn")
    private Set<Vote> votes;

    public VoteOption(String caption, Integer presentationOder) {
        this.caption = caption;
        this.presentationOder = presentationOder;
        this.votes = new LinkedHashSet<>();
    }

    public VoteOption() {
    }


    public String getCaption() {
        return caption;
    }

    public Integer getPresentationOder() {
        return presentationOder;
    }


    public Integer getVoteCount() {
        if (this.votes != null) {
            return this.votes.size();
        }
        return null;
    }

    @JsonIgnore
    public Set<Vote> getVotes() {
        return votes;
    }
}
