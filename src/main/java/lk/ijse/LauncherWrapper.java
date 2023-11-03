package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LauncherWrapper extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/startPage_form.fxml"));
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
