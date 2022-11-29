/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.io.IOException;
import micro.servicos.Log;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author rmacedo
 */
public class ModuloConexao {

    private String ambiente;

    private JdbcTemplate connection;

    public ModuloConexao(String ambiente) {
        this.ambiente = ambiente;
    }

    public JdbcTemplate getConnection() throws IOException {

        if (this.ambiente.equals("Desenvolvimento")) {
            try {

                BasicDataSource dataSource = new BasicDataSource();

                dataSource​.setDriverClassName("com.mysql.cj.jdbc.Driver");

                dataSource​.setUrl("jdbc:mysql://172.17.0.2:3306/hso?allowPublicKeyRetrieval=true&useSSL=false?allowPublicKeyRetrieval=true&useSSL=false");

                dataSource​.setUsername("root");

                dataSource​.setPassword("urubu100");

                this.connection = new JdbcTemplate(dataSource);

                new Log("uso", "Conectado com o banco do Mysql");
                return this.connection;

            } catch (Exception ex) {
                new Log("erro", "Erro ao estabelecer conexão com o Mysql" + ex.getMessage());
                return null;
            }
        } else if (this.ambiente.equals("Produção")) {
            try {

                BasicDataSource dataSource = new BasicDataSource();

                dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                dataSource​.setUrl("jdbc:sqlserver://svr-projeto-hso.database.windows.net:1433;database=bd-projeto-hso");

                dataSource​.setUsername("admin-projeto-hso@svr-projeto-hso");

                dataSource​.setPassword("#Gfgrupo9");

                this.connection = new JdbcTemplate(dataSource);

                new Log("uso", "Conectado com o banco da Azure");
                return this.connection;

            } catch (Exception ex) {
                new Log("erro", "Erro ao estabelecer conexão com o Azure" + ex.getMessage());
                return null;
            }
        } else {
            return null;
        }

    }
}
