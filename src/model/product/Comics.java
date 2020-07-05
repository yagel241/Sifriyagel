package model.product;

import java.util.Map;

public class Comics extends Product {

    private String company;
    private Integer edition;

    public Comics(String serial, String name, String author, Location location, Map<String, Object> attributes) {
        super(serial, name, author, location);
        try {
            this.company = attributes.get("company").toString();
            this.edition = Integer.parseInt(attributes.get("edition").toString());
        } catch (Exception e) {
            throw new RuntimeException("Could not create comics instance. invalid input.");
        }
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
