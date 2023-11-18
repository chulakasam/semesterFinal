package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.ClientDto;
import lk.ijse.dto.Tm.ClientTm;
import lk.ijse.model.ClientModel;

import java.sql.SQLException;
import java.util.List;

public class viewClientController {

    public TableView <ClientTm> tblClientTm;
    public TableColumn <?,?> colID;
    public TableColumn <?,?> colName;
    public TableColumn <?,?> colAddress;
    public TableColumn <?,?> colTel;
    public TableColumn <?,?> colEmail;
    public TableColumn <?,?> colHeight;
    public TableColumn <?,?> colGen;
    public TableColumn <?,?> colWei;
    public TableColumn <?,?> colDOB;


    public void initialize(){
        loadClientDetails();
        setCellValueFactory();
    }
    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colHeight.setCellValueFactory(new PropertyValueFactory<>("height"));
        colWei.setCellValueFactory(new PropertyValueFactory<>("weight"));
        colGen.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
    }
    private void loadClientDetails() {

        var model = new ClientModel();
        ObservableList<ClientTm> obList = FXCollections.observableArrayList();

        try{
            List<ClientDto> dtoList = model.getAllCustomer();

            for (ClientDto dto : dtoList) {
                obList.add(
                        new ClientTm(

                                dto.getId(),
                                dto.getName(),
                                dto.getAddress(),
                                dto.getTel(),
                                dto.getEmail(),
                                (int) dto.getHeight(),
                                (int) dto.getWeight(),
                                dto.getGender(),
                                dto.getDob()
                        )
                );
            }
            tblClientTm.setItems(obList);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
