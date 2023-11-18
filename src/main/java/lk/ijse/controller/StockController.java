package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.Tm.ItemTm;
import lk.ijse.model.ItemModel;

import java.sql.SQLException;
import java.util.List;

public class StockController {
    public TextField txtItemCode;
    public TextField txtItemName;
    public TextField txtPrice;
    public TextField txtQty;
    public TextField txtSearchId;
    public TableView tblItemTm;
    public TableColumn colItemCode;
    public TableColumn colName;
    public TableColumn colPrice;
    public TableColumn colQtyOnHand;


    public void initialize(){
        loadAllItems();
        setCellValueFactory();
    }
    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));
    }
    public void loadAllItems() {
        var model = new ItemModel();
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();
        try{
            List<ItemDto> dtoList = model.getAllItems();
            for (ItemDto dto : dtoList) {
                obList.add(
                        new ItemTm(
                                dto.getItemCode(),
                                dto.getName(),
                                dto.getUnitPrice(),
                                dto.getQty()
                        )
                );
            }
            tblItemTm.setItems(obList);

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }

    }
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
