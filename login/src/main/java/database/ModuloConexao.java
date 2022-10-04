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
public class ModuloConexao {

    //private String ambiente = "Produção";
    private String ambiente = "Desenvolvimento";

    public Connection getConnection() {

        if (this.ambiente.equals("Desenvolvimento")) {
            try {

                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ola?serverTimezone=UTC",
                        "root",
                        "2122"
                );
                
                System.out.println("Conectado com sucesso " + conn);

                return conn;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        } else if(this.ambiente.equals("Produção")){
            System.out.println("Implementar");
            return null;
        } else{
            System.out.println("Por favor descomente alguma das opções de ambiente");
            return null;
        }

    }
}
