package no.hvl.dat250.jpa.tutorial.lectureQueries;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity(name = "Address_Qu")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	@Version
	protected Integer version;
	private String street; 
	private int number;
	private int landPrice; // Stores the price of one square meter in NOK at this address
	public Address() {}
	public Address(String street, int number, int landPrice) {
		this.street = street;
		this.number = number;
		this.landPrice = landPrice;
	}
	public Long getId() {
		return this.id;
	}
	public String getStreet() {
		return this.street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getNumber() {
		return this.number;
	}
	public void setNumber(int number) {
		this.number = number; 
	}
	public int getLandPrice() {
		return this.landPrice;
	}
	public void setLandPrice(int landPrice) {
		this.landPrice = landPrice;
	}
	public String toString() {
		return "[id = " + this.id + ": " + this.street + " " + this.number + ", Price: " + this.landPrice + " NOK]";
	}
}
