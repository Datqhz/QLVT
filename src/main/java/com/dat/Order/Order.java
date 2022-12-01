
package com.dat.Order;

import java.util.List;


public class Order {
    protected String maDon;
    protected String date;
    protected List<CTSP> listSP;
    protected boolean tt;

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

    

    public List<CTSP> getListSP() {
        return listSP;
    }

    public void setListSP(List<CTSP> listSP) {
        this.listSP = listSP;
    }
    
    
    public void addSP(CTSP ct){
        listSP.add(ct);
    }
    public void setTT(boolean tt){
        this.tt = tt;
    }
    public boolean getTT(){
        return tt;
    }
    
}
