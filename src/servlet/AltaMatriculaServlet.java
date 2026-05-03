package servlet;

import utils.DBConnection;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/alta-matricula")
@MultipartConfig
public class AltaMatriculaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {

            String fecha = request.getParameter("fecha_matricula");
            String estado = request.getParameter("estado");
            int pagado = Integer.parseInt(request.getParameter("pagado"));
            double nota = Double.parseDouble(request.getParameter("nota_final"));
            double importe = Double.parseDouble(request.getParameter("importe_total"));
            String metodo = request.getParameter("metodo_pago");
            String baja = request.getParameter("fecha_baja");
            int alumno = Integer.parseInt(request.getParameter("id_alumno"));
            int curso = Integer.parseInt(request.getParameter("id_curso"));

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO matricula (fecha_matricula, estado, pagado, nota_final, importe_total, metodo_pago, fecha_baja, id_alumno, id_curso) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            ps.setString(1, fecha);
            ps.setString(2, estado);
            ps.setInt(3, pagado);
            ps.setDouble(4, nota);
            ps.setDouble(5, importe);
            ps.setString(6, metodo);
            ps.setString(7, baja);
            ps.setInt(8, alumno);
            ps.setInt(9, curso);

            ps.executeUpdate();

            response.sendRedirect("matriculas");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}