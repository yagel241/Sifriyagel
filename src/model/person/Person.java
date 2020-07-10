package model.person;

import java.util.Objects;

import static control.LibraryControl.Role;

abstract public class Person {

    protected String name;
    protected String email;
    protected Address address;
    protected String phoneNumber;
    protected String id;

    public Person() {
        super();
    }

    public Person(String id, String name, String email, String phoneNumber, Address address) {
        this.address=address;
        this.email=email;
        this.id=id;
        this.name=name;
        this.phoneNumber=phoneNumber;

    }

    @Override
    public String toString() {
        return String.format("id=%s, name=%s, email=%s, phoneNumber=%s, address=%s",
                this.id, this.name, this.email, this.phoneNumber, this.address.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(email, person.email) &&
                Objects.equals(address, person.address) &&
                Objects.equals(phoneNumber, person.phoneNumber) &&
                Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, address, phoneNumber, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract Role getRole();

    public abstract String getRoleAsString();

}
