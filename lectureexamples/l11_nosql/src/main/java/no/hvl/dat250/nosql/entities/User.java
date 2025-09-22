package no.hvl.dat250.nosql.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "created")
    private Set<Poll> created;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "voted")
    private Set<Vote> voted;


    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.created = new LinkedHashSet<>();
        this.voted = new LinkedHashSet<>();
    }

    public User() {
    }

    public Vote voteFor(VoteOption option) {
        Vote v = new Vote(option);
        this.voted.add(v);
        option.getVotes().add(v);
        return v;
    }

    public Poll createPoll(String question) {
        Poll p = new Poll(question);
        this.created.add(p);
        return p;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Set<Poll> getCreated() {
        return created;
    }

    public Set<Vote> getVoted() {
        return voted;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
