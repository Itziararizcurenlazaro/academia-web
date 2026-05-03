package dao;

import utils.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MatriculaDAO {

    public ResultSet listarMatriculas() {

        ResultSet rs = null;

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            rs = st.executeQuery(
                    "SELECT m.id, a.nombre AS alumno, c.nombre AS curso, m.estado, m.nota_final " +
                            "FROM matricula m " +
                            "JOIN alumno a ON m.id_alumno = a.id " +
                            "JOIN curso c ON m.id_curso = c.id"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
}
