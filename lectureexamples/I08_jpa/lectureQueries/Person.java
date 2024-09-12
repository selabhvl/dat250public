package no.hvl.dat250.jpa.tutorial.lectureQueries;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "Person_Qu")
@NamedQuery(name = "FindByLastNameTemplate", query = "SELECT p FROM Person_Qu p WHERE p.lastName like :searchString") 
public class Person {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
@JoinTable(name = "Person_Address_Qu")
@OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
	private List<Address> addresses;
	public Person() {}
	
	public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.addresses = new ArrayList<>();
	}
	public Long getId() {
		return this.id;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void addAddress(Address address) {
		this.addresses.add(address);
	}
	public List<Address> getAddresses() {
		return this.addresses;
	}
	public String toString() {
		String addressesAsString = "";
		for (Address current : addresses) addressesAsString += (current + "\n");		
		return "Person [id = " + this.id + ", name = " + this.firstName + " " + this.lastName + ", " + "\n       Addresses: " + addressesAsString + "]";
	}
}
