package servlet;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/profesores")
public class ListarProfesoresServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {

            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM profesor");

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

            out.println("<h2>Lista de profesores</h2>");

            out.println("<a href='form-profesor.html' class='btn btn-success mb-3'>Nuevo profesor</a>");

            out.println("<table class='table table-hover table-bordered'>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Apellidos</th>");
            out.println("<th>Email</th>");
            out.println("<th>Especialidad</th>");
            out.println("<th>Acciones</th>");
            out.println("</tr>");

            while (rs.next()) {

                out.println("<tr>");

                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("nombre") + "</td>");
                out.println("<td>" + rs.getString("apellidos") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("especialidad") + "</td>");

                out.println("<td>");
                out.println("<a href='detalle-profesor?id=" + rs.getInt("id") + "' class='btn btn-info btn-sm'>Ver</a> ");
                out.println("<a href='editar-profesor?id=" + rs.getInt("id") + "' class='btn btn-warning btn-sm'>Editar</a> ");
                out.println("<a href='borrar-profesor?id=" + rs.getInt("id") + "' class='btn btn-danger btn-sm'>Borrar</a>");
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