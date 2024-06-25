package controller;

import data.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
        borrowedBookButton.getScene().getWindow().hide();
    }

    @FXML
    private void handleBorrowBookButton() throws IOException {
        student.choiceBooks();
        borrowBookButton.getScene().getWindow().hide();
    }

    @FXML
    private void handleReturnBookButton() throws IOException {
        student.returnBooks();
        returnBookButton.getScene().getWindow().hide();
    }
}
