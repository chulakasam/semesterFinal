package lk.ijse.BO.Custom.Impl;


import lk.ijse.BO.Custom.TrainerBO;
import lk.ijse.DAO.Custom.Impl.TrainerDAOImpl;
import lk.ijse.DAO.Custom.TrainerDAO;
import lk.ijse.dto.TrainerDto;

import java.sql.SQLException;
import java.util.List;

public class TrainerBOImpl implements TrainerBO {
    TrainerDAO trainerDAO=new TrainerDAOImpl();
    @Override
    public String searchTrainerTotal() throws SQLException {
        return trainerDAO.searchTrainerTotal();
    }

    @Override
    public boolean saveTrainer(TrainerDto dto) throws SQLException {
        return trainerDAO.save(dto);
    }

    @Override
    public boolean UpdateTrainer(TrainerDto dto) throws SQLException {
        return trainerDAO.update(dto);
    }

    @Override
    public boolean deleteTrainer(String id) throws SQLException {
        return trainerDAO.delete(id);
    }

    @Override
    public TrainerDto searchTrainer(String trainerId) throws SQLException {
        return trainerDAO.search(trainerId);
    }

    @Override
    public List<TrainerDto> LoadAllTrainers() throws SQLException {
        return trainerDAO.getAlls();
    }

    @Override
    public String generateTrainerId() throws SQLException {
        return trainerDAO.generateId();
    }

    @Override
    public String changeId(String trainerId) {
        return trainerDAO.changeId(trainerId);
    }
}
