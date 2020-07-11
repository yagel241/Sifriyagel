package model.product;

import control.LibraryControl.ProductType;
import javafx.scene.control.Alert;

import java.util.Map;

import static control.LibraryControl.ProductType.MOVIE;
import static utils.Base.showAlert;

public class Movie extends Product{

    private String genre;
    private Integer year;

    public Movie(String serial, String name, String author, Integer quantity, Location location, Map<String, Object> attributes) {
        super(serial, name, author, quantity, location);
        try {
            this.genre = attributes.get("genre").toString();
            this.year = Integer.parseInt(attributes.get("year").toString());
        } catch (Exception e) {
            this.deleteMe = true;
            showAlert(Alert.AlertType.ERROR, "Could not add a new movie.\nInvalid input.");
        }
    }

    public Movie(String serial, String name, String author, Integer quantity, Location location, String genre, Integer year) {
        super(serial, name, author, quantity, location);
        this.genre = genre;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "genre='" + genre + '\'' +
                ", year=" + year +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", location=" + location +
                ", serial='" + serial + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public ProductType getType() {
        return ProductType.MOVIE;
    }

    @Override
    public String getTypeAsString() {
        return MOVIE.toString();
    }
}
