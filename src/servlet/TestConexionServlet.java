package servlet;

import utils.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test-conexion")
public class TestConexionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (DBConnection.getConnection() != null) {
            out.println("<h1>Conexion OK</h1>");
        } else {
            out.println("<h1>Error de conexion</h1>");
        }
    }
}