
package com.dat.Product;

import java.text.NumberFormat;
import java.util.Locale;

public class Product {
    private String maSp;
    private String tenSp;
    private String donViTinh;
    private int slTon;
    private int giaBan;
    
    public Product() {
    }

    public Product(String maSp, String tenSp, String donViTinh, int slTon, int giaBan) {
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.donViTinh = donViTinh;
        this.slTon = slTon;
        this.giaBan = giaBan;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public int getSlTon() {
        return slTon;
    }

    public void setSlTon(int slTon) {
        this.slTon = slTon;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    @Override
    public String toString() {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(giaBan);
        return "Mã sản phẩm: " + maSp + "\nTên sản phẩm:" + tenSp + "\nĐơn vị tính: " + donViTinh + "\nGiá bán: " + str1;
    }
    
    
}
