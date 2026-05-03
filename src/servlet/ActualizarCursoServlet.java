package servlet;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/actualizar-curso")
public class ActualizarCursoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {

            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String tipo = request.getParameter("tipo_manualidad");
            String nivel = request.getParameter("nivel");
            int duracion = Integer.parseInt(request.getParameter("duracion_horas"));
            double precio = Double.parseDouble(request.getParameter("precio"));
            String fecha = request.getParameter("fecha_inicio");
            int activo = Integer.parseInt(request.getParameter("activo"));
            int profesor = Integer.parseInt(request.getParameter("id_profesor"));

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE curso SET nombre=?, tipo_manualidad=?, nivel=?, duracion_horas=?, precio=?, fecha_inicio=?, activo=?, id_profesor=? WHERE id=?"
            );

            ps.setString(1, nombre);
            ps.setString(2, tipo);
            ps.setString(3, nivel);
            ps.setInt(4, duracion);
            ps.setDouble(5, precio);
            ps.setString(6, fecha);
            ps.setInt(7, activo);
            ps.setInt(8, profesor);
            ps.setInt(9, id);

            ps.executeUpdate();

            response.sendRedirect("cursos");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}