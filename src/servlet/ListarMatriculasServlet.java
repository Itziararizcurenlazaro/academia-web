package servlet;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/matriculas")
public class ListarMatriculasServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String rol = (String) session.getAttribute("rol");

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT m.*, a.nombre AS alumno, c.nombre AS curso FROM matricula m JOIN alumno a ON m.id_alumno = a.id JOIN curso c ON m.id_curso = c.id"
            );

            ResultSet rs = ps.executeQuery();

            out.println("<html>");
            out.println("<head>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/flatly/bootstrap.min.css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body>");

            out.println("<nav class='navbar navbar-dark bg-primary'>");
            out.println("<div class='container-fluid'>");
            out.println("<a class='navbar-brand' href='index.html'>Academia</a>");
            out.println("</div>");
            out.println("</nav>");

            out.println("<div class='container mt-4'>");
            out.println("<a href='index.html' class='btn btn-outline-secondary mb-3'>← Inicio</a>");

            out.println("<h2>Lista de matriculas</h2>");

            if ("admin".equals(rol)) {
                out.println("<a href='form-matricula.html' class='btn btn-success mb-3'>Nueva matricula</a>");
            }
            out.println("<table class='table table-hover table-bordered'>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Alumno</th>");
            out.println("<th>Curso</th>");
            out.println("<th>Estado</th>");
            out.println("<th>Nota</th>");
            out.println("<th>Acciones</th>");
            out.println("</tr>");

            while (rs.next()) {

                out.println("<tr>");

                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("alumno") + "</td>");
                out.println("<td>" + rs.getString("curso") + "</td>");
                out.println("<td>" + rs.getString("estado") + "</td>");
                out.println("<td>" + rs.getDouble("nota_final") + "</td>");

                out.println("<td>");
                out.println("<a href='detalle-matricula?id=" + rs.getInt("id") + "' class='btn btn-info btn-sm'>Ver</a> ");

                if ("admin".equals(rol)) {
                    out.println("<a href='editar-matricula?id=" + rs.getInt("id") + "' class='btn btn-warning btn-sm'>Editar</a> ");
                    out.println("<a href='borrar-matricula?id=" + rs.getInt("id") + "' class='btn btn-danger btn-sm'>Borrar</a>");
                }
                out.println("</td>");

                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}