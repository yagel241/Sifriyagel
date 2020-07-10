package view.manager;

import control.LibraryControl.Role;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static utils.Base.PAD;
import static utils.Base.SPACE;


public class PersonDataPane extends HBox {
    private TextField idField;
    private TextField nameField;
    private TextField phoneField;
    private TextField emailField;
    private ToggleGroup group;
    private RadioButton managerFilterRBtn;
    private RadioButton librarianFilterRBtn;
    private RadioButton customerFilterRBtn;


    public PersonDataPane() {
        initTextFields();
        initFilters();
        this.setPadding(new Insets(PAD, PAD, PAD, PAD));
        this.setSpacing(SPACE);
        this.setAlignment(Pos.CENTER);
    }

    private void initFilters() {
        this.group = new ToggleGroup();
        this.managerFilterRBtn = new RadioButton("Manager");
        this.librarianFilterRBtn = new RadioButton("Librarian");
        this.customerFilterRBtn = new RadioButton("Customer");
        this.managerFilterRBtn.setToggleGroup(group);
        this.librarianFilterRBtn.setToggleGroup(group);
        this.customerFilterRBtn.setToggleGroup(group);
        VBox box = new VBox();
        box.setMinWidth(100);
        box.getChildren().addAll(this.managerFilterRBtn, this.librarianFilterRBtn, this.customerFilterRBtn);
        box.setSpacing(SPACE);
        box.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(box);
    }

    private void initTextFields() {
        this.idField = new TextField();
        this.nameField = new TextField();
        this.phoneField = new TextField();
        this.emailField = new TextField();
        this.idField.setPromptText("Enter ID");
        this.nameField.setPromptText("Enter Name");
        this.phoneField.setPromptText("Enter Phone");
        this.emailField.setPromptText("Enter Email");
        initTextFieldsGrid();
    }

    private void initTextFieldsGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(SPACE);
        grid.setVgap(SPACE);
        grid.setAlignment(Pos.CENTER);
        grid.add(this.idField, 0, 0);
        grid.add(this.nameField, 1, 0);
        grid.add(this.phoneField, 1, 1);
        grid.add(this.emailField, 0, 1);
        this.getChildren().add(grid);
    }

    public Role filter() {
        if (managerFilterRBtn.isSelected()) {
            return Role.MANAGER;
        }
        if (librarianFilterRBtn.isSelected()) {
            return Role.LIBRARIAN;
        }
        if (customerFilterRBtn.isSelected()) {
            return Role.CUSTOMER;
        }
        return null;
    }

    public TextField getIdField() {
        return idField;
    }

    public void setIdField(TextField idField) {
        this.idField = idField;
    }

    public TextField getNameField() {
        return nameField;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    public TextField getPhoneField() {
        return phoneField;
    }

    public void setPhoneField(TextField phoneField) {
        this.phoneField = phoneField;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public void setEmailField(TextField emailField) {
        this.emailField = emailField;
    }

    public RadioButton getManagerFilterRBtn() {
        return managerFilterRBtn;
    }

    public void setManagerFilterRBtn(RadioButton managerFilterRBtn) {
        this.managerFilterRBtn = managerFilterRBtn;
    }

    public RadioButton getLibrarianFilterRBtn() {
        return librarianFilterRBtn;
    }

    public void setLibrarianFilterRBtn(RadioButton librarianFilterRBtn) {
        this.librarianFilterRBtn = librarianFilterRBtn;
    }

    public RadioButton getCustomerFilterRBtn() {
        return customerFilterRBtn;
    }

    public void setCustomerFilterRBtn(RadioButton customerFilterRBtn) {
        this.customerFilterRBtn = customerFilterRBtn;
    }

}
