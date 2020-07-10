package view.librarian;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.product.Product;

import java.util.List;

import static utils.Base.isName;
import static utils.Base.isPositiveInteger;

public class ProductTableView extends TableView<Product> {

    public ProductTableView() {
        super();
        this.getColumns().addAll(getNameColumn(), getAuthorColumn(),getQuantityColumn(), getAisleColumn(), getShelfColumn(),getTypeColumn());
        this.setEditable(false);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void update(List<? extends Product> products) {
        if (products != null) {
            this.setItems(FXCollections.observableArrayList(products));
            this.refresh();
        }
    }

    public TableColumn<Product, String> getNameColumn() {
        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        PropertyValueFactory<Product, String> nameCellValFactory = new PropertyValueFactory<>("name");
        nameCol.setCellValueFactory(nameCellValFactory);
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null && isName(event.getNewValue()) ?
                    event.getNewValue() : event.getOldValue();
            ((Product) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow())).setName(value);
            this.refresh();
        });
        return nameCol;
    }

    public TableColumn<Product, String> getQuantityColumn() {
        TableColumn<Product, String> quantityCol = new TableColumn<>("Quantity");
        PropertyValueFactory<Product, String> quantityCellValFactory = new PropertyValueFactory<>("quantityAsString");
        quantityCol.setCellValueFactory(quantityCellValFactory);
        quantityCol.setCellFactory(TextFieldTableCell.forTableColumn());
        quantityCol.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null && isPositiveInteger(event.getNewValue()) ?
                    event.getNewValue() : event.getOldValue();
            ((Product) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow())).setQuantity(Integer.parseInt(value));
            this.refresh();
        });
        this.setEditable(true);
        return quantityCol;
    }

    protected TableColumn<Product, String> getAuthorColumn() {
        TableColumn<Product, String> authorCol = new TableColumn<>("Author");
        PropertyValueFactory<Product, String> authorCellValFactory = new PropertyValueFactory<>("author");
        authorCol.setCellValueFactory(authorCellValFactory);
        authorCol.setCellFactory(TextFieldTableCell.forTableColumn());
        authorCol.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null && isName(event.getNewValue()) ?
                    event.getNewValue() : event.getOldValue();
            ((Product) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow())).setAuthor(value);
            this.refresh();
        });
        return authorCol;
    }

    protected TableColumn<Product, String> getAisleColumn() {
        TableColumn<Product, String> aisleCol = new TableColumn<>("Aisle");
        PropertyValueFactory<Product, String> aisleCellValFactory = new PropertyValueFactory<>("aisle");
        aisleCol.setCellValueFactory(aisleCellValFactory);
        aisleCol.setCellFactory(TextFieldTableCell.forTableColumn());
        aisleCol.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null && isPositiveInteger(event.getNewValue()) ?
                    event.getNewValue() : event.getOldValue();
            ((Product) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow())).getLocation().setAisle(Integer.parseInt(value));
            this.refresh();
        });
        return aisleCol;
    }

    protected TableColumn<Product, String> getShelfColumn() {
        TableColumn<Product, String> shelfCol = new TableColumn<>("Shelf");
        PropertyValueFactory<Product, String> shelfCellValFactory = new PropertyValueFactory<>("shelf");
        shelfCol.setCellValueFactory(shelfCellValFactory);
        shelfCol.setCellFactory(TextFieldTableCell.forTableColumn());
        shelfCol.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null && isPositiveInteger(event.getNewValue()) ?
                    event.getNewValue() : event.getOldValue();
            ((Product) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow())).getLocation().setShelf(Integer.parseInt(value));
            this.refresh();
        });
        return shelfCol;
    }

    public TableColumn<Product, String> getTypeColumn() {
        TableColumn<Product, String> typeCol = new TableColumn<>("Type");
        PropertyValueFactory<Product, String> typeCellValFactory = new PropertyValueFactory<>("type");
        typeCol.setCellValueFactory(typeCellValFactory);
        return typeCol;
    }

}