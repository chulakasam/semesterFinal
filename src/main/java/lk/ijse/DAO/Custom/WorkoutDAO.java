package lk.ijse.DAO.Custom;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.Entity.TrainerClient;
import lk.ijse.Entity.WorkOut;
import lk.ijse.dto.TrainerClientDto;
import lk.ijse.dto.WorkOutDto;

import java.sql.SQLException;

public interface WorkoutDAO extends CrudDAO<WorkOut> {
     boolean setWorkOut(WorkOut dto, TrainerClient dto1) throws SQLException ;
     boolean saveWorkOut(String workOutId, String desc, String trainerId) throws SQLException ;
    // String generateworkoutId() throws SQLException ;
     //String changeId(String workPlanId) ;
}
