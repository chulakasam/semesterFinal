package lk.ijse.DAO;

import lk.ijse.dto.ClientDto;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T> {
    List<T> getAlls() throws SQLException;

    boolean save(T dto) throws SQLException ;
    boolean delete(String id) throws SQLException;
    <T>T search(String searchId) throws SQLException ;
    boolean update(T dto) throws SQLException ;
    String generateId() throws SQLException;
    String changeId(String clientId);
}
