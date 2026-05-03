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

        try {
            while (rs.next()) {
                out.println("<p>" + rs.getString("nombre") + "</p>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
