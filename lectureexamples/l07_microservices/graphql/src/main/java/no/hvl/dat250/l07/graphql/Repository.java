package no.hvl.dat250.l07.graphql;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository {

    private final Map<String, User> users;

    public UserRepository() {
        this.users = new HashMap<>();

        this.users.put("alice", new User("alice"));
        this.users.put("bob", new User("bob"));
    }

    public Map<String, User> getUsers() {
        return users;
    }
}
