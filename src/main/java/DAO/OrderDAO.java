
package DAO;

import com.dat.Order.CTSP;
import com.dat.Order.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class OrderDAO {

    public OrderDAO() {
    }
    
    
    //Lấy tên VT dùng trong form Tạo đơn
    public String getNameVT(String madon) throws Exception{
        String sql = "EXCEC get_Name ?";
        
        try (
            Connection con = DatabaseHelper.openConnection(); 
            PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setString(1, madon);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
               return rs.getString(2); 
            }
            
        }
        return "";
    }
    
    
    //Lấy Danh sách sản phẩm có trong đơn
    public List<CTSP> loadCTPX(String madon) throws Exception{
        List<CTSP> listCTPX = new ArrayList<>();
        String sql = "select * from CTPX where MAPX =?";
        try (
            Connection con = DatabaseHelper.openConnection(); 
            PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setString(1, madon);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                CTSP ct  = new CTSP();
                ct.setMaSP(rs.getString(1));
                ct.setTenSP(getNameVT(ct.getMaSP()));
                ct.setSoLuong(rs.getInt(3));
                ct.setGia(rs.getInt(4));
                listCTPX.add(ct);
            }
            
        }
        return listCTPX;
    }
    
    
    // lấy danh sách đơn hàng
     public List<Order> loadListOrder() throws Exception {
        List<Order> listOrder = new ArrayList<>();
        String sql = "select * from PHIEUXUAT";
        
        try (
            Connection con = DatabaseHelper.openConnection(); 
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);) {
          
            while (rs.next()) {
                Order temp = new Order();
                temp.setMaDon(rs.getString(1));
                temp.setDate(rs.getString(2));
                temp.setTenKhachHang(rs.getString(3));
                temp.setListSP(loadCTPX(temp.getMaDon()));
                listOrder.add(temp);
            }
            return listOrder;
        }

    }
}
