package lk.ijse.DAO.Custom;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.Entity.Trainer;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.TrainerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TrainerDAO extends CrudDAO<Trainer> {
    String searchTrainerTotal() throws SQLException ;
    // boolean saveTrainer(TrainerDto dto) throws SQLException ;
    // boolean UpdateTrainer(TrainerDto dto) throws SQLException ;
     //boolean deleteTrainer(String id) throws SQLException ;
     //TrainerDto searchTrainer(String trainerId) throws SQLException ;
    // List<TrainerDto> LoadAllTrainers() throws SQLException;

     //String generateTrainerId() throws SQLException ;
    // String changeId(String trainerId);
}
