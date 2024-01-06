package lk.ijse.BO.Custom;

import java.sql.SQLException;

public interface TrainerClientBO {
    boolean SaveDetails(String trainerId, String clientId, String date) throws SQLException;
}
