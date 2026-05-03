package servlet;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM alumno WHERE email=? AND password=?"
            );

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                HttpSession session = request.getSession();

                session.setAttribute("usuario", rs.getString("nombre"));
                session.setAttribute("rol", rs.getString("rol"));

                response.sendRedirect("index.html");

            } else {
                response.getWriter().println("Login incorrecto");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
