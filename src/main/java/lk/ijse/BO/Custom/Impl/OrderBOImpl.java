package lk.ijse.BO.Custom.Impl;

import lk.ijse.BO.Custom.OrderBO;
import lk.ijse.DAO.Custom.Impl.OrderDAOImpl;
import lk.ijse.DAO.Custom.OrderDAO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.Entity.Order;
import lk.ijse.dto.OrderDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO= (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
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
       List<Order> all= orderDAO.getAlls();
        ArrayList<OrderDto> orderDto = new ArrayList<>();
        for (Order order:all) {
            orderDto.add(new OrderDto(order.getOrderId(),order.getDate(),order.getClientId(),order.getAmount()));
        }
        return orderDto;
    }
}
