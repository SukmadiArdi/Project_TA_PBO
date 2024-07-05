package data;

import books.Book;
import com.main.LibrarySystem;
import controller.AddBookController;
import controller.ChoiceBooksController;
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


public class User {

    //===================================== ATRIBUT ======================================


    //====================================== METHOD =======================================

    public void menu() {
        // Implementasi dasar menu (bisa kosong atau berisi logika umum)
        System.out.println("Menu User");
    }

    //Method yang digunakan untuk meminjam buku
    public void choiceBooks() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/choice_books.fxml"));
            Parent root = loader.load();

            Stage choiceBooksStage = new Stage();
            choiceBooksStage.setTitle("UMM library - Pilih Buku");
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/Css/style.css").toExternalForm());
            choiceBooksStage.setScene(scene);
            choiceBooksStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Tangani exception jika terjadi kesalahan saat memuat FXML
        }
    }

    public void inputBook() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/input_book.fxml"));
            Parent root = loader.load();

            Stage inputBookStage = new Stage();
            inputBookStage.setTitle("UMM Library - Input Book");
            Scene scene = new Scene(root, 1360, 720);
            scene.getStylesheets().add(getClass().getResource("/Css/style.css").toExternalForm());
            inputBookStage.setScene(scene);
            inputBookStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addBook(Book genreBook, String addBookStageTitle, String addBookSceneTitle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/add_book.fxml"));
            Parent root = loader.load();

            AddBookController controller = loader.getController();
            // Panggil setData setelah scene ditampilkan
            Stage addBookStage = new Stage();
            addBookStage.setOnShown(event -> {
                controller.setData(new Admin(), genreBook.getCategory(), addBookStageTitle, addBookSceneTitle);
            });


            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/Css/style.css").toExternalForm());
            addBookStage.setScene(scene);
            addBookStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


