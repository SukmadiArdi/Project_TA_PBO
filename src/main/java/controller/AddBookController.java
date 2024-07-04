package controller;

import data.Admin;
import books.Book;
import data.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookController {
    @FXML
    private Label sceneTitleLabel;
    @FXML
    private TextField bookIdField;
    @FXML
    private TextField bookTitleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField stockField;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private Button submitButton;
    @FXML
    private Button backButton;

    private Admin admin;
    private String category;

    public void setData(Admin admin, String category, String stageTitle, String sceneTitle) {
        this.admin = admin;
        this.category = category;
        bookIdField.setText(admin.generateId());
        sceneTitleLabel.setText(sceneTitle);

    }

    @FXML
    public void initialize() {
        submitButton.setOnAction(event -> {
            try {
                int stock = Integer.parseInt(stockField.getText());

                // Validasi input
                if (bookTitleField.getText().isEmpty() || authorField.getText().isEmpty() || stock < 0) {
                    errorMessageLabel.setText("Semua field harus diisi dan stok harus berupa angka positif.");
                    errorMessageLabel.setVisible(true);
                    return; // Hentikan proses jika validasi gagal
                }

                // Jika validasi berhasil, tambahkan buku ke array
                Book newBook = new Book();
                newBook.setBookId(bookIdField.getText());
                newBook.setTitle(bookTitleField.getText());
                newBook.setCategory(category);
                newBook.setAuthor(authorField.getText());
                newBook.setStock(stock);

                Book.arr_bookList.add(newBook);

                admin.menu(); // Pindah ke menu admin setelah berhasil menambah buku

                // Tutup stage saat ini
                Stage stage = (Stage) submitButton.getScene().getWindow();
                stage.close();

            } catch (NumberFormatException e) {
                errorMessageLabel.setText("Stok harus berupa angka.");
                errorMessageLabel.setVisible(true);
            }
        });

        backButton.setOnAction(event -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
            User user = new User();
            user.inputBook();
        });
    }
}
