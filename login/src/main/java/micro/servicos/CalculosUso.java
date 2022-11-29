/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package micro.servicos;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import java.util.List;

/**
 *
 * @author rmacedo
 */
public class CalculosUso {

    public Double getPorcentagemProcessador() {
        Double processador = new Looca().getProcessador().getUso();

        return processador;
    }

    public Double getPorcentagemRam() {
        Double porcentUsoMemoria = new Looca().getMemoria().getEmUso() * 100.00 / new Looca().getMemoria().getTotal();

        return porcentUsoMemoria;
    }

    public Double getPorcentagemDisco() {
        List<Volume> volumes = new Looca().getGrupoDeDiscos().getVolumes();

        Long volumeTotal = 0L;
        Long volumeDisponivel = 0L;
        for (Volume v : volumes) {

            volumeTotal = v.getTotal();
            volumeDisponivel = v.getDisponivel();
        }
        
        

        Long volumeEmUso = volumeTotal - volumeDisponivel;
        Double porcentVolumeEmUso = (volumeEmUso * 100.00 / volumeTotal);

        return porcentVolumeEmUso;
    }
}
