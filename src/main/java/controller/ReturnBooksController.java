package controller;

import books.Book;
import com.main.LibrarySystem;
import data.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ReturnBooksController {

    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book, String> idColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> categoryColumn;
    @FXML
    private TableColumn<Book, Integer> stockColumn;
    @FXML
    private TextField bookIdField;
    @FXML
    private Label messageLabel;
    @FXML
    private Button submitButton;
    @FXML
    private Button backButton;

    private Student student;
    private Stage stage;

    @FXML
    public void initialize() {
        // Set up table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        // Event handler for submit button
        submitButton.setOnAction(event -> handleSubmit());

        // Event handler for back button
        backButton.setOnAction(event -> handleBack());
    }

    public void setStudent(Student student) {
        this.student = student;
        filterAndDisplayBorrowedBooks(); // Panggil method untuk memfilter dan menampilkan data setelah student di set
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleSubmit() {
        String idBukuYangDikembalikan = bookIdField.getText();
        boolean bukuDikembalikan = false;

        for (Book book : Book.arr_borrowedBook) {
            if (book.getBookId().equals(idBukuYangDikembalikan)) {
                // Lakukan logika pengembalian buku, seperti menambahkan stok kembali
                book.setStock(book.getStock() + 1);
                book.setBorrower(null); // Hapus informasi peminjam
                Book.arr_borrowedBook.remove(book); // Hapus buku dari daftar yang sedang dipinjam
                bukuDikembalikan = true;
                messageLabel.setText("Buku berhasil dikembalikan.");
                break;
            }
        }

        if (!bukuDikembalikan) {
            messageLabel.setText("ID buku tidak ditemukan.");
        }
        messageLabel.setVisible(true);
    }

    @FXML
    private void handleBack() {
        if (stage != null) {
            stage.close();
        }
    }

    // Helper method to filter and display borrowed books
    private void filterAndDisplayBorrowedBooks() {
        tableView.getItems().clear();
        System.out.println("Borrowed books list size: " + Book.arr_borrowedBook.size());
        for (Book borrowedBook : Book.arr_borrowedBook) {
            System.out.println("Checking book: " + borrowedBook.getTitle() + " borrowed by: " + borrowedBook.getBorrower());
            // Check if the book is borrowed by the current student
            if (borrowedBook.getBorrower() != null &&
                    borrowedBook.getBorrower().equals(LibrarySystem.NIM)) {
                tableView.getItems().add(borrowedBook);
                System.out.println("Added book: " + borrowedBook.getTitle());
            }
        }
    }
}
