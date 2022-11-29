/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package micro.servicos;

import com.github.britooo.looca.api.core.Looca;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import login.LoginCli;

/**
 *
 * @author svaraujo
 */
public class Log {

    // Definir em qual diretorio os text´s de log irão ficar
    private FileWriter arq;
    private String menssagem;
    private String tipoArquivo;
    private String tipoFileSystem;

    public Log(String tipoArquivo, String menssagem) throws IOException {
        this.menssagem = menssagem;
        this.tipoArquivo = tipoArquivo;
        this.criarArquivo();
    }

//OVERLOAD OF CONSTRUCTOR TOWARDS CALL METHOD @lerLog
    public Log() {
    }

    private String buscarUsuarioLocal() throws IOException {

        Looca looca = new Looca();
        LoginCli shell = new LoginCli();

        if (looca.getSistema().getSistemaOperacional().equalsIgnoreCase("Windows")) {
            this.tipoFileSystem = "\\";
        } else {
            this.tipoFileSystem = "/";
        }

        String usuarioLocal;
        if (looca.getSistema().getSistemaOperacional().equalsIgnoreCase("Windows")) {
            usuarioLocal = shell.returnCommand("echo %homedrive%%homepath%");

        } else {
            shell.executeCommand("chmod 777 $HOME");
            usuarioLocal = shell.returnCommand("echo $HOME");

        }
        return usuarioLocal;
    }

    private void criarArquivo() throws IOException {

        SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd-kk"); //você pode usar outras máscara
        String dataAtual = formataData.format(new Date());

        File diretorio = new File(String.format("%s%sLogs", this.buscarUsuarioLocal(), this.tipoFileSystem));
        diretorio.mkdir();

        File arquivo = new File(String.format("%s%sLogs%s%s-LOG-%s(1).txt", this.buscarUsuarioLocal(), this.tipoFileSystem, this.tipoFileSystem, dataAtual, this.tipoArquivo.toUpperCase()));

        // VERIFICA O TAMANHO DO ARQUIVO EM BYTES E CRIA OUTRO COM NOVA VERSÃO CASO ULTRAPASSE O LIMITE
        Integer bytes = 100; // Reajustar o limite de tamanho para apresentação 
        if (arquivo.exists()) {
            for (int i = 2; i <= 10; i++) {
                if (arquivo.length() < bytes) {
                    this.arq = new FileWriter(arquivo, true);
                } else {
                    arquivo = new File(String.format("%s%sLogs%s%s-LOG-%s(%d).txt", this.buscarUsuarioLocal(), this.tipoFileSystem, this.tipoFileSystem, dataAtual, this.tipoArquivo.toUpperCase(), i));
                    this.arq = new FileWriter(arquivo, true);
                }
            }
        } else {
            this.arq = new FileWriter(arquivo, true);
        }

        this.inserirRegistro();
    }

    private void inserirRegistro() throws IOException {

        SimpleDateFormat formataData = new SimpleDateFormat("yyyy/MM/dd"); //você pode usar outras máscaras
        SimpleDateFormat formataTempo = new SimpleDateFormat("kk:mm:ss"); //você pode usar outras máscaras

        String dataAtual = formataData.format(new Date());
        String tempoAtual = formataTempo.format(new Date());

        PrintWriter gravarArq = new PrintWriter(this.arq);
        gravarArq.printf("Menssagem: %s; Data:%s; Horário:%s\n", this.menssagem, dataAtual, tempoAtual);
        this.arq.close();
    }

    public String lerLog(String tipoLog, String dataLog, Integer versaoLog) throws IOException {

        File arquivoValido = new File(String.format("%s%sLogs%s%s-LOG-%s(%d).txt", this.buscarUsuarioLocal(), this.tipoFileSystem, this.tipoFileSystem, dataLog, tipoLog.toUpperCase(), versaoLog));
        Boolean valido = false;
        Integer versoesDisponiveis = 0;
        if (!arquivoValido.exists()) {

            for (int i = 0; i < 10; i++) {
                arquivoValido = new File(String.format("%s%sLogs%s%s-LOG-%s(%d).txt", this.buscarUsuarioLocal(), this.tipoFileSystem, this.tipoFileSystem, dataLog, tipoLog.toUpperCase(), i));
                if (arquivoValido.exists()) {
                    versoesDisponiveis++;
                    valido = true;
                }
            }
            if (!valido) {
                return ("Arquivo Invalido");
            }
            return ("Versão não existe\nExistente no momento até a " + versoesDisponiveis + "° versão.");
        } else {

            // BUSCA O ARQUIVO DE ACORDO COM A VERSÃO DESEJADA
            String arquivoEscolhido = String.format("%s%sLogs%s%s-LOG-%s(%d).txt", this.buscarUsuarioLocal(), this.tipoFileSystem, this.tipoFileSystem, dataLog, tipoLog.toUpperCase(), versaoLog);

//        System.out.println("tamanho em bytes");
//        System.out.println(new File(arquivoEscolhido).length());
            try {
                System.out.println("\n-------------------------------------------------------------------------");
                System.out.printf("|      Conteúdo do Log de: %s, na Data: %s, Versão: %d       |\n", tipoLog.toUpperCase(), dataLog, versaoLog);
                System.out.println("-------------------------------------------------------------------------");

                FileReader arquivoLeitura = new FileReader(arquivoEscolhido);
                BufferedReader lerArq = new BufferedReader(arquivoLeitura);

                String conteudoLog = "";
                String linha = lerArq.readLine();
                while (linha != null) {
                    conteudoLog += String.format("| %s |\n", linha);
                    linha = lerArq.readLine(); // lê da segunda até a última linha
                }

                arquivoLeitura.close();

                return conteudoLog;
            } catch (IOException e) {
                return String.format("Erro na abertura do arquivo: %s.\n", e.getMessage());
            }

        }

    }
}
