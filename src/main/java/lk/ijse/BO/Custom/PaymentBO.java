package lk.ijse.BO.Custom;

import lk.ijse.DAO.SuperDAO;
import lk.ijse.dto.paymentDto;

import java.sql.SQLException;

public interface PaymentBO extends SuperDAO {
    String generateNextOrderId() throws SQLException ;
    String splitOrderId(String currentpayId);
    boolean savePayment(paymentDto dto) throws SQLException;
}
