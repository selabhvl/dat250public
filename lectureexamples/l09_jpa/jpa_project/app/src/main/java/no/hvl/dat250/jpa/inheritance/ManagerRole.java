package no.hvl.dat250.jpa.inheritance;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
public class ManagerRole extends Role {

    private String managesDepartment;

    public ManagerRole(String positionText, String managesDepartment) {
        super(positionText);
        this.managesDepartment = managesDepartment;
    }

    public ManagerRole() {
    }
}
