package lk.ijse.model;

import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TrainerClientModel {
    public boolean SaveDetails(String trainerId, String clientId, String date) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="INSERT INTO trainerClientDetails VALUES(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,trainerId);
        pstm.setString(2,clientId);
        pstm.setString(3,date);

        return pstm.executeUpdate()>0;
    }
}
