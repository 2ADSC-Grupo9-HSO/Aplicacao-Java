/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package captura.dados;

import database.Requests;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import maquina.Maquina;
import micro.servicos.Inovacao;
import static micro.servicos.SlackIntegrationTest.sendMessageDiscoToSlack;
import static micro.servicos.SlackIntegrationTest.sendMessageProcessadorToSlack;
import static micro.servicos.SlackIntegrationTest.sendMessageRamToSlack;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author rmacedo
 */
public class TelaDados {

    private Requests requisicoes;
    private Inovacao inovacao;

    /**
     * Creates new form TelaDados
     */
    public TelaDados(JdbcTemplate conexaoMysql, Maquina maquinaMysql, JdbcTemplate conexaoAzure, Maquina maquinaAzure) {
        this.requisicoes = new Requests();
        this.inovacao = new Inovacao(conexaoAzure, maquinaAzure);

        this.inserirDados(conexaoMysql, maquinaMysql, conexaoAzure, maquinaAzure);
    }

    private void inserirDados(JdbcTemplate conexaoMysql, Maquina maquinaMysql, JdbcTemplate conexaoAzure, Maquina maquinaAzure) {
        int delay = 2000;
        int interval = 30000;
        int delay2 = 2000;
        int interval2 = 700;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                try {
                    new EnviaDados().enviarDados(maquinaMysql, conexaoMysql);
                    new EnviaDados().enviarDados(maquinaAzure, conexaoAzure);

                    sendMessageProcessadorToSlack("Valor de processador ultrapassado!", conexaoAzure, maquinaAzure);
                    sendMessageRamToSlack("Valor de memória RAM ultrapassada!", conexaoAzure, maquinaAzure);
                    sendMessageDiscoToSlack("Valor de disco ultrapassado!");
                } catch (Exception ex) {
                }
            }
        }, delay, interval);

        Timer timer2 = new Timer();
        timer2.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                try {
                    inovacao.repararTotem();
                    inovacao.reiniciarMaquinaRemoto();
                    inovacao.atualizaProcessosClasse();
                } catch (IOException ex) {
                    System.out.println("Erro" + ex);
                }
            }
        }, delay2, interval2);
    }
}
