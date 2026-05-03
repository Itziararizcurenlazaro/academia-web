package servlet;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/detalle-curso")
public class DetalleCursoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM curso WHERE id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
                out.println("<div class='container mt-5'>");

                out.println("<h2>Detalle Curso</h2>");

                out.println("<p><b>ID:</b> " + rs.getInt("id") + "</p>");
                out.println("<p><b>Nombre:</b> " + rs.getString("nombre") + "</p>");
                out.println("<p><b>Tipo:</b> " + rs.getString("tipo_manualidad") + "</p>");
                out.println("<p><b>Nivel:</b> " + rs.getString("nivel") + "</p>");
                out.println("<p><b>Duración:</b> " + rs.getInt("duracion_horas") + "</p>");
                out.println("<p><b>Precio:</b> " + rs.getDouble("precio") + "</p>");
                out.println("<p><b>Fecha inicio:</b> " + rs.getDate("fecha_inicio") + "</p>");
                out.println("<p><b>Activo:</b> " + rs.getBoolean("activo") + "</p>");
                out.println("<p><b>ID Profesor:</b> " + rs.getInt("id_profesor") + "</p>");

                out.println("<a href='editar-curso?id=" + rs.getInt("id") + "' class='btn btn-warning'>Editar</a> ");
                out.println("<a href='borrar-curso?id=" + rs.getInt("id") + "' class='btn btn-danger' onclick='return confirm(\"¿Seguro?\")'>Borrar</a><br><br>");
                out.println("<a href='cursos' class='btn btn-secondary mt-3'>Volver</a>");

                out.println("</div>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
