package lk.ijse.DAO.Custom.Impl;

import lk.ijse.DAO.Custom.WorkoutDAO;
import lk.ijse.DAO.SQLUtil;
import lk.ijse.Entity.TrainerClient;
import lk.ijse.Entity.WorkOut;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.TrainerClientDto;
import lk.ijse.dto.WorkOutDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WorkoutDAOImpl implements WorkoutDAO {
    public  boolean setWorkOut(WorkOut dto, TrainerClient dto1) throws SQLException {

        String workOutId = dto.getWorkOutId();

        String desc = dto.getDesc();

        String trainerId = dto.getTrainerId();

        Connection connection=null;
        try{
            connection=DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean isSaved=saveWorkOut(workOutId,desc,trainerId);
            if(isSaved){
                var model=new TrainerClientDAOImpl();
                boolean isEntered=model.SaveDetails(dto1.getTrainerId(),dto1.getClientId(),dto1.getDate());
                if(isEntered){
                    connection.commit();
                }
            }
        }catch (SQLException e){
            connection.rollback();
        }finally {
            connection.setAutoCommit(true);
        }
        return true;
    }
    public boolean saveWorkOut(String workOutId, String desc, String trainerId) throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String  sql="INSERT INTO workoutPlan VALUES (?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,workOutId);
        pstm.setString(2,desc);
        pstm.setString(3,trainerId);

        return pstm.executeUpdate()>0;*/
        return SQLUtil.test("INSERT INTO workoutPlan VALUES (?,?,?)",workOutId,desc,trainerId);
    }

    @Override
    public List<WorkOut> getAlls() throws SQLException {
        return null;
    }

    @Override
    public boolean save(WorkOut dto) throws SQLException {
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
    public boolean update(WorkOut dto) throws SQLException {
        return false;
    }

    public String generateId() throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT workPlanId FROM workoutPlan ORDER BY workPlanId DESC LIMIT 1 ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();*/
        ResultSet resultSet=SQLUtil.test("SELECT workPlanId FROM workoutPlan ORDER BY workPlanId DESC LIMIT 1 ");
        if(resultSet.next()){
            return changeId(resultSet.getString(1));
        }
        return changeId(null);
    }
    public String changeId(String workPlanId) {
        if(workPlanId!=null){
            String[] split = workPlanId.split("W0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            if(id<10) {
                return "W00" + id;
            }else{
                return "W0" + id;
            }
        }else{
            return "W001";
        }
    }
}
