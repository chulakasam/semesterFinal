package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.db.DbConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class dashboardController {
    public AnchorPane dashPanel;
    public AnchorPane dashSubPanel;
    public Label lblDate;
    public Label lblIme;
    public BarChart <?,?>tbldashboard;



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
        lblIme.setText(String.valueOf(LocalTime.now()));
    }
    public void initialize(){
        setDate();
        setBarchart();
    }

    public void btnGenerateReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {

        InputStream resourceAsStream = getClass().getResourceAsStream("/report/ordersReport.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());


        JasperViewer.viewReport(jasperPrint, false);
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
}
