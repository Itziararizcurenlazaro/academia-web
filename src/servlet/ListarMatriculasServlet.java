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

            String estado = request.getParameter("estado");
            String pagado = request.getParameter("pagado");
            String nota = request.getParameter("nota");
            String alumno = request.getParameter("id_alumno");

            String sql = "SELECT m.*, a.nombre AS alumno, c.nombre AS curso " +
                    "FROM matricula m " +
                    "JOIN alumno a ON m.id_alumno = a.id " +
                    "JOIN curso c ON m.id_curso = c.id WHERE 1=1";

            if (estado != null && !estado.isEmpty()) {
                sql += " AND m.estado = ?";
            }

            if (pagado != null && !pagado.isEmpty()) {
                sql += " AND m.pagado = ?";
            }

            if (nota != null && !nota.isEmpty()) {
                sql += " AND m.nota_final >= ?";
            }

            if (alumno != null && !alumno.isEmpty()) {
                sql += " AND m.id_alumno = ?";
            }

            PreparedStatement ps = con.prepareStatement(sql);

            int i = 1;

            if (estado != null && !estado.isEmpty()) {
                ps.setString(i++, estado);
            }

            if (pagado != null && !pagado.isEmpty()) {
                ps.setInt(i++, Integer.parseInt(pagado));
            }

            if (nota != null && !nota.isEmpty()) {
                ps.setDouble(i++, Double.parseDouble(nota));
            }

            if (alumno != null && !alumno.isEmpty()) {
                ps.setInt(i++, Integer.parseInt(alumno));
            }

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
            out.println("<form method='get' action='matriculas' class='mb-3'>");

            out.println("<div class='row'>");

            out.println("<div class='col-md-3'><input name='estado' class='form-control' placeholder='Estado'></div>");
            out.println("<div class='col-md-3'><input name='pagado' class='form-control' placeholder='Pagado (0/1)'></div>");
            out.println("<div class='col-md-3'><input name='nota' class='form-control' placeholder='Nota mínima'></div>");
            out.println("<div class='col-md-3'><input name='id_alumno' class='form-control' placeholder='ID alumno'></div>");

            out.println("</div>");

            out.println("<div class='mt-2'>");
            out.println("<button class='btn btn-primary'>Buscar</button>");
            out.println("<a href='matriculas' class='btn btn-secondary ms-2'>Limpiar</a>");
            out.println("</div>");

            out.println("</form>");

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