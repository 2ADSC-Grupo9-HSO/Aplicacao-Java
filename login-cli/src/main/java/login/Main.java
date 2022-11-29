/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessosGroup;
import java.io.IOException;
import java.util.List;
import micro.servicos.Log;

/**
 *
 * @author rmacedo
 */
public class Main {
    public static void main(String[] args) throws IOException {
        LoginCli shell = new LoginCli();
        
        //REGISTERING THE START OF APPLICATION IN USAGE LOG AT EACH RUN 
        shell.executeCommand("clear");
        
        System.out.println("Bem Vindo!");
        
        new Log("Uso", "Aplicação começou a rodar");
        
        shell.cli();
    }
}
