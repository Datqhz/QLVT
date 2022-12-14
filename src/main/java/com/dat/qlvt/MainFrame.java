
package com.dat.qlvt;
import DAO.UserDAO;
import com.dat.Dialog.LoginFom;
import com.dat.Dialog.QsDelete;
import com.dat.Dialog.Question;
import com.dat.Dialog.QuestionOrder;
import com.dat.Dialog.QuestionUpdate;
import com.dat.Dialog.Success;
import com.dat.Dialog.Warning;
import com.dat.component.Header;
import com.dat.component.Menu;
import net.miginfocom.swing.MigLayout;
import com.dat.MainForm.MainForm;
import com.dat.User.User;
import java.util.List;


public class MainFrame extends javax.swing.JFrame {
    private Menu menu;
    private Header header;
    private MigLayout layout;
    private MainForm main;
    private List<User> users;
    //Dialog add
    public MainFrame() {
        initComponents();
        init();
        this.setLocationRelativeTo(null);
        
    }
    
    private void init(){
        layout =new MigLayout("fill", "0[]0[100%, fill]0","0[fill, top]0");
        background.setLayout(layout);
        header = new Header();
        main = new MainForm();
        loadUser();
        LoginFom login = new LoginFom(this,true,new Warning(this,true),users);
        login.setVisible(true);
        menu = new Menu(main, new Warning(this,true),  new Question(this,true) , new Success(this,true),new QuestionUpdate(this,true),new QuestionOrder(this,true),new QsDelete(this,true),login.getPresent());
        background.add(menu,"h 600!, w 200!, spany 2");
        background.add(header, "h 35!, wrap");
        background.add(main, "w 100%, h 100%");
        
    }
   
    
    public void loadUser(){
        try{
            UserDAO dao = new UserDAO();
            users = dao.loadUser();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        background = new javax.swing.JPanel();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setBackground(new java.awt.Color(244, 244, 243));

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
