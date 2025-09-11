package no.hvl.dat250.l07.graphql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class User {

    private String username;

    private List<Poll> created;

    private Collection<Vote> allVotes;


    public User(String username) {
        this.username = username;
        this.created = new ArrayList<>();
        this.allVotes = new HashSet<>();
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Poll> getCreated() {
        return created;
    }

    public Collection<Vote> getAllVotes() {
        return allVotes;
    }
}
