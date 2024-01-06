package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.Custom.ClientBO;
import lk.ijse.BO.Custom.Impl.ClientBOImpl;
import lk.ijse.BO.Custom.Impl.ItemBOImpl;
import lk.ijse.BO.Custom.Impl.OrderBOImpl;
import lk.ijse.BO.Custom.ItemBO;
import lk.ijse.BO.Custom.OrderBO;
import lk.ijse.dto.ClientDto;
import lk.ijse.dto.ConfirmOrderDto;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.Tm.CartTm;
import lk.ijse.DAO.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderController {
    public Label lblDate;
    public JFXComboBox <String> cmbClient;
    public JFXComboBox <String> cmbItem;
    public Label txtClientname;
    public Label lblItemName;
    public Label lblPrice;
    public TableColumn <?,?> colQty;
    public TextField txtQty;
    public Label lblNetTotal;
    public TableColumn <?,?> colItemcode;
    public TableColumn <?,?> coldescription;
    public TableColumn <?,?>colUnitprice;
    public TableColumn<?,?> colTotal;
    public TableColumn<?,?> colAction;
    public TableView <CartTm>tblOrderCart;
    public JFXButton btnAddtoCart;
    public ObservableList<CartTm> obList = FXCollections.observableArrayList();
    public Label lblOrderId;
    public AnchorPane orderPanel;

    //ItemDAO itemDAOImpl = new ItemDAOImpl();
    //ClientDAO clientDAOImpl = new ClientDAOImpl();
    //OrderDAO orderDAOImpl=new OrderDAOImpl();
    ItemBO itemBO= new ItemBOImpl();
    ClientBO clientBO=new ClientBOImpl();
    OrderBO orderBO=new OrderBOImpl();

    public void setDate(){
       lblDate.setText(String.valueOf(LocalDate.now()));
    }
    public  void initialize(){
        setCellValueFactory();
        setDate();
        LoadAllClients();
        loadAllItem();
        generateOrderId();
    }
    public void LoadAllClients() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            
            List<ClientDto> idList = clientBO.getAllCustomer();

            for (ClientDto dto : idList) {
                obList.add(dto.getId());
            }

            cmbClient.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void loadAllItem(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            
            List<ItemDto> codeList= itemBO.getAllItems();
            for (ItemDto dto:codeList){
                obList.add(dto.getItemCode());
            }
            cmbItem.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
    public void cmbClientOnAction(ActionEvent actionEvent) throws SQLException {
        String Id = cmbClient.getValue();
        try{
          
           ClientDto dto=clientBO.searchClient(Id);
           txtClientname.setText(dto.getName());
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }
    public void cmbItemOnAction(ActionEvent actionEvent) {
        String code = cmbItem.getValue();

        try{
           
            ItemDto dto=itemBO.searchItem(code);
            lblItemName.setText(dto.getName());
            lblPrice.setText(String.valueOf(dto.getUnitPrice()));
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }
    public void btnAddTocartOnAction(ActionEvent actionEvent) {
        String code = cmbItem.getValue();
        String description = lblItemName.getText();
        double unitPrice = Double.parseDouble(lblPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double tot=unitPrice * qty;
        Button btn=new Button("DELETE");

        setRemoveBtnAction(btn);
        btn.setCursor(Cursor.HAND);

        if (!obList.isEmpty()) {
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                if (colItemcode.getCellData(i).equals(code)) {
                    int col_qty = (int) colQty.getCellData(i);
                    qty += col_qty;
                    tot = unitPrice * qty;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTot(tot);

                    calculateTotal();
                    tblOrderCart.refresh();
                    return;
                }
            }
        }
        var cartTm = new CartTm(code, description, qty, unitPrice, tot, btn);

        obList.add(cartTm);

        tblOrderCart.setItems(obList);
        calculateTotal();
        txtQty.clear();
    }
    private void setRemoveBtnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int focusedIndex = tblOrderCart.getSelectionModel().getSelectedIndex();

                obList.remove(focusedIndex);
                tblOrderCart.refresh();
                calculateTotal();
            }
        });
    }
    private void calculateTotal() {
        double total = 0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            total += (double) colTotal.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(total));
    }
    private void setCellValueFactory(){
        colItemcode.setCellValueFactory(new PropertyValueFactory<>("code"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitprice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("tot"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }
    public void generateOrderId(){
        try {
            String id = orderBO.generateNextOrderId();
           lblOrderId.setText(id);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void btnConfirmOrderOnAction(ActionEvent actionEvent) {
        String orderId = lblOrderId.getText();
        String date = lblDate.getText();
        String clientId = cmbClient.getValue();
        double netTotal = Double.parseDouble(lblNetTotal.getText());
        List<CartTm> cartTmList = new ArrayList<>();

        for (int i=0;i<tblOrderCart.getItems().size();i++){
            CartTm cartTm = obList.get(i);
            cartTmList.add(cartTm);
        }
        var confirmOrderDto = new ConfirmOrderDto(orderId, date, clientId, netTotal, cartTmList);
        var confirmOrderModel = new ConfirmOrderModel();
        try {
            boolean isSuccess = confirmOrderModel.confirmOrder(confirmOrderDto);
            if (isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Success!").show();
            }
        } catch (SQLException e) {
          //new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }

    }
    public void btnBackToHomeOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashform.fxml"));
        orderPanel.getChildren().clear();
        orderPanel.getChildren().add(anchorPane);
    }
}
