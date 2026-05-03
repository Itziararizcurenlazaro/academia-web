package dao;

import utils.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProfesorDAO {

    public ResultSet listarProfesores() {

        ResultSet rs = null;

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            rs = st.executeQuery("SELECT * FROM profesor");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
}
