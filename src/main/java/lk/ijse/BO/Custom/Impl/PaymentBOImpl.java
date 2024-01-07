package lk.ijse.BO.Custom.Impl;

import lk.ijse.BO.Custom.PaymentBO;
import lk.ijse.DAO.Custom.Impl.PaymentDAOImpl;
import lk.ijse.DAO.Custom.PaymentDAO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.Entity.Payment;
import lk.ijse.dto.paymentDto;

import java.sql.SQLException;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public String generateNextOrderId() throws SQLException {
        return paymentDAO.generateId();
    }

    @Override
    public String splitOrderId(String currentpayId) {
        return paymentDAO.changeId(currentpayId);
    }

    @Override
    public boolean savePayment(paymentDto dto) throws SQLException {
        return paymentDAO.save(new Payment(dto.getPaymentId(),dto.getDate(),dto.getAmount(),dto.getClientId(),dto.getOrderId(),dto.getType()));
    }
}
