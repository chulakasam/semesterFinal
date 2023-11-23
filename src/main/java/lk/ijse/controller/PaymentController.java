package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.ClientDto;
import lk.ijse.dto.OrderDto;
import lk.ijse.dto.Tm.AddPaymentTm;
import lk.ijse.dto.paymentDto;
import lk.ijse.model.ClientModel;
import lk.ijse.model.OrderModel;
import lk.ijse.model.PaymentModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
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
    private OrderModel orderModel=new OrderModel();

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
            List<OrderDto> orderDtos = orderModel.getAllCustomer();

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
           var model = new PaymentModel();
           boolean isSaved=model.savePayment(dto);
           if (isSaved){
               new Alert(Alert.AlertType.CONFIRMATION,"Payment Added Successfully!!!").show();
               clearField();
           }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
}
    private void clearField() {
        txtAmount.setText("");
    }


    public void btnGenerateReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {

        InputStream resourceAsStream = getClass().getResourceAsStream("/report/report.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());


        JasperViewer.viewReport(jasperPrint, false);
    }
}
