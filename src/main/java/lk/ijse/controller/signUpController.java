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

public class signUpController {
    public AnchorPane signUpPanel;
    public TextField txtusername;
    public TextField txtpassword;
    public TextField txtEmail;

    public void btnSignInOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) signUpPanel.getScene().getWindow();
        stage.setScene(scene);
    }

    public void btnCreateAccountOnAction(ActionEvent actionEvent) {
        String username = txtusername.getText();
        String password = txtpassword.getText();
        String email = txtEmail.getText();

        var dto = new UserDto(username,password,email);
        try {
            var userModel = new UserModel();
           boolean isSaved=userModel.saveUser(dto);
           if(isSaved){
             new Alert(Alert.AlertType.CONFIRMATION,"User added successfully!!!").show();
             clearField();
           }
        }catch (SQLException e){
            new  Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    private void clearField() {
        txtusername.setText("");
        txtpassword.setText("");
        txtEmail.setText("");
    }
}
