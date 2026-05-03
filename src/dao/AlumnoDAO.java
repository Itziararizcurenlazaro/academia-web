package dao;

import utils.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AlumnoDAO {

    public ResultSet listarAlumnos() {

        ResultSet rs = null;

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            rs = st.executeQuery("SELECT * FROM alumno");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
}
