package com.example.easyneedsaoop;

import java.util.Date;

public class rentOrderData {
    private int id;
    private String ownerName;
    private String houseName;

    private String ownerUserName;
    private String tanentUserName;
    private double rent;
    private String address;
    private String nidImage;
    private Date date;

    public int getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getHouseName() {
        return houseName;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public String getTanentUserName() {
        return tanentUserName;
    }

    public double getRent() {
        return rent;
    }

    public String getAddress() {
        return address;
    }

    public String getNidImage() {
        return nidImage;
    }

    public Date getDate() {
        return date;
    }

    public rentOrderData(int id, String ownerName, String houseName, String ownerUserName, String tanentUserName, double rent, String address, String nidImage, Date date) {
        this.id = id;
        this.ownerName = ownerName;
        this.houseName = houseName;
        this.ownerUserName = ownerUserName;
        this.tanentUserName = tanentUserName;
        this.rent = rent;
        this.address = address;
        this.nidImage = nidImage;
        this.date = date;
    }
}
