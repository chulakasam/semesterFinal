package lk.ijse.DAO.Custom.Impl;

import lk.ijse.DAO.Custom.ClientDAO;
import lk.ijse.DAO.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.ClientDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    @Override
    public  List<ClientDto> getAlls() throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM client";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        */
       ResultSet resultSet= SQLUtil.test("SELECT * FROM client");
        ArrayList<ClientDto> dtoList = new ArrayList<>();
        while(resultSet.next()) {
            dtoList.add(
                    new ClientDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getString(5),
                            resultSet.getInt(6),
                            resultSet.getInt(7),
                            resultSet.getString(8),
                            resultSet.getString(9)

                    )
            );
        }
        return dtoList;
    }
    @Override
    public  String searchClientTotal() throws SQLException {
        String count="0";
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT COUNT(*) FROM client";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        */
        ResultSet resultSet=SQLUtil.test("SELECT COUNT(*) FROM client");

        if(resultSet.next()){
            count=resultSet.getString(1);
        }
        return count;
    }
    @Override
    public boolean save(ClientDto dto) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO client VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setInt(4,dto.getTel());
        pstm.setString(5, dto.getEmail());
        pstm.setDouble(6,dto.getHeight());
        pstm.setDouble(7,dto.getWeight());
        pstm.setString(8, dto.getGender());
        pstm.setString(9, dto.getDob());

        return pstm.executeUpdate()>0;

        */
        return  SQLUtil.test("INSERT INTO client VALUES(?,?,?,?,?,?,?,?,?)",dto.getId(),dto.getName(),dto.getAddress(),dto.getTel(),dto.getEmail(),dto.getHeight(),dto.getWeight(),dto.getGender(),dto.getDob());
    }
    @Override
    public boolean delete(String id) throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql="DELETE FROM  client WHERE clientId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);
        return pstm.executeUpdate()>0;

         */
        return SQLUtil.test("DELETE FROM  client WHERE clientId=?",id);
    }
    @Override
    public ClientDto search(String searchId) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM client WHERE clientId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,searchId);
        ResultSet resultSet = pstm.executeQuery();

        return clientDto;

        */
        ResultSet resultSet= SQLUtil.test("SELECT * FROM client WHERE clientId=?",searchId);

        ClientDto clientDto=null;
        if (resultSet.next()){
            String client_id = resultSet.getString(1);
            String client_name = resultSet.getString(2);
            String client_address = resultSet.getString(3);
            int client_tel = Integer.parseInt(resultSet.getString(4));
            String email = resultSet.getString(5);
            int height = Integer.parseInt((resultSet.getString(6)));
            int weight = (int) Double.parseDouble(resultSet.getString(7));
            String gender = resultSet.getString(8);
            String dob = resultSet.getString(9);

            clientDto=new ClientDto(client_id,client_name,client_address,client_tel,email,height,weight,gender,dob);
        }
        return clientDto;
    }
    @Override
    public boolean update(ClientDto dto) throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql="UPDATE client SET name=?,Address=?,contactNo=? ,email=?,height=?,weight=?,gender=?,dob=? WHERE clientId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setInt(3,dto.getTel());
        pstm.setString(4, dto.getEmail());
        pstm.setDouble(5,dto.getHeight());
        pstm.setDouble(6,dto.getWeight());
        pstm.setString(7, dto.getGender());
        pstm.setString(8, dto.getDob());
        pstm.setString(9, dto.getId());

        return pstm.executeUpdate()>0;

         */
        return SQLUtil.test("UPDATE client SET name=?,Address=?,contactNo=? ,email=?,height=?,weight=?,gender=?,dob=? WHERE clientId=?",dto.getName(),dto.getAddress(),dto.getTel(),dto.getEmail(),dto.getHeight(),dto.getWeight(),dto.getGender(),dto.getDob(),dto.getId());
    }
    @Override
    public String generateId() throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT clientId FROM client ORDER BY clientId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        */
        ResultSet resultSet=SQLUtil.test("SELECT clientId FROM client ORDER BY clientId DESC LIMIT 1");
        if(resultSet.next()){
            return changeId(resultSet.getString(1));
        }
        return changeId(null);
    }
    @Override
    public  String changeId(String clientId){
        if(clientId!=null){
            String[] split = clientId.split("C0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            if(id<10) {
                return "C00" + id;
            }else{
                return "C0" + id;
            }
        }else{
            return "C001";
        }
    }

    }

