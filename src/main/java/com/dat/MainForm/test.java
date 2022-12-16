
package com.dat.MainForm;

import DAO.UserDAO;
import com.dat.User.User;
import java.util.List;


public class test {
    
    public static void main(String args[]){
        List<User> user = null;
        try{
            UserDAO dao = new UserDAO();
            user = dao.loadUser();
        }catch(Exception e){
            e.printStackTrace();
        }
        for(User i: user){
            System.out.println(i.getAccount()+ " " +i.getPassword());
        }
    }
}
