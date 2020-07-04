package control;

import model.product.*;
import model.product.Product.Type;
import utils.Base;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import static model.product.Product.Type.*;

public class ProductControl {
    private Collection<Product> products;

    public void addNewProduct(String name, String author, Location location, Type type, Map<String, Object> attributes) {
        if (type == COMICS) {
            String serial = UUID.randomUUID().toString();
            Base.addNotNull(this.products, new Comics(serial, name, author, location, attributes));


        }
        if (type == MOVIE) {
            String serial = UUID.randomUUID().toString();
            Base.addNotNull(this.products, new Movie(serial, name, author, location, attributes));


        }
        if (type == TEXT_BOOK) {
            String serial = UUID.randomUUID().toString();
            Base.addNotNull(this.products, new TextBook(serial, name, author, location, attributes));


        }

    }
}
