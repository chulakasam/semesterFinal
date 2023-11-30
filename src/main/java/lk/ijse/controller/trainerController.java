package lk.ijse.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.TrainerDto;
import lk.ijse.model.ClientModel;
import lk.ijse.model.ItemModel;
import lk.ijse.model.TrainerModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class trainerController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtTel;
    public TextField txtTrainerId;
    public TextField txtNic;
    public TextField txtEmail;
    public DatePicker dpDOB;
    public ComboBox cmbgen;
    public TextField txtDesc;
    public Label lblTrainerId;
    public AnchorPane trainerPanel;


    public void initialize(){
        loadGender();
        generateNextTrainerId();
    }

    private void generateNextTrainerId() {
        try {
            String id = new TrainerModel().generateTrainerId();
            lblTrainerId.setText(id);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadGender() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Male");
        obList.add("Female");
        cmbgen.setItems(obList);
    }
    public void btnAddOnAction(ActionEvent actionEvent) {

        boolean isValidated=validated();

        String Id = lblTrainerId.getText();
        String name = txtName.getText();
        int tel = Integer.parseInt(txtTel.getText());
        String nic = txtNic.getText();
        String email = txtEmail.getText();
        String gender = (String) cmbgen.getValue();
        String dob= String.valueOf(dpDOB.getValue());
        String desc = txtDesc.getText();

        var dto = new TrainerDto(Id,name,tel,nic,email,gender,dob,desc);

        if(isValidated) {
            try {
                var model = new TrainerModel();
                boolean isAdded = model.saveTrainer(dto);
                if (isAdded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Trainer Saved successfully!!!").show();
                    clearField();
                    generateNextTrainerId();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Trainer Saved Unsuccessfully!!!").show();
        }
    }
    private boolean validated() {
        boolean isMatched;
        String tel = txtTel.getText();
        isMatched= Pattern.compile("[0-9]{9,10}").matcher(tel).matches();
        if(!isMatched){
            new Alert(Alert.AlertType.ERROR,"Invalid telephone no").show();
            return false;
        }
        String nic = txtNic.getText();
        isMatched=Pattern.compile("[0-9]{12,}").matcher(nic).matches();
        if(!isMatched){
            new Alert(Alert.AlertType.ERROR,"Invalid NIC").show();
            return false;
        }
        String email = txtEmail.getText();
        isMatched=Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$").matcher(email).matches();
        if(!isMatched){
            new Alert(Alert.AlertType.ERROR,"Invalid email").show();
            return false;
        }
        return true;
    }
    private void clearField() {
        lblTrainerId.setText("");
        txtName.setText("");
        txtTel.setText("");
        txtDesc.setText("");
        txtEmail.setText("");
        txtNic.setText("");

    }
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String Id = lblTrainerId.getText();
        String Name = txtName.getText();
        int Tel = Integer.parseInt(txtTel.getText());
        String nic = txtNic.getText();
        String email = txtEmail.getText();
        String gender = (String) cmbgen.getValue();
        String dob= String.valueOf(dpDOB.getValue());
        String desc = txtDesc.getText();

        var dto = new TrainerDto(Id, Name, Tel,nic,email,gender,dob,desc);
        try{
            var model = new TrainerModel();
            boolean isUpdated=model.UpdateTrainer(dto);
            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"update successfully!!").show();
                clearField();
                generateNextTrainerId();
            }
        }catch (SQLException e){
           new Alert(Alert.AlertType.ERROR, e.getMessage());
        }

    }
    public void btnTrainerDeleteOnAction(ActionEvent actionEvent) {
        String Id = txtTrainerId.getText();
        try{
            TrainerModel model = new TrainerModel();
            boolean isDeleted=model.deleteTrainer(Id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted successfully!!!").show();
                clearField();
                generateNextTrainerId();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public void btnSearchOnAction(ActionEvent actionEvent) {
        String trainerId = txtTrainerId.getText();
        var model = new TrainerModel();
        try {
            TrainerDto dto=model.searchTrainer(trainerId);
           if(dto!=null){
               lblTrainerId.setText(dto.getTrainerId());
               txtName.setText(dto.getName());
               txtTel.setText(String.valueOf(dto.getTel()));
               txtNic.setText(dto.getNic());
               txtEmail.setText(dto.getEmail());
               cmbgen.setValue(dto.getGender());
               dpDOB.setValue(LocalDate.parse(dto.getDob()));
               txtDesc.setText(dto.getDesc());
           }else {
               new Alert(Alert.AlertType.INFORMATION,"Trainer not found!!!").show();
           }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public void btnViewTrainersOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ViewTrainers_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public void btnWorkOutDetailsOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/TrainerClientForm.fxml"));
        trainerPanel.getChildren().clear();
        trainerPanel.getChildren().add(anchorPane);
    }
}
