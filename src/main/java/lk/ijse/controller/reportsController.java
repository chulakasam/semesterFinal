package lk.ijse.controller;

import javafx.event.ActionEvent;
import lk.ijse.db.DbConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;

public class reportsController {
    public void btnClientReportsAction(ActionEvent actionEvent) throws JRException, SQLException {

        InputStream resourceAsStream = getClass().getResourceAsStream("/report/client Report.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());


        JasperViewer.viewReport(jasperPrint, false);


    }
    public void btnPaymentReportsOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/report.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());


        JasperViewer.viewReport(jasperPrint, false);
    }
    public void btnOrdersReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/ordersReport.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());


        JasperViewer.viewReport(jasperPrint, false);
    }
    public void btnStockReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/stock report.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());


        JasperViewer.viewReport(jasperPrint, false);
    }
}
