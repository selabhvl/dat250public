package no.hvl.dat250.jpa.tutorial.lectureInheritance;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity(name = "Person_Inh")
public class Person {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	@ManyToMany
	private List<Address> addresses;
	public Person() {}
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.addresses = new ArrayList<>();
	}
	public void addAddress(Address address) {
		this.addresses.add(address);
	}
	public void removeAddress(Address address) {
		this.addresses.remove(address);
	}
	public String toString() {
		String addressesAsString = "";
		for (Address current : addresses) addressesAsString += (current + ", ");		
		return "Person [id = " + this.id + ", name = " + this.firstName + " " + this.lastName + ", " + addressesAsString + ")]";
	}
}
