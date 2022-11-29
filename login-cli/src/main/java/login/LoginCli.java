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

    public void cli() throws IOException {
        Scanner leitor = new Scanner(System.in);
        String username;
        String Password;
        do {
            System.out.println("Username:");
            username = leitor.nextLine();
        } while (username.isEmpty());

        do {
            System.out.println("Password:");
            Password = leitor.nextLine();
        } while (Password.isEmpty());
        this.efetuarLogin(username, Password);
    }

    private void efetuarLogin(String user, String senha) throws IOException {
        JdbcTemplate conexaoMysql = this.conexaoMysql.getConnection();
        JdbcTemplate conexaoAzure = this.conexaoAzure.getConnection();

        Maquina maquinaMysql = this.requisicoes.loginSQL(conexaoMysql, user, senha);
        Maquina maquinaAzure = this.requisicoes.loginSQL(conexaoAzure, user, senha);

        if (maquinaMysql != null && maquinaAzure != null) {
            
            this.executeCommand("clear");

            System.out.println("Bem vindo usuário da máquina " + maquinaAzure.getHostName());
            System.out.println("Pode usar seu computador normalmente, seus dados já estão sendo coletados");

            new TelaDados(conexaoMysql, maquinaMysql, conexaoAzure, maquinaAzure);

        } else if (maquinaMysql == null && maquinaAzure != null) {

            this.requisicoes.insertMaquinaDocker(conexaoMysql, maquinaAzure.getHostName(), maquinaAzure.getSenhaMaquina(), maquinaAzure.getSistemaOperacional());

            Maquina maquinaMysql2 = this.requisicoes.loginSQL(conexaoMysql, user, senha);

            if (maquinaMysql2 != null) {

                this.executeCommand("clear");

                System.out.println("Bem vindo usuário da máquina " + maquinaAzure.getHostName());
                System.out.println("Pode usar su computador normalmente, seus dados já estão sendo coletados");

                new TelaDados(conexaoMysql, maquinaMysql2, conexaoAzure, maquinaAzure);
            }

        } else {

            this.executeCommand("clear");

            System.out.println("Ops... usuário ou senha errado, por favor tente novamente");

            this.cli();
        }

    }

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
