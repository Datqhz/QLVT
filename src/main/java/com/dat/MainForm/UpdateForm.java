
package com.dat.MainForm;

import DAO.ProductDAO;
import com.dat.Dialog.Question;
import com.dat.Dialog.QuestionUpdate;
import com.dat.Dialog.Success;
import com.dat.Product.Product;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;


public class UpdateForm extends javax.swing.JPanel {
    List<Product> list;
    Question Qs ;
    Success success;
    QuestionUpdate QsU;
    private Product temp;
    private DefaultTableModel tblModel = new DefaultTableModel();
    public UpdateForm(Question Qs, Success sc, QuestionUpdate QsU) {
        initComponents();
        initTable();
        btnRemove.setEnabled(false);
        btnUpdate.setEnabled(false);
        this.Qs = Qs;
        this.success = sc;
        this.QsU=QsU;
        fillToTable();
    }

    private void initTable(){
        String[] header = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Giá bán"};
        tblModel.setColumnIdentifiers(header);
        tblSP.setModel(tblModel);
    } 
    
    public void fillToTable(){
        try{
            ProductDAO dao = new ProductDAO();
            list=dao.loadListProduct();   
        }catch(Exception e){
            e.printStackTrace();
        }
        
        tblModel.setRowCount(0);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        
        for(Product prd : list){
            String str1 = currencyVN.format(prd.getGiaBan());
            Object[] row = new Object[]{prd.getMaSp(),prd.getTenSp(),prd.getDonViTinh(),str1};
            tblModel.addRow(row);
        }
        tblModel.fireTableDataChanged();
    }
    public void resetForm(){
        txtMasp.setText("");
        txtTensp.setText("");
        txtDVT.setText("");
        txtGia.setText("");
    }
    
    public String chuanhoaMa(String ma){
        return ma.replaceAll(" ", "");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnRemove = new com.dat.Swing.ButtonCustom();
        jLabel2 = new javax.swing.JLabel();
        txtMasp = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTensp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDVT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        lblThongbao = new javax.swing.JLabel();
        btnUpdate = new com.dat.Swing.ButtonCustom();
        btnSearch = new com.dat.Swing.ButtonCustom();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        jLabel1.setText("Danh sách sản phẩm:");

        btnRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/remove.png"))); // NOI18N
        btnRemove.setText("Xóa");
        btnRemove.setBorderColor(new java.awt.Color(102, 255, 255));
        btnRemove.setColorOver(new java.awt.Color(153, 255, 255));
        btnRemove.setRadius(10);
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        jLabel2.setText("Mã sản phẩm");

        txtMasp.setEditable(false);

        jLabel3.setText("Tên sản phẩm");

        jLabel4.setText("Đơn vị tính:");

        jLabel5.setText("Giá bán");

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
        tblSP.setSelectionBackground(new java.awt.Color(204, 255, 255));
        tblSP.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSP);

        jLabel6.setText("Tìm kiếm theo mã sản phẩm:");

        lblThongbao.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblThongbao.setForeground(new java.awt.Color(255, 0, 0));

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-update-24.png"))); // NOI18N
        btnUpdate.setText("Cập nhật");
        btnUpdate.setBorderColor(new java.awt.Color(102, 255, 255));
        btnUpdate.setColorOver(new java.awt.Color(102, 255, 255));
        btnUpdate.setRadius(10);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1)
                                    .addComponent(lblThongbao, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 189, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDVT)
                                    .addComponent(txtMasp)
                                    .addComponent(txtTensp)
                                    .addComponent(txtGia)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2))
                                        .addGap(0, 338, Short.MAX_VALUE)))
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMasp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDVT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(258, 258, 258))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblThongbao, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        int row = tblSP.getSelectedRow();
        if(row>=0){
            temp = list.get(row);
            txtMasp.setText(tblSP.getValueAt(row,0).toString());
            txtTensp.setText(tblSP.getValueAt(row,1).toString());
            txtDVT.setText(tblSP.getValueAt(row,2).toString());
            txtGia.setText(Integer.toString(temp.getGiaBan()));
            btnRemove.setEnabled(true);
            btnUpdate.setEnabled(true);
            
        }
    }//GEN-LAST:event_tblSPMouseClicked

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed

        Qs.setContent("Thông tin sản phẩm:",temp.toString(),"Bạn có muốn xóa sản phẩm không?");
        Qs.setVisible(true);
        if(Qs.isYn()){
            try{
                ProductDAO dao = new ProductDAO();
                dao.delete(txtMasp.getText());
                success.setContent("Bạn đã xóa sản phẩm thành công.");
                success.setVisible(true);
                resetForm();
                fillToTable();
                btnRemove.setEnabled(false);
                btnUpdate.setEnabled(false);
                tblSP.setRowSelectionInterval(0,0);
            }catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        Product prd = new Product();
        prd.setMaSp(txtMasp.getText());
        prd.setTenSp(txtTensp.getText());
        prd.setDonViTinh(txtDVT.getText());
        prd.setSlTon(temp.getSlTon());
        prd.setGiaBan(Integer.parseInt(txtGia.getText()));

        QsU.setContent(temp.toString(),prd.toString(),"Thông tin sản phẩm:", "Bạn có muốn cập nhật thông tin sản phẩm không?");
        QsU.setVisible(true);
        if(QsU.isYn()){
            try{
                ProductDAO dao = new ProductDAO();
                dao.updateProduct(prd);
                success.setContent("Bạn đã cập nhật thông tin sản phẩm thành công.");
                success.setVisible(true);
                resetForm();
                fillToTable();
                btnRemove.setEnabled(false);
                btnUpdate.setEnabled(false);
                tblSP.setRowSelectionInterval(0,0);
            }catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        if(chuanhoaMa(txtSearch.getText()).equals("")){
            lblThongbao.setText("*Vui lòng nhập mã sản phẩm cần tìm!");
            lblThongbao.setForeground(new java.awt.Color(255, 51, 51));
        }else{
            
            try{
            ProductDAO dao = new ProductDAO();
            temp = dao.findProductByMa(chuanhoaMa(txtSearch.getText()));
            if(temp==null){
                lblThongbao.setText("*Sản phẩm không tồn tại.");
                lblThongbao.setForeground(new java.awt.Color(255, 51, 51));
            }else{
               lblThongbao.setText("");
               txtMasp.setText(temp.getMaSp());
               txtTensp.setText(temp.getTenSp());
               txtDVT.setText(temp.getDonViTinh());
               txtGia.setText(Integer.toString(temp.getGiaBan()));
               int row = dao.selectRowTable(chuanhoaMa(txtSearch.getText().toUpperCase()));
               tblSP.setRowSelectionInterval(row,row);
               txtSearch.setText("");
               btnRemove.setEnabled(true);
               btnUpdate.setEnabled(true);
            }
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
    }//GEN-LAST:event_btnSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.dat.Swing.ButtonCustom btnRemove;
    private com.dat.Swing.ButtonCustom btnSearch;
    private com.dat.Swing.ButtonCustom btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblThongbao;
    private javax.swing.JTable tblSP;
    private javax.swing.JTextField txtDVT;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMasp;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTensp;
    // End of variables declaration//GEN-END:variables
}
