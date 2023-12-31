package lk.ijse.BO.Custom.Impl;

import lk.ijse.BO.Custom.ItemBO;
import lk.ijse.DAO.Custom.Impl.ItemDAOImpl;
import lk.ijse.DAO.Custom.ItemDAO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.Entity.Item;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.Tm.CartTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public List<ItemDto> getAllItems() throws SQLException {
        List<Item>  all= itemDAO.getAlls();
        ArrayList<ItemDto> itemDto = new ArrayList<>();
        for(Item item:all){
            itemDto.add(new ItemDto(item.getItemCode(),item.getName(),item.getUnitPrice(),item.getQty()));
        }
        return itemDto;
    }

    @Override
    public boolean saveItem(ItemDto dto) throws SQLException {
        return itemDAO.save(new Item(dto.getItemCode(),dto.getName(),dto.getUnitPrice(),dto.getQty()));
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException {
        return itemDAO.update(new Item(dto.getItemCode(),dto.getName(),dto.getUnitPrice(),dto.getQty()));
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
