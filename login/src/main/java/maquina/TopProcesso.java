/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maquina;

/**
 *
 * @author Rafael
 */
public class TopProcesso {
    private Integer idProcesso;
    private String pid;
    private String chaveAtivacao;

    public Integer getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Integer idProcesso) {
        this.idProcesso = idProcesso;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getChaveAtivacao() {
        return chaveAtivacao;
    }

    public void setChaveAtivacao(String chaveAtivacao) {
        this.chaveAtivacao = chaveAtivacao;
    }

    @Override
    public String toString() {
        return "TopProcesso{" + "idProcesso=" + idProcesso + ", pid=" + pid + ", chaveAtivacao=" + chaveAtivacao + '}';
    }
}
