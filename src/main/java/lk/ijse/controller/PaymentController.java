package lk.ijse.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.model.PaymentModel;

import java.sql.SQLException;
import java.time.LocalDate;

public class PaymentController {
    public Label lblPayId;
    public Label lblDate;

    public void initialize(){
        setDate();
        generatePayId();
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
}
