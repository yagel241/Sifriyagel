package model.person;

public class Address {
    private String city;
    private String street;
    private int apartmentNumber;
    private int buildingNumber;
    private int zipCode;

    public Address(){}
    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }
}
