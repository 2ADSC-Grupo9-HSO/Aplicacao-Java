/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import com.github.britooo.looca.api.core.Looca;
import maquina.Maquina;
import database.ModuloConexao;
import database.Requests;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import captura.dados.TelaDados;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author svaraujo
 */
public class LoginCli {

    private ModuloConexao conexaoMysql = new ModuloConexao("Desenvolvimento");
    private ModuloConexao conexaoAzure = new ModuloConexao("Produção");
    private Requests requisicoes = new Requests();

    public void executeCommand(final String command) throws IOException {

        final ArrayList<String> commands = new ArrayList<String>();

        Looca looca = new Looca();

        if (looca.getSistema().getSistemaOperacional().equalsIgnoreCase("Windows")) {
//          No Windão
            commands.add("cmd");
            commands.add("/c");
            commands.add(command);
        } else {
//            No Linux
            commands.add("/bin/bash");
            commands.add("-c");
            commands.add(command);

        }

        BufferedReader br = null;
        try {
            final ProcessBuilder p = new ProcessBuilder(commands);
            final Process process = p.start();
            final InputStream is = process.getInputStream();
            final InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("retorno do comando shell: " + line);
            }

        } catch (IOException ioe) {
            System.out.println("Erro ao executar comando shell" + ioe.getMessage());
        } finally {
            secureClose(br);
        }
    }

    public String returnCommand(String command) throws IOException {

        final ArrayList<String> commands = new ArrayList<String>();

        Looca looca = new Looca();

        if (looca.getSistema().getSistemaOperacional().equalsIgnoreCase("Windows")) {
//          No Windão
            commands.add("cmd");
            commands.add("/c");
            commands.add(command);
        } else {
//            No Linux
            commands.add("/bin/bash");
            commands.add("-c");
            commands.add(command);

        }

        BufferedReader br = null;
        String retorno = "";
        try {
            final ProcessBuilder p = new ProcessBuilder(commands);
            final Process process = p.start();
            final InputStream is = process.getInputStream();
            final InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                retorno = line;
            }

        } catch (IOException ioe) {
            System.out.println("Erro ao executar comando shell" + ioe.getMessage());
        } finally {
            secureClose(br);
            return retorno;
        }
        

    }

    private void secureClose(final Closeable resource) {
        try {
            if (resource != null) {
                resource.close();
            }
        } catch (IOException ex) {
            System.out.println("Erro = " + ex.getMessage());
        }
    }

}
