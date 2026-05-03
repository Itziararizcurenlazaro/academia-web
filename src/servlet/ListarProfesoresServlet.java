package servlet;

import dao.ProfesorDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

@WebServlet("/profesores")
public class ListarProfesoresServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        ProfesorDAO dao = new ProfesorDAO();
        ResultSet rs = dao.listarProfesores();

        out.println("<h1>Lista de profesores</h1>");

        out.println("<table border='1'>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Nombre</th>");
        out.println("<th>Apellidos</th>");
        out.println("<th>Email</th>");
        out.println("<th>Especialidad</th>");
        out.println("<th>Fecha contratacion</th>");
        out.println("<th>Salario</th>");
        out.println("<th>Activo</th>");
        out.println("</tr>");

        try {
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("nombre") + "</td>");
                out.println("<td>" + rs.getString("apellidos") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("especialidad") + "</td>");
                out.println("<td>" + rs.getDate("fecha_contratacion") + "</td>");
                out.println("<td>" + rs.getDouble("salario") + "</td>");
                out.println("<td>" + rs.getBoolean("activo") + "</td>");
                out.println("</tr>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("</table>");
    }
}