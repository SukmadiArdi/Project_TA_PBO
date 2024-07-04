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

    @FXML
    public void initialize() {
        // Set up table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        // Event handler for submit button
        submitButton.setOnAction(_ -> handleSubmit());

        // Event handler for back button
        backButton.setOnAction(_ -> handleBack());
    }

    public void setStudent(Student student) {
        this.student = student;
        Book.filterAndDisplayBorrowedBooks(tableView, LibrarySystem.NIM);
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
        if (student != null) {
            student.menu();
        }
        backButton.getScene().getWindow().hide();
    }

}
