
package com.dat.MainForm;

import DAO.OrderDAO;
import com.dat.Dialog.QsDelete;
import com.dat.Dialog.Success;
import com.dat.Order.CTSP;
import com.dat.Order.DonHang;
import com.dat.Order.Order;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;


public class DSDonHangForm extends javax.swing.JPanel {
    DefaultTableModel tblModelDH = new DefaultTableModel();
    DefaultTableModel tblModelSP_DH = new DefaultTableModel();
    private List<DonHang> listOrder;
    private List<CTSP> listCTSP;
    
    QsDelete qs;
    Success sc;
    public DSDonHangForm( QsDelete qs,Success sc) {
        
        initComponents();
        initTable();
        getData();
        fillToTableDH();
        this.qs=qs;
        this.sc=sc;
        setOpaque(false);
        btnConfirm.setEnabled(false);
        btnDelete.setEnabled(false);
    }
    
    public void initTable(){
        String[] header = new String[]{"Mã đơn", "Tên khách hàng", "Ngày lập", "Thành tiền","Trạng thái"};
        tblModelDH.setColumnIdentifiers(header);
        tblDH.setModel(tblModelDH);
        String[] header1 = new String[]{"Mã sản phẩm", "Tên sản phẩm",  "Số lượng", "Giá", "Thành tiền"};
        tblModelSP_DH.setColumnIdentifiers(header1);
        tblSP_DH.setModel(tblModelSP_DH);
    }
    
    public String convertTT(Order order){
        if(order.getTT()){
            return "Đã thanh toán";
        }else{
            return "Chưa thanh toán";
        }
    }
    public void getData(){
        try{
            OrderDAO dao = new OrderDAO();
            listOrder=dao.loadListDonHang();   
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void fillToTableDH(){
        tblModelDH.setRowCount(0);
        for(DonHang order : listOrder){
            Object[] row = new Object[]{order.getMaDon(),order.getTenKhachHang(),order.getDate(),convertMoney(totalCost(order.getListSP())),convertTT(order)};
            tblModelDH.addRow(row);
        }
        tblModelDH.fireTableDataChanged();
    }
    
    public void fillToTableDH_SP(){
        tblModelSP_DH.setRowCount(0);
        for(CTSP ct : listCTSP){
            Object[] row = new Object[]{ct.getMaSP(),ct.getTenSP(),ct.getSoLuong(),convertMoney(ct.getGia()),convertMoney(ct.getGia() * ct.getSoLuong())};
            tblModelSP_DH.addRow(row);
        }
        tblModelSP_DH.fireTableDataChanged();
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
    
    public void sortByMa(){
        
         Comparator<DonHang> com = new Comparator<DonHang>(){
            @Override
            public int compare(DonHang o1, DonHang o2){
                return o1.getMaDon().compareTo(o2.getMaDon());
            }
        };   
        Collections.sort(listOrder, com);
    }
    public void sortByOld(){
        
         Comparator<DonHang> com = new Comparator<DonHang>(){
            @Override
            public int compare(DonHang o1, DonHang o2){
                return o1.getDate().compareTo(o2.getDate());
            }
        };   
        Collections.sort(listOrder, com);
    }
    public void sortByNew(){
        
         Comparator<DonHang> com = new Comparator<DonHang>(){
            @Override
            public int compare(DonHang o1, DonHang o2){
                return o2.getDate().compareTo(o1.getDate());
            }
        };   
        Collections.sort(listOrder, com);
    }
    public String chuanhoaMa(String ma){
        return ma.replaceAll(" ", "").toUpperCase();
    }
    // set select cho sản phẩm được tìm thấy
    public int selectRowTable(Order order){
        if(order!= null){
            int i = 0;
            for(Order or : listOrder){
                boolean check = or.getMaDon().equals(order.getMaDon());
                if(check){
                    return i;
                }
                i++;
            }
        }
        return -1;
    }
    
    public void sortEXEC(){
        String temp =cbxSort.getSelectedItem().toString();
        switch (temp){
            case "Cũ đến mới":
                sortByOld();
                fillToTableDH();
                break;
            case "Mã đơn":
                sortByMa();
                fillToTableDH();
                break;
            case "Mới đến cũ":
                sortByNew();
                fillToTableDH();
                break;
            default:
                getData();
                fillToTableDH();
                break;
       }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDH = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSP_DH = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnConfirm = new com.dat.Swing.ButtonCustom();
        btnDelete = new com.dat.Swing.ButtonCustom();
        cbxSort = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new com.dat.Swing.ButtonCustom();
        lblTB = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        tblDH.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDH.setSelectionBackground(new java.awt.Color(102, 255, 255));
        tblDH.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblDH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDH);

        jLabel1.setText("Danh sách đơn hàng:");

        jLabel2.setText("Thông tin chi tiết đơn hàng:");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Danh sách sản phẩm:");

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
        tblSP_DH.setSelectionBackground(new java.awt.Color(102, 255, 255));
        tblSP_DH.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jScrollPane2.setViewportView(tblSP_DH);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Thành tiền:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lblTotal))
                .addGap(26, 26, 26))
        );

        btnConfirm.setText("Xác nhận hoàn thành");
        btnConfirm.setBorderColor(new java.awt.Color(0, 0, 255));
        btnConfirm.setColorClick(new java.awt.Color(0, 204, 255));
        btnConfirm.setColorOver(new java.awt.Color(0, 255, 255));
        btnConfirm.setRadius(10);
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa đơn");
        btnDelete.setBorderColor(new java.awt.Color(0, 51, 255));
        btnDelete.setColorClick(new java.awt.Color(0, 204, 255));
        btnDelete.setColorOver(new java.awt.Color(0, 255, 255));
        btnDelete.setName(""); // NOI18N
        btnDelete.setRadius(10);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        cbxSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên", "Mã đơn", "Mới đến cũ","Cũ đến mới"}));
        cbxSort.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbxSort.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSortItemStateChanged(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-sort-alpha-up-24.png"))); // NOI18N
        jLabel5.setText("Sắp xếp");

        txtSearch.setForeground(new java.awt.Color(204, 204, 204));
        txtSearch.setText("*Mã hóa đơn");
        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png"))); // NOI18N
        btnSearch.setBorderColor(new java.awt.Color(255, 255, 255));
        btnSearch.setColorClick(new java.awt.Color(0, 204, 255));
        btnSearch.setColorOver(new java.awt.Color(0, 255, 255));
        btnSearch.setRadius(10);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        lblTB.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblTB.setForeground(new java.awt.Color(255, 51, 51));
        lblTB.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                                .addGap(1, 1, 1)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearch))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTB, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(cbxSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblDHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDHMouseClicked
        int row = tblDH.getSelectedRow();
        if(row>=0){
            listCTSP = listOrder.get(row).getListSP();
            fillToTableDH_SP();
            lblTotal.setText(convertMoney(totalCost(listCTSP)));
            if(!listOrder.get(row).getTT()){
                btnConfirm.setEnabled(true);
                btnDelete.setEnabled(true);
            }else{
                btnConfirm.setEnabled(false);
                btnDelete.setEnabled(false);
            }
        }
    }//GEN-LAST:event_tblDHMouseClicked

    private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusGained
        txtSearch.setText("");
        txtSearch.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_txtSearchFocusGained

    private void cbxSortItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSortItemStateChanged
        sortEXEC();
        
    }//GEN-LAST:event_cbxSortItemStateChanged

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        if(chuanhoaMa(txtSearch.getText()).equals("")){
            lblTB.setText("*Vui lòng nhập đơn cần tìm!");
            lblTB.setForeground(new java.awt.Color(255, 51, 51));
        }else{
            
            try{
            OrderDAO dao = new OrderDAO();
            
            Order temp = dao.findOrderByMa(chuanhoaMa(txtSearch.getText()));
            if(temp==null){
                lblTB.setText("*Đơn hàng không tồn tại.");
                lblTB.setForeground(new java.awt.Color(255, 51, 51));
            }else{
                lblTB.setText("");
               int row = selectRowTable(temp);
               tblDH.setRowSelectionInterval(row,row);
               txtSearch.setText("");
               listCTSP=listOrder.get(row).getListSP();
               fillToTableDH_SP();
               if(!listOrder.get(row).getTT()){
                btnConfirm.setEnabled(true);
                btnDelete.setEnabled(true);
                }else{
                   btnConfirm.setEnabled(false);
                   btnDelete.setEnabled(false);
               }
                
            }
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        int row = tblDH.getSelectedRow();
        if(row>=0){
           DonHang order = listOrder.get(row);
            order.setTT(true);
            try{
                OrderDAO dao = new OrderDAO();
                dao.updateTTDonHang(order);
                getData();
                sortEXEC();
                fillToTableDH();
                sc.setContent("Xác nhận đơn hàng thành công.");
                sc.setVisible(true);
            }catch(Exception e){
                e.printStackTrace();
            }
               
 
        }
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        qs.setContent("Bạn có muốn xóa đơn hàng không?");
        qs.setVisible(true);
        if(qs.isY_n()){
            int row = tblDH.getSelectedRow();
            if(row>=0){
                DonHang order = listOrder.get(row);
                try{
                    OrderDAO dao = new OrderDAO();
                    dao.deleteDonHang(order.getMaDon());
                    getData();
                    sortEXEC();
                    fillToTableDH();
                    sc.setContent("Xóa đơn hàng thành công.");
                    sc.setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.dat.Swing.ButtonCustom btnConfirm;
    private com.dat.Swing.ButtonCustom btnDelete;
    private com.dat.Swing.ButtonCustom btnSearch;
    private javax.swing.JComboBox<String> cbxSort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTB;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblDH;
    private javax.swing.JTable tblSP_DH;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
