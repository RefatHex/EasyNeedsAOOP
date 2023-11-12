package com.example.easyneedsaoop;

import java.util.Date;

public class CourseData {
    int courseID;
    String courseName;
    double price;
    String instructorName;
    String userName;
    String description;
    String image;
    String info;
    String category;
    String type;
    Date date;

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CourseData(int courseID, String courseName, double price, String instructorName, String userName, String description, String image, String info, String category, String type, Date date) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.price = price;
        this.instructorName = instructorName;
        this.userName = userName;
        this.description = description;
        this.image = image;
        this.info = info;
        this.category = category;
        this.type = type;
        this.date = date;
    }
}
