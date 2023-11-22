package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.SupplierDto;
import lk.ijse.dto.Tm.SupplierTm;
import lk.ijse.dto.TrainerDto;
import lk.ijse.model.SupplierModel;
import lk.ijse.model.TrainerModel;

import java.io.IOException;
import java.sql.SQLException;

public class SupplierController {

    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtTel;
    public TextField txtSuppId;
    public TableView <SupplierTm>tblSupplierTm;
    public TableColumn <?,?>colSupId;
    public TableColumn <?,?>colSupName;
    public TableColumn <?,?>colAddress;
    public TableColumn <?,?>colTel;
    public AnchorPane supplierPanel;


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
    public void btnSearchOnAction(ActionEvent actionEvent) {
        String suppId = txtSuppId.getText();

        var model = new SupplierModel();
        try {
            SupplierDto dto=model.searchSupplier(suppId);
            if(dto!=null){
                txtId.setText(dto.getSupplierId());
                txtName.setText(dto.getName());
                txtAddress.setText(dto.getAddress());
                txtTel.setText(String.valueOf(dto.getContactNo()));
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Supplier not found!!!").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }
    public void btnGoToItemOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Stock_form.fxml"));
        supplierPanel.getChildren().clear();
        supplierPanel.getChildren().add(anchorPane);

    }
}
