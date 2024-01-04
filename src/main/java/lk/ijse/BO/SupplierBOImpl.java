package lk.ijse.BO;

import lk.ijse.DAO.Custom.Impl.SupplierDAOImpl;
import lk.ijse.DAO.Custom.SupplierDAO;
import lk.ijse.dto.SupplierDto;

import java.sql.SQLException;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {
   SupplierDAO supplierDAO= new SupplierDAOImpl();
    @Override
    public String generateSupplierId() throws SQLException {
        return supplierDAO.generateId();
    }

    @Override
    public String changeId(String supplierId) {
        return supplierDAO.changeId(supplierId);
    }

    @Override
    public String searchSupplierTotal() throws SQLException {
        return supplierDAO.searchSupplierTotal();
    }

    @Override
    public boolean saveSupplier(SupplierDto dto) throws SQLException {
        return supplierDAO.save(dto);
    }

    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException {
        return supplierDAO.update(dto);
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException {
        return supplierDAO.delete(id);
    }

    @Override
    public SupplierDto searchSupplier(String suppId) throws SQLException {
        return supplierDAO.search(suppId);
    }

    @Override
    public List<SupplierDto> getAllSupplier() throws SQLException {
        return supplierDAO.getAlls();
    }
}
