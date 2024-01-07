package lk.ijse.BO.Custom.Impl;

import lk.ijse.BO.Custom.SupplierBO;
import lk.ijse.DAO.Custom.Impl.SupplierDAOImpl;
import lk.ijse.DAO.Custom.SupplierDAO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.Entity.Supplier;
import lk.ijse.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {
   SupplierDAO supplierDAO= (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
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
        return supplierDAO.save(new Supplier(dto.getSupplierId(),dto.getName(),dto.getAddress(),dto.getContactNo()));
    }

    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException {
        return supplierDAO.update(new Supplier(dto.getSupplierId(),dto.getName(),dto.getAddress(),dto.getContactNo()));
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
      List<Supplier> all=supplierDAO.getAlls();
        ArrayList<SupplierDto> supplierDto =new ArrayList<>();
        for(Supplier supplier:all){
            supplierDto.add(new SupplierDto(supplier.getSupplierId(),supplier.getName(),supplier.getAddress(),supplier.getContactNo()));
        }
        return supplierDto;
    }
}
