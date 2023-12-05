package com.example.easyneedsaoop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class OrderShow {

    @FXML
    private Label customer_name;

    @FXML
    private Button details_btn;

    @FXML
    private Label date;

    @FXML
    private Label house_Name;
    rentOrderData data;
    CourseData courseData;
    CateringData cateringData;
    ClothShopData clothShopData;

    public void setData(rentOrderData data){
        this.data=data;
        customer_name.setText(data.getOwnerName());
        house_Name.setText(data.getHouseName());
        date.setText(String.valueOf(data.getDate()));
    }
    public void setData(CourseData data){
        this.courseData=data;
        customer_name.setText(courseData.getUserName());
        house_Name.setText(String.valueOf(courseData.getCourseID()));
        date.setText(String.valueOf(data.getDate()));
    }
    public void setData(ClothShopData clothShopData){
        this.clothShopData=clothShopData;
        customer_name.setText(clothShopData.getUsername());
        house_Name.setText(String.valueOf(clothShopData.getProductID()));
        date.setText(String.valueOf(data.getDate()));
    }
}
