package view;

import control.LibraryControl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.person.Customer;
import model.person.Employee;
import model.person.Librarian;
import model.person.Manager;
import model.product.Location;
import model.product.TextBook;
import view.librarian.ProductManagementPane;
import view.manager.PersonManagementPane;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.IntStream;

import static control.LibraryControl.ProductType.*;
import static control.LibraryControl.Role;
import static utils.Base.*;

public class Main extends Application {

    private LibraryControl libraryControl;
    private Stage loginStage;
    private Stage managerStage;
    private Stage librarianStage;
    private LoginPane loginPane;
    private PersonManagementPane managerPane;
    private ProductManagementPane librarianPane;

    public static final Employee yagelLibrarian = new Librarian("311301493","Yagel Abbou", "yagel241@gmail.com",
            "0525757441", "123456789");

    public static final Employee yagelManager = new Manager("123456789","Yagel Abbou", "yagel241@gmail.com",
            "0525757441", "987654321");

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        this.libraryControl = new LibraryControl();
        this.libraryControl.getEmployeeControl().getEmployees().add(yagelManager);
        this.libraryControl.getEmployeeControl().getEmployees().add(yagelLibrarian);
        this.initLoginStage();
        this.initManagerStage();
        this.initLibrarianStage();
        this.handleLogin();
        this.handleLogout();
        this.initData();
    }

    private void handleLogin() {
        //TODO for debugging
        this.loginPane.getIdField().setText(yagelLibrarian.getId());
        this.loginPane.getEmailField().setText(yagelLibrarian.getEmail());
        this.loginPane.getRoleSelection().getSelectionModel().select(Role.LIBRARIAN);

//        this.loginPane.getIdField().setText(yagelManager.getId());
//        this.loginPane.getEmailField().setText(yagelManager.getEmail());
//        this.loginPane.getRoleSelection().getSelectionModel().select(Role.MANAGER);


        this.loginPane.getLoginBtn().setOnMouseClicked(e -> {
            Employee employee = this.libraryControl.getEmployeeControl().findEmployeeById_Email_Role(
                    this.loginPane.getIdField().getText(), this.loginPane.getEmailField().getText(),
                    this.loginPane.getRoleSelection().getSelectionModel().getSelectedItem());
            if (employee != null) {
                if (employee.getRole() == Role.MANAGER) {
                    this.managerPane.setLoggedOnManager((Manager) employee);
                    this.loginPane.clearInputData();
                    this.loginStage.close();
                    this.managerStage.show();
                } else if (employee.getRole() == Role.LIBRARIAN) {
                    //TODO change to Librarian!
                    this.librarianPane.setLoggedOnLibrarian((Librarian) employee);
                    this.loginPane.clearInputData();
                    this.loginStage.close();
                    this.librarianStage.show();
                }
            } else {
                showAlert(AlertType.WARNING, "Invalid Credentials!");
            }
        });
    }

    private void handleLogout() {
       this.librarianStage.setOnCloseRequest(e -> {
           this.librarianPane.setLoggedOnLibrarian(null);
           this.loginStage.show();
       });
        this.managerStage.setOnCloseRequest(e -> {
            this.managerPane.setLoggedOnManager(null);
            this.loginStage.show();
        });
    }

    private void initLoginStage() {
        this.loginPane = SingletonLoginPane.getInstance();
        this.loginStage = new Stage();
        this.loginStage.setScene(new Scene(this.loginPane, 400, 200));
        this.loginStage.setTitle("SifriYagel - Login");
        this.loginStage.setResizable(false);
        this.loginStage.show();
    }

    private void initManagerStage() {
        this.managerPane = new PersonManagementPane(this.libraryControl);
        this.managerStage = new Stage();
        this.managerStage.setScene(new Scene(this.managerPane, WIDTH, HEIGHT));
        this.managerStage.setMinHeight(STAGE_MIN_HEIGHT);
        this.managerStage.setMinWidth(STAGE_MIN_WIDTH);
        this.managerStage.setTitle("SifriYagel - Management");
        this.managerStage.setResizable(true);
    }

    private void initLibrarianStage() {
        this.librarianPane = new ProductManagementPane(this.libraryControl);
        this.librarianStage = new Stage();
        this.librarianStage.setScene(new Scene(this.librarianPane, WIDTH, HEIGHT));
        this.librarianStage.setMinHeight(HEIGHT);
        this.librarianStage.setMinWidth(STAGE_MIN_WIDTH);
        this.librarianStage.setTitle("SifriYagel - Products");
        this.librarianStage.setResizable(true);
    }

    private void initData() {
        initPeople();
        initProducts();
    }

    private void initProducts() {
        IntStream.range(0, 10)
                .forEach(i -> this.libraryControl.addNewProduct(
                        yagelLibrarian.getId(), yagelLibrarian.getEmployeeId(), random(), random(), r(), r(), "10", COMICS, "1", "1"));
        IntStream.range(0, 10)
                .forEach(i -> this.libraryControl.addNewProduct(
                        yagelLibrarian.getId(), yagelLibrarian.getEmployeeId(), random(), random(), r(), r(), "10", MOVIE, "1", "1"));
        IntStream.range(0, 10)
                .forEach(i -> this.libraryControl.addNewProduct(
                        yagelLibrarian.getId(), yagelLibrarian.getEmployeeId(), random(), random(), r(), r(), "10", TEXT_BOOK, "1", "1"));
    }

    private void initPeople() {
        IntStream.range(0, 10)
                .forEach(i -> this.libraryControl.addNewEmployee(
                        yagelManager.getId(),yagelManager.getEmployeeId(), r(), random(), random() + "a@a.a", r() + "1", true));
        IntStream.range(0, 10)
                .forEach(i -> this.libraryControl.addNewEmployee(
                        yagelManager.getId(),yagelManager.getEmployeeId(), r(), random(), random() + "a@a.a", r() + "1", false));
        IntStream.range(0, 10)
                .forEach(i -> this.libraryControl.addNewCustomer(
                        yagelManager.getId(),yagelManager.getEmployeeId(), r(), random(), random() + "a@a.a", r() + "1"));
        Customer c = this.libraryControl.getCustomerControl().getCustomers().iterator().next();
        System.out.println(c.getId());
        Map<String, Object> m = new TreeMap<>();
        m.put("field", "aa");
        m.put("grade", "10");
        IntStream.range(0, 1)
                .forEach(i -> this.libraryControl.getProductControl().getProducts().add(new TextBook("a", "aabbcc", "aabbcc",5, new Location(1, 1), m)));

        IntStream.range(0, 1)
                .forEach(i -> c.borrow(new TextBook("a", "aabbcc", "aabbcc",5, new Location(1, 1), m)));
    }

    private String random() {
        Random r = new Random();
        String[] s = "abcdefghijklmnopq".split("");
        return String.join(s[r.nextInt(s.length)], s[r.nextInt(s.length)], s[r.nextInt(s.length)],
                s[r.nextInt(s.length)], s[r.nextInt(s.length)], s[r.nextInt(s.length)]);
    }

    private String r() {
        Random r = new Random();
        String[] s = "1234567890".split("");
        return String.join(s[r.nextInt(s.length)], s[r.nextInt(s.length)],
                s[r.nextInt(s.length)], s[r.nextInt(s.length)],
                s[r.nextInt(s.length)], s[r.nextInt(s.length)]);
    }

}
