package lk.ijse.BO.Custom.Impl;


import lk.ijse.BO.Custom.TrainerBO;
import lk.ijse.DAO.Custom.Impl.TrainerDAOImpl;
import lk.ijse.DAO.Custom.TrainerDAO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.Entity.Trainer;
import lk.ijse.dto.TrainerDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerBOImpl implements TrainerBO {
    TrainerDAO trainerDAO= (TrainerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TRAINER);
    @Override
    public String searchTrainerTotal() throws SQLException {
        return trainerDAO.searchTrainerTotal();
    }

    @Override
    public boolean saveTrainer(TrainerDto dto) throws SQLException {
        return trainerDAO.save(new Trainer(dto.getTrainerId(),dto.getName(),dto.getTel(),dto.getNic(),dto.getEmail(),dto.getGender(),dto.getDob(),dto.getDesc()));
    }

    @Override
    public boolean UpdateTrainer(TrainerDto dto) throws SQLException {
        return trainerDAO.update(new Trainer(dto.getTrainerId(),dto.getName(),dto.getTel(),dto.getNic(),dto.getEmail(),dto.getGender(),dto.getDob(),dto.getDesc()));
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
        List<Trainer> all=trainerDAO.getAlls();
        ArrayList<TrainerDto> trainerDto = new ArrayList<>();
        for (Trainer trainer:all){
            trainerDto.add(new TrainerDto(trainer.getTrainerId(),trainer.getName(),trainer.getTel(),trainer.getNic(),trainer.getEmail(),trainer.getGender(),trainer.getDob(),trainer.getDesc()));
        }
        return trainerDto;
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
