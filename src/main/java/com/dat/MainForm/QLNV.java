
package com.dat.MainForm;

import DAO.UserDAO;
import com.dat.Dialog.Question;
import com.dat.Dialog.QuestionUpdate;
import com.dat.Dialog.Success;
import com.dat.Dialog.Warning;
import com.dat.User.User;
import java.util.Collections;
import java.util.Comparator;
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
    private int rowSelect;
    
    
    public QLNV(Warning WarningError,Question Qs, Success sc, QuestionUpdate QsU) {
        initComponents();
        setOpaque(false);
        this.WarningError = WarningError;
        this.Qs = Qs;
        this.success = sc;
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
    public boolean checkEqualAccount(String account){
        User  t = this.users.stream().filter(pr -> pr.getTenNV().equals(account)).findFirst().orElse(null);
        if(t == null)return false;
        return true;
    }
    //Kiểm tra trùng mã nhân viên
    public boolean checkEqualMaNV(String manv){
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
   public void resetForm(){
        txtMaNV.setText("");
        txtName.setText("");
        txtAccount.setText("");
        txtPass.setText("");
        tblNhanvien.setRowSelectionInterval(0,0);
        btnAdd.setEnabled(false);
        btnRemove.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnRenew.setEnabled(false);
        
    }
    
    public User getInfoUser(){
         
        User user = new User();
        user.setMaNV(chuanhoaMa(txtMaNV.getText()));
        user.setTenNV(chuanhoaTennv(txtName.getText()));
        user.setPermission("employee");
        user.setAccount(txtAccount.getText().replace(" ",""));
        user.setPassword(txtPass.getText());
        return user;
    }
    
    public User searchUser(String manv){
        rowSelect = 0;
        for( User user: users){
            rowSelect++;
            if(user.getMaNV().equals(manv))
                return user;
        }
        return null;
    }
    
    public boolean checkStatusAdd(){
        if(chuanhoaMa(txtMaNV.getText()).equals("")||chuanhoaTennv(txtName.getText()).equals("")||chuanhoaMa(txtAccount.getText()).equals("")||chuanhoaMa(txtPass.getText()).equals("")) return false;
        return true;
    }
    
    public void sortByName(){
        
         Comparator<User> com = new Comparator<User>(){
            @Override
            public int compare(User o1, User o2){
                return o1.getTenNV().compareTo(o2.getTenNV());
            }
        };   
        Collections.sort(users, com);
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
        cbxSort = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new com.dat.Swing.ButtonCustom();
        lblThongBao = new javax.swing.JLabel();

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

        txtName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNameFocusGained(evt);
            }
        });

        txtAccount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAccountFocusGained(evt);
            }
        });

        txtPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPassFocusGained(evt);
            }
        });

        cbxSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã nhân viên", "Tên" }));
        cbxSort.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSortItemStateChanged(evt);
            }
        });

        jLabel5.setText("Danh sách nhân viên:");

        jLabel6.setText("Sắp xếp:");

        txtMaNV.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMaNVFocusGained(evt);
            }
        });

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
                                .addComponent(cbxSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                        .addComponent(txtAccount, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                        .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                        .addComponent(txtMaNV)))))
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
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(cbxSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        temp= null;
        if(chuanhoaMa(txtSearch.getText()).equals("")){
            lblThongBao.setText("*Vui lòng nhập mã nhân viên cần tìm!");
            lblThongBao.setForeground(new java.awt.Color(255, 51, 51));
        }else{
            temp = searchUser(chuanhoaMa(txtSearch.getText()));
            if(temp==null){
                lblThongBao.setText("*Nhân viên không tồn tại.");
                lblThongBao.setForeground(new java.awt.Color(255, 51, 51));
            }else{
               lblThongBao.setText("");
               tblNhanvien.setRowSelectionInterval(rowSelect-1,rowSelect-1);
               txtSearch.setText("");
               txtMaNV.setText(tblNhanvien.getValueAt(rowSelect-1,0).toString());
               txtName.setText(tblNhanvien.getValueAt(rowSelect-1,1).toString());
               txtAccount.setText(tblNhanvien.getValueAt(rowSelect-1,2).toString());
               txtPass.setText(tblNhanvien.getValueAt(rowSelect-1,3).toString());
               btnAdd.setEnabled(true);
               btnRemove.setEnabled(true);
               btnUpdate.setEnabled(true);
               btnRenew.setEnabled(true);
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
            if(!checkStatusAdd()){
                WarningError.setContent("Vui lòng điền đủ thông tin yêu cầu!");
                WarningError.setVisible(true);
            }else if(checkEqualMaNV(chuanhoaMa(txtMaNV.getText()))){
                WarningError.setContent("Mã nhân viên đã tồn tại!");
                WarningError.setVisible(true);
            }else if(checkEqualAccount(chuanhoaMa(txtAccount.getText()))){
                WarningError.setContent("Account đã tồn tại!");
                WarningError.setVisible(true);
            }else{
                User nv = getInfoUser();
                Qs.setContent("Thông tin nhân viên:",nv.toString(),"Bạn có muốn thêm nhân viên không?");
                Qs.setVisible(true);
                if(Qs.isYn()){
                    UserDAO dao =new UserDAO();
                    dao.addUser(nv);
                    success.setContent("Bạn đã thêm nhân viên thành công.");
                    success.setVisible(true);
                    loadNV();
                    resetForm();
                    fillToTable();
                    
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        Qs.setContent("Thông tin nhân viên:",temp.toString(),"Bạn có muốn xóa nhân viên không?");
        Qs.setVisible(true);
        if(Qs.isYn()){
            try{
                UserDAO dao = new UserDAO();
                dao.deleteUser(txtMaNV.getText());
                success.setContent("Bạn đã xóa nhân viên thành công.");
                success.setVisible(true);
                loadNV();
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
        
        if(!checkStatusAdd()){
                WarningError.setContent("Vui lòng điền đủ thông tin yêu cầu!");
                WarningError.setVisible(true);
            }else if(checkEqualAccount(chuanhoaMa(txtAccount.getText()))){
                WarningError.setContent("Account đã tồn tại!");
                WarningError.setVisible(true);
            }else{
                User user = getInfoUser();
                QsU.setContent(temp.toString(),user.toString(), "Thông tin nhân viên:", "Bạn có muốn cập nhật thông tin nhân viên không?");
        
                QsU.setVisible(true);
                if(QsU.isYn()){
                    try{
                        UserDAO dao = new UserDAO();
                        dao.updateUser(user);
                        success.setContent("Bạn đã cập nhật thông tin nhân viên thành công.");
                        success.setVisible(true);
                        resetForm();
                        loadNV();
                        fillToTable();
                        btnAdd.setEnabled(false);
                        btnRemove.setEnabled(false);
                        btnUpdate.setEnabled(false);
                        btnRenew.setEnabled(false);
                        tblNhanvien.setRowSelectionInterval(0,0);
                    }catch(Exception e){

                    } 
                }
       
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRenewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenewActionPerformed
        resetForm();
    }//GEN-LAST:event_btnRenewActionPerformed

    private void txtMaNVFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaNVFocusGained
        btnAdd.setEnabled(true);
        btnRemove.setEnabled(true);
        btnUpdate.setEnabled(true);
        btnRenew.setEnabled(true);
    }//GEN-LAST:event_txtMaNVFocusGained

    private void txtNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNameFocusGained
        btnAdd.setEnabled(true);
        btnRemove.setEnabled(true);
        btnUpdate.setEnabled(true);
        btnRenew.setEnabled(true);
    }//GEN-LAST:event_txtNameFocusGained

    private void txtAccountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAccountFocusGained
        btnAdd.setEnabled(true);
        btnRemove.setEnabled(true);
        btnUpdate.setEnabled(true);
        btnRenew.setEnabled(true);
    }//GEN-LAST:event_txtAccountFocusGained

    private void txtPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassFocusGained
        btnAdd.setEnabled(true);
        btnRemove.setEnabled(true);
        btnUpdate.setEnabled(true);
        btnRenew.setEnabled(true);
    }//GEN-LAST:event_txtPassFocusGained

    private void cbxSortItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSortItemStateChanged
        String choose = cbxSort.getSelectedItem().toString();
        if(choose.equals("Tên")){
            sortByName();
            fillToTable();
            resetForm();
        }
    }//GEN-LAST:event_cbxSortItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.dat.Swing.ButtonCustom btnAdd;
    private com.dat.Swing.ButtonCustom btnRemove;
    private com.dat.Swing.ButtonCustom btnRenew;
    private com.dat.Swing.ButtonCustom btnSearch;
    private com.dat.Swing.ButtonCustom btnUpdate;
    private javax.swing.JComboBox<String> cbxSort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblThongBao;
    private javax.swing.JTable tblNhanvien;
    private javax.swing.JTextField txtAccount;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
