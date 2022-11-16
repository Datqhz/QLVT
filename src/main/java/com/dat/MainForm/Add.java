
package com.dat.MainForm;

import DAO.ProductDAO;
import com.dat.DialogAdd.Question;
import com.dat.DialogAdd.Success;
import com.dat.DialogAdd.Warning;
import com.dat.Product.Product;
import java.awt.Color;

import java.awt.Font;
import java.text.NumberFormat;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;


public class Add extends javax.swing.JPanel {
    List<Product> list = new ArrayList() ;
    Warning WarningError;
    Question Qs ;
    Success success;
    boolean addStatus = false;
    
    public Add( Warning WarningError,Question Qs, Success success) {
        initComponents();
        setOpaque(false);
        this.WarningError = WarningError;
        this.Qs = Qs;
        this.success = success;
        tblSp.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
        tblSp.getTableHeader().setOpaque(false);
        tblSp.setRowHeight(25);
        fillToTable();

    }
    //thêm một sản phẩm mới vào bảng
    public Product getInfoProduct(){
         
        Product prd = new Product();
        prd.setMaSp(chuanhoaMa(txtMasp.getText()));
        prd.setTenSp(chuanhoaTen(txtTensp.getText()));
        if(chuanhoaMa(txtDonvitinh.getText()).equals("")){
            prd.setDonViTinh("Cái");
        }else{
            prd.setDonViTinh(chuanhoaMa(txtDonvitinh.getText()));
        }
        prd.setSlTon(0);
        prd.setGiaBan(Integer.parseInt(chuanhoaMa(txtGia.getText()),10));
        return prd;
    }
    //làm mới bảng
    private void fillToTable(){
        try{
            ProductDAO dao = new ProductDAO();
            list=dao.loadListProduct();   
            SortByNameProduct();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        DefaultTableModel model = (DefaultTableModel) tblSp.getModel();
        model.setRowCount(0);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        
        for(Product prd : list){
            String str1 = currencyVN.format(prd.getGiaBan());
            Object[] row = new Object[]{prd.getMaSp(),prd.getTenSp(),prd.getDonViTinh(),str1};
            model.addRow(row);
        }
        model.fireTableDataChanged();
    }
    
    public void ResetForm(){
        txtMasp.setText("");
        txtTensp.setText("");
        txtDonvitinh.setText("");
        txtGia.setText("");
    }
    
    public void SortByNameProduct(){
        Comparator<Product> com = new Comparator<Product>(){
            @Override
            public int compare(Product o1, Product o2){
                return o1.getTenSp().compareTo(o2.getTenSp());
            }
        };   
        Collections.sort(list, com);
    }
    
    //Kiểm tra trùng tên sản phẩm
    public boolean checkEqualName(String name){
        Product  t = this.list.stream().filter(pr -> pr.getTenSp().equals(name)).findFirst().orElse(null);
        if(t == null)return false;
        return true;
    }
    //Kiểm tra trùng mã sản phẩm
    public boolean checkEqualMasp(String masp){
        Product  t = this.list.stream().filter(pr -> pr.getMaSp().equals(masp)).findFirst().orElse(null);
        if(t == null)return false;
        return true;
    }
    
    //Chuẩn hóa mã 
    public String chuanhoaMa(String ma){
        return ma.replaceAll(" ", "");
    }
    //Chuẩn hóa tên sản phẩm
    public String chuanhoaTen(String ma){
        ma = ma.trim();
        return ma.replaceAll("\\s+", " ");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMasp = new javax.swing.JTextField();
        txtTensp = new javax.swing.JTextField();
        txtDonvitinh = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSp = new javax.swing.JTable();
        lblMsp = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblDvt = new javax.swing.JLabel();
        lblIconmsp = new javax.swing.JLabel();
        lblIconname = new javax.swing.JLabel();
        btnAdd = new com.dat.Swing.ButtonCustom();
        jLabel4 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lblCheckGia = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        jLabel1.setText("Mã sản phẩm:");

        jLabel2.setText("Tên sản phẩm:");

        jLabel3.setText("Đơn vị tính:");

        txtMasp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaspFocusLost(evt);
            }
        });

        txtTensp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTenspFocusLost(evt);
            }
        });
        txtTensp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenspActionPerformed(evt);
            }
        });

        jLabel5.setText("Danh sách các sản phẩm: ");

        tblSp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Giá bán"
            }
        ));
        tblSp.setToolTipText("");
        tblSp.setFocusable(false);
        tblSp.setGridColor(new java.awt.Color(255, 255, 255));
        tblSp.setRowHeight(25);
        tblSp.setSelectionBackground(new java.awt.Color(0, 228, 245));
        tblSp.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblSp.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblSp);

        lblMsp.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblMsp.setForeground(new java.awt.Color(51, 51, 51));
        lblMsp.setText("VD: VT01,VT02.......");

        lblName.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        lblDvt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblDvt.setForeground(new java.awt.Color(255, 51, 51));
        lblDvt.setText("*Mặc định sẽ là \"Cái\"");

        btnAdd.setText("Thêm");
        btnAdd.setBorderColor(new java.awt.Color(0, 0, 255));
        btnAdd.setColorOver(new java.awt.Color(51, 255, 255));
        btnAdd.setRadius(10);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel4.setText("Giá bán:");

        txtGia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGiaFocusLost(evt);
            }
        });

        jLabel6.setText("VND");

        lblCheckGia.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblCheckGia.setForeground(new java.awt.Color(255, 0, 0));
        lblCheckGia.setText("*Vui lòng chỉ dùng số, không dùng chữ");
        lblCheckGia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                lblCheckGiaFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 918, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDvt)
                                    .addComponent(lblName)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblMsp)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtMasp, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblIconmsp))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtTensp, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtDonvitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel6))
                                                    .addComponent(lblCheckGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(3, 3, 3)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblIconname)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(107, 107, 107)))
                .addGap(0, 50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMasp)
                    .addComponent(lblIconmsp, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(lblMsp)
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(lblIconname, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(lblName)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDonvitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDvt)
                    .addComponent(lblCheckGia))
                .addGap(7, 7, 7)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaspFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaspFocusLost
         if(chuanhoaMa(txtMasp.getText()).equals("")){
            lblMsp.setText("*Mã sản phẩm không được để trống");
            lblMsp.setForeground(new Color (255,0,0));
            lblIconmsp.setIcon(new ImageIcon(getClass().getResource("/cross.png")));
            addStatus = false;
        }else if(checkEqualMasp(chuanhoaMa(txtMasp.getText()))){
            lblMsp.setText("*Mã sản phẩm đã tồn tại");
            lblMsp.setForeground(new Color (255,0,0));
            lblIconmsp.setIcon(new ImageIcon(getClass().getResource("/cross.png")));
            addStatus = false;
        }else{
            lblMsp.setText("");
            lblIconmsp.setIcon(new ImageIcon(getClass().getResource("/checked.png")));
            addStatus = true;
        }
    }//GEN-LAST:event_txtMaspFocusLost

    private void txtTenspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenspActionPerformed

    }//GEN-LAST:event_txtTenspActionPerformed

    private void txtTenspFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenspFocusLost
         if(chuanhoaTen(txtTensp.getText()).equals("")){
            lblName.setText("*Tên sản phẩm không được để trống");
            lblName.setForeground(new Color (255,0,0));
            lblIconname.setIcon(new ImageIcon(getClass().getResource("/cross.png")));
            addStatus = false;
        }else if(checkEqualName(chuanhoaTen(txtTensp.getText()))){
            lblName.setText("*Tên sản phẩm đã tồn tại");
            lblName.setForeground(new Color (255,0,0));
            lblIconname.setIcon(new ImageIcon(getClass().getResource("/cross.png")));
            addStatus = false;
        }else{
            lblName.setText("");
            lblIconname.setIcon(new ImageIcon(getClass().getResource("/checked.png")));
            addStatus = true;
        }
    }//GEN-LAST:event_txtTenspFocusLost

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try{
            if(addStatus == false){
                WarningError.setContent("Vui lòng điền đủ thông tin yêu cầu!");
                WarningError.setVisible(true);
            }else{
                Product sp = getInfoProduct();
                Qs.setContent(sp.toString(),"Bạn có muốn thêm sản phẩm không?");
                Qs.setVisible(true);
                if(Qs.isYn()){
                    ProductDAO dao =new ProductDAO();
                    dao.addProduct(sp);
                    ResetForm();
                    lblIconmsp.setIcon(null);
                    lblIconname.setIcon(null);
                    fillToTable();
                    success.setContent("Bạn đã thêm sản phẩm thành công.");
                    success.setVisible(true);
                    addStatus=false;
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void lblCheckGiaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lblCheckGiaFocusLost
        
    }//GEN-LAST:event_lblCheckGiaFocusLost

    private void txtGiaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGiaFocusLost
        boolean checkGia =false;
        try{
            int i = Integer.parseInt(chuanhoaMa(txtGia.getText()),10);
            checkGia = true;
        }catch(NumberFormatException e){
            checkGia = false;
        }
        if(checkGia== false){
            lblCheckGia.setText("*Không đúng yêu cầu, vui lòng nhập lại!");
            addStatus = false;
        }else {
            lblCheckGia.setText("");
            addStatus = true;
        }
    }//GEN-LAST:event_txtGiaFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.dat.Swing.ButtonCustom btnAdd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblCheckGia;
    private javax.swing.JLabel lblDvt;
    private javax.swing.JLabel lblIconmsp;
    private javax.swing.JLabel lblIconname;
    private javax.swing.JLabel lblMsp;
    private javax.swing.JLabel lblName;
    private javax.swing.JTable tblSp;
    private javax.swing.JTextField txtDonvitinh;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMasp;
    private javax.swing.JTextField txtTensp;
    // End of variables declaration//GEN-END:variables
}
