
package com.dat.Order;

import java.util.List;


public class Order {
    private String maDon;
    private String date;
    private String tenKhachHang;
    private List<CTSP> listSP;

    public String getMaDon() {
        return maDon;
    }

    public void setMaDon(String maDon) {
        this.maDon = maDon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public List<CTSP> getListSP() {
        return listSP;
    }

    public void setListSP(List<CTSP> listSP) {
        this.listSP = listSP;
    }
    
    
    public void addSP(CTSP ct){
        listSP.add(ct);
    }
}
