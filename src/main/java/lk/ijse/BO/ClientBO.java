package lk.ijse.BO;

import lk.ijse.dto.ClientDto;

import java.sql.SQLException;
import java.util.List;

public interface ClientBO {
    List<ClientDto> getAllCustomer() throws SQLException;
    String searchClientTotal() throws SQLException;
    boolean saveClient(ClientDto dto) throws SQLException ;
     boolean deleteClient(String id) throws SQLException;
    ClientDto searchClient(String searchId) throws SQLException ;
    boolean updateClient(ClientDto dto) throws SQLException ;
    String generateClientId() throws SQLException;
    String changeId(String clientId);
}
