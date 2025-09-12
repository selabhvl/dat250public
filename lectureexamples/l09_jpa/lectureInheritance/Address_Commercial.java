package no.hvl.dat250.jpa.tutorial.lectureInheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
@Entity
public class Address_Commercial extends Address {
	private String postOfficeBox; 
	public Address_Commercial() {}
	
	public Address_Commercial(String street, int number, String postOfficeBox) {
		super(street, number);
		this.postOfficeBox = postOfficeBox;
	}
	public String toString() {
		return super.toString() + " Address_Commercial [postOfficeBox=" + this.postOfficeBox + "]";
	}
}
