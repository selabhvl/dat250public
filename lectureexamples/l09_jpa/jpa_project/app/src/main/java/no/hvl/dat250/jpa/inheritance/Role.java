package no.hvl.dat250.jpa.inheritance;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "role_seq")
public abstract class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String positionText;

    public Role(String positionText) {
        this.positionText = positionText;
    }

    protected Role() {
    }
}
