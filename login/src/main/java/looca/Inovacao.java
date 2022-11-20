/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package looca;

import Maquina.Maquina;
import Maquina.MaquinaComProblema;
import database.ModuloConexao;
import database.Requests;
import java.io.IOException;
import login.LoginCli;

/**
 *
 * @author Rafael
 */
public class Inovacao {
    
    public static void main(String[] args) throws IOException {
        
        LoginCli shell = new LoginCli();
        
        ModuloConexao modulo = new ModuloConexao("Desenvolvimento");
        
        Requests req = new Requests();
        
        Maquina maquinaMysql =  new Maquina();
        
        maquinaMysql = req.loginSQL(modulo.getConnection(), "pc1", "123");
        
        MaquinaComProblema maq = req.selectInovacao(modulo.getConnection(), maquinaMysql);
        
        System.out.println(maq.getQtd_maquinas_debilitadas());
        
        if(maq.getQtd_maquinas_debilitadas() > 0){
            if(maquinaMysql.getHostName().indexOf("p") == 0){
                shell.executeCommand("shutdown /h");
            }
        } else{
            System.out.println("Maquina saudavel");
        }
    }
    
}
