package model.person;


public abstract class Employee extends Person {

    private String employeeId;

    public Employee(String id, String name, String email, String phoneNumber, Address address, String employeeId) {

        super(id, name, email, phoneNumber, address);
        this.employeeId=employeeId;

    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

}
