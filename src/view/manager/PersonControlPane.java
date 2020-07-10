package view.manager;

import control.LibraryControl.Role;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import static utils.Base.SPACE;


public class PersonControlPane extends HBox {

    private PersonDataPane data;
    private PersonBtnPane buttons;

    public PersonControlPane() {
        this.buttons = new PersonBtnPane();
        this.data = new PersonDataPane();
        this.getChildren().addAll(this.data, this.buttons);
        this.setSpacing(SPACE);
        this.setAlignment(Pos.CENTER);
    }


    public Role filter() {
        if (this.data.getManagerFilterRBtn().isSelected()) {
            return Role.MANAGER;
        }
        if (this.data.getLibrarianFilterRBtn().isSelected()) {
            return Role.LIBRARIAN;
        }
        if (this.data.getCustomerFilterRBtn().isSelected()) {
            return Role.CUSTOMER;
        }
        return null;
    }

    public PersonDataPane getData() {
        return data;
    }

    public void setData(PersonDataPane data) {
        this.data = data;
    }

    public PersonBtnPane getButtons() {
        return buttons;
    }

    public void setButtons(PersonBtnPane buttons) {
        this.buttons = buttons;
    }

    public String getIdText() {
        return this.data.getIdField().getText();
    }

    public String getNameText() {
        return this.data.getNameField().getText();
    }

    public String getPhoneNumberText() {
        return this.data.getPhoneField().getText();
    }

    public String getEmailText(){
        return  this.data.getEmailField().getText();
    }



}
