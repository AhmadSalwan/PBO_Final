/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Andi
 */
public class App extends javax.swing.JFrame {
    int nomor=1;
    boolean isEditMode = false;
    
    
    
   public void hitungTotal(){
        
        int jumlahBaris=jTable1.getRowCount();
        int totalBiaya = 0;
        int jumlahBarang,hargaBarang,kembalian;
        for (int i=0;i<jumlahBaris;i++){
            jumlahBarang=Integer.parseInt(jTable1.getValueAt(i,2).toString());
            hargaBarang=Integer.parseInt(jTable1.getValueAt(i,3).toString());
           totalBiaya=totalBiaya+(jumlahBarang*hargaBarang);
        }
        TxTotal.setText(String.valueOf(totalBiaya));
   }
   public void hitungNo(){
       DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
       int jumlahBaris = jTable1.getRowCount();
       int nomor=0;
       for (int i=0;i<jumlahBaris;i++){
           nomor+=1;
           model.setValueAt(nomor, i, 0);
    
       }
   }
   
           
           
   public void clearL(){
       TxNama.setText("");
        TxHarga.setText("");
        TxJumlah.setText("");
   }
   public void clearR(){
       DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
        
        while(model.getRowCount()>0){
            model.removeRow(0);
        }
   }
   public void clearD(){
       TxTotal.setText("");
       TxBayar.setText("");
       TxKembali.setText("");
   }
   
   public void load(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        String nama = TxNama.getText();
        String harga=TxHarga.getText();
        String jumlah=TxJumlah.getText();
        if(harga.contains("+")||jumlah.contains("+")){
             JOptionPane.showMessageDialog(null,"Tidak boleh menginput \"+\"");
        }
        else if(harga.contains("-")||jumlah.contains("-")){
             JOptionPane.showMessageDialog(null,"Tidak boleh menginput \"-\"");
        }
        else{
        try{
            int hargaint=Integer.parseInt(harga);
            int jumlahint=Integer.parseInt(jumlah);
            
            
        
        model.addRow(new Object[]{
            
            nomor,
            TxNama.getText(),
            TxHarga.getText(),
            TxJumlah.getText(),
         
        });
        hitungNo();
        clearL();
        hitungTotal();
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Harga dan Jumlah Harus Berupa Angka");
            }
        }
   }
   
   public void loadEdit(){
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        String nama = TxNama.getText();
        String harga=TxHarga.getText();
        String jumlah=TxJumlah.getText();
        if(harga.contains("+")||jumlah.contains("+")){
             JOptionPane.showMessageDialog(null,"Tidak boleh menginput \"+\"");
        }
        else if(harga.contains("-")||jumlah.contains("-")){
             JOptionPane.showMessageDialog(null,"Tidak boleh menginput \"-\"");
        }
        else{
        try{
            int hargaint=Integer.parseInt(harga);
            int jumlahint=Integer.parseInt(jumlah);
            int selectedRow = jTable1.getSelectedRow(); // Mendapatkan indeks baris yang sedang dipilih

            if (selectedRow != -1) {
                model.setValueAt(nomor, selectedRow, 0); // Mengganti nilai kolom 'No' pada baris yang dipilih
                model.setValueAt(TxNama.getText(), selectedRow, 1); // Mengganti nilai kolom 'Nama' pada baris yang dipilih
                model.setValueAt(TxHarga.getText(), selectedRow, 2); // Mengganti nilai kolom 'Harga' pada baris yang dipilih
                model.setValueAt(TxJumlah.getText(), selectedRow, 3); // Mengganti nilai kolom 'Jumlah' pada baris yang dipilih
            }
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Harga dan Jumlah Harus Berupa Angka");
            }           
    hitungNo();
    clearL();
    hitungTotal();
        }
   }
   public boolean isEmpty(){
       DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        if (model.getRowCount()==0){
           return true;
        }
        else{
            return false;
        }
   }
   
    /**
     * Creates new form App
     */
    public App() {
        initComponents();
        TxBayar.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                int total,tunai,kembalian;
                total=Integer.valueOf(TxTotal.getText());
                tunai=Integer.valueOf(TxBayar.getText());
                if (total>tunai){
                    TxKembali.setText("Uang Tidak Cukup");
                }
                else{
                    kembalian=tunai-total;
                    TxKembali.setText(String.valueOf(kembalian));
                }
            
            }
        }
    });
    
    TxNama.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            TxHarga.requestFocus();
            }
        }
    });
    TxHarga.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            TxJumlah.requestFocus();
            }
        }
    });
    TxJumlah.addKeyListener(new KeyAdapter() {
        
        public void keyPressed(KeyEvent e) {
            if(isEditMode!=true){
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(TxNama.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Nama Belum diiput");
        }else if (TxHarga.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Harga Belum diiput");
        }else if(TxJumlah.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Jumlah Belum diiput");
        }else{
            load();
            TxNama.requestFocus();
            
                }          
            }
        }                         
    }
    });             
    
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
        TxTitle = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        label_Nama = new javax.swing.JLabel();
        label_Harga = new javax.swing.JLabel();
        label_Jumlah = new javax.swing.JLabel();
        TxNama = new javax.swing.JTextField();
        TxHarga = new javax.swing.JTextField();
        TxJumlah = new javax.swing.JTextField();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        TxBayar = new javax.swing.JTextField();
        label_total = new javax.swing.JLabel();
        label_Kembali = new javax.swing.JLabel();
        TxKembali = new javax.swing.JTextField();
        label_Tunai = new javax.swing.JLabel();
        TxTotal = new javax.swing.JTextField();
        TxHitung = new javax.swing.JButton();
        TxSimpan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(87, 100, 188));

        TxTitle.setFont(new java.awt.Font("Segoe UI Historic", 0, 36)); // NOI18N
        TxTitle.setForeground(new java.awt.Color(242, 242, 242));
        TxTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TxTitle.setText("TOKO ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(454, Short.MAX_VALUE)
                .addComponent(TxTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(454, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(TxTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(87, 100, 188));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 51, Short.MAX_VALUE)
        );

        label_Nama.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_Nama.setText("Nama");
        label_Nama.setMaximumSize(new java.awt.Dimension(52, 25));
        label_Nama.setMinimumSize(new java.awt.Dimension(52, 25));
        label_Nama.setName(""); // NOI18N

        label_Harga.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_Harga.setText("Harga");

        label_Jumlah.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_Jumlah.setText("Jumlah");
        label_Jumlah.setMaximumSize(new java.awt.Dimension(52, 25));
        label_Jumlah.setMinimumSize(new java.awt.Dimension(52, 25));
        label_Jumlah.setName(""); // NOI18N

        TxNama.setBackground(new java.awt.Color(0, 0, 0));
        TxNama.setForeground(new java.awt.Color(255, 255, 255));
        TxNama.setMaximumSize(new java.awt.Dimension(52, 25));
        TxNama.setMinimumSize(new java.awt.Dimension(52, 25));
        TxNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxNamaActionPerformed(evt);
            }
        });

        TxHarga.setBackground(new java.awt.Color(0, 0, 0));
        TxHarga.setForeground(new java.awt.Color(242, 242, 242));
        TxHarga.setMaximumSize(new java.awt.Dimension(52, 25));
        TxHarga.setMinimumSize(new java.awt.Dimension(52, 25));
        TxHarga.setName(""); // NOI18N
        TxHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxHargaActionPerformed(evt);
            }
        });

        TxJumlah.setBackground(new java.awt.Color(0, 0, 0));
        TxJumlah.setForeground(new java.awt.Color(242, 242, 242));
        TxJumlah.setMaximumSize(new java.awt.Dimension(52, 25));
        TxJumlah.setMinimumSize(new java.awt.Dimension(52, 25));
        TxJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxJumlahActionPerformed(evt);
            }
        });

        btnTambah.setBackground(new java.awt.Color(0, 0, 0));
        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(242, 242, 242));
        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(102, 204, 255));
        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDel.setBackground(new java.awt.Color(255, 255, 0));
        btnDel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDel.setText("Delete");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(255, 0, 0));
        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnClear.setForeground(new java.awt.Color(242, 242, 242));
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setForeground(new java.awt.Color(51, 51, 51));

        TxBayar.setBackground(new java.awt.Color(0, 0, 0));
        TxBayar.setForeground(new java.awt.Color(242, 242, 242));
        TxBayar.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        TxBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxBayarActionPerformed(evt);
            }
        });

        label_total.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_total.setForeground(new java.awt.Color(242, 242, 242));
        label_total.setText("Total");

        label_Kembali.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_Kembali.setForeground(new java.awt.Color(242, 242, 242));
        label_Kembali.setText("Kembali");

        TxKembali.setBackground(new java.awt.Color(0, 0, 0));
        TxKembali.setForeground(new java.awt.Color(242, 242, 242));
        TxKembali.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TxKembali.setEnabled(false);
        TxKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxKembaliActionPerformed(evt);
            }
        });

        label_Tunai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_Tunai.setForeground(new java.awt.Color(242, 242, 242));
        label_Tunai.setText("Tunai");

        TxTotal.setBackground(new java.awt.Color(0, 0, 0));
        TxTotal.setForeground(new java.awt.Color(255, 255, 255));
        TxTotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TxTotal.setEnabled(false);
        TxTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxTotalActionPerformed(evt);
            }
        });

        TxHitung.setBackground(new java.awt.Color(0, 204, 204));
        TxHitung.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TxHitung.setText("Hitung");
        TxHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxHitungActionPerformed(evt);
            }
        });

        TxSimpan.setBackground(new java.awt.Color(0, 204, 204));
        TxSimpan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TxSimpan.setText("Simpan");
        TxSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TxSimpan.setEnabled(false);
        TxSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxSimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_Tunai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_Kembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxTotal)
                    .addComponent(TxBayar)
                    .addComponent(TxKembali))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(TxHitung)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxSimpan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_total, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxTotal))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label_Tunai, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label_Kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxHitung)
                    .addComponent(TxSimpan))
                .addGap(49, 49, 49))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Nama", "Harga", "Jumlah"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label_Nama, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label_Harga, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label_Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TxHarga, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                                    .addComponent(TxJumlah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TxNama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDel, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_Nama, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TxNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(2, 2, 2)))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_Harga, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(TxHarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_Jumlah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TxJumlah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 241, Short.MAX_VALUE)
                        .addGap(83, 83, 83))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                        .addGap(154, 154, 154)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TxNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxNamaActionPerformed

    private void TxJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxJumlahActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
    

        if(TxNama.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Nama Belum diiput");
        }else if (TxHarga.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Harga Belum diiput");
        }else if(TxJumlah.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Jumlah Belum diiput");
        }else{
            load();
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void TxKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxKembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxKembaliActionPerformed

    private void TxTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxTotalActionPerformed

    private void TxBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxBayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxBayarActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
     
        btnTambah.setEnabled(true);
        if(isEditMode){
            btnDel.setEnabled(true);
                
            btnEdit.setText("Edit");
                
            isEditMode = false;
        }else{
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        hitungTotal(); 
        }
    // TODO add your handling code here:
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        if(isEmpty()){
            JOptionPane.showMessageDialog(null, "Tabel Kosong");
        }else{
            DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
            int row = jTable1.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Pilih baris untuk dihapus");
            } else {
                model.removeRow(row);
                hitungTotal();
                hitungNo();
            }
            
        }              
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        
        try {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        if (model.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Tabel Kosong");
        }else{
        int row = jTable1.getSelectedRow();
       
            if (isEditMode==false) {
                // Mode edit
                btnTambah.setEnabled(false);
                btnDel.setEnabled(false);
                clearL();
                btnEdit.setText("Update");
                System.out.println("Edit mode");
                isEditMode = true;
                
            } else {
                
                if(TxNama.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Nama Belum diiput");
                }else if (TxHarga.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Harga Belum diiput");
                }else if(TxJumlah.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Jumlah Belum diiput");
                }else if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Pilih baris untuk diedit");
                }else{
                    btnTambah.setEnabled(true);
                    btnDel.setEnabled(true);                
                    btnEdit.setText("Edit");
                    loadEdit();
                    
                    isEditMode = false;
                    }
                
                }
            
        }
    }   catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void TxSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxSimpanActionPerformed
         int total,bayar,kembalian;
         total=Integer.valueOf(TxTotal.getText());
         bayar=Integer.valueOf(TxBayar.getText());
        
        if(total>bayar){
            JOptionPane.showMessageDialog(null,"uang tidak cukup");
        }
        else{
             DefaultTableModel model=(DefaultTableModel) jTable1.getModel();        
        try{
            java.sql.Connection c=Connection.getKoneksi();
            int baris = jTable1.getRowCount();
            
            for (int i=0;i<baris;i++){
                String sql = "INSERT INTO tb_product (NAMA,HARGA,Jumlah) VALUES('"
                        + jTable1.getValueAt(i, 1)+"','"+ jTable1.getValueAt(i,2)
                        +"','"+ jTable1.getValueAt(i,3)+"')";
                PreparedStatement p =c.prepareStatement(sql);
                p.executeUpdate();
                p.close();
            }
        }catch(Exception e) {
        System.out.println("Error: " + e.getMessage());
        }
        clearL();
        clearR();
        clearD();
        }
        TxSimpan.setEnabled(false);
    }//GEN-LAST:event_TxSimpanActionPerformed

    private void TxHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxHitungActionPerformed
        if(isEmpty()){            
            JOptionPane.showMessageDialog(null, "Tabel Kosong");
        }else{
            try{
            int total,tunai,kembalian;
            total=Integer.valueOf(TxTotal.getText());
            tunai=Integer.valueOf(TxBayar.getText());
            if (total>tunai){
                TxKembali.setText("Uang Tidak Cukup");
            }
            else{
                kembalian=tunai-total;
                TxKembali.setText(String.valueOf(kembalian));
                TxSimpan.setEnabled(true);
            }  
            }
             catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Pembayaran Harus Berupa Angka");
            }
        }
            
    }//GEN-LAST:event_TxHitungActionPerformed

    private void TxHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxHargaActionPerformed

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
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxBayar;
    private javax.swing.JTextField TxHarga;
    private javax.swing.JButton TxHitung;
    private javax.swing.JTextField TxJumlah;
    private javax.swing.JTextField TxKembali;
    private javax.swing.JTextField TxNama;
    private javax.swing.JButton TxSimpan;
    private javax.swing.JLabel TxTitle;
    private javax.swing.JTextField TxTotal;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnTambah;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel label_Harga;
    private javax.swing.JLabel label_Jumlah;
    private javax.swing.JLabel label_Kembali;
    private javax.swing.JLabel label_Nama;
    private javax.swing.JLabel label_Tunai;
    private javax.swing.JLabel label_total;
    // End of variables declaration//GEN-END:variables
}
