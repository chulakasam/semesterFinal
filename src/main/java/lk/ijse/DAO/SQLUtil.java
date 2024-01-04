package lk.ijse.DAO;

import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    public static <T>T test(String sql,Object...object) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        for(int i=0;i< object.length;i++){
            pstm.setObject((i+1),object[i]);
        }
        if(sql.startsWith("SELECT")){
            return (T) pstm.executeQuery();
        }else{
            return (T) ((Boolean) (pstm.executeUpdate()>0));
        }
    }
}
