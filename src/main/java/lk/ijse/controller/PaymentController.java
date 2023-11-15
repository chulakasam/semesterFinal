package lk.ijse.controller;

import javafx.scene.control.Label;

import java.time.LocalDate;

public class PaymentController {
    public Label lblPayId;
    public Label lblDate;

    public void initialize(){
        setDate();
    }

    public void setDate(){
        lblDate.setText(String.valueOf(LocalDate.now()));
    }
}
