package step.learning.servlets;

import com.google.inject.Singleton;
import step.learning.User;
import step.learning.dao.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/auth")
@Singleton
public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = authenticate(email, password);
        System.out.println(email + " | " + password);
        System.out.println(user);
        HttpSession session = req.getSession();
        if (user != null) {
            // Аутентификация успешна
            session.setAttribute("user", user);
            session.setAttribute("auth-status", "");
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            // Аутентификация не удалась
            session.setAttribute("auth-status", "Авторизация не удалась: неверный email или пароль");
            session.setAttribute("openModal", "true");
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }

    private User authenticate(String email, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM Users WHERE email = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int id = rs.getInt("id");

                if (checkPassword(password, dbPassword)) {
                    return new User(id, firstName, lastName, email, dbPassword);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private boolean checkPassword(String password, String dbPassword) {

        return password.equals(dbPassword);
    }
}
