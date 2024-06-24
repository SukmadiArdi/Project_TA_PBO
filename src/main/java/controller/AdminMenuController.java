package controller;

import com.main.LibrarySystem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import data.Admin;
import javafx.stage.Stage;

public class AdminMenuController {

    @FXML
    private Button addStudentButton;
    @FXML
    private Button displayStudentButton;
    @FXML
    private Button addBookButton;
    @FXML
    private Button logoutButton;

    private Admin admin;

    public void initialize(){
        admin = LibrarySystem.getInstance().getAdmin(); // Get the Admin instance from LibrarySystem
    }

    @FXML
    private void addStudent() {
        admin.addStudent();
    }

    @FXML
    private void displayStudent() {
        admin.displayStudent();
    }

    @FXML
    private void inputBook() {
        admin.inputBook();
    }

    @FXML
    private void logout() throws Exception {
        LibrarySystem.getInstance().start(new Stage());
        logoutButton.getScene().getWindow().hide();
    }
}
