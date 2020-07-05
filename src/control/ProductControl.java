package control;

import model.person.Employee;
import model.product.*;
import model.product.Product.ProductType;
import utils.Base;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static model.product.Product.ProductType.*;

public class ProductControl {
    private Collection<Product> products;

    public void add(String name, String author, Location location, ProductType productType, Map<String, Object> attributes) {
        String serial = UUID.randomUUID().toString();
        if (productType == COMICS) {
            Base.addNotNull(this.products, new Comics(serial, name, author, location, attributes));
        } else if (productType == MOVIE) {
            Base.addNotNull(this.products, new Movie(serial, name, author, location, attributes));
        } else if (productType == TEXT_BOOK) {
            Base.addNotNull(this.products, new TextBook(serial, name, author, location, attributes));
        }
    }
    public void delete(String serial){
        this.products.remove(find(serial));
    }

    private Product find(String serial) {
        return this.products.stream()
                .filter(product -> product.getSerial().equals(serial)).findFirst().orElse(null);
    }




    public Collection<Product> findByNameAndType(String name, ProductType productType) {
        return this.products.stream()
                .filter(product -> product.getName().equals(name) &&
                        product.getType().equals(productType)).collect(Collectors.toList());
    }

}
