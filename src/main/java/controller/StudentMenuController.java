package controller;

import com.main.LibrarySystem;
import data.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentMenuController {
    @FXML
    private Button borrowedBookButton;
    @FXML
    private Button borrowBookButton;
    @FXML
    private Button returnBookButton;

    private Student student;

    public void initialize(Student student) {
        this.student = student;
    }

    @FXML
    private void handleBorrowedBookButton() throws IOException {
        student.showBorrowedBooks();
        Stage stage = (Stage) borrowedBookButton.getScene().getWindow();
        stage.close(); // Tutup stage dengan benar
    }

    @FXML
    private void handleBorrowBookButton() throws IOException {
        student.choiceBooks();
        Stage stage = (Stage) borrowBookButton.getScene().getWindow();
        stage.close(); // Tutup stage dengan benar
    }

    @FXML
    private void handleReturnBookButton() throws IOException {
        student.returnBooks();
        Stage stage = (Stage) returnBookButton.getScene().getWindow();
        stage.close(); // Tutup stage dengan benar
    }
    @FXML
    private void handlereturnBotton() throws IOException {
        // 1. Get the LibrarySystem instance (assuming it's a Singleton)
        LibrarySystem librarySystem = LibrarySystem.getInstance();

        // 2. Close the current student menu stage
        Stage currentStage = (Stage) returnBookButton.getScene().getWindow(); // Use any button for reference
        currentStage.close();

        // 3. Check if the loginStage is open. If it is, just show the mainStage
        if (librarySystem.loginStage != null && librarySystem.loginStage.isShowing()) {
            librarySystem.mainStage.show();
        } else { // Otherwise, create a new main menu stage
            // 4. Create a new main menu scene and stage
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/main_menu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
    }
}
