package model.product;

import java.util.Map;

public class TextBook extends Product {
    private String field;
    private Integer grade;

    public TextBook(String serial, String name, String author, Location location, Map<String, Object> attributes) {
        super(serial, name, author, location);
        try {
            this.field = attributes.get("field").toString();
            this.grade = Integer.parseInt(attributes.get("grade").toString());
        } catch (Exception e) {
            throw new RuntimeException("Could not create textbook instance. invalid input.");
        }
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
}
