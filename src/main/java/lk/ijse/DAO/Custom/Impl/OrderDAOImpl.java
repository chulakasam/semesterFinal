package lk.ijse.DAO.Custom.Impl;

import lk.ijse.DAO.Custom.OrderDAO;
import lk.ijse.DAO.SQLUtil;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.OrderDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public  String generateId() throws SQLException {
      /*  Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet= SQLUtil.test("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");
        if(resultSet.next()){
            return changeId(resultSet.getString(1));
        }
        return changeId(null);
    }
    @Override
    public  String changeId(String orderId){
        if(orderId!=null){
            String[] split = orderId.split("O0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            if(id<10) {
                return "O00" + id;
            }else{
                return "O0" + id;
            }
        }else{
            return "O001";
        }
    }
    @Override
    public boolean saveOrder(String orderId, LocalDate date, String clientId, double netTotal) throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO orders VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, orderId);
        pstm.setDate(2, Date.valueOf(date));
        pstm.setString(3, clientId);
        pstm.setDouble(4, netTotal);

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.test("INSERT INTO orders VALUES(?,?,?,?)",orderId,date,clientId,netTotal);

    }
    @Override
    public List<OrderDto> getAlls() throws SQLException {
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM orders";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();*/
        ArrayList<OrderDto> dto = new ArrayList<>();
        ResultSet resultSet=SQLUtil.test("SELECT * FROM orders");
        while (resultSet.next()){
            dto.add(new OrderDto(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)

            ));
        }
       return  dto;
    }

    @Override
    public boolean save(OrderDto dto) throws SQLException {
        return false;
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
    public boolean update(OrderDto dto) throws SQLException {
        return false;
    }
}
