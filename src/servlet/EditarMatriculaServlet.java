package servlet;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/editar-matricula")
public class EditarMatriculaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM matricula WHERE id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
                out.println("<div class='container mt-5'>");

                out.println("<h2>Editar Matricula</h2>");

                out.println("<form action='actualizar-matricula' method='post'>");

                out.println("<input type='hidden' name='id' value='" + rs.getInt("id") + "'>");

                out.println("<input type='date' class='form-control mb-2' name='fecha_matricula' value='" + rs.getDate("fecha_matricula") + "'>");

                out.println("<input class='form-control mb-2' name='estado' value='" + rs.getString("estado") + "'>");
                out.println("<input class='form-control mb-2' name='pagado' value='" + rs.getInt("pagado") + "'>");
                out.println("<input class='form-control mb-2' name='nota_final' value='" + rs.getDouble("nota_final") + "'>");
                out.println("<input class='form-control mb-2' name='importe_total' value='" + rs.getDouble("importe_total") + "'>");
                out.println("<input class='form-control mb-2' name='metodo_pago' value='" + rs.getString("metodo_pago") + "'>");

                out.println("<input type='date' class='form-control mb-2' name='fecha_baja' value='" + rs.getDate("fecha_baja") + "'>");

                out.println("<input class='form-control mb-2' name='id_alumno' value='" + rs.getInt("id_alumno") + "'>");
                out.println("<input class='form-control mb-2' name='id_curso' value='" + rs.getInt("id_curso") + "'>");

                out.println("<button class='btn btn-primary'>Actualizar</button>");
                out.println("</form>");

                out.println("<a href='matriculas' class='btn btn-secondary mt-3'>Volver</a>");

                out.println("</div>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
