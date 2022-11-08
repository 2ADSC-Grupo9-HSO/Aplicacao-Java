/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author rmacedo
 */
public class Requests {

    public Maquina loginSQL(JdbcTemplate conexao, String user, String password) {

        String sql = "SELECT idMaquina, hostName, senhaMaquina FROM tbMaquina WHERE hostName = ? AND senhaMaquina = ?";

        String sql2 = "SELECT idHardware, fkComponente "
                + "FROM tbMaquina "
                + "JOIN tbHardware ON idMaquina = fkMaquina "
                + "WHERE hostName = ? AND senhaMaquina = ?";

        try {

            Maquina maquina = conexao.queryForObject(sql, new BeanPropertyRowMapper<>(Maquina.class), user, password);

            maquina.hardMaquina = conexao.query(sql2, new BeanPropertyRowMapper<>(HardMaquina.class), user, password);

            System.out.println(maquina);

            for (HardMaquina itemMaquina : maquina.hardMaquina) {
                System.out.println(itemMaquina);
            }

            return maquina;

        } catch (Exception ex) {

            System.out.println("Não foi possivel selecionar os dados " + ex);

            return null;
        }
    }

    public void insertSQL(JdbcTemplate conexao, Integer fkMaquina, Double registro) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO tbHistorico (fkHardware, valorRegistro) VALUES (" + fkMaquina + "," + registro + " )";

        try {

            conexao.execute(sql);

        } catch (Exception ex) {

            System.out.println("Ocorreu um problema ao inserir dados " + ex);

        }
    }

    public void insertMaquinaDocker(JdbcTemplate conexao, String hostName, String senhaMaquina, String sistemaOperacional) {
        String sql = "INSERT INTO tbMaquina (idMaquina, hostName, senhaMaquina, sistemaOperacional) VALUES( 1, '" + hostName + "', '" + senhaMaquina + "', '" + sistemaOperacional + "');";

        String sql2 = "INSERT INTO tbHardware (fkComponente, fkMaquina) VALUES (1, 1), (2, 1), (3, 1);";

        try {
            
            conexao.execute(sql);
            conexao.execute(sql2);
            
        } catch(Exception ex){
            
            System.out.println("Erro ao inserir dados no docker" + ex);
            
        }
    }
}
