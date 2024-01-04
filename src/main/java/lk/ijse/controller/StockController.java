package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.ItemBO;
import lk.ijse.BO.ItemBOImpl;
import lk.ijse.DAO.Custom.ItemDAO;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.Tm.ItemTm;
import lk.ijse.DAO.Custom.Impl.ItemDAOImpl;

import java.io.IOException;
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
    public AnchorPane itemPanel;
    public Label lblItemCode;

   // ItemDAO itemDAOImpl = new ItemDAOImpl();
    ItemBO itemBO= new ItemBOImpl();
    public void initialize(){
        loadAllItems();
        setCellValueFactory();
        generateNextItemId();
    }

    private void generateNextItemId() {
        try {
           // ItemDAOImpl itemDAOImpl = new ItemDAOImpl();
            String nextItemId = itemBO.generateNextItemId();
            lblItemCode.setText(nextItemId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));
    }
    public void loadAllItems() {
       
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();
        try{
            List<ItemDto> dtoList = itemBO.getAllItems();
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
        String code = lblItemCode.getText();
        String name = txtItemName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());

        var dto= new ItemDto(code,name,price,qty);
        try{
            
            boolean isAdded=itemBO.saveItem(dto);
            if(isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Item Added successed!!!").show();
                clearField();
                generateNextItemId();
                loadAllItems();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    private void clearField() {
        lblItemCode.setText("");
        txtItemName.setText("");
        txtPrice.setText("");
        txtQty.setText("");
    }
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String code = lblItemCode.getText();
        String name = txtItemName.getText();
        double price= Double.parseDouble(txtPrice.getText());
        int qty= Integer.parseInt(txtQty.getText());

        try {
            var dto = new ItemDto(code,name,price,qty);
          
            boolean isUpdated=itemBO.updateItem(dto);
            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Item Update successfully!!!").show();
                clearField();
                generateNextItemId();
                clearField();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String code = txtSearchId.getText();

       
        try{
           boolean isDeleted=itemBO.deleteItem(code);
           if(isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION,"Item Deleted successfully!!!").show();
            clearField();
            generateNextItemId();
            loadAllItems();
           }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public void btnSearchOnAction(ActionEvent actionEvent) {
        String searchId = txtSearchId.getText();

       
        try {
            ItemDto dto=itemBO.searchItem(searchId);
            if(dto!=null){
                lblItemCode.setText(dto.getItemCode());
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
    public void btnBackToHomeOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashform.fxml"));
        itemPanel.getChildren().clear();
       itemPanel.getChildren().add(anchorPane);
    }
}
