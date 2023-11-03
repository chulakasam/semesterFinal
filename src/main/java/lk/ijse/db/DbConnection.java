package lk.ijse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection dbConnection;
    private Connection connection;
    private DbConnection() throws SQLException {
       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LAFittrack", "root", "IJSE@1234");
    }
    public static DbConnection getInstance() throws SQLException {
        return (null == dbConnection) ? dbConnection = new DbConnection() : dbConnection;
    }
    public Connection getConnection(){
        return connection;
    }
}
