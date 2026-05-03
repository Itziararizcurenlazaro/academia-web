package servlet;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/borrar-profesor")
public class BorrarProfesorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {

            int id = Integer.parseInt(request.getParameter("id"));

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement("DELETE FROM profesor WHERE id=?");
            ps.setInt(1, id);

            ps.executeUpdate();

            response.sendRedirect("profesores");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
