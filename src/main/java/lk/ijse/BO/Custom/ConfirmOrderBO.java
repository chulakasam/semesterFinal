package lk.ijse.BO.Custom;

import lk.ijse.dto.ConfirmOrderDto;

import java.sql.SQLException;

public interface ConfirmOrderBO {
      boolean confirmOrder(ConfirmOrderDto confitmOrderDto) throws SQLException;
}
