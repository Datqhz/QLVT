
package DAO;

import com.dat.User.User;
import java.sql.Connection;
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
}
