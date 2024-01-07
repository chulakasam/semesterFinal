package lk.ijse.BO.Custom.Impl;

import lk.ijse.BO.Custom.UserBO;
import lk.ijse.DAO.Custom.Impl.UserDAOImpl;
import lk.ijse.DAO.Custom.UserDAO;
import lk.ijse.Entity.User;
import lk.ijse.dto.UserDto;

import java.sql.SQLException;

public class UserBOImpl implements UserBO {
    UserDAO userDAO=new UserDAOImpl();
    @Override
    public boolean saveUser(UserDto dto) throws SQLException {
        return userDAO.save(new User(dto.getUsername(),dto.getPassword(),dto.getEmail()));
    }

    @Override
    public UserDto checkcredential(String username, String password) throws SQLException {
        return userDAO.checkcredential(username, password);
    }
}
