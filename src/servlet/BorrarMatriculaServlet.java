package servlet;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
@WebServlet("/borrar-matricula")
public class BorrarMatriculaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {

            int id = Integer.parseInt(request.getParameter("id"));

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement("DELETE FROM matricula WHERE id=?");
            ps.setInt(1, id);

            ps.executeUpdate();

            response.sendRedirect("matriculas");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
