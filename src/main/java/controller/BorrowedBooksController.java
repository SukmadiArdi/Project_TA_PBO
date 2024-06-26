package controller;

import books.Book;
import com.main.LibrarySystem;
import data.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BorrowedBooksController {

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

    @FXML
    public void initialize() {
        // Set up table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        // Title of the scene
        sceneTitle.setText("Daftar Buku yang Dipinjam");

        // Event handler for back button
        backButton.setOnAction(event -> {
            if (student != null) {
                student.menu();
            }
            backButton.getScene().getWindow().hide();
        });
    }

    public void setStudent(Student student) {
        this.student = student;
        filterAndDisplayBorrowedBooks(); // Panggil method untuk memfilter dan menampilkan data setelah student di set
    }

    // Helper method to filter and display borrowed books
    private void filterAndDisplayBorrowedBooks() {
        table.getItems().clear();
        System.out.println("Borrowed books list size: " + Book.arr_borrowedBook.size());
        for (Book borrowedBook : Book.arr_borrowedBook) {
            System.out.println("Checking book: " + borrowedBook.getTitle() + " borrowed by: " + borrowedBook.getBorrower());
            // Check if the book is borrowed by the current student
            if (borrowedBook.getBorrower() != null &&
                    borrowedBook.getBorrower().equals(LibrarySystem.NIM)) {
                table.getItems().add(borrowedBook);
                System.out.println("Added book: " + borrowedBook.getTitle());
            }
        }
    }
}
