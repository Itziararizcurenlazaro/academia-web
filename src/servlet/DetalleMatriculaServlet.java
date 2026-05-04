package servlet;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/detalle-matricula")
public class DetalleMatriculaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String rol = (String) session.getAttribute("rol");

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT m.*, a.nombre AS alumno, c.nombre AS curso " +
                            "FROM matricula m " +
                            "JOIN alumno a ON m.id_alumno = a.id " +
                            "JOIN curso c ON m.id_curso = c.id " +
                            "WHERE m.id=?"
            );

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
                out.println("<div class='container mt-5'>");

                out.println("<h2>Detalle Matricula</h2>");

                out.println("<p><b>ID:</b> " + rs.getInt("id") + "</p>");
                out.println("<p><b>Alumno:</b> " + rs.getString("alumno") + "</p>");
                out.println("<p><b>Curso:</b> " + rs.getString("curso") + "</p>");
                out.println("<p><b>Estado:</b> " + rs.getString("estado") + "</p>");
                out.println("<p><b>Pagado:</b> " + rs.getBoolean("pagado") + "</p>");
                out.println("<p><b>Nota:</b> " + rs.getDouble("nota_final") + "</p>");
                out.println("<p><b>Importe:</b> " + rs.getDouble("importe_total") + "</p>");
                out.println("<p><b>Método:</b> " + rs.getString("metodo_pago") + "</p>");
                out.println("<p><b>Fecha matrícula:</b> " + rs.getDate("fecha_matricula") + "</p>");
                out.println("<p><b>Fecha baja:</b> " + rs.getDate("fecha_baja") + "</p>");

                if ("admin".equals(rol)) {
                    out.println("<a href='editar-matricula?id=" + rs.getInt("id") + "' class='btn btn-warning btn-sm' onclick=\"return confirm('¿Seguro que quieres modificar esta matrícula?')\">Editar</a> ");
                    out.println("<a href='borrar-matricula?id=" + rs.getInt("id") + "' class='btn btn-danger btn-sm' onclick=\"return confirm('¿Seguro que quieres borrar esta matrícula?')\">Borrar</a>");
                }
                out.println("<a href='matriculas' class='btn btn-secondary mt-3'>Volver</a>");

                out.println("</div>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}