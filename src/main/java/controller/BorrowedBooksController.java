package controller;

import com.main.LibrarySystem;
import data.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import books.Book;

import java.net.URL;
import java.util.ResourceBundle;

public class BorrowedBooksController implements Initializable {

    @FXML
    private Label sceneTitle;
    @FXML
    private TableView<Book> table;
    @FXML
    private TableColumn<Book, String> idColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> categoryColumn;
    @FXML
    private TableColumn<Book, Integer> durationColumn;
    @FXML
    private Button backButton;

    private Student student;

    public void initialize(Student student) {
        this.student = student;

        sceneTitle.setText("Daftar Buku yang Dipinjam");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        studentTable.setItems(Student.arr_userStudent);
        // Filter and display borrowed books for the current student
        filterAndDisplayBorrowedBooks();

        backButton.setOnAction(event -> {
            student.menu();
            backButton.getScene().getWindow().hide();
        });
    }

    // Helper method to filter and display borrowed books
    private void filterAndDisplayBorrowedBooks() {
        table.getItems().clear(); // Clear previous items
        for (Book borrowedBook : Book.arr_borrowedBook) {
            if (borrowedBook.borrower != null && borrowedBook.borrower.equals(LibrarySystem.NIM)) {
                table.getItems().add(borrowedBook); // Add only if borrowed by the current student
            }
        }
    }
}
