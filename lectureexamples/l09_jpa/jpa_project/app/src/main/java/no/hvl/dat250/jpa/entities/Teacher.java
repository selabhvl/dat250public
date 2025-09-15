package no.hvl.dat250.jpa.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ssn;

    private String name;

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", ssn='" + ssn + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
