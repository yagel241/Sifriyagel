package view.librarian;

import control.LibraryControl;
import control.LibraryControl.ProductType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.person.Customer;
import model.person.Librarian;
import model.product.Comics;
import model.product.Movie;
import model.product.Product;
import model.product.TextBook;
import utils.Base;
import view.manager.PersonTableView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import static control.LibraryControl.ProductType.*;
import static utils.Base.BOTTOM_TABLE_HEIGHT;
import static utils.Base.SPACE;

public class ProductManagementPane extends BorderPane {

    private Stage transferStage;
    private PersonTableView personView;
    private ProductTableView productView;
    private LibraryControl libraryControl;
    private ProductDataPane dataPane;
    private ProductBtnPane btnPane;
    private ProductTransferPane productTransferPane;
    private ContextMenu productMenu;
    private MenuItem productBorrowItem;
    private MenuItem productRetrieveItem;
    private MenuItem productUnique1Item;
    private MenuItem productUnique2Item;
    private Menu productMoreDetailsMenu;
    private Librarian loggedOnLibrarian;

    public ProductManagementPane(LibraryControl libraryControl) {
        super();
        this.libraryControl = libraryControl;
        initTransferPane();
        initTop();
        initCenter();
        initBottom();
        initHandlers();
    }

    private void initTransferPane() {
        this.transferStage = new Stage();
        this.productTransferPane = new ProductTransferPane();
        transferStage.setScene(new Scene(this.productTransferPane, 350, 100));
        this.productBorrowItem = new MenuItem("Borrow Product");
        this.productRetrieveItem = new MenuItem("Retrieve Product");
        this.productMoreDetailsMenu = new Menu("More Details");
        this.productUnique1Item = new MenuItem();
        this.productUnique2Item = new MenuItem();
        this.productMenu = new ContextMenu(this.productBorrowItem,
                this.productRetrieveItem,
                new SeparatorMenuItem(),
                this.productMoreDetailsMenu);

    }

    private void initTop() {
        this.btnPane = new ProductBtnPane();
        this.dataPane = new ProductDataPane();
        HBox box = new HBox();
        box.getChildren().addAll(this.dataPane, this.btnPane);
        box.setSpacing(SPACE);
        box.setAlignment(Pos.CENTER);
        this.setTop(box);
    }

    private void initCenter() {
        this.productView = new ProductTableView();
        TitledPane productsTitle = new TitledPane();
        productsTitle.setText("Library Products");
        productsTitle.setAlignment(Pos.TOP_CENTER);
        productsTitle.setCollapsible(false);
        productsTitle.setContent(this.productView);
        this.productView.getQuantityColumn().setEditable(true);
        this.setCenter(productsTitle);
    }

    private void initBottom() {
        this.personView = new PersonTableView();
        this.personView.setPrefHeight(BOTTOM_TABLE_HEIGHT);
        TitledPane personTitle = new TitledPane();
        personTitle.setText("Borrowed By");
        personTitle.setAlignment(Pos.TOP_CENTER);
        personTitle.setCollapsible(false);
        personTitle.setContent(this.personView);
        this.setBottom(personTitle);
    }

    private void initHandlers() {
        handleSearch();
        handleAdd();
        handleTransfer();
        handleRemove();
        handleViewOfBorrowedBy();
    }

    private void handleViewOfBorrowedBy() {
        this.productView.getSelectionModel().selectedItemProperty().addListener(e -> {
            Product product = this.productView.getSelectionModel().getSelectedItem();
            if (product != null) {
                this.personView.update(this.libraryControl.whoBorrowed(product.getSerial()));
            } else {
                this.personView.update(new ArrayList<>());

            }
        });
    }

    private void updateView() {
        this.productView.update(this.libraryControl.searchByName_Author_Type(this.dataPane.getNameText(),
                this.dataPane.getAuthorText(),
                this.dataPane.filter()));
    }


    private void handleSearch() {
        this.btnPane.getSearchBtn().setOnMouseClicked(e -> updateView());
        this.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER) {
                updateView();
            }
        });
    }

    private void handleAdd() {
        this.btnPane.getAddBtn()
                .setOnMouseClicked(e -> {
                    this.libraryControl
                            .addNewProduct(loggedOnLibrarian.getId(), loggedOnLibrarian.getEmployeeId(),
                                    this.dataPane.getNameText(), this.dataPane.getAuthorText(),
                                    this.dataPane.getAisleText(), this.dataPane.getShelfText(),
                                    this.dataPane.getQuantityText(), this.dataPane.filter(),
                                    this.dataPane.getUniqueText(), this.dataPane.getUnique2Text());
                    this.updateView();
                });
    }

    private void handleTransfer() {
        handleTransferInitiation();
        handleTransferAction();
    }

    private void handleTransferAction() {
        this.productTransferPane.getApprovalBtn().setOnMouseClicked(e -> {
            String customerId = this.productTransferPane.getCustomerIdField().getText();
            AtomicReference<Customer> customer = new AtomicReference<>();
            if (customerId != null) {
                customer.set(this.libraryControl.findCustomerById(this.loggedOnLibrarian.getId(),
                        this.loggedOnLibrarian.getEmployeeId(), customerId));
            }
            if (customer.get() != null) {
                if (this.productTransferPane.getPerformBorrow()) { // then borrow
                    if (this.libraryControl.borrowProduct(this.loggedOnLibrarian.getId(),
                            this.loggedOnLibrarian.getEmployeeId(), customer.get().getId(),
                            customer.get().getCustomerId(), this.productTransferPane.getSerial())) {
                        this.updateView();
                        this.productTransferPane.getCustomerIdField().clear();
                        this.transferStage.close();
                    } else {
                        Base.showAlert(Alert.AlertType.ERROR, "Failed to borrow the product!");
                    }
                } else { // then retrieve
                    if (this.libraryControl.retrieveProduct(this.loggedOnLibrarian.getId(),
                            this.loggedOnLibrarian.getEmployeeId(), customer.get().getId(),
                            customer.get().getCustomerId(), this.productTransferPane.getSerial())) {
                        this.updateView();
                        this.productTransferPane.getCustomerIdField().clear();
                        transferStage.close();
                    } else {
                        Base.showAlert(Alert.AlertType.ERROR, "Failed to retrieve the product!");
                    }
                }
            }
        });
    }

    private void handleTransferInitiation() {
        this.productView.setOnContextMenuRequested(e1 -> {
            this.productMoreDetailsMenu.getItems().clear();
            Product product = this.productView.getSelectionModel().getSelectedItem();
            if (product != null) {
                this.productUnique1Item = new MenuItem();
                this.productUnique2Item = new MenuItem();
                this.productMenu.show(this.personView, e1.getScreenX(), e1.getScreenY());
                this.productBorrowItem.setOnAction(e2 -> {
                    this.productTransferPane.setStyle(product.getSerial(), true);
                    transferStage.setTitle("SifriYagel - Borrow a Product");
                    transferStage.show();
                });
                this.productRetrieveItem.setOnAction(e3 -> {
                    this.productTransferPane.setStyle(product.getSerial(), false);
                    transferStage.setTitle("SifriYagel - Retrieve a Product");
                    transferStage.show();
                });
                if(product instanceof Comics){
                    Comics comics = (Comics) product;
                    this.productUnique1Item.setText("Company:" + comics.getCompany());
                    this.productUnique2Item.setText("Edition:" + comics.getEdition());
                    this.productMoreDetailsMenu.getItems().addAll(this.productUnique1Item,this.productUnique2Item);
                }
                else if(product instanceof TextBook){
                    TextBook textBook = (TextBook) product;
                    this.productUnique1Item.setText("Field:" + textBook.getField());
                    this.productUnique2Item.setText("Grade:" + textBook.getGrade());
                    this.productMoreDetailsMenu.getItems().addAll(this.productUnique1Item, this.productUnique2Item);
                }
                else if(product instanceof Movie){
                    Movie movie = (Movie) product;
                    this.productUnique1Item.setText("Genre:" + movie.getGenre());
                    this.productUnique2Item.setText("Year:" + movie.getYear());
                    this.productMoreDetailsMenu.getItems().addAll(this.productUnique1Item, this.productUnique2Item);
                }
            }
        });
    }

    private void handleRemove() {
        this.btnPane.getRemoveBtn().setOnMouseClicked(e -> {
            Product product = this.productView.getSelectionModel().getSelectedItem();
            if (product != null) {
                this.libraryControl.deleteProduct(this.loggedOnLibrarian.getId(),
                        this.loggedOnLibrarian.getEmployeeId(), product.getSerial());
                updateView();
            }
        });
    }

    public PersonTableView getPersonView() {
        return personView;
    }

    public void setPersonView(PersonTableView personView) {
        this.personView = personView;
    }

    public ProductTableView getProductView() {
        return productView;
    }

    public void setProductView(ProductTableView productView) {
        this.productView = productView;
    }

    public LibraryControl getLibraryControl() {
        return libraryControl;
    }

    public void setLibraryControl(LibraryControl libraryControl) {
        this.libraryControl = libraryControl;
    }

    public Librarian getLoggedOnLibrarian() {
        return loggedOnLibrarian;
    }

    public void setLoggedOnLibrarian(Librarian loggedOnLibrarian) {
        this.loggedOnLibrarian = loggedOnLibrarian;
    }

}
