package lk.ijse.BO.Custom;

import lk.ijse.dto.paymentDto;

import java.sql.SQLException;

public interface PaymentBO {
    String generateNextOrderId() throws SQLException ;
    String splitOrderId(String currentpayId);
    boolean savePayment(paymentDto dto) throws SQLException;
}
