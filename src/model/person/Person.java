package model.person;

import java.util.Objects;

import static control.LibraryControl.Role;

abstract public class Person {

    protected String name;
    protected String email;
    protected String phoneNumber;
    protected String id;

    public Person() {
        super();
    }

    public Person(String id, String name, String email, String phoneNumber) {
        this.email=email;
        this.id=id;
        this.name=name;
        this.phoneNumber=phoneNumber;

    }

    @Override
    public String toString() {
        return String.format("id=%s, name=%s, email=%s, phoneNumber=%s",
                this.id, this.name, this.email, this.phoneNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(email, person.email) &&
                Objects.equals(phoneNumber, person.phoneNumber) &&
                Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phoneNumber, id);
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
