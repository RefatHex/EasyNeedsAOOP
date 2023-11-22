package com.example.easyneedsaoop;

import java.util.Date;

public class rentData {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(int flatNo) {
        this.flatNo = flatNo;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEinfo() {
        return einfo;
    }

    public void setEinfo(String einfo) {
        this.einfo = einfo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isBachelor() {
        return bachelor;
    }

    public void setBachelor(boolean bachelor) {
        this.bachelor = bachelor;
    }

    public boolean isSublet() {
        return sublet;
    }

    public void setSublet(boolean sublet) {
        this.sublet = sublet;
    }

    public boolean isDn_draw() {
        return dn_draw;
    }

    public void setDn_draw(boolean dn_draw) {
        this.dn_draw = dn_draw;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private int id;
    private String ownerName;
    private String houseName;
    private String userName;
    private int room;
    private int flatNo;
    private String contact;
    private double rent;
    private String address;
    private String einfo;
    private String image;
    private boolean bachelor;
    private boolean sublet;
    private boolean dn_draw;
    private Date date;


    public rentData(int id, String ownerName,
                    String houseName, int room,
                    int flatNo, String contact,
                    double rent, String address,
                    String einfo, String image,
                    boolean bachelor, boolean sublet,
                    boolean dn_draw, Date date) {
        this.id = id;
        this.ownerName = ownerName;
        this.houseName = houseName;
        this.userName = data.username;
        this.room = room;
        this.flatNo = flatNo;
        this.contact = contact;
        this.rent = rent;
        this.address = address;
        this.einfo = einfo;
        this.image = image;
        this.bachelor = bachelor;
        this.sublet = sublet;
        this.dn_draw = dn_draw;
        this.date=date;
    }

    public rentData(int id, String houseName, int flatNo, double rent, String address, String image) {
        this.id = id;
        this.houseName = houseName;
        this.flatNo = flatNo;
        this.rent = rent;
        this.address = address;
        this.image = image;
    }

    public rentData(int id, String ownerName, String houseName, String userName, double rent, String address, String einfo, String image, boolean bachelor, boolean sublet, boolean dn_draw) {
        this.id = id;
        this.ownerName = ownerName;
        this.houseName = houseName;
        this.userName = userName;
        this.rent = rent;
        this.address = address;
        this.einfo = einfo;
        this.image = image;
        this.bachelor = bachelor;
        this.sublet = sublet;
        this.dn_draw = dn_draw;
    }
}
