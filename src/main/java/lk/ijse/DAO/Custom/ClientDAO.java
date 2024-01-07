package lk.ijse.DAO.Custom;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.Entity.Client;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.ClientDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ClientDAO extends CrudDAO<Client> {
    //List<ClientDto> getAllCustomer() throws SQLException;
     String searchClientTotal() throws SQLException ;
    //boolean saveClient(ClientDto dto) throws SQLException ;
    // boolean deleteClient(String id) throws SQLException;
    //ClientDto searchClient(String searchId) throws SQLException ;
    //boolean updateClient(ClientDto dto) throws SQLException ;
    //String generateClientId() throws SQLException;
    //String changeId(String clientId);
}
