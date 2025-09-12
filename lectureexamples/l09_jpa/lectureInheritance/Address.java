package no.hvl.dat250.jpa.tutorial.lectureInheritance;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;

@Entity(name = "Address_Inh")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Address implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String street; 
	private int number;
	public Address() {}
	public Address(String street, int number) {
		this.street = street;
		this.number = number;
	}
	public String toString() {
		return "[id = " + this.id + ": " + this.street + "]";
	}
}
//  =========== Variations ============ 

// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn(name = "DiscType")
// @DiscriminatorValue("C")  -> In Address_Commercial
// @DiscriminatorValue("P")  -> In Address_Private

// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// --->>> @GeneratedValue(strategy = GenerationType.SEQUENCE) !!!