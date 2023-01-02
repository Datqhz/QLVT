
package com.dat.MainForm;

import DAO.OrderDAO;
import com.dat.Dialog.QsDelete;
import com.dat.Dialog.Success;
import com.dat.Dialog.Warning;
import com.dat.Order.CTSP;
import com.dat.Order.DatHang;
import com.dat.Order.Order;
import com.dat.User.User;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class ListTheOrders extends javax.swing.JPanel {
    DefaultTableModel tblModelDDH = new DefaultTableModel();
    DefaultTableModel tblModelSP_DDH = new DefaultTableModel();
    private List<DatHang> listTheOrders;
    private List<CTSP> listCTSP;
    Warning WarningError;
    QsDelete qs;
    Success sc;
    User user;

    
    public ListTheOrders(Warning WarningError,QsDelete qs, Success sc, User user) {
        initComponents();
        initTable();
        getListTheOrders();
        fillToTableDDH();
        this.WarningError = WarningError;
        this.qs = qs;
        this.sc = sc;
        setOpaque(false);
        btnConfirm.setEnabled(false);
        btnDelete.setEnabled(false);
        this.user = user;
       
    }
    public void initTable(){
        String[] header = new String[]{"Mã số đơn đặt hàng", "Nhà cung cấp", "Ngày lập", "Thành tiền","Trạng thái"};
        tblModelDDH.setColumnIdentifiers(header);
        tblDDH.setModel(tblModelDDH);
        String[] header1 = new String[]{"Mã sản phẩm", "Tên sản phẩm",  "Số lượng", "Giá", "Thành tiền"};
        tblModelSP_DDH.setColumnIdentifiers(header1);
        tblSP_DDH.setModel(tblModelSP_DDH);
    }
    
    public void fillToTableDDH(){
        tblModelDDH.setRowCount(0);
        for(DatHang order : listTheOrders){
            Object[] row = new Object[]{order.getMaDon(),order.getNhaCungCap(),order.getDate(),convertMoney(totalCost(order.getListSP())), convertTT(order)};
            tblModelDDH.addRow(row);
        }
        tblModelDDH.fireTableDataChanged();
    }
    
    public void fillToTableDDH_SP(){
        tblModelSP_DDH.setRowCount(0);
        for(CTSP ct : listCTSP){
            Object[] row = new Object[]{ct.getMaSP(),ct.getTenSP(),ct.getSoLuong(),convertMoney(ct.getGia()),convertMoney(ct.getGia() * ct.getSoLuong())};
            tblModelSP_DDH.addRow(row);
        }
        tblModelSP_DDH.fireTableDataChanged();
    }
    public String convertMoney(long money){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(money);
    }
    public long totalCost(List<CTSP> listCT){
        long total=0;
        for(CTSP ct:listCT){
            total+=ct.getSoLuong()* ct.getGia();
        }
        return total;
    }
    public void getListTheOrders(){
        try{
            OrderDAO dao = new OrderDAO();
            listTheOrders= dao.loadListDatHang();   
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public String chuanhoaMa(String ma){
        return ma.replaceAll(" ", "").toUpperCase();
    }
    public void reset(){
        txtMaPN.setText("");
        dateNgayXacNhan.setCalendar(null);
        listCTSP.clear();
        fillToTableDDH();
        fillToTableDDH_SP();
        lblTotal.setText("");
    }
    public String convertTT(Order order){
        if(order.getTT()){
            return "Đã thanh toán";
        }else{
            return "Chưa thanh toán";
        }
    }
//    public Date ConvertDate(String str){
//        Date tmp;
//        try{
//            tmp = new SimpleDateFormat("yyyy-MM-dd").parse(str);
//        }catch(Exception e){
//                    e.printStackTrace();          
//        }
//        return tmp;
//    }
    
    public boolean checkNgayXN(String dt)throws Exception{
        Date date_tmp=new SimpleDateFormat("yyyy-MM-dd").parse(dt);
        if (date_tmp.compareTo(dateNgayXacNhan.getDate())>=0||dateNgayXacNhan.getDate().compareTo(new Date())>0){
            return false;
        }else{
            return true;
        }

    }
    public String getDateXN(){
    
        SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd");
        if(dateNgayXacNhan.getDate()!=null  ){
        String date = g.format(dateNgayXacNhan.getDate());
        return date;}
        else {
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = localDate.format(formatter);
            return date;
        }
            
    }
    public void sortConfirm(){
        Comparator<DatHang> com = new Comparator<DatHang>(){
            @Override
            public int compare(DatHang o1, DatHang o2){
                boolean b1 = o1.getTT();
                boolean b2 = o2.getTT();
               return Boolean.compare( b1, b2 );
            }
        };   
        Collections.sort(listTheOrders, com);
    }
    public void sortByMa(){
        
         Comparator<DatHang> com = new Comparator<DatHang>(){
            @Override
            public int compare(DatHang o1, DatHang o2){
                return o1.getMaDon().compareTo(o2.getMaDon());
            }
        };   
        Collections.sort(listTheOrders, com);
    }
    public void sortByOld(){
        
         Comparator<DatHang> com = new Comparator<DatHang>(){
            @Override
            public int compare(DatHang o1, DatHang o2){
                return o1.getDate().compareTo(o2.getDate());
            }
        };   
        Collections.sort(listTheOrders, com);
    }
    public void sortByNew(){
        
         Comparator<DatHang> com = new Comparator<DatHang>(){
            @Override
            public int compare(DatHang o1, DatHang o2){
                return o2.getDate().compareTo(o1.getDate());
            }
        };   
        Collections.sort(listTheOrders, com);
    }
    public void sortEXEC(){
        String temp =cbxSort.getSelectedItem().toString();
        switch (temp){
            case "Cũ đến mới":
                sortByOld();
                fillToTableDDH();
                break;
            case "Mã đơn":
                sortByMa();
                fillToTableDDH();
                break;
            case "Mới đến cũ":
                sortByNew();
                fillToTableDDH();
                break;
            case "Trạng thái":
                sortConfirm();
                fillToTableDDH();
                break;
           default:
                break;
       }
    }
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDDH = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSP_DDH = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaPN = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        dateNgayXacNhan = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        btnConfirm = new com.dat.Swing.ButtonCustom();
        btnDelete = new com.dat.Swing.ButtonCustom();
        jLabel2 = new javax.swing.JLabel();
        cbxSort = new javax.swing.JComboBox<>();

        tblDDH.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDDH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDDHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDDH);

        jLabel1.setText("Danh sách Đơn đặt hàng:");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblSP_DDH.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblSP_DDH);

        jLabel3.setText("Danh sách sản phẩm:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Thành tiền:");

        jLabel5.setText("Mã phiếu nhập hàng:");

        txtMaPN.setBorder(null);

        jLabel6.setText("Ngày:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                    .addComponent(txtMaPN))))
                        .addContainerGap(310, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateNgayXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 51, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateNgayXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );

        btnConfirm.setText("Xác nhận");
        btnConfirm.setBorderColor(new java.awt.Color(0, 102, 255));
        btnConfirm.setColorClick(new java.awt.Color(0, 153, 255));
        btnConfirm.setColorOver(new java.awt.Color(0, 204, 255));
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa Đơn");
        btnDelete.setBorderColor(new java.awt.Color(0, 51, 255));
        btnDelete.setColorClick(new java.awt.Color(0, 153, 255));
        btnDelete.setColorOver(new java.awt.Color(0, 204, 255));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel2.setText("Sắp xếp:");

        cbxSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã đơn", "Mới đến cũ","Cũ đến mới","Trạng thái"}));
        cbxSort.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSortItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(73, 73, 73)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(cbxSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(157, 157, 157)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 14, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(cbxSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblDDHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDDHMouseClicked
       int row = tblDDH.getSelectedRow();
        if(row>=0){
            listCTSP = listTheOrders.get(row).getListSP();
            fillToTableDDH_SP();
            lblTotal.setText(convertMoney(totalCost(listCTSP)));
            if(!listTheOrders.get(row).getTT()){
                btnConfirm.setEnabled(true);
                btnDelete.setEnabled(true);
            }else{
               btnConfirm.setEnabled(false);
               btnDelete.setEnabled(false); 
            }
            
            
        }
    }//GEN-LAST:event_tblDDHMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       qs.setContent("Bạn có muốn xóa đơn hàng không?");
        qs.setVisible(true);
        if(qs.isY_n()){
            int row = tblDDH.getSelectedRow();
            if(row>=0){
                DatHang theorder = listTheOrders.get(row);
                try{
                    OrderDAO dao = new OrderDAO();
                    dao.deleteDatHang(theorder.getMaDon());
                    getListTheOrders();
                    sortEXEC();
                    fillToTableDDH();
                    sc.setContent("Xóa đơn hàng thành công.");
                    sc.setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        if(chuanhoaMa(txtMaPN.getText()).equals("")){
               WarningError.setContent("Vui lòng điền mã phiếu nhập hàng!");
               WarningError.setVisible(true);
            }else{
                          
                int row = tblDDH.getSelectedRow();
                if(row>=0){
                DatHang theorder = listTheOrders.get(row);
                theorder.setTT(true);
                    try {
                        if(checkNgayXN(theorder.getDate())){
                            try{
                                OrderDAO dao = new OrderDAO();
                                dao.addPhieuNhap(txtMaPN.getText(),theorder,getDateXN(),user.getMaNV());
                                getListTheOrders();
                                
                                sortEXEC();
                                fillToTableDDH();
                                sc.setContent("Xác nhận đơn hàng thành công.");
                                sc.setVisible(true);
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }else{
                            WarningError.setContent("Ngày bạn chọn không hợp lệ!");
                            WarningError.setVisible(true);
                        }} catch (Exception ex) {
                        Logger.getLogger(ListTheOrders.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                    
                }
            }
        
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void cbxSortItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSortItemStateChanged
        sortEXEC();
    }//GEN-LAST:event_cbxSortItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.dat.Swing.ButtonCustom btnConfirm;
    private com.dat.Swing.ButtonCustom btnDelete;
    private javax.swing.JComboBox<String> cbxSort;
    private com.toedter.calendar.JDateChooser dateNgayXacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblDDH;
    private javax.swing.JTable tblSP_DDH;
    private javax.swing.JTextField txtMaPN;
    // End of variables declaration//GEN-END:variables
}
