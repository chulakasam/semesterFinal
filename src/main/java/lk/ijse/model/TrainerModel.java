package lk.ijse.model;


import lk.ijse.db.DbConnection;
import lk.ijse.dto.TrainerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerModel {
    public static String searchTrainerTotal() throws SQLException {
        String count="0";
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT COUNT(*) FROM trainer";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            count=resultSet.getString(1);
        }
        return count;
    }
    public boolean saveTrainer(TrainerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO trainer VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getTrainerId());
        pstm.setString(2, dto.getName());
        pstm.setInt(3,dto.getTel());
        pstm.setString(4, dto.getNic());
        pstm.setString(5, dto.getEmail());
        pstm.setString(6, dto.getGender());
        pstm.setString(7, dto.getDob());
        pstm.setString(8, dto.getDesc());

        return pstm.executeUpdate()>0;
    }
    public boolean UpdateTrainer(TrainerDto dto) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql="UPDATE trainer SET name=?,telephone=?,nic=?,email=?,gender=?,dob=?,description=? WHERE trainerId=?";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, dto.getName());
            pstm.setInt(2,dto.getTel());
            pstm.setString(3, dto.getNic());
            pstm.setString(4, dto.getEmail());
            pstm.setString(5, dto.getGender());
            pstm.setString(6, dto.getDob());
            pstm.setString(7, dto.getDesc());
            pstm.setString(8, dto.getTrainerId());


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
            String nic = resultSet.getString(4);
            String email = resultSet.getString(5);
            String gender = resultSet.getString(6);
            String dob = resultSet.getString(7);
            String desc = resultSet.getString(8);


            dto = new TrainerDto(Id, name, tel,nic,email,gender,dob,desc);
        }
         return dto;
    }
    public List<TrainerDto> LoadAllTrainers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM trainer";

        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<TrainerDto> dtoList = new ArrayList<>();
        while(resultSet.next()){
            dtoList.add(
                    new TrainerDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8)
                    )
            );

        }
        return dtoList;

    }
}
