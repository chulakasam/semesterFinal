package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.UserDto;
import lk.ijse.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;

public class loginController {
    public AnchorPane LoginPanel;
    public TextField txtUserName;
    public TextField txtPassword;


    public void btnSignOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/signUp_form.fxml"));
       Scene scene = new Scene(anchorPane);
       Stage stage = (Stage) LoginPanel.getScene().getWindow();
       stage.setScene(scene);

    }
    public void btnLogInOnAction(ActionEvent actionEvent) throws IOException, SQLException {
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        if(username.isEmpty() || password.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please enter your username and password").show();

        } else {
            var model = new UserModel();

            try {
                UserDto dto = model.checkcredential(username, password);
                if (dto != null) {
                    if (username.equals(dto.getUsername()) && password.equals(dto.getPassword())) {
                        navigateToDashBoard();
                    } else {
                        new Alert(Alert.AlertType.INFORMATION, "Invalid username or password").show();
                    }
                }
                    else {
                        new Alert(Alert.AlertType.INFORMATION, "Invalid username or password").show();
                }
                } catch(SQLException e){
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }

            }
    }
    public void  navigateToDashBoard() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) LoginPanel.getScene().getWindow();
        stage.setScene(scene);
    }
}
