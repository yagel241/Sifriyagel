package control;

import model.person.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static utils.Base.*;


public class CustomerControl {

    private Collection<Customer> customers;

    public CustomerControl() {
        this.customers = new ArrayList<>();
    }

    public void add(String id, String name, String email, String phoneNumber) {
        if(areParametersValid(id, name, email, phoneNumber)) {
            String customerId = UUID.randomUUID().toString();
            addNotNull(this.customers, new Customer(id, name, email, phoneNumber, customerId));
        }
    }

    public void delete(String id, String customerId) {
        this.customers.remove(find(id, customerId));
    }

    public Customer find(String id, String customerId) {
        return this.customers.stream().filter(customer -> customer.getId().equals(id) &&
                customer.getCustomerId().equals(customerId)).findFirst().orElse(null);
    }

    public void update(String id, String customerId, String name, String email, String phoneNumber) {
        Customer customer = find(id, customerId);
        if (customer != null) {
            if (name != null && isName(name)) {
                customer.setName(name);
            }
            if (email != null && isEmail(email)) {
                customer.setEmail(email);
            }
            if (phoneNumber != null && isPhoneNumber(phoneNumber)) {
                customer.setPhoneNumber(phoneNumber);
            }
        }
    }

    public Customer findCustomerById(String cId) {
        return this.customers.stream().filter(customer -> customer.getId().equals(cId)).findFirst().orElse(null);
    }

    public List<Customer> findCustomerBy_Id_Name_Phone_Email(String id, String name, String phone, String email) {
        return this.customers.stream()
                .filter(customer -> doesCustomerHave(customer, id, name, phone, email))
                .collect(Collectors.toList());
    }

    private boolean doesCustomerHave(Customer customer, String id, String name, String phone, String email) {
        if (id != null && !(customer.getId().startsWith(id))) {
            return false;
        }
        if (name != null && !(customer.getName().contains(name))) {
            return false;
        }
        if (email != null && !(customer.getEmail().toLowerCase().startsWith(email.toLowerCase()))) {
            return false;
        }
        return phone == null || customer.getPhoneNumber().startsWith(phone);
    }

    public Collection<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Collection<Customer> customers) {
        this.customers = customers;
    }

    public int size() {
        return this.customers.size();
    }

}
