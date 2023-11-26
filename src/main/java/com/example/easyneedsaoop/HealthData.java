package com.example.easyneedsaoop;

import java.util.Date;

public class HealthData {
    int cardID;
    String docName;
    String userName;
    String docMajor;
    int  start;
    int end;
    double fee;
    String day;
    String service;
    String extraInfo;
    Date date;
    String hospital;
    String image;

    public HealthData(int cardID, String docName, String userName, String docMajor, int start, int end, double fee, String day, String service, String extraInfo, Date date) {
        this.cardID = cardID;
        this.docName = docName;
        this.userName = userName;
        this.docMajor = docMajor;
        this.start = start;
        this.end = end;
        this.fee = fee;
        this.day = day;
        this.service = service;
        this.extraInfo = extraInfo;
        this.date = date;
    }

    public HealthData(int cardID, String docName, String userName, String docMajor, int start, int end, double fee, String day, String service, String extraInfo, Date date, String hospital, String image) {
        this.cardID = cardID;
        this.docName = docName;
        this.userName = userName;
        this.docMajor = docMajor;
        this.start = start;
        this.end = end;
        this.fee = fee;
        this.day = day;
        this.service = service;
        this.extraInfo = extraInfo;
        this.date = date;
        this.hospital = hospital;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getHospital() {
        return hospital;
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

    public String getDocMajor() {
        return docMajor;
    }

    public void setDocMajor(String docMajor) {
        this.docMajor = docMajor;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public double getFee() {
        return fee;
    }


    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "HealthData{" +
                "cardID=" + cardID +
                ", docName='" + docName + '\'' +
                ", userName='" + userName + '\'' +
                ", docMajor='" + docMajor + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", fee=" + fee +
                ", day='" + day + '\'' +
                ", service='" + service + '\'' +
                ", extraInfo='" + extraInfo + '\'' +
                ", date=" + date +
                ", hospital='" + hospital + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
