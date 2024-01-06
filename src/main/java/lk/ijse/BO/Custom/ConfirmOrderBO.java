package lk.ijse.BO.Custom;

import lk.ijse.DAO.SuperDAO;
import lk.ijse.dto.ConfirmOrderDto;

import java.sql.SQLException;

public interface ConfirmOrderBO extends SuperDAO {
      boolean confirmOrder(ConfirmOrderDto confitmOrderDto) throws SQLException;
}
