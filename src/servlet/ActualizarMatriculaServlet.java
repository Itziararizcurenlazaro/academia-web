package servlet;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/actualizar-matricula")
public class ActualizarMatriculaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {

            int id = Integer.parseInt(request.getParameter("id"));

            String fecha = request.getParameter("fecha_matricula");
            String estado = request.getParameter("estado");

            String pagadoStr = request.getParameter("pagado");
            int pagado = (pagadoStr == null || pagadoStr.isEmpty()) ? 0 : Integer.parseInt(pagadoStr);

            String notaStr = request.getParameter("nota_final");
            double nota = (notaStr == null || notaStr.isEmpty()) ? 0 : Double.parseDouble(notaStr);

            String importeStr = request.getParameter("importe_total");
            double importe = (importeStr == null || importeStr.isEmpty()) ? 0 : Double.parseDouble(importeStr);

            String metodo = request.getParameter("metodo_pago");
            String baja = request.getParameter("fecha_baja");

            int alumno = Integer.parseInt(request.getParameter("id_alumno"));
            int curso = Integer.parseInt(request.getParameter("id_curso"));

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE matricula SET fecha_matricula=?, estado=?, pagado=?, nota_final=?, importe_total=?, metodo_pago=?, fecha_baja=?, id_alumno=?, id_curso=? WHERE id=?"
            );

            if (fecha == null || fecha.isEmpty()) {
                ps.setNull(1, java.sql.Types.DATE);
            } else {
                ps.setString(1, fecha);
            }

            ps.setString(2, estado);
            ps.setInt(3, pagado);
            ps.setDouble(4, nota);
            ps.setDouble(5, importe);
            ps.setString(6, metodo);

            if (baja == null || baja.isEmpty()) {
                ps.setNull(7, java.sql.Types.DATE);
            } else {
                ps.setString(7, baja);
            }

            ps.setInt(8, alumno);
            ps.setInt(9, curso);
            ps.setInt(10, id);

            ps.executeUpdate();

            response.sendRedirect("matriculas");

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().println("ERROR: " + e.getMessage());
        }
    }
}
