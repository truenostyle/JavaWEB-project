package step.learning.services.db;

import step.learning.dao.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class DBService {
    public Connection getConnection() throws SQLException { // Обратите внимание на изменения
        return DBConnection.getConnection();
    }
}

