package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class dashboardController {
    public AnchorPane dashPanel;
    public AnchorPane dashSubPanel;


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
}
