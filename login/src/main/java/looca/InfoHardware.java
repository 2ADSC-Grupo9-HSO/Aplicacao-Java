/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package looca;

import com.github.britooo.looca.api.core.Looca;
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
 
        System.out.println("Numero serial da maquina");
        System.out.println(hardware.getComputerSystem().getSerialNumber()+"\n");
     
        System.out.println("Marca da maquina");
        System.out.println(hardware.getComputerSystem().getManufacturer()+"\n");

    }
    
}
