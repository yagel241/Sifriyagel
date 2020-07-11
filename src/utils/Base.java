package utils;

import javafx.scene.control.Alert;

import java.util.Collection;
import java.util.regex.Pattern;

public class Base {

    public static int WIDTH=800, HEIGHT =800, PAD=30, SPACE= 15, STAGE_MIN_HEIGHT =300,
            STAGE_MIN_WIDTH=700, BUTTON_SIZE =30, BOTTOM_TABLE_HEIGHT=200;


    public static <E> E addNotNull(Collection<E> collection, E e) {
        if (e != null) {
            collection.add(e);
        }
        return e;
    }

    public static boolean isId(String id) {
        return Pattern.matches("[0-9]{9}", id);
    }

    public static boolean isPositiveInteger(String number) {
        return Pattern.matches("[0-9]+", number);
    }

    public static boolean isName(String name) {
         return Pattern.matches("[a-zA-Z][a-zA-Z ]*", name);
    }

    public static boolean isEmail(String email) {
        return Pattern.matches("^(.+)@(.+)$", email);
    }

    public static boolean isPhoneNumber(String phoneNumber) {
        return Pattern.matches("[0-9]{10}", phoneNumber);
    }

    public static boolean areParametersValid(String id, String name, String email, String phoneNumber) {
        return isId(id) && isName(name) && isEmail(email) && isPhoneNumber(phoneNumber);
    }

    public static boolean areProductParametersValid(String author, String aisle, String shelf, String quantity) {
        return isName(author) && isPositiveInteger(quantity) && isPositiveInteger(aisle) && isPositiveInteger(shelf);
    }

    public static void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type, message);
        alert.showAndWait();
    }

}
