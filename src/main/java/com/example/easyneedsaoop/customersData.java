package com.example.easyneedsaoop;

import java.util.Date;

public class customersData {
    private int id;
   private Date date;

    private String name;
    private String occupation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public customersData(int id, Date date, String name, String occupation) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.occupation = occupation;
    }
}
