/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KalbisPuskes.gui;

import static java.awt.Color.white;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author IT
 */
public class Antrian_Umum extends javax.swing.JFrame {
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement st = null;
    String ID = Login_Petugas.getUserLogin();

    /**
     * Creates new form Antrian
     */
    public Antrian_Umum() {
        initComponents();
        con = Connect.ConnectDb();
        update_umum();
        autorefresh();
        bNext.setVisible(false);
        this.getContentPane().setBackground(white);
        ImageIcon ico = new ImageIcon("src/KalbisPuskes/gui/logo.png");
        this.setIconImage(ico.getImage());
        no_registrasi.setHorizontalAlignment(SwingConstants.CENTER);
        no_registrasi1.setHorizontalAlignment(SwingConstants.CENTER);
        no_registrasi2.setHorizontalAlignment(SwingConstants.CENTER);
        nama_pasien.setHorizontalAlignment(SwingConstants.CENTER);
        nama_pasien1.setHorizontalAlignment(SwingConstants.CENTER);
        nama_pasien2.setHorizontalAlignment(SwingConstants.CENTER);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bNext = new javax.swing.JButton();
        no_antrian = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        no_registrasi = new javax.swing.JLabel();
        nama_pasien = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        no_antrian1 = new javax.swing.JLabel();
        nama_pasien1 = new javax.swing.JLabel();
        no_registrasi1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        no_antrian2 = new javax.swing.JLabel();
        no_registrasi2 = new javax.swing.JLabel();
        nama_pasien2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Antrian");

        bNext.setText("Next");
        bNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNextActionPerformed(evt);
            }
        });

        no_antrian.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        no_antrian.setText("0");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel1.setText("UMUM");

        no_registrasi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        no_registrasi.setText("-");

        nama_pasien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nama_pasien.setText("-");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel3.setText("GIGI");

        no_antrian1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        no_antrian1.setText("0");

        nama_pasien1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nama_pasien1.setText("-");

        no_registrasi1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        no_registrasi1.setText("-");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel4.setText("KIA");

        no_antrian2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        no_antrian2.setText("0");

        no_registrasi2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        no_registrasi2.setText("-");

        nama_pasien2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nama_pasien2.setText("-");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(bNext)
                .addGap(0, 687, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(no_antrian))
                            .addComponent(jLabel1))
                        .addGap(218, 218, 218)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(no_antrian1))
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(no_registrasi, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nama_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nama_pasien1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(no_registrasi1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nama_pasien2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(no_registrasi2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(no_antrian2))))
                .addGap(62, 62, 62))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(no_antrian, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(no_antrian1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(no_registrasi, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nama_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(no_registrasi1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nama_pasien1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(no_antrian2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(no_registrasi2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nama_pasien2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(bNext)
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void update_umum(){
        try {
            String sql = "select * from antrian where bagian = 'Umum' and flag_active='Y' ORDER BY no_antrian ASC LIMIT 1";
            st = con.prepareStatement(sql);
            try (ResultSet r = st.executeQuery(sql)) {
                while (r.next()){
                    no_antrian.setText(r.getString("no_antrian"));
                    no_registrasi.setText(r.getString("no_registrasi"));
                    nama_pasien.setText(r.getString("nama_pasien"));                       
                }
            }
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan "+e.getMessage());
        }
    }
    
    private void update_gigi(){
        try {   
            String sql = "select * from antrian where bagian = 'Gigi' and flag_active='Y' ORDER BY no_antrian ASC LIMIT 1";
            st = con.prepareStatement(sql);
            try (ResultSet r = st.executeQuery(sql)) {
                while (r.next()){
                    no_antrian1.setText(r.getString("no_antrian"));
                    no_registrasi1.setText(r.getString("no_registrasi"));
                    nama_pasien1.setText(r.getString("nama_pasien"));                       
                }
            }
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan "+e.getMessage());
        }
    }
    
    private void update_bidan(){
        try {   
            String sql = "select * from antrian where bagian = 'Bidan' and flag_active='Y' ORDER BY no_antrian ASC LIMIT 1";
            st = con.prepareStatement(sql);
            try (ResultSet r = st.executeQuery(sql)) {
                while (r.next()){
                    no_antrian2.setText(r.getString("no_antrian"));
                    no_registrasi2.setText(r.getString("no_registrasi"));
                    nama_pasien2.setText(r.getString("nama_pasien"));                       
                }
            }
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan "+e.getMessage());
        }
    }
    
    public void autorefresh(){
        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               update_umum();
               update_gigi();
               update_bidan();
            }
        });
        timer.setDelay(5000); // delay for 30 seconds
        timer.start();
    }
    
    private void bNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNextActionPerformed
        // TODO add your handling code here:
        try {
                
                String sql = "select * from antrian where bagian = 'Umum' and flag_active='Y' ORDER BY tanggal DESC LIMIT 1";
                st = con.prepareStatement(sql);
                try (ResultSet r = st.executeQuery(sql)) {
                    while (r.next()){
                        no_antrian.setText(r.getString("no_antrian"));
                        no_registrasi.setText(r.getString("no_registrasi"));
                        nama_pasien.setText(r.getString("nama_pasien"));                       
                    }
                }
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan "+e.getMessage());
        }
        
    }//GEN-LAST:event_bNextActionPerformed

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
            java.util.logging.Logger.getLogger(Antrian_Umum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Antrian_Umum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Antrian_Umum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Antrian_Umum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Antrian_Umum().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bNext;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel nama_pasien;
    private javax.swing.JLabel nama_pasien1;
    private javax.swing.JLabel nama_pasien2;
    private javax.swing.JLabel no_antrian;
    private javax.swing.JLabel no_antrian1;
    private javax.swing.JLabel no_antrian2;
    private javax.swing.JLabel no_registrasi;
    private javax.swing.JLabel no_registrasi1;
    private javax.swing.JLabel no_registrasi2;
    // End of variables declaration//GEN-END:variables
}