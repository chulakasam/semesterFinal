package lk.ijse.controller;

import com.google.zxing.WriterException;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import lk.ijse.BO.ClientBO;
import lk.ijse.BO.ClientBOImpl;
import lk.ijse.QRGenerator.QRGenerate;
import lk.ijse.dto.ClientDto;
import lk.ijse.DAO.Custom.Impl.ClientDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Pattern;

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
    public AnchorPane lblClientId;
    public Label lblclientId;

    //ClientDAOImpl clientDAOImpl= new ClientDAOImpl();
    ClientBO clientBO=new ClientBOImpl();
    public void initialize(){
        loadGender();
        generateClientId();

    }
    private void generateClientId() {
        try {
            String id = clientBO.generateClientId();
            lblclientId.setText(id);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    private void loadGender() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Male");
        obList.add("Female");
        cmbGender.setItems(obList);
    }
    public void btnAddOnAction(ActionEvent actionEvent) throws IOException, WriterException {
        String id = lblclientId.getText();
        String name = txtname.getText();
        String address = txtaddress.getText();
        int tel= Integer.parseInt(txttel.getText());
        String email = txtEmail.getText();
        int height = (int) Double.parseDouble(txtHeight.getText());
        int weight = (int) Double.parseDouble(txtWeight.getText());
        String gender = (String) cmbGender.getValue();
        String dob = String.valueOf(datePickerDOB.getValue());

        boolean isValidated=Validated();

        var dto = new ClientDto(id, name, address, tel,email,height,weight,gender,dob);
        if(isValidated) {
            try {
                boolean isAdded = clientBO.saveClient(dto);
                if (isAdded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Client added!!!").show();
                    QRGenerate qrGenerate = new QRGenerate(id, name, address, email);
                    clearField();
                    generateClientId();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,"Client can't added!!!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Invalid data").show();
        }
    }
    private boolean Validated() {
        boolean isMatched;

        String tel=txttel.getText();
        isMatched= Pattern.compile("[0-9]{9,10}").matcher(tel).matches();
        if(!isMatched){
            new Alert(Alert.AlertType.ERROR,"Invalid contact No").show();
            return false;
        }

        String email = txtEmail.getText();
        isMatched=Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$").matcher(email).matches();
        if(!isMatched){
            new Alert(Alert.AlertType.ERROR,"Invalid email").show();
            return false;
        }

        String height = txtHeight.getText();
        isMatched=Pattern.compile("[0-9]{1,3}").matcher(height).matches();
        if(!isMatched){
            new Alert(Alert.AlertType.ERROR,"Invalid height").show();
            return false;
        }

        String weight = txtWeight.getText();
        isMatched=Pattern.compile("[0-9]{1,3}").matcher(weight).matches();
        if(!isMatched){
            new Alert(Alert.AlertType.ERROR,"Invalid weight").show();
            return false;
        }
        return true;
    }
    private void clearField() {
        lblclientId.setText("");
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
        String id = txtsearchId.getText();
        try {
            boolean isDeleted = clientBO.deleteClient(id);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Client deleted successfully!!!").show();
                clearField();
                generateClientId();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void btnSearchOnAction(ActionEvent actionEvent) {
        String searchId = txtsearchId.getText();
        try {
            ClientDto clientDto=clientBO.searchClient(searchId);
            if(clientDto!=null){
               lblclientId.setText(clientDto.getId());
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
        String Id = lblclientId.getText();
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
            boolean isUpdated=clientBO.updateClient(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Client Updated successfully!!!").show();
                clearField();
                generateClientId();
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
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashform.fxml"));
        cleintPanel.getChildren().clear();
       cleintPanel.getChildren().add(anchorPane);
    }


}
