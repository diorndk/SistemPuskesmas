/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KalbisPuskes.gui;

import static java.awt.Color.white;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.NumberFormatter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author IT
 */
public class Kasir extends javax.swing.JFrame {
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String ID = Login.getUserLogin();
    /**
     * Creates new form Kasir
     */
    public Kasir() {
        initComponents();
        con=Connect.ConnectDb();
        setupantri();
        this.setLocationRelativeTo(null);
        nama_user.setText("Login as: "+ID);
        TanggalSekarang();
        setuptable();
        jMenuBar1.add(Box.createHorizontalGlue());
        jMenuBar1.add(datetime);
        jMenuBar1.add(nama_user);
        adjustColumn(tabel_rekam);
        adjustColumnResep(tabel_resep);
        setupresep();
        auto_code();
        adjustColumnAntri(tabel_antri);
        antrian.setVisible(false);
        this.getContentPane().setBackground(white);
        ImageIcon ico = new ImageIcon("src/KalbisPuskes/gui/logo.png");
        this.setIconImage(ico.getImage());
        autorefresh();
        autorefreshkode();
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
        try{
            String sql = "select kode_tindakan as 'Kode Tindakan', nama_tindakan as 'Nama Tindakan', harga as Harga, tanggal_entri as 'Tanggal Entri', nama_pengentri as 'Nama Dokter' from detail_tindakan where nomor_rekam_medis ='"+no_rekam_medis.getText()+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tabel_rekam.setModel(DbUtils.resultSetToTableModel(rs));
            //To remove previously added rows
        }
        catch(  SQLException e)
        {
           JOptionPane.showMessageDialog(null, e.toString());
        }
        adjustColumn(tabel_rekam);
    }
    
    public void setupantri(){
        try{
            Calendar cal = new GregorianCalendar();
            int tanggal = cal.get(Calendar.DAY_OF_MONTH);
            String sql = "select no_antrian as 'Nomor Antri', nomor_rekam_medis as 'No Rekam medis', no_registrasi as 'No. Registrasi', nama_pasien as 'Nama Pasien', tanggal_entri as Tanggal, nama_pengentri as Dokter from antrian_bayar where flag_active ='Y' and tanggal='"+tanggal+"'";
            //String sql = "select no_antrian as 'Nomor Antri', nomor_rekam_medis as 'No Rekam medis', no_registrasi as 'No. Registrasi', nama_pasien as 'Nama Pasien', tanggal_entri as Tanggal, nama_pengentri as Dokter from antrian_bayar where flag_active ='Y' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tabel_antri.setModel(DbUtils.resultSetToTableModel(rs));
            //To remove previously added rows
        }
        catch(  SQLException e){
           JOptionPane.showMessageDialog(null, e.toString());
        }
        adjustColumn(tabel_antri);
    }
    
    public void autorefresh(){
        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               setupantri();
            }
        });
        timer.setDelay(5000); // delay for 30 seconds
        timer.start();
    }
    
    public void autorefreshkode(){
        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               auto_code();
            }
        });
        timer.setDelay(10000); // delay for 30 seconds
        timer.start();
    }
    
    public void adjustColumn(JTable tabel_rekam){
     TableColumnModel modelKolom = tabel_rekam.getColumnModel();
     for(int kol=0; kol < modelKolom.getColumnCount(); kol++){
    		int lebarKolomMax=0;
    		for(int baris=0;baris<tabel_rekam.getRowCount();baris++){
                    TableCellRenderer rend=tabel_rekam.getCellRenderer(baris,kol);
                    Object nilaiTablel=tabel_rekam.getValueAt(baris,kol);
                    Component comp=rend.getTableCellRendererComponent(tabel_rekam,nilaiTablel,false,false,baris,kol);
                    lebarKolomMax=Math.max(comp.getPreferredSize().width,lebarKolomMax);
    		}//akhir for baris
    		TableColumn kolom=modelKolom.getColumn(kol);
    		kolom.setPreferredWidth(lebarKolomMax);
    	}//akhir for kolom
    }
    
    public void adjustColumnResep(JTable tabel_resep){
     TableColumnModel modelKolom = tabel_resep.getColumnModel();
     for(int kol=0; kol < modelKolom.getColumnCount(); kol++){
    		int lebarKolomMax=0;
    		for(int baris=0;baris<tabel_resep.getRowCount();baris++){
                    TableCellRenderer rend=tabel_resep.getCellRenderer(baris,kol);
                    Object nilaiTablel=tabel_resep.getValueAt(baris,kol);
                    Component comp=rend.getTableCellRendererComponent(tabel_resep,nilaiTablel,false,false,baris,kol);
                    lebarKolomMax=Math.max(comp.getPreferredSize().width,lebarKolomMax);
    		}//akhir for baris
    		TableColumn kolom=modelKolom.getColumn(kol);
    		kolom.setPreferredWidth(lebarKolomMax);
    	}//akhir for kolom
    }
    
    public void adjustColumnAntri(JTable tabel_antri){
     TableColumnModel modelKolom = tabel_antri.getColumnModel();
     for(int kol=0; kol < modelKolom.getColumnCount(); kol++){
    		int lebarKolomMax=0;
    		for(int baris=0;baris<tabel_antri.getRowCount();baris++){
    			TableCellRenderer rend=tabel_antri.getCellRenderer(baris,kol);
    			Object nilaiTablel=tabel_antri.getValueAt(baris,kol);
    			Component comp=rend.getTableCellRendererComponent(tabel_antri,nilaiTablel,false,false,baris,kol);
				lebarKolomMax=Math.max(comp.getPreferredSize().width,lebarKolomMax);
    		}//akhir for baris
    		TableColumn kolom=modelKolom.getColumn(kol);
    		kolom.setPreferredWidth(lebarKolomMax);
    	}//akhir for kolom
    }
    
    public void setupresep(){
        try{
            String sql = "select kode_obat as 'Kode Obat', nama_obat as 'Nama Obat', jumlah as Jml, harga_satuan as Harga, tanggal_entri as 'Tanggal Entri', nama_pengentri as 'Nama Dokter' from detail_resep where kode_resep ='"+kode_resep.getText()+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tabel_resep.setModel(DbUtils.resultSetToTableModel(rs));
            //To remove previously added rows
        }
        catch(  SQLException e)
        {
           JOptionPane.showMessageDialog(null, e.toString());
        }
        adjustColumnResep(tabel_resep);
    }
    
    public void auto_code(){
        try{
            Calendar cal = new GregorianCalendar();
            int tanggal = cal.get(Calendar.DAY_OF_MONTH);
            String tgl;
            if(tanggal<10){
                tgl = "0"+tanggal;
            }else{
                tgl = String.valueOf(tanggal);
            }
            int bulannya = cal.get(Calendar.MONTH);
            String bulans;
            if(bulannya<10){
                bulans = "0"+(bulannya+1);
            }else{
                bulans = String.valueOf((bulannya+1));
            }
            int tahun = cal.get(Calendar.YEAR);
            String sql = "SELECT id_transaksi FROM transaksi where DATE_FORMAT(tanggal_transaksi,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') order by id_transaksi desc LIMIT 1 ";   
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {                
                String kode_lama = rs.getString("id_transaksi").substring(14);
                String kode_lama_int = ""+ (Integer.parseInt(kode_lama)+1);
                String nol= "";
                if(kode_lama_int.length()==1){
                    nol="00";
                } else if(kode_lama_int.length()==2){
                    nol="0";
                } else if(kode_lama_int.length()==3){
                    nol="";
                }
                kode_transaksi.setText("TR-"+tgl+"/"+bulans+"/"+tahun+"-"+nol+kode_lama_int);
            }else{
                kode_transaksi.setText("TR-"+tgl+"/"+bulans+"/"+tahun+"-001");
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this,e);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        no_rekam_medis = new javax.swing.JTextField();
        no_regis = new javax.swing.JTextField();
        kode_resep = new javax.swing.JTextField();
        nama_pasien = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_rekam = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_resep = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        total_tindakan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        total_resep = new javax.swing.JTextField();
        total_kembalian = new javax.swing.JTextField();
        biaya_total = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        total_bayar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_antri = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        kode_transaksi = new javax.swing.JTextField();
        antrian = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jenis_pembayaran = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        datetime = new javax.swing.JMenu();
        nama_user = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pembayaran");

        jLabel1.setText("No. Rekam Medis");

        jLabel2.setText("No. Registrasi");

        jLabel3.setText("Kode Resep");

        jLabel4.setText("Nama Pasien");

        no_rekam_medis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                no_rekam_medisKeyTyped(evt);
            }
        });

        tabel_rekam.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabel_rekam);

        tabel_resep.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabel_resep);

        jLabel5.setText("Total Harga Tindakan");

        jLabel6.setText("Total Harga Resep");

        total_resep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_resepActionPerformed(evt);
            }
        });

        total_kembalian.setFont(new java.awt.Font("Dialog", 1, 26)); // NOI18N

        biaya_total.setFont(new java.awt.Font("Dialog", 1, 26)); // NOI18N

        jLabel7.setText("Total Biaya");

        jLabel8.setText("Kembalian");

        jLabel9.setText("Total Bayar");

        total_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_bayarActionPerformed(evt);
            }
        });
        total_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                total_bayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                total_bayarKeyTyped(evt);
            }
        });

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tabel_antri.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_antri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_antriMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabel_antri);

        jLabel10.setText("Kode Transaksi");

        kode_transaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kode_transaksiKeyTyped(evt);
            }
        });

        antrian.setText("0");

        jLabel11.setText("Jenis Pembayaran");

        jenis_pembayaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tunai", "Debit", "BPJS" }));

        jMenu1.setText("Koneksi");

        jMenuItem2.setText("Log Out");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Navigasi");

        jMenuItem1.setText("Obat");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

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
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(nama_pasien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(37, 37, 37)
                                    .addComponent(no_regis, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(no_rekam_medis, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(kode_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(kode_resep, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(jenis_pembayaran, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(total_tindakan, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(jLabel5)
                                            .addComponent(total_bayar))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(biaya_total, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(89, 89, 89)
                                        .addComponent(jLabel7)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(total_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(46, 46, 46))
                                    .addComponent(total_resep, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(57, 57, 57)
                                .addComponent(antrian)
                                .addGap(75, 75, 75)
                                .addComponent(jButton1)))
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2)
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(total_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(biaya_total, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(jButton1))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(antrian))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(kode_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(no_rekam_medis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(total_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(no_regis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(nama_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(total_tindakan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(kode_resep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(total_resep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jenis_pembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void no_rekam_medisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_no_rekam_medisKeyTyped
        // TODO add your handling code here:
        try{
            String sql = "select no_registrasi, nama_pasien, kode_resep, total_harga_tindakan from rekam_medis where nomor_rekam_medis ='"+no_rekam_medis.getText()+"' ";
            pst = con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                no_regis.setText(rs.getString("no_registrasi"));
                nama_pasien.setText(rs.getString("nama_pasien"));
                kode_resep.setText(rs.getString("kode_resep"));
                total_tindakan.setText(rs.getString("total_harga_tindakan"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);            //2004170001
        }
        setuptable();
        try{            
            String sql = "select * from header_resep where kode_resep ='"+kode_resep.getText()+"' ";
            pst = con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                total_resep.setText(rs.getString("total_harga_resep"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);            
        }
        setupresep();
        Integer harga_tindakan = Integer.valueOf(total_tindakan.getText());
        Integer harga_resep = Integer.valueOf(total_resep.getText());
        Integer total_bill = harga_tindakan + harga_resep;
        String bill =String.valueOf(total_bill);
        biaya_total.setText(bill);
    }//GEN-LAST:event_no_rekam_medisKeyTyped

    private void total_resepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_resepActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_total_resepActionPerformed

    private void total_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_bayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total_bayarActionPerformed

    private void total_bayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_total_bayarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_total_bayarKeyTyped

    private void total_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_total_bayarKeyReleased
        // TODO add your handling code here:
        int total_biaya = Integer.valueOf(biaya_total.getText());
        int bayar = Integer.valueOf(total_bayar.getText());
        int kembalian = bayar-total_biaya;
        String balikin = String.valueOf(kembalian);
        total_kembalian.setText(balikin);
    }//GEN-LAST:event_total_bayarKeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Obat().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void tabel_antriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_antriMouseClicked
        // TODO add your handling code here:
        try{
            Calendar cal = new GregorianCalendar();
            int tanggal = cal.get(Calendar.DAY_OF_MONTH);
            int row = tabel_antri.getSelectedRow();
            //String klik=(tabel_antri.getModel().getValueAt(row,1).toString());
            String klak=(tabel_antri.getModel().getValueAt(row,0).toString());
            String sql = "select no_antrian as 'Nomor Antri', nomor_rekam_medis as 'No. Rekam Medis', no_registrasi as 'No. Registrasi', nama_pasien as 'Nama Pasien', tanggal_entri as Tanggal, nama_pengentri as Dokter from antrian_bayar where no_antrian = '"+klak+"' and flag_active='Y' and tanggal='"+tanggal+"' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                antrian.setText(rs.getString("Nomor Antri"));
                no_rekam_medis.setText(rs.getString("No. Rekam Medis"));
                no_regis.setText(rs.getString("No. Registrasi"));
                nama_pasien.setText(rs.getString("Nama Pasien"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        try{
            String sql = "select no_registrasi, nama_pasien, kode_resep, total_harga_tindakan from rekam_medis where nomor_rekam_medis ='"+no_rekam_medis.getText()+"' ";
            pst = con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                no_regis.setText(rs.getString("no_registrasi"));
                nama_pasien.setText(rs.getString("nama_pasien"));
                kode_resep.setText(rs.getString("kode_resep"));
                total_tindakan.setText(rs.getString("total_harga_tindakan"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);            //2004170001
        }
        setuptable();
        try{            
            String sql = "select * from header_resep where kode_resep ='"+kode_resep.getText()+"' ";
            pst = con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                total_resep.setText(rs.getString("total_harga_resep"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);            
        }
        setupresep();
        Integer harga_tindakan = Integer.valueOf(total_tindakan.getText());
        Integer harga_resep = Integer.valueOf(total_resep.getText());
        Integer total_bill = harga_tindakan + harga_resep;
        String bill =String.valueOf(total_bill);
        biaya_total.setText(bill);
    }//GEN-LAST:event_tabel_antriMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
            String sql ="insert into transaksi (id_transaksi,nomor_rekam_medis,no_registrasi,nama_pasien,kode_resep,total_biaya,total_bayar,jenis_pembayaran,tanggal_transaksi,nama_kasir) values (?,?,?,?,?,?,?,?,DATE_FORMAT(NOW(),'%Y-%m-%d %T'),?)";
            pst=con.prepareStatement(sql);
            pst.setString(1, kode_transaksi.getText());
            pst.setString(2, no_rekam_medis.getText());
            pst.setString(3, no_regis.getText());
            pst.setString(4, nama_pasien.getText());
            pst.setString(5, kode_resep.getText());
            pst.setString(6, biaya_total.getText());
            pst.setString(7, total_bayar.getText());
            pst.setString(8, jenis_pembayaran.getSelectedItem().toString());
            pst.setString(9, ID);
            pst.execute();
            con.createStatement().execute("update antrian_bayar set flag_active ='N' where no_antrian = '"+antrian.getText()+"' ");
            JOptionPane.showMessageDialog(rootPane, "Data Tersimpan!");
            no_rekam_medis.setText("");
            no_regis.setText("");
            nama_pasien.setText("");
            kode_resep.setText("");
            total_bayar.setText("");
            total_tindakan.setText("");
            total_resep.setText("");
            biaya_total.setText("");
            total_kembalian.setText("");
        }catch (SQLException | HeadlessException e){
           System.out.println("Data sudah pernah diinput");
            JOptionPane.showMessageDialog(null,"Data yang diinput belum lengkap! / Data sudah pernah diinput! / tidak boleh input data yang sama!");
        }
        setuptable();
        setupresep();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void kode_transaksiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_transaksiKeyTyped
        // TODO add your handling code here:
        if(kode_transaksi.getText().length()>=12){  
           evt.consume();
        }
    }//GEN-LAST:event_kode_transaksiKeyTyped

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel antrian;
    private javax.swing.JTextField biaya_total;
    private javax.swing.JMenu datetime;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox jenis_pembayaran;
    private javax.swing.JTextField kode_resep;
    private javax.swing.JTextField kode_transaksi;
    private javax.swing.JTextField nama_pasien;
    private javax.swing.JMenu nama_user;
    private javax.swing.JTextField no_regis;
    private javax.swing.JTextField no_rekam_medis;
    private javax.swing.JTable tabel_antri;
    private javax.swing.JTable tabel_rekam;
    private javax.swing.JTable tabel_resep;
    private javax.swing.JTextField total_bayar;
    private javax.swing.JTextField total_kembalian;
    private javax.swing.JTextField total_resep;
    private javax.swing.JTextField total_tindakan;
    // End of variables declaration//GEN-END:variables
}
