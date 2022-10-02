/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sptech.dados.da.maquina;

import com.github.britooo.looca.api.group.processos.ProcessosGroup;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.core.Looca;
import java.util.List;


/**
 *
 * @author svaraujo
 */
public class ListarInformacoes {

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
        System.out.println(looca.getProcessador());
        System.out.println(".............................\n");
        System.out.println("|   Detalhes da memoria   |");
        System.out.println(looca.getMemoria());
        System.out.println(".............................\n");
        System.out.println("|   Detalhes do Disco     |");
        System.out.println(looca.getGrupoDeDiscos().getDiscos());
        System.out.println(".............................");
        

    }
}
