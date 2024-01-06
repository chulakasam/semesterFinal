package lk.ijse.BO.Custom.Impl;

import lk.ijse.BO.Custom.ClientBO;
import lk.ijse.DAO.Custom.ClientDAO;
import lk.ijse.DAO.Custom.Impl.ClientDAOImpl;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.dto.ClientDto;

import java.sql.SQLException;
import java.util.List;

public class ClientBOImpl implements ClientBO {

    ClientDAO clientDAO= (ClientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CLIENT);
    @Override
    public List<ClientDto> getAllCustomer() throws SQLException {
        return clientDAO.getAlls();
    }

    @Override
    public String searchClientTotal() throws SQLException {
        return clientDAO.searchClientTotal();
    }

    @Override
    public boolean saveClient(ClientDto dto) throws SQLException {
        return clientDAO.save(new ClientDto(dto.getId(),dto.getName(),dto.getAddress(),dto.getTel(),dto.getEmail(), (int) dto.getHeight(), (int) dto.getWeight(),dto.getGender(),dto.getDob()));
    }

    @Override
    public boolean deleteClient(String id) throws SQLException {
        return clientDAO.delete(id);
    }

    @Override
    public ClientDto searchClient(String searchId) throws SQLException {
        return clientDAO.search(searchId);
    }

    @Override
    public boolean updateClient(ClientDto dto) throws SQLException {
        return clientDAO.update(new ClientDto(dto.getId(),dto.getName(),dto.getAddress(),dto.getTel(),dto.getEmail(), (int) dto.getHeight(), (int) dto.getWeight(),dto.getGender(),dto.getDob()));
    }

    @Override
    public String generateClientId() throws SQLException {
        return clientDAO.generateId();
    }

    @Override
    public String changeId(String clientId) {
        return clientDAO.changeId(clientId);
    }
}
