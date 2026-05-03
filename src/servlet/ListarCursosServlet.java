package servlet;

import dao.CursoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

@WebServlet("/cursos")
public class ListarCursosServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<div class='container mt-4'>");

        CursoDAO dao = new CursoDAO();
        ResultSet rs = dao.listarCursos();

        out.println("<h1>Lista de cursos</h1>");

        out.println("<a href='form-curso.html' class='btn btn-success mb-3'>Nuevo curso</a>");

        out.println("<table class='table table-striped'>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Nombre</th>");
        out.println("<th>Tipo</th>");
        out.println("<th>Nivel</th>");
        out.println("<th>Duracion</th>");
        out.println("<th>Precio</th>");
        out.println("<th>Fecha inicio</th>");
        out.println("<th>Activo</th>");
        out.println("</tr>");

        try {
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("nombre") + "</td>");
                out.println("<td>" + rs.getString("tipo_manualidad") + "</td>");
                out.println("<td>" + rs.getString("nivel") + "</td>");
                out.println("<td>" + rs.getInt("duracion_horas") + "</td>");
                out.println("<td>" + rs.getDouble("precio") + "</td>");
                out.println("<td>" + rs.getDate("fecha_inicio") + "</td>");
                out.println("<td>" + rs.getBoolean("activo") + "</td>");
                out.println("<td>");
                out.println("<a href='detalle-curso?id=" + rs.getInt("id") + "' class='btn btn-info btn-sm'>Ver</a> ");
                out.println("<a href='editar-curso?id=" + rs.getInt("id") + "' class='btn btn-warning btn-sm'>Editar</a> ");
                out.println("<a href='borrar-curso?id=" + rs.getInt("id") + "' class='btn btn-danger btn-sm' onclick='return confirm(\"¿Seguro?\")'>Borrar</a>");
                out.println("</td>");
                out.println("</tr>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("</table");
        out.println("</div>");
    }
}