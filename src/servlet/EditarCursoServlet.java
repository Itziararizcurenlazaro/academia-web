package servlet;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/editar-curso")
public class EditarCursoServlet extends HttpServlet {

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

                out.println("<h2>Editar Curso</h2>");

                out.println("<form action='actualizar-curso' method='post'>");

                out.println("<input type='hidden' name='id' value='" + rs.getInt("id") + "'>");

                out.println("<input class='form-control mb-2' name='nombre' value='" + rs.getString("nombre") + "'>");
                out.println("<input class='form-control mb-2' name='tipo_manualidad' value='" + rs.getString("tipo_manualidad") + "'>");

                out.println("<input class='form-control mb-2' name='nivel' value='" + rs.getString("nivel") + "'>");
                out.println("<input class='form-control mb-2' name='duracion_horas' value='" + rs.getInt("duracion_horas") + "'>");
                out.println("<input class='form-control mb-2' name='precio' value='" + rs.getDouble("precio") + "'>");

                out.println("<input class='form-control mb-2' type='date' name='fecha_inicio' value='" + rs.getDate("fecha_inicio") + "'>");

                out.println("<select class='form-control mb-2' name='activo'>");
                out.println("<option value='1' " + (rs.getBoolean("activo") ? "selected" : "") + ">Activo</option>");
                out.println("<option value='0' " + (!rs.getBoolean("activo") ? "selected" : "") + ">Inactivo</option>");
                out.println("</select>");

                out.println("<input class='form-control mb-2' name='id_profesor' value='" + rs.getInt("id_profesor") + "'>");

                out.println("<button class='btn btn-primary'>Actualizar</button>");
                out.println("</form>");

                out.println("<a href='cursos' class='btn btn-secondary mt-3'>Volver</a>");

                out.println("</div>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
