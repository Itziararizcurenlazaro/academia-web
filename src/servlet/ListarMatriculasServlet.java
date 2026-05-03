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

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT m.*, a.nombre AS alumno, c.nombre AS curso " +
                            "FROM matricula m " +
                            "JOIN alumno a ON m.id_alumno = a.id " +
                            "JOIN curso c ON m.id_curso = c.id"
            );

            ResultSet rs = ps.executeQuery();

            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<div class='container mt-5'>");

            out.println("<h2>Lista de matriculas</h2>");

            out.println("<a href='form-matricula.html' class='btn btn-success mb-3'>Nueva matricula</a>");

            out.println("<table class='table table-striped'>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Alumno</th>");
            out.println("<th>Curso</th>");
            out.println("<th>Estado</th>");
            out.println("<th>Pagado</th>");
            out.println("<th>Nota</th>");
            out.println("<th>Importe</th>");
            out.println("<th>Método</th>");
            out.println("<th>Fecha matrícula</th>");
            out.println("<th>Fecha baja</th>");
            out.println("<th>Acciones</th>");
            out.println("</tr>");

            while (rs.next()) {

                out.println("<tr>");

                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("alumno") + "</td>");
                out.println("<td>" + rs.getString("curso") + "</td>");
                out.println("<td>" + rs.getString("estado") + "</td>");
                out.println("<td>" + rs.getBoolean("pagado") + "</td>");
                out.println("<td>" + rs.getDouble("nota_final") + "</td>");
                out.println("<td>" + rs.getDouble("importe_total") + "</td>");
                out.println("<td>" + rs.getString("metodo_pago") + "</td>");
                out.println("<td>" + rs.getDate("fecha_matricula") + "</td>");
                out.println("<td>" + rs.getDate("fecha_baja") + "</td>");

                out.println("<td>");
                out.println("<a href='detalle-matricula?id=" + rs.getInt("id") + "' class='btn btn-info btn-sm'>Ver</a> ");
                out.println("<a href='editar-matricula?id=" + rs.getInt("id") + "' class='btn btn-warning btn-sm'>Editar</a> ");
                out.println("<a href='borrar-matricula?id=" + rs.getInt("id") + "' class='btn btn-danger btn-sm' onclick='return confirm(\"¿Seguro?\")'>Borrar</a>");
                out.println("</td>");

                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</div>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}