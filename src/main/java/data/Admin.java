package data;

import Util.iMenu;
import controller.AddStudentController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;


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
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DisplayStudent.fxml"));
            Parent root = loader.load();

            // Set the scene and stage
            Stage stage = new Stage();
            stage.setTitle("Daftar Mahasiswa");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
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