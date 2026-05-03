package servlet;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/editar-profesor")
public class EditarProfesorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM profesor WHERE id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
                out.println("<div class='container mt-5'>");

                out.println("<h2>Editar Profesor</h2>");

                out.println("<form action='actualizar-profesor' method='post'>");

                out.println("<input type='hidden' name='id' value='" + rs.getInt("id") + "'>");

                out.println("<input class='form-control mb-2' name='nombre' value='" + rs.getString("nombre") + "'>");
                out.println("<input class='form-control mb-2' name='apellidos' value='" + rs.getString("apellidos") + "'>");
                out.println("<input class='form-control mb-2' name='email' value='" + rs.getString("email") + "'>");
                out.println("<input class='form-control mb-2' name='especialidad' value='" + rs.getString("especialidad") + "'>");
                out.println("<input class='form-control mb-2' type='date' name='fecha_contratacion' value='" + rs.getDate("fecha_contratacion") + "'>");
                out.println("<input class='form-control mb-2' name='salario' value='" + rs.getDouble("salario") + "'>");

                out.println("<select class='form-control mb-2' name='activo'>");
                out.println("<option value='1' " + (rs.getBoolean("activo") ? "selected" : "") + ">Activo</option>");
                out.println("<option value='0' " + (!rs.getBoolean("activo") ? "selected" : "") + ">Inactivo</option>");
                out.println("</select>");

                out.println("<input class='form-control mb-2' name='id_supervisor' value='" + rs.getInt("id_supervisor") + "'>");

                out.println("<button class='btn btn-primary'>Actualizar</button>");
                out.println("</form>");

                out.println("<a href='profesores' class='btn btn-secondary mt-3'>Volver</a>");

                out.println("</div>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}