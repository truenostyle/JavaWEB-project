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

@WebServlet("/register")
@Singleton
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String password = req.getParameter("password");


        boolean isRegistered = registerUser(firstName, lastName, phone, email, password);

        if (isRegistered) {
            req.setAttribute("page-body", "successful-register.jsp");
            req.getRequestDispatcher("WEB-INF/_layout.jsp")
                    .forward(req, resp);
        } else {
            req.setAttribute("error", "Не удалось зарегистрироваться");
            req.getRequestDispatcher("signup.jsp").forward(req, resp);
        }
    }


    private boolean registerUser(String firstName, String lastName, String phone, String email, String password) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DBConnection.getConnection();

            String sql = "INSERT INTO Users (firstName, lastName, phone, email, password) VALUES (?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, phone);
            statement.setString(4, email);
            statement.setString(5, password);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
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
