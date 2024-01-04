package lk.ijse.DAO.Custom.Impl;

import lk.ijse.DAO.Custom.UserDAO;
import lk.ijse.DAO.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<UserDto> getAlls() throws SQLException {
        return null;
    }

    @Override
    public boolean save(UserDto dto) throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO user VALUES(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getUsername());
        pstm.setString(2, dto.getPassword());
        pstm.setString(3, dto.getEmail());

        return pstm.executeUpdate()>0;

         */
        return SQLUtil.test("INSERT INTO user VALUES(?,?,?)",dto.getUsername(),dto.getPassword(),dto.getEmail());
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
    public boolean update(UserDto dto) throws SQLException {
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
    public UserDto checkcredential(String username, String password) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
     //   String sql="SELECT * FROM user WHERE useName=? AND password=?";
        String sql = "SELECT * FROM user WHERE useName = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,username);
     //   pstm.setString(2,password);

        ResultSet resultSet = pstm.executeQuery();


        */

       ResultSet resultSet=SQLUtil.test("SELECT * FROM user WHERE useName=? AND password=?",username,password);

        UserDto dto=null;
        if(resultSet.next()){
            String userName = resultSet.getString(1);
            String pw = resultSet.getString(2);
            String email = resultSet.getString(3);

            dto=new  UserDto(username,pw);
        }
        return dto;
    }
}
