
package DAO;

import com.dat.Product.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProductDAO {

    public ProductDAO() {
    }
    
    //Lấy danh sách sản phẩm có trong bảng VATTU từ database
    public List<Product> loadListProduct() throws Exception {
        List<Product> lstProduct = new ArrayList<>();
        String sql = "select * from VATTU";
        try (
            Connection con = DatabaseHelper.openConnection(); 
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);) {
          
            while (rs.next()) {
                Product sp = new Product();
                sp.setMaSp(rs.getString(1));
                sp.setTenSp(rs.getString(2));
                sp.setDonViTinh(rs.getString(3));
                sp.setSlTon(rs.getInt(4));
                sp.setGiaBan(rs.getInt(5));
                lstProduct.add(sp);
            }
            return lstProduct;
        }

    }
    public boolean updateSLT()throws Exception{
        String sql ="{call updateSLT()}";
        try (
            Connection con = DatabaseHelper.openConnection(); 
            Statement stmt = con.createStatement();){
            return stmt.executeUpdate(sql)<0;
        }
            
        
    }
     //thêm sản phẩm
    public boolean addProduct(Product prd) throws Exception{
        String sql = "insert into VATTU(MAVT,TENVT,DVT,SOLUONGTON,GIABAN) values (?,?,?,?,?)";
        try (
                Connection con = DatabaseHelper.openConnection();  
                PreparedStatement pstm = con.prepareStatement(sql);) 
        {
            pstm.setString(1, prd.getMaSp());
            pstm.setString(2, prd.getTenSp());
            pstm.setString(3, prd.getDonViTinh());
            pstm.setString(4, Integer.toString(prd.getSlTon()));
            pstm.setString(5, Integer.toString(prd.getGiaBan()));
            
            return pstm.executeUpdate() > 0;
        }
    }
    
    
    // cập nhật thông tin sản phẩm
    public boolean updateProduct(Product prd) throws Exception{
        String sql = "update VATTU set TENVT=?,DVT=?,SOLUONGTON=?,GIABAN=? where MAVT=?";
         try (
                Connection con = DatabaseHelper.openConnection();  
                PreparedStatement pstm = con.prepareStatement(sql);) 
        {
            
            pstm.setString(1, prd.getTenSp());
            pstm.setString(2, prd.getDonViTinh());
            pstm.setString(3, Integer.toString(prd.getSlTon()));
            pstm.setString(4, Integer.toString(prd.getGiaBan()));
            pstm.setString(5, prd.getMaSp());
            return pstm.executeUpdate() > 0;
        }
    }
    
    
    //Xóa sản phẩm
    public boolean delete(String masp) throws Exception {
        String sql = "delete from VATTU where MAVT= ?";
        try (
            Connection con = DatabaseHelper.openConnection();  
            PreparedStatement pstm = con.prepareStatement(sql);) 
        {
            pstm.setString(1, masp);
            return pstm.executeUpdate() > 0;
            
        }
    }
    
    
    //Tìm kiếm sản phẩm chỉ định
    public Product findProductByMa(String msp) throws Exception{
        String sql = "select * from VATTU where MAVT= ?";
        try (
            Connection con = DatabaseHelper.openConnection();  
            PreparedStatement pstm = con.prepareStatement(sql) ;
           )
        {
            pstm.setString(1, msp);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Product prd = new Product();
                prd.setMaSp(rs.getString(1));
                prd.setTenSp(rs.getString(2));
                prd.setDonViTinh(rs.getString(3));
                prd.setSlTon(rs.getInt(4));
                prd.setGiaBan(rs.getInt(5));
                return prd;
            }
     
        }
        return null;
    }
    
    
    // set select cho sản phẩm được tìm thấy
    public int selectRowTable(String msp) throws Exception{
        Product temp = findProductByMa(msp);
        if(temp!= null){
            int i = 0;
            List<Product> listSP = loadListProduct();
            for(Product sp : listSP){
                boolean check = sp.getMaSp().equals(msp);
                if(check){
                    return i;
                }
                i++;
            }
        }
        return -1;
    }
}
