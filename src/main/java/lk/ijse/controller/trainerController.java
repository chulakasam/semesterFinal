package lk.ijse.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.dto.TrainerDto;
import lk.ijse.model.ItemModel;
import lk.ijse.model.TrainerModel;

import java.sql.SQLException;
import java.time.LocalDate;

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


    public void initialize(){
        loadGender();
    }
    private void loadGender() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Male");
        obList.add("Female");
        cmbgen.setItems(obList);
    }
    public void btnAddOnAction(ActionEvent actionEvent) {
        String Id = txtId.getText();
        String name = txtName.getText();
        int tel = Integer.parseInt(txtTel.getText());
        String nic = txtNic.getText();
        String email = txtEmail.getText();
        String gender = (String) cmbgen.getValue();
        String dob= String.valueOf(dpDOB.getValue());
        String desc = txtDesc.getText();

        var dto = new TrainerDto(Id,name,tel,nic,email,gender,dob,desc);
        try {
            var model = new TrainerModel();
            boolean isAdded=model.saveTrainer(dto);
            if(isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Trainer Saved successfully!!!").show();
                clearField();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }

    }
    private void clearField() {
        txtId.setText("");
        txtName.setText("");
        txtTel.setText("");
    }
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String Id = txtId.getText();
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
            }
        }catch (SQLException e){
           new Alert(Alert.AlertType.ERROR, e.getMessage());
        }

    }
    public void btnTrainerDeleteOnAction(ActionEvent actionEvent) {
        String Id = txtId.getText();
        try{
            TrainerModel model = new TrainerModel();
            boolean isDeleted=model.deleteTrainer(Id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted successfully!!!").show();
                clearField();
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
               txtId.setText(dto.getTrainerId());
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
}
