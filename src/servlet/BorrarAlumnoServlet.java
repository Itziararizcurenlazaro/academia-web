package servlet;

import utils.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/borrar-alumno")
public class BorrarAlumnoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id = Integer.parseInt(request.getParameter("id"));

            Connection con = DBConnection.getConnection();

            PreparedStatement ps1 = con.prepareStatement("DELETE FROM matricula WHERE id_alumno=?");
            ps1.setInt(1, id);
            ps1.executeUpdate();

            PreparedStatement ps2 = con.prepareStatement("DELETE FROM alumno WHERE id=?");
            ps2.setInt(1, id);
            ps2.executeUpdate();

            response.sendRedirect("alumnos");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}