
package com.dat.Order;
import java.util.List;

public class PhieuNhap {
    private String maPN;
    private String date_XN;
    private String maSODDH;
    private List<CTSP> listSP;
    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public String getDate_XN() {
        return date_XN;
    }

    public void setDate_XN(String date_XN) {
        this.date_XN = date_XN;
    }
    public String getMaSODDH() {
        return maSODDH;
    }

    public void setMaSODDH(String maSODDH) {
        this.maSODDH = maSODDH;
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
