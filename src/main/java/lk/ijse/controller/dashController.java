package lk.ijse.controller;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import lk.ijse.model.ClientModel;
import lk.ijse.model.SupplierModel;
import lk.ijse.model.TrainerModel;

public class dashController {
    public Label lblclientTot;
    public Label lblTrainerTot;
    public Label lblSuppierTot;
    public BarChart barchart;

    private void loadTrainerTotal() {

        String TrainerValue="0";
        try{
            TrainerValue= TrainerModel.searchTrainerTotal();
        }catch (Exception e){
            TrainerValue="0";
        }
       lblTrainerTot.setText(TrainerValue);
    }
    private void loadClientTotal() {
        String ClientValue="0";
        try{
            ClientValue= ClientModel.searchClientTotal();
        }catch (Exception e){
            ClientValue="0";
        }

       lblclientTot.setText(ClientValue);

    }
    private void loadSupplierTot() {
        String SupplierValue="0";
        try{
            SupplierValue= SupplierModel.searchSupplierTotal();
        }catch (Exception e){
           SupplierValue="0";
        }
        lblSuppierTot.setText(SupplierValue);
    }
    public void initialize(){
        loadClientTotal();
        loadTrainerTotal();
        loadSupplierTot();
        setBarchart();
    }
    public void setBarchart(){

        XYChart.Series series = new XYChart.Series();

        series.setName("Income");
        series.getData().add(new XYChart.Data("Monday",8));
        series.getData().add(new XYChart.Data("Tuesday",12));
        series.getData().add(new XYChart.Data("Wednesday",10));
        series.getData().add(new XYChart.Data("Thursday",15));
        series.getData().add(new XYChart.Data("Friday",12));
        series.getData().add(new XYChart.Data("Saturday",8));
        series.getData().add(new XYChart.Data("Sunday",5));
        barchart.getData().addAll(series);
    }



}
