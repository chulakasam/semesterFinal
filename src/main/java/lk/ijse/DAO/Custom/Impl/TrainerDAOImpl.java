package lk.ijse.DAO.Custom.Impl;


import lk.ijse.DAO.Custom.TrainerDAO;
import lk.ijse.DAO.SQLUtil;
import lk.ijse.Entity.Trainer;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.TrainerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAOImpl implements TrainerDAO {
    @Override
    public String searchTrainerTotal() throws SQLException {
        String count="0";
       /* Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT COUNT(*) FROM trainer";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        */
        ResultSet resultSet=SQLUtil.test("SELECT COUNT(*) FROM trainer");
        if(resultSet.next()){
            count=resultSet.getString(1);
        }
        return count;
    }
    @Override
    public boolean save(Trainer dto) throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
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

         */
        return SQLUtil.test("INSERT INTO trainer VALUES(?,?,?,?,?,?,?,?)",dto.getTrainerId(),dto.getName(),dto.getTel(),dto.getNic(),dto.getEmail(),dto.getGender(),dto.getDob(),dto.getDesc());
    }
    @Override
    public boolean update(Trainer dto) throws SQLException {
            /*Connection connection = DbConnection.getInstance().getConnection();
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

             */
        return SQLUtil.test("UPDATE trainer SET name=?,telephone=?,nic=?,email=?,gender=?,dob=?,description=? WHERE trainerId=?",dto.getName(),dto.getTel(),dto.getNic(),dto.getEmail(),dto.getGender(),dto.getDob(),dto.getDesc(),dto.getTrainerId());
    }
    @Override
    public boolean delete(String id) throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql="DELETE FROM trainer WHERE trainerId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;
        */
        return SQLUtil.test("DELETE FROM trainer WHERE trainerId=?",id);
    }
    @Override
    public TrainerDto search(String trainerId) throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM trainer WHERE trainerId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,trainerId);

        ResultSet resultSet = pstm.executeQuery();
        */
        ResultSet resultSet=SQLUtil.test("SELECT * FROM trainer WHERE trainerId=?",trainerId);
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
    @Override
    public List<Trainer> getAlls() throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM trainer";

        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

         */
        ResultSet resultSet=SQLUtil.test("SELECT * FROM trainer");
        ArrayList<Trainer> dtoList = new ArrayList<>();
        while(resultSet.next()){
            dtoList.add(
                    new Trainer(
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
    @Override
    public String generateId() throws SQLException {
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT trainerId FROM trainer ORDER BY trainerId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
         ResultSet resultSet = pstm.executeQuery();
         */
       ResultSet resultSet=SQLUtil.test("SELECT trainerId FROM trainer ORDER BY trainerId DESC LIMIT 1");
        if(resultSet.next()){
            return changeId(resultSet.getString(1));
        }
        return changeId(null);
    }
    @Override
    public  String changeId(String trainerId){
        if(trainerId!=null){
            String[] split = trainerId.split("T0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            if(id<10) {
                return "T00" + id;
            }else{
                return "T0" + id;
            }
        }else{
            return "T001";
        }
    }
}


