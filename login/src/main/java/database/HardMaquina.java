/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author rmacedo
 */
public class HardMaquina {
    private Integer idMaquina;
    private Integer fkComponente;
    private String valorTotal;

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public void setFkComponente(Integer fkComponente) {
        this.fkComponente = fkComponente;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getIdMaquina() {
        return this.idMaquina;
    }

    public Integer getFkComponente() {
        return this.fkComponente;
    }

    public String getValorTotal() {
        return this.valorTotal;
    }

    @Override
    public String toString() {
        return "HardMaquina{" + "idMaquina=" + idMaquina + ", fkComponente=" + fkComponente + ", valorTotal=" + valorTotal + '}';
    }
}
