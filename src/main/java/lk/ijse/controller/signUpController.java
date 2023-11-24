package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.UserDto;
import lk.ijse.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class signUpController {
    public AnchorPane signUpPanel;
    public TextField txtusername;
    public TextField txtpassword;
    public TextField txtEmail;
    public PasswordField pwPassword;

    public void btnSignInOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) signUpPanel.getScene().getWindow();
        stage.setScene(scene);
    }

    public void btnCreateAccountOnAction(ActionEvent actionEvent) {

        String username = txtusername.getText();
        String password = pwPassword.getText();
        String email = txtEmail.getText();

        boolean isValidated = validateSignUp();
        var dto = new UserDto(username,password,email);
       if(isValidated){
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
           new Alert(Alert.AlertType.INFORMATION,"User added successfully!!!").show();
       }else{
           new Alert(Alert.AlertType.ERROR,"User added failed!!!").show();
       }
    }
    private boolean validateSignUp() {
        boolean ismatch;
        String username = txtusername.getText();
        ismatch= Pattern.compile("[A-Za-z]{4,}").matcher(username).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid userName !!!").show();
            return false;
        }

        String password = pwPassword.getText();
        ismatch= Pattern.compile("[A-Za-z0-9]{4,}").matcher(password).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid password !!!").show();
            return false;
        }

        String email = txtEmail.getText();
        ismatch= Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$").matcher(email).matches();
        if(!ismatch){
            new Alert(Alert.AlertType.ERROR,"invalid email !!!").show();
            return false;
        }
        return true;
    }

    private void clearField() {
        txtusername.setText("");
        pwPassword.setText("");
        txtEmail.setText("");
    }
}
