
package com.dat.Order;

public class DatHang extends Order{
    private String nhacungcap;

    public DatHang() {
        super();
    }
    
    
    public String getNhaCungCap() {
        return nhacungcap;
    }

    public void setNhaCungCap(String nhacungcap) {
        this.nhacungcap = nhacungcap;
    }
}