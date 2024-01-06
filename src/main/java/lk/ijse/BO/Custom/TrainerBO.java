package lk.ijse.BO.Custom;

import lk.ijse.dto.TrainerDto;

import java.sql.SQLException;
import java.util.List;

public interface TrainerBO {
    String searchTrainerTotal() throws SQLException;
     boolean saveTrainer(TrainerDto dto) throws SQLException ;
     boolean UpdateTrainer(TrainerDto dto) throws SQLException ;
     boolean deleteTrainer(String id) throws SQLException ;
    TrainerDto searchTrainer(String trainerId) throws SQLException ;
    List<TrainerDto> LoadAllTrainers() throws SQLException;

    String generateTrainerId() throws SQLException ;
    String changeId(String trainerId);
}
