package control;

import model.product.Comics;
import model.product.Location;
import model.product.Product;
import model.product.Product.Type;
import utils.Base;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import static model.product.Product.Type.COMICS;

public class ProductControl {
    private Collection<Product> products;

    public void addNewProduct(String name, String author, Location location, Type type, Map<String, Object> attributes) {
        if (type == COMICS) {
            String serial = UUID.randomUUID().toString();
            Base.addNotNull(this.products, new Comics(serial, name, author, location, attributes));


        }

    }
}
