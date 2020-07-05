package model.product;

import java.util.Map;

public class Movie extends Product{

    private String genre;
    private Integer year;

    public Movie(String serial, String name, String author, Location location, Map<String, Object> attributes) {
        super(serial, name, author, location);
        try {
            this.genre = attributes.get("genre").toString();
            this.year = Integer.parseInt(attributes.get("year").toString());
        } catch (Exception e) {
            throw new RuntimeException("Could not create movie instance. invalid input.");
        }
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
}
