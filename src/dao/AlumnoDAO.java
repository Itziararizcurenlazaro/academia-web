package dao;

import utils.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AlumnoDAO {

    public void listarAlumnos() {

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM alumno");

            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
