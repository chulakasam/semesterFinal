package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lk.ijse.dto.ClientDto;
import lk.ijse.model.ClientModel;
import lk.ijse.model.PaymentModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PaymentController {
    public Label lblPayId;
    public Label lblDate;
    public JFXComboBox cmbClientId;
    public JFXComboBox cmbOrderId;
    public JFXComboBox cmbType;
    public TableView tblPayementTm;
    public TableColumn colPayId;
    public TableColumn colDate;
    public TableColumn colDesc;
    public TableColumn colClientId;
    public TableColumn colOrderId;
    public TableColumn colType;

    public void initialize(){
        setDate();
        generatePayId();
        loadAllClients();
        loadType();
    }
    public void setDate(){
        lblDate.setText(String.valueOf(LocalDate.now()));
    }
    public  void generatePayId(){
        try {
            String payId = PaymentModel.generateNextOrderId();
            lblPayId.setText(payId);

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void  loadAllClients(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<ClientDto> idList = ClientModel.getAllCustomer();

            for (ClientDto dto : idList) {
                obList.add(dto.getId());
            }

            cmbClientId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadType(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Order Payment");
        obList.add("Monthly Payment");
        cmbType.setItems(obList);
    }




}
