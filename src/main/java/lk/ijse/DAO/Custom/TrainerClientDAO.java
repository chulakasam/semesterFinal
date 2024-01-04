package lk.ijse.DAO.Custom;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.TrainerClientDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface TrainerClientDAO extends CrudDAO<TrainerClientDto> {
     boolean SaveDetails(String trainerId, String clientId, String date) throws SQLException ;
}
