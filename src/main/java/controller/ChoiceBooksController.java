package controller;

import books.Book;
import com.main.LibrarySystem;
import data.Student;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ChoiceBooksController {

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
    private TextField durationField;
    @FXML
    private Label messageLabel;
    @FXML
    private Button submitButton;
    @FXML
    private Button backButton;


    private Student studentObj = new Student();


    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        for (Book book : Book.arr_bookList) {
            tableView.getItems().add(book);
        }
        // Event handler for submit button
        submitButton.setOnAction(_ -> handleSubmit());

        // Event handler for back button
        backButton.setOnAction(_ -> handleBack());
    }


    @FXML
    public void handleSubmit() {
        boolean validasi = false;
        String idBukuYangDipinjam = bookIdField.getText();
        int inputWaktuPinjaman;

        try {
            inputWaktuPinjaman = Integer.parseInt(durationField.getText());
        } catch (NumberFormatException ex) {
            messageLabel.setText("Durasi harus berupa angka.");
            messageLabel.setVisible(true);
            return;
        }

        for (Book book : Book.arr_bookList) {
            if (book.getBookId().equals(idBukuYangDipinjam)) {
                if (book.getStock() > 0) {
                    if (inputWaktuPinjaman < 15 && inputWaktuPinjaman > 0) {
                        book.setStock(book.getStock() - 1);
                        book.setDuration(inputWaktuPinjaman);
                        book.setBorrower(LibrarySystem.NIM);
                        Book.arr_borrowedBook.add(book);
                        validasi = true;
                        break;
                    } else {
                        messageLabel.setText("Durasi peminjaman harus antara 1-14 hari.");
                        messageLabel.setVisible(true);
                        return;
                    }
                } else {
                    messageLabel.setText("== Stok buku habis! ==");
                    return;
                }
            }
        }

        if (validasi) {
            messageLabel.setText("==== Buku berhasil dipinjam! ====");
            messageLabel.setVisible(true);
        } else {
            messageLabel.setText("== ID tidak ditemukan! ==");
            messageLabel.setVisible(true);
        }
    }

    @FXML
    public void handleBack() {
        studentObj.menu();
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    // Metode menu() yang ada di kelas User
    public void menu() {
        System.out.println("Menu User");
        // Atau Anda bisa tambahkan logika lain yang sesuai di sini
    }
}
