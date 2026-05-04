package servlet;

import utils.DBConnection;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/alta-alumno")
@MultipartConfig
public class AltaAlumnoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {

            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");

            String apellidos = request.getParameter("apellidos");
            String password = request.getParameter("password");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            String fecha = request.getParameter("fecha_nacimiento");
            String nivel = request.getParameter("nivel");
            String rol = request.getParameter("rol");
            int activo = Integer.parseInt(request.getParameter("activo"));

            Part filePart = request.getPart("foto");
            String fileName = filePart.getSubmittedFileName();

            String ruta = getServletContext().getRealPath("/imagenes/");

            if (fileName != null && !fileName.isEmpty()) {
                filePart.write(ruta + fileName);
            }

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO alumno (nombre, email, apellidos, password, telefono, direccion, fecha_nacimiento, nivel, rol, activo, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, apellidos);
            ps.setString(4, password);
            ps.setString(5, telefono);
            ps.setString(6, direccion);
            ps.setString(7, fecha);
            ps.setString(8, nivel);
            ps.setString(9, rol);
            ps.setInt(10, activo);
            ps.setString(11, fileName);

            ps.executeUpdate();

            response.sendRedirect("alumnos");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}