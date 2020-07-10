package view.librarian;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static utils.Base.PAD;
import static utils.Base.SPACE;

public class ProductTransferPane extends VBox {

    private TextField customerIdField;
    private Label customerIdLabel;
    private Button approvalBtn;
    private Boolean performBorrow;
    private String serial;

    public ProductTransferPane() {
        this.customerIdField = new TextField();
        this.customerIdField.setPromptText("Enter Customer ID");
        this.customerIdLabel = new Label("Customer ID:");
        this.approvalBtn = new Button();
        HBox box = new HBox();
        box.setSpacing(SPACE);
        box.getChildren().addAll(this.customerIdLabel, this.customerIdField);
        this.setPadding(new Insets(PAD, PAD, PAD, PAD));
        this.setSpacing(SPACE);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(box, this.approvalBtn);
    }

    public void setStyle(String serial, boolean performBorrow) {
        this.setSerial(serial);
        this.setPerformBorrow(performBorrow);
        if (this.performBorrow) {
            this.approvalBtn.setText("Borrow");
        } else {
            this.approvalBtn.setText("Retrieve");
        }

    }

    public TextField getCustomerIdField() {
        return customerIdField;
    }

    public void setCustomerIdField(TextField customerIdField) {
        this.customerIdField = customerIdField;
    }

    public Label getCustomerIdLabel() {
        return customerIdLabel;
    }

    public void setCustomerIdLabel(Label customerIdLabel) {
        this.customerIdLabel = customerIdLabel;
    }

    public Button getApprovalBtn() {
        return approvalBtn;
    }

    public void setApprovalBtn(Button approvalBtn) {
        this.approvalBtn = approvalBtn;
    }

    public Boolean getPerformBorrow() {
        return performBorrow;
    }

    public void setPerformBorrow(Boolean performBorrow) {
        this.performBorrow = performBorrow;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

}
