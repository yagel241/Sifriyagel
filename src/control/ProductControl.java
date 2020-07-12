package control;

import control.LibraryControl.ProductType;
import model.product.*;
import utils.Base;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static control.LibraryControl.ProductType.*;

public class ProductControl {

    private Collection<Product> products;

    public ProductControl() {
        this.products = new ArrayList<>();
    }

    public void add(String name, String author, Integer quantity, Location location,
                    ProductType productType, String[] attributes) {
        String serial = UUID.randomUUID().toString();
        List<String> fieldNames;
        Map<String, Object> uniqueAttr = new TreeMap<>();
        Product product = null;
        if (productType == COMICS) {
            fieldNames = getFieldNamesOf(Comics.class);
            IntStream.range(0, fieldNames.size())
                    .forEach(index -> uniqueAttr.put(fieldNames.get(index), attributes[index]));
            product = new Comics(serial, name, author, quantity, location, uniqueAttr);
        } else if (productType == MOVIE) {
            fieldNames = getFieldNamesOf(Movie.class);
            IntStream.range(0, fieldNames.size())
                    .forEach(index -> uniqueAttr.put(fieldNames.get(index), attributes[index]));
            product = new Movie(serial, name, author, quantity, location, uniqueAttr);
        } else if (productType == TEXT_BOOK) {
            fieldNames = getFieldNamesOf(TextBook.class);
            IntStream.range(0, fieldNames.size())
                    .forEach(index -> uniqueAttr.put(fieldNames.get(index), attributes[index]));
            product = new TextBook(serial, name, author, quantity, location, uniqueAttr);
        }
        if (product == null || product.isDeleteMe()) {
            return;
        }
        Base.addNotNull(this.products, product);
    }

    private List<String> getFieldNamesOf(Class<? extends Product> comicsClass) {
        return Arrays.stream(comicsClass.getDeclaredFields())
                .map(Field::getName)
                .sorted()
                .collect(Collectors.toList());
    }

    public boolean give(String serial) {
        Product product = find(serial);
        if (product == null) {
            return false;
        }
        Integer quantity = product.getQuantity();
        if (quantity == 1) {
            this.products.remove(product);
            return true;
        }
        product.setQuantity(quantity - 1);
        return true;
    }

    public boolean take(String serial) {
        Product product = find(serial);
        if (product == null) {
            return false;
        }
        product.setQuantity(product.getQuantity() + 1);
        return true;
    }

    public Product find(String serial) {
        return this.products.stream()
                .filter(product -> product.getSerial().equals(serial)).findFirst().orElse(null);
    }

    public Collection<Product> findByNameAndType(String name, ProductType productType) {
        return this.products.stream()
                .filter(product -> product.getName().equals(name) &&
                        product.getType().equals(productType)).collect(Collectors.toList());
    }

    public List<Product> findComicsBy_Name_Author(String name, String author) {
        return this.products.stream()
                .filter(product -> doesProductHave(product, name, author, COMICS))
                .collect(Collectors.toList());
    }

    public List<Product> findTextBookBy_Name_Author(String name, String author) {
        return this.products.stream()
                .filter(product -> doesProductHave(product, name, author, TEXT_BOOK))
                .collect(Collectors.toList());
    }

    public List<Product> findMovieBy_Name_Author(String name, String author) {
        return this.products.stream()
                .filter(product -> doesProductHave(product, name, author, MOVIE))
                .collect(Collectors.toList());
    }

    private boolean doesProductHave(Product product, String name, String author, ProductType type) {
        if (type == COMICS) {
            if (!(product instanceof Comics)) {
                return false;
            }
        }
        if (type == TEXT_BOOK) {
            if (!(product instanceof TextBook)) {
                return false;
            }
        }
        if (type == MOVIE) {
            if (!(product instanceof Movie)) {
                return false;
            }
        }
        if (name != null && !(product.getName().toLowerCase().trim()
                .contains(name.toLowerCase().trim()))) {
            return false;
        }
        return author == null || product.getAuthor().toLowerCase().trim().contains(author.toLowerCase().trim());
    }

    public Collection<Product> getProducts() {
        return products;
    }

}

