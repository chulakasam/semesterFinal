package lk.ijse.BO.Custom.Impl;

import lk.ijse.BO.Custom.OrderBO;
import lk.ijse.DAO.Custom.Impl.OrderDAOImpl;
import lk.ijse.DAO.Custom.OrderDAO;
import lk.ijse.dto.OrderDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO=new OrderDAOImpl();
    @Override
    public String generateNextOrderId() throws SQLException {
        return orderDAO.generateId();
    }

    @Override
    public String changeId(String orderId) {
        return orderDAO.changeId(orderId);
    }

    @Override
    public boolean saveOrder(String orderId, LocalDate date, String clientId, double netTotal) throws SQLException {
        return orderDAO.saveOrder(orderId,date,clientId,netTotal);
    }

    @Override
    public List<OrderDto> getAllCustomer() throws SQLException {
        return orderDAO.getAlls();
    }
}
