package lk.ijse.DAO.Custom.Impl;

import lk.ijse.DAO.SQLUtil;
import lk.ijse.DAO.Custom.TrainerClientDAO;
import lk.ijse.Entity.TrainerClient;
import lk.ijse.dto.TrainerClientDto;

import java.sql.SQLException;
import java.util.List;

public class TrainerClientDAOImpl  implements TrainerClientDAO {
    @Override
    public boolean SaveDetails(String trainerId, String clientId, String date) throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO trainerClientDetails VALUES(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,trainerId);
        pstm.setString(2,clientId);
        pstm.setString(3,date);

        return pstm.executeUpdate()>0;

         */
        return SQLUtil.test("INSERT INTO trainerClientDetails VALUES(?,?,?)",trainerId,clientId,date);
    }

    @Override
    public List<TrainerClient> getAlls() throws SQLException {
        return null;
    }

    @Override
    public boolean save(TrainerClient dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public <T> T search(String searchId) throws SQLException {
        return null;
    }

    @Override
    public boolean update(TrainerClient dto) throws SQLException {
        return false;
    }

    @Override
    public String generateId() throws SQLException {
        return null;
    }

    @Override
    public String changeId(String clientId) {
        return null;
    }
}
