package com.nguyenducthang.cau2;

import androidx.annotation.NonNull;

public class Sanpham {
    private String masp;
    private String tensp;
    private int gia;

    public Sanpham(String masp, String tensp, int gia) {
        this.masp = masp;
        this.tensp = tensp;
        this.gia = gia;
    }

    public String getMasp() {
        return masp;
    }

    public String getTensp() {
        return tensp;
    }

    public int getGia() {
        return gia;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    @NonNull
    @Override
    public String toString() {
        return "ma san pham:"+masp+"\n"+"ten san pham:"+tensp+"\n"+"gia san pham ;"+gia;
    }
}
