
package com.dat.Order;

public class DatHang extends Order{
    private String nhacungcap;
    //privete int giamoi;
    
    public DatHang() {
        super();
    }
    
    
    public String getNhaCungCap() {
        return nhacungcap;
    }

    public void setNhaCungCap(String nhacungcap) {
        this.nhacungcap = nhacungcap;
    }
//     public int getGiaMoi() {
//        return giamoi;
//    }
//
//    public void setGiaMoi(int giamoi) {
//        this.giamoi = giamoi;
//    }
}