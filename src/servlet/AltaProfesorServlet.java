package servlet;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/alta-profesor")
public class AltaProfesorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {

            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");

            if (nombre == null || nombre.isEmpty() || email == null || email.isEmpty()) {
                response.getWriter().println("ERROR: nombre y email obligatorios");
                return;
            }

            if (!email.contains("@")) {
                response.getWriter().println("ERROR: email no válido");
                return;
            }

            String apellidos = request.getParameter("apellidos");
            String especialidad = request.getParameter("especialidad");
            String fecha = request.getParameter("fecha_contratacion");
            double salario = Double.parseDouble(request.getParameter("salario"));
            int activo = Integer.parseInt(request.getParameter("activo"));

            String supStr = request.getParameter("id_supervisor");

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO profesor (nombre, apellidos, email, especialidad, fecha_contratacion, salario, activo, id_supervisor) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );

            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, email);
            ps.setString(4, especialidad);
            ps.setString(5, fecha);
            ps.setDouble(6, salario);
            ps.setInt(7, activo);

            if (supStr == null || supStr.isEmpty()) {
                ps.setNull(8, java.sql.Types.INTEGER);
            } else {
                ps.setInt(8, Integer.parseInt(supStr));
            }

            ps.executeUpdate();

            response.sendRedirect("profesores");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("ERROR: " + e.getMessage());
        }
    }
}
