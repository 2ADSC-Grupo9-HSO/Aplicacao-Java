/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package looca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author svaraujo
 */
public class Log {
// procurar classe que me informa a data de tras para frente - ok
    // Definir quando criar e fechar o log - ok
    // Definir em qual diretorio os text´s de log irão ficar 
    // Criar diretorio a partir do java usando File 
    // Buscar variavel de ambiente para saber o usuario do user usando ProcessBuilder
    // Validar existencia de arquivo log pela verção usando indexOf

    private FileWriter arq;
    private String menssagem;
    private String tipoArquivo;

    public Log(String tipoArquivo, String menssagem) throws IOException {
        this.menssagem = menssagem;
        this.tipoArquivo = tipoArquivo;
        this.criarArquivo();
    }

//OVERLOAD OF CONSTRUCTOR TOWARDS CALL METHOD @lerLog
    public Log() {
    }

    private void criarArquivo() throws IOException {

        SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd-kk"); //você pode usar outras máscara
        String dataAtual = formataData.format(new Date());

        File arquivo = new File(String.format("C:\\Users\\svaraujo\\OneDrive - Grupo VR\\Documentos\\%s-LOG-%s(1).txt", dataAtual, this.tipoArquivo.toUpperCase()));

        // VERIFICA O TAMANHO DO ARQUIVO EM BYTES E CRIA OUTRO COM NOVA VERSÃO CASO ULTRAPASSE O LIMITE
        Integer bytes = 100; // Reajustar o limite de tamanho para apresentação 
        if (arquivo.exists()) {
            for (int i = 2; i <= 10; i++) {
                if (arquivo.length() < bytes) {
                    this.arq = new FileWriter(arquivo, true);
                } else {
                    arquivo = new File(String.format("C:\\Users\\svaraujo\\OneDrive - Grupo VR\\Documentos\\%s-LOG-%s(%d).txt", dataAtual, this.tipoArquivo.toUpperCase(), i));
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

        System.out.printf("\nLog de %s foi gravado com sucesso\n", this.tipoArquivo);
    }

    public String lerLog(String tipoLog, String dataLog, Integer versaoLog) {

        // BUSCA O ARQUIVO DE ACORDO COM A VERSÃO DESEJADA
        if (versaoLog <= 10) {
            String arquivoEscolhido = String.format("C:\\Users\\svaraujo\\OneDrive - Grupo VR\\Documentos\\%s-LOG-%s(%d).txt", dataLog, tipoLog.toUpperCase(), versaoLog);

//        System.out.println("tamanho em bytes");
//        System.out.println(new File(arquivoEscolhido).length());
            try {
                System.out.println("\n-------------------------------------------------------------------------");
                System.out.printf("|      Conteúdo do Log de: %s, na Data: %s, Versão: %d       |\n", tipoLog.toUpperCase(), dataLog, versaoLog);
                System.out.println("-------------------------------------------------------------------------");

                FileReader arquivoLeitura = new FileReader(arquivoEscolhido);
                BufferedReader lerArq = new BufferedReader(arquivoLeitura);

                String conteudoLog = "";
                while (lerArq.readLine() != null) {
                    conteudoLog += String.format("| %s |\n", lerArq.readLine());
                }

                arquivoLeitura.close();

                return conteudoLog;
            } catch (IOException e) {
                return String.format("Erro na abertura do arquivo: %s.\n", e.getMessage());
            }

        } else {
            return String.format("Versão de arquivo inexistente");
        }

    }
}
