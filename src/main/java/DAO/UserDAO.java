
package DAO;

import com.dat.User.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {
    public UserDAO(){
        
    }
    
    public List<User> loadUser ()throws Exception{
        List<User> temp = new ArrayList<>();
        String sql = "SELECT * FROM NHANVIEN";
        
        try (
            Connection con = DatabaseHelper.openConnection(); 
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);) {
          
            while (rs.next()) {
                User temps = new User();
                temps.setMaNV(rs.getString(1).replaceAll(" ", ""));
                temps.setTenNV(rs.getString(2));
                temps.setAccount(rs.getString(3).replaceAll(" ", ""));
                temps.setPassword(rs.getString(4).replaceAll(" ", ""));
                temps.setPermission(rs.getString(5).replaceAll(" ", ""));
                
                temp.add(temps);
            }
            return temp;
        }
    }
    public User findUser(String manv) throws Exception{
        String sql = "select * from NHANVIEN where MANV= ?";
        try (
            Connection con = DatabaseHelper.openConnection();  
            PreparedStatement pstm = con.prepareStatement(sql) ;
           )
        {
            pstm.setString(1, manv);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                User temps = new User();
                temps.setMaNV(rs.getString(1).replaceAll(" ", ""));
                temps.setTenNV(rs.getString(2));
                temps.setAccount(rs.getString(3).replaceAll(" ", ""));
                temps.setPassword(rs.getString(4).replaceAll(" ", ""));
                temps.setPermission(rs.getString(5).replaceAll(" ", ""));
                return temps;
            }
     
        }
        return null;
    }
     //thêm sản phẩm
    public boolean addUser(User prd) throws Exception{
        String sql = "insert into NHANVIEN(MANV,TENNV,ACC,PASS,PER) values (?,?,?,?,?)";
        try (
                Connection con = DatabaseHelper.openConnection();  
                PreparedStatement pstm = con.prepareStatement(sql);) 
        {
            pstm.setString(1, prd.getMaNV());
            pstm.setString(2, prd.getTenNV());
            pstm.setString(3, prd.getAccount());
            pstm.setString(4, prd.getPassword());
            pstm.setString(5, prd.getPermission());
            
            return pstm.executeUpdate() > 0;
        }
    }
    
    
    // cập nhật thông tin sản phẩm
    public boolean updateUser(User prd) throws Exception{
        String sql = "update NHANVIEN set TENNV=?,ACC=?,PASS=?,PER=? where MANV=?";
         try (
                Connection con = DatabaseHelper.openConnection();  
                PreparedStatement pstm = con.prepareStatement(sql);) 
        {
            
            pstm.setString(1, prd.getTenNV());
            pstm.setString(2, prd.getAccount());
            pstm.setString(3, prd.getPassword());
            //pstm.setString(4, prd.getPermission());
            pstm.setString(5, prd.getMaNV());
            return pstm.executeUpdate() > 0;
        }
    }
    
    
    //Xóa sản phẩm
    public boolean deleteUser(String manv) throws Exception {
        String sql = "delete from NHANVIEN where MANV= ?";
        try (
            Connection con = DatabaseHelper.openConnection();  
            PreparedStatement pstm = con.prepareStatement(sql);) 
        {
            pstm.setString(1, manv);
            return pstm.executeUpdate() > 0;
            
        }
    }
}
