/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KalbisPuskes.gui;

import static java.awt.Color.gray;
import static java.awt.Color.white;
import java.awt.Component;
import java.io.File;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DIOR
 */
public class Kepala_Klinik extends javax.swing.JFrame {
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String ID = Login.getUserLogin();
    public String[] require = new String[6];
    public String required = "";
    /**
     * Creates new form Kepala_Klinik
     */
    public Kepala_Klinik() {
        initComponents();
        con=Connect.ConnectDb();
        getYear();
        this.setLocationRelativeTo(null);
        jPanel2.setBackground(white);
        ImageIcon ico = new ImageIcon("src/KalbisPuskes/gui/logo.png");
        this.setIconImage(ico.getImage());
        setuptable();
        adjustColumn(tabels);
        setDate();
        //getBulan();
        this.setSize(1114,553);
        jPanel2.setSize(910,488);
        TanggalSekarang();
        nama_user.setText("Login as: "+ID);
        jMenuBar1.add(Box.createHorizontalGlue());
        jMenuBar1.add(datetime);
        jMenuBar1.add(nama_user);
        GetDailyTotal();
        jCheckBox1.setSelected(true);
        jCheckBox2.setSelected(true);
        jCheckBox3.setSelected(true);
        jCheckBox1.setVisible(false);
        jCheckBox2.setVisible(false);
        jCheckBox3.setVisible(false);
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        cbTgl.setVisible(true);
        cbBulan.setVisible(false);
        cbTahun.setVisible(false);
        btn_cari.setVisible(true);
        btn_reset.setVisible(true);
        jLabel1.setVisible(false);
        cbTgl.setVisible(false);
        jCheckBox2.setVisible(false);
        btn_reset.setVisible(false);
        btn_cari.setVisible(false);
        
    }
    
    public void GetDailyTotal(){
        try{
            String sql = "select count(*) as 'pasien', sum(total_biaya) as 'total' from transaksi";
            pst = con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                total_income.setText(rs.getString("total"));
                total_pasien.setText(rs.getString("pasien"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void TanggalSekarang(){     
        Thread clock = new Thread(){
            public void run(){
                for(;;){
                    Calendar cal = new GregorianCalendar();
                    int tanggal = cal.get(Calendar.DAY_OF_MONTH);
                    int bulan1 = cal.get(Calendar.MONTH);
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
    
    public void getYear(){
        /*ArrayList<String> years_tmp = new ArrayList<String>();
        for(int years = 1980 ; years<=Calendar.getInstance().get(Calendar.YEAR);years++){
      years_tmp.add(years+"");
       }
        jComboBox2 = new JComboBox(years_tmp.toArray());*/
        Calendar cal = new GregorianCalendar();
        int tahun = cal.get(Calendar.YEAR);
        for(int i = 2017;i<=tahun;i++){
            cbTahun.addItem(i);
        }
    }
    
    /*public void getBulan(){
        Calendar cal = new GregorianCalendar();
        int tahun = cal.get(Calendar.YEAR);
        for(int i = 2014;i<=tahun;i++){
            cbTahun.addItem(i);
        }
    }*/
    
    public void setDate(){
        Calendar cal = new GregorianCalendar();
        int tanggalnya = cal.get(Calendar.DATE);
        for(int i = 1;i<=31;i++){
            cbTgl.addItem(i);
        }
    }
    
    public void adjustColumn(JTable tbl_transaksi){
     TableColumnModel modelKolom = tbl_transaksi.getColumnModel();
     for(int kol=0; kol < modelKolom.getColumnCount(); kol++){
    		int lebarKolomMax=0;
    		for(int baris=0;baris<tbl_transaksi.getRowCount();baris++){
    			TableCellRenderer rend=tbl_transaksi.getCellRenderer(baris,kol);
    			Object nilaiTablel=tbl_transaksi.getValueAt(baris,kol);
    			Component comp=rend.getTableCellRendererComponent(tbl_transaksi,nilaiTablel,false,false,baris,kol);
				lebarKolomMax=Math.max(comp.getPreferredSize().width,lebarKolomMax);
    		}//akhir for baris
    		TableColumn kolom=modelKolom.getColumn(kol);
    		kolom.setPreferredWidth(lebarKolomMax);
    	}//akhir for kolom
    }
    
    public void setuptable(){
        try
        {
            String sql = "select id_transaksi as 'Kode Transaksi', nomor_rekam_medis as 'No. Rekam Medis', no_registrasi as 'No. Registrasi', nama_pasien as 'Nama Pasien', kode_resep as 'Resep', total_biaya as 'Tagihan', total_bayar as 'Bayar', jenis_pembayaran as 'Jenis Bayar', DATE_FORMAT(tanggal_transaksi, '%d %M %Y') as 'Tanggal' from transaksi";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tabels.setModel(DbUtils.resultSetToTableModel(rs));
            //To remove previously added rows
        }
        catch(  SQLException e)
        {
           JOptionPane.showMessageDialog(null, e.toString());
        }
        adjustColumn(tabels);
    }
    
    public void tableobat(){
        try
        {
            String sql = "select kode_obat as 'Kode Obat', nama_obat as 'Nama Obat', jenis_obat as 'Jenis Obat', harga_satuan as 'Satuan', stok as 'Stok' from obat";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tabels.setModel(DbUtils.resultSetToTableModel(rs));
            //To remove previously added rows
        }
        catch(  SQLException e)
        {
           JOptionPane.showMessageDialog(null, e.toString());
        }
        adjustColumn(tabels);
    }
    
    public void searchTransaksi(){
        
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        cbBulan = new javax.swing.JComboBox();
        cbTahun = new javax.swing.JComboBox();
        cbTgl = new javax.swing.JComboBox();
        btn_cari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabels = new javax.swing.JTable();
        btn_reset = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        total_income = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        total_pasien = new javax.swing.JLabel();
        btn_export = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        datetime = new javax.swing.JMenu();
        nama_user = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Laporan Klinik Umi Rahma");

        jButton1.setText("Laporan Transaksi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Laporan Obat");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Tahun");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("Tanggal");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.setText("Bulan");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(19, 19, 19)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addContainerGap(335, Short.MAX_VALUE))
        );

        jPanel2.setLayout(null);

        cbBulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));
        cbBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBulanActionPerformed(evt);
            }
        });
        jPanel2.add(cbBulan);
        cbBulan.setBounds(193, 28, 128, 26);

        cbTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTahunActionPerformed(evt);
            }
        });
        cbTahun.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbTahunPropertyChange(evt);
            }
        });
        jPanel2.add(cbTahun);
        cbTahun.setBounds(355, 28, 135, 26);

        cbTgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTglActionPerformed(evt);
            }
        });
        cbTgl.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbTglPropertyChange(evt);
            }
        });
        jPanel2.add(cbTgl);
        cbTgl.setBounds(22, 28, 135, 26);

        btn_cari.setText("CARI");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        jPanel2.add(btn_cari);
        btn_cari.setBounds(536, 28, 57, 26);

        tabels.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabels);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(22, 72, 890, 382);

        btn_reset.setText("RESET");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });
        jPanel2.add(btn_reset);
        btn_reset.setBounds(605, 28, 80, 26);

        jLabel4.setText("Total Pendapatan:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(22, 466, 102, 16);

        total_income.setText("0");
        jPanel2.add(total_income);
        total_income.setBounds(142, 466, 140, 16);

        jLabel5.setText("Total Transaksi:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(287, 466, 100, 16);

        total_pasien.setText("0");
        jPanel2.add(total_pasien);
        total_pasien.setBounds(390, 470, 110, 10);

        btn_export.setText("EXPORT");
        btn_export.setMinimumSize(new java.awt.Dimension(55, 32));
        btn_export.setPreferredSize(new java.awt.Dimension(67, 32));
        btn_export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exportActionPerformed(evt);
            }
        });
        jPanel2.add(btn_export);
        btn_export.setBounds(780, 30, 130, 30);

        jLabel1.setText("Tanggal");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(30, 10, 50, 16);

        jLabel2.setText("Bulan");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(190, 10, 32, 16);

        jLabel3.setText("Tahun");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(360, 10, 35, 16);

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

        nama_user.setText("User");
        jMenuBar1.add(nama_user);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 947, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void cbTahunPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbTahunPropertyChange
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbTahunPropertyChange

    private void cbBulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBulanActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbBulanActionPerformed

    private void cbTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTahunActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbTahunActionPerformed

    private void cbTglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTglActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTglActionPerformed

    private void cbTglPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbTglPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTglPropertyChange

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        /*try{
            String Hariii = cbTgl.getSelectedItem().toString();
            int bulannya = cbBulan.getSelectedIndex();
            int bulans = bulannya+1;
            String bulanz = "";
            bulanz = String.valueOf(bulans);
            String Tahuun = cbTahun.getSelectedItem().toString();
            if(jCheckBox2.isSelected()){
                Hariii = "";
            }else{
                Hariii = Hariii;
            }
            if(jCheckBox1.isSelected()){
                bulanz = "";
            }else{
                bulanz = bulanz;
            }
            if(jCheckBox3.isSelected()){
                Tahuun = "";
            }else{
                Tahuun = Tahuun;
            }
            required="";
            if(Hariii.equals("")){
                require[0] = "";
            }else{
                require[0] = "and Date_format(tanggal_transaksi,'%d') = ?";
            }            
            if(bulanz.equals("")){
                require[1] = "";
            }else{
                require[1] = "and Month(tanggal_transaksi) = ?";
            }            
            if(Tahuun.equals("")){
                require[2] = "";
            }else{
                require[2] = "and Year(tanggal_transaksi) = ?";
            }

            for(int i = 0;i <= 2;i++){
                if(require[i]!=""){
                        required = required;
                }
                if(require[i]!=""){
                        required = required + require[i];
                }
            }
            String sql = "select id_transaksi as 'Kode Transaksi', nomor_rekam_medis as 'No. Rekam Medis', no_registrasi as 'No. Registrasi', nama_pasien as 'Nama Pasien', kode_resep as 'Resep', total_biaya as 'Tagihan', total_bayar as 'Bayar', jenis_pembayaran as 'Jenis Bayar', tanggal_transaksi as 'Tanggal' from transaksi where 1=1 "+required+" ";
            pst = con.prepareStatement(sql);
            pst.setString(1, Hariii);
            pst.setString(2, Integer.toString(bulans));
            pst.setString(3, Tahuun);
            rs=pst.executeQuery();
            tabels.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        try{
            int bulannya = cbBulan.getSelectedIndex();
            int bulans = bulannya+1;
            String sql = "select count(*) as 'pasien', sum(total_biaya) as 'total' from transaksi where Date_format(tanggal_transaksi,'%d') = ? or Month(tanggal_transaksi) = ? or Year(tanggal_transaksi) = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, cbTgl.getSelectedItem().toString());
            pst.setString(2, Integer.toString(bulans));
            pst.setString(3, cbTahun.getSelectedItem().toString());
            rs=pst.executeQuery();
            if(rs.next()){
                total_income.setText(rs.getString("total"));
                total_pasien.setText(rs.getString("pasien"));
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);            
        }
        adjustColumn(tabels);*/
        /*try{
            int bulannya = cbBulan.getSelectedIndex();
            int bulans = bulannya+1;
            String sql = "select id_transaksi as 'Kode Transaksi', nomor_rekam_medis as 'No. Rekam Medis', no_registrasi as 'No. Registrasi', nama_pasien as 'Nama Pasien', kode_resep as 'Resep', total_biaya as 'Tagihan', total_bayar as 'Bayar', jenis_pembayaran as 'Jenis Bayar', tanggal_transaksi as 'Tanggal' from transaksi where Date_format(tanggal_transaksi,'%d') = ? or Month(tanggal_transaksi) = ? or Year(tanggal_transaksi) = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, cbTgl.getSelectedItem().toString());
            pst.setString(2, Integer.toString(bulans));
            pst.setString(3, cbTahun.getSelectedItem().toString());
            rs=pst.executeQuery();
            tabels.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);            
        }
        try{
            int bulannya = cbBulan.getSelectedIndex();
            int bulans = bulannya+1;
            String sql = "select count(*) as 'pasien', sum(total_biaya) as 'total' from transaksi where Date_format(tanggal_transaksi,'%d') = ? or Month(tanggal_transaksi) = ? or Year(tanggal_transaksi) = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, cbTgl.getSelectedItem().toString());
            pst.setString(2, Integer.toString(bulans));
            pst.setString(3, cbTahun.getSelectedItem().toString());
            rs=pst.executeQuery();
            if(rs.next()){
                total_income.setText(rs.getString("total"));
                total_pasien.setText(rs.getString("pasien"));
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);            
        }
        adjustColumn(tabels);*/
        try{
            if(jCheckBox3.isSelected() == false){
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btn_cariActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        setuptable();
        cbTgl.setSelectedIndex(0);
        cbBulan.setSelectedIndex(0);
        cbTahun.setSelectedIndex(0);
    }//GEN-LAST:event_btn_resetActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        tableobat();
        jCheckBox1.setVisible(false);
        jCheckBox2.setVisible(false);
        jCheckBox3.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        cbTgl.setVisible(false);
        cbBulan.setVisible(false);
        cbTahun.setVisible(false);
        total_income.setVisible(false);
        total_pasien.setVisible(false);
        btn_cari.setVisible(false);
        btn_reset.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setuptable();
        /*jCheckBox1.setVisible(true);
        jCheckBox2.setVisible(true);
        jCheckBox3.setVisible(true);
        jLabel4.setVisible(true);
        jLabel5.setVisible(true);
        cbTgl.setVisible(true);
        cbBulan.setVisible(true);
        cbTahun.setVisible(true);*/
        total_income.setVisible(true);
        total_pasien.setVisible(true);/*
        btn_cari.setVisible(true);
        btn_reset.setVisible(true);*/
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exportActionPerformed
        // TODO add your handling code here:
        Excel export = new Excel();
        JFileChooser fc = new JFileChooser();
        int option = fc.showSaveDialog(Kepala_Klinik.this);
        if(option == JFileChooser.APPROVE_OPTION){
            String filename = fc.getSelectedFile().getName(); 
            String path = fc.getSelectedFile().getParentFile().getPath();

            int len = filename.length();
            String ext = "";
            String file = "";

            if(len > 4){
                    ext = filename.substring(len-4, len);
            }

            if(ext.equals(".xls")){
                    file = path + "\\" + filename; 
            }else{
                    file = path + "\\" + filename + ".xls"; 
            }
            export.toExcel(tabels, new File(file));
        }
    }//GEN-LAST:event_btn_exportActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox2.isSelected()){
            cbTgl.setEnabled(true);
            setDate();
        }else{
            cbTgl.setEnabled(false);
            cbTgl.removeAllItems();
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox3.isSelected()){
            cbBulan.setEnabled(true);
        }else{
            cbBulan.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox1.isSelected()){
            cbTahun.setEnabled(true);
        }else{
            cbTahun.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(Kepala_Klinik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kepala_Klinik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kepala_Klinik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kepala_Klinik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kepala_Klinik().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_export;
    private javax.swing.JButton btn_reset;
    private javax.swing.JComboBox cbBulan;
    private javax.swing.JComboBox cbTahun;
    private javax.swing.JComboBox cbTgl;
    private javax.swing.JMenu datetime;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu nama_user;
    private javax.swing.JTable tabels;
    private javax.swing.JLabel total_income;
    private javax.swing.JLabel total_pasien;
    // End of variables declaration//GEN-END:variables
}
