/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import com.github.britooo.looca.api.core.Looca;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import Maquina.Maquina;
import database.ModuloConexao;
import database.Requests;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import looca.TelaDados;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author svaraujo
 */
public class LoginCli {

    private ModuloConexao conexaoMysql = new ModuloConexao("Desenvolvimento");
    private ModuloConexao conexaoAzure = new ModuloConexao("Produção");
    private Requests requisicoes = new Requests();

    private void efetuarLogin(String user, String senha) {
        JdbcTemplate conexaoMysql = this.conexaoMysql.getConnection();
        JdbcTemplate conexaoAzure = this.conexaoAzure.getConnection();

        Maquina maquinaMysql = this.requisicoes.loginSQL(conexaoMysql, user, senha);
        Maquina maquinaAzure = this.requisicoes.loginSQL(conexaoAzure, user, senha);

        if (maquinaMysql != null && maquinaAzure != null) {

            new TelaDados(conexaoMysql, maquinaMysql, conexaoAzure, maquinaAzure).setVisible(false);

        } else if (maquinaMysql == null && maquinaAzure != null) {

            this.requisicoes.insertMaquinaDocker(conexaoMysql, maquinaAzure.getHostName(), maquinaAzure.getSenhaMaquina(), maquinaAzure.getSistemaOperacional());

            Maquina maquinaMysql2 = this.requisicoes.loginSQL(conexaoMysql, user, senha);

            if (maquinaMysql2 != null) {
                new TelaDados(conexaoMysql, maquinaMysql2, conexaoAzure, maquinaAzure).setVisible(false);
            }

        }

    }

    public void cli() {
        Scanner leitor = new Scanner(System.in);
        String username;
        String Password;
        do {
            System.out.println("Username:");
            username = leitor.nextLine();
        } while (username.isEmpty());

        do {
            System.out.println("Password");
            Password = leitor.nextLine();
        } while (Password.isEmpty());
        this.efetuarLogin(username, Password);
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
//         
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
                System.out.println("Retorno do comando = [" + line + "]");
            }
        } catch (IOException ioe) {
            //log.severe("Erro ao executar comando shell" + ioe.getMessage());
            throw ioe;
        } finally {
            secureClose(br);
        }
    }

    private void secureClose(final Closeable resource) {
        try {
            if (resource != null) {
                resource.close();
            }
        } catch (IOException ex) {
            //log.severe("Erro = " + ex.getMessage());
        }
    }

}
