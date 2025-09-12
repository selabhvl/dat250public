package no.hvl.dat250.jpa.tutorial.lectureInheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
public class Address_Private extends Address {
	private int flatNr; 
	public Address_Private() {}
	
	public Address_Private(String street, int number, int flatNr) {
		super(street, number);
		this.flatNr = flatNr;
	}
	public String toString() {
		return super.toString() + " Address_Private [flatNr=" + this.flatNr + "]";
	}
}
