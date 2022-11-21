/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import maquina.MaquinaComProblema;
import maquina.HardMaquina;
import maquina.Maquina;
import maquina.TopProcesso;
import com.github.britooo.looca.api.group.processos.Processo;
import java.sql.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author rmacedo
 */
public class Requests {

    public Maquina loginSQL(JdbcTemplate conexao, String user, String password) {

        String sql = "SELECT idMaquina, hostName, senhaMaquina FROM tbMaquina WHERE hostName = ? AND senhaMaquina = ?";

        String sql2 = "SELECT idHardware, fkComponente "
                + "FROM tbMaquina "
                + "JOIN tbHardware ON idMaquina = fkMaquina "
                + "WHERE hostName = ? AND senhaMaquina = ?";

        try {

            Maquina maquina = conexao.queryForObject(sql, new BeanPropertyRowMapper<>(Maquina.class), user, password);

            maquina.hardMaquina = conexao.query(sql2, new BeanPropertyRowMapper<>(HardMaquina.class), user, password);

            System.out.println(maquina);

            for (HardMaquina itemMaquina : maquina.hardMaquina) {
                System.out.println(itemMaquina);
            }

            return maquina;

        } catch (Exception ex) {

            System.out.println("Não foi possivel selecionar os dados " + ex);

            return null;
        }
    }

    public void insertSQL(JdbcTemplate conexao, Integer fkMaquina, Double registro) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO tbHistorico (fkHardware, valorRegistro) VALUES (" + fkMaquina + "," + registro + " )";

        try {

            conexao.execute(sql);

        } catch (Exception ex) {

            System.out.println("Ocorreu um problema ao inserir dados " + ex);

        }
    }

    public void insertMaquinaDocker(JdbcTemplate conexao, String hostName, String senhaMaquina, String sistemaOperacional) {
        String sql = "INSERT INTO tbMaquina (idMaquina, hostName, senhaMaquina, sistemaOperacional) VALUES( 1, '" + hostName + "', '" + senhaMaquina + "', '" + sistemaOperacional + "');";

        String sql2 = "INSERT INTO tbHardware (fkComponente, fkMaquina) VALUES (1, 1), (2, 1), (3, 1);";

        try {

            conexao.execute(sql);
            conexao.execute(sql2);

        } catch (Exception ex) {

            System.out.println("Erro ao inserir dados no docker " + ex);

        }
    }

    public MaquinaComProblema selectMaquinaDanificada(JdbcTemplate conexao, Maquina maquina) {
        String sql = "select sum(cont) as \"qtd_maquinas_debilitadas\" from(\n"
                + "\n"
                + "\n"
                + "\n"
                + "       select count(contagem) as 'cont' from(\n"
                + "          select count(fkMaquina) as 'contagem' from tbHistorico as hi\n"
                + "           join tbHardware as hw on hw.idHardware = hi.fkHardware\n"
                + "           join tbMaquina as m on m.idMaquina = hw.fkMaquina\n"
                + "           where valorRegistro >= 95\n"
                + "           and hi.momentoRegistro > now() - interval 10 minute\n"
                + "           and hw.fkComponente = 1\n"
                + "           and m.idMaquina =" + maquina.getIdMaquina() + "\n"
                + "           group by hw.fkMaquina\n"
                + "        ) as tabela_comp_1\n"
                + "           where contagem > 1\n"
                + "\n"
                + "           union all\n"
                + "\n"
                + "        select count(contagem) as 'cont' from(\n"
                + "          select count(fkMaquina) as 'contagem' from tbHistorico as hi\n"
                + "           join tbHardware as hw on hw.idHardware = hi.fkHardware\n"
                + "           join tbMaquina as m on m.idMaquina = hw.fkMaquina\n"
                + "           where valorRegistro >= 90\n"
                + "           and hi.momentoRegistro > now() - interval 1 minute\n"
                + "           and hw.fkComponente = 2\n"
                + "           and m.idMaquina = " + maquina.getIdMaquina() + "\n"
                + "           group by hw.fkMaquina\n"
                + "        ) as tabela_comp_2\n"
                + "           where contagem > 5\n"
                + "\n"
                + "           union all\n"
                + "\n"
                + "        select count(contagem) as 'cont' from(\n"
                + "          select count(fkMaquina) as 'contagem' from tbHistorico as hi\n"
                + "           join tbHardware as hw on hw.idHardware = hi.fkHardware\n"
                + "           join tbMaquina as m on m.idMaquina = hw.fkMaquina\n"
                + "           where valorRegistro >= 99\n"
                + "           and hi.momentoRegistro > now() - interval 3 minute\n"
                + "           and hw.fkComponente = 3\n"
                + "           and m.idMaquina = " + maquina.getIdMaquina() + "\n"
                + "           group by hw.fkMaquina\n"
                + "        ) as tabela_comp_3\n"
                + "           where contagem > 6\n"
                + "\n"
                + "        ) as tabela_final;";

        try {

            MaquinaComProblema maquinaComProblema = conexao.queryForObject(sql, new BeanPropertyRowMapper<>(MaquinaComProblema.class));

            return maquinaComProblema;

        } catch (Exception ex) {

            System.out.println("Erro ao buscar problema " + ex);

            return null;
        }
    }
    
    public void selectProcessos(JdbcTemplate conexao, Maquina maquina){
        
        String sql = "SELECT idProcesso, chaveAtivacao, pid FROM processos WHERE fkMaquina = ?";
        
        try {
            maquina.TopProcessos = conexao.query(sql, new BeanPropertyRowMapper<>(TopProcesso.class), maquina.getIdMaquina());
            
        } catch (Exception ex){
            
            System.out.println("Não foi possivel retornar os dados da lista " + ex);
            
        }
    }

    public void insertProcessos(JdbcTemplate conexao, Processo processo, Integer fkMaquina) {

        String sql = String.format("INSERT INTO processos (fkMaquina, pid, nomeProcesso, consumoRam, chaveAtivacao) VALUES (%d, '%d', '%s', " + processo.getUsoMemoria() + ", '0')", fkMaquina, processo.getPid(), processo.getNome());

        try {

            conexao.execute(sql);

        } catch (Exception ex) {

            System.out.println("Erro ao inserir dados dos processos " + ex);

        }
    }

    public void atualizarProcessos(JdbcTemplate conexao, Processo processo, Integer id) {

        String sql = String.format("UPDATE processos SET pid =  '%d', nomeProcesso = '%s', consumoRam = " + processo.getUsoMemoria() + ", chaveAtivacao = 0 WHERE  idProcesso = %s", processo.getPid(), processo.getNome(), id);

        try {

            conexao.execute(sql);

        } catch (Exception ex) {

            System.out.println("Erro ao atualizar dados dos processos " + ex);

        }
    }

}
