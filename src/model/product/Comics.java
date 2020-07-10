package model.product;

import control.LibraryControl;
import control.LibraryControl.ProductType;
import javafx.scene.control.Alert;

import java.util.Map;

import static utils.Base.showAlert;

public class Comics extends Product {

    private String company;
    private Integer edition;

    public Comics(String serial, String name, String author, Integer quantity, Location location, Map<String, Object> attributes) {
        super(serial, name, author, quantity, location);
        try {
            this.company = attributes.get("company").toString();
            this.edition = Integer.parseInt(attributes.get("edition").toString());
        } catch (Exception e) {
            this.deleteMe = true;
            showAlert(Alert.AlertType.ERROR, "Could not add a new comics.\nInvalid input.");
        }
    }

    public Comics(String serial, String name, String author, Integer quantity, Location location, String company, Integer edition) {
        super(serial, name, author, quantity, location);
        this.company = company;
        this.edition = edition;
    }

    @Override
    public String toString() {
        return "Comics{" +
                "company='" + company + '\'' +
                ", edition=" + edition +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", location=" + location +
                ", serial='" + serial + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    @Override
    public ProductType getType() {
        return ProductType.COMICS;
    }
}
