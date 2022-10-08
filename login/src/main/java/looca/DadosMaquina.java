/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package looca;

import com.github.britooo.looca.api.group.processos.ProcessosGroup;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.util.Conversor;
import java.util.List;
//a
/**
 *
 * @author svaraujo
 */
public class DadosMaquina {
        public static void main(String[] args) {

        Looca looca = new Looca();

        ProcessosGroup grupoProcessos = looca.getGrupoDeProcessos();
        List<Processo> processos = grupoProcessos.getProcessos();

        System.out.println("|  Detalhes dos pocessos  |");
        for (Processo processo : processos) {
            System.out.println(processo);
            System.out.println("............................................................................\n");
        }
       

        System.out.println("| Detalhes do processador |");
        System.out.println(looca.getProcessador().getUso());
        System.out.println(".............................\n");
        





// Listando a memoria RAM e seus atributos
        System.out.println("|   Detalhes da memoria   |");
        System.out.println("Valor total da memoria RAM");
        System.out.println(looca.getMemoria().getTotal());
        System.out.println("\nValor sendo usado da memoria RAM");
        System.out.println(looca.getMemoria().getEmUso());
        System.out.println("\nValor disponivel memoria RAM");
        System.out.println(looca.getMemoria().getDisponivel());
        System.out.println("\nvalor percentual sendo usada da Ram");
        Double porcentUsoMemoria =  looca.getMemoria().getEmUso() * 100.00 / looca.getMemoria().getTotal();
        System.out.println(porcentUsoMemoria);
        System.out.println("..............................................\n\n");
        
        

//Listando o Volume e seus atributos
        List<Volume> volumes = new Looca().getGrupoDeDiscos().getVolumes();

        Long volumeTotal = 0L;
        Long volumeDisponivel = 0L;
        for (Volume v : volumes) {

            volumeTotal = v.getTotal();
            volumeDisponivel = v.getDisponivel();
        }

        Long volumeEmUso = volumeTotal - volumeDisponivel;
        Double porcentVolumeEmUso = (volumeEmUso * 100.00 / volumeTotal);

        System.out.println("|   Detalhes do volume do disco   |");
        System.out.println("Volume total do disco");
        System.out.println(volumeTotal);
        System.out.println("\nVolume sendo usado do disco");
        System.out.println(volumeEmUso);
        System.out.println("\nVolume disponivel do disco");
        System.out.println(volumeDisponivel);
        System.out.println("\nvolume percentual em uso do disco");
        System.out.println(porcentVolumeEmUso);
        System.out.println("..............................................");
    }
}

