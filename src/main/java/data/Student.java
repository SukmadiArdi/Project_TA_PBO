package data;

import Util.iMenu;
import com.main.LibrarySystem;
import controller.BorrowedBooksController;
import controller.ReturnBooksController;
import controller.StudentMenuController;
import exception.custom.IllegalAdminAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

            Scene studentMenuScene = new Scene(root);
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
            controller.setStudent(LibrarySystem.getInstance().getStudent()); // Panggil setStudent untuk mengatur objek Student

            Scene scene = new Scene(root);
            scene.getStylesheets().add(Student.class.getResource("/Css/style.css").toExternalForm());
            showBorrowedBooksStage.setScene(scene);
            showBorrowedBooksStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void returnBooks() {
        try {
            Stage returnBooksStage = new Stage();
            returnBooksStage.setTitle("UMM Library - Pengembalian buku");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/return_books.fxml"));
            Parent root = loader.load();

            ReturnBooksController controller = loader.getController();
            controller.setStudent(this); // Set the student instance

            Scene scene = new Scene(root);
            // Use the correct path for the CSS file
            scene.getStylesheets().add(getClass().getResource("/Css/style.css").toExternalForm());
            returnBooksStage.setScene(scene);
            returnBooksStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isStudents(TextField username) throws IllegalAdminAccess {
        if (username.getText().length() != 15) {
            throw new IllegalAdminAccess("NIM harus 15 digit!");
        }
        for (UserStudent i : arr_userStudent) {
            if (i.nim.equals(username.getText())) {
                LibrarySystem.getInstance().setNIM(i.nim); // Set NIM saat login berhasil
                menu();
                return false;
            }
        }
        throw new IllegalAdminAccess("NIM tidak ditemukan!");
    }
}
