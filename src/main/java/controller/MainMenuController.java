package controller;

import com.main.LibrarySystem;
import data.Admin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private void showAdminLogin() throws IOException {
        showLogin(Admin.adminusername);
    }

    @FXML
    private void showStudentLogin() throws IOException {
        showLogin("");
    }

    private void showLogin(String defaultUsername) throws IOException {
        Stage loginStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/login.fxml"));
        Parent root = loader.load();

        LibrarySystem loginController = loader.getController();
        loginController.usernameField.setText(defaultUsername);

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/Css/Style.css").toExternalForm());
        loginStage.setScene(scene);
        loginStage.show();

    }
}
