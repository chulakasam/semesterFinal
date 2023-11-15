package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.ItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModel {
    public static List<ItemDto> getAllItems() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM item";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<ItemDto> list = new ArrayList<>();
        while (resultSet.next()){

            list.add(
                    new ItemDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getDouble(3),
                            resultSet.getInt(4)
                    )
            );
        }
        return list;
    }
    public boolean saveItem(ItemDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO item VALUES(?,?,?,?)";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getItemCode());
        pstm.setString(2, dto.getName());
        pstm.setDouble(3,dto.getUnitPrice());
        pstm.setInt(4,dto.getQty());

        return  pstm.executeUpdate()>0;
    }
    public boolean updateItem(ItemDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="UPDATE item SET name=?,unitPrice=?,qtyOnHand=? WHERE itemCode=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getName());
        pstm.setDouble(2,dto.getUnitPrice());
        pstm.setInt(3,dto.getQty());
        pstm.setString(4, dto.getItemCode());

        return pstm.executeUpdate()>0;
    }
    public boolean deleteItem(String code) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="DELETE FROM item WHERE itemCode=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,code);

        return pstm.executeUpdate()>0;
    }
    public ItemDto searchItem(String searchId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT *FROM item WHERE itemCode=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,searchId);
        ResultSet resultSet = pstm.executeQuery();
        ItemDto itemDto=null;
        if(resultSet.next()){
            String code = resultSet.getString(1);
            String name = resultSet.getString(2);
            double price = resultSet.getDouble(3);
            int qty = resultSet.getInt(4);

            itemDto=new ItemDto(code,name,price,qty);
        }
           return itemDto;
    }

}
