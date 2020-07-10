package view.manager;

import control.LibraryControl;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import model.person.Customer;
import model.person.Employee;
import model.person.Manager;
import model.person.Person;
import model.product.Product;
import view.librarian.ProductTableView;

import java.util.ArrayList;
import java.util.List;

import static utils.Base.BOTTOM_TABLE_HEIGHT;

public class PersonManagementPane extends BorderPane {

    private PersonTableView personView;
    private ProductTableView productView;
    private LibraryControl libraryControl;
    private PersonControlPane managementPane;
    private Manager loggedOnManager;
    private MenuItem changeRoleItem;
    private ContextMenu changeRoleContextMenu;

    public PersonManagementPane(LibraryControl libraryControl) {
        super();
        this.libraryControl = libraryControl;
        initCenter();
        initTop();
        initBottom();
    }

    private void initCenter() {
        this.personView = new PersonTableView();
        this.changeRoleItem = new MenuItem("Change Role");
        this.changeRoleContextMenu = new ContextMenu(changeRoleItem);
        TitledPane personTitle = new TitledPane();
        personTitle.setText("Employees and Costumers");
        personTitle.setAlignment(Pos.TOP_CENTER);
        personTitle.setCollapsible(false);
        personTitle.setContent(this.personView);
        this.setCenter(personTitle);
    }

    private void initTop() {
        this.managementPane = new PersonControlPane();
        handleSearch();
        handleAdd();
        handleUpdate();
        handleRemove();
        handleBorrowView();
        this.setTop(this.managementPane);
    }

    private void initBottom() {
        this.productView = new ProductTableView();
        this.productView.setPrefHeight(BOTTOM_TABLE_HEIGHT);
        TitledPane productTitle = new TitledPane();
        productTitle.setText("Products");
        productTitle.setAlignment(Pos.TOP_CENTER);
        productTitle.setCollapsible(false);
        productTitle.setContent(this.productView);
        this.setBottom(productTitle);
    }

    private void handleBorrowView() {
        this.personView.getSelectionModel().selectedItemProperty().addListener(e -> {
            Person person = this.personView.getSelectionModel().getSelectedItem();
            if (person instanceof Customer) {
                Customer customer = (Customer) person;
                if (customer.getProducts().size() > 0) {
                    this.productView.update((List<? extends Product>) customer.getProducts());
                } else {
                    this.productView.update(new ArrayList<>());
                }
            } else {
                this.productView.update(new ArrayList<>());
            }
        });
    }

    private void handleSearch() {
        this.managementPane.getButtons().getSearchBtn().setOnMouseClicked(e -> updateView());
        this.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER) {
                updateView();
            }
        });
    }

    private void handleChangeRole() {
        this.personView.setOnContextMenuRequested(e1 -> {
            Person person = this.personView.getSelectionModel().getSelectedItem();
            if (person instanceof Employee) {
                changeRoleContextMenu.show(this.personView, e1.getScreenX(), e1.getScreenY());
                changeRoleItem.setOnAction(e2 -> {
                    this.libraryControl.getEmployeeControl().changeRole((Employee) person);
                    this.updateView();

                });
            }
        });
    }

    private void handleAdd() {
        this.managementPane.getButtons().getAddBtn()
                .setOnMouseClicked(e -> {
                    this.libraryControl
                            .addNewPerson(loggedOnManager.getId(), loggedOnManager.getEmployeeId(),
                                    this.managementPane.getIdText(), this.managementPane.getNameText(),
                                    this.managementPane.getPhoneNumberText(), this.managementPane.getEmailText(),
                                    this.managementPane.filter());
                    updateView();
                });
    }

    public void handleUpdate() {
        this.managementPane.getButtons().getUpdateBtn()
                .setOnMouseClicked(e -> {
                    this.personView.edit(-1, null);
                    this.personView.setEditable(!this.personView.isEditable());
                    if (this.personView.isEditable()) {
                        handleChangeRole();
                        this.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, null, null)));
                    } else {
                        this.personView.setOnContextMenuRequested(e1 -> {
                        });
                        this.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, null, null)));
                    }
                });
    }

    private void handleRemove() {
        this.managementPane.getButtons().getRemoveBtn().setOnMouseClicked(e -> removeSelectedPerson());
        this.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.DELETE) {
                removeSelectedPerson();
            }
        });
    }

    private void removeSelectedPerson() {
        Person person = this.personView.getSelectionModel().getSelectedItem();
        if (person == null) {
            return;
        }
        this.libraryControl.deletePerson(loggedOnManager.getId(),
                loggedOnManager.getEmployeeId(), person.getId(), this.getRoleIdFromPerson(person),
                person.getRole());
        updateView();
    }

    private String getRoleIdFromPerson(Person person) {
        if (person instanceof Employee) {
            return ((Employee) person).getEmployeeId();
        }
        if (person instanceof Customer) {
            return ((Customer) person).getCustomerId();
        }
        return null;
    }

    private void updateView() {
        this.personView.update(this.libraryControl.
                searchByIdNamePhoneEmailAndRole(this.managementPane.getIdText(),
                        this.managementPane.getNameText(), this.managementPane.getPhoneNumberText(),
                        this.managementPane.getEmailText(),
                        this.managementPane.filter()));
    }

    public Manager getLoggedOnManager() {
        return loggedOnManager;
    }

    public void setLoggedOnManager(Manager loggedOnManager) {
        this.loggedOnManager = loggedOnManager;
    }

}
