package lk.ijse.BO.Custom;

import lk.ijse.dto.TrainerClientDto;
import lk.ijse.dto.WorkOutDto;

import java.sql.SQLException;

public interface WorkoutBO {
    boolean setWorkOut(WorkOutDto dto, TrainerClientDto dto1) throws SQLException;
    boolean saveWorkOut(String workOutId, String desc, String trainerId) throws SQLException ;
     String generateworkoutId() throws SQLException ;
     String changeId(String workPlanId) ;
}
