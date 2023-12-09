package com.example.easyneedsaoop;

import java.util.Date;

public class userData {
    private int id;
            private String name;
    private String username;
            private String occupation;
    private String password;
            private Date date;

    public userData(int id, String name, String username,String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password=password;
    }

    public userData(int id, String name, String username, String occupation, String password, Date date) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.occupation = occupation;
        this.password = password;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
