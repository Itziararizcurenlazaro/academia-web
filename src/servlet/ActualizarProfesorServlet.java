package servlet;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/actualizar-profesor")
public class ActualizarProfesorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {

            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String email = request.getParameter("email");
            String especialidad = request.getParameter("especialidad");
            String fecha = request.getParameter("fecha_contratacion");
            double salario = Double.parseDouble(request.getParameter("salario"));
            int activo = Integer.parseInt(request.getParameter("activo"));

            String supStr = request.getParameter("id_supervisor");

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE profesor SET nombre=?, apellidos=?, email=?, especialidad=?, fecha_contratacion=?, salario=?, activo=?, id_supervisor=? WHERE id=?"
            );

            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, email);
            ps.setString(4, especialidad);
            ps.setString(5, fecha);
            ps.setDouble(6, salario);
            ps.setInt(7, activo);

            if (supStr == null || supStr.isEmpty() || supStr.equals("0")) {
                ps.setNull(8, java.sql.Types.INTEGER);
            } else {
                ps.setInt(8, Integer.parseInt(supStr));
            }

            ps.setInt(9, id);

            ps.executeUpdate();

            response.sendRedirect("profesores");

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().println("ERROR: " + e.getMessage());
        }
    }
}