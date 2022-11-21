/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package micro.servicos;

import maquina.Maquina;
import maquina.TopProcesso;
import maquina.MaquinaComProblema;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessosGroup;
import database.Requests;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import login.LoginCli;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Rafael
 */
public class Inovacao {

    private LoginCli shell;
    private Looca looca;
    private JdbcTemplate conexao;
    private Maquina maquina;

    public Inovacao(JdbcTemplate conexao, Maquina maquina) {
        this.shell = new LoginCli();
        this.looca = new Looca();
        this.conexao = conexao;
        this.maquina = maquina;
    }

    public void repararTotem() throws IOException {
        MaquinaComProblema maqPro = new Requests().selectMaquinaDanificada(this.conexao, this.maquina);

        if (maqPro.getQtd_maquinas_debilitadas() > 0) {
            if (maquina.getHostName().indexOf("T") == 0) {
                shell.executeCommand("shutdown /r");
            }
        } else {
            System.out.println("Maquina saudavel");
        }
    }

    public void atualizaProcessosClasse() throws IOException {
        this.maquina.TopProcessos.removeAll(this.maquina.TopProcessos);

        new Requests().selectProcessos(this.conexao, this.maquina);

        matarProcessos();
    }

    private void matarProcessos() throws IOException {
        for (TopProcesso processo : this.maquina.TopProcessos) {
            if (processo.getChaveAtivacao().equals("1")) {
                System.out.println("Caiu aqui ");
                if (looca.getSistema().getSistemaOperacional().equalsIgnoreCase("Windows")) {
                    shell.executeCommand("taskkill /F /PID " + processo.getPid());
                } else {
                    shell.executeCommand("kill -9 " + processo.getPid());
                }
            }
        }
        listarMaioresProcessos();
    }

    private void listarMaioresProcessos() {
        ProcessosGroup grupoProcessos = looca.getGrupoDeProcessos();
        List<Processo> processos = grupoProcessos.getProcessos();

        List<Processo> maioresProcessos = new ArrayList();

        for (int i = 0; i < 5; i++) {
            Processo maiorProcesso = processos.get(0);
            for (int k = 0; k < processos.size(); k++) {
                if (processos.get(k).getUsoMemoria() > maiorProcesso.getUsoMemoria()) {
                    maiorProcesso = processos.get(k);
                }
            }
            maioresProcessos.add(maiorProcesso);
            processos.remove(maiorProcesso);
        }
        inserirProcessosBanco(maioresProcessos);
    }

    private void inserirProcessosBanco(List<Processo> maioresProcessos) {
        if (this.maquina.TopProcessos.isEmpty()) {
            for (Processo processo : maioresProcessos) {
                new Requests().insertProcessos(this.conexao, processo, this.maquina.getIdMaquina());
            }
        } else {
            for (int i = 0; i < maioresProcessos.size(); i++) {
                new Requests().atualizarProcessos(this.conexao, maioresProcessos.get(i), this.maquina.TopProcessos.get(i).getIdProcesso());
            }
        }
    }
}
