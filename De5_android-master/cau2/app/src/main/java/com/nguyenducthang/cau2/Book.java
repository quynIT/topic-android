package com.nguyenducthang.cau2;

import androidx.annotation.NonNull;

public class Book {
    private int masach;
    private String tensach;
    private  int giasach;


    public Book(int masach, String tensach, int giasach) {
        this.masach = masach;
        this.tensach = tensach;
        this.giasach = giasach;
    }

    @Override
    public String toString() {
        return "mã sách:"+ masach+"\n"+"Tên Sách:"+ tensach+"\n"+"Giá Bán:"+giasach;
    }

    public int getMasach() {
        return masach;
    }

    public String getTensach() {
        return tensach;
    }

    public int getGiasach() {
        return giasach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public void setGiasach(int giasach) {
        this.giasach = giasach;
    }
}
