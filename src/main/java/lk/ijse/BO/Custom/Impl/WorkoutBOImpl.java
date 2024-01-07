package lk.ijse.BO.Custom.Impl;

import lk.ijse.BO.Custom.WorkoutBO;
import lk.ijse.DAO.Custom.Impl.WorkoutDAOImpl;
import lk.ijse.DAO.Custom.WorkoutDAO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.Entity.TrainerClient;
import lk.ijse.Entity.WorkOut;
import lk.ijse.dto.TrainerClientDto;
import lk.ijse.dto.WorkOutDto;

import java.sql.SQLException;

public class WorkoutBOImpl implements WorkoutBO {
   WorkoutDAO workoutDAO= (WorkoutDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.WORKOUT);
    @Override
    public boolean setWorkOut(WorkOutDto dto, TrainerClientDto dto1) throws SQLException {
        return workoutDAO.setWorkOut(new WorkOut(dto.getWorkOutId(),dto.getDesc(),dto.getTrainerId()),new TrainerClient(dto1.getTrainerId(),dto1.getClientId(),dto1.getDate()));
    }

    @Override
    public boolean saveWorkOut(String workOutId, String desc, String trainerId) throws SQLException {
        return workoutDAO.saveWorkOut(workOutId,desc,trainerId);
    }

    @Override
    public String generateworkoutId() throws SQLException {
        return workoutDAO.generateId();
    }

    @Override
    public String changeId(String workPlanId) {
        return workoutDAO.changeId(workPlanId);
    }
}
