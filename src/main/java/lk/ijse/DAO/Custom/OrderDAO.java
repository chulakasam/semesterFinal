package lk.ijse.DAO.Custom;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.Entity.Order;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.OrderDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface OrderDAO extends CrudDAO<Order> {
   // String generateNextOrderId() throws SQLException ;
   // String changeId(String orderId);
    boolean saveOrder(String orderId, LocalDate date, String clientId, double netTotal) throws SQLException ;
   // List<OrderDto> getAllCustomer() throws SQLException ;
}
