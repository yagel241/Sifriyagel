package control;

import model.person.Address;

public class LibraryControl {
    EmployeeControl employeeControl;
    private CustomerControl customerControl;

    public void addNewCustomer(String id, String managerId, String name, String email, String phoneNumber, Address address) {
        if (this.employeeControl.isManager(id, managerId)) {
            this.customerControl.add(id, name, email, phoneNumber, address);
        }
    }

    public void deleteCustomer(String id, String managerId, String id2, String customerId) {
        if (this.employeeControl.isManager(id,managerId)) {
            this.customerControl.delete(id2, customerId);
        }
    }

    public void updateCustomer(String id, String managerId,String id2, String customerId, String name, String email, String phoneNumber, Address address) {
        if (this.employeeControl.isManager(id, managerId)) {
            this.customerControl.update(id2, customerId, name, email, phoneNumber, address);
        }
    }

    public void addNewEmployee(String id, String managerId, String name, String email, String phoneNumber, Address address, Boolean employeeRole) {
        if (this.employeeControl.isManager(id, managerId)) {
            this.employeeControl.add(id, name, email, phoneNumber, address,employeeRole);
        }
    }

    public void deleteEmployee(String id, String managerId, String id2, String customerId) {
        if (this.employeeControl.isManager(id,managerId)) {
            this.employeeControl.delete(id2, customerId);
        }
    }

    public void updateEmployee(String id, String managerId,String id2, String customerId,Boolean changRole, String name, String email, String phoneNumber, Address address) {
        if (this.employeeControl.isManager(id, managerId)) {
            this.employeeControl.update(id2, customerId, changRole, name, email, phoneNumber, address);
        }
    }



}
