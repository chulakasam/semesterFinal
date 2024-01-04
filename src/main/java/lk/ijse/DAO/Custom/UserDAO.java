package lk.ijse.DAO.Custom;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDAO extends CrudDAO<UserDto> {
   // boolean saveUser(UserDto dto) throws SQLException ;

    UserDto checkcredential(String username, String password) throws SQLException;
}
