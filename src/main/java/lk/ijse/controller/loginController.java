package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {
    public AnchorPane LoginPanel;


    public void btnSignOnAction(ActionEvent actionEvent) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/signUp_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) LoginPanel.getScene().getWindow();
        stage.setScene(scene);
    }

    public void btnLogInOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) LoginPanel.getScene().getWindow();
        stage.setScene(scene);
    }
}
