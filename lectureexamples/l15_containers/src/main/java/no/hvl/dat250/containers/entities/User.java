package no.hvl.dat250.containers.entities;

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

    @Basic(optional = false)
    @Column(unique = true)
    private String username;

    @Basic(optional = false)
    @Column(unique = true)
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "createdBy")
    @JsonManagedReference(value = "created")
    private Set<Poll> created;

    @OneToMany(fetch = FetchType.EAGER)
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
        return v;
    }

    public Poll createPoll(String question) {
        Poll p = new Poll(question, this);
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


}
