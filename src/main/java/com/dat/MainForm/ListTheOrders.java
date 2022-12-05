
package com.dat.MainForm;

import DAO.OrderDAO;
import com.dat.DialogAdd.Success;
import com.dat.DialogAdd.Warning;
import com.dat.Order.CTSP;
import com.dat.Order.DatHang;
import com.dat.Order.XacNhan;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;


public class ListTheOrders extends javax.swing.JPanel {
    DefaultTableModel tblModelDDH = new DefaultTableModel();
    DefaultTableModel tblModelSP_DDH = new DefaultTableModel();
    private List<DatHang> listTheOrders;
    private List<CTSP> listCTSP;
    private boolean ttAdd= false;
    Warning WarningError;
    Success success;
    boolean addStatus=false;
    private XacNhan confirm;
    boolean y_n,cn;
    
    public ListTheOrders(Warning WarningError, Success success) {
        initComponents();
        initTable();
        fillToTableDDH();
        this.WarningError = WarningError;
        this.success = success;
        y_n=false;
        cn=false;
        btnConfirm.setEnabled(false);
        btnRemove.setEnabled(false);
        setOpaque(false);
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
        try{
            OrderDAO dao = new OrderDAO();
            listTheOrders =dao.loadListDatHang();   
        }catch(Exception e){
            e.printStackTrace();
        }
        for(DatHang order : listTheOrders){
            Object[] row = new Object[]{order.getMaDon(),order.getNhaCungCap(),order.getDate(),convertMoney(totalCost(order.getListSP())), order.getTrangThai()};
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
    public void removeSPFromDDH(){
        int row = tblDDH.getSelectedRow();
        DatHang order = listTheOrders.get(row);
        
        listTheOrders.remove(row);
        //ListCTSP.remove();
    }
    private void getListTheOrders(){
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
    public XacNhan getInfoConfirm(){
       XacNhan confirm = new XacNhan();
       confirm.setMaPhieu(chuanhoaMa(txtMaPN.getText()));
       //confirm.setListSP(listCTSP);
       return confirm;
    }
    public void reset(){
        txtMaPN.setText("");
        listCTSP.clear();
        fillToTableDDH();
        fillToTableDDH_SP();
        lblTotal.setText("");
    }
    public boolean isY_n() {
        return y_n;
    }
    public void setCn(boolean cn) {
        this.cn = cn;
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
        btnConfirm = new com.dat.Swing.ButtonCustom();
        btnRemove = new com.dat.Swing.ButtonCustom();
        jLabel2 = new javax.swing.JLabel();

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 23, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMaPN, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );

        btnConfirm.setText("Xác nhận");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnRemove.setText("Xóa Đơn");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        jLabel2.setText("Sắp xếp:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(71, 71, 71)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(217, 217, 217))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(21, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115)
                        .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(147, 147, 147))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblDDHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDDHMouseClicked
       int row = tblDDH.getSelectedRow();
        if(row>=0){
            listCTSP = listTheOrders.get(row).getListSP();
            fillToTableDDH_SP();
            lblTotal.setText(convertMoney(totalCost(listCTSP)));
            btnConfirm.setEnabled(true);
        }
    }//GEN-LAST:event_tblDDHMouseClicked

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        removeSPFromDDH();
        fillToTableDDH();
        fillToTableDDH_SP();
        lblTotal.setText(convertMoney(totalCost(listCTSP)));
        btnRemove.setEnabled(false);
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        try{
            if(listTheOrders.isEmpty()){
               addStatus=false;
            }
            if(addStatus){
//                XacNhan confirm = getInfoConfirm();
//                setCn(false);
//                confirm.getMaPhieu();
//                setVisible(true);
                  txtMaPN.setText("");
                if(isY_n()){
                    OrderDAO dao = new OrderDAO();
                    dao.addXacNhan(confirm);
                    
                    reset();
                    getListTheOrders();
                    success.setContent("Bạn đã xác nhận đơn đặt hàng thành công.");
                    success.setVisible(true);
                    addStatus=false;
                    
                }
            }else{
                WarningError.setContent("Vui lòng điền mã phiếu nhập hàng!");
                WarningError.setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnConfirmActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.dat.Swing.ButtonCustom btnConfirm;
    private com.dat.Swing.ButtonCustom btnRemove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblDDH;
    private javax.swing.JTable tblSP_DDH;
    private javax.swing.JTextField txtMaPN;
    // End of variables declaration//GEN-END:variables
}
