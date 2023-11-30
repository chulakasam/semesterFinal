package lk.ijse.controller;

import javafx.scene.control.Label;
import lk.ijse.model.ClientModel;
import lk.ijse.model.TrainerModel;

public class dashController {
    public Label lblclientTot;
    public Label lblTrainerTot;
    public Label lblSuppierTot;

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

    }
    public void initialize(){
        loadClientTotal();
        loadTrainerTotal();
        loadSupplierTot();
    }



}
