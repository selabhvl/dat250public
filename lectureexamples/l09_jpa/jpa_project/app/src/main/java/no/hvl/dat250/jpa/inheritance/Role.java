package no.hvl.dat250.jpa.inheritance;

import jakarta.persistence.*;

@Entity
public abstract class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String positionText;

    public Role(String positionText) {
        this.positionText = positionText;
    }

    protected Role() {
    }
}
