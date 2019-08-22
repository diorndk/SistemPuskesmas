/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KalbisPuskes.gui;

import static java.awt.Color.white;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import static java.lang.Integer.parseInt;
import static java.lang.Thread.sleep;
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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author AiVarelEzel
 */
public class Karyawan extends javax.swing.JFrame{
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String ID = Login.getUserLogin(); 
    /**
     * Creates new form Data_Petugas
     */
    public Karyawan() {
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
        tabel_karyawan.setAutoCreateRowSorter(true);
        adjustColumn(tabel_karyawan);
        this.getContentPane().setBackground(white);
        ImageIcon ico = new ImageIcon("src/KalbisPuskes/gui/logo.png");
        this.setIconImage(ico.getImage());
    }
    
    public void adjustColumn(JTable tabel_karyawan){
     TableColumnModel modelKolom = tabel_karyawan.getColumnModel();
     for(int kol=0; kol < modelKolom.getColumnCount(); kol++){
    		int lebarKolomMax=0;
    		for(int baris=0;baris<tabel_karyawan.getRowCount();baris++){
    			TableCellRenderer rend=tabel_karyawan.getCellRenderer(baris,kol);
    			Object nilaiTablel=tabel_karyawan.getValueAt(baris,kol);
    			Component comp=rend.getTableCellRendererComponent(tabel_karyawan,nilaiTablel,false,false,baris,kol);
				lebarKolomMax=Math.max(comp.getPreferredSize().width,lebarKolomMax);
    		}//akhir for baris
    		TableColumn kolom=modelKolom.getColumn(kol);
    		kolom.setPreferredWidth(lebarKolomMax);
    	}//akhir for kolom
    }
    
    public void TanggalSekarang(){     
        Thread clock = new Thread(){
            public void run(){
                for(;;){
                    Calendar cal = new GregorianCalendar();
                    int tanggal = cal.get(Calendar.DAY_OF_MONTH);
                    int tahun = cal.get(Calendar.YEAR);
                    java.util.Date d = new java.util.Date();
                    String bulan = "MMMM";
                    String dayweek = "EEEE";
                    String jam = "HH";
                    String menit = "mm";
                    String detik = "ss";
                    SimpleDateFormat sdf = new SimpleDateFormat(dayweek);
                    SimpleDateFormat bln = new SimpleDateFormat(bulan);
                    SimpleDateFormat hr = new SimpleDateFormat(jam);
                    SimpleDateFormat mnt = new SimpleDateFormat(menit);
                    SimpleDateFormat sd = new SimpleDateFormat(detik);
                    datetime.setText("Tanggal: "+sdf.format(d)+", "+tanggal+" "+(bln.format(d))+" "+tahun+"   Waktu: "+hr.format(d)+":"+(mnt.format(d))+":"+sd.format(d));
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
            String sql = "select kode_karyawan as 'Kode Karyawan', nama_karyawan as 'Nama Karyawan', jk_karyawan as 'Jenis Kelamin', jabatan as Jabatan, password as Password from karyawan where kode_karyawan != '000000'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tabel_karyawan.setModel(DbUtils.resultSetToTableModel(rs));
            //To remove previously added rows
        }
        catch(  SQLException e)
        {
           JOptionPane.showMessageDialog(null, e.toString());
        }
        adjustColumn(tabel_karyawan);
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
        tabel_karyawan = new javax.swing.JTable();
        bUbah = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nama_karyawan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        bSimpan = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();
        bHapus = new javax.swing.JButton();
        bClear = new javax.swing.JButton();
        txtSearchKaryawan = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jabatan = new javax.swing.JComboBox();
        kode_karyawan = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        datetime = new javax.swing.JMenu();
        nama_user = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Data Karyawan");
        setBackground(new java.awt.Color(255, 255, 255));

        tabel_karyawan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_karyawanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_karyawan);

        bUbah.setText("UBAH");
        bUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Kode Karyawan");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Nama Karyawan");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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

        txtSearchKaryawan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKaryawanKeyReleased(evt);
            }
        });

        jLabel8.setText("Cari:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Jabatan");

        jabatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Jabatan", "Dokter Umum", "Dokter Gigi", "KIA", "Perawat", "Analis", "Apoteker", "Tenaga Gizi", "Administrasi" }));
        jabatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jabatanMouseClicked(evt);
            }
        });
        jabatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jabatanActionPerformed(evt);
            }
        });

        kode_karyawan.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        kode_karyawan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kode_karyawanKeyTyped(evt);
            }
        });

        jMenu1.setText("Koneksi");

        jMenuItem1.setText("Log Out");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        datetime.setText("Date");
        jMenuBar1.add(datetime);

        nama_user.setText("user");
        jMenuBar1.add(nama_user);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(631, 631, 631)
                        .addComponent(jLabel8)
                        .addGap(6, 6, 6)
                        .addComponent(txtSearchKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(kode_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)
                            .addComponent(nama_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbLaki)
                                .addGap(18, 18, 18)
                                .addComponent(rbPerempuan))
                            .addComponent(jLabel6)
                            .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(bSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(46, 46, 46)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addComponent(bUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(bHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(bClear, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel8))
                    .addComponent(txtSearchKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7)
                        .addGap(6, 6, 6)
                        .addComponent(jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3)
                        .addGap(8, 8, 8)
                        .addComponent(kode_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4)
                        .addGap(6, 6, 6)
                        .addComponent(nama_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbLaki)
                            .addComponent(rbPerempuan))
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6)
                        .addGap(6, 6, 6)
                        .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(bSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bHapus)
                    .addComponent(bUbah)
                    .addComponent(bClear))
                .addGap(64, 64, 64))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
            new Login().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void bUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahActionPerformed
        // TODO add your handling code here:
    int p = JOptionPane.showConfirmDialog(null, "Anda yakin ingin mengubah data?","Update Data",JOptionPane.YES_NO_OPTION);
    if(p==0){
        try{        
            String value1 = kode_karyawan.getText();
            String value2 = nama_karyawan.getText();
            String value4 = jk;
            String value5 = String.valueOf(password.getPassword());
            String value6 = ID;
            con.createStatement().execute("update karyawan set kode_karyawan ='"+value1+"', nama_karyawan='"+value2+"', jk_karyawan ='"+value4+"',"
                    + " password='"+value5+"', tanggal_entri = DATE_FORMAT(NOW(),'%Y-%m-%d %T'), nama_pengentri = '"+value6+"' where kode_karyawan='"+value1+"' ");
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
            con.createStatement().execute("insert into karyawan values('"+kode_karyawan.getText()
                    +"','"+nama_karyawan.getText()+"','"+jk+"','"+jabatan.getSelectedItem().toString()+"','"+String.valueOf(password.getPassword())+"',DATE_FORMAT(NOW(),'%Y-%m-%d %T'),'"+ID+"')");
            JOptionPane.showMessageDialog(rootPane,"Data Tersimpan!");
        }catch (SQLException | HeadlessException e){
            System.out.println("Data sudah pernah diinput");
            JOptionPane.showMessageDialog(null,"Data yang diinput belum lengkap! / Data sudah pernah diinput! / tidak boleh input data yang sama! / nama harus menggunakan huruf A-Z, a-z ");
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
        String sql = "delete from karyawan where kode_karyawan=?";
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, kode_karyawan.getText());
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
        kode_karyawan.setText("");
        nama_karyawan.setText("");
        password.setText("");
        buttonGroup1.clearSelection();
        jabatan.setSelectedItem("Pilih Jabatan");
    }//GEN-LAST:event_bClearActionPerformed

    private void txtSearchKaryawanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKaryawanKeyReleased
        // TODO add your handling code here:
        try{
            String querynya = txtSearchKaryawan.getText();
            if(querynya.equals("000000")){
                //txtSearchKaryawan.setText("");
                //querynya.equals("");
            }
            String sql = "select kode_karyawan as 'Kode Karyawan', nama_karyawan as 'Nama Karyawan', jk_karyawan as 'Jenis Kelamin', jabatan as Jabatan, password from karyawan where kode_karyawan != '000000' and kode_karyawan LIKE ? or nama_karyawan LIKE ? or jabatan LIKE ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%"+querynya+"%");
            pst.setString(2, "%"+querynya+"%");
            pst.setString(3, "%"+querynya+"%");
            rs=pst.executeQuery();
            tabel_karyawan.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);            
        }
        adjustColumn(tabel_karyawan);
    }//GEN-LAST:event_txtSearchKaryawanKeyReleased

    private void tabel_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_karyawanMouseClicked
        // TODO add your handling code here:
        try{
            int row = tabel_karyawan.getSelectedRow();
            String klik=(tabel_karyawan.getModel().getValueAt(row,0).toString());
            String sql = "select kode_karyawan as 'Kode Karyawan', nama_karyawan as 'Nama Karyawan', jk_karyawan as 'Jenis Kelamin', jabatan as Jabatan, password as 'Password' from karyawan where kode_karyawan ='"+klik+"' and kode_karyawan != '000000' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                String add1 = rs.getString("Kode Karyawan");
                kode_karyawan.setText(add1);
                String add2 = rs.getString("Nama Karyawan");
                nama_karyawan.setText(add2);
                String add3 = rs.getString("Jenis Kelamin");
                switch(add3){
                    case"L":
                        rbLaki.setSelected(true);
                        break;
                    case"P":
                        rbPerempuan.setSelected(true);
                        break;
                }
                String add5 = rs.getString("Password");
                password.setText(add5);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tabel_karyawanMouseClicked

    private void jabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jabatanActionPerformed
        // TODO add your handling code here:
        try{
            String nama_jabat = jabatan.getSelectedItem().toString();
            String sql = "select kode_karyawan from karyawan where jabatan = '"+nama_jabat+"' order by kode_karyawan desc limit 1";   
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String kode_lama = rs.getString("kode_karyawan").substring(2);
                String kode_lama_int = ""+ (Integer.parseInt(kode_lama)+1);
                String nol= "";
                if(nama_jabat.equals("Dokter Umum")){
                    if(kode_lama_int.length()==1){
                        nol="000";
                    } else if(kode_lama_int.length()==2){
                        nol="00";
                    } else if(kode_lama_int.length()==3){
                        nol="0";
                    } else if(kode_lama_int.length()==1){
                        nol="";
                    }
                    kode_karyawan.setText("DU"+nol+kode_lama_int);
                }else if(nama_jabat.equals("Dokter Gigi")){
                    if(kode_lama_int.length()==1){
                        nol="000";
                    } else if(kode_lama_int.length()==2){
                        nol="00";
                    } else if(kode_lama_int.length()==3){
                        nol="0";
                    } else if(kode_lama_int.length()==1){
                        nol="";
                    }
                    kode_karyawan.setText("DG"+nol+kode_lama_int);
                }else if(nama_jabat.equals("KIA")){
                    if(kode_lama_int.length()==1){
                        nol="000";
                    } else if(kode_lama_int.length()==2){
                        nol="00";
                    } else if(kode_lama_int.length()==3){
                        nol="0";
                    } else if(kode_lama_int.length()==1){
                        nol="";
                    }
                    kode_karyawan.setText("KI"+nol+kode_lama_int);
                }else if(nama_jabat.equals("Perawat")){
                    if(kode_lama_int.length()==1){
                        nol="000";
                    } else if(kode_lama_int.length()==2){
                        nol="00";
                    } else if(kode_lama_int.length()==3){
                        nol="0";
                    } else if(kode_lama_int.length()==1){
                        nol="";
                    }
                    kode_karyawan.setText("PW"+nol+kode_lama_int);
                }else if(nama_jabat.equals("Analis")){
                    if(kode_lama_int.length()==1){
                        nol="000";
                    } else if(kode_lama_int.length()==2){
                        nol="00";
                    } else if(kode_lama_int.length()==3){
                        nol="0";
                    } else if(kode_lama_int.length()==1){
                        nol="";
                    }
                    kode_karyawan.setText("AN"+nol+kode_lama_int);
                }else if(nama_jabat.equals("Apoteker")){
                    if(kode_lama_int.length()==1){
                        nol="000";
                    } else if(kode_lama_int.length()==2){
                        nol="00";
                    } else if(kode_lama_int.length()==3){
                        nol="0";
                    } else if(kode_lama_int.length()==1){
                        nol="";
                    }
                    kode_karyawan.setText("AP"+nol+kode_lama_int);
                }else if(nama_jabat.equals("Tenaga Gizi")){
                    if(kode_lama_int.length()==1){
                        nol="000";
                    } else if(kode_lama_int.length()==2){
                        nol="00";
                    } else if(kode_lama_int.length()==3){
                        nol="0";
                    } else if(kode_lama_int.length()==1){
                        nol="";
                    }
                    kode_karyawan.setText("TG"+nol+kode_lama_int);
                }else if(nama_jabat.equals("Administrasi")){
                    if(kode_lama_int.length()==1){
                        nol="000";
                    } else if(kode_lama_int.length()==2){
                        nol="00";
                    } else if(kode_lama_int.length()==3){
                        nol="0";
                    } else if(kode_lama_int.length()==1){
                        nol="";
                    }
                    kode_karyawan.setText("AD"+nol+kode_lama_int);
                }
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this,e);
        }
    }//GEN-LAST:event_jabatanActionPerformed

    private void jabatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jabatanMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jabatanMouseClicked

    private void kode_karyawanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_karyawanKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_kode_karyawanKeyTyped

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
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Karyawan().setVisible(true);
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jabatan;
    private javax.swing.JTextField kode_karyawan;
    private javax.swing.JTextField nama_karyawan;
    private javax.swing.JMenu nama_user;
    private javax.swing.JPasswordField password;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JTable tabel_karyawan;
    private javax.swing.JTextField txtSearchKaryawan;
    // End of variables declaration//GEN-END:variables
private String jk;

    private void setPreferredWidth(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
