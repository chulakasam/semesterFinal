package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {


    public static String generateSupplierId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT  supplierId FROM supplier ORDER BY supplierId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            return changeId(resultSet.getString(1));
        }
        return changeId(null);
    }
    public static String changeId(String supplierId){
        if(supplierId!=null){
            String[] split = supplierId.split("S0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            if(id<10) {
                return "S00" + id;
            }else{
                return "S0" + id;
            }
        }else{
            return "S001";
        }
    }

    public static String searchSupplierTotal() throws SQLException {
        String count="0";
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT COUNT(*) FROM supplier";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            count=resultSet.getString(1);
        }
        return count;
    }

    public boolean saveSupplier(SupplierDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO supplier VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getSupplierId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setInt(4,dto.getContactNo());

        return pstm.executeUpdate()>0;
    }
    public boolean updateSupplier(SupplierDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="UPDATE supplier SET name=?,Address=?,contactNo=? WHERE supplierId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setInt(3,dto.getContactNo());
        pstm.setString(4, dto.getSupplierId());

        return pstm.executeUpdate()>0;
    }
    public boolean deleteSupplier(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="DELETE FROM supplier WHERE supplierId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);
        return pstm.executeUpdate()>0;

    }
    public SupplierDto searchSupplier(String suppId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM supplier WHERE supplierId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,suppId);
        ResultSet resultSet = pstm.executeQuery();
        SupplierDto dto=null;
        if(resultSet.next()){
            String Id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            int tel = resultSet.getInt(4);

            dto=new SupplierDto(Id,name,address,tel);
        }
       return dto;
    }

    public ArrayList<SupplierDto> getAllSupplier() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM supplier";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<SupplierDto> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(
                    new SupplierDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4)
                    )
            );

        }
        return list;
    }
}
