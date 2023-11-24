package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import lk.ijse.dto.ClientDto;
import lk.ijse.model.ClientModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class clientController {
    public AnchorPane cleintPanel;
    public TextField txtid;
    public TextField txtname;
    public TextField txtaddress;
    public TextField txttel;
    public TextField txtsearchId;
    public TextField txtEmail;
    public DatePicker datePickerDOB;
    public TextField txtHeight;
    public TextField txtWeight;
    public JFXComboBox cmbGender;
    public void initialize(){
        loadGender();
    }
    private void loadGender() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Male");
        obList.add("Female");
        cmbGender.setItems(obList);
    }
    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtid.getText();
        String name = txtname.getText();
        String address = txtaddress.getText();
        int tel= Integer.parseInt(txttel.getText());
        String email = txtEmail.getText();
        int height = (int) Double.parseDouble(txtHeight.getText());
        int weight = (int) Double.parseDouble(txtWeight.getText());
        String gender = (String) cmbGender.getValue();
        String dob = String.valueOf(datePickerDOB.getValue());


        var dto = new ClientDto(id, name, address, tel,email,height,weight,gender,dob);
        try {
           var clientModel=new ClientModel();
            boolean isAdded=clientModel.saveClient(dto);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Client added!!!").show();
                clearField();
            }
        } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    private void clearField() {
        txtid.setText("");
        txtname.setText("");
        txtaddress.setText("");
        txttel.setText("");
        txtEmail.setText("");
        txtHeight.setText("");
        txtWeight.setText("");
        datePickerDOB.setValue(null);
        cmbGender.setValue(null);
    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtid.getText();
        var clientModel = new ClientModel();
        try {
            boolean isDeleted = clientModel.deleteClient(id);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Client deleted successfully!!!").show();
                clearField();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void btnSearchOnAction(ActionEvent actionEvent) {
        String searchId = txtsearchId.getText();

        var clientModel = new ClientModel();
        try {
            ClientDto clientDto=clientModel.searchClient(searchId);
            if(clientDto!=null){
                txtid.setText(clientDto.getId());
                txtname.setText(clientDto.getName());
                txtaddress.setText(clientDto.getAddress());
                txttel.setText(String.valueOf(clientDto.getTel()));
                txtEmail.setText(clientDto.getEmail());
                txtHeight.setText(String.valueOf(clientDto.getHeight()));
                txtWeight.setText(String.valueOf(clientDto.getWeight()));
                cmbGender.setValue(clientDto.getGender());
                datePickerDOB.setValue(LocalDate.parse(clientDto.getDob()));

            }else {
                new Alert(Alert.AlertType.INFORMATION,"client not found").show();
            }
        }catch (SQLException e){
             new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String Id = txtid.getText();
        String Name = txtname.getText();
        String Address = txtaddress.getText();
        int Tel = Integer.parseInt(txttel.getText());
        String Email = txtEmail.getText();
        int Height = (int) Double.parseDouble(txtHeight.getText());
        int Weight = (int) Double.parseDouble(txtWeight.getText());
        String Gender = (String) cmbGender.getValue();
        String DOB = String.valueOf(datePickerDOB.getValue());

        try{
            var dto = new ClientDto(Id, Name, Address, Tel,Email,Height,Weight,Gender,DOB);
            var clientModel = new ClientModel();
            boolean isUpdated=clientModel.updateClient(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Client Updated successfully!!!").show();
                clearField();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }
    public void hypelinkOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Payment_form.fxml"));
        cleintPanel.getChildren().clear();
        cleintPanel.getChildren().add(anchorPane);
    }
    public void btnViewClientsOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/viewClientDetails_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public void btnBackToHomeAction(ActionEvent actionEvent) throws IOException {
    }
}
