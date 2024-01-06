package lk.ijse.BO.Custom;

import lk.ijse.dto.OrderDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface OrderBO {
    String generateNextOrderId() throws SQLException ;
    String changeId(String orderId);
    boolean saveOrder(String orderId, LocalDate date, String clientId, double netTotal) throws SQLException;
    List<OrderDto > getAllCustomer() throws SQLException ;
}
