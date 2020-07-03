package model;

import model.person.Customer;
import model.person.Employee;
import model.product.Product;

import java.util.Collection;

public class Library {

    Collection<Employee> employees;
    Collection<Product> products;
    Collection<Customer> customers;

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public Collection<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Collection<Customer> customers) {
        this.customers = customers;
    }
}
