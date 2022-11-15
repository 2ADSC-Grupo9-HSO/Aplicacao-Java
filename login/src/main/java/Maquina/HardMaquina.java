/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maquina;

/**
 *
 * @author rmacedo
 */
public class HardMaquina {
    private Integer idHardware;
    private Integer fkComponente;

    public void setIdHardware(Integer idHardware) {
        this.idHardware = idHardware;
    }

    public void setFkComponente(Integer fkComponente) {
        this.fkComponente = fkComponente;
    }

    public Integer getIdHardware() {
        return this.idHardware;
    }

    public Integer getFkComponente() {
        return this.fkComponente;
    }

    @Override
    public String toString() {
        return "HardMaquina{" + "idMaquina=" + idHardware + ", fkComponente=" + fkComponente + '}';
    }
}
