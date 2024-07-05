package controller;

import data.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

public class DisplayStudentController {

    @FXML
    private Label sceneTitle;

    @FXML
    private TableView<Student.UserStudent> studentTable;

    @FXML
    private TableColumn<Student.UserStudent, String> nimColumn;

    @FXML
    private TableColumn<Student.UserStudent, String> namaColumn;

    @FXML
    private TableColumn<Student.UserStudent, String> fakultasColumn;

    @FXML
    private TableColumn<Student.UserStudent, String> prodiColumn;

    @FXML
    public void initialize() {
        // Set up the columns in the table
        nimColumn.setCellValueFactory(new PropertyValueFactory<>("nim"));
        namaColumn.setCellValueFactory(new PropertyValueFactory<>("nama"));
        fakultasColumn.setCellValueFactory(new PropertyValueFactory<>("fakultas"));
        prodiColumn.setCellValueFactory(new PropertyValueFactory<>("prodi"));

        sceneTitle.setText("Daftar Mahasiswa");
        // Load data into the table
        studentTable.setItems(Student.arr_userStudent);
    }

    @FXML
    private void handleBackButton() {
        // Handle the back button action (e.g., close the current stage)
        Stage stage = (Stage) studentTable.getScene().getWindow();
        stage.close();
    }
}
