package step.learning.servlets;

import com.google.inject.Singleton;
import step.learning.Appartament;
import step.learning.dao.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Appartament> appartaments = getAppartamentsFromDb(); // Загрузите данные из БД
        System.out.println("Servlet apparts: " + appartaments.get(1).getName());

        req.setAttribute("appartaments", appartaments); // Установите атрибут для доступа в JSP
        req.setAttribute("page-body", "index.jsp");
        req.getRequestDispatcher("WEB-INF/_layout.jsp")
                .forward(req, resp); // ~ return View()
    }

    private List<Appartament> getAppartamentsFromDb() {
        List<Appartament> appartaments = new ArrayList<>();


        try {
            if(DBConnection.getStatus())
            {
                System.out.println("CONNECTION STATUS: YES");
            }
            else {
                System.out.println("CONNECTION STATUS: NO   ");
            }

            // Подключение к базе данных
            Connection connection = DBConnection.getConnection();

            // Создание SQL запроса для получения данных
            String sql = "SELECT * FROM Appartaments";

            // Создание Statement
            Statement statement = connection.createStatement();

            // Выполнение SQL запроса
            ResultSet resultSet = statement.executeQuery(sql);

            // Обработка результатов запроса
            while(resultSet.next()) {
                Appartament appartament = new Appartament();
                appartament.setId(resultSet.getInt("ID"));
                appartament.setName(resultSet.getString("Name"));
                appartament.setDescription(resultSet.getString("Description"));
                appartament.setPrice(resultSet.getDouble("Price"));
                appartament.setCity(resultSet.getString("City"));
                appartament.setRating(resultSet.getDouble("Rating"));
                appartament.setImage(resultSet.getString("Image"));

                appartaments.add(appartament);
            }

            // Закрытие соединений
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
            // Обработка ошибок подключения к базе данных
        }

        return appartaments;
    }
}
