package view;

import control.LibraryControl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.person.Employee;
import model.person.Librarian;
import model.person.Manager;
import view.librarian.ProductManagementPane;
import view.manager.PersonManagementPane;

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

    public static final Employee yagelLibrarian = new Librarian("311301493","Yagel Abbou", "yagelLibrarian@gmail.com",
            "0525757441", "123456789");

    public static final Employee yagelManager = new Manager("123456789","Yagel Abbou", "yagelManager@gmail.com",
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
        this.libraryControl.addNewProduct(yagelLibrarian.getId(), yagelLibrarian.getEmployeeId(), "Harry Potter and The Sorcerer's Stone", "JK Rolling", "1", "1", "10", MOVIE, "Science Fiction", "2001");
        this.libraryControl.addNewProduct(yagelLibrarian.getId(), yagelLibrarian.getEmployeeId(), "Harry Potter and The Chamber Of Secrets", "JK Rolling", "1", "2", "10", MOVIE, "Science Fiction", "2002");
        this.libraryControl.addNewProduct(yagelLibrarian.getId(), yagelLibrarian.getEmployeeId(), "Harry Potter and The Prisoner Of Azkaban", "JK Rolling", "1", "3", "10", MOVIE, "Science Fiction", "2004");
        this.libraryControl.addNewProduct(yagelLibrarian.getId(), yagelLibrarian.getEmployeeId(), "Iron Man Extremis", "Warren Ellis", "2", "1", "10", COMICS, "Marvel", "1");
        this.libraryControl.addNewProduct(yagelLibrarian.getId(), yagelLibrarian.getEmployeeId(), "The Avengers", "Stan Lee", "2", "2", "10", COMICS, "Marvel", "1");
        this.libraryControl.addNewProduct(yagelLibrarian.getId(), yagelLibrarian.getEmployeeId(), "Spider Man", "Stan Lee", "2", "3", "10", COMICS, "Marvel", "15");
        this.libraryControl.addNewProduct(yagelLibrarian.getId(), yagelLibrarian.getEmployeeId(), "Bli Sodot", "Naomi Limor", "3", "1", "10", TEXT_BOOK, "Language", "1");
        this.libraryControl.addNewProduct(yagelLibrarian.getId(), yagelLibrarian.getEmployeeId(), "Theory Of Electricity", "Dr Yaakov Gal", "3", "2", "10", TEXT_BOOK, "Electronics", "13");
        this.libraryControl.addNewProduct(yagelLibrarian.getId(), yagelLibrarian.getEmployeeId(), "Algebra", "Beni Goren", "3", "3", "10", TEXT_BOOK, "Math", "7");
        }

    private void initPeople() {
this.libraryControl.addNewEmployee(yagelManager.getId(),yagelManager.getEmployeeId(),"308145390", "Orel Hadad", "orel.Manager@gmail.com", "0546838282", true);
this.libraryControl.addNewEmployee(yagelManager.getId(),yagelManager.getEmployeeId(),"987654321", "Orel Hadad", "orel.Librarian@gmail.com" , "0546838282", false);
this.libraryControl.addNewCustomer(yagelManager.getId(),yagelManager.getEmployeeId(),"170263236", "Michael Jordan", "TheBestEver@gmail.com", "0549193968");
    }
}
