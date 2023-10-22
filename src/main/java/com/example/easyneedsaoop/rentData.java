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

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
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

    public boolean isDn_Draw() {
        return dn_Draw;
    }

    public void setDn_Draw(boolean dn_Draw) {
        this.dn_Draw = dn_Draw;
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
    private int person;
    private String contact;
    private double rent;
    private String address;
    private String einfo;
    private String image;
    private boolean bachelor;
    private boolean sublet;
    private boolean dn_Draw;
    private Date date;


    public rentData(int id, String ownerName,
                    String houseName, int roomNo,
                    int person, String contactNo,
                    double rent, String address,
                    String einfo, String image,
                    boolean bachelor, boolean sublet,
                    boolean dn_Draw,Date date) {
        this.id = id;
        this.ownerName = ownerName;
        this.houseName = houseName;
        this.userName = data.username;
        this.room = roomNo;
        this.person = person;
        this.contact = contactNo;
        this.rent = rent;
        this.address = address;
        this.einfo = einfo;
        this.image = image;
        this.bachelor = bachelor;
        this.sublet = sublet;
        this.dn_Draw = dn_Draw;
        this.date=date;
    }

}
