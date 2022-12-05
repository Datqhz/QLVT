
package com.dat.Order;

public class DatHang extends Order{
    private String nhacungcap;
    private String trangthai;
    
    public DatHang() {
        super();
    }
    
    
    public String getNhaCungCap() {
        return nhacungcap;
    }

    public void setNhaCungCap(String nhacungcap) {
        this.nhacungcap = nhacungcap;
    }
    
    public String getTrangThai() {
        return trangthai;
    }

    public void setTrangThai(String trangthai) {
        this.trangthai = trangthai;
    }
}