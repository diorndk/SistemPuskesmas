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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author AiVarelEzel
 */
public class Pasien extends javax.swing.JFrame {
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String ID = Login.getUserLogin();
    RekamMedis rm = new RekamMedis();
    

    /**
     * Creates new form Pasien
     */
    public Pasien() {
        initComponents();
        this.getContentPane().setBackground(white);
        ImageIcon ico = new ImageIcon("src/KalbisPuskes/gui/logo.png");
        this.setIconImage(ico.getImage());
        con=Connect.ConnectDb();
        rm.setupantri();
        setuptable();
        auto_number();
        bGroupJk.add(rbLaki);
        bGroupJk.add(rbPerempuan);
        bGroupGol.add(rbA);
        bGroupGol.add(rbB);
        bGroupGol.add(rbO);
        bGroupGol.add(rbAB);
        bGroupGol.add(rbOther);
        nama_user.setText("Login as: "+ID);
        TanggalSekarang();
        jMenuBar1.add(Box.createHorizontalGlue());
        jMenuBar1.add(datetime);
        jMenuBar1.add(nama_user);
        no_antri.setVisible(false);
        generate_regis();
        autorefreshregis();
        no_registrasi.setEditable(false);
        this.setSize(1384,624);
        no_registrasi1.setVisible(false);
        no_registrasi1.setEditable(false);
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
    
    
    public void generate_regis(){
       /* try{
            String tanggallahir = ((JTextField)tanggal_lahir.getDateEditor().getUiComponent()).getText();
            if(!tanggallahir.equals("")){
                SimpleDateFormat formatlama = new SimpleDateFormat("dd MMMM yyyy",Locale.ENGLISH);
                SimpleDateFormat formatbaru = new SimpleDateFormat("dd-mm-yyyy");
                Date tanggalan = (Date) formatlama.parse(tanggallahir);
                String tanggalbaru = formatbaru.format(tanggalan);
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
                    no_registrasi.setText(tanggalbaru);
                }else{
                    no_registrasi.setText(tanggalbaru);
                }
            }else{
                System.out.println("kosong");
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this,e);
        }*/
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
            String stringyear = String.valueOf(tahun);
            String tahuns = stringyear.substring(2);
            String sql = "SELECT no_registrasi FROM pasien where DATE_FORMAT(tanggal_entri,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') order by no_registrasi desc LIMIT 1 ";   
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {                
                String kode_lama = rs.getString("no_registrasi").substring(11);
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
                no_registrasi.setText("P"+hr.format(d)+mnt.format(d)+tahuns+bulans+tgl+nol+kode_lama_int);
            }else{
                no_registrasi.setText("P"+hr.format(d)+mnt.format(d)+tahuns+bulans+tgl+"0001");
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this,"rusak "+e);
        }
    }
            
    public void auto_number(){
        try{
            Calendar cal = new GregorianCalendar();
            int tanggal = cal.get(Calendar.DAY_OF_MONTH);
            String antri = bagian.getSelectedItem().toString();
            String sql = "select no_antrian from antrian where tanggal = '"+tanggal+"' and bagian ='"+antri+"' ORDER BY no_antrian DESC LIMIT 1";
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
            JOptionPane.showMessageDialog(this,"ancur "+e);
        }
    }
    
    public void autorefresh(){
        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               auto_number();
            }
        });
        timer.setDelay(1000); // delay for 30 seconds
        timer.start();
    }
    
    public void autorefreshregis(){
        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               generate_regis();
            }
        });
        timer.setDelay(1000); // delay for 30 seconds
        timer.start();
    }
    
    public void setuptable(){
        try
        {
            String sql = "select no_registrasi as 'No. Registrasi', nama_pasien as 'Nama Pasien', tempat_lahir as 'Tempat Lahir', DATE_FORMAT(tanggal_lahir, '%d %M %Y') as 'Tanggal Lahir', jk_pasien as 'Jenis Kelamin', gol_darah as 'Golongan Darah', alamat as 'Alamat', no_telp as 'No. Telp', no_bpjs as 'No. BPJS', DATE_FORMAT(tanggal_entri, '%d %M %Y') as 'Tanggal Masuk', nama_pengentri as 'Admin' from pasien order by tanggal_entri desc";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tabel_pasien.setModel(DbUtils.resultSetToTableModel(rs));
            //To remove previously added rows            
        }
        catch(  SQLException e)
        {
           JOptionPane.showMessageDialog(null, "rusak bos "+e.toString());
        }
        adjustColumn(tabel_pasien);
    }
    
    public void adjustColumn(JTable tabel_pasien){
     TableColumnModel modelKolom = tabel_pasien.getColumnModel();
     for(int kol=0; kol < modelKolom.getColumnCount(); kol++){
    		int lebarKolomMax=0;
    		for(int baris=0;baris<tabel_pasien.getRowCount();baris++){
    			TableCellRenderer rend=tabel_pasien.getCellRenderer(baris,kol);
    			Object nilaiTablel=tabel_pasien.getValueAt(baris,kol);
    			Component comp=rend.getTableCellRendererComponent(tabel_pasien,nilaiTablel,false,false,baris,kol);
				lebarKolomMax=Math.max(comp.getPreferredSize().width,lebarKolomMax);
    		}//akhir for baris
    		TableColumn kolom=modelKolom.getColumn(kol);
    		kolom.setPreferredWidth(lebarKolomMax);
    	}//akhir for kolom
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bGroupJk = new javax.swing.ButtonGroup();
        bGroupGol = new javax.swing.ButtonGroup();
        tabel_pasien1 = new javax.swing.JTable();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        bUbah = new javax.swing.JButton();
        bHapus = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        no_registrasi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nama_pasien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tempat_lahir = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        rbA = new javax.swing.JRadioButton();
        rbB = new javax.swing.JRadioButton();
        rbO = new javax.swing.JRadioButton();
        rbAB = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        alamat = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        no_telp = new javax.swing.JTextField();
        bClear = new javax.swing.JButton();
        bSimpan = new javax.swing.JButton();
        tanggal_lahir = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_pasien = new javax.swing.JTable();
        txtSearchPasien = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        bagian = new javax.swing.JComboBox();
        bAntri = new javax.swing.JButton();
        no_antri = new javax.swing.JLabel();
        rbOther = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        orang_terdekat = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        no_bpjs = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        hubungan = new javax.swing.JTextField();
        no_registrasi1 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        datetime = new javax.swing.JMenu();
        nama_user = new javax.swing.JMenu();

        tabel_pasien1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No. Registrasi", "Nama Pasien", "Tempat Lahir", "Tanggal Lahir", "Jenis Kelamin", "Golongan Darah", "Alamat", "No. Telepon"
            }
        ));

        jMenuItem3.setText("jMenuItem3");

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu6.setText("Edit");
        jMenuBar2.add(jMenu6);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Data Pasien");
        getContentPane().setLayout(null);

        bUbah.setText("UBAH");
        bUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahActionPerformed(evt);
            }
        });
        getContentPane().add(bUbah);
        bUbah.setBounds(511, 503, 72, 37);

        bHapus.setText("HAPUS");
        bHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusActionPerformed(evt);
            }
        });
        getContentPane().add(bHapus);
        bHapus.setBounds(601, 503, 73, 37);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("No. Registrasi");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(17, 38, 82, 24);

        no_registrasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                no_registrasiKeyTyped(evt);
            }
        });
        getContentPane().add(no_registrasi);
        no_registrasi.setBounds(17, 68, 150, 24);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Nama Pasien");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(185, 42, 77, 17);
        getContentPane().add(nama_pasien);
        nama_pasien.setBounds(185, 68, 174, 24);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tempat Lahir");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(17, 104, 80, 17);

        tempat_lahir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tempat_lahirActionPerformed(evt);
            }
        });
        getContentPane().add(tempat_lahir);
        tempat_lahir.setBounds(17, 127, 150, 24);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Tanggal Lahir");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(185, 104, 81, 17);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Jenis Kelamin");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(17, 168, 79, 17);

        rbLaki.setText("Laki laki");
        rbLaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLakiActionPerformed(evt);
            }
        });
        getContentPane().add(rbLaki);
        rbLaki.setBounds(17, 191, 79, 28);

        rbPerempuan.setText("Perempuan");
        rbPerempuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPerempuanActionPerformed(evt);
            }
        });
        getContentPane().add(rbPerempuan);
        rbPerempuan.setBounds(17, 225, 98, 28);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Golongan Darah");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(185, 168, 99, 17);

        rbA.setText("A");
        rbA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAActionPerformed(evt);
            }
        });
        getContentPane().add(rbA);
        rbA.setBounds(185, 191, 40, 28);

        rbB.setText("B");
        rbB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbBActionPerformed(evt);
            }
        });
        getContentPane().add(rbB);
        rbB.setBounds(231, 191, 40, 28);

        rbO.setText("O");
        rbO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOActionPerformed(evt);
            }
        });
        getContentPane().add(rbO);
        rbO.setBounds(185, 225, 41, 28);

        rbAB.setText("AB");
        rbAB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbABActionPerformed(evt);
            }
        });
        getContentPane().add(rbAB);
        rbAB.setBounds(232, 225, 48, 28);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Alamat");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(17, 265, 41, 17);

        alamat.setColumns(20);
        alamat.setRows(5);
        jScrollPane2.setViewportView(alamat);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(17, 288, 342, 83);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("No. Tlp/Hp");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(17, 383, 65, 17);

        no_telp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                no_telpKeyTyped(evt);
            }
        });
        getContentPane().add(no_telp);
        no_telp.setBounds(17, 406, 127, 24);

        bClear.setText("CLEAR");
        bClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearActionPerformed(evt);
            }
        });
        getContentPane().add(bClear);
        bClear.setBounds(692, 503, 83, 37);

        bSimpan.setText("SIMPAN");
        bSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(bSimpan);
        bSimpan.setBounds(418, 503, 75, 37);

        tanggal_lahir.setDateFormatString("dd MMMM yyyy");
        tanggal_lahir.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tanggal_lahirPropertyChange(evt);
            }
        });
        getContentPane().add(tanggal_lahir);
        tanggal_lahir.setBounds(185, 127, 174, 29);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tabel_pasien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Registrasi", "Nama Depan Pasien", "Nama Belakang Pasien"
            }
        ));
        tabel_pasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_pasienMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabel_pasienMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_pasien);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(377, 38, 989, 432);

        txtSearchPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchPasienActionPerformed(evt);
            }
        });
        txtSearchPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchPasienKeyReleased(evt);
            }
        });
        getContentPane().add(txtSearchPasien);
        txtSearchPasien.setBounds(1207, 2, 159, 24);

        jLabel12.setText("Cari:");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(1175, 0, 26, 26);

        bagian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Umum", "Gigi", "Bidan" }));
        bagian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bagianActionPerformed(evt);
            }
        });
        getContentPane().add(bagian);
        bagian.setBounds(17, 509, 171, 26);

        bAntri.setText("Antrian Baru");
        bAntri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAntriActionPerformed(evt);
            }
        });
        getContentPane().add(bAntri);
        bAntri.setBounds(206, 504, 101, 37);

        no_antri.setText("A");
        getContentPane().add(no_antri);
        no_antri.setBounds(289, 547, 8, 16);

        rbOther.setText("Other");
        rbOther.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOtherActionPerformed(evt);
            }
        });
        getContentPane().add(rbOther);
        rbOther.setBounds(277, 191, 64, 28);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Nama Orang Terdekat");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(170, 383, 135, 17);

        orang_terdekat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orang_terdekatActionPerformed(evt);
            }
        });
        orang_terdekat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                orang_terdekatKeyTyped(evt);
            }
        });
        getContentPane().add(orang_terdekat);
        orang_terdekat.setBounds(170, 406, 189, 24);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("No. BPJS");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(17, 442, 55, 17);

        no_bpjs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                no_bpjsKeyTyped(evt);
            }
        });
        getContentPane().add(no_bpjs);
        no_bpjs.setBounds(17, 467, 116, 24);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Hubungan");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(170, 442, 64, 17);

        hubungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hubunganActionPerformed(evt);
            }
        });
        hubungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hubunganKeyTyped(evt);
            }
        });
        getContentPane().add(hubungan);
        hubungan.setBounds(170, 467, 189, 24);

        no_registrasi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                no_registrasi1KeyTyped(evt);
            }
        });
        getContentPane().add(no_registrasi1);
        no_registrasi1.setBounds(17, 68, 150, 24);

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

        jMenuItem2.setText("Home");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem4.setText("Antrian");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        datetime.setText("Date");
        jMenuBar1.add(datetime);

        nama_user.setText("user");
        jMenuBar1.add(nama_user);

        setJMenuBar(jMenuBar1);

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
                String value1 = no_registrasi1.getText();
                String value2 = nama_pasien.getText();
                String value4 = tempat_lahir.getText();
                String value5 = ((JTextField)tanggal_lahir.getDateEditor().getUiComponent()).getText();
                String value6 = jk;
                String value7 = gol;
                String value8 = alamat.getText();
                String value9 = no_telp.getText();
                String org_dkt = orang_terdekat.getText();
                String hub = hubungan.getText();
                String bpjs = no_bpjs.getText();
                String value10= ID;
                con.createStatement().execute("update pasien set no_registrasi ='"+value1+"', nama_pasien='"+value2+"',tempat_lahir='"+value4+"', tanggal_lahir = STR_TO_DATE('"+value5+"','%d %M %Y'), jk_pasien='"+value6+"', gol_darah='"+value7+"', alamat='"+value8+"', no_telp='"+value9+"', orang_terdekat='"+org_dkt+"', hubungan='"+hub+"', no_bpjs='"+bpjs+"', tanggal_entri = DATE_FORMAT(NOW(),'%Y-%m-%d %T'), nama_pengentri = '"+value10+"'where no_registrasi='"+value1+"' ");
                JOptionPane.showMessageDialog(rootPane,"Data Terupdate!");            
            }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null,e);
            }
        setuptable();
        }
    }//GEN-LAST:event_bUbahActionPerformed

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        // TODO add your handling code here:
        int p = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data?","Hapus Data",JOptionPane.YES_NO_OPTION);
        if(p==0){
                String sql = "delete from pasien where no_registrasi=?";
            try{
                pst = con.prepareStatement(sql);
                pst.setString(1, no_registrasi1.getText());
                pst.execute();
                JOptionPane.showMessageDialog(null,"Data Terhapus");
            }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null,e);
            }
            setuptable();
        }
        
    }//GEN-LAST:event_bHapusActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
            new Home().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearActionPerformed
        // TODO add your handling code here:
        no_registrasi.setVisible(true);
        no_registrasi1.setVisible(false);
        no_registrasi1.setText("");
        nama_pasien.setText("");
        tempat_lahir.setText("");
        ((JTextField)tanggal_lahir.getDateEditor().getUiComponent()).setText("");
        alamat.setText("");
        no_telp.setText("");
        bGroupJk.clearSelection();
        bGroupGol.clearSelection();
        no_bpjs.setText("");
        hubungan.setText("");
        orang_terdekat.setText("");
    }//GEN-LAST:event_bClearActionPerformed

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        // TODO add your handling code here:
        try{
            con.createStatement().execute("insert into pasien values('"+no_registrasi.getText()+"','"+nama_pasien.getText()+"','"+tempat_lahir.getText()+"',STR_TO_DATE('"+((JTextField)tanggal_lahir.getDateEditor().getUiComponent()).getText()+"','%d %M %Y'),'"+jk+"','"+gol+"','"+alamat.getText()+"','"+no_telp.getText()+"','"+orang_terdekat.getText()+"','"+hubungan.getText()+"','"+no_bpjs.getText()+"',DATE_FORMAT(NOW(),'%Y-%m-%d %T'),'"+ID+"')");
            JOptionPane.showMessageDialog(rootPane, "Data Tersimpan!");
        }catch (SQLException | HeadlessException e){
            System.out.println("Data sudah pernah diinput");
            JOptionPane.showMessageDialog(null,e);
        }
        setuptable();
        try{    
            Calendar cal = new GregorianCalendar();
            String value1 = no_registrasi.getText();
            String value2 = nama_pasien.getText();
            String value3 = bagian.getSelectedItem().toString();
            String value4= ID;
            String value5 = no_antri.getText();
            int tanggal = cal.get(Calendar.DAY_OF_MONTH);
            con.createStatement().execute("insert into antrian (no_antrian,no_registrasi,nama_pasien,bagian,tanggal,flag_active,flag_skip,tanggal_entri,nama_pengentri) values ('"+value5+"', '"+value1+"', '"+value2+"','"+value3+"','"+tanggal+"','Y','N',DATE_FORMAT(NOW(),'%Y-%m-%d %T'), '"+value4+"') ");
            auto_number();
            JOptionPane.showMessageDialog(rootPane,"Antrian Terupdate");            
        }catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,e);
        }
        auto_number();
        rm.setupantri();
    }//GEN-LAST:event_bSimpanActionPerformed

    private void rbLakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLakiActionPerformed
        // TODO add your handling code here:
        jk = "L";
    }//GEN-LAST:event_rbLakiActionPerformed

    private void rbPerempuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPerempuanActionPerformed
        // TODO add your handling code here:
        jk = "P";
    }//GEN-LAST:event_rbPerempuanActionPerformed

    private void rbAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAActionPerformed
        // TODO add your handling code here:
        gol = "A";
    }//GEN-LAST:event_rbAActionPerformed

    private void rbBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbBActionPerformed
        // TODO add your handling code here:
        gol = "B";
    }//GEN-LAST:event_rbBActionPerformed

    private void rbOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbOActionPerformed
        // TODO add your handling code here:
        gol = "O";
    }//GEN-LAST:event_rbOActionPerformed

    private void rbABActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbABActionPerformed
        // TODO add your handling code here:
        gol = "AB";
    }//GEN-LAST:event_rbABActionPerformed

    private void tabel_pasienMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pasienMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabel_pasienMouseEntered

    private void tabel_pasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pasienMouseClicked
        // TODO add your handling code here:
        try{
            no_registrasi.setVisible(false);
            no_registrasi1.setVisible(true);
            int row = tabel_pasien.getSelectedRow();
            String klik=(tabel_pasien.getModel().getValueAt(row,0).toString());
            String sql = "select no_registrasi as 'No. Registrasi', nama_pasien as 'Nama Pasien', tempat_lahir as 'Tempat Lahir', tanggal_lahir as 'Tanggal Lahir', jk_pasien as 'Jenis Kelamin', gol_darah as 'Golongan Darah', alamat as 'Alamat', no_telp as 'No. Telp', orang_terdekat as 'Orang Terdekat', hubungan as Hubungan, no_bpjs as 'No. BPJS' from pasien where no_registrasi='"+klik+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                String add1 = rs.getString("No. Registrasi");
                no_registrasi1.setText(add1);
                String add2 = rs.getString("Nama Pasien");
                nama_pasien.setText(add2);
                String add3 = rs.getString("Tempat Lahir");
                tempat_lahir.setText(add3);
                Date add4 = rs.getDate("Tanggal Lahir");
                tanggal_lahir.setDate(add4);
                String add5 = rs.getString("Jenis Kelamin");
                switch(add5){
                    case"L":
                        rbLaki.setSelected(true);
                        jk="L";
                        break;
                    case"P":
                        rbPerempuan.setSelected(true);
                        jk="P";
                        break;
                }
                String add6 = rs.getString("Golongan Darah");
                switch(add6){
                    case"A":
                        rbA.setSelected(true);
                        gol="A";
                        break;
                    case"B":
                        rbB.setSelected(true);
                        gol="B";
                        break;
                    case"O":
                        rbO.setSelected(true);
                        gol="O";
                        break;
                    case"AB":
                        rbAB.setSelected(true);
                        gol="AB";
                        break;
                    case"Other":
                        rbOther.setSelected(true);
                        gol="Other";
                        break;
                }
                String add7 = rs.getString("Alamat");
                alamat.setText(add7);
                String add8 = rs.getString("No. Telp");
                no_telp.setText(add8);
                orang_terdekat.setText(rs.getString("Orang Terdekat"));
                hubungan.setText(rs.getString("Hubungan"));
                no_bpjs.setText(rs.getString("No. BPJS"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tabel_pasienMouseClicked

    private void txtSearchPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchPasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchPasienActionPerformed

    private void txtSearchPasienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchPasienKeyReleased
        // TODO add your handling code here:
        try{            
            String sql = "select no_registrasi as 'No. Registrasi', nama_pasien as 'Nama Pasien', tempat_lahir as 'Tempat Lahir', DATE_FORMAT(tanggal_lahir, '%d %M %Y') as 'Tanggal Lahir', jk_pasien as 'Jenis Kelamin', gol_darah as 'Golongan Darah', alamat as 'Alamat', no_telp as 'No. Telp', no_bpjs as 'No. BPJS', DATE_FORMAT(tanggal_entri, '%d %M %Y') as 'Tanggal Daftar', nama_pengentri as 'Admin' from pasien where no_registrasi LIKE ? or nama_pasien LIKE ? or no_bpjs LIKE ?";            
            pst = con.prepareStatement(sql);
            pst.setString(1, "%"+txtSearchPasien.getText()+"%");
            pst.setString(2, "%"+txtSearchPasien.getText()+"%");
            pst.setString(3, "%"+txtSearchPasien.getText()+"%");
            
            rs=pst.executeQuery();
            tabel_pasien.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);            
        }
        adjustColumn(tabel_pasien);
    }//GEN-LAST:event_txtSearchPasienKeyReleased

    private void no_registrasiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_no_registrasiKeyTyped
        // TODO add your handling code here:
        if(no_registrasi.getText().length()>=15){  
           evt.consume();
        }
    }//GEN-LAST:event_no_registrasiKeyTyped

    private void no_telpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_no_telpKeyTyped
        // TODO add your handling code here:
        if(no_telp.getText().length()>=12){  
           evt.consume();
        }
    }//GEN-LAST:event_no_telpKeyTyped

    private void tempat_lahirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tempat_lahirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tempat_lahirActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
       new Antrian_Umum().setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void bAntriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAntriActionPerformed
        // TODO add your handling code here:
        try{    
            Calendar cal = new GregorianCalendar();
            String value1 = no_registrasi1.getText();
            String value2 = nama_pasien.getText();
            String value3 = bagian.getSelectedItem().toString();
            String value4= ID;
            String value5 = no_antri.getText();
            int tanggal = cal.get(Calendar.DAY_OF_MONTH);
            con.createStatement().execute("insert into antrian (no_antrian,no_registrasi,nama_pasien,bagian,tanggal,flag_active,flag_skip,tanggal_entri,nama_pengentri) values ('"+value5+"', '"+value1+"', '"+value2+"','"+value3+"','"+tanggal+"','Y','N',DATE_FORMAT(NOW(),'%Y-%m-%d %T'), '"+value4+"') ");
            auto_number();
            JOptionPane.showMessageDialog(rootPane,"Antrian Terupdate");            
        }catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,e);
        }
        auto_number();
        rm.setupantri();
    }//GEN-LAST:event_bAntriActionPerformed

    private void bagianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bagianActionPerformed
        // TODO add your handling code here:
        auto_number();
    }//GEN-LAST:event_bagianActionPerformed

    private void rbOtherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbOtherActionPerformed
        // TODO add your handling code here:
        gol = "Other";
    }//GEN-LAST:event_rbOtherActionPerformed

    private void orang_terdekatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_orang_terdekatKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_orang_terdekatKeyTyped

    private void no_bpjsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_no_bpjsKeyTyped
        // TODO add your handling code here:
        if(no_bpjs.getText().length()>=13){  
           evt.consume();
        }
    }//GEN-LAST:event_no_bpjsKeyTyped

    private void orang_terdekatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orang_terdekatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orang_terdekatActionPerformed

    private void hubunganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hubunganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hubunganActionPerformed

    private void hubunganKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hubunganKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_hubunganKeyTyped

    private void tanggal_lahirPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tanggal_lahirPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggal_lahirPropertyChange

    private void no_registrasi1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_no_registrasi1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_no_registrasi1KeyTyped

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
            java.util.logging.Logger.getLogger(Pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pasien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamat;
    private javax.swing.JButton bAntri;
    private javax.swing.JButton bClear;
    private javax.swing.ButtonGroup bGroupGol;
    private javax.swing.ButtonGroup bGroupJk;
    private javax.swing.JButton bHapus;
    private javax.swing.JButton bSimpan;
    private javax.swing.JButton bUbah;
    private javax.swing.JComboBox bagian;
    private javax.swing.JMenu datetime;
    private javax.swing.JTextField hubungan;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nama_pasien;
    private javax.swing.JMenu nama_user;
    private javax.swing.JLabel no_antri;
    private javax.swing.JTextField no_bpjs;
    private javax.swing.JTextField no_registrasi;
    private javax.swing.JTextField no_registrasi1;
    private javax.swing.JTextField no_telp;
    private javax.swing.JTextField orang_terdekat;
    private javax.swing.JRadioButton rbA;
    private javax.swing.JRadioButton rbAB;
    private javax.swing.JRadioButton rbB;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbO;
    private javax.swing.JRadioButton rbOther;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JTable tabel_pasien;
    private javax.swing.JTable tabel_pasien1;
    private com.toedter.calendar.JDateChooser tanggal_lahir;
    private javax.swing.JTextField tempat_lahir;
    private javax.swing.JTextField txtSearchPasien;
    // End of variables declaration//GEN-END:variables
private String jk;
private String gol;
}
