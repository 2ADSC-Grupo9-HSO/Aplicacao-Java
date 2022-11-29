/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package captura.dados;

import maquina.HardMaquina;
import maquina.Maquina;
import database.Requests;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import micro.servicos.CalculosUso;
import org.springframework.jdbc.core.JdbcTemplate;
import micro.servicos.Log;

/**
 *
 * @author Rafael
 */
public class EnviaDados {

    public void enviarDados(Maquina maquina, JdbcTemplate conexao) throws IOException {
        for (HardMaquina componente : maquina.hardMaquina) {
            if (componente.getFkComponente().equals(1)) {

                Double processador = new CalculosUso().getPorcentagemProcessador();
                try {
                    new Requests().insertSQL(conexao, componente.getIdHardware(), processador);
                    new Log("uso", "Enviando dados de porcentagem do Processador");
                } catch (Exception ex) {
                    new Log("erro", "Erro ao tentar inserir dados do Processador no banco" + ex.getMessage());
                }
                System.out.println("processador");

            } else if (componente.getFkComponente().equals(2)) {

                Double porcentUsoMemoria = new CalculosUso().getPorcentagemRam();
                try {
                    new Requests().insertSQL(conexao, componente.getIdHardware(), porcentUsoMemoria);
                    new Log("uso", "Enviando dados de porcentagem da memoria RAM");
                } catch (Exception ex) {
                    new Log("erro", "Erro ao tentar inserir dados da RAM no banco" + ex.getMessage());
                }

                System.out.println("memoria");

            } else if (componente.getFkComponente().equals(3)) {

                Double porcentVolumeEmUso = new CalculosUso().getPorcentagemDisco();

                Double discoSorteado = ThreadLocalRandom.current().nextDouble(20.00, 89.00);
                try {
                    new Requests().insertSQL(conexao, componente.getIdHardware(), discoSorteado);
                    new Log("uso", "Enviando dados de porcentagem do Disco");
                } catch (Exception ex) {
                    new Log("erro", "Erro ao tentar inserir dados do Disco no banco" + ex.getMessage());
                }

                System.out.println("disco");

            }
        }
    }
}
