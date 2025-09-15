package no.hvl.dat250.jpa.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    public User(String username) {
        this.username = username;
    }


    protected User() {
        // needed by the framework
    }

}
