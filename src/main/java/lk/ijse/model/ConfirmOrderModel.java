package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.ConfirmOrderDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class ConfirmOrderModel {


    public static boolean confirmOrder(ConfirmOrderDto confitmOrderDto) throws SQLException {


            System.out.println(confitmOrderDto);

            String orderId = confitmOrderDto.getOrderId();
            LocalDate date = LocalDate.parse(confitmOrderDto.getDate());
            String clientId = confitmOrderDto.getClientId();
            double netTotal = confitmOrderDto.getNetTotal();

            //confitmOrderDto.getCartTmList();
            Connection connection = null;
            try {
                var orderModel = new OrderModel();
                var itemModel = new ItemModel();
                var orderDetailModel = new OrderDetailModel();

                connection = DbConnection.getInstance().getConnection();
                connection.setAutoCommit(false);

                boolean isOrderSaved = orderModel.saveOrder(orderId,date,clientId,netTotal);
                if (isOrderSaved) {
                    boolean isUpdated = itemModel.updateItem(confitmOrderDto.getCartTmList());
                    if (isUpdated) {
                        boolean isOrderDetailSaved = orderDetailModel.saveOrderDetails(confitmOrderDto.getOrderId(),confitmOrderDto.getCartTmList());
                        if (isOrderDetailSaved) {
                            connection.commit();
                        }
                    }
                }
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
            return true;
        }

    }

