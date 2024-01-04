package lk.ijse.DAO;

import lk.ijse.DAO.Custom.Impl.ItemDAOImpl;
import lk.ijse.DAO.Custom.Impl.OrderDAOImpl;
import lk.ijse.DAO.Custom.Impl.OrderDetailDAOImpl;
import lk.ijse.DAO.Custom.ItemDAO;
import lk.ijse.DAO.Custom.OrderDAO;
import lk.ijse.DAO.Custom.OrderDetailDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.ConfirmOrderDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class ConfirmOrderModel {
    public static boolean confirmOrder(ConfirmOrderDto confitmOrderDto) throws SQLException {
        OrderDAO orderDAO = new OrderDAOImpl();
        ItemDAO itemDAO = new ItemDAOImpl();
        OrderDetailDAO  orderDetailDAO = new OrderDetailDAOImpl();

        System.out.println(confitmOrderDto);

            String orderId = confitmOrderDto.getOrderId();
            LocalDate date = LocalDate.parse(confitmOrderDto.getDate());
            String clientId = confitmOrderDto.getClientId();
            double netTotal = confitmOrderDto.getNetTotal();

            //confitmOrderDto.getCartTmList();
            Connection connection = null;
            try {


                connection = DbConnection.getInstance().getConnection();
                connection.setAutoCommit(false);

                boolean isOrderSaved = orderDAO.saveOrder(orderId,date,clientId,netTotal);
                if (isOrderSaved) {
                    boolean isUpdated = itemDAO.updateItem(confitmOrderDto.getCartTmList());
                    if (isUpdated) {
                        boolean isOrderDetailSaved = orderDetailDAO.saveOrderDetails(confitmOrderDto.getOrderId(),confitmOrderDto.getCartTmList());
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

