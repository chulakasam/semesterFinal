package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.ClientDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientModel {


    public static List<ClientDto> getAllCustomer() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM client";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<ClientDto> dtoList = new ArrayList<>();
        while(resultSet.next()) {
            dtoList.add(
                    new ClientDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4)
                    )
            );
        }
        return dtoList;
    }

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

    public ClientDto searchClient(String searchId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM client WHERE clientId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,searchId);
        ResultSet resultSet = pstm.executeQuery();
        ClientDto clientDto=null;
        if (resultSet.next()){
            String client_id = resultSet.getString(1);
            String client_name = resultSet.getString(2);
            String client_address = resultSet.getString(3);
           int client_tel = Integer.parseInt(resultSet.getString(4));

            clientDto=new ClientDto(client_id,client_name,client_address,client_tel);
        }
        return clientDto;
    }

    public boolean updateClient(ClientDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="UPDATE client SET name=?,Address=?,contactNo=? WHERE clientId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setInt(3,dto.getTel());
        pstm.setString(4, dto.getId());

        return pstm.executeUpdate()>0;

    }
}
