package model.person;


public class Employee extends Person {

    String employeeId;

    public Employee(String id, String name, String email, String phoneNumber, Address address, String employeeId) {

        super(id, name, email, phoneNumber, address);
        this.employeeId=employeeId;

    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
