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

        String sql = "SELECT * FROM tbInfoMaquina WHERE hostName = ?";

        try {
            
            Maquina usuario = conexao.queryForObject(sql,new BeanPropertyRowMapper<>(Maquina.class), user);
            
            System.out.println(usuario);
            
            return usuario;

        } catch (Exception ex) {
            System.out.println("Não foi possivel selecionar os dados " + ex);
            
            return null;
        }
    }

    public void insertSQL(JdbcTemplate conexao) {
        PreparedStatement stmt = null;
        String sql = "INSEERT INTO usuarios VALUES ('fabiano', '987')";
        
        conexao.execute(sql);

        try {
        } catch (Exception ex) {
            System.out.println("Ocorreu um problema ao inserir dados " + ex);
        }
    }
}
