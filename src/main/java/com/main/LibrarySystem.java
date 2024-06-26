package com.main;

import data.Admin;
import data.Student;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class LibrarySystem extends Application {

    public static LibrarySystem instance;
    public static String NIM;

    private final Admin adminObj;
    private final Student studentObj;

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
        Scene scene = new Scene(root, 1360, 720);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Css/style.css")).toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
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

    public void setNIM(String nim) {
        NIM = nim;
    }
}
