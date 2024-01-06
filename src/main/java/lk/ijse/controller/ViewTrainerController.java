package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.BO.Custom.Impl.BOFactory;
import lk.ijse.BO.Custom.Impl.TrainerBOImpl;
import lk.ijse.BO.Custom.TrainerBO;
import lk.ijse.dto.Tm.TrainerTm;
import lk.ijse.dto.TrainerDto;
import lk.ijse.DAO.Custom.Impl.TrainerDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class ViewTrainerController {
    public TableView <TrainerTm>tblTrainerTm;
    public TableColumn <?,?>colTrainerId;
    public TableColumn <?,?>colName;
    public TableColumn <?,?>colEmail;
    public TableColumn <?,?>colDob;
    public TableColumn <?,?>colTel;
    public TableColumn <?,?>colNIc;
    public TableColumn <?,?>colGen;
    public TableColumn <?,?>colDesc;
    public JFXButton closBtn;
    TrainerBO trainerBO= (TrainerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TRAINER);

    public void initialize(){
        loadAllTrainers();
        setCellValueFactory();
    }
    private void setCellValueFactory() {
        colTrainerId.setCellValueFactory(new PropertyValueFactory<>("trainerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colNIc.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colGen.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
    }
    private void loadAllTrainers() {

        ObservableList<TrainerTm> obList = FXCollections.observableArrayList();
        try{
            List<TrainerDto> dtoList=trainerBO.LoadAllTrainers();

            for (TrainerDto dto:dtoList){

                obList.add(
                  new TrainerTm(
                          dto.getTrainerId(),
                          dto.getName(),
                          dto.getEmail(),
                          dto.getDob(),
                          dto.getTel(),
                          dto.getNic(),
                          dto.getGender(),
                          dto.getDesc()
                ));
            }
            tblTrainerTm.setItems(obList);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        Stage window = (Stage) closBtn.getScene().getWindow();
        window.close();
    }
}
