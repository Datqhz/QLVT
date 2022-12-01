    
package com.dat.component;


import com.dat.DialogAdd.Question;
import com.dat.DialogAdd.QuestionOrder;
import com.dat.DialogAdd.QuestionUpdate;
import com.dat.DialogAdd.Success;
import com.dat.DialogAdd.Warning;
import com.dat.MainForm.Add;
import com.dat.MainForm.ListTheOrders;
import com.dat.MainForm.CreateOrder;
import com.dat.MainForm.ListOrder;
import com.dat.MainForm.MainForm;
import com.dat.MainForm.TheOrders;
import com.dat.MainForm.Remove;
import com.dat.MainForm.ShowWareHouse;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class Menu extends javax.swing.JPanel {
    Question Qs ;
    Success sc;
    Warning WarningError;
    QuestionUpdate QsU;
    MainForm main;
    QuestionOrder QsO;
    public Menu(MainForm main, Warning WarningError, Question Qs,  Success sc, QuestionUpdate QsU,QuestionOrder QsO) {
        initComponents();
        this.main = main;
        setOpaque(false);
        pnHome.setOpaque(false);
        pnMenu.setOpaque(false);
        this.WarningError = WarningError;
        this.Qs = Qs;
        this.sc = sc;
        this.QsU=QsU;
        this.QsO=QsO;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, 0, new Color(33,105, 249), getWidth(),0 ,new Color(93,58,196));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g); 
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        pnHome = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pnMenu = new javax.swing.JPanel();
        lblAdd = new javax.swing.JLabel();
        lblRemove = new javax.swing.JLabel();
        lblCreate = new javax.swing.JLabel();
        lblList = new javax.swing.JLabel();
        lblWarehouse = new javax.swing.JLabel();
        lblTheOrders = new javax.swing.JLabel();
        lblListTheOrders = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 4, 1));
        setMaximumSize(new java.awt.Dimension(200, 1));

        pnHome.setBackground(new java.awt.Color(204, 255, 255));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/box (1).png"))); // NOI18N
        jLabel2.setText("Quản lí đồ điện tử");

        javax.swing.GroupLayout pnHomeLayout = new javax.swing.GroupLayout(pnHome);
        pnHome.setLayout(pnHomeLayout);
        pnHomeLayout.setHorizontalGroup(
            pnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        pnHomeLayout.setVerticalGroup(
            pnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHomeLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblAdd.setBackground(new java.awt.Color(11, 13, 171));
        lblAdd.setForeground(new java.awt.Color(255, 255, 255));
        lblAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add-box-insert-button-plus.png"))); // NOI18N
        lblAdd.setText("Thêm sản phẩm");
        lblAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAddMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblAddMouseEntered(evt);
            }
        });

        lblRemove.setBackground(new java.awt.Color(11, 13, 171));
        lblRemove.setForeground(new java.awt.Color(255, 255, 255));
        lblRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-update-24 (1).png"))); // NOI18N
        lblRemove.setText("Cập nhật sản phẩm");
        lblRemove.setToolTipText("");
        lblRemove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblRemove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRemoveMouseClicked(evt);
            }
        });

        lblCreate.setBackground(new java.awt.Color(11, 13, 171));
        lblCreate.setForeground(new java.awt.Color(255, 255, 255));
        lblCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/create.png"))); // NOI18N
        lblCreate.setText("Tạo đơn hàng");
        lblCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCreateMouseClicked(evt);
            }
        });

        lblList.setBackground(new java.awt.Color(11, 13, 171));
        lblList.setForeground(new java.awt.Color(255, 255, 255));
        lblList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/list (1).png"))); // NOI18N
        lblList.setText("Danh sách đơn hàng");
        lblList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblListMouseClicked(evt);
            }
        });

        lblWarehouse.setBackground(new java.awt.Color(11, 13, 171));
        lblWarehouse.setForeground(new java.awt.Color(255, 255, 255));
        lblWarehouse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/warehouse.png"))); // NOI18N
        lblWarehouse.setText("Kho");
        lblWarehouse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblWarehouse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblWarehouseMousePressed(evt);
            }
        });

        lblTheOrders.setBackground(new java.awt.Color(11, 13, 171));
        lblTheOrders.setForeground(new java.awt.Color(255, 255, 255));
        lblTheOrders.setIcon(new javax.swing.ImageIcon(getClass().getResource("/download.png"))); // NOI18N
        lblTheOrders.setText("Đặt hàng");
        lblTheOrders.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTheOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblTheOrdersMousePressed(evt);
            }
        });

        lblListTheOrders.setBackground(new java.awt.Color(11, 13, 171));
        lblListTheOrders.setForeground(new java.awt.Color(255, 255, 255));
        lblListTheOrders.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-import-goods-24.png"))); // NOI18N
        lblListTheOrders.setText("Danh sách đơn đặt hàng");
        lblListTheOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblListTheOrdersMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblRemove, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addComponent(lblCreate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTheOrders, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblWarehouse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblListTheOrders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblList, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblWarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTheOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblListTheOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel1.setMinimumSize(new java.awt.Dimension(200, 1));
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddMouseEntered

    }//GEN-LAST:event_lblAddMouseEntered

    private void lblAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddMouseClicked
        lblRemove.setOpaque(false);
        lblCreate.setOpaque(false);
        lblList.setOpaque(false);
        lblWarehouse.setOpaque(false);
        lblTheOrders.setOpaque(false);
        lblListTheOrders.setOpaque(false);
        lblAdd.setOpaque(true);
        
        main.showForm(new Add( WarningError,Qs , sc));
        
        
    }//GEN-LAST:event_lblAddMouseClicked

    private void lblRemoveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRemoveMouseClicked
        lblAdd.setOpaque(false);
        lblCreate.setOpaque(false);
        lblList.setOpaque(false);
        lblWarehouse.setOpaque(false);
        lblTheOrders.setOpaque(false);
        lblListTheOrders.setOpaque(false);
        lblRemove.setOpaque(true);
        main.showForm(new Remove(Qs,sc,QsU));
    }//GEN-LAST:event_lblRemoveMouseClicked

    private void lblCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCreateMouseClicked
        lblRemove.setOpaque(false);
        lblAdd.setOpaque(false);
        lblList.setOpaque(false);
        lblWarehouse.setOpaque(false);
        lblTheOrders.setOpaque(false);
        lblListTheOrders.setOpaque(false);
        lblCreate.setOpaque(true);
        main.showForm(new CreateOrder(WarningError,QsO , sc));
    }//GEN-LAST:event_lblCreateMouseClicked

    private void lblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblListMouseClicked
        lblRemove.setOpaque(false);
        lblCreate.setOpaque(false);
        lblAdd.setOpaque(false);
        lblWarehouse.setOpaque(false);
        lblTheOrders.setOpaque(false);
        lblListTheOrders.setOpaque(false);
        lblList.setOpaque(true);
        main.showForm(new ListOrder());
    }//GEN-LAST:event_lblListMouseClicked

    private void lblWarehouseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblWarehouseMousePressed
        lblTheOrders.setOpaque(false);
        lblRemove.setOpaque(false);
        lblCreate.setOpaque(false);
        lblList.setOpaque(false);
        lblAdd.setOpaque(false);
        lblListTheOrders.setOpaque(false);
        lblWarehouse.setOpaque(true);
        main.showForm(new ShowWareHouse());
    }//GEN-LAST:event_lblWarehouseMousePressed

    private void lblTheOrdersMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTheOrdersMousePressed
         lblRemove.setOpaque(false);
        lblCreate.setOpaque(false);
        lblList.setOpaque(false);
        lblAdd.setOpaque(false);
        lblWarehouse.setOpaque(false);
        lblListTheOrders.setOpaque(false);
        lblTheOrders.setOpaque(true);
        main.showForm(new TheOrders(WarningError,QsO , sc));
    }//GEN-LAST:event_lblTheOrdersMousePressed

    private void lblListTheOrdersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblListTheOrdersMouseClicked
        lblRemove.setOpaque(false);
        lblCreate.setOpaque(false);
        lblList.setOpaque(false);
        lblAdd.setOpaque(false);
        lblWarehouse.setOpaque(false);
        lblTheOrders.setOpaque(false);
        lblListTheOrders.setOpaque(true);
        main.showForm(new ListTheOrders());
    }//GEN-LAST:event_lblListTheOrdersMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel lblAdd;
    private javax.swing.JLabel lblCreate;
    private javax.swing.JLabel lblList;
    private javax.swing.JLabel lblListTheOrders;
    private javax.swing.JLabel lblRemove;
    private javax.swing.JLabel lblTheOrders;
    private javax.swing.JLabel lblWarehouse;
    private javax.swing.JPanel pnHome;
    private javax.swing.JPanel pnMenu;
    // End of variables declaration//GEN-END:variables
}
