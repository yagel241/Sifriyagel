package control;

import model.product.*;
import model.product.Product.Type;
import utils.Base;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

import static model.product.Product.Type.*;

public class ProductControl {
    private Collection<Product> products;

    public void addNewProduct(String name, String author, Location location, Type type, Map<String, Object> attributes) {
        String serial = UUID.randomUUID().toString();
        if (type == COMICS) {
            Base.addNotNull(this.products, new Comics(serial, name, author, location, attributes));
        } else if (type == MOVIE) {
            Base.addNotNull(this.products, new Movie(serial, name, author, location, attributes));
        } else if (type == TEXT_BOOK) {
            Base.addNotNull(this.products, new TextBook(serial, name, author, location, attributes));
        }
    }
    public void deleteProduct(String name, Type type){




    }

    public Collection<Product> findByName(String name,Type type) {
        return this.products.stream()
                .filter(product -> product.getName().equals(name) &&
                        product.getType().equals(type)).collect(Collectors.toList());
    }

}
