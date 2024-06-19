package com.letrungquyen.model;

public class Oto {
    int code;
    String name;
    double price;
    String address;

    byte[] image;
    String category;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Oto(int code, String name, double price, String address, byte[] image, String category) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.address = address;
        this.image = image;
        this.category = category;
    }

}
