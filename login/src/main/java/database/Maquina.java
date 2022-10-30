/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.util.List;

/**
 *
 * @author rmacedo
 */
public class Maquina {

    private Integer idInfoMaquina;
    private String hostName;
    private String senhaMaquina;
    private String sistemaOperacional;
    public List<HardMaquina> hardMaquina;

    public void setIdInfoMaquina(Integer idInfoMaquina) {
        this.idInfoMaquina = idInfoMaquina;
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

    public Integer getIdInfoMaquina() {
        return this.idInfoMaquina;
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
        return "idInfoMaquina: " + this.idInfoMaquina + "\nhostName: " + this.hostName + "\nsenhaMaquina: " + this.senhaMaquina;
    }
}
