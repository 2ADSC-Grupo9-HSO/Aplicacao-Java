/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.*;

/**
 *
 * @author rmacedo
 */
public class Requests {

    public Boolean loginSQL(Connection connec, String user, String password) {
        Boolean achou = false;
        ResultSet resultSet = null;
        String sql = String.format("SELECT * FROM usuarios WHERE usuario = '%s' AND senha = '%s'", user, password);

        try {
            Statement statement = connec.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.isBeforeFirst()) {
                achou = true;
            }

        } catch (SQLException ex) {
            System.out.println("Não foi possivel selecionar os dados " + ex);
        }
        return achou;
    }

    public void insertSQL(Connection connec) {
        PreparedStatement stmt = null;
        String sql = "INSEERT INTO usuarios VALUES ('fabiano', '987')";

        try {
            stmt = connec.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um problema ao inserir dados " + ex);
        }
    }
}
