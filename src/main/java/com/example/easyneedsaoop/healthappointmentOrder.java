package com.example.easyneedsaoop;

import java.util.Date;

public class healthappointmentOrder {
    private int id;
    private int cardID;
    private String docName;
    private String userName;
    private Date date;
    private String ownerUserName;

    public healthappointmentOrder(int id, int cardID, String docName, String userName, Date date, String ownerUserName) {
        this.id = id;
        this.cardID = cardID;
        this.docName = docName;
        this.userName = userName;
        this.date = date;
        this.ownerUserName = ownerUserName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setOwnerUserName(String ownerUserName) {
        this.ownerUserName = ownerUserName;
    }
}
