package com.nguyenducthang.cau2;

import androidx.annotation.NonNull;

public class Sinhvien {
    private String masv;
    private String tensv;
    private String lop;

    public Sinhvien(String masv, String tensv, String lop) {
        this.masv = masv;
        this.tensv = tensv;
        this.lop = lop;
    }

    public String getMasv() {
        return masv;
    }

    public String getTensv() {
        return tensv;
    }

    public String getLop() {
        return lop;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    @NonNull
    @Override
    public String toString() {
        return "ten sinh vien:"+tensv+"\n"+"lop:"+lop+"\n"+"ma sinh vien:"+masv;
    }
}
