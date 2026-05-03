package servlet;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/alta-curso")
public class AltaCursoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {

            String nombre = request.getParameter("nombre");
            String tipo = request.getParameter("tipo_manualidad");

            if (nombre == null || nombre.isEmpty() || tipo == null || tipo.isEmpty()) {
                response.getWriter().println("ERROR: nombre y tipo obligatorios");
                return;
            }

            String nivel = request.getParameter("nivel");
            String duracionStr = request.getParameter("duracion_horas");
            String precioStr = request.getParameter("precio");
            String fecha = request.getParameter("fecha_inicio");
            String activoStr = request.getParameter("activo");
            String profesorStr = request.getParameter("id_profesor");

            int duracion = (duracionStr == null || duracionStr.isEmpty()) ? 0 : Integer.parseInt(duracionStr);
            double precio = (precioStr == null || precioStr.isEmpty()) ? 0 : Double.parseDouble(precioStr);
            int activo = (activoStr == null || activoStr.isEmpty()) ? 1 : Integer.parseInt(activoStr);

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO curso (nombre, tipo_manualidad, nivel, duracion_horas, precio, fecha_inicio, activo, id_profesor) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );

            ps.setString(1, nombre);
            ps.setString(2, tipo);
            ps.setString(3, nivel);
            ps.setInt(4, duracion);
            ps.setDouble(5, precio);
            ps.setString(6, fecha);
            ps.setInt(7, activo);

            if (profesorStr == null || profesorStr.isEmpty()) {
                ps.setNull(8, java.sql.Types.INTEGER);
            } else {
                ps.setInt(8, Integer.parseInt(profesorStr));
            }

            ps.executeUpdate();

            response.sendRedirect("cursos");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("ERROR: " + e.getMessage());
        }
    }
}
