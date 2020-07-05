package model.product;

abstract public class Product {

    public enum ProductType {COMICS, TEXT_BOOK, MOVIE};

    protected String name;
    protected String author;
    protected Location location;
    protected String serial;

    public Product(String serial, String name, String author, Location location) {
        this.serial = serial;
        this.name = name;
        this.author = author;
        this.location = location;
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

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    abstract public ProductType getType();


}
