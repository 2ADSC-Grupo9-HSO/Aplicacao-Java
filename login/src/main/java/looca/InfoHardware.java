/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package looca;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.util.Conversor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.SystemInfo;


/**
 *
 * @author svaraujo
 */
public class InfoHardware {
    public static void main(String[] args) {
       Looca looca = new Looca();
        SystemInfo sis = new SystemInfo();
        HardwareAbstractionLayer hardware = sis.getHardware();
        
        Conversor conversor = new Conversor();
// 
//        System.out.println("Numero serial da maquina");
//        System.out.println(hardware.getComputerSystem().getSerialNumber()+"\n");
//     
//        System.out.println("Marca da maquina");
//        System.out.println(hardware.getComputerSystem().getManufacturer()+"\n");
        

        System.out.println("..............................................");
        System.out.println("Informações do sistema\n");
        System.out.println(looca.getSistema());
        System.out.println("..............................................");
        System.out.println("HardInfo Disco");
        System.out.println("Caracteristica do disco");
        System.out.println("Quantidade de discos:" + looca.getGrupoDeDiscos().getQuantidadeDeDiscos());
        System.out.println("Volume total de discos: " + conversor.formatarBytes(looca.getGrupoDeDiscos().getTamanhoTotal()));
        System.out.println("Tipo do disco: "+ looca.getGrupoDeDiscos().getVolumes().get(0).getTipo());
        System.out.println("\nInformações dos disco \n" + looca.getGrupoDeDiscos().getDiscos());
        System.out.println("..............................................");
        System.out.println("HardInfo Memoria ram");
        System.out.println("Tamanho total memória ram: " + conversor.formatarBytes(looca.getMemoria().getTotal()));
        System.out.println("..............................................");
        System.out.println("HardInfo Processador");
        System.out.println(looca.getProcessador().toString());
        System.out.println("..............................................");
    }
    
}
