package lk.ijse.BO;

import lk.ijse.dto.ItemDto;
import lk.ijse.dto.Tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface ItemBO {
    List<ItemDto> getAllItems() throws SQLException ;
    boolean saveItem(ItemDto dto) throws SQLException ;
    boolean updateItem(ItemDto dto) throws SQLException ;
    boolean deleteItem(String code) throws SQLException ;
    ItemDto searchItem(String searchId) throws SQLException ;
    boolean updateItem(List<CartTm> cartTmList) throws SQLException;
    boolean updateQty(String code, int qty) throws SQLException ;
    String generateNextItemId() throws SQLException ;
    String changeId(String itemCode);
}
