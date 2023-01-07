
package com.dat.MainForm;

import DAO.UserDAO;
import com.dat.Dialog.Question;
import com.dat.Dialog.QuestionUpdate;
import com.dat.Dialog.Success;
import com.dat.Dialog.Warning;
import com.dat.User.User;
import java.util.List;
import javax.swing.table.DefaultTableModel;


public class QLNV extends javax.swing.JPanel {
    DefaultTableModel model = new DefaultTableModel();
    private List<User> users;
    Warning WarningError;
    Question Qs ;
    Success success;
    QuestionUpdate QsU;
    private User temp;
    boolean addStatus = false;
    public QLNV(Warning WarningError,Question Qs, Success sc, QuestionUpdate QsU) {
        initComponents();
        setOpaque(false);
        this.WarningError = WarningError;
        this.Qs = Qs;
        this.success = success;
        this.QsU = QsU;
        initTable();
        
        btnAdd.setEnabled(false);
        btnRemove.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnRenew.setEnabled(false);
        loadNV();
        fillToTable();
    }
    
    public void initTable(){
        String[] header = new String[]{"Mã nhân viên", "Tên viên", "Account", "Password"};
        model.setColumnIdentifiers(header);
        tblNhanvien.setModel(model);
        
    }
    
   
   public void loadNV(){
       try{
           UserDAO dao = new UserDAO();
           users = dao.loadUser();
           
       }catch(Exception ex){
           
       }
       
   }
   
   public void fillToTable(){
       model.setRowCount(0);
       for(User user: users){
           if(user.getPermission().equals("employee")){
                Object[] row = new Object[]{user.getMaNV(),user.getTenNV(),user.getAccount(),user.getPassword()};
                model.addRow(row);
           }
       }
   }
   //Kiểm tra trùng tên nhân viên
    public boolean checkEqualName(String name){
        User  t = this.users.stream().filter(pr -> pr.getTenNV().equals(name)).findFirst().orElse(null);
        if(t == null)return false;
        return true;
    }
    //Kiểm tra trùng mã nhân viên
    public boolean checkEqualMasp(String manv){
        User  t = this.users.stream().filter(pr -> pr.getMaNV().equals(manv)).findFirst().orElse(null);
        if(t == null)return false;
        return true;
    }
    
    //Chuẩn hóa tên nhân viên
    public String chuanhoaTennv(String ma){
        ma = ma.trim();
        return ma.replaceAll("\\s+", " ");
    }
    // chuẩn hóa mã nhân viên
   public String chuanhoaMa(String ma){
        return ma.replaceAll(" ", "").toUpperCase();
    }
   public int selectRowTable(User user){
        if(user!= null){
            int i = 0;
            for(User or : users){
                boolean check = or.getMaNV().equals(user.getMaNV());
                if(check){
                    return i;
                }
                i++;
            }
        }
        return -1;
    }
   public void resetForm(){
        txtMaNV.setText("");
        txtName.setText("");
        txtAccount.setText("");
        txtPass.setText("");
    }
    
    public User getInfoUser(){
         
        User prd = new User();
        prd.setMaNV(chuanhoaMa(txtMaNV.getText()));
        prd.setTenNV(chuanhoaTennv(txtName.getText()));
        //prd.setPermission(chuanhoaMa(getText()).equals(""))
        prd.setAccount(chuanhoaMa(txtAccount.getText()));
        prd.setPassword(txtPass.getText());
        return prd;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanvien = new javax.swing.JTable();
        btnUpdate = new com.dat.Swing.ButtonCustom();
        btnAdd = new com.dat.Swing.ButtonCustom();
        btnRemove = new com.dat.Swing.ButtonCustom();
        btnRenew = new com.dat.Swing.ButtonCustom();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtAccount = new javax.swing.JTextField();
        txtPass = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new com.dat.Swing.ButtonCustom();
        lblThongBao = new javax.swing.JLabel();
        lblIconmnv = new javax.swing.JLabel();
        lblIconname = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblNhanvien.setModel(new javax.swing.table.DefaultTableModel(
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
        tblNhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanvienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanvien);

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-update-24.png"))); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.setRadius(10);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add-new.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.setRadius(10);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-remove-24.png"))); // NOI18N
        btnRemove.setText("Xóa");
        btnRemove.setRadius(10);
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnRenew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-renew-24.png"))); // NOI18N
        btnRenew.setText("Làm mới");
        btnRenew.setRadius(10);
        btnRenew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenewActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã nhân viên:");

        jLabel2.setText("Tên nhân viên:");

        jLabel3.setText("Account:");

        jLabel4.setText("Password:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã nhân viên", "Tên" }));

        jLabel5.setText("Danh sách nhân viên:");

        jLabel6.setText("Sắp xếp:");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/magnifying-glass.png"))); // NOI18N
        btnSearch.setToolTipText("");
        btnSearch.setBorderColor(new java.awt.Color(255, 255, 255));
        btnSearch.setColorClick(new java.awt.Color(0, 255, 255));
        btnSearch.setColorOver(new java.awt.Color(51, 255, 255));
        btnSearch.setRadius(10);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        lblThongBao.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        lblIconmnv.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        lblIconname.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(76, 76, 76)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2))
                                        .addGap(2, 2, 2))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(29, 29, 29)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                            .addComponent(txtAccount, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                            .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                            .addComponent(txtMaNV))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblIconmnv, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                                            .addComponent(lblIconname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnRenew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(89, 89, 89))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap()))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(89, 89, 89))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIconmnv, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIconname, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAccount)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPass)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnRenew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        if(chuanhoaMa(txtSearch.getText()).equals("")){
            lblThongBao.setText("*Vui lòng nhập mã nhân viên cần tìm!");
            lblThongBao.setForeground(new java.awt.Color(255, 51, 51));
        }else{
            
            try{
            UserDAO dao = new UserDAO();
            User temp = dao.findUser(chuanhoaMa(txtSearch.getText()));
            if(temp==null){
                lblThongBao.setText("*Nhân viên không tồn tại.");
                lblThongBao.setForeground(new java.awt.Color(255, 51, 51));
            }else{
               lblThongBao.setText("");
               int row = selectRowTable(temp);
               tblNhanvien.setRowSelectionInterval(row,row);
               txtSearch.setText("");
               btnAdd.setEnabled(true);
            }
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed

    }//GEN-LAST:event_txtSearchActionPerformed

    private void tblNhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanvienMouseClicked
        int row = tblNhanvien.getSelectedRow();
        if(row>=0){
            temp = users.get(row);
            txtMaNV.setText(tblNhanvien.getValueAt(row,0).toString());
            txtName.setText(tblNhanvien.getValueAt(row,1).toString());
            txtAccount.setText(tblNhanvien.getValueAt(row,2).toString());
            txtPass.setText(tblNhanvien.getValueAt(row,3).toString());
             btnAdd.setEnabled(true);
            btnRemove.setEnabled(true);
            btnUpdate.setEnabled(true);
            btnRenew.setEnabled(true);
            
        }
    }//GEN-LAST:event_tblNhanvienMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try{
            if(addStatus == false){
                WarningError.setContent("Vui lòng điền đủ thông tin yêu cầu!");
                WarningError.setVisible(true);
            }else{
                User nv = getInfoUser();
                Qs.setContent(nv.toString(),"Bạn có muốn thêm nhân viên không?");
                Qs.setVisible(true);
                if(Qs.isYn()){
                    UserDAO dao =new UserDAO();
                    dao.addUser(nv);
                    resetForm();
                    lblIconmnv.setIcon(null);
                    lblIconname.setIcon(null);
                    fillToTable();
                    success.setContent("Bạn đã thêm nhân viên thành công.");
                    success.setVisible(true);
                    addStatus=false;
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        Qs.setContent(temp.toString(),"Bạn có muốn xóa nhân viên không?");
        Qs.setVisible(true);
        if(Qs.isYn()){
            try{
                UserDAO dao = new UserDAO();
                dao.deleteUser(txtMaNV.getText());
                success.setContent("Bạn đã xóa nhân viên thành công.");
                success.setVisible(true);
                resetForm();
                fillToTable();
                btnAdd.setEnabled(false);
            btnRemove.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnRenew.setEnabled(false);
                tblNhanvien.setRowSelectionInterval(0,0);
            }catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        User prd = new User();
        prd.setMaNV(txtMaNV.getText());
        prd.setTenNV(txtName.getText());
        prd.setAccount(txtAccount.getText());
        prd.setPassword(txtPass.getText());

        QsU.setContent(temp.toString(),prd.toString());
        
        QsU.setVisible(true);
        if(QsU.isYn()){
            try{
                UserDAO dao = new UserDAO();
                dao.updateUser(prd);
                success.setContent("Bạn đã thêm thông tin nhân viên thành công.");
                success.setVisible(true);
                resetForm();
                fillToTable();
                btnAdd.setEnabled(false);
                btnRemove.setEnabled(false);
                btnUpdate.setEnabled(false);
                btnRenew.setEnabled(false);
                tblNhanvien.setRowSelectionInterval(0,0);
            }catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRenewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenewActionPerformed
        resetForm();
    }//GEN-LAST:event_btnRenewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.dat.Swing.ButtonCustom btnAdd;
    private com.dat.Swing.ButtonCustom btnRemove;
    private com.dat.Swing.ButtonCustom btnRenew;
    private com.dat.Swing.ButtonCustom btnSearch;
    private com.dat.Swing.ButtonCustom btnUpdate;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIconmnv;
    private javax.swing.JLabel lblIconname;
    private javax.swing.JLabel lblThongBao;
    private javax.swing.JTable tblNhanvien;
    private javax.swing.JTextField txtAccount;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
