package controller;

import com.main.LibrarySystem;
import data.Admin;
import exception.custom.IllegalAdminAccess;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLoginMessage;

    private LibrarySystem librarySystem;

    public LoginController() {
        librarySystem = LibrarySystem.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLoginMessage.setVisible(false);
    }

    @FXML
    private void handleLogin() {
        if (usernameField.getText().equals(Admin.adminusername) && passwordField.getText().equals(Admin.adminpassword)) {
            librarySystem.getAdmin().menu();
            usernameField.getScene().getWindow().hide();
        } else if (usernameField.getText().length() == 15 && passwordField.getText().length() < 15) {
            try {
                if (librarySystem.getStudent().isStudents(usernameField) && librarySystem.getStudent().isStudents(passwordField)) {
                    errorLoginMessage.setVisible(false);
                    LibrarySystem.NIM = usernameField.getText();
                    librarySystem.getStudent().menu();
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
}
