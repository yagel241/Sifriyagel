package control;

import control.LibraryControl.Role;
import model.person.Address;
import model.person.Employee;
import model.person.Librarian;
import model.person.Manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static control.LibraryControl.Role.MANAGER;
import static utils.Base.*;

public class EmployeeControl {

    private Collection<Employee> employees;

    public EmployeeControl() {
        this.employees = new ArrayList<>();
    }

    public EmployeeControl(Manager manager) {
        this();
        this.employees.add(manager);
    }

    public void add(String id, String name, String email, String phoneNumber, Address address, Boolean employeeRole) {
        if (areParametersValid(id, name, email, phoneNumber) &&
                this.employees.stream().noneMatch(employee -> employee.getId().equals(id))) {
            String employeeId = UUID.randomUUID().toString();
            if (employeeRole) {
                addNotNull(this.employees, new Manager(id, name, email, phoneNumber, address, employeeId));
            } else {
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
            if (name != null && isName(name)) {
                employee.setName(name);
            }
            if (email != null && isEmail(email)) {
                employee.setEmail(email);
            }
            if (phoneNumber != null && isPhoneNumber(phoneNumber)) {
                employee.setPhoneNumber(phoneNumber);
            }
            if (address != null) {
                employee.setAddress(address);
            }
        }
    }

    public void changeRole(Employee employee) {
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
        this.employees.remove(find(id, employeeId));
    }

    public boolean isManager(String id, String employeeId) {
        Employee employee = find(id, employeeId);
        return employee instanceof Manager;
    }

    public boolean isLibrarian(String id, String employeeId) {
        Employee employee = find(id, employeeId);
        return employee instanceof Librarian;
    }

    private Employee find(String id, String employeeId) {
        return this.employees.stream()
                .filter(employee -> employee.getId().equals(id) &&
                        employee.getEmployeeId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> findManagerBy_Id_Name_Phone_Email(String id, String name, String phone, String email) {
        return this.employees.stream()
                .filter(employee -> doesEmployeeHave(employee, id, name, phone, email, true))
                .collect(Collectors.toList());
    }

    public List<Employee> findLibrarianBy_Id_Name_Phone_Email(String id, String name, String phone, String email) {
        return this.employees.stream()
                .filter(employee -> doesEmployeeHave(employee, id, name, phone, email, false))
                .collect(Collectors.toList());
    }

    public Employee findEmployeeById_Email_Role(String id, String email, Role role) {
        if (id == null || email == null || role == null) {
            return null;
        }
        return this.employees.stream()
                .filter(employee -> doesEmployeeHaveExactly(employee, id, email, role == MANAGER))
                .findFirst().orElse(null);
    }

    private boolean doesEmployeeHaveExactly(Employee employee, String id, String email, boolean isManager) {
        if (!isEmployeeRoleValid(employee, isManager)) {
            return false;
        }
        if (id != null && !(employee.getId().equals(id))) {
            return false;
        }
        return email == null || employee.getEmail().equals(email);
    }

    private boolean isEmployeeRoleValid(Employee employee, boolean isManager) {
        if (isManager) {
            return employee instanceof Manager;
        }
        return employee instanceof Librarian;
    }

    private boolean doesEmployeeHave(Employee employee, String id, String name, String phone, String email, boolean isManager) {
        if (!isEmployeeRoleValid(employee, isManager)) {
            return false;
        }
        if (id != null && !(employee.getId().startsWith(id))) {
            return false;
        }
        if (name != null && !(employee.getName().toLowerCase().trim()
                .contains(name.toLowerCase().trim()))) {
            return false;
        }
        if (email != null && !(employee.getEmail().toLowerCase().startsWith(email.toLowerCase()))) {
            return false;
        }
        return phone == null || employee.getPhoneNumber().startsWith(phone);
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }

    public int size() {
        return this.employees.size();
    }
}


