package lk.ijse.DAO.Custom;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.Entity.Supplier;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO  extends CrudDAO<Supplier> {
     //String generateSupplierId() throws SQLException ;
    // String changeId(String supplierId);

     String searchSupplierTotal() throws SQLException ;

     //boolean saveSupplier(SupplierDto dto) throws SQLException ;
    // boolean updateSupplier(SupplierDto dto) throws SQLException ;
    //boolean deleteSupplier(String id) throws SQLException ;
     //SupplierDto searchSupplier(String suppId) throws SQLException ;

    //ArrayList<SupplierDto> getAllSupplier() throws SQLException ;
}
