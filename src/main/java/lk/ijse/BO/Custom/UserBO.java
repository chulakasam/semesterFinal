package lk.ijse.BO.Custom;

import lk.ijse.DAO.SuperDAO;
import lk.ijse.dto.UserDto;

import java.sql.SQLException;

public interface UserBO extends SuperDAO {
    boolean saveUser(UserDto dto) throws SQLException ;

    UserDto checkcredential(String username, String password) throws SQLException;
}
