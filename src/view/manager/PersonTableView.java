package view.manager;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.person.Person;

import java.util.List;

import static utils.Base.*;


public class PersonTableView extends TableView<Person> {

    public PersonTableView() {
        super();
        this.getColumns().addAll(getIdColumn(), getNameColumn(), getPhoneColumn(), getEmailColumn(), getRoleColumn());
        this.setEditable(false);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void update(List<? extends Person> people) {
        if (people != null) {
            this.setItems(FXCollections.observableArrayList(people));
        }
    }

    public TableColumn<Person, String> getIdColumn() {
        TableColumn<Person, String> idCol = new TableColumn<>("Id");
        PropertyValueFactory<Person, String> idCellValFactory = new PropertyValueFactory<>("id");
        idCol.setCellValueFactory(idCellValFactory);
        return idCol;
    }

    public TableColumn<Person, String> getNameColumn() {
        TableColumn<Person, String> nameCol = new TableColumn<>("Name");
        PropertyValueFactory<Person, String> nameCellValFactory = new PropertyValueFactory<>("name");
        nameCol.setCellValueFactory(nameCellValFactory);
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null && isName(event.getNewValue()) ?
                    event.getNewValue() : event.getOldValue();
            ((Person) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow())).setName(value);
            this.refresh();
        });
        return nameCol;
    }

    protected TableColumn<Person, String> getPhoneColumn() {
        TableColumn<Person, String> phoneCol = new TableColumn<>("Phone");
        PropertyValueFactory<Person, String> phoneCellValFactory = new PropertyValueFactory<>("phoneNumber");
        phoneCol.setCellValueFactory(phoneCellValFactory);
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null && isPhoneNumber(event.getNewValue()) ?
                    event.getNewValue() : event.getOldValue();
            ((Person) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow())).setPhoneNumber(value);
            this.refresh();
        });
        return phoneCol;
    }

    protected TableColumn<Person, String> getRoleColumn() {
        TableColumn<Person, String> roleCol = new TableColumn<>("Role");
        PropertyValueFactory<Person, String> roleCellValFactory = new PropertyValueFactory<>("role");
        roleCol.setCellValueFactory(roleCellValFactory);
        return roleCol;
    }

    protected TableColumn<Person, String> getEmailColumn() {
        TableColumn<Person, String> emailCol = new TableColumn<>("Email");
        PropertyValueFactory<Person, String> emailCellValFactory = new PropertyValueFactory<>("email");
        emailCol.setCellValueFactory(emailCellValFactory);
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(event -> {
            final String value = event.getNewValue() != null && isEmail(event.getNewValue()) ?
                    event.getNewValue() : event.getOldValue();
            ((Person) event.getTableView().getItems()
                    .get(event.getTablePosition().getRow())).setEmail(value);
            this.refresh();
        });
        return emailCol;
    }

}