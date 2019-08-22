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
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author AiVarelEzel
 */
public class Obat extends javax.swing.JFrame {
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String ID = Login.getUserLogin();

    /**
     * Creates new form Obat
     */
    public Obat() {
        initComponents();
        con=Connect.ConnectDb();
        setuptable();
        this.setLocationRelativeTo(null);
        nama_user.setText("Login as: "+ID);
        adjustColumn(tabel_obat);
        TanggalSekarang();
        jMenuBar1.add(Box.createHorizontalGlue());
        jMenuBar1.add(datetime);
        jMenuBar1.add(nama_user);
        autorefresh();
        //autorefreshcode();
        UpperCasedTextFieldTester();
        kode_obat.setVisible(true);
        kode_obat.setEditable(false);
        //auto_code();
        kode_obat1.setVisible(false);
        kode_obat1.setEditable(false);
        kodeobat.setVisible(true);
        this.getContentPane().setBackground(white);
        ImageIcon ico = new ImageIcon("src/KalbisPuskes/gui/logo.png");
        this.setIconImage(ico.getImage());
    }
    
    public void UpperCasedTextFieldTester(){
        
        DocumentFilter filter = new UppercaseDocumentFilter();

        kode_obat.setPreferredSize(new Dimension(100, 20));
        kodeobat.setPreferredSize(new Dimension(100, 20));
        ((AbstractDocument) kode_obat.getDocument()).setDocumentFilter(filter);
        ((AbstractDocument) kodeobat.getDocument()).setDocumentFilter(filter);

    }
     
    class UppercaseDocumentFilter extends DocumentFilter {
        public void insertString(DocumentFilter.FilterBypass fb, int offset,
                String text, AttributeSet attr) throws BadLocationException {

            fb.insertString(offset, text.toUpperCase(), attr);
        }

        public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
                String text, AttributeSet attrs) throws BadLocationException {

            fb.replace(offset, length, text.toUpperCase(), attrs);
        }
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
    
    public void auto_code(){
        try{
            String nama_jenis = jenis_obat.getSelectedItem().toString();
            String nama_substr = nama_jenis.substring(0,3);
            String sql = "select kode_obat from obat where jenis_obat = '"+nama_jenis+"' order by kode_obat desc LIMIT 1 ";   
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {                
                String kode_lama = rs.getString("kode_obat").substring(3);
                String kode_lama_int = ""+ (Integer.parseInt(kode_lama)+1);
                String nol= "";
                if(nama_jenis.equals("Pilih Jenis")){
                    kode_obat.setText("");
                    nama_obat.setText("");
                    kodeobat.setText("");
                }else if(nama_jenis.equals("Tablet")){
                    if(kode_lama_int.length()==1){
                        nol="00";
                    } else if(kode_lama_int.length()==2){
                        nol="0";
                    } else if(kode_lama_int.length()==3){
                        nol="";
                    }
                    kode_obat.setText(nama_substr+nol+kode_lama_int);
                    kodeobat.setText(nama_substr+nol+kode_lama_int);
                }else if(nama_jenis.equals("Vitamin")){
                    if(kode_lama_int.length()==1){
                        nol="00";
                    } else if(kode_lama_int.length()==2){
                        nol="0";
                    } else if(kode_lama_int.length()==3){
                        nol="";
                    }
                    kode_obat.setText(nama_substr+nol+kode_lama_int);
                    kodeobat.setText(nama_substr+nol+kode_lama_int);
                }else if(nama_jenis.equals("Pil")){
                    if(kode_lama_int.length()==1){
                        nol="00";
                    } else if(kode_lama_int.length()==2){
                        nol="0";
                    } else if(kode_lama_int.length()==3){
                        nol="";
                    }
                    kode_obat.setText(nama_substr+nol+kode_lama_int);
                    kodeobat.setText(nama_substr+nol+kode_lama_int);
                }else if(nama_jenis.equals("PKB")){
                    if(kode_lama_int.length()==1){
                        nol="00";
                    } else if(kode_lama_int.length()==2){
                        nol="0";
                    } else if(kode_lama_int.length()==3){
                        nol="";
                    }
                    kode_obat.setText(nama_substr+nol+kode_lama_int);
                    kodeobat.setText(nama_substr+nol+kode_lama_int);
                }else if(nama_jenis.equals("Sirup")){
                    if(kode_lama_int.length()==1){
                        nol="00";
                    } else if(kode_lama_int.length()==2){
                        nol="0";
                    } else if(kode_lama_int.length()==3){
                        nol="";
                    }
                    kode_obat.setText(nama_substr+nol+kode_lama_int);
                    kodeobat.setText(nama_substr+nol+kode_lama_int);
                }else if(nama_jenis.equals("Salep")){
                    if(kode_lama_int.length()==1){
                        nol="00";
                    } else if(kode_lama_int.length()==2){
                        nol="0";
                    } else if(kode_lama_int.length()==3){
                        nol="";
                    }
                    kode_obat.setText(nama_substr+nol+kode_lama_int);
                    kodeobat.setText(nama_substr+nol+kode_lama_int);
                }else if(nama_jenis.equals("Lain-lain")){
                    if(kode_lama_int.length()==1){
                        nol="00";
                    } else if(kode_lama_int.length()==2){
                        nol="0";
                    } else if(kode_lama_int.length()==3){
                        nol="";
                    }
                    kode_obat.setText(nama_substr+nol+kode_lama_int);
                    kodeobat.setText(nama_substr+nol+kode_lama_int);
                }
            }else if(!nama_jenis.equals("Pilih Jenis")){
                    kode_obat.setText(nama_substr+"001");
                    kodeobat.setText(nama_substr+"001");
                }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this,e);
        }
    }
    
    public void autorefresh(){
        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               setuptable();
            }
        });
        timer.setDelay(30000); // delay for 30 seconds
        timer.start();
    }
    
   /* public void autorefreshcode(){
        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               auto_code();
            }
        });
        timer.setDelay(1000); // delay for 30 seconds
        timer.start();
    }*/
    
    public void kurang(){
        
    }
    
    public void setuptable(){
        try
        {
            String sql = "select kode_obat as 'Kode Obat', nama_obat as 'Nama Obat', jenis_obat as 'Jenis Obat', harga_satuan as 'Harga', stok as Stok, tanggal_entri as 'Tanggal Entri', nama_pengentri as 'Nama Pengentri' from obat";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tabel_obat.setModel(DbUtils.resultSetToTableModel(rs));
            //To remove previously added rows
        }
        catch(  SQLException e)
        {
           JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    
    public void adjustColumn(JTable tabel_obat){
     TableColumnModel modelKolom = tabel_obat.getColumnModel();
     for(int kol=0; kol < modelKolom.getColumnCount(); kol++){
    		int lebarKolomMax=0;
    		for(int baris=0;baris<tabel_obat.getRowCount();baris++){
    			TableCellRenderer rend=tabel_obat.getCellRenderer(baris,kol);
    			Object nilaiTablel=tabel_obat.getValueAt(baris,kol);
    			Component comp=rend.getTableCellRendererComponent(tabel_obat,nilaiTablel,false,false,baris,kol);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_obat = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        kode_obat = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nama_obat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jenis_obat = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        harga_satuan = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        stok = new javax.swing.JTextField();
        bSimpan = new javax.swing.JButton();
        bUbah = new javax.swing.JButton();
        bHapus = new javax.swing.JButton();
        bClear = new javax.swing.JButton();
        txtSearchObat = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        kode_obat1 = new javax.swing.JTextField();
        kodeobat = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        datetime = new javax.swing.JMenu();
        nama_user = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Data Obat");

        jPanel1.setLayout(null);

        tabel_obat.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_obat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_obatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_obat);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(14, 36, 887, 230);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Kode Obat");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(14, 276, 59, 15);

        kode_obat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kode_obatKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kode_obatKeyTyped(evt);
            }
        });
        jPanel1.add(kode_obat);
        kode_obat.setBounds(14, 293, 190, 24);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Nama Obat");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(294, 276, 61, 15);
        jPanel1.add(nama_obat);
        nama_obat.setBounds(300, 294, 190, 24);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Jenis Obat");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(14, 330, 57, 15);

        jenis_obat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Jenis", "Tablet", "Vitamin", "Pil", "PKB", "Sirup", "Salep", "Lain-lain" }));
        jenis_obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenis_obatActionPerformed(evt);
            }
        });
        jPanel1.add(jenis_obat);
        jenis_obat.setBounds(14, 351, 170, 26);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Harga Satuan");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(300, 330, 73, 15);
        jPanel1.add(harga_satuan);
        harga_satuan.setBounds(340, 350, 126, 24);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Stok");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(515, 330, 25, 15);
        jPanel1.add(stok);
        stok.setBounds(515, 351, 60, 24);

        bSimpan.setText("SIMPAN");
        bSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(bSimpan);
        bSimpan.setBounds(306, 428, 92, 39);

        bUbah.setText("UPDATE");
        bUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahActionPerformed(evt);
            }
        });
        jPanel1.add(bUbah);
        bUbah.setBounds(427, 428, 92, 39);

        bHapus.setText("HAPUS");
        bHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusActionPerformed(evt);
            }
        });
        jPanel1.add(bHapus);
        bHapus.setBounds(561, 428, 92, 39);

        bClear.setText("CLEAR");
        bClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearActionPerformed(evt);
            }
        });
        jPanel1.add(bClear);
        bClear.setBounds(686, 428, 92, 39);

        txtSearchObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchObatActionPerformed(evt);
            }
        });
        txtSearchObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchObatKeyReleased(evt);
            }
        });
        jPanel1.add(txtSearchObat);
        txtSearchObat.setBounds(709, 6, 192, 24);

        jLabel8.setText("Cari:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(671, 10, 26, 16);

        jLabel1.setText("Rp.");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(300, 355, 40, 16);

        kode_obat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kode_obat1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kode_obat1KeyTyped(evt);
            }
        });
        jPanel1.add(kode_obat1);
        kode_obat1.setBounds(14, 293, 190, 24);

        kodeobat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodeobatActionPerformed(evt);
            }
        });
        jPanel1.add(kodeobat);
        kodeobat.setBounds(700, 310, 100, 24);

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

        jMenuItem6.setText("Kasir");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

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
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 911, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        // TODO add your handling code here:
        try{
            String sql ="insert into obat (kode_obat,nama_obat,jenis_obat,harga_satuan,stok,tanggal_entri,nama_pengentri) values (?,?,?,?,?,DATE_FORMAT(NOW(),'%Y-%m-%d %T'),?)";
            pst=con.prepareStatement(sql);
            pst.setString(1, kode_obat.getText());
            pst.setString(2, nama_obat.getText());
            String value=jenis_obat.getSelectedItem().toString();
            pst.setString(3, value);
            pst.setString(4, harga_satuan.getText());
            pst.setString(5, stok.getText());
            pst.setString(6, ID);
            pst.execute();
            JOptionPane.showMessageDialog(rootPane, "Data Tersimpan!");
        }catch (SQLException | HeadlessException e){
           System.out.println("Data sudah pernah diinput");
            JOptionPane.showMessageDialog(null,"Data yang diinput belum lengkap! / Data sudah pernah diinput! / tidak boleh input data yang sama!");
        }
        setuptable();
        auto_code();
    }//GEN-LAST:event_bSimpanActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
            new Login().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        // TODO add your handling code here:
    int p = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data?","Hapus Data",JOptionPane.YES_NO_OPTION);
    if(p==0){
        String sql = "delete from obat where kode_obat=?";
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, kode_obat1.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Data Terhapus");
        }catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,e);
        }
        setuptable();
    }
    }//GEN-LAST:event_bHapusActionPerformed

    private void bUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahActionPerformed
        // TODO add your handling code here:
    int p = JOptionPane.showConfirmDialog(null, "Anda yakin ingin mengubah data?","Update Data",JOptionPane.YES_NO_OPTION);
    if(p==0){
        try{
            String sql = "select * from obat where kode_obat ='"+kode_obat1.getText()+"'";
            int stoklama = 0;
            int hasil = 0;
            int stokbaru = parseInt(stok.getText());
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                stoklama = rs.getInt("stok");
            }
            hasil += stoklama + stokbaru;
            String value1 = kodeobat.getText();
            String asli = kode_obat1.getText();
            String value2 = nama_obat.getText();
            String value3 = jenis_obat.getSelectedItem().toString();
            String value4 = harga_satuan.getText();
            con.createStatement().execute("update obat set kode_obat ='"+asli+"', nama_obat='"+value2+"'"
                    +" , jenis_obat='"+value3+"', harga_satuan='"+value4+"', stok='"+hasil+"' where kode_obat='"+asli+"' ");
            JOptionPane.showMessageDialog(rootPane,"Data Terupdate!");
        }catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,e);
        }
        setuptable();
    }
    }//GEN-LAST:event_bUbahActionPerformed

    private void txtSearchObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchObatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchObatActionPerformed

    private void txtSearchObatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchObatKeyReleased
        // TODO add your handling code here:
        try{
            String sql = "select kode_obat as 'Kode Obat', nama_obat as 'Nama Obat', jenis_obat as 'Jenis Obat', harga_satuan as 'Harga', stok as Stok, tanggal_entri as 'Tanggal Entri', nama_pengentri as 'Nama Pengentri' from obat where kode_obat LIKE ? or nama_obat LIKE ? or jenis_obat LIKE ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%"+txtSearchObat.getText()+"%");
            pst.setString(2, "%"+txtSearchObat.getText()+"%");
            pst.setString(3, "%"+txtSearchObat.getText()+"%");
            rs=pst.executeQuery();
            tabel_obat.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);            
        }
    }//GEN-LAST:event_txtSearchObatKeyReleased

    private void tabel_obatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_obatMouseClicked
        // TODO add your handling code here:
        try{
            kode_obat.setVisible(false);
            kode_obat1.setVisible(true);
            int row = tabel_obat.getSelectedRow();
            String klik=(tabel_obat.getModel().getValueAt(row,0).toString());
            String sql = "select kode_obat as 'Kode Obat', nama_obat as 'Nama Obat', jenis_obat as 'Jenis Obat', harga_satuan as 'Harga', stok as Stok from obat where kode_obat='"+klik+"' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                String add1 = rs.getString("Kode Obat");
                kode_obat1.setText(add1);
                String add2 = rs.getString("Nama Obat");
                nama_obat.setText(add2); 
                String add4 = rs.getString("Harga");
                harga_satuan.setText(add4);
                String add5 = rs.getString("Stok");
                stok.setText(add5);
                String add3 = rs.getString("Jenis Obat");
                jenis_obat.setSelectedItem(add3);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_tabel_obatMouseClicked

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearActionPerformed
        // TODO add your handling code here:
        kode_obat.setText("");
        kode_obat.setVisible(true);
        kode_obat1.setText("");
        kode_obat1.setVisible(true);
        nama_obat.setText("");
        jenis_obat.setSelectedItem("Pilih Jenis");
        harga_satuan.setText("");
        stok.setText("");
    }//GEN-LAST:event_bClearActionPerformed

    private void jenis_obatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenis_obatActionPerformed
        // TODO add your handling code here:
        auto_code();
    }//GEN-LAST:event_jenis_obatActionPerformed

    private void kode_obatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_obatKeyTyped
        // TODO add your handling code here:
        if(kode_obat.getText().length()>=6){
            evt.consume();
        }
    }//GEN-LAST:event_kode_obatKeyTyped

    private void kode_obatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_obatKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_kode_obatKeyReleased

    private void kode_obat1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_obat1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_kode_obat1KeyReleased

    private void kode_obat1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_obat1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_kode_obat1KeyTyped

    private void kodeobatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeobatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodeobatActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Kasir().setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

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
            java.util.logging.Logger.getLogger(Obat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Obat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Obat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Obat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Obat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClear;
    private javax.swing.JButton bHapus;
    private javax.swing.JButton bSimpan;
    private javax.swing.JButton bUbah;
    private javax.swing.JMenu datetime;
    private javax.swing.JTextField harga_satuan;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jenis_obat;
    private javax.swing.JTextField kode_obat;
    private javax.swing.JTextField kode_obat1;
    private javax.swing.JTextField kodeobat;
    private javax.swing.JTextField nama_obat;
    private javax.swing.JMenu nama_user;
    private javax.swing.JTextField stok;
    private javax.swing.JTable tabel_obat;
    private javax.swing.JTextField txtSearchObat;
    // End of variables declaration//GEN-END:variables
}
