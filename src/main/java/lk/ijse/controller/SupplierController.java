package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.dto.SupplierDto;
import lk.ijse.model.SupplierModel;

import java.sql.SQLException;

public class SupplierController {

    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtTel;

    public void btnAddOnAction(ActionEvent actionEvent) {
        String Id = txtId.getText();
        String Name = txtName.getText();
        String Address = txtAddress.getText();
        int Tel = Integer.parseInt(txtTel.getText());

        var dto = new SupplierDto(Id, Name, Address, Tel);
        try {
            var supplierModel = new SupplierModel();
            boolean isAdded=supplierModel.saveSupplier(dto);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier Added sucessfully!!!").show();
                clearField();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    private void clearField() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtTel.setText("");
    }
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String Id = txtId.getText();
        String Name = txtName.getText();
        String Address = txtAddress.getText();
        int Tel = Integer.parseInt(txtTel.getText());

        try{
            SupplierDto dto=new SupplierDto(Id,Name,Address,Tel);
            var supplierModel = new SupplierModel();
            boolean isUpdated=supplierModel.updateSupplier(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier Update successfully!!!").show();
                clearField();
            }
        }catch (SQLException e){
            new  Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String Id = txtId.getText();

        try {
            var supplierModel = new SupplierModel();
            boolean isDeleted=supplierModel.deleteSupplier(Id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier Deleted successfully!!!").show();
                clearField();
            }
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
