package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import lk.ijse.dto.ClientDto;
import lk.ijse.model.ClientModel;

import java.sql.SQLException;

public class clientController {
    public AnchorPane cleintPanel;
    public TextField txtid;
    public TextField txtname;
    public TextField txtaddress;
    public TextField txttel;
    public TextField txtsearchId;


    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtid.getText();
        String name = txtname.getText();
        String address = txtaddress.getText();
        int tel= Integer.parseInt(txttel.getText());

        var dto = new ClientDto(id, name, address, tel);
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

        try{
            var dto = new ClientDto(Id, Name, Address, Tel);
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
}
