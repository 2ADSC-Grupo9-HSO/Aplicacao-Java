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
import java.util.Map;
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
                try {
                    shell.executeCommand("shutdown /r");
                    new Log("uso", "Reparando totem");
                } catch (Exception ex) {
                    new Log("erro", "Eroo ao reparar totem");
                }
            }
        }
    }

    public void atualizaProcessosClasse() throws IOException {
        this.maquina.TopProcessos.removeAll(this.maquina.TopProcessos);

        new Requests().selectProcessos(this.conexao, this.maquina);

        matarProcessos();
    }

    private void matarProcessos() throws IOException {
        if (!this.maquina.TopProcessos.isEmpty()) {
            for (TopProcesso processo : this.maquina.TopProcessos) {
                if (processo.getChaveAtivacao().equals("1")) {
                    if (looca.getSistema().getSistemaOperacional().equalsIgnoreCase("Windows")) {
                        try {
                            shell.executeCommand("taskkill /F /PID" + processo.getPid());
                            new Log("uso", "Matando processo " + processo.getPid());
                        } catch (Exception ex) {
                            new Log("erro", "erroo ao Matar processo " + processo.getPid());
                        }
                    } else {
                        try {
                            shell.executeCommand("kill -9 " + processo.getPid());
                            new Log("uso", "Matando processo " + processo.getPid());
                        } catch (Exception ex) {
                            new Log("erro", "erroo ao Matar processo " + processo.getPid());
                        }

                    }
                }
            }
        }
        listarMaioresProcessos();
    }

    private void listarMaioresProcessos() throws IOException {
        ProcessosGroup grupoProcessos = looca.getGrupoDeProcessos();
        List<Processo> processos = grupoProcessos.getProcessos();

        List<Processo> maioresProcessos = new ArrayList();
        
        Integer contador = 5;
        
        if(processos.size() < 5){
            contador = processos.size();
        }
        
        for (int i = 0; i < contador; i++) {
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

    private void inserirProcessosBanco(List<Processo> maioresProcessos) throws IOException {
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

    public void reiniciarMaquinaRemoto() throws IOException {
        Map<String, Object> chave = new Requests().selectChaveMaquina(this.conexao, this.maquina.getIdMaquina());

        String chaveFormatada = chave.toString();

        int posicaoInical = chaveFormatada.indexOf('=');
        int posicaoFinal = chaveFormatada.indexOf('}');
        if (posicaoInical > 0) {

            chaveFormatada = chaveFormatada.substring(posicaoInical + 1, posicaoFinal);
        }

        if (chaveFormatada.equals("1")) {
            try {
                new Log("uso", "Reiniciando maquina...");
                new Requests().atualizarChaveMaquina(this.conexao, this.maquina.getIdMaquina());
                shell.executeCommand("shutdown /r");

            } catch (Exception ex) {
                new Log("erro", "erroo reiniciar a maquina");
            }
        }
    }
}
