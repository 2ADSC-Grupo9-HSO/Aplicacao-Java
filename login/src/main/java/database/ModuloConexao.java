/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

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

    public JdbcTemplate getConnection() {

        if (this.ambiente.equals("Desenvolvimento")) {
            try {
                
                BasicDataSource dataSource = new BasicDataSource();

                dataSource​.setDriverClassName("com.mysql.cj.jdbc.Driver");

                dataSource​.setUrl("jdbc:mysql://localhost:3306/HSO?serverTimezone=UTC");

                dataSource​.setUsername("root");

                dataSource​.setPassword("2122");
                
                this.connection = new JdbcTemplate(dataSource);
                
                System.out.println("Conectado com sucesso " + this.connection);

                return this.connection;

            } catch (Exception e) {
                System.out.println("erro conexão Mysql" + e.getMessage());
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

                System.out.println("Conectado com sucesso " + this.connection);

                return this.connection;

            } catch (Exception e) {
                System.out.println( "erro conexão Azure" + e.getMessage());
                return null;
            }
        } else {
            System.out.println("Por favor descomente alguma das opções de ambiente");
            return null;
        }

    }
}
