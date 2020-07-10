package view.librarian;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static control.LibraryControl.ProductType;
import static utils.Base.PAD;
import static utils.Base.SPACE;

public class ProductDataPane extends HBox {

    private RadioButton allFilterBtn;
    private RadioButton textBookFilterRBtn;
    private RadioButton comicsFilterRBtn;
    private RadioButton movieFilterRBtn;
    private ToggleGroup group;

    private TextField nameField;
    private TextField authorField;
    private TextField aisleField;
    private TextField shelfField;
    private TextField quantityField;
    private TextField uniqueField;
    private TextField unique2Field;

    public ProductDataPane() {
        initTextFields();
        initFilters();
        this.setSpacing(SPACE);
        this.setAlignment(Pos.CENTER);
    }

    private void initFilters() {
        this.group = new ToggleGroup();
        this.allFilterBtn = new RadioButton("All Types");
        this.textBookFilterRBtn = new RadioButton("Text Book");
        this.comicsFilterRBtn = new RadioButton("Comics");
        this.movieFilterRBtn = new RadioButton("Movie");
        this.allFilterBtn.setToggleGroup(group);
        this.textBookFilterRBtn.setToggleGroup(group);
        this.comicsFilterRBtn.setToggleGroup(group);
        this.movieFilterRBtn.setToggleGroup(group);
        VBox box = new VBox();
        box.setSpacing(SPACE);
        box.setMinWidth(100);
        box.getChildren().addAll(this.textBookFilterRBtn, this.comicsFilterRBtn, this.movieFilterRBtn, this.allFilterBtn);
        this.getChildren().add(box);
        this.allFilterBtn.setSelected(true);
        this.group.selectedToggleProperty().addListener(e -> filter());
    }

    private void initTextFields() {
        this.nameField = new TextField();
        this.authorField = new TextField();
        this.aisleField = new TextField();
        this.shelfField = new TextField();
        this.quantityField = new TextField();
        this.uniqueField = new TextField();
        this.unique2Field = new TextField();
        this.nameField.setPromptText("Enter Name");
        this.authorField.setPromptText("Enter Author");
        this.aisleField.setPromptText("Enter Aisle Number");
        this.shelfField.setPromptText("Enter Shelf Number");
        this.quantityField.setPromptText("Enter Quantity");
        this.setUniqueFieldsTo(true, "", "");
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(PAD, PAD, PAD, PAD));
        GridPane grid = new GridPane();
        grid.add(this.nameField,0, 0);
        grid.add(this.authorField,1, 0);
        grid.add(this.quantityField,2, 0);
        grid.add(this.aisleField,0, 1);
        grid.add(this.shelfField,1, 1);
        grid.add(this.uniqueField,0, 2);
        grid.add(this.unique2Field,1, 2);
        grid.setHgap(SPACE);
        grid.setVgap(SPACE);
        this.getChildren().add(grid);

    }

    public ProductType filter() {
        if (this.textBookFilterRBtn.isSelected()) {
            this.setUniqueFieldsTo(false, "Enter Study Field", "Enter Dedicated Grade");
            return ProductType.TEXT_BOOK;
        }
        if (this.comicsFilterRBtn.isSelected()) {
            this.setUniqueFieldsTo(false, "Enter Comics Company", "Enter Comics Edition");
            return ProductType.COMICS;
        }
        if (this.movieFilterRBtn.isSelected()) {
            this.setUniqueFieldsTo(false, "Enter Movie Genre", "Enter Published Year");
            return ProductType.MOVIE;
        }
        if (this.allFilterBtn.isSelected()) {
            this.setUniqueFieldsTo(true, "", "");
        }
        return null;
    }

    private void setUniqueFieldsTo(Boolean disable, String field1prompt, String field2prompt) {
        this.uniqueField.setPromptText(field1prompt);
        this.unique2Field.setPromptText(field2prompt);
        this.uniqueField.setDisable(disable);
        this.unique2Field.setDisable(disable);
        this.uniqueField.setEditable(true);
        this.unique2Field.setEditable(true);
        if (disable) {
            this.uniqueField.clear();
            this.unique2Field.clear();
        }
    }

    public String getNameText() {
        return this.getNameField().getText();
    }

    public String getAuthorText() {
        return this.getAuthorField().getText();
    }

    public String getAisleText() {
        return this.getAisleField().getText();
    }

    public String getShelfText(){
        return  this.getShelfField().getText();
    }

    public String getQuantityText(){
        return  this.getQuantityField().getText();
    }

    public String getUniqueText(){
        return  this.getUniqueField().getText();
    }

    public String getUnique2Text(){
        return  this.getUnique2Field().getText();
    }

    public RadioButton getAllFilterBtn() {
        return allFilterBtn;
    }

    public void setAllFilterBtn(RadioButton allFilterBtn) {
        this.allFilterBtn = allFilterBtn;
    }

    public RadioButton getTextBookFilterRBtn() {
        return textBookFilterRBtn;
    }

    public void setTextBookFilterRBtn(RadioButton textBookFilterRBtn) {
        this.textBookFilterRBtn = textBookFilterRBtn;
    }

    public RadioButton getComicsFilterRBtn() {
        return comicsFilterRBtn;
    }

    public void setComicsFilterRBtn(RadioButton comicsFilterRBtn) {
        this.comicsFilterRBtn = comicsFilterRBtn;
    }

    public RadioButton getMovieFilterRBtn() {
        return movieFilterRBtn;
    }

    public void setMovieFilterRBtn(RadioButton movieFilterRBtn) {
        this.movieFilterRBtn = movieFilterRBtn;
    }

    public ToggleGroup getGroup() {
        return group;
    }

    public void setGroup(ToggleGroup group) {
        this.group = group;
    }

    public TextField getNameField() {
        return nameField;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    public TextField getAuthorField() {
        return authorField;
    }

    public void setAuthorField(TextField authorField) {
        this.authorField = authorField;
    }

    public TextField getAisleField() {
        return aisleField;
    }

    public void setAisleField(TextField aisleField) {
        this.aisleField = aisleField;
    }

    public TextField getShelfField() {
        return shelfField;
    }

    public void setShelfField(TextField shelfField) {
        this.shelfField = shelfField;
    }

    public TextField getQuantityField() {
        return quantityField;
    }

    public void setQuantityField(TextField quantityField) {
        this.quantityField = quantityField;
    }

    public TextField getUniqueField() {
        return uniqueField;
    }

    public void setUniqueField(TextField uniqueField) {
        this.uniqueField = uniqueField;
    }

    public TextField getUnique2Field() {
        return unique2Field;
    }

    public void setUnique2Field(TextField unique2Field) {
        this.unique2Field = unique2Field;
    }
}
