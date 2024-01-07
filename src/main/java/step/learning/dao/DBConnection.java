package step.learning.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    private static final String URL = "jdbc:mysql://aws.connect.psdb.cloud/main_db?sslMode=VERIFY_IDENTITY"; // Адрес подключения к базе данных
    private static final String USER = "9av2bzuex9qgy72f2461"; // Имя пользователя
    private static final String PASSWORD = "pscale_pw_F0sYlmimhmv0Tk5cFijOYFylLB50mCVFmjbPVzUsH8T"; // Пароль
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Загрузка класса драйвера
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Создание соединения
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                // Обработка исключений
                throw new RuntimeException("Error connecting to the database", e);
            }
        }
        return connection;
    }

    public static boolean getStatus() {
        try {
            getConnection(); // Попробуйте получить соединение
            if (connection != null && !connection.isClosed()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
