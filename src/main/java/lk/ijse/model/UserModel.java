package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public boolean saveUser(UserDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO user VALUES(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getUsername());
        pstm.setString(2, dto.getPassword());
        pstm.setString(3, dto.getEmail());

        return pstm.executeUpdate()>0;
    }

    public UserDto checkcredential(String username, String password) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
     //   String sql="SELECT * FROM user WHERE useName=? AND password=?";
        String sql = "SELECT * FROM user WHERE useName = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,username);
     //   pstm.setString(2,password);

        ResultSet resultSet = pstm.executeQuery();
        UserDto dto=null;
        if(resultSet.next()){
            String useName = resultSet.getString(1);
            String pw = resultSet.getString(2);
            String email = resultSet.getString(3);

            dto=new  UserDto(username,pw);
        }
        return dto;
    }
}
