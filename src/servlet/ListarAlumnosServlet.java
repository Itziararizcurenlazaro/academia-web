package servlet;

import dao.AlumnoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

@WebServlet("/alumnos")
public class ListarAlumnosServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        AlumnoDAO dao = new AlumnoDAO();
        ResultSet rs = dao.listarAlumnos();

        out.println("<h1>Lista de alumnos</h1>");

        out.println("<table border='1'>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Nombre</th>");
        out.println("<th>Apellidos</th>");
        out.println("<th>Email</th>");
        out.println("<th>Rol</th>");
        out.println("<th>Fecha nacimiento</th>");
        out.println("<th>Nivel</th>");
        out.println("<th>Activo</th>");
        out.println("<th>Telefono</th>");
        out.println("<th>Direccion</th>");
        out.println("</tr>");

        try {
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("nombre") + "</td>");
                out.println("<td>" + rs.getString("apellidos") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("rol") + "</td>");
                out.println("<td>" + rs.getDate("fecha_nacimiento") + "</td>");
                out.println("<td>" + rs.getString("nivel") + "</td>");
                out.println("<td>" + rs.getBoolean("activo") + "</td>");
                out.println("<td>" + rs.getString("telefono") + "</td>");
                out.println("<td>" + rs.getString("direccion") + "</td>");
                out.println("</tr>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("</table>");
    }
}