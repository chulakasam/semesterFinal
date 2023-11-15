package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import lk.ijse.dto.ClientDto;
import lk.ijse.dto.ItemDto;
import lk.ijse.model.ClientModel;
import lk.ijse.model.ItemModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrderController {
    public Label lblDate;
    public JFXComboBox <String> cmbClient;
    public JFXComboBox <String> cmbItem;
    public Label txtClientname;
    public Label lblItemName;
    public Label lblPrice;

    public void setDate(){
       lblDate.setText(String.valueOf(LocalDate.now()));
    }
    public  void initialize(){
        setDate();
        LoadAllClients();
        loadAllItem();
    }

    public void LoadAllClients() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<ClientDto> idList = ClientModel.getAllCustomer();

            for (ClientDto dto : idList) {
                obList.add(dto.getId());
            }

            cmbClient.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void loadAllItem(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<ItemDto> codeList=ItemModel.getAllItems();
            for (ItemDto dto:codeList){
                obList.add(dto.getItemCode());
            }
            cmbItem.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void cmbClientOnAction(ActionEvent actionEvent) throws SQLException {
        String Id = cmbClient.getValue();
        try{
           var model = new ClientModel();
           ClientDto dto=model.searchClient(Id);
           txtClientname.setText(dto.getName());
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    public void cmbItemOnAction(ActionEvent actionEvent) {
        String code = cmbItem.getValue();

        try{
            var model = new ItemModel();
            ItemDto dto=model.searchItem(code);
            lblItemName.setText(dto.getName());
            lblPrice.setText(String.valueOf(dto.getUnitPrice()));
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }
}
