package no.hvl.dat250.jpa.tutorial.lectureBasic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity(name = "Person_Basic")
public class Person {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// D1, D3ff
	private String firstName; 
	// D2
//@ElementCollection(fetch = FetchType.LAZY)
//@CollectionTable(name = "Firstnames")
//@Column(name = "contents")
//	private List<String> firstNames; // Change parameter in constructor to String... firstnames, convert array to list with Arrays.asList()  
@Column(name = "surName")
	private String lastName;
	// D3
	private Role role;
	// D4
//@ManyToOne(cascade = CascadeType.PERSIST)
//	private Address address;
	// D5
//@ManyToMany(cascade = CascadeType.PERSIST)
// 	private List<Address> addresses;
	// D6
//@OneToMany(mappedBy = "resident")
//	private List<Address> addresses;
	// D7
//@ManyToMany(cascade = CascadeType.PERSIST)
//	private List<Address> addresses;

	public Person() {}
	public Person(String lastName, String firstName) { //, Address a) { // String... firstNames  // Address a
		this.firstName = firstName;
		this.lastName = lastName;
// 		this.address = a;  // D4 
//		this.addresses = new ArrayList<>(); // D5 -> 
		this.role = Role.STUDENT;
	}
// D5 -> 
//	public void addAddress(Address a) {
//		this.addresses.add(a);
//	}
// D6 ->  
//	public List<Address> getAdresses(){
//		return this.addresses;
//	}
	public String toString() {
// D1 
		return "Person [id = " + this.id + ", name = " + this.firstName + " " + this.lastName + "]";
// D2
// 		return "Person [id = " + this.id + ", name = " + this.firstNames + " " + this.lastName + "]";  
// D3		
//		return "Person [id = " + this.id + ", name = " + this.firstName + " " + this.lastName + ", " + this.role + "]";
// D4
// 		return "Person [id = " + this.id + ", name = " + this.firstName + " " + this.lastName  + ", " + (address==null ? "null" : address.toString()) + ")]";
// D5 ->  
//		String addressesAsString = "";
//		if(this.addresses==null) return "Person [id = " + this.id + ", name = " + this.firstName + " " + this.lastName + "(no Address given))]";
//		for (Address current : addresses) addressesAsString += (current + ", ");		
//		return "Person [id = " + this.id + ", name = " + this.firstName + " " + this.lastName + ", " + addressesAsString + ")]";
	}
}
