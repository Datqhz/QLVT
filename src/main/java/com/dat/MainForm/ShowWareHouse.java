package com.dat.MainForm;

import DAO.ProductDAO;
import com.dat.Product.Product;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;

public class ShowWareHouse extends javax.swing.JPanel {
    List<Product> list = new ArrayList() ;
    DefaultTableModel tblModelSP = new DefaultTableModel();
    private Product temp;
    public ShowWareHouse() {
        initComponents();
        setOpaque(false);
        initTable();
        fillToTable();
    }
public void initTable(){
        String[] header = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Số lượng tồn", "Giá bán"};
        tblModelSP.setColumnIdentifiers(header);
        tblSP.setModel(tblModelSP);
        
    }
//làm mới bảng
private void fillToTable(){
        try{
            ProductDAO dao = new ProductDAO();
            list=dao.loadListProduct();   
           
        }catch(Exception e){
            e.printStackTrace();
        }
        
        DefaultTableModel model = (DefaultTableModel) tblSP.getModel();
        model.setRowCount(0);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        
        for(Product prd : list){
            String str1 = currencyVN.format(prd.getGiaBan());
            Object[] row = new Object[]{prd.getMaSp(),prd.getTenSp(),prd.getDonViTinh(),prd.getSlTon(),str1};
            model.addRow(row);
        }
        model.fireTableDataChanged();
    }   
    public String chuanhoaMa(String ma){
        return ma.replaceAll(" ", "").toUpperCase();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        lblMaVT = new javax.swing.JLabel();
        btnsearch = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        lblThongBao = new javax.swing.JLabel();

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
        jScrollPane1.setViewportView(tblSP);

        lblMaVT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMaVT.setText("Mã vật tư:");

        btnsearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png"))); // NOI18N
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblMaVT, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMaVT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        if(chuanhoaMa(txtsearch.getText()).equals("")){
            lblThongBao.setText("*Vui lòng nhập mã sản phẩm cần tìm!");
            lblThongBao.setForeground(new java.awt.Color(255, 51, 51));
        }else{
            
            try{
            ProductDAO dao = new ProductDAO();
            temp = dao.findProductByMa(chuanhoaMa(txtsearch.getText()));
            if(temp==null){
                lblThongBao.setText("*Sản phẩm không tồn tại.");
                lblThongBao.setForeground(new java.awt.Color(255, 51, 51));
            }else{
                lblThongBao.setText("");
               int row = dao.selectRowTable(chuanhoaMa(txtsearch.getText().toUpperCase()));
               tblSP.setRowSelectionInterval(row,row);
               txtsearch.setText("");
               btnsearch.setEnabled(true);
            }
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
    }//GEN-LAST:event_btnsearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMaVT;
    private javax.swing.JLabel lblThongBao;
    private javax.swing.JTable tblSP;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables
}
