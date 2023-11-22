package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.paymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentModel {
    public static String generateNextOrderId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT paymentId FROM payment ORDER BY paymentId  DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }
    private static String splitOrderId(String currentpayId) {
        if(currentpayId!=null){
            String[] split = currentpayId.split("P0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "P00" + id;
        }else{
            return "P001";
        }
    }

    public boolean savePayment(paymentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String  sql="INSERT INTO payment VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getPaymentId());
        pstm.setString(2,dto.getDate());
        pstm.setDouble(3,dto.getAmount());
        pstm.setString(4,dto.getClientId());
        pstm.setString(5,dto.getOrderId());
        pstm.setString(6,dto.getType());

        return pstm.executeUpdate()>0;
    }
}
