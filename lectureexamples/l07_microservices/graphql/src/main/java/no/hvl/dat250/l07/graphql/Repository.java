package no.hvl.dat250.l07.graphql;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Repository {

    private final Map<String, User> users;
    private final Map<Integer, Poll> polls;

    public Repository() {
        this.users = new HashMap<>();
        this.polls = new HashMap<>();

        User u1 = new User("alice");
        this.users.put("alice", u1);
        User u2 = new User("bob");
        this.users.put("bob", u2);
        User u3 = new User("eve");
        this.users.put("eve", u3);

        Poll p1 = new Poll(1, "Annanas on Pizza?", u1);
        VoteOption o1 = new VoteOption("Oh yes!", p1);
        VoteOption o2 = new VoteOption("Mamma mia! Noooo!", p1);
        new Vote(u1, o2);
        new Vote(u2, o2);
        new Vote(u3, o1);
        Poll p2 = new Poll(2, "Vim or Emacs?", u2);
        VoteOption o3 = new VoteOption("VIM", p2);
        new Vote(u2, o3);
        new VoteOption("Emcas", p2);
        this.polls.put(1, p1);
        this.polls.put(2, p2);


    }

    public Map<Integer, Poll> getPolls() {
        return polls;
    }

    public Map<String, User> getUsers() {
        return users;
    }
}
