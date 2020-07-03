package model.person;


public class Customer extends Person {
    private String customerId;

    public Customer(String id, String name, String email, String phoneNumber, Address address, String customerId) {

        super(id, name, email, phoneNumber, address);
        this.customerId=customerId;

    }
    @Override
    public String toString() {
        return "Customer={customerId=" + customerId + ", " + super.toString() + "}";
    }

    public void borrowAProduct(){

    }
    public void returnAProduct(){

    }
    public void displayProducts(){

    }
}
