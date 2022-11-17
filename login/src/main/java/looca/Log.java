/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package looca;

import com.mysql.cj.protocol.a.LocalDateTimeValueEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author svaraujo
 */
public class Log {
// procurar classe que me informa a data de tras para frente
    // definir quando criar e fechar o log
    // definir em qual diretorio os text´s de log irão ficar
    // criar metodos especificos para tipos diferentes de log

    private FileWriter arq;

    public Log(String nomeRecebido, String menssagem) throws IOException {

        SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd-hh-mm"); //você pode usar outras máscaras
        Date data = new Date();
        String dataAtual = formataData.format(data);

        String localArquivo = String.format("C:\\Users\\svaraujo\\OneDrive - Grupo VR\\Documentos\\NetBeansProjects\\%s-LOG-%s.txt", dataAtual, nomeRecebido.toUpperCase());
        this.arq = new FileWriter(localArquivo, true);
        this.inserirRegistro(menssagem);

    }

    public Log() {
    }

    private void inserirRegistro(String menssagem) throws IOException {

        SimpleDateFormat formataDataHora = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //você pode usar outras máscaras
        Date data = new Date();
        String TempoAtual = formataDataHora.format(data);

        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.println(menssagem + "  " + TempoAtual);
        arq.close();

        System.out.printf("\nfoi gravada com sucesso");
    }

    public String lerLog(String dataLog, String nomeRecebido) {

        String localArquivo = String.format("C:\\Users\\svaraujo\\OneDrive - Grupo VR\\Documentos\\NetBeansProjects\\%s-LOG-%s.txt", dataLog, nomeRecebido.toUpperCase());
        System.out.printf("\nConteúdo do log de %s, na data de %s:\n", nomeRecebido.toUpperCase(), dataLog);

        try {
            FileReader arquivoLeitura = new FileReader(localArquivo);
            BufferedReader lerArq = new BufferedReader(arquivoLeitura);

            String conteudo = "";

            String linha = lerArq.readLine(); // lê a primeira linha
            while (linha != null) {
                conteudo += linha + "\n";
                linha = lerArq.readLine(); // lê da segunda até a última linha   
            }

            arquivoLeitura.close();
            return conteudo;
        } catch (IOException e) {
            return String.format("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }
}
