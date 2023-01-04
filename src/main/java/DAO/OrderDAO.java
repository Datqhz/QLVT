
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
    
    ////////////////////////////////////////////////////////      ĐƠN HÀNG    /////////////////////////////////////////////////////////////////////
    //Lấy Danh sách sản phẩm có trong đơn
    public List<CTSP> loadCTPX(String madon) throws Exception{
        List<CTSP> listCTPX = new ArrayList<>();
        String sql = "{call getCT_PX(?)}";
        try (
            Connection con = DatabaseHelper.openConnection(); 
            PreparedStatement pstm = con.prepareStatement(sql);) {
            pstm.setString(1, madon);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                CTSP ct  = new CTSP();
                ct.setMaSP(rs.getString(1));
                ct.setTenSP(rs.getString(2));
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
        String sql = "select * from PHIEUXUAT with (INDEX(IX_PHIEUXUAT))";
        
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
                temp.setTT(rs.getBoolean(4));
                temp.setMaNV(rs.getString(5));
                listOrder.add(temp);
            }
            return listOrder;
        }

    }
     //thêm đơn hàng
     public void addOrder(DonHang order)throws Exception{
         String sql = "insert into PHIEUXUAT(MAPX,NGAY,HOTENKH,TRANGTHAI,MANV) values (?,?,?,?,?)";
         String sql1 = "insert into CTPX(MAPX,MAVT,SOLUONG,DONGIA) values (?,?,?,?)";
         String updateSLT = "{call updateSLT}";
        try (
                Connection con = DatabaseHelper.openConnection();  
                PreparedStatement pstm = con.prepareStatement(sql);
                PreparedStatement addCT = con.prepareStatement(sql1);
                PreparedStatement update = con.prepareStatement(updateSLT)) 
        {
            pstm.setString(1, order.getMaDon());
            pstm.setString(2, order.getDate());
            pstm.setString(3, order.getTenKhachHang());
            pstm.setString(4,Boolean.toString(order.getTT()));
            pstm.setString(5,order.getMaNV());
            pstm.executeUpdate();
            for(CTSP ct : order.getListSP()){
                addCT.setString(1, order.getMaDon());
                addCT.setString(2, ct.getMaSP());
                addCT.setString(3,Integer.toString(ct.getSoLuong()));
                addCT.setString(4,Integer.toString(ct.getGia()));
                addCT.executeUpdate();
            }
            update.executeUpdate();
        }
     }
     //cập nhật trạng thái đơn hàng
     public void updateTTDonHang(DonHang order)throws Exception{
         String sql = "Update PHIEUXUAT set NGAY=?,HOTENKH=?,TRANGTHAI=? ,MANV=? where MAPX = ?";
         try (
                Connection con = DatabaseHelper.openConnection();  
                PreparedStatement pstm = con.prepareStatement(sql);) 
        {
            pstm.setString(1, order.getDate());
            pstm.setString(2, order.getTenKhachHang());
            pstm.setString(3, Boolean.toString(order.getTT()));
            pstm.setString(4, order.getMaNV());
            pstm.setString(5, order.getMaDon());
            pstm.executeUpdate();
        }
     }
     
     //xóa đơn hàng
     public void deleteDonHang(String madon) throws Exception{
         String sql = "Delete CTPX where MAPX=?";
         String sql1 = "Delete PHIEUXUAT where MAPX=?";
         String updateSLT = "{call updateSLT}";
         try (
                Connection con = DatabaseHelper.openConnection();  
                PreparedStatement deleteCT = con.prepareStatement(sql);
                 PreparedStatement deletePX = con.prepareStatement(sql1);
                 PreparedStatement update = con.prepareStatement(updateSLT)) 
        {
            deleteCT.setString(1, madon);
            deletePX.setString(1, madon);
            deleteCT.executeUpdate();
            deletePX.executeUpdate();
            update.executeUpdate();
        }
     }
     
     //Tìm kiếm đơn hàng bằng mã đơn
    public Order findOrderByMa(String madon) throws Exception{
        String sql = "select * from PHIEUXUAT where MAPX= ?";
        try (
            Connection con = DatabaseHelper.openConnection();  
            PreparedStatement pstm = con.prepareStatement(sql) ;
           )
        {
            pstm.setString(1, madon);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                DonHang prd = new DonHang();
                prd.setMaDon(rs.getString(1).replaceAll(" ", ""));
                prd.setTenKhachHang(rs.getString(2));
                prd.setDate(rs.getString(3));
                prd.setListSP(loadCTPX(madon));
                prd.setTT(rs.getBoolean(4));
                prd.setMaNV(rs.getString(5));
                return prd;
            }
     
        }
        return null;
    }
     
     
     
     
     ////////////////////////////////////////////////////////////////////////////ĐƠN ĐẶT HÀNG//////////////////////////////////////////////////////
     
    //Lấy Danh sách sản phẩm có trong đơn đặt hàng
    public List<CTSP> loadCTDDH(String madon) throws Exception{
        List<CTSP> listCTDDH = new ArrayList<>();
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
                listCTDDH.add(ct);
            }
            
        }
        return listCTDDH;
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
                temps.setTT(rs.getBoolean(4));
                temps.setListSP(loadCTDDH(temps.getMaDon()));
                temps.setMaNV(rs.getString(5));
                listTheOrders.add(temps);
            }
            return listTheOrders;
        }

    }
     
     
     
     //tạo đơn đặt hàng
     public void addTheOrders(DatHang theOrders)throws Exception{
         String sql = "insert into DATHANG(MASODDH,NGAY,NHACC,TRANGTHAI,MANV) values (?,?,?,?,?)";
         String sql1 = "insert into CTDDH(MASODDH,MAVT,SOLUONG,DONGIA) values (?,?,?,?)";
         String updateSLT = "{call updateSLT}";
        try (
                Connection con = DatabaseHelper.openConnection();  
                PreparedStatement pstm = con.prepareStatement(sql);
                PreparedStatement addCT = con.prepareStatement(sql1);
                PreparedStatement update = con.prepareStatement(updateSLT)) 
        {
            pstm.setString(1, theOrders.getMaDon());
            pstm.setString(2, theOrders.getDate());
            pstm.setString(3, theOrders.getNhaCungCap());
            pstm.setString(4,Boolean.toString(theOrders.getTT()));
            pstm.setString(5, theOrders.getMaNV());
            pstm.executeUpdate();
            for(CTSP ct : theOrders.getListSP()){
                addCT.setString(1, theOrders.getMaDon());
                addCT.setString(2, ct.getMaSP());
                addCT.setString(3,Integer.toString(ct.getSoLuong()));
                addCT.setString(4,Integer.toString(ct.getGia()));
                addCT.executeUpdate();
            }
            update.executeUpdate();
        }
     }
     
     public String chuanhoaMa(String ma){
        return ma.replaceAll(" ", "");
    }
     
    
     
     public void addPhieuNhap(String mapn,DatHang theorder, String ngay,String manv)throws Exception{
         String sql = "{call tao_phieu_nhap(?,?,?,?)}";
         String updateDDH = "Update DATHANG set NGAY=?, NHACC=?, TRANGTHAI=?, MANV=? where MASODDH = ?";
           
         String updateSLT = "{call updateSLT}";
         try (
                Connection con = DatabaseHelper.openConnection();  
                PreparedStatement pstm = con.prepareStatement(sql);
                 PreparedStatement updateTTDDH = con.prepareStatement(updateDDH);
                PreparedStatement update = con.prepareStatement(updateSLT)) 
        {
            updateTTDDH.setString(1, theorder.getDate());
            updateTTDDH.setString(2, theorder.getNhaCungCap());
            updateTTDDH.setString(3, Boolean.toString(theorder.getTT()));
            updateTTDDH.setString(5, theorder.getMaDon());
            updateTTDDH.setString(4, theorder.getMaNV());
            ///chua sua tao phieu nhap
            pstm.setString(1, mapn);
            pstm.setString(2, theorder.getMaDon());
            pstm.setString(3, ngay);
            pstm.setString(4,manv);
            pstm.executeUpdate();
            update.executeUpdate();
            updateTTDDH.executeUpdate();
        }
     }

     
     public void deleteDatHang(String madon) throws Exception{
         String sql = "Delete CTDDH where MASODDH=?";
         String sql1 = "Delete DATHANG where MASODDH=?";
         //String updateSLT = "{call updateSLT}";
         try (
                Connection con = DatabaseHelper.openConnection();  
                PreparedStatement deleteCT = con.prepareStatement(sql);
                 PreparedStatement deleteDH = con.prepareStatement(sql1);
                 ) //PreparedStatement update = con.prepareStatement(updateSLT)) 
        {
            deleteCT.setString(1, madon);
            deleteDH.setString(1, madon);
            deleteCT.executeUpdate();
            deleteDH.executeUpdate();
          
        }
     }

     
    
    
    
}
