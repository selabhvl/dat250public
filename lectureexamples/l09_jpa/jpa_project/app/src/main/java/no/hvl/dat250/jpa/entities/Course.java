package no.hvl.dat250.jpa.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String code;

    @Embedded
    private SemesterInfo semesterInfo;

    @ManyToOne
    @JoinColumn(name = "teacher")
    private Teacher teacher;


    @ManyToMany
    @JoinTable(name = "enrollment",
            joinColumns=@JoinColumn(name="course_id"),
            inverseJoinColumns=@JoinColumn(name="student_id")
    )
    private Set<Student> takes;

    protected Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", semesterInfo=" + semesterInfo +
                ", teacher=" + teacher +
                ", noOfStudents=" + takes.size() +
                '}';
    }
}
