package step.learning.servlets;

import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import step.learning.dao.DBConnection;

@WebServlet("/delete")
@Singleton
public class DeleteAppartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Name = req.getParameter("name");

        boolean isRegistered = deleteAppartamentByName(Name);

        if (isRegistered) {
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("error", "Не удалось зарегистрироваться");
            req.getRequestDispatcher("signup.jsp").forward(req, resp);
        }
    }


    private boolean deleteAppartamentByName(String appartamentName) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DBConnection.getConnection(); // Получаем соединение
            String sql = "DELETE FROM Appartaments WHERE Name = ?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, appartamentName);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {  }
            }
        }
    }
}


