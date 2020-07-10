package control;

import javafx.scene.control.Alert;
import model.person.Customer;
import model.person.Person;
import model.product.Location;
import model.product.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static utils.Base.areProductParametersValid;
import static utils.Base.showAlert;

public class LibraryControl {

    public enum Role {MANAGER, LIBRARIAN, CUSTOMER}

    public enum ProductType {COMICS, TEXT_BOOK, MOVIE}

    private EmployeeControl employeeControl;
    private CustomerControl customerControl;
    private ProductControl productControl;

    public LibraryControl() {
        this.employeeControl = new EmployeeControl();
        this.customerControl = new CustomerControl();
        this.productControl = new ProductControl();
    }

    public void addNewCustomer(String mId, String managerId, String cId, String name, String email, String phoneNumber) {
        if (this.employeeControl.isManager(mId, managerId)) {
            this.customerControl.add(cId, name, email, phoneNumber);
        }
    }

    public void deleteCustomer(String id, String managerId, String id2, String customerId) {
        if (this.employeeControl.isManager(id, managerId)) {
            this.customerControl.delete(id2, customerId);
        }
    }

    public void updateCustomer(String id, String managerId, String id2, String customerId, String name, String email, String phoneNumber) {
        if (this.employeeControl.isManager(id, managerId)) {
            this.customerControl.update(id2, customerId, name, email, phoneNumber);
        }
    }

    public void addNewEmployee(String mId, String managerId, String eId, String name, String email, String phoneNumber, Boolean employeeRole) {
        if (this.employeeControl.isManager(mId, managerId)) {
            this.employeeControl.add(eId, name, email, phoneNumber, employeeRole);
        }
    }

    public void deleteEmployee(String mId, String managerId, String eId, String employeeId) {
        if (this.employeeControl.isManager(mId, managerId)) {
            this.employeeControl.delete(eId, employeeId);
        }
    }

    public void updateEmployee(String id, String managerId, String id2, String customerId, Boolean changRole,
                               String name, String email, String phoneNumber) {
        if (this.employeeControl.isManager(id, managerId)) {
            this.employeeControl.update(id2, customerId, changRole, name, email, phoneNumber);
        }
    }

    public boolean borrowProduct(String lId, String librarianId, String cId, String customerId, String serial) {
        if (this.employeeControl.isLibrarian(lId, librarianId)) {
            Customer customer = this.customerControl.find(cId, customerId);
            if (customer != null) {
                Product product = this.productControl.find(serial);
                if (product != null && this.productControl.delete(serial)) {
                    return customer.add(product);
                }
            }
        }
        return false;
    }

    public List<Customer> whoBorrowed(String serial) {
        if (this.productControl.find(serial) != null) {
            return this.customerControl.getCustomers().stream()
                    .filter(customer -> customer.find(serial) != null)
                    .collect(Collectors.toList());
        }
        return null;
    }

    public boolean retrieveProduct(String lId, String librarianId, String cId, String customerId, String serial) {
        if (this.employeeControl.isLibrarian(lId, librarianId)) {
            Customer c = this.customerControl.find(cId, customerId);
            if (c != null) {
                Product p = c.find(serial);
                if (p != null && this.productControl.returnLoanProduct(p)) {
                    return c.remove(p);
                }
            }
        }
        return false;
    }

    public Customer findCustomerById(String lId, String librarianId, String cId) {
        if (this.employeeControl.isLibrarian(lId, librarianId)) {
            return this.customerControl.findCustomerById(cId);
        }
        return null;
    }

    public List<? extends Person> searchByIdNamePhoneEmailAndRole(String id, String name, String phone, String email,
                                                                  Role role) {
        if (role == Role.MANAGER) {
            return this.employeeControl.findManagerBy_Id_Name_Phone_Email(id, name, phone, email);
        }
        if (role == Role.LIBRARIAN) {
            return this.employeeControl.findLibrarianBy_Id_Name_Phone_Email(id, name, phone, email);
        }
        if (role == Role.CUSTOMER) {
            return this.customerControl.findCustomerBy_Id_Name_Phone_Email(id, name, phone, email);
        }
        List<Person> found = new ArrayList<>();
        found.addAll(this.employeeControl.findLibrarianBy_Id_Name_Phone_Email(id, name, phone, email));
        found.addAll(this.employeeControl.findManagerBy_Id_Name_Phone_Email(id, name, phone, email));
        found.addAll(this.customerControl.findCustomerBy_Id_Name_Phone_Email(id, name, phone, email));
        return found;
    }

    public List<? extends Product> searchByName_Author_Type(String name, String author, ProductType type) {
        if (type == ProductType.COMICS) {
            return this.productControl.findComicsBy_Name_Author(name, author);
        }
        if (type == ProductType.TEXT_BOOK) {
            return this.productControl.findTextBookBy_Name_Author(name, author);
        }
        if (type == ProductType.MOVIE) {
            return this.productControl.findMovieBy_Name_Author(name, author);
        }
        List<Product> found = new ArrayList<>();
        found.addAll(this.productControl.findComicsBy_Name_Author(name, author));
        found.addAll(this.productControl.findTextBookBy_Name_Author(name, author));
        found.addAll(this.productControl.findMovieBy_Name_Author(name, author));
        return found;
    }

    public boolean addNewPerson(String mId, String managerId, String id, String name, String phone, String email,
                                Role role) {
        if (role == Role.MANAGER) {
            this.addNewEmployee(mId, managerId, id, name, email, phone, true);
            return true;
        }
        if (role == Role.LIBRARIAN) {
            this.addNewEmployee(mId, managerId, id, name, email, phone, false);
            return true;
        }
        if (role == Role.CUSTOMER) {
            this.addNewCustomer(mId, managerId, id, name, email, phone);
            return true;
        }
        return false;
    }

    public void addNewProduct(String lId, String librarianId, String name, String author, String aisle, String shelf,
                              String quantity, ProductType type, String attr1, String attr2) {
        if (this.employeeControl.isLibrarian(lId, librarianId) &&
                areProductParametersValid(author, aisle, shelf, quantity) &&
                this.productControl.getProducts().stream().noneMatch(product -> product.getName().equals(name))) {
            String[] attributes = new String[] {attr1, attr2};
            if (Arrays.stream(attributes).anyMatch(Objects::isNull)) {
                return;
            }
            this.productControl.add(name, author, Integer.parseInt(quantity),
                    new Location(Integer.parseInt(aisle),
                            Integer.parseInt(shelf)), type, attributes);
        } else {
            showAlert(Alert.AlertType.ERROR, "Could not add a new product.\n Invalid input!");
        }
    }

    public void deletePerson(String mId, String managerId, String pId, String personId, Role role) {
        if (role == Role.MANAGER || role == Role.LIBRARIAN) {
            this.deleteEmployee(mId, managerId, pId, personId);
        } else if (role == Role.CUSTOMER) {
            this.deleteCustomer(mId, managerId, pId, personId);
        }
    }

    public void deleteProduct(String lId, String librarianId, String serial) {
        if (this.employeeControl.isLibrarian(lId, librarianId) &&
                (this.whoBorrowed(serial) == null || this.whoBorrowed(serial).size() == 0)) {
            this.productControl.getProducts().remove(this.productControl.find(serial));
        }
    }

    public EmployeeControl getEmployeeControl() {
        return employeeControl;
    }

    public void setEmployeeControl(EmployeeControl employeeControl) {
        this.employeeControl = employeeControl;
    }

    public CustomerControl getCustomerControl() {
        return customerControl;
    }

    public void setCustomerControl(CustomerControl customerControl) {
        this.customerControl = customerControl;
    }

    public ProductControl getProductControl() {
        return productControl;
    }

    public void setProductControl(ProductControl productControl) {
        this.productControl = productControl;
    }

    public int numPeople() {
        return this.employeeControl.size() + this.customerControl.size();
    }

    public int numProducts() {
        return this.employeeControl.size() + this.customerControl.size();
    }

}
