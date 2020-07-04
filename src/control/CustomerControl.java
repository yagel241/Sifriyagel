package control;

import model.person.Address;
import model.person.Customer;
import java.util.Collection;
import java.util.UUID;
import static utils.Base.addNotNull;


public class CustomerControl {

    Collection<Customer> customers;

    public void add(String id, String name, String email, String phoneNumber, Address address) {
        String customerId = UUID.randomUUID().toString();
        addNotNull(this.customers, new Customer(id, name, email, phoneNumber, address, customerId));
    }

    public void delete(String id, String customerId) {
        this.customers.remove(find(id, customerId));
    }

    private Customer find(String id, String customerId) {
        return this.customers.stream().filter(customer -> customer.getId().equals(customerId)).findFirst().orElse(null);
    }

    public void update(String id, String customerId, String name, String email, String phoneNumber, Address address) {
        Customer customer = find(id, customerId);
        if (customer != null) {
            if (name != null) {
                customer.setName(name);
            }
            if (email != null) {
                customer.setEmail(email);
            }
            if (phoneNumber != null) {
                customer.setPhoneNumber(phoneNumber);
            }
            if (address != null) {
                customer.setAddress(address);
            }
        }
    }

}
