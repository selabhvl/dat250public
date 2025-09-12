package no.hvl.dat250.jpa.tutorial.lectureBasic;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
@Entity(name = "Address_Basic")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String street; 
	private int number;
	//  D6
//	@ManyToOne
//	private Person resident;
	// D7 	
//	@ManyToMany(mappedBy = "addresses")
//	private List<Person> residents;
	
	public Address() {}
	public Address(String street, int number) {
		this.street = street;
		this.number = number;
	}
// D6 	
//	public void setResident(Person p) {
//		this.resident = p;
//	}
// D7 
//	public List<Person> getResidents(){
//		return this.residents;
//	}
	public String toString() {
		return "[id = " + this.id + ": " + this.street + " " + this.number + "]";
	}
}
