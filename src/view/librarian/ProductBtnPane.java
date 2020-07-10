package view.librarian;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import static utils.Base.*;
import static view.manager.PersonBtnPane.*;

public class ProductBtnPane extends VBox {

    private Button searchBtn ;
    private Button addBtn;
    private Button removeBtn;

    public ProductBtnPane() {
        initButtons();
        this.setPadding(new Insets(PAD, PAD, PAD, PAD));
        this.setSpacing(SPACE);
        this.setAlignment(Pos.CENTER);
    }

    private void initButtons() {
        this.searchBtn = new Button("", new ImageView(new Image(searchLogoURL,
                BUTTON_SIZE, BUTTON_SIZE, false, false)));
        this.addBtn = new Button("", new ImageView(new Image(addLogoURL,
                BUTTON_SIZE, BUTTON_SIZE, false, false)));
        this.removeBtn = new Button("", new ImageView(new Image(removeLogoURL,
                BUTTON_SIZE, BUTTON_SIZE, false, false)));
        this.getChildren().addAll(this.searchBtn, this.addBtn, this.removeBtn);
    }

    public Button getSearchBtn() {
        return searchBtn;
    }

    public void setSearchBtn(Button searchBtn) {
        this.searchBtn = searchBtn;
    }

    public Button getAddBtn() {
        return addBtn;
    }

    public void setAddBtn(Button addBtn) {
        this.addBtn = addBtn;
    }

    public Button getRemoveBtn() {
        return removeBtn;
    }

    public void setRemoveBtn(Button removeBtn) {
        this.removeBtn = removeBtn;
    }
}
