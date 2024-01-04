package lk.ijse.DAO.Custom.Impl;

import lk.ijse.DAO.Custom.PaymentDAO;
import lk.ijse.DAO.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.paymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public  String generateId() throws SQLException {
      /*  Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT paymentId FROM payment ORDER BY paymentId  DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet= SQLUtil.test("SELECT paymentId FROM payment ORDER BY paymentId  DESC LIMIT 1");
        if(resultSet.next()){
            return changeId(resultSet.getString(1));
        }
        return changeId(null);
    }
    @Override
    public   String changeId(String currentpayId) {
        if(currentpayId!=null){
            String[] split = currentpayId.split("P0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            if(id<10) {
                return "P00" + id;
            }else{
                return "P0" + id;
            }
        }else{
            return "P001";
        }
    }

    @Override
    public List<paymentDto> getAlls() throws SQLException {
        return null;
    }

    @Override
    public boolean save(paymentDto dto) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String  sql="INSERT INTO payment VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getPaymentId());
        pstm.setString(2,dto.getDate());
        pstm.setDouble(3,dto.getAmount());
        pstm.setString(4,dto.getClientId());
        pstm.setString(5,dto.getOrderId());
        pstm.setString(6,dto.getType());

        return pstm.executeUpdate()>0;*/
        return SQLUtil.test("INSERT INTO payment VALUES(?,?,?,?,?,?)",dto.getPaymentId(),dto.getDate(),dto.getAmount(),dto.getClientId(),dto.getOrderId(),dto.getType());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public <T> T search(String searchId) throws SQLException {
        return null;
    }

    @Override
    public boolean update(paymentDto dto) throws SQLException {
        return false;
    }
}
