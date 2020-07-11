package model.product;

import control.LibraryControl.ProductType;

abstract public class Product {

    protected String name;
    protected String author;
    protected Location location;
    protected String serial;
    protected Integer quantity;

    protected boolean deleteMe;

    public Product(String serial, String name, String author, Integer quantity, Location location) {
        this.serial = serial;
        this.name = name;
        this.author = author;
        this.location = location;
        this.quantity = quantity;
        this.deleteMe = false;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", location=" + location +
                ", serial='" + serial + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Location getLocation() {
        return location;
    }

    public String getLocationAsString() {
        return "A" + this.location.getAisle() + "-S" + this.location.getShelf();
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getAisle() {
        return this.location.getAisle() + "";
    }

    public String getShelf() {
        return this.location.getAisle() + "";
    }

    public String getQuantityAsString() {
        return this.quantity + "";
    }

    public boolean isDeleteMe() {
        return deleteMe;
    }

    public abstract ProductType getType();

    public abstract String getTypeAsString();

}
