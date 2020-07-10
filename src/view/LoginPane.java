package view;

import control.LibraryControl.Role;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import static utils.Base.SPACE;

public class LoginPane extends VBox {

    private static Integer count;

    private Button loginBtn;
    private Label idLabel;
    private Label emailLabel;
    private Label roleLabel;
    private TextField idField;
    private TextField emailField;
    private ChoiceBox<Role> roleSelection;

    public LoginPane() {
        this.loginBtn = new Button("Log In");
        this.getChildren().addAll(this.initDataSection(), this.loginBtn);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(SPACE);
    }


    private GridPane initDataSection() {
        GridPane grid = new GridPane();
        this.idLabel = new Label("ID:");
        this.emailLabel = new Label("Email:");
        this.idField = new PasswordField();
        this.emailField = new TextField();
        this.roleSelection = new ChoiceBox<>();
        this.roleLabel = new Label("Role:");
        this.roleSelection.setItems(FXCollections.observableArrayList(Role.MANAGER, Role.LIBRARIAN));

        grid.add(this.emailLabel, 0, 0);
        grid.add(this.idLabel, 0, 1);
        grid.add(this.emailField, 1, 0);
        grid.add(this.idField, 1, 1);
        grid.add(this.roleLabel, 0, 2);
        grid.add(this.roleSelection, 1, 2);

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(SPACE);
        grid.setVgap(SPACE);
        return grid;
    }

    public void clearInputData() {
        this.getIdField().clear();
        this.getEmailField().clear();
        this.getRoleSelection().getSelectionModel().clearSelection();
    }

    public Button getLoginBtn() {
        return loginBtn;
    }

    public void setLoginBtn(Button loginBtn) {
        this.loginBtn = loginBtn;
    }

    public Label getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(Label idLabel) {
        this.idLabel = idLabel;
    }

    public TextField getIdField() {
        return idField;
    }

    public void setIdField(TextField idField) {
        this.idField = idField;
    }

    public Label getEmailLabel() {
        return emailLabel;
    }

    public void setEmailLabel(Label emailLabel) {
        this.emailLabel = emailLabel;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public void setEmailField(TextField emailField) {
        this.emailField = emailField;
    }

    public ChoiceBox<Role> getRoleSelection() {
        return roleSelection;
    }

    public void setRoleSelection(ChoiceBox<Role> roleSelection) {
        this.roleSelection = roleSelection;
    }

}
