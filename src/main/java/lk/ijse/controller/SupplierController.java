package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.SupplierBO;
import lk.ijse.BO.SupplierBOImpl;
import lk.ijse.DAO.Custom.SupplierDAO;
import lk.ijse.dto.SupplierDto;
import lk.ijse.dto.Tm.SupplierTm;
import lk.ijse.DAO.Custom.Impl.SupplierDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

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
    public Label lblSupplierId;
   //SupplierDAO supplierDAOImpl= new SupplierDAOImpl();
     SupplierBO supplierBO=new SupplierBOImpl();

    public void initialize() {
        loadAllSuppliers();
        setCellValueFactory();
        generateSupplierId();
    }
    private void generateSupplierId() {
        try {
            String supplierId = supplierBO.generateSupplierId();
            lblSupplierId.setText(supplierId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    private void setCellValueFactory() {
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
    }
    public void btnAddOnAction(ActionEvent actionEvent) {
        boolean isValidated=validated();

        String Id = lblSupplierId.getText();
        String Name = txtName.getText();
        String Address = txtAddress.getText();
        int Tel = Integer.parseInt(txtTel.getText());
       if(isValidated) {
           var dto = new SupplierDto(Id, Name, Address, Tel);
           try {

               boolean isAdded = supplierBO.saveSupplier(dto);
               if (isAdded) {
                   new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added successfully!!!").show();
                   clearField();
                   generateSupplierId();
                   loadAllSuppliers();

               }
           } catch (SQLException e) {
               new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
           }
       }else{
           new Alert(Alert.AlertType.ERROR,"Supplier not Added!!!").show();
       }

    }
    private boolean validated() {
        String address = txtAddress.getText();
        boolean isMatched=Pattern.compile("[A-Za-z]{8,}").matcher(address).matches();
        if(!isMatched){
            new Alert(Alert.AlertType.ERROR,"Invalid address").show();
            return false;
        }
        return true;
    }
    private void clearField() {
        lblSupplierId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtTel.setText("");
    }
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String Id = lblSupplierId.getText();
        String Name = txtName.getText();
        String Address = txtAddress.getText();
        int Tel = Integer.parseInt(txtTel.getText());

        try{
            SupplierDto dto=new SupplierDto(Id,Name,Address,Tel);

            boolean isUpdated=supplierBO.updateSupplier(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier Update successfully!!!").show();
                clearField();
                generateSupplierId();
                loadAllSuppliers();
            }
        }catch (SQLException e){
            new  Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String Id = txtSuppId.getText();

        try {

            boolean isDeleted=supplierBO.deleteSupplier(Id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier Deleted successfully!!!").show();
                clearField();
                generateSupplierId();
                loadAllSuppliers();
            }
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void btnSearchOnAction(ActionEvent actionEvent) {
        String suppId = txtSuppId.getText();


        try {
            SupplierDto dto=supplierBO.searchSupplier(suppId);
            if(dto!=null){
                lblSupplierId.setText(dto.getSupplierId());
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
    public void loadAllSuppliers()  {

        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();
        try{
            List<SupplierDto> dtoList = supplierBO.getAllSupplier();
            for (SupplierDto dto : dtoList) {
                obList.add(
                        new SupplierTm(
                                dto.getSupplierId(),
                                dto.getName(),
                                dto.getAddress(),
                                dto.getContactNo()
                        )
                );
            }
            tblSupplierTm.setItems(obList);
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    public void btnBackToHomeOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashform.fxml"));
        supplierPanel.getChildren().clear();
        supplierPanel.getChildren().add(anchorPane);
    }
}
