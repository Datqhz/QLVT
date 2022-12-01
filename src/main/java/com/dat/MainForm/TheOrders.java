
package com.dat.MainForm;

import DAO.OrderDAO;
import DAO.ProductDAO;
import com.dat.DialogAdd.QuestionOrder;
import com.dat.DialogAdd.Success;
import com.dat.DialogAdd.Warning;
import com.dat.Order.CTSP;
import com.dat.Order.DatHang;
import com.dat.Order.Order;
import com.dat.Product.Product;
import java.awt.Color;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;


public class TheOrders extends javax.swing.JPanel {
    private List<Product> listSP;
    DefaultTableModel tblModelSP = new DefaultTableModel();
    DefaultTableModel tblModelSP_DH = new DefaultTableModel();
    private Product temp;
    private List<DatHang> listTheOrders;
    private List<CTSP> listCTSP;
    private boolean ttAdd= false;
    Warning WarningError;
    Success success;
    boolean addStatus=false;
    QuestionOrder Qs;
  
    public TheOrders(Warning WarningError,QuestionOrder Qs, Success success) {
        initComponents();
        initTable();
        getListSP();
        //getListOrder();
        fillToTable();
        btnAdd.setEnabled(false);
        btnRemove.setEnabled(false);
        listCTSP = new ArrayList<>();
        this.WarningError = WarningError;
        this.Qs = Qs;
        this.success = success;
    }
    public void initTable(){
        String[] header = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính"};
        tblModelSP.setColumnIdentifiers(header);
        tblSP.setModel(tblModelSP);
        String[] header1 = new String[]{"Mã sản phẩm", "Tên sản phẩm",  "Số lượng", "Giá", "Thành tiền"};
        tblModelSP_DH.setColumnIdentifiers(header1);
        tblSP_DH.setModel(tblModelSP_DH);
    }
    
    public String chuanhoaMa(String ma){
        return ma.replaceAll(" ", "").toUpperCase();
    }
    private void getListSP(){
        try{
            ProductDAO dao = new ProductDAO();
            listSP=dao.loadListProduct();   
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void getListTheOrders(){
        try{
            OrderDAO dao = new OrderDAO();
            listTheOrders= dao.loadListDatHang();   
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void fillToTable(){
        tblModelSP.setRowCount(0);
        
        for(Product prd : listSP){
            Object[] row = new Object[]{prd.getMaSp(),prd.getTenSp(),prd.getDonViTinh()};
            tblModelSP.addRow(row);
        }
        tblModelSP.fireTableDataChanged();
    }
    
    public boolean checkEqualMasoDDH(String masoDDH){
        try{
           OrderDAO dao = new OrderDAO();
            listTheOrders = dao.loadListDatHang(); 
        }catch(Exception e){
            e.printStackTrace();
        }
        
        Order t = this.listTheOrders.stream().filter(pr -> pr.getMaDon().equals(masoDDH)).findFirst().orElse(null);
        return t != null;
    }
    
    public String chuanhoaTen(String ma){
        ma = ma.trim();
        return ma.replaceAll("\\s+", " ");
    }
    
    public String getDate(){
        SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd");
        if(dateNgayLap.getDate()!=null){
        String date = g.format(dateNgayLap.getDate());
        return date;}
        else {
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = localDate.format(formatter);
            return date;
        }
            
    }
    
    public void addSPToDH(){
        int row = tblSP.getSelectedRow();
        Product prd  = listSP.get(row);
        CTSP ct = new CTSP();
        if(!checkTrungSP(prd)){
            ct.setMaSP(tblSP.getValueAt(row,0).toString());
            ct.setTenSP(tblSP.getValueAt(row,1).toString());
            ct.setSoLuong(Integer.parseInt(chuanhoaMa(txtSoLuong.getText()),10));
            ct.setGia(Integer.parseInt(chuanhoaMa(txtGiaBan.getText()),10));
            listCTSP.add(ct);
            //listSP.get(row).setSlTon(prd.getSlTon()-Integer.parseInt(chuanhoaMa(txtSoLuong.getText()),10));
        }
    }
    public void fillToTableDH(){
        tblModelSP_DH.setRowCount(0);
        for(CTSP ct : listCTSP){
            Object[] row = new Object[]{ct.getMaSP(),ct.getTenSP(),ct.getSoLuong(),convertMoney(ct.getGia()),convertMoney(ct.getGia() * ct.getSoLuong())};
            tblModelSP_DH.addRow(row);
        }
        tblModelSP_DH.fireTableDataChanged();
    }
    public DatHang getInfoOrder(){
       DatHang order = new DatHang();
       order.setMaDon(chuanhoaMa(txtMasoDDH.getText()));
       order.setNhaCungCap(chuanhoaTen(txtNhaCC.getText()));
       order.setDate(getDate());
       order.setListSP(listCTSP);
       return order;
    }
    public String convertMoney(long money){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(money);
    }
    //check trùng sản phẩm trong đặt hàng
    public boolean checkTrungSP(Product prd){
        for(CTSP ct: listCTSP){
            if(ct.getMaSP().equals(prd.getMaSp())){
                    ct.setSoLuong(ct.getSoLuong() + Integer.parseInt(txtSoLuong.getText()));
                   
                    return true;
            }
        }
        return false;
    }
    
    public void checkAdd(){
        if(!chuanhoaMa(txtSoLuong.getText()).equals("")&&tblSP.getSelectedRow()>=0){
            ttAdd = true;
        }
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
    
    public long updateTotalCost(){
        long total=0;
        for(CTSP ct:listCTSP){
            total+=ct.getSoLuong()* ct.getGia();
        }
        return total;
    }
    public void resetCTSP(){
        for(CTSP ct :listCTSP){
            for(Product prd : listSP){
                if(prd.getMaSp().equals(ct.getMaSP())){
                    prd.setSlTon(prd.getSlTon()+ct.getSoLuong());
                    break;
                }
            }
        }
    }
    public void resetDDH(){
        txtMasoDDH.setText("");
        txtNhaCC.setText("");
        dateNgayLap.setCalendar(null);
        lblMaso.setText("");
        txtGiaBan.setText("");
        lblIConMaso.setIcon(null);
        //lbl1.setText("");
        listCTSP.clear();
        fillToTableDH();
        fillToTable();
        lblTotal.setText("");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSP_DH = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtMasoDDH = new javax.swing.JTextField();
        lblMaso = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNhaCC = new javax.swing.JTextField();
        lblErrorName = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblIConMaso = new javax.swing.JLabel();
        dateNgayLap = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        lblThongBao = new javax.swing.JLabel();
        lblSearch = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        lblCheckGia = new javax.swing.JLabel();
        btnCreate = new com.dat.Swing.ButtonCustom();
        btnRemove = new com.dat.Swing.ButtonCustom();
        btnRenew = new com.dat.Swing.ButtonCustom();

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
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSP);

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
        tblSP_DH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSP_DHMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSP_DH);

        jLabel1.setText("Mã số đơn đặt hàng:");

        txtMasoDDH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMasoDDHFocusLost(evt);
            }
        });

        lblMaso.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        jLabel3.setText("Nhà cung cấp:");

        txtNhaCC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNhaCCFocusLost(evt);
            }
        });

        lblErrorName.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        jLabel5.setText("Ngày:");

        jLabel6.setText("Chi tiết đơn đặt hàng:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Tổng:");

        lblIConMaso.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNhaCC, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblErrorName, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtMasoDDH, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblIConMaso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(lblMaso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMasoDDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIConMaso, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMaso, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtNhaCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblErrorName, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(dateNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)))
                .addGap(14, 14, 14))
        );

        jLabel9.setText("Mã sản phẩm:");

        lblThongBao.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        lblSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png"))); // NOI18N
        lblSearch.setText("Search");
        lblSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblSearchActionPerformed(evt);
            }
        });

        jLabel11.setText("Danh sách sản phẩm:");

        jLabel12.setText("Số lượng:");

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add-new.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel2.setText("Giá bán:");

        txtGiaBan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGiaBanFocusLost(evt);
            }
        });
        txtGiaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaBanActionPerformed(evt);
            }
        });

        lblCheckGia.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        btnCreate.setText("Tạo");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnRemove.setText("Xóa");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnRenew.setText("Làm mới");
        btnRenew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(23, 23, 23)
                                .addComponent(lblSearch))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(125, 125, 125)
                                        .addComponent(btnAdd))
                                    .addComponent(lblCheckGia, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(btnRenew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRenew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearch))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCheckGia, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        checkAdd();
        if(ttAdd ){
                addSPToDH();
                fillToTable();
                fillToTableDH();
                txtSoLuong.setText("");
                lblTotal.setText(convertMoney(updateTotalCost()));
        }else {
            WarningError.setContent("Vui lòng nhập đủ thông tin yêu cầu.");
            WarningError.setVisible(true);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        int row = tblSP.getSelectedRow();
        if(row>=0){
            temp = listSP.get(row);
            btnAdd.setEnabled(true);
        }
    }//GEN-LAST:event_tblSPMouseClicked

    private void lblSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblSearchActionPerformed
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
    }//GEN-LAST:event_lblSearchActionPerformed

    private void txtMasoDDHFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMasoDDHFocusLost
        if(chuanhoaMa(txtMasoDDH.getText()).equals("")){
            lblMaso.setText("*Mã số đơn đặt hàng không được để trống");
            lblMaso.setForeground(new Color (255,0,0));
            lblIConMaso.setIcon(new ImageIcon(getClass().getResource("/cross.png")));
            addStatus = false;
        }else if(checkEqualMasoDDH(chuanhoaMa(txtMasoDDH.getText()))){
            lblMaso.setText("*Mã số đơn đặt hàng đã tồn tại");
            lblMaso.setForeground(new Color (255,0,0));
            lblIConMaso.setIcon(new ImageIcon(getClass().getResource("/cross.png")));
            addStatus = false;
        }else{
            lblMaso.setText("");
            lblIConMaso.setIcon(new ImageIcon(getClass().getResource("/checked.png")));
            addStatus = true;
        }
    }//GEN-LAST:event_txtMasoDDHFocusLost

    private void txtNhaCCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNhaCCFocusLost
        if(chuanhoaTen(txtNhaCC.getText()).equals("")){
            lblErrorName.setText("*Nhà cung cấp không được để trống");
            lblErrorName.setForeground(new Color (255,0,0));
            addStatus = false;
        }else{
            lblErrorName.setText("");
            addStatus = true;
        }
    }//GEN-LAST:event_txtNhaCCFocusLost

    private void tblSP_DHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSP_DHMouseClicked
         int row = tblSP_DH.getSelectedRow();
        if(row>=0){
            btnRemove.setEnabled(true);
        }
    }//GEN-LAST:event_tblSP_DHMouseClicked

    private void txtGiaBanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGiaBanFocusLost
        boolean checkGia =false;
        try{
            int i = Integer.parseInt(chuanhoaMa(txtGiaBan.getText()),10);
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
    }//GEN-LAST:event_txtGiaBanFocusLost

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        try{
            if(listCTSP.isEmpty()){
               addStatus=false;
            }
            if(addStatus){
                DatHang theOrders = getInfoOrder();
                Qs.setCn(false);
                Qs.setContent(theOrders.getMaDon(),theOrders.getNhaCungCap(),theOrders.getDate(),theOrders.getListSP());
                Qs.setVisible(true);
                if(Qs.isY_n()){
                    OrderDAO dao = new OrderDAO();
                    dao.addTheOrders(theOrders);
                    resetDDH();
                    getListTheOrders();
                    success.setContent("Bạn đã thêm đơn đặt hàng thành công.");
                    success.setVisible(true);
                    addStatus=false;
                    
                }
            }else{
                WarningError.setContent("Vui lòng điền đủ thông tin yêu cầu!");
                WarningError.setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        removeSPFromDH();
        fillToTable();
        fillToTableDH();
        lblTotal.setText(convertMoney(updateTotalCost()));
        btnRemove.setEnabled(false);
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnRenewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenewActionPerformed
       resetDDH();
    }//GEN-LAST:event_btnRenewActionPerformed

    private void txtGiaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaBanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private com.dat.Swing.ButtonCustom btnCreate;
    private com.dat.Swing.ButtonCustom btnRemove;
    private com.dat.Swing.ButtonCustom btnRenew;
    private com.toedter.calendar.JDateChooser dateNgayLap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCheckGia;
    private javax.swing.JLabel lblErrorName;
    private javax.swing.JLabel lblIConMaso;
    private javax.swing.JLabel lblMaso;
    private javax.swing.JButton lblSearch;
    private javax.swing.JLabel lblThongBao;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblSP;
    private javax.swing.JTable tblSP_DH;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMasoDDH;
    private javax.swing.JTextField txtNhaCC;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
