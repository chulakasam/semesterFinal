package lk.ijse.BO;

import lk.ijse.DAO.Custom.Impl.ItemDAOImpl;
import lk.ijse.DAO.Custom.ItemDAO;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.Tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public class ItemBOImpl implements ItemBO{
    ItemDAO itemDAO=new ItemDAOImpl();
    @Override
    public List<ItemDto> getAllItems() throws SQLException {
        return itemDAO.getAlls();
    }

    @Override
    public boolean saveItem(ItemDto dto) throws SQLException {
        return itemDAO.save(dto);
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException {
        return itemDAO.update(dto);
    }

    @Override
    public boolean deleteItem(String code) throws SQLException {
        return itemDAO.delete(code);
    }

    @Override
    public ItemDto searchItem(String searchId) throws SQLException {
        return itemDAO.search(searchId);
    }

    @Override
    public boolean updateItem(List<CartTm> cartTmList) throws SQLException {
        return itemDAO.updateItem(cartTmList);
    }

    @Override
    public boolean updateQty(String code, int qty) throws SQLException {
        return itemDAO.updateQty(code,qty);
    }

    @Override
    public String generateNextItemId() throws SQLException {
        return itemDAO.generateId();
    }

    @Override
    public String changeId(String itemCode) {
        return itemDAO.changeId(itemCode);
    }
}
