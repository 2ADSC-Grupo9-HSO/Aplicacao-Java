/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import java.io.IOException;
import micro.servicos.Log;

/**
 *
 * @author rmacedo
 */
public class Main {
    public static void main(String[] args) throws IOException {
        LoginCli shell = new LoginCli();
        
        //REGISTERING THE START OF APPLICATION IN USAGE LOG AT EACH RUN 
        System.out.println("Bem Vindo!");
        
        new Log("Uso", "Aplicação começou a rodar");
        
        shell.cli();
    }
}
