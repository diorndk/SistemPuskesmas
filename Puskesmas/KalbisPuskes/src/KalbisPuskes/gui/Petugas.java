/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KalbisPuskes.gui;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author AiVarelEzel
 */
public class Petugas extends javax.swing.JFrame{
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String ID = Login_Admin.getUserLogin();
    

    /**
     * Creates new form Data_Petugas
     */
    public Petugas() {
        initComponents();
        con=Connect.ConnectDb();
        setuptable();
        this.setLocationRelativeTo(null);
        nama_user.setText("Login as: " +ID);
        buttonGroup1.add(rbLaki);
        buttonGroup1.add(rbPerempuan);
        jMenuBar1.add(Box.createHorizontalGlue());
        jMenuBar1.add(datetime);
        jMenuBar1.add(nama_user);
        TanggalSekarang();
    }
    
    public void TanggalSekarang(){     
        Thread clock = new Thread(){
            public void run(){
                for(;;){
                    Calendar cal = new GregorianCalendar();
                    int tanggal = cal.get(Calendar.DAY_OF_MONTH);
                    int bulan = cal.get(Calendar.MONTH);
                    int tahun = cal.get(Calendar.YEAR);
                    int jam = cal.get(Calendar.HOUR_OF_DAY);
                    int menit = cal.get(Calendar.MINUTE);
                    int detik = cal.get(Calendar.SECOND);
                    java.util.Date d = new java.util.Date();
                    String dayweek = "EEEE";
                    SimpleDateFormat sdf = new SimpleDateFormat(dayweek);
                    
                    datetime.setText("Tanggal: "+sdf.format(d)+", "+tanggal+"-"+(bulan+1)+"-"+tahun+"   Waktu: "+jam+":"+(menit)+":"+detik);
                    
                    try{
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Pembayaran.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };        
        clock.start();        
    }
    
    public void setuptable(){
        try
        {
            String sql = "select kode_petugas as 'Kode Petugas', nama_petugas as 'Nama Petugas', jk_petugas as 'Jenis Kelamin', Jabatan, Password from petugas";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tabel_petugas.setModel(DbUtils.resultSetToTableModel(rs));
            //To remove previously added rows
        }
        catch(  SQLException e)
        {
           JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_petugas = new javax.swing.JTable();
        bUbah = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        kode_petugas = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nama_petugas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        bSimpan = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();
        bHapus = new javax.swing.JButton();
        bClear = new javax.swing.JButton();
        txtSearchPetugas = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        datetime = new javax.swing.JMenu();
        nama_user = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Data Petugas");

        tabel_petugas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode Petugas", "Nama Petugas", "Jenis Kelamin", "Password"
            }
        ));
        tabel_petugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_petugasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_petugas);

        bUbah.setText("UBAH");
        bUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Kode Petugas");

        kode_petugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kode_petugasKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Nama Petugas");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Jenis Kelamin");

        rbLaki.setText("Laki laki");
        rbLaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLakiActionPerformed(evt);
            }
        });

        rbPerempuan.setText("Perempuan");
        rbPerempuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPerempuanActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Password");

        bSimpan.setText("SIMPAN");
        bSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanActionPerformed(evt);
            }
        });

        bHapus.setText("HAPUS");
        bHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusActionPerformed(evt);
            }
        });

        bClear.setText("CLEAR");
        bClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearActionPerformed(evt);
            }
        });

        txtSearchPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchPetugasKeyReleased(evt);
            }
        });

        jLabel8.setText("Cari:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Jabatan");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Perawat", "Analis", "Apoteker", "Tenaga Gizi", "Administrasi" }));

        jMenu1.setText("Koneksi");

        jMenuItem1.setText("Log Out");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Navigasi");

        jMenuItem2.setText("Data Dokter");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        datetime.setText("Date");
        jMenuBar1.add(datetime);

        nama_user.setText("user");
        jMenuBar1.add(nama_user);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(kode_petugas)
                    .addComponent(jLabel4)
                    .addComponent(nama_petugas, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbLaki)
                        .addGap(18, 18, 18)
                        .addComponent(rbPerempuan))
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(bSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(password, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bClear, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(7, 7, 7)
                        .addComponent(txtSearchPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(8, 8, 8)
                        .addComponent(kode_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nama_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbLaki)
                            .addComponent(rbPerempuan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(bSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(bHapus)
                    .addComponent(bClear))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
            new Login_Admin().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void bUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahActionPerformed
        // TODO add your handling code here:
    int p = JOptionPane.showConfirmDialog(null, "Anda yakin ingin mengubah data?","Update Data",JOptionPane.YES_NO_OPTION);
    if(p==0){
        try{        
            String value1 = kode_petugas.getText();
            String value2 = nama_petugas.getText();
            String value4 = jk;
            String value5 = String.valueOf(password.getPassword());
            String value6 = ID;
            
            con.createStatement().execute("update petugas set kode_petugas ='"+value1+"', nama_petugas='"+value2+"'"
                    + ", jk_petugas='"+value4+"',"
                    + " password='"+value5+"', tanggal_entri = DATE_FORMAT(NOW(),'%Y-%m-%d %T'), nama_pengentri = '"+value6+"' where kode_petugas='"+value1+"' ");
            JOptionPane.showMessageDialog(rootPane,"Data Terupdate!");
            
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,e);
        }
        setuptable();
    }
    }//GEN-LAST:event_bUbahActionPerformed

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        // TODO add your handling code here:
        try{
            con.createStatement().execute("insert into petugas values('"+kode_petugas.getText()
                    +"','"+nama_petugas.getText()+"','"+jk+"','"+String.valueOf(password.getPassword())+"')");
            JOptionPane.showMessageDialog(rootPane,"Data Tersimpan!");
        }catch (SQLException | HeadlessException e){
            System.out.println("Data sudah pernah diinput");
            JOptionPane.showMessageDialog(null,"Data yang diinput belum lengkap! / Data sudah pernah diinput! / tidak boleh input data yang sama!");
        }
        setuptable();
    }//GEN-LAST:event_bSimpanActionPerformed

    private void rbLakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLakiActionPerformed
        // TODO add your handling code here:
        jk = "L";
    }//GEN-LAST:event_rbLakiActionPerformed

    private void rbPerempuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPerempuanActionPerformed
        // TODO add your handling code here:
        jk = "P";
    }//GEN-LAST:event_rbPerempuanActionPerformed

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        // TODO add your handling code here:
        int p = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data?","Hapus Data",JOptionPane.YES_NO_OPTION);
        if(p==0){
        String sql = "delete from petugas where kode_petugas=?";
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, kode_petugas.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Data Terhapus");
        }catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,e);
        }
        setuptable();
        }
    }//GEN-LAST:event_bHapusActionPerformed

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearActionPerformed
        // TODO add your handling code here:
        kode_petugas.setText("");
        nama_petugas.setText("");
        password.setText("");
    }//GEN-LAST:event_bClearActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
            new Dokter().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void kode_petugasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_petugasKeyTyped
        // TODO add your handling code here:
        if(kode_petugas.getText().length()>=6){  
           evt.consume();
        }
    }//GEN-LAST:event_kode_petugasKeyTyped

    private void txtSearchPetugasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchPetugasKeyReleased
        // TODO add your handling code here:
        try{
            
            String sql = "select kode_petugas as 'Kode Petugas', nama_petugas as 'Nama Petugas', jk_petugas as 'Jenis Kelamin', Password from petugas where kode_petugas LIKE ? or nama_petugas LIKE ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%"+txtSearchPetugas.getText()+"%");
            pst.setString(2, "%"+txtSearchPetugas.getText()+"%");
            rs=pst.executeQuery();
            tabel_petugas.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);            
        }
    }//GEN-LAST:event_txtSearchPetugasKeyReleased

    private void tabel_petugasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_petugasMouseClicked
        // TODO add your handling code here:
        try{
            int row = tabel_petugas.getSelectedRow();
            String klik=(tabel_petugas.getModel().getValueAt(row,0).toString());
            String sql = "select kode_petugas as 'Kode Petugas', nama_petugas as 'Nama Petugas', jk_petugas as 'Jenis Kelamin', Password from petugas where kode_petugas='"+klik+"' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                String add1 = rs.getString("Kode Petugas");
                kode_petugas.setText(add1);
                String add2 = rs.getString("Nama Petugas");
                nama_petugas.setText(add2);
                String add3 = rs.getString("Jenis Kelamin");
                switch(add3){
                    case"L":
                        rbLaki.setSelected(true);
                        break;
                    case"P":
                        rbPerempuan.setSelected(true);
                        break;
                }
                String add4 = rs.getString("Password");
                password.setText(add4);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tabel_petugasMouseClicked

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
            java.util.logging.Logger.getLogger(Petugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Petugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Petugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Petugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Petugas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClear;
    private javax.swing.JButton bHapus;
    private javax.swing.JButton bSimpan;
    private javax.swing.JButton bUbah;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenu datetime;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kode_petugas;
    private javax.swing.JTextField nama_petugas;
    private javax.swing.JMenu nama_user;
    private javax.swing.JPasswordField password;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JTable tabel_petugas;
    private javax.swing.JTextField txtSearchPetugas;
    // End of variables declaration//GEN-END:variables
private String jk;
}
