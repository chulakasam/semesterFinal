package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.dto.ItemDto;
import lk.ijse.model.ItemModel;

import java.sql.SQLException;

public class StockController {
    public TextField txtItemCode;
    public TextField txtItemName;
    public TextField txtPrice;
    public TextField txtQty;
    public TextField txtSearchId;


    public void btnAddOnAction(ActionEvent actionEvent) {
        String code = txtItemCode.getText();
        String name = txtItemName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());

        var dto= new ItemDto(code,name,price,qty);
        try{
            var itemModel = new ItemModel();
            boolean isAdded=itemModel.saveItem(dto);
            if(isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Item Added successed!!!").show();
                clearField();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    private void clearField() {
        txtItemCode.setText("");
        txtItemName.setText("");
        txtPrice.setText("");
        txtQty.setText("");
    }
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String code = txtItemCode.getText();
        String name = txtItemName.getText();
        double price= Double.parseDouble(txtPrice.getText());
        int qty= Integer.parseInt(txtQty.getText());

        try {
            var dto = new ItemDto(code,name,price,qty);
            var itemModel = new ItemModel();
            boolean isUpdated=itemModel.updateItem(dto);
            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Item Update successfully!!!").show();
                clearField();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String code = txtItemCode.getText();

        var itemModel = new ItemModel();
        try{
           boolean isDeleted=itemModel.deleteItem(code);
           if(isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION,"Item Deleted successfully!!!").show();
            clearField();
           }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public void btnSearchOnAction(ActionEvent actionEvent) {
        String searchId = txtSearchId.getText();

        var itemModel = new ItemModel();
        try {
            ItemDto dto=itemModel.searchItem(searchId);
            if(dto!=null){
                txtItemCode.setText(dto.getItemCode());
                txtItemName.setText(dto.getName());
                txtPrice.setText(String.valueOf(dto.getUnitPrice()));
                txtQty.setText(String.valueOf(dto.getQty()));
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Item cannot found!!!").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }
}
