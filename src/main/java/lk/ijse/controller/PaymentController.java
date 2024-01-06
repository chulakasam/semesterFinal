package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.BO.Custom.ClientBO;
import lk.ijse.BO.Custom.Impl.BOFactory;
import lk.ijse.BO.Custom.Impl.ClientBOImpl;
import lk.ijse.BO.Custom.Impl.OrderBOImpl;
import lk.ijse.BO.Custom.Impl.PaymentBOImpl;
import lk.ijse.BO.Custom.OrderBO;
import lk.ijse.BO.Custom.PaymentBO;
import lk.ijse.DAO.Custom.ClientDAO;
import lk.ijse.DAO.Custom.PaymentDAO;
import lk.ijse.dto.ClientDto;
import lk.ijse.dto.OrderDto;
import lk.ijse.dto.Tm.AddPaymentTm;
import lk.ijse.dto.paymentDto;
import lk.ijse.DAO.Custom.Impl.ClientDAOImpl;
import lk.ijse.DAO.Custom.Impl.OrderDAOImpl;
import lk.ijse.DAO.Custom.Impl.PaymentDAOImpl;

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
    public TableColumn colAmount;
    public TextField txtAmount;
    //OrderDAOImpl orderModel=new OrderDAOImpl();
    //PaymentDAO paymentDAO = new PaymentDAOImpl();
    //ClientDAO clientDAOImpl = new ClientDAOImpl();

    OrderBO orderBO= (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);
    PaymentBO paymentBO= (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENTS);
    ClientBO clientBO= (ClientBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CLIENT);

    public void initialize(){
        setDate();
        generatePayId();
        loadAllClients();
        loadType();
        loadAllOrders();
        setCellValueFactory();
    }
    private void setCellValueFactory() {
        colPayId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colClientId.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));

    }
    private void loadAllOrders() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<OrderDto> orderDtos = orderBO.getAllCustomer();

            for (OrderDto dto : orderDtos) {
                obList.add(dto.getOrderId());
            }

            cmbOrderId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void setDate(){
        lblDate.setText(String.valueOf(LocalDate.now()));
    }
    public  void generatePayId(){
        try {
            String payId = paymentBO.generateNextOrderId();
            lblPayId.setText(payId);

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void  loadAllClients(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {

            List<ClientDto> idList = clientBO.getAllCustomer();

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
    public void btnAddPayementOnAction(ActionEvent actionEvent) {
        ObservableList<AddPaymentTm> obList = FXCollections.observableArrayList();
        String payId = lblPayId.getText();
        String date = lblDate.getText();
        double amount= Double.parseDouble(txtAmount.getText());
        String clientId = (String) cmbClientId.getValue();
        String orderId = (String) cmbOrderId.getValue();
        String type = (String) cmbType.getValue();
        var addPaymentTm=new AddPaymentTm(payId,date,amount,clientId,orderId,type);
        obList.add(addPaymentTm);
        tblPayementTm.setItems(obList);


        var dto = new paymentDto(payId,date,amount,clientId,orderId,type);
        try{

           boolean isSaved=paymentBO.savePayment(dto);
           if (isSaved){
               new Alert(Alert.AlertType.CONFIRMATION,"Payment Added Successfully!!!").show();
               clearField();
               generatePayId();
           }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
}
    private void clearField() {
        txtAmount.setText("");
    }

}
