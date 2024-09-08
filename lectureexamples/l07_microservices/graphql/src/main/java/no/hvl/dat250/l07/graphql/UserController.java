package no.hvl.dat250.l07.graphql;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {


    private final Repository repository;

    public UserController(@Autowired Repository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public List<User> getUsers() {
        return new ArrayList<>(this.repository.getUsers().values());
    }

}
