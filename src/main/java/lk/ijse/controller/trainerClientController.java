package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.ClientDto;
import lk.ijse.dto.TrainerClientDto;
import lk.ijse.dto.TrainerDto;
import lk.ijse.dto.WorkOutDto;
import lk.ijse.model.ClientModel;
import lk.ijse.model.TrainerModel;
import lk.ijse.model.WorkoutModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class trainerClientController {
    public Label lblDate;
    public JFXComboBox cmbClientId;
    public JFXComboBox cmbTrainerId;
    public TextArea txtareadesc;
    public Label lblWorkOutId;
    public JFXButton btnAdddetail;
    public AnchorPane workOutPanel;


    public void initialize(){
        generateWorkOutId();
        setDate();
        LoadAllTrainer();
        LoadClient();

    }
    private void generateWorkOutId() {

        try {
            String id = new WorkoutModel().generateworkoutId();
            lblWorkOutId.setText(id);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void setDate(){
        lblDate.setText(String.valueOf(LocalDate.now()));
    }
    public void LoadClient() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<ClientDto> idList = ClientModel.getAllCustomer();

            for (ClientDto dto : idList) {
                obList.add(dto.getId());
            }

            cmbClientId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void LoadAllTrainer() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<TrainerDto> idList = TrainerModel.LoadAllTrainers();

            for (TrainerDto dto : idList) {
                obList.add(dto.getTrainerId());
            }

            cmbTrainerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/trainer_form.fxml"));
        workOutPanel.getChildren().clear();
        workOutPanel.getChildren().add(anchorPane);
    }
    public void btnAddDetailsOnAction(ActionEvent actionEvent) {
        String workOutId = lblWorkOutId.getText();
        String desc = txtareadesc.getText();
        String trainerId = (String) cmbTrainerId.getValue();

        String clientId = (String) cmbClientId.getValue();
        String date = lblDate.getText();

       var dto= new WorkOutDto(workOutId,desc,trainerId);
       var Dto=new TrainerClientDto(trainerId,clientId,date);

       try{
           boolean isAdded= WorkoutModel.setWorkOut(dto,Dto);

           if(isAdded){
               new Alert(Alert.AlertType.CONFIRMATION,"Added Successfully!!!!").show();
           }
       }catch (SQLException e){
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
       }
    }
}
