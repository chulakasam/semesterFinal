package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.TrainerClientDto;
import lk.ijse.dto.WorkOutDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkoutModel {
    public static boolean setWorkOut(WorkOutDto dto, TrainerClientDto dto1) throws SQLException {

        String workOutId = dto.getWorkOutId();

        String desc = dto.getDesc();

        String trainerId = dto.getTrainerId();

        Connection connection=null;
        try{
            connection=DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean isSaved=saveWorkOut(workOutId,desc,trainerId);
            if(isSaved){
                var model=new TrainerClientModel();
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
    private static boolean saveWorkOut(String workOutId, String desc, String trainerId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String  sql="INSERT INTO workoutPlan VALUES (?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,workOutId);
        pstm.setString(2,desc);
        pstm.setString(3,trainerId);

        return pstm.executeUpdate()>0;
    }
    public String generateworkoutId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT workPlanId FROM workoutPlan ORDER BY workPlanId DESC LIMIT 1 ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            return changeId(resultSet.getString(1));
        }
        return changeId(null);
    }
    private String changeId(String workPlanId) {
        if(workPlanId!=null){
            String[] split = workPlanId.split("W0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "W00" + id;
        }else{
            return "W001";
        }
    }
}
