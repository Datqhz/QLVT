
package com.dat.MainForm;

import DAO.OrderDAO;
import DAO.ProductDAO;
import com.dat.DialogAdd.Question;
import com.dat.DialogAdd.Success;
import com.dat.DialogAdd.Warning;
import com.dat.Order.CTSP;
import com.dat.Order.Order;
import com.dat.Product.Product;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;


public class CreateOrder extends javax.swing.JPanel {
    private List<Product> listSP;
    DefaultTableModel tblModelSP = new DefaultTableModel();
    DefaultTableModel tblModelSP_DH = new DefaultTableModel();
    private Product temp;
    private List<Order> listOrder;
    private List<CTSP> listCTSP;
    private boolean ttAdd= false;
    Warning WarningError;
    Question Qs ;
    Success success;
    
    public CreateOrder( Warning WarningError,Question Qs, Success success) {
        initComponents();
        initTable();
        getListSP();
        fillToTable();
        btnAdd.setEnabled(false);
        btnRemove.setEnabled(false);
        listCTSP = new ArrayList<>();
        this.WarningError = WarningError;
        this.Qs = Qs;
        this.success = success;
    }
    
    public void initTable(){
        String[] header = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Số lượng tồn", "Giá bán"};
        tblModelSP.setColumnIdentifiers(header);
        tblSP.setModel(tblModelSP);
        String[] header1 = new String[]{"Mã sản phẩm", "Tên sản phẩm",  "Số lượng", "Giá", "Thành tiền"};
        tblModelSP_DH.setColumnIdentifiers(header1);
        tblSP_DH.setModel(tblModelSP_DH);
    }
    
    public String chuanhoaMa(String ma){
        return ma.replaceAll(" ", "");
    }
    
    
    private void getListSP(){
        try{
            ProductDAO dao = new ProductDAO();
            listSP=dao.loadListProduct();   
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void fillToTable(){
        tblModelSP.setRowCount(0);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        
        for(Product prd : listSP){
            String str1 = currencyVN.format(prd.getGiaBan());
            Object[] row = new Object[]{prd.getMaSp(),prd.getTenSp(),prd.getDonViTinh(),prd.getSlTon(),str1};
            tblModelSP.addRow(row);
        }
        tblModelSP.fireTableDataChanged();
    }
    
    public boolean checkEqualMaDon(String madon){
        try{
           OrderDAO Odao = new OrderDAO();
            listOrder = Odao.loadListOrder(); 
        }catch(Exception e){
            e.printStackTrace();
        }
        
        Order  t = this.listOrder.stream().filter(pr -> pr.getMaDon().equals(madon)).findFirst().orElse(null);
        if(t == null)return false;
        return true;
    }
    
    public String chuanhoaTen(String ma){
        ma = ma.trim();
        return ma.replaceAll("\\s+", " ");
    }
    
    public String getDate(){
        SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd");
        String date = g.format(dateNgayLap.getDate());
        return date;
    }
    public void showListCT(){
        for(CTSP ct: listCTSP){
            System.out.println(ct.getMaSP() +" " +ct.getTenSP());
        }
    }
    public void addSPToDH(){
        int row = tblSP.getSelectedRow();
        Product prd  = listSP.get(row);
        CTSP ct = new CTSP();
        if(!checkTrung(prd)){
            ct.setMaSP(tblSP.getValueAt(row,0).toString());
            ct.setTenSP(tblSP.getValueAt(row,1).toString());
            ct.setSoLuong(Integer.parseInt(chuanhoaMa(txtSoLuong.getText()),10));
            ct.setGia(listSP.get(row).getGiaBan());
            listCTSP.add(ct);
            listSP.get(row).setSlTon(prd.getSlTon()-Integer.parseInt(chuanhoaMa(txtSoLuong.getText()),10));
        }
    }
    public void fillToTableDH(){
        tblModelSP_DH.setRowCount(0);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        
        for(CTSP ct : listCTSP){
            String str1 = currencyVN.format(ct.getGia() * ct.getSoLuong());
            String str = currencyVN.format(ct.getGia());
            Object[] row = new Object[]{ct.getMaSP(),ct.getTenSP(),ct.getSoLuong(),str,str1};
            tblModelSP_DH.addRow(row);
        }
        tblModelSP_DH.fireTableDataChanged();
    }
    public Order getInfoOrder(){
       Order order = new Order();
       order.setMaDon(chuanhoaMa(txtMaDon.getText()));
       order.setTenKhachHang(chuanhoaTen(txtTenKhachHang.getText()));
       order.setDate(getDate());
//       order.addSP();
       return order;
    }
    
    //check trùng sản phẩm trong đơn hàng
    public boolean checkTrung(Product prd){
        for(CTSP ct: listCTSP){
            if(ct.getMaSP().equals(prd.getMaSp())){
                    ct.setSoLuong(ct.getSoLuong() + Integer.parseInt(txtSoLuong.getText()));
                    prd.setSlTon(prd.getSlTon()-Integer.parseInt(txtSoLuong.getText()));
                    return true;
            }
        }
        return false;
    }
    
    public boolean checkSLTon(){
        int row = tblSP.getSelectedRow();
        Product prd  = listSP.get(row);
        if(prd.getSlTon()>=Integer.parseInt(txtSoLuong.getText())){
            return true;
        }else return false;
    }
    
    public void checkAdd(){
        if(!chuanhoaMa(txtSoLuong.getText()).equals("")&&tblSP.getSelectedRow()>=0){
            ttAdd = true;
        }
    }
    
    public void resetAllSelect(){
        txtSearch.setText("");
        txtSoLuong.setText("");
        btnAdd.setEnabled(false);
        tblSP.setRowSelectionInterval(0,0);
    }
    
    public void removeSPFromDH(){
        int row = tblSP_DH.getSelectedRow();
        CTSP ct = listCTSP.get(row);
        
        for(Product prd : listSP){
            if(prd.getMaSp().equals(ct.getMaSP())){
                prd.setSlTon(prd.getSlTon()+ct.getSoLuong());
                break;
            }
        }
        listCTSP.remove(row);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new com.dat.Swing.ButtonCustom();
        btnAdd = new com.dat.Swing.ButtonCustom();
        buttonCustom3 = new com.dat.Swing.ButtonCustom();
        buttonCustom4 = new com.dat.Swing.ButtonCustom();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSP_DH = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMaDon = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dateNgayLap = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblErrorName = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        lblThongBao = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();
        btnRemove = new com.dat.Swing.ButtonCustom();

        setOpaque(false);

        jLabel1.setText("Danh sách sản phẩm:");

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSP.setSelectionBackground(new java.awt.Color(153, 255, 255));
        tblSP.setSelectionForeground(new java.awt.Color(0, 51, 51));
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSP);

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png"))); // NOI18N
        btnSearch.setBorderColor(new java.awt.Color(255, 255, 255));
        btnSearch.setColorOver(new java.awt.Color(102, 255, 255));
        btnSearch.setRadius(10);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add-new.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        buttonCustom3.setText("Tạo");

        buttonCustom4.setText("Làm mới");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblSP_DH.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSP_DH.setSelectionBackground(new java.awt.Color(153, 255, 255));
        tblSP_DH.setSelectionForeground(new java.awt.Color(0, 51, 51));
        tblSP_DH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSP_DHMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSP_DH);

        jLabel3.setText("Mã đơn hàng");

        jLabel4.setText("Tên khách hàng:");

        jLabel6.setText("Ngày lập:");

        jLabel2.setText("Chi tiết đơn hàng");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 51));
        jLabel8.setText("*Mặc định sẽ là ngày hiện tại.");

        lblErrorName.setText("jLabel9");

        jLabel10.setText("jLabel10");

        jLabel11.setText("jLabel11");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Tổng:");

        lblTotal.setText("jLabel13");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtMaDon, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(lblErrorName))
                        .addGap(86, 86, 86)
                        .addComponent(jLabel5))
                    .addComponent(dateNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 1, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblErrorName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(lblTotal))
                .addContainerGap())
        );

        jLabel7.setText("Số lượng");

        btnRemove.setText("Xóa ");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblThongBao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(238, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(buttonCustom3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(buttonCustom4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblThongBao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel7)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblError)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonCustom3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonCustom4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        checkAdd();
        if(ttAdd){
            if(!checkSLTon()){
                WarningError.setContent("Số lượng sản phẩm trong kho không đủ.");
                WarningError.setVisible(true);
            }else{
                addSPToDH();
                fillToTable();
                fillToTableDH();
                resetAllSelect();
                
            }
        }else {
            WarningError.setContent("Vui lòng nhập đủ thông tin yêu cầu.");
            WarningError.setVisible(true);
        }
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        if(chuanhoaMa(txtSearch.getText()).equals("")){
            lblThongBao.setText("*Vui lòng nhập mã sản phẩm cần tìm!");
            lblThongBao.setForeground(new java.awt.Color(255, 51, 51));
        }else{
            
            try{
            ProductDAO dao = new ProductDAO();
            temp = dao.findProductByMa(chuanhoaMa(txtSearch.getText()));
            if(temp==null){
                lblThongBao.setText("*Sản phẩm không tồn tại.");
                lblThongBao.setForeground(new java.awt.Color(255, 51, 51));
            }else{
                lblThongBao.setText("");
               int row = dao.selectRowTable(chuanhoaMa(txtSearch.getText().toUpperCase()));
               tblSP.setRowSelectionInterval(row,row);
               txtSearch.setText("");
               btnAdd.setEnabled(true);
            }
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        int row = tblSP.getSelectedRow();
        if(row>=0){
            temp = listSP.get(row);
            btnAdd.setEnabled(true);
        }
    }//GEN-LAST:event_tblSPMouseClicked

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        removeSPFromDH();
        fillToTable();
        fillToTableDH();
        btnRemove.setEnabled(false);
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void tblSP_DHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSP_DHMouseClicked
        int row = tblSP_DH.getSelectedRow();
        if(row>=0){
            btnRemove.setEnabled(true);
        }
    }//GEN-LAST:event_tblSP_DHMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.dat.Swing.ButtonCustom btnAdd;
    private com.dat.Swing.ButtonCustom btnRemove;
    private com.dat.Swing.ButtonCustom btnSearch;
    private com.dat.Swing.ButtonCustom buttonCustom3;
    private com.dat.Swing.ButtonCustom buttonCustom4;
    private com.toedter.calendar.JDateChooser dateNgayLap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblErrorName;
    private javax.swing.JLabel lblThongBao;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblSP;
    private javax.swing.JTable tblSP_DH;
    private javax.swing.JTextField txtMaDon;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenKhachHang;
    // End of variables declaration//GEN-END:variables
}
