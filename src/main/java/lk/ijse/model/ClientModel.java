package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.ClientDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientModel {


    public boolean saveClient(ClientDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO client VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setInt(4,dto.getTel());



        return pstm.executeUpdate()>0;
    }

    public boolean deleteClient(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="DELETE FROM  client WHERE clientId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);

        return pstm.executeUpdate()>0;
    }
}
