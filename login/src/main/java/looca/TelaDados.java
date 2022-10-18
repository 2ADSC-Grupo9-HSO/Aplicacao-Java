/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package looca;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import database.HardMaquina;
import database.Maquina;
import database.Requests;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author rmacedo
 */
public class TelaDados extends javax.swing.JFrame {

    private Requests requisicoes = new Requests();
    Looca looca = new Looca();

    /**
     * Creates new form TelaDados
     */
    public TelaDados(JdbcTemplate conexao, Maquina maquina) {
        initComponents();

        System.out.println(maquina);

        for (HardMaquina itemMaquina : maquina.hardMaquina) {
            System.out.println(itemMaquina);
        }

        this.inserirDados(conexao, maquina);
    }

    private void inserirDados(JdbcTemplate conexao, Maquina maquina) {
        int delay = 2000;   // tempo de espera antes da 1ª execução da tarefa.
        int interval = 10000;  // intervalo no qual a tarefa será executada.
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                for (HardMaquina componente : maquina.hardMaquina) {
                    if (componente.getFkComponente().equals(1)) {

                        Double processador = looca.getProcessador().getUso();

                        requisicoes.insertSQL(conexao, componente.getIdMaquina(), processador);

                        System.out.println("processador");

                    } else if (componente.getFkComponente().equals(2)) {

                        Double porcentUsoMemoria = looca.getMemoria().getEmUso() * 100.00 / looca.getMemoria().getTotal();

                        requisicoes.insertSQL(conexao, componente.getIdMaquina(), porcentUsoMemoria);

                        System.out.println("memoria");

                    } else if (componente.getFkComponente().equals(3)) {

                        List<Volume> volumes = new Looca().getGrupoDeDiscos().getVolumes();

                        Long volumeTotal = 0L;
                        Long volumeDisponivel = 0L;
                        for (Volume v : volumes) {

                            volumeTotal = v.getTotal();
                            volumeDisponivel = v.getDisponivel();
                        }

                        Long volumeEmUso = volumeTotal - volumeDisponivel;
                        Double porcentVolumeEmUso = (volumeEmUso * 100.00 / volumeTotal);

                        requisicoes.insertSQL(conexao, componente.getIdMaquina(), porcentVolumeEmUso);

                        System.out.println("disco");

                    }
                }
            }
        }, delay, interval);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblLoading = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 191, 255));
        jLabel1.setText("Os dados da sua máquina já estão sendo capturados");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 191, 255));
        jLabel2.setText("você pode utilizar o computador normalmente");

        lblLoading.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblLoading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/LoadingGif.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel2)))
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 303, Short.MAX_VALUE)
                .addComponent(lblLoading)
                .addGap(303, 303, 303))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(lblLoading)
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaDados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblLoading;
    // End of variables declaration//GEN-END:variables
}
