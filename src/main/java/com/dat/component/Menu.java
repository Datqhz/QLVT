    
package com.dat.component;


import com.dat.Dialog.QsDelete;
import com.dat.Dialog.Question;
import com.dat.Dialog.QuestionOrder;
import com.dat.Dialog.QuestionUpdate;
import com.dat.Dialog.Success;
import com.dat.Dialog.Warning;
import com.dat.MainForm.Add;
import com.dat.MainForm.ListTheOrders;
import com.dat.MainForm.TaoDonHangForm;
import com.dat.MainForm.DSDonHangForm;
import com.dat.MainForm.MainForm;
import com.dat.MainForm.TheOrders;
import com.dat.MainForm.UpdateForm;
import com.dat.MainForm.KhoForm;
import com.dat.MainForm.QLNV;
import com.dat.User.User;
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
    QsDelete dl;
    private Add addForm ;
    private UpdateForm updateForm;
    private TaoDonHangForm tdh;
    private DSDonHangForm dsdh;
    private KhoForm kho;
    private TheOrders dathang;
    private ListTheOrders dsdathang;
    private User user;
    private int functionIsSelected = 0;
    
    public Menu(MainForm main, Warning WarningError, Question Qs,  Success sc, QuestionUpdate QsU,QuestionOrder QsO,QsDelete dl, User user) {
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
        this.dl=dl;
        this.user = user;
        lblUser.setText(this.user.getTenNV());
        if(user.getPermission().equals("owner")){
            btnQuanly.setEnabled(true);
        }
        lblPermision.setText(user.getPermission());
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
    public void disableSelected(){
        switch (functionIsSelected){
            case 1:
                lblAdd.setOpaque(false);
                addForm = null;
                break;
            case 2:
                lblUpdate.setOpaque(false);
                updateForm = null;
                break;
            case 3:
                lblCreate.setOpaque(false);
                tdh = null;
                break;
            case 4:
                lblList.setOpaque(false);
                dsdh =null;
                break;
            case 5:
                lblWarehouse.setOpaque(false);
                kho =null;
                break;
            case 6:
                lblTheOrders.setOpaque(false);
                dathang=null;
                break;
            case 7:
                lblListTheOrders.setOpaque(false);
                dsdathang=null;
                break;
            default: 
                break;
        }
    }
    public void changeForm(){
        switch (functionIsSelected){
            case 1:
                lblAdd.setOpaque(true);
                this.addForm =  new Add( WarningError,Qs , sc);
                break;
            case 2:
                lblUpdate.setOpaque(true);
                this.updateForm  = new UpdateForm(Qs,sc,QsU);
                break;
            case 3:
                lblCreate.setOpaque(true);
                this.tdh = new TaoDonHangForm(WarningError,QsO , sc,user.getMaNV());
                break;
            case 4:
                lblList.setOpaque(true);
                this.dsdh = new DSDonHangForm(dl,sc);
                break;
            case 5:
                lblWarehouse.setOpaque(true);
                this.kho = new KhoForm();
                break;
            case 6:
                lblTheOrders.setOpaque(true);
                this.dathang = new TheOrders(WarningError,QsO , sc,user);
                break;
            case 7:
                lblListTheOrders.setOpaque(true);
                this.dsdathang = new ListTheOrders(WarningError,dl , sc,user);
                break;
            default:
                break;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        pnHome = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pnMenu = new javax.swing.JPanel();
        lblAdd = new javax.swing.JLabel();
        lblUpdate = new javax.swing.JLabel();
        lblCreate = new javax.swing.JLabel();
        lblList = new javax.swing.JLabel();
        lblWarehouse = new javax.swing.JLabel();
        lblTheOrders = new javax.swing.JLabel();
        lblListTheOrders = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblPermision = new javax.swing.JLabel();
        btnQuanly = new com.dat.Swing.ButtonCustom();
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
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnHomeLayout.setVerticalGroup(
            pnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHomeLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnMenu.setBackground(new java.awt.Color(51, 204, 255));

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

        lblUpdate.setBackground(new java.awt.Color(11, 13, 171));
        lblUpdate.setForeground(new java.awt.Color(255, 255, 255));
        lblUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-update-24 (1).png"))); // NOI18N
        lblUpdate.setText("Cập nhật sản phẩm");
        lblUpdate.setToolTipText("");
        lblUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUpdateMouseClicked(evt);
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
        lblListTheOrders.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add-box-insert-button-plus.png"))); // NOI18N
        lblListTheOrders.setText("Danh sách đơn đặt hàng");
        lblListTheOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblListTheOrdersMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Name:");

        lblUser.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblUser.setForeground(new java.awt.Color(255, 255, 255));
        lblUser.setText(" ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Permision:");

        lblPermision.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblPermision.setForeground(new java.awt.Color(255, 255, 255));
        lblPermision.setText("  ");

        btnQuanly.setForeground(new java.awt.Color(255, 255, 255));
        btnQuanly.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-admin-settings-male-30 (1).png"))); // NOI18N
        btnQuanly.setText("Quản lí nhân viên");
        btnQuanly.setBorderColor(new java.awt.Color(0, 51, 255));
        btnQuanly.setColor(new java.awt.Color(51, 51, 255));
        btnQuanly.setColorClick(new java.awt.Color(0, 0, 255));
        btnQuanly.setColorOver(new java.awt.Color(0, 51, 255));
        btnQuanly.setEnabled(false);
        btnQuanly.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQuanly.setOpaque(true);
        btnQuanly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanlyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblCreate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTheOrders, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblWarehouse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblListTheOrders, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addComponent(lblList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnMenuLayout.createSequentialGroup()
                        .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPermision, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnQuanly, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuanly, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUser)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPermision)
                    .addComponent(jLabel5))
                .addGap(31, 31, 31))
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
        disableSelected();
        functionIsSelected = 1;
        changeForm();
        
        main.showForm(addForm);
        
        
    }//GEN-LAST:event_lblAddMouseClicked

    private void lblUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUpdateMouseClicked
        disableSelected();
        functionIsSelected = 2;
        changeForm();
        
        main.showForm(updateForm);
    }//GEN-LAST:event_lblUpdateMouseClicked

    private void lblCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCreateMouseClicked
        disableSelected();
        functionIsSelected = 3;
        changeForm();
        
        main.showForm(tdh);
    }//GEN-LAST:event_lblCreateMouseClicked

    private void lblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblListMouseClicked
        disableSelected();
        functionIsSelected = 4;
        changeForm();
        
        main.showForm(dsdh);
    }//GEN-LAST:event_lblListMouseClicked

    private void lblWarehouseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblWarehouseMousePressed
        disableSelected();
        functionIsSelected = 5;
        changeForm();
        
        main.showForm(kho);
    }//GEN-LAST:event_lblWarehouseMousePressed

    private void lblTheOrdersMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTheOrdersMousePressed
        disableSelected();
        functionIsSelected = 6;
        changeForm();
        
        main.showForm(dathang);
    }//GEN-LAST:event_lblTheOrdersMousePressed

    private void lblListTheOrdersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblListTheOrdersMouseClicked
        disableSelected();
        functionIsSelected = 7;
        changeForm();
        
        main.showForm(dsdathang);
    }//GEN-LAST:event_lblListTheOrdersMouseClicked

    private void btnQuanlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanlyActionPerformed
        main.showForm(new QLNV(WarningError,Qs,sc,QsU));
    }//GEN-LAST:event_btnQuanlyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.dat.Swing.ButtonCustom btnQuanly;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAdd;
    private javax.swing.JLabel lblCreate;
    private javax.swing.JLabel lblList;
    private javax.swing.JLabel lblListTheOrders;
    private javax.swing.JLabel lblPermision;
    private javax.swing.JLabel lblTheOrders;
    private javax.swing.JLabel lblUpdate;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lblWarehouse;
    private javax.swing.JPanel pnHome;
    private javax.swing.JPanel pnMenu;
    // End of variables declaration//GEN-END:variables
}
