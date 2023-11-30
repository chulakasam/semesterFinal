package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.Tm.CartTm;

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
    public boolean updateItem(List<CartTm> cartTmList) throws SQLException {
        for(CartTm tm : cartTmList) {
            System.out.println("Item: " + tm);
            if(!updateQty(tm.getCode(), tm.getQty())) {
                return false;
            }
        }
        return true;
    }
    public boolean updateQty(String code, int qty) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE item SET qtyOnHand = qtyOnHand - ? WHERE itemcode = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, code);

        return pstm.executeUpdate() > 0; //false
    }
    public String generateNextItemId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT itemCode FROM item ORDER BY itemCode DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            return changeId(resultSet.getString(1));
        }
        return changeId(null);
    }
    public static String changeId(String itemCode){
        if(itemCode!=null){
            String[] split = itemCode.split("I0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            if(id<10) {
                return "I00" + id;
            }else{
                return "I0" + id;
            }
        }else{
            return "I001";
        }
    }
}


