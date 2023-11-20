package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.Tm.CartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailModel {
    public boolean saveOrderDetails(String orderId, List<CartTm> cartTmList) throws SQLException {
            for(CartTm tm : cartTmList) {
                if(!saveOrderDetails(orderId, tm)) {
                    return false;
                }
            }
            return true;
        }

        private boolean saveOrderDetails(String orderId, CartTm tm) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO orderItemDetails VALUES(?, ?, ?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, orderId);
            pstm.setString(2, tm.getCode());
            pstm.setInt(3, tm.getQty());
            pstm.setDouble(4, tm.getUnitPrice());

            return pstm.executeUpdate() > 0;
        }

    }

