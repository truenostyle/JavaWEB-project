package step.learning.servlets;

import com.google.inject.Singleton;
import step.learning.dao.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/create")
@Singleton
public class CreateAppartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Name = req.getParameter("name");
        String City = req.getParameter("city");
        String Price = req.getParameter("price");
        String ImageURL = req.getParameter("image");
        String Description = req.getParameter("description");

        boolean isRegistered = createAppart(Name, City, Price, ImageURL, Description);

        if(isRegistered) {
            req.setAttribute("page-body", "successful-create.jsp");
            req.getRequestDispatcher("WEB-INF/_layout.jsp")
                    .forward(req, resp);
        } else {
            req.setAttribute("error", "Не удалось зарегистрироваться");
            req.getRequestDispatcher("signup.jsp").forward(req, resp);
        }
    }


    // Метод для регистрации пользователя (примерная реализация)
    private boolean createAppart(String Name, String City, String Price, String ImageURL, String Description) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DBConnection.getConnection();

            String sql = "INSERT INTO Appartaments (Name, City, Price, Image, Description, Rating) VALUES (?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, Name);
            statement.setString(2, City);
            statement.setString(3, Price);
            statement.setString(4, ImageURL);
            statement.setString(5, Description);
            statement.setDouble(6, 0);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) { }
            }
        }
    }


}