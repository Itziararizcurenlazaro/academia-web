package servlet;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/alta-alumno")
public class AltaAlumnoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {

            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String rol = request.getParameter("rol");
            String fecha = request.getParameter("fecha_nacimiento");
            String nivel = request.getParameter("nivel");
            int activo = Integer.parseInt(request.getParameter("activo"));
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");

            if (nombre == null || nombre.isEmpty() || email == null || email.isEmpty()) {
                response.getWriter().println("ERROR: nombre y email obligatorios");
                return;
            }

            if (!email.contains("@")) {
                response.getWriter().println("ERROR: email no válido");
                return;
            }

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO alumno (nombre, apellidos, email, password, rol, fecha_nacimiento, nivel, activo, telefono, direccion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, rol);
            ps.setString(6, fecha);
            ps.setString(7, nivel);
            ps.setInt(8, activo);
            ps.setString(9, telefono);
            ps.setString(10, direccion);

            ps.executeUpdate();

            response.sendRedirect("alumnos");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("ERROR: " + e.getMessage());
        }
    }
}