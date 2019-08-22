/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KalbisPuskes.gui;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DIOR
 */
public class RekamMedis_Bidan extends javax.swing.JFrame {
     public Tindakan_Bidan tb;
    Kasir ksr = new Kasir();
    Connection con = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    PreparedStatement pst = null;
    String ID = Login.getUserLogin();
    public int jumlah_obat, jumlah_obat1, jumlah_obat2, jumlah_obat3, jumlah_obat4, jumlah_obat5;
    public int harga_obat, harga_obat1, harga_obat2, harga_obat3, harga_obat4, harga_obat5;
    public int total_harga_obat, total_harga_obat1, total_harga_obat2, total_harga_obat3, total_harga_obat4, total_harga_obat5;
    public static String no_rekam;
    
    public static void setNoRekam(String no_rekam) {
    RekamMedis_Bidan.no_rekam = no_rekam;
    }
    
    public static String getNoRekam() {
    return no_rekam;
    }
    /**
     * Creates new form RekamMedis_Bidan
     */
    public RekamMedis_Bidan() {
        initComponents();
        ksr.setupantri();
        this.setSize(1036,654);
        con=Connect.ConnectDb();
        getJenisObat();
        auto_number();
        autorefreshantrian();
        setuptable();
        setupantri();
        setupresep();
        autorefresh();
        this.setLocationRelativeTo(null);
        nama_user.setText("Login as: "+ID);
        TanggalSekarang();
        jMenuBar1.add(Box.createHorizontalGlue());
        jMenuBar1.add(datetime);
        jMenuBar1.add(nama_user);
        total_harga_resep.setVisible(false);
        total_harga_resep1.setVisible(false);
        total_harga_resep2.setVisible(false);
        total_harga_resep3.setVisible(false);
        total_harga_resep4.setVisible(false);
        total_harga_resep5.setVisible(false);
        harga_satuan.setVisible(false);
        harga_satuan1.setVisible(false);
        harga_satuan2.setVisible(false);
        harga_satuan3.setVisible(false);
        harga_satuan4.setVisible(false);
        harga_satuan5.setVisible(false);
        clear_resep.setVisible(false);
        jLabel7.setVisible(false);
        jLabel14.setVisible(false);
        jLabel16.setVisible(false);
        jLabel17.setVisible(false);
        jLabel18.setVisible(false);
        jLabel19.setVisible(false);
        jLabel20.setVisible(false);
        jLabel21.setVisible(false);
        jLabel22.setVisible(false);
        jLabel23.setVisible(false);
        jLabel24.setVisible(false);
        jLabel25.setVisible(false);
        jLabel26.setVisible(false);
        jLabel27.setVisible(false);
        jLabel28.setVisible(false);
        jLabel29.setVisible(false);
        jLabel30.setVisible(false);
        jLabel31.setVisible(false);
        jLabel32.setVisible(false);
        jLabel6.setVisible(false);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);
        jLabel33.setVisible(false);
        jLabel34.setVisible(false);
        jLabel35.setVisible(false);
        jLabel36.setVisible(false);
        jenis_obat.setVisible(false);
        jenis_obat1.setVisible(false);
        jenis_obat2.setVisible(false);
        jenis_obat3.setVisible(false);
        jenis_obat4.setVisible(false);
        jenis_obat5.setVisible(false);
        kode_obat.setVisible(false);
        kode_obat1.setVisible(false);
        kode_obat2.setVisible(false);
        kode_obat3.setVisible(false);
        kode_obat4.setVisible(false);
        kode_obat5.setVisible(false);
        nama_obat.setVisible(false);
        nama_obat1.setVisible(false);
        nama_obat2.setVisible(false);
        nama_obat3.setVisible(false);
        nama_obat4.setVisible(false);
        nama_obat5.setVisible(false);
        jumlah.setVisible(false);
        jumlah1.setVisible(false);
        jumlah2.setVisible(false);
        jumlah3.setVisible(false);
        jumlah4.setVisible(false);
        jumlah5.setVisible(false);
        add.setVisible(false);
        add1.setVisible(false);
        add2.setVisible(false);
        add3.setVisible(false);
        add4.setVisible(false);
        add5.setVisible(false);
        adjustColumn(tabel_rekam);
        adjustColumnAntri(tabel_antri);
        adjustColumnResep(tabel_resep);
        tabel_resep.setVisible(true);
        ganti_tab();
        total_harga_resep.setText("0");
        total_harga_resep1.setText("0");
        total_harga_resep2.setText("0");
        total_harga_resep3.setText("0");
        total_harga_resep4.setText("0");
        total_harga_resep5.setText("0");
        lblCariResep.setVisible(false);
        txtSearchResep.setVisible(false);
        jScrollPane5.setVisible(false);
        submit_aturan.setVisible(false);
        ImageIcon ico = new ImageIcon("src/KalbisPuskes/gui/logo.png");
        this.setIconImage(ico.getImage());
        jButton1.setVisible(false);
        generate_norekam();
        generate_noresep();
        nomor_rekam_medis_lama.setVisible(false);
        no_antri.setVisible(false);
        bHapus.setVisible(false);
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
                    tgl_resep.setText(""+sdf.format(d)+", "+tanggal+" / "+(bulan1+1)+" / "+tahun);
                    tgl_rekam_medis.setText(tanggal+"-"+(bulan1+1)+"-"+tahun);
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
    
    public void generate_norekam(){
        try{
            Calendar cal = new GregorianCalendar();
            java.util.Date d = new java.util.Date();
            SimpleDateFormat hr = new SimpleDateFormat("HH");
            SimpleDateFormat mnt = new SimpleDateFormat("mm");
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
            String sql = "SELECT nomor_rekam_medis FROM rekam_medis where DATE_FORMAT(tgl_rekam_medis,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') order by nomor_rekam_medis desc LIMIT 1 ";   
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {                
                String kode_lama = rs.getString("nomor_rekam_medis").substring(11);
                String kode_lama_int = ""+ (Integer.parseInt(kode_lama)+1);
                String nol= "";
                if(kode_lama_int.length()==1){
                    nol="000";
                } else if(kode_lama_int.length()==2){
                    nol="00";
                } else if(kode_lama_int.length()==3){
                    nol="0";
                } else if(kode_lama_int.length()==4){
                    nol="";
                }
                nomor_rekam_medis.setText("RM"+tgl+bulans+tahun+nol+kode_lama_int);
                no_rekam=nomor_rekam_medis.getText();
            }else{
                nomor_rekam_medis.setText("RM"+tgl+bulans+tahun+"0001");
                no_rekam=nomor_rekam_medis.getText();
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this,e);
        }
    }
    
    public void generate_noresep(){
        try{
            Calendar cal = new GregorianCalendar();
            java.util.Date d = new java.util.Date();
            SimpleDateFormat hr = new SimpleDateFormat("HH");
            SimpleDateFormat mnt = new SimpleDateFormat("mm");
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
            String stryear = String.valueOf(tahun);
            String year = stryear.substring(2);
            String sql = "SELECT kode_resep FROM header_resep where DATE_FORMAT(tgl_resep,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') order by kode_resep desc LIMIT 1 ";   
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {                
                String kode_lama = rs.getString("kode_resep").substring(9);
                String kode_lama_int = ""+ (Integer.parseInt(kode_lama)+1);
                String nol= "";
                if(kode_lama_int.length()==1){
                    nol="00";
                } else if(kode_lama_int.length()==2){
                    nol="0";
                } else if(kode_lama_int.length()==3){
                    nol="";
                }
                kode_resep.setText("RSP"+year+bulans+tgl+nol+kode_lama_int);
                kode_resep1.setText("RSP"+year+bulans+tgl+nol+kode_lama_int);
            }else{
                kode_resep.setText("RSP"+year+bulans+tgl+"001");
                kode_resep1.setText("RSP"+year+bulans+tgl+"001");
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this,e);
        }
    }
    
    public void ganti_tab(){
        Tabular.addChangeListener(new ChangeListener() { //add the Listener
            public void stateChanged(ChangeEvent e) {
                if(Tabular.getSelectedIndex()==0) //Index starts at 0, so Index 2 = Tab3
                {
                    jScrollPane1.setVisible(true);
                    jScrollPane4.setVisible(false);
                    lblCariResep.setVisible(false);
                    txtSearchResep.setVisible(false);
                    setuptable();
                    //do your stuff on Tab 2
                } else if(Tabular.getSelectedIndex()==1) //Index starts at 0, so Index 2 = Tab3
                {
                    jScrollPane1.setVisible(true);
                    jScrollPane4.setVisible(false);
                    lblCariResep.setVisible(false);
                    txtSearchResep.setVisible(false);
                    setuptable();
                    //do your stuff on Tab 2
                } else if(Tabular.getSelectedIndex()==2) //Index starts at 0, so Index 2 = Tab3
                {
                    jScrollPane1.setVisible(false);
                    jScrollPane4.setVisible(true);
                    lblCariResep.setVisible(true);
                    txtSearchResep.setVisible(true);
                    setupresep();
                    //do your stuff on Tab 3
                }
            }
        });
    }
    
    public void autorefreshantrian(){
        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               auto_number();
               //generate_norekam();
               //generate_noresep();
            }
        });
        timer.setDelay(1000); // delay for 30 seconds
        timer.start();
    }
    
    public void auto_number(){
        try{
            Calendar cal = new GregorianCalendar();
            int tanggal = cal.get(Calendar.DAY_OF_MONTH);
            String sql = "select no_antrian from antrian_bayar where tanggal = '"+tanggal+"' ORDER BY no_antrian DESC LIMIT 1";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                int antrian = rs.getInt("no_antrian")+1;
                String AN = String.valueOf(antrian);
                no_antri.setText(AN);//sesuaikan dengan variable namenya
            } else {
                no_antri.setText("1");//sesuaikan dengan variable namenya           
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this,e);
        }
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
    
    public void setuptable(){
        try
        {
            String sql = "select nomor_rekam_medis as 'No. Rekam Medis', DATE_FORMAT(tgl_rekam_medis, '%d %M %Y') as 'Tanggal Rekam Medis', no_registrasi as 'No. Registrasi', nama_pasien as 'Nama Depan', kode_resep as 'Kode Resep', keluhan as Keluhan, tanggal_entri as 'Tanggal Entri', nama_dokter as 'Nama Dokter' from rekam_medis where bagian ='Bidan' order by tgl_rekam_medis desc";
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
    
    public void setupresep(){
        try
        {
            String sql = "SELECT a.kode_resep AS 'Kode Resep', b.kode_obat as 'Kode Obat', b.nama_obat AS 'Nama Obat', b.jumlah as Jumlah, b.harga_satuan as 'Harga Obat', a.nama_pasien as 'Nama Pasien', DATE_FORMAT(a.tgl_resep, '%d, %M %Y') as 'Tanggal Resep', a.nama_dokter as 'Nama Dokter' FROM header_resep a, detail_resep b where a.kode_resep = b.kode_resep order by a.tgl_resep desc, a.kode_resep, b.tanggal_entri desc";
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
    
    public void setupantri(){
        try
        {
            Calendar cal = new GregorianCalendar();
            int tanggal = cal.get(Calendar.DAY_OF_MONTH);
            String sql = "select no_antrian as 'Nomor Antri', no_registrasi as 'No. Registrasi', nama_pasien as 'Nama Pasien', tanggal_entri as Tanggal, nama_pengentri as Petugas from antrian where bagian ='Bidan' and flag_active ='Y' or bagian ='Bidan' and flag_skip ='Y' and tanggal='"+tanggal+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tabel_antri.setModel(DbUtils.resultSetToTableModel(rs));
            //To remove previously added rows            
        }
        catch(  SQLException e)
        {
           JOptionPane.showMessageDialog(null, e.toString());
        }
        adjustColumnAntri(tabel_antri);
    }
    
    private void getJenisObat(){
        try{
            String sql = "Select distinct jenis_obat From obat";    
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                jenis_obat.addItem(rs.getString("jenis_obat"));
                jenis_obat1.addItem(rs.getString("jenis_obat"));
                jenis_obat2.addItem(rs.getString("jenis_obat"));
                jenis_obat3.addItem(rs.getString("jenis_obat"));
                jenis_obat4.addItem(rs.getString("jenis_obat"));
                jenis_obat5.addItem(rs.getString("jenis_obat"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
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

        txtSearchRekamMedis = new javax.swing.JTextField();
        Tabular = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_antri = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        no_skrg = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        no_antri = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        nama_pasien = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        no_registrasi = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nomor_rekam_medis = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        kode_resep = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        keluhan = new javax.swing.JTextArea();
        bSimpan = new javax.swing.JButton();
        bHapus = new javax.swing.JButton();
        bClear = new javax.swing.JButton();
        tgl_rekam_medis = new javax.swing.JLabel();
        btn_tindakan = new javax.swing.JButton();
        nomor_rekam_medis_lama = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jumlah = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        kode_obat = new javax.swing.JTextField();
        tgl_resep = new javax.swing.JLabel();
        total_harga_resep = new javax.swing.JTextField();
        harga_satuan = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        kode_resep1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        kode_obat1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jumlah1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        kode_obat2 = new javax.swing.JTextField();
        jumlah2 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        kode_obat3 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jumlah3 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        kode_obat4 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jumlah4 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        kode_obat5 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jumlah5 = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        add1 = new javax.swing.JButton();
        add2 = new javax.swing.JButton();
        add3 = new javax.swing.JButton();
        add4 = new javax.swing.JButton();
        add5 = new javax.swing.JButton();
        buat_resep = new javax.swing.JButton();
        clear_resep = new javax.swing.JButton();
        harga_satuan1 = new javax.swing.JTextField();
        harga_satuan2 = new javax.swing.JTextField();
        harga_satuan3 = new javax.swing.JTextField();
        harga_satuan4 = new javax.swing.JTextField();
        harga_satuan5 = new javax.swing.JTextField();
        total_harga_resep1 = new javax.swing.JTextField();
        total_harga_resep2 = new javax.swing.JTextField();
        total_harga_resep3 = new javax.swing.JTextField();
        total_harga_resep4 = new javax.swing.JTextField();
        total_harga_resep5 = new javax.swing.JTextField();
        nama_obat = new javax.swing.JComboBox();
        nama_obat5 = new javax.swing.JComboBox();
        nama_obat1 = new javax.swing.JComboBox();
        nama_obat2 = new javax.swing.JComboBox();
        nama_obat3 = new javax.swing.JComboBox();
        nama_obat4 = new javax.swing.JComboBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        aturan = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        submit_aturan = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jenis_obat = new javax.swing.JComboBox();
        jenis_obat1 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jenis_obat2 = new javax.swing.JComboBox();
        jenis_obat3 = new javax.swing.JComboBox();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jenis_obat4 = new javax.swing.JComboBox();
        jLabel36 = new javax.swing.JLabel();
        jenis_obat5 = new javax.swing.JComboBox();
        lblCariRekam = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_rekam = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabel_resep = new javax.swing.JTable();
        lblCariResep = new javax.swing.JLabel();
        txtSearchResep = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        datetime = new javax.swing.JMenu();
        nama_user = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Check Up Pasien & Resep");

        txtSearchRekamMedis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchRekamMedisKeyReleased(evt);
            }
        });

        Tabular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabularMouseClicked(evt);
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
        jScrollPane2.setViewportView(tabel_antri);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Antrian Ke :");

        no_skrg.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N

        jButton1.setText("Selesai");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        no_antri.setText("0");

        jButton2.setText("Lewati");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(12, 12, 12)
                        .addComponent(no_skrg, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(358, 358, 358)
                .addComponent(no_antri))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(no_skrg))
                .addGap(15, 15, 15)
                .addComponent(no_antri)
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)))
        );

        Tabular.addTab("Antrian", jPanel3);

        nama_pasien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Nama Pasien");

        no_registrasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                no_registrasiKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nomor Rekam Medis");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("No. Registrasi");

        nomor_rekam_medis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nomor_rekam_medisKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomor_rekam_medisKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tanggal Rekam Medis");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Kode Resep");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Tindakan");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Keluhan");

        keluhan.setColumns(20);
        keluhan.setRows(5);
        jScrollPane3.setViewportView(keluhan);

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

        tgl_rekam_medis.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tgl_rekam_medis.setText("0/0/0000");

        btn_tindakan.setText("Pilih Tindakan");
        btn_tindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tindakanActionPerformed(evt);
            }
        });

        nomor_rekam_medis_lama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nomor_rekam_medis_lamaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomor_rekam_medis_lamaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addGap(75, 75, 75)
                        .addComponent(jLabel10)
                        .addGap(508, 508, 508)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(nomor_rekam_medis, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(kode_resep, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(407, 407, 407)
                        .addComponent(tgl_rekam_medis, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4)
                        .addGap(118, 118, 118)
                        .addComponent(jLabel11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(no_registrasi, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(btn_tindakan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(nama_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(bSimpan)
                        .addGap(61, 61, 61)
                        .addComponent(bHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(bClear, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel5)
                .addGap(123, 123, 123)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nomor_rekam_medis_lama, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(202, 202, 202))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel10)
                    .addComponent(jLabel3))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomor_rekam_medis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kode_resep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(tgl_rekam_medis)))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(no_registrasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tindakan))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(nomor_rekam_medis_lama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(nama_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bClear, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        Tabular.addTab("Rekam Medis", jPanel1);

        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });
        jPanel2.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Kode Obat");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(17, 89, 59, 15);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Nama Obat");
        jPanel2.add(jLabel14);
        jLabel14.setBounds(340, 90, 61, 15);

        jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahActionPerformed(evt);
            }
        });
        jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlahKeyReleased(evt);
            }
        });
        jPanel2.add(jumlah);
        jumlah.setBounds(530, 110, 43, 24);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Tanggal Resep :");
        jPanel2.add(jLabel15);
        jLabel15.setBounds(660, 10, 100, 17);

        kode_obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kode_obatActionPerformed(evt);
            }
        });
        kode_obat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kode_obatKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kode_obatKeyTyped(evt);
            }
        });
        jPanel2.add(kode_obat);
        kode_obat.setBounds(17, 114, 130, 24);

        tgl_resep.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tgl_resep.setText("jLabel11");
        jPanel2.add(tgl_resep);
        tgl_resep.setBounds(760, 10, 200, 20);
        jPanel2.add(total_harga_resep);
        total_harga_resep.setBounds(390, 30, 50, 24);
        jPanel2.add(harga_satuan);
        harga_satuan.setBounds(80, 0, 51, 24);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Kode Resep");
        jPanel2.add(jLabel19);
        jLabel19.setBounds(17, 30, 65, 15);

        kode_resep1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kode_resep1KeyTyped(evt);
            }
        });
        jPanel2.add(kode_resep1);
        kode_resep1.setBounds(17, 53, 180, 24);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Jumlah");
        jPanel2.add(jLabel16);
        jLabel16.setBounds(530, 90, 37, 15);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Kode Obat");
        jPanel2.add(jLabel17);
        jLabel17.setBounds(17, 144, 59, 15);

        kode_obat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kode_obat1ActionPerformed(evt);
            }
        });
        kode_obat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kode_obat1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kode_obat1KeyTyped(evt);
            }
        });
        jPanel2.add(kode_obat1);
        kode_obat1.setBounds(17, 169, 130, 24);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Nama Obat");
        jPanel2.add(jLabel18);
        jLabel18.setBounds(340, 150, 61, 15);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Jumlah");
        jPanel2.add(jLabel20);
        jLabel20.setBounds(530, 150, 37, 15);

        jumlah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlah1ActionPerformed(evt);
            }
        });
        jumlah1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlah1KeyReleased(evt);
            }
        });
        jPanel2.add(jumlah1);
        jumlah1.setBounds(530, 170, 43, 24);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Nama Obat");
        jPanel2.add(jLabel21);
        jLabel21.setBounds(340, 200, 61, 15);

        kode_obat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kode_obat2ActionPerformed(evt);
            }
        });
        kode_obat2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kode_obat2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kode_obat2KeyTyped(evt);
            }
        });
        jPanel2.add(kode_obat2);
        kode_obat2.setBounds(17, 224, 130, 24);

        jumlah2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlah2ActionPerformed(evt);
            }
        });
        jumlah2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlah2KeyReleased(evt);
            }
        });
        jPanel2.add(jumlah2);
        jumlah2.setBounds(530, 220, 43, 24);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("Jumlah");
        jPanel2.add(jLabel22);
        jLabel22.setBounds(530, 200, 37, 15);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("Kode Obat");
        jPanel2.add(jLabel23);
        jLabel23.setBounds(17, 199, 59, 15);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("Kode Obat");
        jPanel2.add(jLabel24);
        jLabel24.setBounds(17, 254, 59, 15);

        kode_obat3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kode_obat3ActionPerformed(evt);
            }
        });
        kode_obat3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kode_obat3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kode_obat3KeyTyped(evt);
            }
        });
        jPanel2.add(kode_obat3);
        kode_obat3.setBounds(17, 279, 130, 24);

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText("Nama Obat");
        jPanel2.add(jLabel25);
        jLabel25.setBounds(340, 260, 61, 15);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText("Jumlah");
        jPanel2.add(jLabel26);
        jLabel26.setBounds(530, 260, 49, 15);

        jumlah3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlah3ActionPerformed(evt);
            }
        });
        jumlah3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlah3KeyReleased(evt);
            }
        });
        jPanel2.add(jumlah3);
        jumlah3.setBounds(530, 280, 43, 24);

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setText("Kode Obat");
        jPanel2.add(jLabel27);
        jLabel27.setBounds(17, 309, 59, 15);

        kode_obat4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kode_obat4ActionPerformed(evt);
            }
        });
        kode_obat4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kode_obat4KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kode_obat4KeyTyped(evt);
            }
        });
        jPanel2.add(kode_obat4);
        kode_obat4.setBounds(17, 334, 130, 24);

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setText("Nama Obat");
        jPanel2.add(jLabel28);
        jLabel28.setBounds(340, 310, 61, 15);

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setText("Jumlah");
        jPanel2.add(jLabel29);
        jLabel29.setBounds(530, 310, 49, 15);

        jumlah4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlah4ActionPerformed(evt);
            }
        });
        jumlah4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlah4KeyReleased(evt);
            }
        });
        jPanel2.add(jumlah4);
        jumlah4.setBounds(530, 330, 43, 24);

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setText("Kode Obat");
        jPanel2.add(jLabel30);
        jLabel30.setBounds(17, 364, 59, 15);

        kode_obat5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kode_obat5ActionPerformed(evt);
            }
        });
        kode_obat5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kode_obat5KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kode_obat5KeyTyped(evt);
            }
        });
        jPanel2.add(kode_obat5);
        kode_obat5.setBounds(17, 389, 130, 24);

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setText("Nama Obat");
        jPanel2.add(jLabel31);
        jLabel31.setBounds(340, 370, 61, 15);

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setText("Jumlah");
        jPanel2.add(jLabel32);
        jLabel32.setBounds(530, 370, 49, 15);

        jumlah5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlah5ActionPerformed(evt);
            }
        });
        jumlah5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlah5KeyReleased(evt);
            }
        });
        jPanel2.add(jumlah5);
        jumlah5.setBounds(530, 390, 43, 24);

        add.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        add.setText("+");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel2.add(add);
        add.setBounds(590, 110, 41, 24);

        add1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        add1.setText("+");
        add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add1ActionPerformed(evt);
            }
        });
        jPanel2.add(add1);
        add1.setBounds(590, 170, 41, 24);

        add2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        add2.setText("+");
        add2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add2ActionPerformed(evt);
            }
        });
        jPanel2.add(add2);
        add2.setBounds(590, 220, 41, 24);

        add3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        add3.setText("+");
        add3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add3ActionPerformed(evt);
            }
        });
        jPanel2.add(add3);
        add3.setBounds(590, 280, 41, 24);

        add4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        add4.setText("+");
        add4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add4ActionPerformed(evt);
            }
        });
        jPanel2.add(add4);
        add4.setBounds(590, 330, 41, 24);

        add5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        add5.setText("+");
        add5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add5ActionPerformed(evt);
            }
        });
        jPanel2.add(add5);
        add5.setBounds(590, 390, 41, 24);

        buat_resep.setText("Buat Resep");
        buat_resep.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buat_resep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buat_resepActionPerformed(evt);
            }
        });
        jPanel2.add(buat_resep);
        buat_resep.setBounds(230, 50, 96, 24);

        clear_resep.setText("Clear");
        clear_resep.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        clear_resep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_resepActionPerformed(evt);
            }
        });
        jPanel2.add(clear_resep);
        clear_resep.setBounds(230, 50, 96, 24);
        jPanel2.add(harga_satuan1);
        harga_satuan1.setBounds(140, 0, 51, 24);
        jPanel2.add(harga_satuan2);
        harga_satuan2.setBounds(320, 0, 51, 24);
        jPanel2.add(harga_satuan3);
        harga_satuan3.setBounds(17, 0, 51, 24);
        jPanel2.add(harga_satuan4);
        harga_satuan4.setBounds(200, 0, 51, 24);
        jPanel2.add(harga_satuan5);
        harga_satuan5.setBounds(260, 0, 51, 24);
        jPanel2.add(total_harga_resep1);
        total_harga_resep1.setBounds(390, 0, 50, 24);
        jPanel2.add(total_harga_resep2);
        total_harga_resep2.setBounds(450, 0, 50, 24);
        jPanel2.add(total_harga_resep3);
        total_harga_resep3.setBounds(510, 0, 50, 24);
        jPanel2.add(total_harga_resep4);
        total_harga_resep4.setBounds(570, 0, 50, 24);
        jPanel2.add(total_harga_resep5);
        total_harga_resep5.setBounds(450, 30, 50, 24);

        nama_obat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " Pilih" }));
        nama_obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_obatActionPerformed(evt);
            }
        });
        jPanel2.add(nama_obat);
        nama_obat.setBounds(340, 110, 160, 30);

        nama_obat5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Obat" }));
        nama_obat5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_obat5ActionPerformed(evt);
            }
        });
        jPanel2.add(nama_obat5);
        nama_obat5.setBounds(340, 390, 160, 30);

        nama_obat1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Obat" }));
        nama_obat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_obat1ActionPerformed(evt);
            }
        });
        jPanel2.add(nama_obat1);
        nama_obat1.setBounds(340, 170, 160, 30);

        nama_obat2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Obat" }));
        nama_obat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_obat2ActionPerformed(evt);
            }
        });
        jPanel2.add(nama_obat2);
        nama_obat2.setBounds(340, 220, 160, 30);

        nama_obat3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Obat" }));
        nama_obat3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_obat3ActionPerformed(evt);
            }
        });
        jPanel2.add(nama_obat3);
        nama_obat3.setBounds(340, 280, 160, 30);

        nama_obat4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Obat" }));
        nama_obat4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_obat4ActionPerformed(evt);
            }
        });
        jPanel2.add(nama_obat4);
        nama_obat4.setBounds(340, 330, 160, 30);

        aturan.setColumns(20);
        aturan.setRows(5);
        jScrollPane5.setViewportView(aturan);

        jPanel2.add(jScrollPane5);
        jScrollPane5.setBounds(670, 110, 280, 200);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Aturan Pakai");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(670, 90, 80, 15);

        submit_aturan.setText("Submit");
        submit_aturan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submit_aturanActionPerformed(evt);
            }
        });
        jPanel2.add(submit_aturan);
        submit_aturan.setBounds(670, 320, 90, 32);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Jenis Obat");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(180, 90, 70, 15);

        jenis_obat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Jenis Obat" }));
        jenis_obat.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jenis_obatPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jenis_obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenis_obatActionPerformed(evt);
            }
        });
        jPanel2.add(jenis_obat);
        jenis_obat.setBounds(180, 110, 130, 26);

        jenis_obat1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Jenis Obat" }));
        jenis_obat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenis_obat1ActionPerformed(evt);
            }
        });
        jPanel2.add(jenis_obat1);
        jenis_obat1.setBounds(180, 170, 130, 26);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Jenis Obat");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(180, 150, 70, 15);

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText("Jenis Obat");
        jPanel2.add(jLabel33);
        jLabel33.setBounds(180, 200, 70, 15);

        jenis_obat2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Jenis Obat" }));
        jenis_obat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenis_obat2ActionPerformed(evt);
            }
        });
        jPanel2.add(jenis_obat2);
        jenis_obat2.setBounds(180, 220, 130, 26);

        jenis_obat3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Jenis Obat" }));
        jenis_obat3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenis_obat3ActionPerformed(evt);
            }
        });
        jPanel2.add(jenis_obat3);
        jenis_obat3.setBounds(180, 280, 130, 26);

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel34.setText("Jenis Obat");
        jPanel2.add(jLabel34);
        jLabel34.setBounds(180, 260, 70, 15);

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel35.setText("Jenis Obat");
        jPanel2.add(jLabel35);
        jLabel35.setBounds(180, 310, 70, 15);

        jenis_obat4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Jenis Obat" }));
        jenis_obat4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenis_obat4ActionPerformed(evt);
            }
        });
        jPanel2.add(jenis_obat4);
        jenis_obat4.setBounds(180, 330, 130, 26);

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel36.setText("Jenis Obat");
        jPanel2.add(jLabel36);
        jLabel36.setBounds(180, 370, 70, 15);

        jenis_obat5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Jenis Obat" }));
        jenis_obat5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenis_obat5ActionPerformed(evt);
            }
        });
        jPanel2.add(jenis_obat5);
        jenis_obat5.setBounds(180, 390, 130, 26);

        Tabular.addTab("Resep", jPanel2);

        lblCariRekam.setText("Cari:");

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
        tabel_rekam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_rekamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabel_rekamMouseEntered(evt);
            }
        });
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
        jScrollPane4.setViewportView(tabel_resep);

        lblCariResep.setText("Cari:");

        txtSearchResep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchResepKeyReleased(evt);
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

        nama_user.setText("User");
        jMenuBar1.add(nama_user);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(774, 774, 774)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCariRekam)
                            .addComponent(lblCariResep))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSearchResep, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearchRekamMedis, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 976, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(Tabular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCariRekam)
                            .addComponent(lblCariResep)))
                    .addComponent(txtSearchResep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchRekamMedis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(Tabular, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void txtSearchRekamMedisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchRekamMedisKeyReleased
        // TODO add your handling code here:
        try{
            String sql = "select nomor_rekam_medis as 'No. Rekam Medis', DATE_FORMAT(tgl_rekam_medis, '%d, %M %Y') as 'Tanggal Rekam Medis', no_registrasi as 'No. Registrasi', nama_pasien as 'Nama Depan', kode_resep as 'Kode Resep', keluhan as Keluhan, tanggal_entri as 'Tanggal Entri', nama_dokter as 'Nama Dokter' from rekam_medis where bagian = 'umum' and tgl_rekam_medis LIKE ? or nomor_rekam_medis LIKE ? or no_registrasi LIKE ? or nama_pasien LIKE ? order by tgl_rekam_medis desc ";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%"+txtSearchRekamMedis.getText()+"%");
            pst.setString(2, "%"+txtSearchRekamMedis.getText()+"%");
            pst.setString(3, "%"+txtSearchRekamMedis.getText()+"%");
            pst.setString(4, "%"+txtSearchRekamMedis.getText()+"%");
            rs=pst.executeQuery();
            tabel_rekam.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        adjustColumn(tabel_rekam);
    }//GEN-LAST:event_txtSearchRekamMedisKeyReleased

    private void tabel_antriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_antriMouseClicked
        // TODO add your handling code here:
        try{
            int row = tabel_antri.getSelectedRow();
            String klik=(tabel_antri.getModel().getValueAt(row,1).toString());
            String klak=(tabel_antri.getModel().getValueAt(row,0).toString());
            String sql = "select no_antrian as 'Nomor Antri', no_registrasi as 'No. Registrasi', nama_pasien as 'Nama Pasien', flag_active as 'Status Antrian', tanggal_entri as Tanggal, nama_pengentri as Petugas from antrian where bagian ='Bidan' and no_antrian = '"+klak+"' and no_registrasi='"+klik+"' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                String add1 = rs.getString("No. Registrasi");
                no_registrasi.setText(add1);
                String add2 = rs.getString("Nama Pasien");
                nama_pasien.setText(add2);
                String add3 = rs.getString("Nomor Antri");
                no_skrg.setText(add3);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tabel_antriMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
            String value1 = no_skrg.getText();
            con.createStatement().execute("update antrian set flag_active ='N' where no_antrian='"+value1+"' ");
            Calendar cal = new GregorianCalendar();
            String regis = no_registrasi.getText();
            String no_rek = nomor_rekam_medis.getText();
            String pasien = nama_pasien.getText();
            String dok= ID;
            String antrian = no_antri.getText();
            int tanggal = cal.get(Calendar.DAY_OF_MONTH);
            con.createStatement().execute("insert into antrian_bayar (no_antrian,nomor_rekam_medis,no_registrasi,nama_pasien,tanggal,flag_active,tanggal_entri,nama_pengentri)values ('"+antrian+"','"+no_rek+"','"+regis+"','"+pasien+"','"+tanggal+"','Y',DATE_FORMAT(NOW(),'%Y-%m-%d %T'),'"+dok+"')");
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,e);
        }
        setupantri();
        ksr.setupantri();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try{
            String noantri = no_skrg.getText();
            con.createStatement().execute("update antrian set flag_active = 'N', flag_skip ='Y' where no_antrian='"+noantri+"' and no_registrasi = '"+no_registrasi.getText()+"' ");
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,e);
        }
        setupantri();
        ksr.setupantri();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void no_registrasiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_no_registrasiKeyReleased
        // TODO add your handling code here:
        try {
            String sql = "Select * From pasien Where no_registrasi='"+this.no_registrasi.getText()+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                nama_pasien.setText(rs.getString("nama_pasien"));
            }
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan "+e.getMessage());
        }
    }//GEN-LAST:event_no_registrasiKeyReleased

    private void nomor_rekam_medisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomor_rekam_medisKeyReleased
        // TODO add your handling code here:
        no_rekam = nomor_rekam_medis.getText();
    }//GEN-LAST:event_nomor_rekam_medisKeyReleased

    private void nomor_rekam_medisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomor_rekam_medisKeyTyped
        // TODO add your handling code here:
        if(nomor_rekam_medis.getText().length()>=10){
            evt.consume();
        }
    }//GEN-LAST:event_nomor_rekam_medisKeyTyped

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        // TODO add your handling code here:
        int p = JOptionPane.showConfirmDialog(null, "Anda yakin ingin mengubah data?","Simpan Data",JOptionPane.YES_NO_OPTION);
        if(p==0){
            try{
                String value1 = nomor_rekam_medis_lama.getText();
                String value2 = no_registrasi.getText();
                String value3 = nama_pasien.getText();
                String value4 = ID;
                String value5 = kode_resep.getText();
                String value7 = keluhan.getText();
                con.createStatement().execute("update rekam_medis set nomor_rekam_medis ='"+value1+"', tgl_rekam_medis= DATE_FORMAT(NOW(),'%Y-%m-%d'), no_registrasi='"+value2+"', nama_pasien='"+value3+"', kode_resep='"+value5+"', keluhan='"+value7+"', tanggal_entri=DATE_FORMAT(NOW(),'%Y-%m-%d %T'), nama_dokter='"+value4+"' where nomor_rekam_medis='"+value1+"' ");
                JOptionPane.showMessageDialog(rootPane,"Data Terupdate!");
            } catch(SQLException | HeadlessException e){
                System.out.println("Data sudah pernah diinput");
                JOptionPane.showMessageDialog(null,"Data yang diinput belum lengkap! / Data sudah pernah diinput! / tidak boleh input data yang sama!");
            }
            setuptable();
            try{
            String value1 = no_skrg.getText();
            con.createStatement().execute("update antrian set flag_active ='N', flag_skip='N' where no_antrian='"+value1+"' ");
            Calendar cal = new GregorianCalendar();
            String regis = no_registrasi.getText();
            String no_rek = nomor_rekam_medis_lama.getText();
            String pasien = nama_pasien.getText();
            String dok= ID;
            String antrian = no_antri.getText();
            int tanggal = cal.get(Calendar.DAY_OF_MONTH);
            con.createStatement().execute("insert into antrian_bayar (no_antrian,nomor_rekam_medis,no_registrasi,nama_pasien,tanggal,flag_active,tanggal_entri,nama_pengentri)values ('"+antrian+"','"+no_rek+"','"+regis+"','"+pasien+"','"+tanggal+"','Y',DATE_FORMAT(NOW(),'%Y-%m-%d %T'),'"+dok+"')");
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,e);
        }
        setupantri();
        ksr.setupantri();
        generate_norekam();
        generate_noresep();
        }
    }//GEN-LAST:event_bSimpanActionPerformed

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        // TODO add your handling code here:
        int p = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data?","Hapus Data",JOptionPane.YES_NO_OPTION);
        if(p==0){
            String sql = "delete from rekam_medis where nomor_rekam_medis=?";
            try{
                pst = con.prepareStatement(sql);
                pst.setString(1, nomor_rekam_medis.getText());
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
        nomor_rekam_medis.setText("");
        no_registrasi.setText("");
        nama_pasien.setText("");
        kode_resep.setText("");
        keluhan.setText("");
    }//GEN-LAST:event_bClearActionPerformed

    private void btn_tindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tindakanActionPerformed
        // TODO add your handling code here:
        try{
            String insert ="insert into rekam_medis (nomor_rekam_medis, tgl_rekam_medis, no_registrasi, nama_pasien, kode_resep, keluhan, total_harga_tindakan, bagian, tanggal_entri, nama_dokter) values (?,DATE_FORMAT(NOW(),'%Y-%m-%d'),?,?,'00000000',?,'0','Bidan',DATE_FORMAT(NOW(),'%Y-%m-%d %T'),?)";
            pst=con.prepareStatement(insert);
            pst.setString(1, nomor_rekam_medis.getText());
            pst.setString(2, no_registrasi.getText());
            pst.setString(3, nama_pasien.getText());
            pst.setString(4, keluhan.getText());
            pst.setString(5, ID);
            pst.execute();
            nomor_rekam_medis_lama.setText(nomor_rekam_medis.getText());
        }catch (SQLException | HeadlessException e){
            System.out.println("Data sudah pernah diinput");
            JOptionPane.showMessageDialog(null,e);
        }
        setuptable();
        new Tindakan_Bidan().setVisible(true);
    }//GEN-LAST:event_btn_tindakanActionPerformed

    private void jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahActionPerformed

    private void jumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahKeyReleased
        // TODO add your handling code here:
        harga_obat = Integer.valueOf(harga_satuan.getText());
        jumlah_obat = Integer.valueOf(jumlah.getText());
        total_harga_obat= jumlah_obat*harga_obat;
        String ini = String.valueOf(total_harga_obat);
        total_harga_resep.setText(ini);
    }//GEN-LAST:event_jumlahKeyReleased

    private void kode_obatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kode_obatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kode_obatActionPerformed

    private void kode_obatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_obatKeyReleased
        // TODO add your handling code here:
        try {
            String sql = "select * from obat where kode_obat='"+this.kode_obat.getText()+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                nama_obat.setSelectedItem(rs.getString("nama_obat"));
                harga_satuan.setText(rs.getString("harga_satuan"));
            }
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan "+e.getMessage());
        }
    }//GEN-LAST:event_kode_obatKeyReleased

    private void kode_obatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_obatKeyTyped
        // TODO add your handling code here:
        if(kode_obat.getText().length()>=6){
            evt.consume();
        }
    }//GEN-LAST:event_kode_obatKeyTyped

    private void kode_resep1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_resep1KeyTyped
        // TODO add your handling code here:
        if(kode_resep1.getText().length()>=8){
            evt.consume();
        }
    }//GEN-LAST:event_kode_resep1KeyTyped

    private void kode_obat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kode_obat1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kode_obat1ActionPerformed

    private void kode_obat1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_obat1KeyReleased
        // TODO add your handling code here:
        try {
            String sql = "select * from obat where kode_obat='"+this.kode_obat1.getText()+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                nama_obat1.setSelectedItem(rs.getString("nama_obat"));
                harga_satuan1.setText(rs.getString("harga_satuan"));
            }
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan "+e.getMessage());
        }
    }//GEN-LAST:event_kode_obat1KeyReleased

    private void kode_obat1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_obat1KeyTyped
        // TODO add your handling code here:
        if(kode_obat1.getText().length()>=6){
            evt.consume();
        }
    }//GEN-LAST:event_kode_obat1KeyTyped

    private void jumlah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlah1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlah1ActionPerformed

    private void jumlah1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlah1KeyReleased
        // TODO add your handling code here:
        harga_obat1 = Integer.valueOf(harga_satuan1.getText());
        jumlah_obat1 = Integer.valueOf(jumlah1.getText());
        total_harga_obat1= jumlah_obat1*harga_obat1;
        total_harga_obat=Integer.valueOf(total_harga_resep.getText());
        int total=total_harga_obat1+total_harga_obat;
        String ini = String.valueOf(total);
        total_harga_resep1.setText(ini);
    }//GEN-LAST:event_jumlah1KeyReleased

    private void kode_obat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kode_obat2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kode_obat2ActionPerformed

    private void kode_obat2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_obat2KeyReleased
        // TODO add your handling code here:
        try {
            String sql = "select * from obat where kode_obat='"+this.kode_obat2.getText()+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                nama_obat2.setSelectedItem(rs.getString("nama_obat"));
                harga_satuan2.setText(rs.getString("harga_satuan"));
            }
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan "+e.getMessage());
        }
    }//GEN-LAST:event_kode_obat2KeyReleased

    private void kode_obat2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_obat2KeyTyped
        // TODO add your handling code here:
        if(kode_obat2.getText().length()>=6){
            evt.consume();
        }
    }//GEN-LAST:event_kode_obat2KeyTyped

    private void jumlah2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlah2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlah2ActionPerformed

    private void jumlah2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlah2KeyReleased
        // TODO add your handling code here:
        harga_obat2 = Integer.valueOf(harga_satuan2.getText());
        jumlah_obat2 = Integer.valueOf(jumlah2.getText());
        total_harga_obat2= jumlah_obat2*harga_obat2;
        total_harga_obat1=Integer.valueOf(total_harga_resep1.getText());
        int total=total_harga_obat2+total_harga_obat1;
        String ini = String.valueOf(total);
        total_harga_resep2.setText(ini);
    }//GEN-LAST:event_jumlah2KeyReleased

    private void kode_obat3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kode_obat3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kode_obat3ActionPerformed

    private void kode_obat3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_obat3KeyReleased
        // TODO add your handling code here:
        try {
            String sql = "select * from obat where kode_obat='"+this.kode_obat3.getText()+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                nama_obat3.setSelectedItem(rs.getString("nama_obat"));
                harga_satuan3.setText(rs.getString("harga_satuan"));
            }
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan "+e.getMessage());
        }
    }//GEN-LAST:event_kode_obat3KeyReleased

    private void kode_obat3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_obat3KeyTyped
        // TODO add your handling code here:
        if(kode_obat3.getText().length()>=6){
            evt.consume();
        }
    }//GEN-LAST:event_kode_obat3KeyTyped

    private void jumlah3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlah3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlah3ActionPerformed

    private void jumlah3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlah3KeyReleased
        // TODO add your handling code here:
        harga_obat3 = Integer.valueOf(harga_satuan3.getText());
        jumlah_obat3 = Integer.valueOf(jumlah3.getText());
        total_harga_obat3 = jumlah_obat3*harga_obat3;
        total_harga_obat2 = Integer.valueOf(total_harga_resep2.getText());
        int total = total_harga_obat3+total_harga_obat2;
        String ini = String.valueOf(total);
        total_harga_resep3.setText(ini);
    }//GEN-LAST:event_jumlah3KeyReleased

    private void kode_obat4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kode_obat4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kode_obat4ActionPerformed

    private void kode_obat4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_obat4KeyReleased
        // TODO add your handling code here:
        try {
            String sql = "select * from obat where kode_obat='"+this.kode_obat4.getText()+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                nama_obat4.setSelectedItem(rs.getString("nama_obat"));
                harga_satuan4.setText(rs.getString("harga_satuan"));
            }
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan "+e.getMessage());
        }
    }//GEN-LAST:event_kode_obat4KeyReleased

    private void kode_obat4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_obat4KeyTyped
        // TODO add your handling code here:
        if(kode_obat4.getText().length()>=6){
            evt.consume();
        }
    }//GEN-LAST:event_kode_obat4KeyTyped

    private void jumlah4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlah4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlah4ActionPerformed

    private void jumlah4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlah4KeyReleased
        // TODO add your handling code here:
        harga_obat4 = Integer.valueOf(harga_satuan4.getText());
        jumlah_obat4 = Integer.valueOf(jumlah4.getText());
        total_harga_obat4 = jumlah_obat4*harga_obat4;
        total_harga_obat3 = Integer.valueOf(total_harga_resep3.getText());
        int total = total_harga_obat4+total_harga_obat3;
        String ini = String.valueOf(total);
        total_harga_resep4.setText(ini);
    }//GEN-LAST:event_jumlah4KeyReleased

    private void kode_obat5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kode_obat5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kode_obat5ActionPerformed

    private void kode_obat5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_obat5KeyReleased
        // TODO add your handling code here:
        try {
            String sql = "select * from obat where kode_obat='"+this.kode_obat5.getText()+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                nama_obat5.setSelectedItem(rs.getString("nama_obat"));
                harga_satuan5.setText(rs.getString("harga_satuan"));
            }
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan "+e.getMessage());
        }
    }//GEN-LAST:event_kode_obat5KeyReleased

    private void kode_obat5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_obat5KeyTyped
        // TODO add your handling code here:
        if(kode_obat5.getText().length()>=6){
            evt.consume();
        }
    }//GEN-LAST:event_kode_obat5KeyTyped

    private void jumlah5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlah5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlah5ActionPerformed

    private void jumlah5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlah5KeyReleased
        // TODO add your handling code here:
        harga_obat5 = Integer.valueOf(harga_satuan5.getText());
        jumlah_obat5 = Integer.valueOf(jumlah5.getText());
        total_harga_obat5 = jumlah_obat5*harga_obat5;
        total_harga_obat4 = Integer.valueOf(total_harga_resep4.getText());
        int total = total_harga_obat5+total_harga_obat4;
        String ini = String.valueOf(total);
        total_harga_resep5.setText(ini);
    }//GEN-LAST:event_jumlah5KeyReleased

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        try{
            String value1 = jumlah.getText();
            String value2 = kode_obat.getText();
            int pengurang = parseInt(value1);
            int stockitem = 0;
            String sql = "select * from obat where kode_obat ='"+value2+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                stockitem = rs.getInt("stok");
            }
            int hasil=0;
            hasil+=stockitem-pengurang;
            System.out.println("ini hasilnya "+hasil);
            if(hasil<0){
                JOptionPane.showMessageDialog(null,"Maaf, stok obat kurang");
            }
            else if (hasil > 0){
                try{
                    String insert ="insert into detail_resep (kode_resep,kode_obat,nama_obat,jumlah,harga_satuan,tanggal_entri,nama_pengentri)values (?,?,?,?,?,DATE_FORMAT(NOW(),'%Y-%m-%d %T'),?)";
                    pst=con.prepareStatement(insert);
                    pst.setString(1, kode_resep1.getText());
                    pst.setString(2, kode_obat.getText());
                    pst.setString(3, (String) nama_obat.getSelectedItem());
                    pst.setString(4, jumlah.getText());
                    pst.setString(5, harga_satuan.getText());
                    pst.setString(6, ID);
                    pst.execute();
                }catch (SQLException | HeadlessException e){
                    System.out.println("Data sudah pernah diinput");
                    JOptionPane.showMessageDialog(null,e);
                }
                try{
                    String resep = kode_resep1.getText();
                    String harga = total_harga_resep.getText();

                    con.createStatement().execute("update header_resep set total_harga_resep ='"+harga+"' where kode_resep='"+resep+"' ");
                } catch(SQLException | HeadlessException e){
                    JOptionPane.showMessageDialog(null,e);
                }
                con.createStatement().execute("update obat set stok = '"+hasil+"' Where kode_obat= '"+value2+"' ");
                jLabel17.setVisible(true);
                jLabel18.setVisible(true);
                jLabel20.setVisible(true);
                kode_obat1.setVisible(true);
                nama_obat1.setVisible(true);
                jumlah1.setVisible(true);
                add1.setVisible(true);
                add.setVisible(false);
                jLabel9.setVisible(true);
                jenis_obat1.setVisible(true);
            }
            else{
                System.out.println("ini error ");
            }

        } catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        setupresep();
    }//GEN-LAST:event_addActionPerformed

    private void add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add1ActionPerformed
        // TODO add your handling code here:
        try{
            String value1 = jumlah1.getText();
            String value2 = kode_obat1.getText();
            int pengurang = parseInt(value1);
            int stockitem = 0;
            String sql = "select * from obat where kode_obat ='"+value2+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                stockitem = rs.getInt("stok");
            }
            int hasil=0;
            hasil+=stockitem-pengurang;
            System.out.println("ini hasilnya "+hasil);
            if(hasil<0){
                JOptionPane.showMessageDialog(null,"Maaf, stok obat kurang");
            }
            else if (hasil > 0){
                try{
                    String insert ="insert into detail_resep (kode_resep,kode_obat,nama_obat,jumlah,harga_satuan,tanggal_entri,nama_pengentri)values (?,?,?,?,?,DATE_FORMAT(NOW(),'%Y-%m-%d %T'),?)";
                    pst=con.prepareStatement(insert);
                    pst.setString(1, kode_resep1.getText());
                    pst.setString(2, kode_obat1.getText());
                    pst.setString(3, (String)nama_obat1.getSelectedItem());
                    pst.setString(4, jumlah1.getText());
                    pst.setString(5, harga_satuan1.getText());
                    pst.setString(6, ID);
                    pst.execute();
                }catch (SQLException | HeadlessException e){
                    System.out.println("Data sudah pernah diinput");
                    JOptionPane.showMessageDialog(null,e);
                }
                try{
                    String resep = kode_resep1.getText();
                    String harga = total_harga_resep1.getText();

                    con.createStatement().execute("update header_resep set total_harga_resep ='"+harga+"' where kode_resep='"+resep+"' ");
                } catch(SQLException | HeadlessException e){
                    JOptionPane.showMessageDialog(null,e);
                }
                con.createStatement().execute("update obat set stok = '"+hasil+"' Where kode_obat= '"+value2+"' ");
                jLabel23.setVisible(true);
                jLabel21.setVisible(true);
                jLabel22.setVisible(true);
                kode_obat2.setVisible(true);
                nama_obat2.setVisible(true);
                jumlah2.setVisible(true);
                add2.setVisible(true);
                add1.setVisible(false);
                jLabel33.setVisible(true);
                jenis_obat2.setVisible(true);
            }
            else{
                System.out.println("ini error ");
            }

        } catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        setupresep();

    }//GEN-LAST:event_add1ActionPerformed

    private void add2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add2ActionPerformed
        // TODO add your handling code here:
        try{
            String value1 = jumlah2.getText();
            String value2 = kode_obat2.getText();
            int pengurang = parseInt(value1);
            int stockitem = 0;
            String sql = "select * from obat where kode_obat ='"+value2+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                stockitem = rs.getInt("stok");
            }
            int hasil=0;
            hasil+=stockitem-pengurang;
            System.out.println("ini hasilnya "+hasil);
            if(hasil<0){
                JOptionPane.showMessageDialog(null,"Maaf, stok obat kurang");
            }
            else if (hasil > 0){
                try{
                    String insert ="insert into detail_resep (kode_resep,kode_obat,nama_obat,jumlah,harga_satuan,tanggal_entri,nama_pengentri)values (?,?,?,?,?,DATE_FORMAT(NOW(),'%Y-%m-%d %T'),?)";
                    pst=con.prepareStatement(insert);
                    pst.setString(1, kode_resep1.getText());
                    pst.setString(2, kode_obat2.getText());
                    pst.setString(3, (String) nama_obat2.getSelectedItem());
                    pst.setString(4, jumlah2.getText());
                    pst.setString(5, harga_satuan2.getText());
                    pst.setString(6, ID);
                    pst.execute();
                }catch (SQLException | HeadlessException e){
                    System.out.println("Data sudah pernah diinput");
                    JOptionPane.showMessageDialog(null,e);
                }
                try{
                    String resep = kode_resep1.getText();
                    String harga = total_harga_resep2.getText();

                    con.createStatement().execute("update header_resep set total_harga_resep ='"+harga+"' where kode_resep='"+resep+"' ");
                } catch(SQLException | HeadlessException e){
                    JOptionPane.showMessageDialog(null,e);
                }
                con.createStatement().execute("update obat set stok = '"+hasil+"' Where kode_obat= '"+value2+"' ");
                jLabel24.setVisible(true);
                jLabel25.setVisible(true);
                jLabel26.setVisible(true);
                kode_obat3.setVisible(true);
                nama_obat3.setVisible(true);
                jumlah3.setVisible(true);
                add3.setVisible(true);
                add2.setVisible(false);
                jLabel34.setVisible(true);
                jenis_obat3.setVisible(true);
            }
            else{
                System.out.println("ini error ");
            }

        } catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        setupresep();

    }//GEN-LAST:event_add2ActionPerformed

    private void add3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add3ActionPerformed
        // TODO add your handling code here:
        try{
            String value1 = jumlah3.getText();
            String value2 = kode_obat3.getText();
            int pengurang = parseInt(value1);
            int stockitem = 0;
            String sql = "select * from obat where kode_obat ='"+value2+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                stockitem = rs.getInt("stok");
            }
            int hasil=0;
            hasil+=stockitem-pengurang;
            System.out.println("ini hasilnya "+hasil);
            if(hasil<0){
                JOptionPane.showMessageDialog(null,"Maaf, stok obat kurang");
            }
            else if (hasil > 0){
                try{
                    String insert ="insert into detail_resep (kode_resep,kode_obat,nama_obat,jumlah,harga_satuan,tanggal_entri,nama_pengentri)values (?,?,?,?,?,DATE_FORMAT(NOW(),'%Y-%m-%d %T'),?)";
                    pst=con.prepareStatement(insert);
                    pst.setString(1, kode_resep1.getText());
                    pst.setString(2, kode_obat3.getText());
                    pst.setString(3, (String) nama_obat3.getSelectedItem());
                    pst.setString(4, jumlah3.getText());
                    pst.setString(5, harga_satuan3.getText());
                    pst.setString(6, ID);
                    pst.execute();
                }catch (SQLException | HeadlessException e){
                    System.out.println("Data sudah pernah diinput");
                    JOptionPane.showMessageDialog(null,e);
                }
                try{
                    String resep = kode_resep1.getText();
                    String harga = total_harga_resep3.getText();

                    con.createStatement().execute("update header_resep set total_harga_resep ='"+harga+"' where kode_resep='"+resep+"' ");
                } catch(SQLException | HeadlessException e){
                    JOptionPane.showMessageDialog(null,e);
                }
                con.createStatement().execute("update obat set stok = '"+hasil+"' Where kode_obat= '"+value2+"' ");
                jLabel27.setVisible(true);
                jLabel28.setVisible(true);
                jLabel29.setVisible(true);
                kode_obat4.setVisible(true);
                nama_obat4.setVisible(true);
                jumlah4.setVisible(true);
                add4.setVisible(true);
                add3.setVisible(false);
                jLabel35.setVisible(true);
                jenis_obat4.setVisible(true);
            }
            else{
                System.out.println("ini error ");
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        setupresep();
    }//GEN-LAST:event_add3ActionPerformed

    private void add4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add4ActionPerformed
        // TODO add your handling code here:
        try{
            String value1 = jumlah4.getText();
            String value2 = kode_obat4.getText();
            int pengurang = parseInt(value1);
            int stockitem = 0;
            String sql = "select * from obat where kode_obat ='"+value2+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                stockitem = rs.getInt("stok");
            }
            int hasil=0;
            hasil+=stockitem-pengurang;
            System.out.println("ini hasilnya "+hasil);
            if(hasil<0){
                JOptionPane.showMessageDialog(null,"Maaf, stok obat kurang");
            }
            else if (hasil > 0){
                try{
                    String insert ="insert into detail_resep (kode_resep,kode_obat,nama_obat,jumlah,harga_satuan,tanggal_entri,nama_pengentri)values (?,?,?,?,?,DATE_FORMAT(NOW(),'%Y-%m-%d %T'),?)";
                    pst=con.prepareStatement(insert);
                    pst.setString(1, kode_resep1.getText());
                    pst.setString(2, kode_obat4.getText());
                    pst.setString(3, (String) nama_obat4.getSelectedItem());
                    pst.setString(4, jumlah4.getText());
                    pst.setString(5, harga_satuan4.getText());
                    pst.setString(6, ID);
                    pst.execute();
                }catch (SQLException | HeadlessException e){
                    System.out.println("Data sudah pernah diinput");
                    JOptionPane.showMessageDialog(null,e);
                }
                try{
                    String resep = kode_resep1.getText();
                    String harga = total_harga_resep4.getText();

                    con.createStatement().execute("update header_resep set total_harga_resep ='"+harga+"' where kode_resep='"+resep+"' ");
                } catch(SQLException | HeadlessException e){
                    JOptionPane.showMessageDialog(null,e);
                }
                con.createStatement().execute("update obat set stok = '"+hasil+"' Where kode_obat= '"+value2+"' ");
                jLabel30.setVisible(true);
                jLabel31.setVisible(true);
                jLabel32.setVisible(true);
                kode_obat5.setVisible(true);
                nama_obat5.setVisible(true);
                jumlah5.setVisible(true);
                add5.setVisible(true);
                add4.setVisible(false);
                jLabel36.setVisible(true);
                jenis_obat5.setVisible(true);
            }
            else{
                System.out.println("ini error ");
            }

        } catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        setupresep();
    }//GEN-LAST:event_add4ActionPerformed

    private void add5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add5ActionPerformed
        // TODO add your handling code here:
        try{
            String value1 = jumlah5.getText();
            String value2 = kode_obat5.getText();
            int pengurang = parseInt(value1);
            int stockitem = 0;
            String sql = "select * from obat where kode_obat ='"+value2+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                stockitem = rs.getInt("stok");
            }
            int hasil=0;
            hasil+=stockitem-pengurang;
            System.out.println("ini hasilnya "+hasil);
            if(hasil<0){
                JOptionPane.showMessageDialog(null,"Maaf, stok obat kurang");
            }
            else if (hasil > 0){
                try{
                    String insert ="insert into detail_resep (kode_resep,kode_obat,nama_obat,jumlah,harga_satuan,tanggal_entri,nama_pengentri)values (?,?,?,?,?,DATE_FORMAT(NOW(),'%Y-%m-%d %T'),?)";
                    pst=con.prepareStatement(insert);
                    pst.setString(1, kode_resep1.getText());
                    pst.setString(2, kode_obat5.getText());
                    pst.setString(3, (String) nama_obat5.getSelectedItem());
                    pst.setString(4, jumlah5.getText());
                    pst.setString(5, harga_satuan5.getText());
                    pst.setString(6, ID);
                    pst.execute();
                }catch (SQLException | HeadlessException e){
                    System.out.println("Data sudah pernah diinput");
                    JOptionPane.showMessageDialog(null,e);
                }
                try{
                    String resep = kode_resep1.getText();
                    String harga = total_harga_resep5.getText();

                    con.createStatement().execute("update header_resep set total_harga_resep ='"+harga+"' where kode_resep='"+resep+"' ");
                } catch(SQLException | HeadlessException e){
                    JOptionPane.showMessageDialog(null,e);
                }
                con.createStatement().execute("update obat set stok = '"+hasil+"' Where kode_obat= '"+value2+"' ");
            }
            else{
                System.out.println("ini error ");
            }

        } catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        setupresep();
    }//GEN-LAST:event_add5ActionPerformed

    private void buat_resepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buat_resepActionPerformed
        // TODO add your handling code here:
        if(kode_resep1.getText().equals("")){
            JOptionPane.showMessageDialog(null,"kode resep harus diisi");
        }else{
            try{
                String sql ="insert into header_resep (kode_resep,no_registrasi,nama_pasien,total_harga_resep,tgl_resep,nama_dokter,tanggal_entri)values (?,?,?,?,DATE_FORMAT(NOW(),'%Y-%m-%d'),?,DATE_FORMAT(NOW(),'%Y-%m-%d %T'))";
                pst=con.prepareStatement(sql);
                pst.setString(1, kode_resep1.getText());
                pst.setString(2, no_registrasi.getText());
                pst.setString(3, nama_pasien.getText());
                pst.setString(4, total_harga_resep.getText());
                pst.setString(5, ID);
                pst.execute();
            }catch (SQLException | HeadlessException e){
                System.out.println("Data sudah pernah diinput");
                JOptionPane.showMessageDialog(null,e);
            }
            String value = kode_resep1.getText();
            kode_resep.setText(value);
            jLabel7.setVisible(true);
            jLabel14.setVisible(true);
            jLabel16.setVisible(true);
            kode_obat.setVisible(true);
            jLabel8.setVisible(true);
            jenis_obat.setVisible(true);
            nama_obat.setVisible(true);
            jumlah.setVisible(true);
            add.setVisible(true);
            buat_resep.setVisible(false);
            clear_resep.setVisible(true);
            kode_resep1.setEditable(false);
            jLabel6.setVisible(true);
            jScrollPane5.setVisible(true);
            submit_aturan.setVisible(true);
        }
    }//GEN-LAST:event_buat_resepActionPerformed

    private void clear_resepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_resepActionPerformed
        // TODO add your handling code here:
        clear_resep.setVisible(false);
        kode_resep1.setText("");
        kode_resep1.setEditable(true);
        buat_resep.setVisible(true);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);
        jLabel14.setVisible(false);
        jLabel16.setVisible(false);
        jLabel17.setVisible(false);
        jLabel18.setVisible(false);
        jLabel19.setVisible(false);
        jLabel20.setVisible(false);
        jLabel21.setVisible(false);
        jLabel22.setVisible(false);
        jLabel23.setVisible(false);
        jLabel24.setVisible(false);
        jLabel25.setVisible(false);
        jLabel26.setVisible(false);
        jLabel27.setVisible(false);
        jLabel28.setVisible(false);
        jLabel29.setVisible(false);
        jLabel30.setVisible(false);
        jLabel31.setVisible(false);
        jLabel32.setVisible(false);
        jLabel33.setVisible(false);
        kode_obat.setVisible(false);
        kode_obat1.setVisible(false);
        kode_obat2.setVisible(false);
        kode_obat3.setVisible(false);
        kode_obat4.setVisible(false);
        kode_obat5.setVisible(false);
        jenis_obat.setVisible(false);
        jenis_obat1.setVisible(false);
        jenis_obat2.setVisible(false);
        jenis_obat3.setVisible(false);
        jenis_obat4.setVisible(false);
        jenis_obat5.setVisible(false);
        nama_obat.setVisible(false);
        nama_obat1.setVisible(false);
        nama_obat2.setVisible(false);
        nama_obat3.setVisible(false);
        nama_obat4.setVisible(false);
        nama_obat5.setVisible(false);
        jumlah.setVisible(false);
        jumlah1.setVisible(false);
        jumlah2.setVisible(false);
        jumlah3.setVisible(false);
        jumlah4.setVisible(false);
        jumlah5.setVisible(false);
        add.setVisible(false);
        add1.setVisible(false);
        add2.setVisible(false);
        add3.setVisible(false);
        add4.setVisible(false);
        add5.setVisible(false);
        kode_obat.setText("");
        kode_obat1.setText("");
        kode_obat2.setText("");
        kode_obat3.setText("");
        kode_obat4.setText("");
        kode_obat5.setText("");
        nama_obat.setSelectedItem("");
        nama_obat1.setSelectedItem("");
        nama_obat2.setSelectedItem("");
        nama_obat3.setSelectedItem("");
        nama_obat4.setSelectedItem("");
        nama_obat5.setSelectedItem("");
        jumlah.setText("");
        jumlah1.setText("");
        jumlah2.setText("");
        jumlah3.setText("");
        jumlah4.setText("");
        jumlah5.setText("");
        jScrollPane5.setVisible(false);
        submit_aturan.setVisible(false);
    }//GEN-LAST:event_clear_resepActionPerformed

    private void nama_obatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama_obatActionPerformed
        // TODO add your handling code here:
        try {
            if(jenis_obat.getSelectedItem().equals("Pilih Jenis Obat")){
                kode_obat.setText("");
            }else{
                String sql = "select * from obat where nama_obat='"+this.nama_obat.getSelectedItem()+"'";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    kode_obat.setText(rs.getString("kode_obat"));
                    harga_satuan.setText(rs.getString("harga_satuan"));
                }
            }
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan "+e.getMessage());
        }
    }//GEN-LAST:event_nama_obatActionPerformed

    private void nama_obat5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama_obat5ActionPerformed
        // TODO add your handling code here:
        try {
            if(this.nama_obat5.getSelectedItem().equals("Pilih Obat")){
                kode_obat5.setText("");
            }else{
                String sql = "select * from obat where nama_obat='"+this.nama_obat5.getSelectedItem()+"'";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    kode_obat5.setText(rs.getString("kode_obat"));
                    harga_satuan5.setText(rs.getString("harga_satuan"));
                }
            }
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan "+e.getMessage());
        }
    }//GEN-LAST:event_nama_obat5ActionPerformed

    private void nama_obat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama_obat1ActionPerformed
        // TODO add your handling code here:
        try {
            if(this.nama_obat1.getSelectedItem().equals("Pilih Obat")){
                kode_obat1.setText("");
            }else{
                String view = "select * from obat where nama_obat='"+this.nama_obat1.getSelectedItem()+"'";
                pst = con.prepareStatement(view);
                rs = pst.executeQuery();
                while (rs.next()) {
                    kode_obat1.setText(rs.getString("kode_obat"));
                    harga_satuan1.setText(rs.getString("harga_satuan"));
                }
            }
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan "+e.getMessage());
        }
    }//GEN-LAST:event_nama_obat1ActionPerformed

    private void nama_obat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama_obat2ActionPerformed
        // TODO add your handling code here:
        try {
            if(this.nama_obat2.getSelectedItem().equals("Pilih Obat")){
                kode_obat2.setText("");
            }else{
                String view = "select * from obat where nama_obat='"+this.nama_obat2.getSelectedItem()+"'";
                pst = con.prepareStatement(view);
                rs = pst.executeQuery();
                while (rs.next()) {
                    kode_obat2.setText(rs.getString("kode_obat"));
                    harga_satuan2.setText(rs.getString("harga_satuan"));
                }
            }
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan: "+e.getMessage());
        }
    }//GEN-LAST:event_nama_obat2ActionPerformed

    private void nama_obat3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama_obat3ActionPerformed
        // TODO add your handling code here:
        try {
            if(this.nama_obat3.getSelectedItem().equals("Pilih Obat")){
                kode_obat3.setText("");
            }else{
                String view = "select * from obat where nama_obat='"+this.nama_obat3.getSelectedItem()+"'";
                pst = con.prepareStatement(view);
                rs = pst.executeQuery();
                while (rs.next()) {
                    kode_obat3.setText(rs.getString("kode_obat"));
                    harga_satuan3.setText(rs.getString("harga_satuan"));
                }
            }
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan "+e.getMessage());
        }
    }//GEN-LAST:event_nama_obat3ActionPerformed

    private void nama_obat4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama_obat4ActionPerformed
        // TODO add your handling code here:
        try {
            if(this.nama_obat4.getSelectedItem().equals("Pilih Obat")){
                kode_obat4.setText("");
            }else{
                String view = "select * from obat where nama_obat='"+this.nama_obat4.getSelectedItem()+"'";
                pst = con.prepareStatement(view);
                rs = pst.executeQuery();
                while (rs.next()) {
                    kode_obat4.setText(rs.getString("kode_obat"));
                    harga_satuan4.setText(rs.getString("harga_satuan"));
                }
            }
        }catch(SQLException e) {
            System.out.println("Terjadi kesalahan "+e.getMessage());
        }
    }//GEN-LAST:event_nama_obat4ActionPerformed

    private void submit_aturanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submit_aturanActionPerformed
        // TODO add your handling code here:
        try{
            String resep = kode_resep1.getText();
            String catatan = aturan.getText();
            con.createStatement().execute("update header_resep set aturan_minum ='"+catatan+"' where kode_resep='"+resep+"' ");
            JOptionPane.showMessageDialog(rootPane,"Resep terinput!");
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_submit_aturanActionPerformed

    private void jenis_obatPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jenis_obatPopupMenuWillBecomeInvisible
        // TODO add your handling code here:

    }//GEN-LAST:event_jenis_obatPopupMenuWillBecomeInvisible

    private void jenis_obatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenis_obatActionPerformed
        // TODO add your handling code here:
        try {
            String macam_obat = jenis_obat.getSelectedItem().toString();
            String sql = "select nama_obat from obat where jenis_obat = '"+macam_obat+"' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            Vector v = new Vector();
            while (rs.next()) {
                String item = rs.getString("nama_obat");
                v.add(item);
            }
            nama_obat.setModel(new DefaultComboBoxModel(v));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_jenis_obatActionPerformed

    private void jenis_obat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenis_obat1ActionPerformed
        // TODO add your handling code here:
        try {
            String macam_obat1 = jenis_obat1.getSelectedItem().toString();
            String sql = "select nama_obat from obat where jenis_obat = '"+macam_obat1+"' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            Vector v = new Vector();
            while (rs.next()) {
                String item = rs.getString("nama_obat");
                v.add(item);
            }
            nama_obat1.setModel(new DefaultComboBoxModel(v));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jenis_obat1ActionPerformed

    private void jenis_obat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenis_obat2ActionPerformed
        // TODO add your handling code here:
        try {
            String macam_obat = jenis_obat2.getSelectedItem().toString();
            String sql = "select nama_obat from obat where jenis_obat = '"+macam_obat+"' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            Vector v = new Vector();
            while (rs.next()) {
                String item = rs.getString("nama_obat");
                v.add(item);
            }
            nama_obat2.setModel(new DefaultComboBoxModel(v));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jenis_obat2ActionPerformed

    private void jenis_obat3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenis_obat3ActionPerformed
        // TODO add your handling code here:
        try {
            String macam_obat = jenis_obat3.getSelectedItem().toString();
            String sql = "select nama_obat from obat where jenis_obat = '"+macam_obat+"' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            Vector v = new Vector();
            while (rs.next()) {
                String item = rs.getString("nama_obat");
                v.add(item);
            }
            nama_obat3.setModel(new DefaultComboBoxModel(v));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jenis_obat3ActionPerformed

    private void jenis_obat4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenis_obat4ActionPerformed
        // TODO add your handling code here:
        try {
            String macam_obat = jenis_obat4.getSelectedItem().toString();
            String sql = "select nama_obat from obat where jenis_obat = '"+macam_obat+"' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            Vector v = new Vector();
            while (rs.next()) {
                String item = rs.getString("nama_obat");
                v.add(item);
            }
            nama_obat4.setModel(new DefaultComboBoxModel(v));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jenis_obat4ActionPerformed

    private void jenis_obat5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenis_obat5ActionPerformed
        // TODO add your handling code here:
        try {
            String macam_obat = jenis_obat5.getSelectedItem().toString();
            String sql = "select nama_obat from obat where jenis_obat = '"+macam_obat+"' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            Vector v = new Vector();
            while (rs.next()) {
                String item = rs.getString("nama_obat");
                v.add(item);
            }
            nama_obat5.setModel(new DefaultComboBoxModel(v));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jenis_obat5ActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel2MouseClicked

    private void TabularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabularMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_TabularMouseClicked

    private void tabel_rekamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_rekamMouseClicked
        // TODO add your handling code here:
        try{
            int row = tabel_rekam.getSelectedRow();
            String klik=(tabel_rekam.getModel().getValueAt(row,0).toString());
            String sql = "select nomor_rekam_medis as 'No. Rekam Medis', tgl_rekam_medis as 'Tanggal Rekam Medis', no_registrasi as 'No. Registrasi', nama_pasien as 'Nama Depan', kode_resep as 'Kode Resep', keluhan as Keluhan, tanggal_entri as 'Tanggal Entri', nama_dokter as 'Nama Dokter' from rekam_medis where nomor_rekam_medis='"+klik+"' and bagian = 'umum' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                String add1 = rs.getString("No. Rekam Medis");
                nomor_rekam_medis.setText(add1);
                String add3 = rs.getString("No. Registrasi");
                no_registrasi.setText(add3);
                String add4 = rs.getString("Nama Depan");
                nama_pasien.setText(add4);
                String add8 = rs.getString("Kode Resep");
                kode_resep.setText(add8);
                String add10 = rs.getString("Keluhan");
                keluhan.setText(add10);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tabel_rekamMouseClicked

    private void tabel_rekamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_rekamMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabel_rekamMouseEntered

    private void txtSearchResepKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchResepKeyReleased
        // TODO add your handling code here:
        try{
            String sql = "SELECT a.kode_resep AS 'Kode Resep', b.kode_obat as 'Kode Obat', b.nama_obat AS 'Nama Obat', b.jumlah as Jumlah, b.harga_satuan as 'Harga Obat', a.nama_pasien as 'Nama Pasien', DATE_FORMAT(a.tgl_resep, '%d, %M %Y') as 'Tanggal Resep', a.nama_dokter as 'Nama Dokter' FROM header_resep a, detail_resep b where a.kode_resep = b.kode_resep and a.kode_resep LIKE ? or a.nama_pasien LIKE ? order by a.tgl_resep desc, a.kode_resep, b.tanggal_entri desc";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%"+txtSearchResep.getText()+"%");
            pst.setString(2, "%"+txtSearchResep.getText()+"%");
            rs=pst.executeQuery();
            tabel_resep.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        adjustColumnResep(tabel_resep);
    }//GEN-LAST:event_txtSearchResepKeyReleased

    private void nomor_rekam_medis_lamaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomor_rekam_medis_lamaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_nomor_rekam_medis_lamaKeyReleased

    private void nomor_rekam_medis_lamaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomor_rekam_medis_lamaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nomor_rekam_medis_lamaKeyTyped

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
            java.util.logging.Logger.getLogger(RekamMedis_Bidan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RekamMedis_Bidan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RekamMedis_Bidan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RekamMedis_Bidan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RekamMedis_Bidan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Tabular;
    private javax.swing.JButton add;
    private javax.swing.JButton add1;
    private javax.swing.JButton add2;
    private javax.swing.JButton add3;
    private javax.swing.JButton add4;
    private javax.swing.JButton add5;
    private javax.swing.JTextArea aturan;
    private javax.swing.JButton bClear;
    private javax.swing.JButton bHapus;
    private javax.swing.JButton bSimpan;
    private javax.swing.JButton btn_tindakan;
    private javax.swing.JButton buat_resep;
    private javax.swing.JButton clear_resep;
    private javax.swing.JMenu datetime;
    private javax.swing.JTextField harga_satuan;
    private javax.swing.JTextField harga_satuan1;
    private javax.swing.JTextField harga_satuan2;
    private javax.swing.JTextField harga_satuan3;
    private javax.swing.JTextField harga_satuan4;
    private javax.swing.JTextField harga_satuan5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JComboBox jenis_obat;
    private javax.swing.JComboBox jenis_obat1;
    private javax.swing.JComboBox jenis_obat2;
    private javax.swing.JComboBox jenis_obat3;
    private javax.swing.JComboBox jenis_obat4;
    private javax.swing.JComboBox jenis_obat5;
    private javax.swing.JTextField jumlah;
    private javax.swing.JTextField jumlah1;
    private javax.swing.JTextField jumlah2;
    private javax.swing.JTextField jumlah3;
    private javax.swing.JTextField jumlah4;
    private javax.swing.JTextField jumlah5;
    private javax.swing.JTextArea keluhan;
    private javax.swing.JTextField kode_obat;
    private javax.swing.JTextField kode_obat1;
    private javax.swing.JTextField kode_obat2;
    private javax.swing.JTextField kode_obat3;
    private javax.swing.JTextField kode_obat4;
    private javax.swing.JTextField kode_obat5;
    private javax.swing.JTextField kode_resep;
    private javax.swing.JTextField kode_resep1;
    private javax.swing.JLabel lblCariRekam;
    private javax.swing.JLabel lblCariResep;
    private javax.swing.JComboBox nama_obat;
    private javax.swing.JComboBox nama_obat1;
    private javax.swing.JComboBox nama_obat2;
    private javax.swing.JComboBox nama_obat3;
    private javax.swing.JComboBox nama_obat4;
    private javax.swing.JComboBox nama_obat5;
    private javax.swing.JLabel nama_pasien;
    private javax.swing.JMenu nama_user;
    private javax.swing.JLabel no_antri;
    private javax.swing.JTextField no_registrasi;
    private javax.swing.JLabel no_skrg;
    private javax.swing.JTextField nomor_rekam_medis;
    private javax.swing.JTextField nomor_rekam_medis_lama;
    private javax.swing.JButton submit_aturan;
    private javax.swing.JTable tabel_antri;
    private javax.swing.JTable tabel_rekam;
    private javax.swing.JTable tabel_resep;
    private javax.swing.JLabel tgl_rekam_medis;
    private javax.swing.JLabel tgl_resep;
    private javax.swing.JTextField total_harga_resep;
    private javax.swing.JTextField total_harga_resep1;
    private javax.swing.JTextField total_harga_resep2;
    private javax.swing.JTextField total_harga_resep3;
    private javax.swing.JTextField total_harga_resep4;
    private javax.swing.JTextField total_harga_resep5;
    private javax.swing.JTextField txtSearchRekamMedis;
    private javax.swing.JTextField txtSearchResep;
    // End of variables declaration//GEN-END:variables
}
