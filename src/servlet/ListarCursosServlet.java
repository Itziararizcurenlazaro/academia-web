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

        CursoDAO dao = new CursoDAO();
        ResultSet rs = dao.listarCursos();

        out.println("<h1>Lista de cursos</h1>");

        out.println("<table border='1'>");
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
                out.println("</tr>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("</table");
    }
}