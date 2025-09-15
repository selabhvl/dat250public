package no.hvl.dat250.jpa.inheritance;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
public class EngineerRole extends Role {

    public EngineerRole(String positionText) {
        super(positionText);
    }

    public EngineerRole() {
    }
}

