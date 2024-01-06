package lk.ijse.BO.Custom;

import lk.ijse.DAO.SuperDAO;

import java.sql.SQLException;

public interface TrainerClientBO extends SuperDAO {
    boolean SaveDetails(String trainerId, String clientId, String date) throws SQLException;
}
