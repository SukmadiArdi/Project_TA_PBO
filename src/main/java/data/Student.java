package data;


import Util.iMenu;
import books.Book;
import com.main.LibrarySystem;
import controller.BorrowedBooksController;
import controller.StudentMenuController;
import exception.custom.IllegalAdminAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Student extends User implements iMenu {
    public static ObservableList<UserStudent> arr_userStudent = FXCollections.observableArrayList();

    //Konstruktor untuk arraylist arr_userStudent.
    public static class UserStudent {
        String nama, nim, fakultas, prodi;

        public UserStudent(String nama, String nim, String fakultas, String prodi) {
            this.nama = nama;
            this.nim = nim;
            this.fakultas = fakultas;
            this.prodi = prodi;
        }

        public String getNama() {
            return nama;
        }

        public String getNim() {
            return nim;
        }

        public String getFakultas() {
            return fakultas;
        }

        public String getProdi() {
            return prodi;
        }
    }

    @Override
    public void menu() {
        try {
            Stage studentMenuStage = new Stage();
            studentMenuStage.setTitle("UMM Library - Student Menu");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/student_menu.fxml"));
            Parent root = loader.load();

            StudentMenuController controller = loader.getController();
            controller.initialize(this); // Inisialisasi controller dengan objek Student ini

            Scene studentMenuScene = new Scene(root, 1360, 720);
            studentMenuScene.getStylesheets().add(Objects.requireNonNull(LibrarySystem.class.getResource("/Css/style.css")).toExternalForm());
            studentMenuStage.setScene(studentMenuScene);
            studentMenuStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void choiceBooks() {
        super.choiceBooks();
    }

    public static void showBorrowedBooks() {
        try {
            Stage showBorrowedBooksStage = new Stage();
            showBorrowedBooksStage.setTitle("Informasi Buku Yang Dipinjam");

            FXMLLoader loader = new FXMLLoader(Student.class.getResource("/Fxml/borrowed_books.fxml"));
            Parent root = loader.load();

            BorrowedBooksController controller = loader.getController();
            controller.initialize(LibrarySystem.getInstance().getStudent()); // Inisialisasi controller dengan objek Student

            Scene scene = new Scene(root, 600, 400);
            scene.getStylesheets().add(Objects.requireNonNull(Student.class.getResource("/Css/style.css")).toExternalForm());
            showBorrowedBooksStage.setScene(scene);
            showBorrowedBooksStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void returnBooks() {
        Stage returnBooksStage = new Stage();
        returnBooksStage.setTitle("UMM Library - Pengembalian buku");

        // Label
        Label headerTitle = new Label("Pengembalian buku");
        headerTitle.getStyleClass().add("scene-title");
        Label bookIdLabel = new Label("Inputkan ID buku yang ingin dikembalikan:");
        bookIdLabel.getStyleClass().add("form-label");

        // Notification Label
        Label submitSuccessLabel = new Label("Pengembalian berhasil");
        submitSuccessLabel.getStyleClass().add("success-message");
        Label submitFailedLabel = new Label("Pengembalian gagal");
        submitFailedLabel.getStyleClass().add("error-message");

        submitSuccessLabel.setVisible(false);
        submitFailedLabel.setVisible(false);

        // Field
        TextField bookIdField = new TextField();
        bookIdField.getStyleClass().add("form-field");

        // Button
        Button submitButton = new Button("Kembalikan");
        submitButton.getStyleClass().add("login-button");
        Button returnButton = new Button("Kembali");
        returnButton.getStyleClass().add("login-button");

        // Table View
        TableView<Book> tableView = new TableView<>();
        tableView.getStyleClass().add("table-view");

        TableColumn<Book, String> idBookColumn = new TableColumn<>("ID Buku");
        idBookColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        idBookColumn.setPrefWidth(100);

        TableColumn<Book, String> titleBookColumn = new TableColumn<>("Judul");
        titleBookColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleBookColumn.setPrefWidth(200);

        TableColumn<Book, String> authorBookColumn = new TableColumn<>("Penulis"); // Correct variable name
        authorBookColumn.setCellValueFactory(new PropertyValueFactory<>("author")); // Correct variable name
        authorBookColumn.setPrefWidth(150);

        TableColumn<Book, String> categoryBookColumn = new TableColumn<>("Kategori"); // Correct variable name
        categoryBookColumn.setCellValueFactory(new PropertyValueFactory<>("category")); // Correct variable name
        categoryBookColumn.setPrefWidth(100);

        TableColumn<Book, String> durationBookColumn = new TableColumn<>("Durasi Pinjaman (Hari)");
        durationBookColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        durationBookColumn.setPrefWidth(150);


        tableView.getColumns().add(idBookColumn);
        tableView.getColumns().add(titleBookColumn);
        tableView.getColumns().add(authorBookColumn);  // Use corrected variable name
        tableView.getColumns().add(categoryBookColumn); // Use corrected variable name
        tableView.getColumns().add(durationBookColumn);

        // Filter and display borrowed books for the current student
        filterAndDisplayBorrowedBooks(tableView);

        // Grid layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(headerTitle, 0, 0, 2, 1); // Span 2 kolom untuk judul
        grid.add(tableView, 0, 1, 2, 1);   // Span 2 kolom untuk tabel
        grid.add(bookIdLabel, 0, 2);
        grid.add(bookIdField, 1, 2);
        grid.add(submitSuccessLabel, 0, 3);
        grid.add(submitFailedLabel, 1, 3);
        grid.add(submitButton, 0, 4);
        grid.add(returnButton, 1, 4);

        Scene scene = new Scene(grid, 600, 400); // Sesuaikan ukuran scene
        scene.getStylesheets().add(Objects.requireNonNull(Student.class.getResource("/Css/style.css")).toExternalForm());
        returnBooksStage.setScene(scene);
        returnBooksStage.show();

        // Action button
        submitButton.setOnAction(event -> {
            String bookId = bookIdField.getText().trim(); // Hapus spasi berlebih

            // Validasi input
            if (bookId.isEmpty()) {
                submitFailedLabel.setText("Masukkan ID buku yang valid.");
                submitFailedLabel.setVisible(true);
                submitSuccessLabel.setVisible(false);
                return;
            }

            boolean found = false;
            for (int i = 0; i < Book.arr_borrowedBook.size(); i++) {
                Book borrowedBook = Book.arr_borrowedBook.get(i);
                if (borrowedBook.borrower != null && borrowedBook.getBookId().equals(bookId) && borrowedBook.borrower.equals(LibrarySystem.NIM)) {
                    // Iterasi pada arr_bookList
                    for (Book book : Book.arr_bookList) {
                        if (book.getBookId().equals(bookId)) {
                            book.setStock(book.getStock() + 1);
                            Book.arr_borrowedBook.remove(i);
                            found = true;
                            book.setBorrower(null);
                            break; // Keluar dari loop dalam setelah buku ditemukan
                        }
                    }
                    if (found) {
                        break; // Keluar dari loop luar setelah buku dikembalikan
                    }
                }
            }

            // Refresh the table view
            filterAndDisplayBorrowedBooks(tableView);

            if (found) {
                submitSuccessLabel.setVisible(true);
                submitFailedLabel.setVisible(false);
            } else {
                submitFailedLabel.setText("Buku tidak ditemukan atau bukan milik Anda.");
                submitFailedLabel.setVisible(true);
                submitSuccessLabel.setVisible(false);
            }
        });

        // Action Button Kembali
        returnButton.setOnAction(event -> {
            LibrarySystem mainApp = LibrarySystem.getInstance();
            if (mainApp != null) {
                Student studentObj = mainApp.getStudent();
                if (studentObj != null) {
                    studentObj.menu();
                }
            }
            returnBooksStage.close();
        });
    }

    // Helper method to filter and display borrowed books
    private static void filterAndDisplayBorrowedBooks(TableView<Book> tableView) {
        tableView.getItems().clear(); // Clear previous items
        for (Book borrowedBook : Book.arr_borrowedBook) {
            if (borrowedBook.borrower != null && borrowedBook.borrower.equals(LibrarySystem.NIM)) {
                tableView.getItems().add(borrowedBook); // Add only if borrowed by the current student
            }
        }
    }


    public boolean isStudents(TextField username) throws IllegalAdminAccess {
        if (username.getText().length() != 15) {
            throw new IllegalAdminAccess("NIM harus 15 digit!");
        }
        for (UserStudent i : arr_userStudent) {
            if (i.nim.equals(username.getText())) {
                menu();
                return false;
            }
        }
        throw new IllegalAdminAccess("NIM tidak ditemukan!");
    }
}