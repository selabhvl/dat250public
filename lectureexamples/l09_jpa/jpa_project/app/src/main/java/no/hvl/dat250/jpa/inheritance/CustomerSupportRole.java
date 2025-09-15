package no.hvl.dat250.jpa.inheritance;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
public class CustomerSupportRole extends Role {

    public CustomerSupportRole(String positionText) {
        super(positionText);
    }

    public CustomerSupportRole() {
    }
}
