/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author rmacedo
 */
public class Maquina {
    private Integer idInfoMaquina; 
           private String hostName; 
           private Integer senhaMaquina;

    public Integer getIdInfoMaquina() {
        return this.idInfoMaquina;
    }

    public String getHostName() {
        return this.hostName;
    }

    @Override
    public String toString() {
        return "idInfoMaquina: " + this.idInfoMaquina + "\nhostName: " + this.hostName + "\nsenhaMaquina: " + this.senhaMaquina;
    }

    public void setIdInfoMaquina(Integer idInfoMaquina) {
        this.idInfoMaquina = idInfoMaquina;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setSenhaMaquina(Integer senhaMaquina) {
        this.senhaMaquina = senhaMaquina;
    }
}
