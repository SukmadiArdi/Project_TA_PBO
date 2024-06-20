package data;

import Util.iMenu;
import controller.AddStudentController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

import static data.Student.arr_userStudent;


public class Admin extends User implements iMenu {

    //=================================== ATRIBUT =====================================

    public static String adminusername = "admin";
    public static String adminpassword = "admin";

    //======================================= MENU Method =======================================
    @Override
    public void menu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/admin_menu.fxml"));
            Parent root = loader.load();

            Stage adminMenuStage = new Stage();
            adminMenuStage.setTitle("UMM Library - Admin Menu");
            Scene scene = new Scene(root, 1360, 720);
            scene.getStylesheets().add(getClass().getResource("/Css/style.css").toExternalForm());
            adminMenuStage.setScene(scene);
            adminMenuStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //===================================== Other Method =======================================
    public void addStudent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/add_student.fxml"));
            Parent root = loader.load();

            // Setelah FXML dimuat, dapatkan controller-nya dan berikan referensi this (Admin)
            AddStudentController controller = loader.getController();
            controller.setAdmin(this);

            Stage addStudentStage = new Stage();
            addStudentStage.setTitle("Tambah Mahasiswa");
            Scene scene = new Scene(root, 400, 350); // Sesuaikan ukuran form
            scene.getStylesheets().add(getClass().getResource("/Css/style.css").toExternalForm());
            addStudentStage.setScene(scene);
            addStudentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void displayStudent() {
        // Membuat stage baru
        Stage displayStudentStage = new Stage();
        displayStudentStage.setTitle("UMM Library - Daftar Mahasiswa");

        // Label
        Label sceneTitle = new Label("Daftar Mahasiswa");
        sceneTitle.getStyleClass().add("scene-title");

        // Membuat ListView untuk menampilkan mahasiswa
        ListView<String> listView = new ListView<>();
        listView.getStyleClass().add("list-view");

        // Menampilkan daftar mahasiswa (dengan pemisah)
        for (Student.UserStudent student : arr_userStudent) {
            String studentInfo = String.format(
                    "Nama: %s\nNIM: %s\nFakultas: %s\nProdi: %s\n---------------------------",
                    student.nama, student.nim, student.fakultas, student.prodi
            );
            listView.getItems().add(studentInfo);
        }

        // Tombol Kembali
        Button backButton = new Button("Kembali");
        backButton.getStyleClass().add("login-button");

        // Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(sceneTitle, 0, 0, 2, 1); // Span 2 kolom
        grid.add(listView, 0, 1, 2, 1);   // Span 2 kolom dan beberapa baris
        grid.add(backButton, 0, 2, 2, 1); // Span 2 kolom, di bawah ListView

        Scene scene = new Scene(grid, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/Css/style.css").toExternalForm());
        displayStudentStage.setScene(scene);
        displayStudentStage.show();

        // Action Button Kembali
        backButton.setOnAction(event -> {
            menu(); // Kembali ke menu admin
            displayStudentStage.close();
        });
    }


    public void inputBook() {
        super.inputBook();
    }

    public String generateId() {
        Random random = new Random();

        StringBuilder idTengah = new StringBuilder();
        StringBuilder idAkhir = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            idTengah.append(random.nextInt(10));
            idAkhir.append(random.nextInt(10));

        }
        return ("UMM-" + idTengah + "-" + idAkhir);

    }


}