package lk.ijse.DAO.Custom.Impl;

import lk.ijse.DAO.Custom.OrderDetailDAO;
import lk.ijse.DAO.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.Tm.CartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
        public boolean saveOrderDetails(String orderId, List<CartTm> cartTmList) throws SQLException {
            for(CartTm tm : cartTmList) {
                if(!saveOrderDetails(orderId, tm)) {
                    return false;
                }
            }
            return true;
        }
        public boolean saveOrderDetails(String orderId, CartTm tm) throws SQLException {
          /*  Connection connection = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO orderItemDetails VALUES(?, ?, ?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, orderId);
            pstm.setString(2, tm.getCode());
            pstm.setInt(3, tm.getQty());
            pstm.setDouble(4, tm.getUnitPrice());

            return pstm.executeUpdate() > 0;*/
            return SQLUtil.test("INSERT INTO orderItemDetails VALUES(?, ?, ?,?)",orderId,tm.getCode(),tm.getQty(),tm.getUnitPrice());
        }

    @Override
    public List getAlls() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Object dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Object dto) throws SQLException {
        return false;
    }

    @Override
    public String generateId() throws SQLException {
        return null;
    }

    @Override
    public String changeId(String clientId) {
        return null;
    }

    @Override
    public Object search(String searchId) throws SQLException {
        return null;
    }
}

