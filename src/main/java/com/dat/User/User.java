
package com.dat.User;


public class User {
    private String account;
    private String password;
    private String permission;
    private String maNV;
    private String tenNV;
    
    public User(){}

    public User(String account, String password, String permission, String maNV, String tenNV) {
        this.account = account;
        this.password = password;
        this.permission = permission;
        this.maNV = maNV;
        this.tenNV = tenNV;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }
    
    
    
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
    
    public String toString(){
        return "Mã nhân viên: " + maNV + "/nTên nhân viên: " + tenNV + "/nAccount: " + account + "/nPassword: " + password + "/nPermission: "+ permission;
    }
}
