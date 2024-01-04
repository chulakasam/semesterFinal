package lk.ijse.DAO.Custom;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.Tm.CartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO extends CrudDAO {
     boolean saveOrderDetails(String orderId, List<CartTm> cartTmList) throws SQLException ;
      boolean saveOrderDetails(String orderId, CartTm tm) throws SQLException ;
}
