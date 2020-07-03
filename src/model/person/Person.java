package model.person;

abstract public class Person {

    protected String name;
    protected String email;
    protected Address address;
    protected String phoneNumber;
    protected String id;

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
}
