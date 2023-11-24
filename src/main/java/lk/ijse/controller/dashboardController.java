package lk.ijse.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.db.DbConnection;
import lk.ijse.model.ClientModel;
import lk.ijse.model.TrainerModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


public class dashboardController {
    public AnchorPane dashPanel;
    public AnchorPane dashSubPanel;
    public Label lblDate;
    public Label lblIme;
    public BarChart <?,?>tbldashboard;
    public Label lbltotalClient;
    public Label lblTrainerTotal;


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) dashPanel.getScene().getWindow();
        stage.setScene(scene);
    }
    public void btnManageClientOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/client_form.fxml"));
        dashSubPanel.getChildren().clear();
        dashSubPanel.getChildren().add(anchorPane);

    }
    public void btnManageTrainerAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/trainer_form.fxml"));
        dashSubPanel.getChildren().clear();
        dashSubPanel.getChildren().add(anchorPane);
    }
    public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Stock_form.fxml"));
        dashSubPanel.getChildren().clear();
        dashSubPanel.getChildren().add(anchorPane);
    }
    public void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Orders.fxml"));
        dashSubPanel.getChildren().clear();
        dashSubPanel.getChildren().add(anchorPane);
    }
    public void btnManageSuppliersOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/supplier_form.fxml"));
        dashSubPanel.getChildren().clear();
        dashSubPanel.getChildren().add(anchorPane);
    }
    public void setDate(){
        lblDate.setText(String.valueOf(LocalDate.now()));
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            while(true) {
                try{
                    Thread.sleep(1000);
                }catch(Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(() -> {
                    lblIme.setText(timenow);
                });
            }
        });
        thread.start();
    }
    public void initialize(){
        setDate();
        setBarchart();
        loadClientTotal();
        loadTrainerTotal();
    }
    private void loadTrainerTotal() {

        String TrainerValue="0";
        try{
            TrainerValue= TrainerModel.searchTrainerTotal();
        }catch (Exception e){
            TrainerValue="0";
        }
        lblTrainerTotal.setText(TrainerValue);
    }
    private void loadClientTotal() {
        String ClientValue="0";
        try{
            ClientValue= ClientModel.searchClientTotal();
        }catch (Exception e){
            ClientValue="0";
        }

       lbltotalClient.setText(ClientValue);

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
        tbldashboard.getData().addAll(series);
    }
    public void btnReportsOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Reports_form.fxml"));
        dashSubPanel.getChildren().clear();
        dashSubPanel.getChildren().add(anchorPane);
    }
}
