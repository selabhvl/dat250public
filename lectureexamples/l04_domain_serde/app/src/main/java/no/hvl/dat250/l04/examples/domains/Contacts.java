package no.hvl.dat250.l04.examples.domains;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Contacts {

    public enum Gender {
        FEMALE,
        MALE,
        OTHER,
        NOT_SPECIFIED
    }

    public static abstract class Address {

        public abstract String render();

    }


    public static class VisitingAddress extends Address {
        private final String street;
        private final String houseNo;

        private final String city;

        private final String postalCode;

        private final String country;

        public VisitingAddress(String street, String houseNo, String city, String postalCode, String country) {
            this.street = street;
            this.houseNo = houseNo;
            this.city = city;
            this.postalCode = postalCode;
            this.country = country;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VisitingAddress that = (VisitingAddress) o;
            return Objects.equals(street, that.street) && Objects.equals(houseNo, that.houseNo) && Objects.equals(city, that.city) && Objects.equals(postalCode, that.postalCode) && Objects.equals(country, that.country);
        }

        @Override
        public int hashCode() {
            return Objects.hash(street, houseNo, city, postalCode, country);
        }

        @Override
        public String render() {
            return street + " " + houseNo + "\n" + postalCode + " " + city + "\n" + country;
        }
    }

    private static class PostBox extends Address {
        private final String box;

        private final String postalCode;

        private final String city;

        private final String country;

        public PostBox(String box, String postalCode, String city, String country) {
            this.box = box;
            this.postalCode = postalCode;
            this.city = city;
            this.country = country;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PostBox postBox = (PostBox) o;
            return Objects.equals(box, postBox.box) && Objects.equals(postalCode, postBox.postalCode) && Objects.equals(city, postBox.city) && Objects.equals(country, postBox.country);
        }

        @Override
        public int hashCode() {
            return Objects.hash(box, postalCode, city, country);
        }

        @Override
        public String render() {
            return "P.O. Box " + box + "\n" + postalCode + " " + city + "\n" + country;
        }
    }

    public static abstract class Contact {

        public abstract String render();

    }

    public static class Email extends Contact {
        private final String address;

        public Email(String address) {
            this.address = address;
        }

        public String getAddress() {
            return address;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Email email = (Email) o;
            return Objects.equals(address, email.address);
        }

        @Override
        public int hashCode() {
            return Objects.hash(address);
        }

        @Override
        public String render() {
            return address;
        }
    }

    public static class Phone extends Contact {
        private final int number;

        private final String countryCode;

        public Phone(int number, String countryCode) {
            this.number = number;
            this.countryCode = countryCode;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Phone phone = (Phone) o;
            return number == phone.number && Objects.equals(countryCode, phone.countryCode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(number, countryCode);
        }

        @Override
        public String render() {
            return countryCode + " " + number;
        }
    }

    public static class Person {

        private final String firstname;
        private String lastname;

        private final LocalDate birthdate;

        private Gender gender;

        private final List<Contact> contact;

        private final List<Address> addresses;

        public Person(String firstname, String lastname, LocalDate birthdate, Gender gender) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.birthdate = birthdate;
            this.gender = gender;
            this.contact = new ArrayList<>();
            this.addresses = new ArrayList<>();
        }

        public String getFirstname() {
            return firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public String getFullName() {
            return lastname + ", " + firstname;
        }

        public LocalDate getBirthdate() {
            return birthdate;
        }

        public long getAge() {
            return ChronoUnit.YEARS.between(Instant.now(), getBirthdate().atStartOfDay().atZone(ZoneId.systemDefault()));
        }

        public void changeLastName(String newLastname) {
            this.lastname = newLastname;
        }

        public void changeGender(Gender gender) {
            this.gender = gender;
        }

        public List<Address> getAddresses() {
            return addresses;
        }

        public List<Email> getEmailAddresses() {
            return this.contact.stream().filter(a -> a instanceof Email).map(a -> (Email)a).collect(Collectors.toList());
        }

        public List<Phone> getPhoneNumbers() {
            return this.contact.stream().filter(a -> a instanceof Phone).map(a -> (Phone)a).collect(Collectors.toList());
        }

        public void addAddress(Address address) {
            this.addresses.add(address);
        }

        public void removeAddress(Address address) {
            this.addresses.remove(address);
        }

        public void setPrimaryAddress(Address address) {
            this.addresses.remove(address);
            this.addresses.set(0, address);
        }
        public Address getPrimaryAddress() {
            return this.addresses.getFirst();
        }

        public void addEmail(String email) {
            this.contact.add(new Email(email));
        }

        public void removeEmail(String email) {
            this.contact.remove(new Email(email));
        }

        public void addPhone(String countryCode, int number) {
            this.contact.add(new Phone(number, countryCode));
        }



        public static class PersonBuilder {

            public PersonBuilder() {
            }



        }
     }




}
