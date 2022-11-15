/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Maquina;

import java.util.List;

/**
 *
 * @author rmacedo
 */
public class Maquina {

    private Integer idMaquina;
    private String hostName;
    private String senhaMaquina;
    private String sistemaOperacional;
    public List<HardMaquina> hardMaquina;

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setSenhaMaquina(String senhaMaquina) {
        this.senhaMaquina = senhaMaquina;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public Integer getIdMaquina() {
        return this.idMaquina;
    }

    public String getHostName() {
        return this.hostName;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public String getSenhaMaquina() {
        return senhaMaquina;
    }

    @Override
    public String toString() {
        return "idInfoMaquina: " + this.idMaquina + "\nhostName: " + this.hostName + "\nsenhaMaquina: " + this.senhaMaquina;
    }
}
