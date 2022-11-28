
package DAO;

import com.dat.Order.CTSP;
import com.dat.Order.DatHang;
import com.dat.Order.DonHang;
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
        String sql = "{call get_Name_VT(?)}";
        
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
                ct.setMaSP(rs.getString(2));
                ct.setTenSP(getNameVT(ct.getMaSP()));
                ct.setSoLuong(rs.getInt(3));
                ct.setGia(rs.getInt(4));
                listCTPX.add(ct);
            }
            
        }
        return listCTPX;
    }
    //Lấy Danh sách sản phẩm có trong đơn đặt hàng
    public List<CTSP> loadCTDDH(String madon) throws Exception{
        List<CTSP> listCTPX = new ArrayList<>();
        String sql = "select * from CTDDH where MASODDH =?";
        try (
            Connection con = DatabaseHelper.openConnection(); 
            PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setString(1, madon);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                CTSP ct  = new CTSP();
                ct.setMaSP(rs.getString(2));
                ct.setTenSP(getNameVT(ct.getMaSP()));
                ct.setSoLuong(rs.getInt(3));
                ct.setGia(rs.getInt(4));
                listCTPX.add(ct);
            }
            
        }
        return listCTPX;
    }
    
    // lấy danh sách đơn hàng
     public List<DonHang> loadListDonHang() throws Exception {
        List<DonHang> listOrder = new ArrayList<>();
        String sql = "select * from PHIEUXUAT";
        
        try (
            Connection con = DatabaseHelper.openConnection(); 
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);) {
          
            while (rs.next()) {
                DonHang temp = new DonHang();
                temp.setMaDon(chuanhoaMa(rs.getString(1)));
                temp.setDate(rs.getString(2));
                temp.setTenKhachHang(rs.getString(3));
                temp.setListSP(loadCTPX(temp.getMaDon()));
                listOrder.add(temp);
            }
            return listOrder;
        }

    }
    // lấy danh sách đặt hàng
     public List<DatHang> loadListDatHang() throws Exception {
        List<DatHang> listTheOrders = new ArrayList<>();
        String sql = "select * from DATHANG";
        
        try (
            Connection con = DatabaseHelper.openConnection(); 
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);) {
          
            while (rs.next()) {
                DatHang temps = new DatHang();
                temps.setMaDon(chuanhoaMa(rs.getString(1)));
                temps.setDate(rs.getString(2));
                temps.setNhaCungCap(rs.getString(3));
                temps.setListSP(loadCTDDH(temps.getMaDon()));
                listTheOrders.add(temps);
            }
            return listTheOrders;
        }

    }
     
//     public boolean addCTPX(CTSP ct,String MaDon)throws Exception{
//         String sql = "insert into CTPX(MAPX,MAV,SOLUONG,DONGIA) values (?,?,?,?)";
//         try (
//            Connection con = DatabaseHelper.openConnection(); 
//            PreparedStatement pstm = con.prepareStatement(sql);) {
//            pstm.setString(1, MaDon);
//            pstm.setString(2, ct.getMaSP());
//            pstm.setString(3,Integer.toString(ct.getSoLuong()));
//            pstm.setString(4,Integer.toString(ct.getGia()));
//         }
//         return 
//     }
     public void addOrder(DonHang order)throws Exception{
         String sql = "insert into PHIEUXUAT(MAPX,NGAY,HOTENKH) values (?,?,?)";
         String sql1 = "insert into CTPX(MAPX,MAVT,SOLUONG,DONGIA) values (?,?,?,?)";
        try (
                Connection con = DatabaseHelper.openConnection();  
                PreparedStatement pstm = con.prepareStatement(sql);
                PreparedStatement addCT = con.prepareStatement(sql1);) 
        {
            pstm.setString(1, order.getMaDon());
            pstm.setString(2, order.getDate());
            pstm.setString(3, order.getTenKhachHang());
            pstm.executeUpdate();
            for(CTSP ct : order.getListSP()){
                addCT.setString(1, order.getMaDon());
                addCT.setString(2, ct.getMaSP());
                addCT.setString(3,Integer.toString(ct.getSoLuong()));
                addCT.setString(4,Integer.toString(ct.getGia()));
                addCT.executeUpdate();
            }
            
        }
     }
     public void addTheOrders(DatHang theOrders)throws Exception{
         String sql = "insert into DATHANG(MASODDH,NGAY,NHACC) values (?,?,?)";
         String sql1 = "insert into CTDDH(MASODDH,MAVT,SOLUONG,DONGIA) values (?,?,?,?)";
        try (
                Connection con = DatabaseHelper.openConnection();  
                PreparedStatement pstm = con.prepareStatement(sql);
                PreparedStatement addCT = con.prepareStatement(sql1);) 
        {
            pstm.setString(1, theOrders.getMaDon());
            pstm.setString(2, theOrders.getDate());
            pstm.setString(3, theOrders.getNhaCungCap());
            pstm.executeUpdate();
            for(CTSP ct : theOrders.getListSP()){
                addCT.setString(1, theOrders.getMaDon());
                addCT.setString(2, ct.getMaSP());
                addCT.setString(3,Integer.toString(ct.getSoLuong()));
                addCT.setString(4,Integer.toString(ct.getGia()));
                addCT.executeUpdate();
            }
            
        }
     }
     public String chuanhoaMa(String ma){
        return ma.replaceAll(" ", "");
    }
}
