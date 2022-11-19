
package com.dat.Order;


public class DonHang extends Order{
    private String tenKhachHang;

    public DonHang() {
        super();
    }
    
    
    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }
}
