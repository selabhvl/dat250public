package no.hvl.dat250.jpa.inheritance;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NaturalId
    private String ssn;

    private String name;

    private BigDecimal salary;

    private String department;

    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Role> roles;

    protected Employee() {
    }

    public Employee(String ssn, String name, BigDecimal salary, String department) {
        this.ssn = ssn;
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.roles = LinkedHashSet.newLinkedHashSet(1);
    }


    public Employee addRole(Role role) {
        this.roles.add(role);
        return this;
    }
}
