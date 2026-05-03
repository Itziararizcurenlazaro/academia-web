package servlet;

import dao.MatriculaDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

@WebServlet("/matriculas")
public class ListarMatriculasServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<div class='container mt-4'>");

        MatriculaDAO dao = new MatriculaDAO();
        ResultSet rs = dao.listarMatriculas();

        out.println("<h1>Lista de matriculas</h1>");

        out.println("<table class='table table-striped'>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Alumno</th>");
        out.println("<th>Curso</th>");
        out.println("<th>Estado</th>");
        out.println("<th>Nota</th>");
        out.println("</tr>");

        try {
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("alumno") + "</td>");
                out.println("<td>" + rs.getString("curso") + "</td>");
                out.println("<td>" + rs.getString("estado") + "</td>");
                out.println("<td>" + rs.getDouble("nota_final") + "</td>");
                out.println("</tr>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("</table>");
        out.println("</div>");
    }
}