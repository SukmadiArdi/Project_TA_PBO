package controller;

import data.Admin;
import data.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddStudentController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField nimField;
    @FXML
    private TextField fakultasField;
    @FXML
    private TextField jurusanField;
    @FXML
    private Label submitFailed;

    private Admin admin; // Tambahkan atribut untuk menyimpan referensi Admin

    // Method setter untuk mengatur referensi Admin
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @FXML
    private void handleSubmit() {
        if (nimField.getText().length() == 15) {
            Student.arr_userStudent.add(new Student.UserStudent(nameField.getText(), nimField.getText(), fakultasField.getText(), jurusanField.getText()));
            admin.menu();
            nameField.getScene().getWindow().hide();
        } else {
            submitFailed.setVisible(true);
        }
    }

    @FXML
    private void handleBack(){
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
