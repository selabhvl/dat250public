package no.hvl.dat250.jpa.entities;


import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class SemesterInfo {

    private BigDecimal year;

    private Character semester;

    public SemesterInfo(BigDecimal year, Character semester) {
        this.year = year;
        this.semester = semester;
    }

    public SemesterInfo() {
    }

    public BigDecimal getYear() {
        return year;
    }

    public void setYear(BigDecimal year) {
        this.year = year;
    }

    public Character getSemester() {
        return semester;
    }

    public void setSemester(Character semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SemesterInfo that = (SemesterInfo) o;
        return Objects.equals(year, that.year) && Objects.equals(semester, that.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, semester);
    }

    @Override
    public String toString() {
        return "SemesterInfo{" +
                "year=" + year +
                ", semester=" + semester +
                '}';
    }
}
