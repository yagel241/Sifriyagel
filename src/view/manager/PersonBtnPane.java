package view.manager;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import static utils.Base.BUTTON_SIZE;
import static utils.Base.SPACE;

public class PersonBtnPane extends GridPane {

    public static String searchLogoURL ="http://www.clker.com/cliparts/z/w/Q/7/f/C/search-logo-md.png";
    public static String addLogoURL = "http://www.clker.com/cliparts/2/f/6/1/11949856271997454136tasto_2_architetto_franc_01.svg.med.png";
    private static String updateLogoURL = "http://www.clker.com/cliparts/x/g/X/Y/V/o/green-update-button-md.png";
    public static String removeLogoURL ="http://www.clker.com/cliparts/Q/2/K/A/A/G/trash-red.svg.med.png";

    private Button searchBtn;
    private Button addBtn;
    private Button updateBtn;
    private Button removeBtn;

    public PersonBtnPane() {
        initSearchButton();
        initAddButton();
        initUpdateButton();
        initRemoveButton();
        initBtnGrid();
        this.setAlignment(Pos.CENTER);
    }

    private void initBtnGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(SPACE);
        grid.setVgap(SPACE);
        grid.add(this.searchBtn, 0, 0);
        grid.add(this.addBtn, 0, 1);
        grid.add(this.updateBtn, 1, 0);
        grid.add(this.removeBtn, 1, 1);
        this.getChildren().add(grid);
    }

    private void initRemoveButton() {
        this.removeBtn = new Button("", new ImageView(new Image(removeLogoURL,
                BUTTON_SIZE, BUTTON_SIZE, false, false)));
        this.add(this.removeBtn, 0, 3);
    }

    private void initUpdateButton() {
        this.updateBtn = new Button("", new ImageView(new Image(updateLogoURL,
                BUTTON_SIZE, BUTTON_SIZE, false, false)));
        this.add(this.updateBtn, 0, 2);

    }

    private void initAddButton() {
        this.addBtn = new Button("", new ImageView(new Image(addLogoURL,
                BUTTON_SIZE, BUTTON_SIZE, false, false)));
        this.add(this.addBtn, 0, 1);
    }

    private void initSearchButton() {
        this.searchBtn = new Button("", new ImageView(new Image(searchLogoURL,
                BUTTON_SIZE, BUTTON_SIZE, false, false)));
        this.add(this.searchBtn, 0, 0);
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

    public Button getUpdateBtn() {
        return updateBtn;
    }

    public Button getRemoveBtn() {
        return removeBtn;
    }
}
