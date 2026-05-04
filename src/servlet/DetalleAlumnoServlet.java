package servlet;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/detalle-alumno")
public class DetalleAlumnoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String rol = (String) session.getAttribute("rol");

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM alumno WHERE id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
                out.println("<div class='container mt-5'>");

                out.println("<h2>Detalle Alumno</h2>");

                out.println("<p><b>ID:</b> " + rs.getInt("id") + "</p>");
                out.println("<p><b>Nombre:</b> " + rs.getString("nombre") + "</p>");
                out.println("<p><b>Apellidos:</b> " + rs.getString("apellidos") + "</p>");
                out.println("<p><b>Email:</b> " + rs.getString("email") + "</p>");
                out.println("<p><b>Rol:</b> " + rs.getString("rol") + "</p>");
                out.println("<p><b>Fecha nacimiento:</b> " + rs.getDate("fecha_nacimiento") + "</p>");
                out.println("<p><b>Nivel:</b> " + rs.getString("nivel") + "</p>");
                out.println("<p><b>Activo:</b> " + rs.getBoolean("activo") + "</p>");
                out.println("<p><b>Teléfono:</b> " + rs.getString("telefono") + "</p>");
                out.println("<p><b>Dirección:</b> " + rs.getString("direccion") + "</p>");

                String foto = rs.getString("foto");
                if (foto != null && !foto.isEmpty()) {
                    out.println("<p><b>Foto:</b></p>");
                    out.println("<img src='imagenes/" + foto + "' width='150'>");
                }

                if ("admin".equals(rol)) {
                    out.println("<a href='editar-alumno?id=" + rs.getInt("id") + "' class='btn btn-warning btn-sm' onclick=\"return confirm('¿Seguro que quieres modificar este alumno?')\">Editar</a> ");
                    out.println("<a href='borrar-alumno?id=" + rs.getInt("id") + "' class='btn btn-danger btn-sm' onclick=\"return confirm('¿Seguro que quieres borrar este alumno?')\">Borrar</a>");
                }
                out.println("<br><a href='alumnos' class='btn btn-secondary mt-3'>Volver</a>");

                out.println("</div>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
