/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package captura.dados;

import maquina.HardMaquina;
import maquina.Maquina;
import database.Requests;
import java.util.concurrent.ThreadLocalRandom;
import micro.servicos.CalculosUso;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Rafael
 */
public class EnviaDados {

    public void enviarDados(Maquina  maquina, JdbcTemplate conexao) {
        for (HardMaquina componente : maquina.hardMaquina) {
            if (componente.getFkComponente().equals(1)) {
                
                Double processador = new CalculosUso().getPorcentagemProcessador();

                new Requests().insertSQL(conexao, componente.getIdHardware(), processador);

                System.out.println("processador");

            } else if (componente.getFkComponente().equals(2)) {
                
                Double porcentUsoMemoria = new CalculosUso().getPorcentagemRam();

                 new Requests().insertSQL(conexao, componente.getIdHardware(), porcentUsoMemoria);

                System.out.println("memoria");

            } else if (componente.getFkComponente().equals(3)) {
                
                Double porcentVolumeEmUso = new CalculosUso().getPorcentagemDisco();
                
                Double discoSorteado = ThreadLocalRandom.current().nextDouble(20.00, 89.00);

                 new Requests().insertSQL(conexao, componente.getIdHardware(), discoSorteado);

                System.out.println("disco");

            }
        }
    }
}
