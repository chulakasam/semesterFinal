package lk.ijse.DAO.Custom;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.paymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<paymentDto> {
    //String generateNextOrderId() throws SQLException ;
   // String splitOrderId(String currentpayId);
    //boolean savePayment(paymentDto dto) throws SQLException ;
}
