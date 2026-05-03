package servlet;

import utils.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/actualizar-alumno")
@MultipartConfig
public class ActualizarAlumnoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            String fecha = request.getParameter("fecha_nacimiento");
            String nivel = request.getParameter("nivel");
            String rol = request.getParameter("rol");
            int activo = Integer.parseInt(request.getParameter("activo"));

            Part filePart = request.getPart("foto");
            String fileName = filePart.getSubmittedFileName();

            if (fileName != null && !fileName.isEmpty()) {

                String ruta = getServletContext().getRealPath("") + "imagenes/";

                java.io.File carpeta = new java.io.File(ruta);
                if (!carpeta.exists()) {
                    carpeta.mkdirs();
                }

                filePart.write(ruta + fileName);
            }

            Connection con = DBConnection.getConnection();

            String sql;

            if (fileName != null && !fileName.isEmpty()) {
                sql = "UPDATE alumno SET nombre=?, apellidos=?, email=?, password=?, telefono=?, direccion=?, fecha_nacimiento=?, nivel=?, rol=?, activo=?, foto=? WHERE id=?";
            } else {
                sql = "UPDATE alumno SET nombre=?, apellidos=?, email=?, password=?, telefono=?, direccion=?, fecha_nacimiento=?, nivel=?, rol=?, activo=? WHERE id=?";
            }

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, telefono);
            ps.setString(6, direccion);
            ps.setString(7, fecha);
            ps.setString(8, nivel);
            ps.setString(9, rol);
            ps.setInt(10, activo);

            if (fileName != null && !fileName.isEmpty()) {
                ps.setString(11, fileName);
                ps.setInt(12, id);
            } else {
                ps.setInt(11, id);
            }

            ps.executeUpdate();

            response.sendRedirect("alumnos");

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().println("ERROR: " + e.getMessage());
        }
    }
}
