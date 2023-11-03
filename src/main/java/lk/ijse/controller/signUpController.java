package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class signUpController {
    public AnchorPane signUpPanel;

    public void btnSignInOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) signUpPanel.getScene().getWindow();
        stage.setScene(scene);
    }
}
