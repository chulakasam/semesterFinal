package lk.ijse.BO;

import lk.ijse.dto.SupplierDto;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO {
    String generateSupplierId() throws SQLException ;
    String changeId(String supplierId);

    String searchSupplierTotal() throws SQLException;

    boolean saveSupplier(SupplierDto dto) throws SQLException ;
    boolean updateSupplier(SupplierDto dto) throws SQLException ;
    boolean deleteSupplier(String id) throws SQLException ;
    SupplierDto searchSupplier(String suppId) throws SQLException ;

    List<SupplierDto> getAllSupplier() throws SQLException ;
}
