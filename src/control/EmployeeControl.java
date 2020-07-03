package control;

import model.person.*;
import model.product.TextBook;

import java.util.Collection;
import java.util.UUID;

import static utils.Base.addNotNull;

public class EmployeeControl {

    private Collection<Employee> employees;

    public void add(String id, String name, String email, String phoneNumber, Address address, Boolean employeeRole) {
        if(this.employees.stream().noneMatch(employee -> employee.getId().equals(id))) {
            String employeeId = UUID.randomUUID().toString();
            if (employeeRole) {
                addNotNull(this.employees, new Manager(id, name, email, phoneNumber, address, employeeId));
            }
            else {
                addNotNull(this.employees, new Librarian(id, name, email, phoneNumber, address, employeeId));
            }
        }
    }

    public void update(String personId, String employeeId, Boolean changeRole, String name, String email, String phoneNumber, Address address) {
        Employee employee = find(personId, employeeId);
        if (employee != null) {
            if (changeRole) {
                changeRole(employee);
                update(personId, employeeId, false, name, email, phoneNumber, address);
                return;
            }
            if (name != null) {
                employee.setName(name);
            }
            if (email != null) {
                employee.setEmail(email);
            }
            if (phoneNumber != null) {
                employee.setPhoneNumber(phoneNumber);
            }
            if (address != null) {
                employee.setAddress(address);
            }
        }
    }

    private void changeRole(Employee employee) {
        Employee toBeUpdated;
        if (employee instanceof Manager) {
            toBeUpdated = new Librarian(employee.getId(), employee.getName(), employee.getEmail(), employee.getPhoneNumber(), employee.getAddress(), employee.getEmployeeId());
        } else if (employee instanceof Librarian) {
            toBeUpdated = new Manager(employee.getId(), employee.getName(), employee.getEmail(), employee.getPhoneNumber(), employee.getAddress(), employee.getEmployeeId());
        } else {
            throw new RuntimeException(String.format("%s is not a valid employee. hence it will not be updated", employee.toString()));
        }
        this.delete(employee.getId(), employee.getEmployeeId());
        this.employees.add(toBeUpdated);
    }

    public void delete(String id, String employeeId) {
        this.employees.remove(find(id,employeeId));
    }

    public boolean isManager(String id, String employeeId) {

        Employee employee= find(id,employeeId);

        return employee instanceof Manager;
    }

    private Employee find(String id,String employeeId) {
        return this.employees.stream()
                .filter(employee -> employee.getId().equals(id) &&
                        employee.getEmployeeId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }

    public void addNewBook(TextBook book) {

    }
    public <List>TextBook lendingBooks() {

        return null;
    }
    public <List> TextBook booksInLibrary(){

        return null;
    }
    public Customer searchForCustomer(String ID){

        return null;
    }
    public void addNewLibrarian(Customer customer){

    }
    public void addNewLibraryManager(Manager manager){

    }
}


