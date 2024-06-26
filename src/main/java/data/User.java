package data;

import books.Book;
import books.HistoryBook;
import books.StoryBook;
import books.TextBook;
import com.main.LibrarySystem;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class User {

    //===================================== ATRIBUT ======================================


    //====================================== METHOD =======================================

    public void menu() {
        // Implementasi dasar menu (bisa kosong atau berisi logika umum)
        System.out.println("Menu User");
    }

    //Method yang digunakan untuk meminjam buku
    public void choiceBooks() {
        Book bookObj = new Book();
        Student studentObj = new Student();

        Stage choiceBooksStage = new Stage();
        choiceBooksStage.setTitle("UMM library - Pilih Buku");

        TableView<Book> tableView = new TableView<>();
        tableView.getStyleClass().add("table-view"); // Tambahkan style class untuk tabel

        // Kolom-kolom tabel (dengan lebar yang disesuaikan)
        TableColumn<Book, String> idColumn = new TableColumn<>("ID Buku");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        idColumn.setPrefWidth(100); // Atur lebar kolom ID Buku

        TableColumn<Book, String> titleColumn = new TableColumn<>("Nama Buku");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setPrefWidth(200); // Atur lebar kolom Nama Buku

        TableColumn<Book, String> authorColumn = new TableColumn<>("Penulis");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        authorColumn.setPrefWidth(150); // Atur lebar kolom Penulis

        TableColumn<Book, String> categoryColumn = new TableColumn<>("Kategori");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryColumn.setPrefWidth(100); // Atur lebar kolom Kategori

        TableColumn<Book, Integer> stockColumn = new TableColumn<>("Stok");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        stockColumn.setPrefWidth(50);  // Atur lebar kolom Stok

        tableView.getColumns().addAll(idColumn, titleColumn, authorColumn, categoryColumn, stockColumn);

        for (Book i : Book.arr_bookList) {
            tableView.getItems().add(i);
        }

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(tableView, 0, 0, 2, 1); // Span 2 kolom untuk tabel

        Label bookIdLabel = new Label("Input ID buku yang ingin dipinjam:");
        bookIdLabel.getStyleClass().add("form-label");
        grid.add(bookIdLabel, 0, 1);

        TextField bookIdField = new TextField();
        bookIdField.getStyleClass().add("form-field");
        grid.add(bookIdField, 1, 1);

        Label durationLabel = new Label("Berapa hari ingin meminjam buku? (Max 14 hari)");
        durationLabel.getStyleClass().add("form-label");
        grid.add(durationLabel, 0, 2);

        TextField durationField = new TextField();
        durationField.setPromptText("Berapa hari?");
        durationField.getStyleClass().add("form-field");
        grid.add(durationField, 1, 2);

        Button submitButton = new Button("Submit");
        submitButton.getStyleClass().add("login-button");
        grid.add(submitButton, 0, 3, 2, 1); // Span 2 kolom untuk tombol

        Label messageLabel = new Label();
        messageLabel.getStyleClass().add("error-message"); // Tambahkan style class
        grid.add(messageLabel, 0, 4, 2, 1); // Span 2 kolom untuk pesan

        // Tombol Kembali
        Button backButton = new Button("Kembali");
        backButton.getStyleClass().add("login-button");
        grid.add(backButton, 0, 5, 2, 1); // Span 2 kolom untuk tombol kembali

        Scene scene = new Scene(grid, 600, 400); // Sesuaikan ukuran scene
        scene.getStylesheets().add(getClass().getResource("/Css/style.css").toExternalForm());
        choiceBooksStage.setScene(scene);
        choiceBooksStage.show();

        submitButton.setOnAction(e -> {
            boolean validasi = false;
            String idBukuYangDipinjam = bookIdField.getText();
            int inputWaktuPinjaman = 0; // Inisialisasi inputWaktuPinjaman

            try {
                inputWaktuPinjaman = Integer.parseInt(durationField.getText());
            } catch (NumberFormatException ex) {
                messageLabel.setText("Durasi harus berupa angka.");
                messageLabel.setVisible(true);
                return; // Keluar dari event handler jika input tidak valid
            }

            for (Book book : Book.arr_bookList) {
                if (book.getBookId().equals(idBukuYangDipinjam)) {
                    if (book.getStock() > 0) {
                        if (inputWaktuPinjaman < 15 && inputWaktuPinjaman > 0) { // Validasi durasi peminjaman
                            book.setStock(book.getStock() - 1);
                            book.setDuration(inputWaktuPinjaman);
                            book.setBorrower(LibrarySystem.NIM); // Set borrower
                            Book.arr_borrowedBook.add(book); // Tambahkan buku yang sudah diupdate
                            validasi = true;
                            break;
                        } else {
                            messageLabel.setText("Durasi peminjaman harus antara 1-14 hari.");
                            messageLabel.setVisible(true);
                            return; // Keluar dari event handler jika input tidak valid
                        }
                    } else {
                        messageLabel.setText("== Stok buku habis! ==");
                        menu(); // Kembali ke menu mahasiswa jika stok habis
                        return; // Keluar dari event handler
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
        });

        // Action Button Kembali
        backButton.setOnAction(event -> {
            studentObj.menu(); // Panggil metode menu dari objek Student
            choiceBooksStage.close(); // Tutup stage pemilihan buku
        });
    }

    public void inputBook() {
        TextBook textBookObj = new TextBook();
        StoryBook storyBookObj = new StoryBook();
        HistoryBook historyBookObj = new HistoryBook();

        Stage inputBookStage = new Stage();
        inputBookStage.setTitle("UMM Library - Input Book");

        // Label
        Label sceneTitle = new Label("Tambah Buku");
        sceneTitle.getStyleClass().add("scene-title"); // Tambahkan style class
        Label chooseBook = new Label("Pilih kategori buku:");
        chooseBook.getStyleClass().add("form-label");

        // Buttons
        Button historyBookButton = new Button("History Book");
        Button storyBookButton = new Button("Story Book");
        Button textBookButton = new Button("Text Book");

        // Tombol Kembali
        Button backButton = new Button("Kembali");
        backButton.getStyleClass().add("login-button");

        // Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(sceneTitle, 0, 0, 2, 1);
        grid.add(chooseBook, 0, 1);
        grid.add(historyBookButton, 1, 1);
        grid.add(storyBookButton, 1, 2);
        grid.add(textBookButton, 1, 3);
        grid.add(backButton, 0, 4, 2, 1); // Span 2 kolom untuk tombol kembali

        Scene scene = new Scene(grid, 1360, 720);
        scene.getStylesheets().add(getClass().getResource("/Css/style.css").toExternalForm());
        inputBookStage.setScene(scene);
        inputBookStage.show();

        // Action Button
        historyBookButton.setOnAction(event -> {
            addBook(historyBookObj, "UMM Library - Add History Book", "History Book");
            inputBookStage.close();
        });

        storyBookButton.setOnAction(event -> {
            addBook(storyBookObj, "UMM Library - Add Story Book", "Story Book");
            inputBookStage.close();
        });

        textBookButton.setOnAction(event -> {
            addBook(textBookObj, "UMM Library - Add Text Book", "Text Book");
            inputBookStage.close();
        });

        backButton.setOnAction(event -> {
            Admin adminObj = new Admin(); // Membuat instance Admin
            adminObj.menu(); // Kembali ke menu admin
            inputBookStage.close(); // Tutup stage input buku
        });
    }

    public void addBook(Book genreBook, String addBookStageTitle, String addBookSceneTitle) {
        Admin adminObj = new Admin();
        Book bookObj = new Book();

        Stage addbookStage = new Stage();
        addbookStage.setTitle(addBookStageTitle);

        // Label
        Label sceneTitleLabel = new Label(addBookSceneTitle);
        sceneTitleLabel.getStyleClass().add("scene-title");
        Label bookIdLabel = new Label("ID Buku    :");
        Label bookTitleLabel = new Label("Judul Buku :");
        Label authorLabel = new Label("Penulis    :");
        Label stockLabel = new Label("Stok       :");
        bookIdLabel.getStyleClass().add("form-label");
        bookTitleLabel.getStyleClass().add("form-label");
        authorLabel.getStyleClass().add("form-label");
        stockLabel.getStyleClass().add("form-label");

        // Notification Label
        Label errorMessageLabel = new Label();
        errorMessageLabel.getStyleClass().add("error-message");
        errorMessageLabel.setVisible(false);

        // Field
        TextField bookIdField = new TextField(adminObj.generateId());
        bookIdField.setEditable(false); // ID Buku tidak dapat diubah
        TextField bookTitleField = new TextField();
        TextField authorField = new TextField();
        TextField stockField = new TextField();

        bookIdField.getStyleClass().add("form-field");
        bookTitleField.getStyleClass().add("form-field");
        authorField.getStyleClass().add("form-field");
        stockField.getStyleClass().add("form-field");

        // Button
        Button submitButton = new Button("Submit");
        submitButton.getStyleClass().add("login-button");
        Button backButton = new Button("Kembali");
        backButton.getStyleClass().add("login-button");

        // Grid layout
        GridPane gridAddBook = new GridPane();
        gridAddBook.setAlignment(Pos.CENTER);
        gridAddBook.setHgap(10);
        gridAddBook.setVgap(10);
        gridAddBook.setPadding(new Insets(25, 25, 25, 25));

        gridAddBook.add(sceneTitleLabel, 0, 0, 2, 1); // Span 2 kolom
        gridAddBook.add(bookIdLabel, 0, 1);
        gridAddBook.add(bookIdField, 1, 1);
        gridAddBook.add(bookTitleLabel, 0, 2);
        gridAddBook.add(bookTitleField, 1, 2);
        gridAddBook.add(authorLabel, 0, 3);
        gridAddBook.add(authorField, 1, 3);
        gridAddBook.add(stockLabel, 0, 4);
        gridAddBook.add(stockField, 1, 4);
        gridAddBook.add(errorMessageLabel, 0, 5, 2, 1); // Span 2 kolom
        gridAddBook.add(submitButton, 0, 6);
        gridAddBook.add(backButton, 1, 6);

        Scene addbookScene = new Scene(gridAddBook, 1360, 720);
        addbookScene.getStylesheets().add(getClass().getResource("/Css/style.css").toExternalForm());
        addbookStage.setScene(addbookScene);
        addbookStage.show();

        // Action button
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
                bookObj.setBookId(bookIdField.getText());
                bookObj.setTitle(bookTitleField.getText());
                genreBook.setCategory(sceneTitleLabel.getText());
                bookObj.setAuthor(authorField.getText());
                bookObj.setStock(stock);

                Book.arr_bookList.add(new Book(bookObj.getBookId(), bookObj.getTitle(), bookObj.getAuthor(), genreBook.getCategory(), bookObj.getStock()));

                adminObj.menu();
                addbookStage.close();
            } catch (NumberFormatException e) {
                errorMessageLabel.setText("Stok harus berupa angka.");
                errorMessageLabel.setVisible(true);
            }
        });

        backButton.setOnAction(event -> {
            inputBook(); // Kembali ke menu input buku
            addbookStage.close();
        });
    }
}


