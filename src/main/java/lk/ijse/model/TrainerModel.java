package lk.ijse.model;


import lk.ijse.db.DbConnection;
import lk.ijse.dto.TrainerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainerModel {
    public boolean saveTrainer(TrainerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO trainer VALUES(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getTrainerId());
        pstm.setString(2, dto.getName());
        pstm.setInt(3,dto.getTel());

        return pstm.executeUpdate()>0;
    }
    public boolean UpdateTrainer(TrainerDto dto) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="UPDATE trainer SET name=?,telephone=? WHERE trainerId=?";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, dto.getName());
            pstm.setInt(2,dto.getTel());
            pstm.setString(3, dto.getTrainerId());

            return pstm.executeUpdate()>0;
    }
    public boolean deleteTrainer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="DELETE FROM trainer WHERE trainerId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;
    }
    public TrainerDto searchTrainer(String trainerId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM trainer WHERE trainerId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,trainerId);

        ResultSet resultSet = pstm.executeQuery();

        TrainerDto dto=null;

        if(resultSet.next()){
            String Id = resultSet.getString(1);
            String name = resultSet.getString(2);
            int tel = resultSet.getInt(3);

            dto = new TrainerDto(Id, name, tel);
        }
         return dto;
    }
}
