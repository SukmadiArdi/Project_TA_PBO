package controller;

import data.Admin;
import books.HistoryBook;
import books.StoryBook;
import books.TextBook;
import data.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Window;

public class InputBookController {

    @FXML
    private Button historyBookButton;

    @FXML
    private Button storyBookButton;

    @FXML
    private Button textBookButton;

    @FXML
    private Button backButton;

    private User user; // Tambahkan atribut user


    @FXML
    public void initialize() {
        user = new User(); // Inisialisasi objek User

        historyBookButton.setOnAction(event -> {
            System.out.println("History Book Button Pressed");
            user.addBook(new HistoryBook(), "UMM Library - Add History Book", "History Book");
            closeWindow();
        });

        storyBookButton.setOnAction(event -> {
            System.out.println("Story Book Button Pressed");
            user.addBook(new StoryBook(), "UMM Library - Add Story Book", "Story Book");
            closeWindow();
        });

        textBookButton.setOnAction(event -> {
            System.out.println("Text Book Button Pressed");
            user.addBook(new TextBook(), "UMM Library - Add Text Book", "Text Book");
            closeWindow();
        });

        backButton.setOnAction(event -> {
            System.out.println("Back Button Pressed");
            Admin adminObj = new Admin();
            adminObj.menu();
            closeWindow();
        });
    }

    private void closeWindow() {
        Scene scene = historyBookButton.getScene();
        if (scene != null) {
            Window window = scene.getWindow();
            if (window != null) {
                window.hide();
            } else {
                System.out.println("Window is null");
            }
        } else {
            System.out.println("Scene is null");
        }
    }
}
