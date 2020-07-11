package model.product;

import control.LibraryControl.ProductType;
import javafx.scene.control.Alert;

import java.util.Map;

import static control.LibraryControl.ProductType.TEXT_BOOK;
import static utils.Base.showAlert;

public class TextBook extends Product {
    private String field;
    private Integer grade;

    public TextBook(String serial, String name, String author, Integer quantity, Location location, Map<String, Object> attributes) {
        super(serial, name, author, quantity, location);
        try {
            this.field = attributes.get("field").toString();
            this.grade = Integer.parseInt(attributes.get("grade").toString());
        } catch (Exception e) {
            this.deleteMe = true;
            showAlert(Alert.AlertType.ERROR, "Could not add a new text book.\nInvalid input.");
        }
    }

    public TextBook(String serial, String name, String author, Integer quantity, Location location, String field, Integer grade) {
        super(serial, name, author, quantity, location);
        this.field = field;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "TextBook{" +
                "field='" + field + '\'' +
                ", grade=" + grade +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", location=" + location +
                ", serial='" + serial + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public ProductType getType() {
        return TEXT_BOOK;
    }

    @Override
    public String getTypeAsString() {
        return TEXT_BOOK.toString();
    }
}
