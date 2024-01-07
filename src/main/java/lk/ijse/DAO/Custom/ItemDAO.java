package lk.ijse.DAO.Custom;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.Entity.Item;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.Tm.CartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {
     //List<ItemDto> getAllItems() throws SQLException ;
     //boolean saveItem(ItemDto dto) throws SQLException ;
     //boolean updateItem(ItemDto dto) throws SQLException ;
     //boolean deleteItem(String code) throws SQLException ;
     //ItemDto searchItem(String searchId) throws SQLException ;
     boolean updateItem(List<CartTm> cartTmList) throws SQLException ;
     boolean updateQty(String code, int qty) throws SQLException ;
     //String generateNextItemId() throws SQLException ;
     //String changeId(String itemCode);
}
