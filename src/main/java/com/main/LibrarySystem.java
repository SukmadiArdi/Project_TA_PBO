package com.main;

import data.Admin;
import data.Student;
import exception.custom.IllegalAdminAccess;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LibrarySystem extends Application implements Initializable {

    public static LibrarySystem instance;
    public static String NIM;

    public Admin adminObj;
    public Student studentObj;

    @FXML
    public TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLoginMessage;
    @FXML
    private Button loginButton;

    public LibrarySystem() {
        adminObj = new Admin();
        studentObj = new Student();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;

        primaryStage.setTitle("UMM Library");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/main_menu.fxml"));
        Parent root = loader.load();
        loader.setController(this);
        Scene scene = new Scene(root, 1360, 720);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Css/style.css")).toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLoginMessage.setVisible(false);
    }

    @FXML
    private void handleLogin() {
        if (usernameField.getText().equals(Admin.adminusername) && passwordField.getText().equals(Admin.adminpassword)) {
            adminObj.menu();
            usernameField.getScene().getWindow().hide();
        } else if (usernameField.getText().length() == 15 && passwordField.getText().length() < 15) {
            try {
                if (studentObj.isStudents(usernameField)) {
                    errorLoginMessage.setVisible(false);
                    NIM = usernameField.getText();
                    studentObj.menu();
                    usernameField.getScene().getWindow().hide();
                }
            } catch (IllegalAdminAccess pesanError) {
                errorLoginMessage.setText("NIM atau Password Salah.");
                errorLoginMessage.setVisible(true);
            } catch (Exception e) {
                errorLoginMessage.setText("Terjadi kesalahan saat login.");
                errorLoginMessage.setVisible(true);
            }
        } else {
            errorLoginMessage.setVisible(true);
        }
    }


    public static LibrarySystem getInstance() {
        return instance;
    }

    public Admin getAdmin() {
        return adminObj;
    }

    public Student getStudent() {
        return studentObj;
    }
}