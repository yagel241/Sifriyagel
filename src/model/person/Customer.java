package model.person;


import control.LibraryControl;
import model.product.Comics;
import model.product.Movie;
import model.product.Product;
import model.product.TextBook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import static control.LibraryControl.Role.CUSTOMER;

public class Customer extends Person {

    private Collection<Product> products;
    private String customerId;

    public Customer() {
        super();
    }

    public Customer(String id, String name, String email, String phoneNumber, Address address, String customerId) {
        super(id, name, email, phoneNumber, address);
        this.customerId=customerId;
        this.products = new ArrayList<>();
    }

    public boolean add(Product p) {
        Product product = this.find(p.getSerial());
        if (product == null) {
            return this.addNewProduct(p);
        }
        product.setQuantity(product.getQuantity() + 1);
        return true;
    }

    private boolean addNewProduct(Product product) {
        if (product instanceof Movie) {
            Movie movie = (Movie) product;
            this.products.add(new Movie(product.getSerial(), product.getName(), product.getAuthor(),
                    1, product.getLocation(), movie.getGenre(), movie.getYear()));
            return true;
        }
        if (product instanceof TextBook) {
            TextBook textBook = (TextBook) product;
            this.products.add(new TextBook(product.getSerial(), product.getName(), product.getAuthor(),
                    1, product.getLocation(), textBook.getField(), textBook.getGrade()));
            return true;
        }
        if (product instanceof Comics) {
            Comics comics = (Comics) product;
            this.products.add(new Comics(product.getSerial(), product.getName(), product.getAuthor(),
                    1, product.getLocation(), comics.getCompany(), comics.getEdition()));
            return true;
        }
        return false;
    }

    public boolean remove(Product p) {
        Product product = this.find(p.getSerial());
        if (product == null) {
            return false;
        }
        Integer quantity = product.getQuantity();
        if (quantity == 1) {
            this.products.remove(product);
        } else if (quantity > 1) {
            product.setQuantity(product.getQuantity() - 1);
        }
        return true;
    }

    public Product find(String serial) {
        return this.products.stream()
                .filter(product -> product.getSerial().equals(serial))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                "products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(products, customer.products) &&
                Objects.equals(customerId, customer.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), products, customerId);
    }

    @Override
    public LibraryControl.Role getRole() {
        return CUSTOMER;
    }

    @Override
    public String getRoleAsString() {
        return CUSTOMER.toString();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

}
